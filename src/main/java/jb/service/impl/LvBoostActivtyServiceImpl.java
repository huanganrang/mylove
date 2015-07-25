package jb.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.LvBoostActivtyDaoI;
import jb.dao.LvBoostRecordDaoI;
import jb.model.TlvBoostActivty;
import jb.model.TlvBoostRecord;
import jb.pageModel.DataGrid;
import jb.pageModel.LvBoostActivty;
import jb.pageModel.LvBoostRecord;
import jb.pageModel.PageHelper;
import jb.service.LvBoostActivtyServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvBoostActivtyServiceImpl extends BaseServiceImpl<LvBoostActivty> implements LvBoostActivtyServiceI {

	@Autowired
	private LvBoostActivtyDaoI lvBoostActivtyDao;
	
	@Autowired
	private LvBoostRecordDaoI lvBoostRecordDao;

	@Override
	public DataGrid dataGrid(LvBoostActivty lvBoostActivty, PageHelper ph) {
		List<LvBoostActivty> ol = new ArrayList<LvBoostActivty>();
		String hql = " from TlvBoostActivty t ";
		DataGrid dg = dataGridQuery(hql, ph, lvBoostActivty, lvBoostActivtyDao);
		@SuppressWarnings("unchecked")
		List<TlvBoostActivty> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TlvBoostActivty t : l) {
				LvBoostActivty o = new LvBoostActivty();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(LvBoostActivty lvBoostActivty, Map<String, Object> params) {
		String whereHql = "";	
		if (lvBoostActivty != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(lvBoostActivty.getGoodsName())) {
				whereHql += " and t.goodsName = :goodsName";
				params.put("goodsName", lvBoostActivty.getGoodsName());
			}		
			if (lvBoostActivty.getStatus() != null) {
				whereHql += " and t.status = :status";
				params.put("status", lvBoostActivty.getStatus());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(LvBoostActivty lvBoostActivty) {
		TlvBoostActivty t = new TlvBoostActivty();
		BeanUtils.copyProperties(lvBoostActivty, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		lvBoostActivtyDao.save(t);
	}

	@Override
	public LvBoostActivty get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TlvBoostActivty t = lvBoostActivtyDao.get("from TlvBoostActivty t  where t.id = :id", params);
		LvBoostActivty o = new LvBoostActivty();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(LvBoostActivty lvBoostActivty) {
		TlvBoostActivty t = lvBoostActivtyDao.get(TlvBoostActivty.class, lvBoostActivty.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(lvBoostActivty, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvBoostActivtyDao.delete(lvBoostActivtyDao.get(TlvBoostActivty.class, id));
	}

	@Override
	public List<LvBoostActivty> findAllList(Integer openId, Integer hourOfDay) {
		List<LvBoostActivty> lb = new ArrayList<LvBoostActivty>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		hourOfDay = hourOfDay == null ? cal.get(Calendar.HOUR_OF_DAY) : hourOfDay;
		params.put("hourOfDay", hourOfDay);
		params.put("status", 1);
		
		List<TlvBoostActivty> l = lvBoostActivtyDao.find("from TlvBoostActivty t where t.status = :status and (t.hourOfDay = :hourOfDay or t.hourOfDay = -1)", params);
		if(l != null && l.size() > 0) {
			for(TlvBoostActivty t : l) {
				LvBoostActivty b = new LvBoostActivty();
				MyBeanUtils.copyProperties(t, b, true);
				
				params = new HashMap<String, Object>();
				cal = Calendar.getInstance();
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				params.put("startTime", cal.getTime());
				cal.add(Calendar.HOUR_OF_DAY, 1);
				params.put("endTime", cal.getTime());
				b.setRecordNum(lvBoostRecordDao.count("select count(*) from TlvBoostRecord t where t.boostTime between :startTime and :endTime", params).intValue());
				if(cal.get(Calendar.HOUR_OF_DAY) - 1 == hourOfDay) {
					params.put("openId", openId);
					params.put("activtyId", t.getId());
					TlvBoostRecord br = lvBoostRecordDao.get("from TlvBoostRecord t left join fetch t.tlvBoostActivty b where t.boostTime between :startTime and :endTime and t.openId = :openId and t.activtyId = :activtyId", params);
					if(br == null) {
						b.setAssistedNum(-1);
					} else {
						b.setAssistedNum(br.getAssistNum());
					}
				}
				lb.add(b);
			}
		}
		return lb;
	}

	/**
	 * 商品详情（包括挖宝记录）
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public LvBoostActivty getActivtyDetail(String id) {
		LvBoostActivty ba = new LvBoostActivty();
		TlvBoostActivty t = lvBoostActivtyDao.get(TlvBoostActivty.class, id);
		MyBeanUtils.copyProperties(t, ba, true);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activtyId", id);
		List<Map> l = lvBoostRecordDao.findBySql2Map("select r.boostTime, a.nickName from lv_boost_record r left join lv_account a on a.openId = r.openId where r.activtyId = :activtyId order by r.boostTime desc limit 10", params);
		List<LvBoostRecord> rl = new ArrayList<LvBoostRecord>();
		if (l != null && l.size() > 0) {
			for (Map m : l) {
				LvBoostRecord r = new LvBoostRecord();
				try {
					org.apache.commons.beanutils.BeanUtils.populate(r, m);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				rl.add(r);
			}
		}	
		ba.setRecordList(rl);
		return ba;
	}

}
