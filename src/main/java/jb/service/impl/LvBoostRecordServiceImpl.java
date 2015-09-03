package jb.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.LvBoostRecordDaoI;
import jb.model.TlvBoostRecord;
import jb.pageModel.DataGrid;
import jb.pageModel.LvBoostRecord;
import jb.pageModel.PageHelper;
import jb.service.LvBoostRecordServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvBoostRecordServiceImpl extends BaseServiceImpl<LvBoostRecord> implements LvBoostRecordServiceI {

	@Autowired
	private LvBoostRecordDaoI lvBoostRecordDao;

	@Override
	public DataGrid dataGrid(LvBoostRecord lvBoostRecord, PageHelper ph) {
		List<LvBoostRecord> ol = new ArrayList<LvBoostRecord>();
		String hql = " from TlvBoostRecord t ";
		DataGrid dg = dataGridQuery(hql, ph, lvBoostRecord, lvBoostRecordDao);
		@SuppressWarnings("unchecked")
		List<TlvBoostRecord> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TlvBoostRecord t : l) {
				LvBoostRecord o = new LvBoostRecord();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(LvBoostRecord lvBoostRecord, Map<String, Object> params) {
		String whereHql = "";	
		if (lvBoostRecord != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(lvBoostRecord.getActivtyId())) {
				whereHql += " and t.activtyId = :activtyId";
				params.put("activtyId", lvBoostRecord.getActivtyId());
			}		
			if (lvBoostRecord.getOpenId() != null) {
				whereHql += " and t.openId = :openId";
				params.put("openId", lvBoostRecord.getOpenId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(LvBoostRecord lvBoostRecord) {
		TlvBoostRecord t = new TlvBoostRecord();
		BeanUtils.copyProperties(lvBoostRecord, t);
		t.setId(UUID.randomUUID().toString());
		t.setBoostTime(new Date());
		lvBoostRecordDao.save(t);
	}

	@Override
	public LvBoostRecord get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TlvBoostRecord t = lvBoostRecordDao.get("from TlvBoostRecord t  where t.id = :id", params);
		LvBoostRecord o = new LvBoostRecord();
		BeanUtils.copyProperties(t, o);
		return o;
	}
	
	@Override
	public LvBoostRecord get(LvBoostRecord record) {
		Map<String, Object> params = new HashMap<String, Object>();
		String where = whereHql(record, params);
		TlvBoostRecord t = lvBoostRecordDao.get("from TlvBoostRecord t  " + where, params);
		if(t != null) {
			LvBoostRecord o = new LvBoostRecord();
			BeanUtils.copyProperties(t, o);
			return o;
		}
		return null;
	}

	@Override
	public void edit(LvBoostRecord lvBoostRecord) {
		TlvBoostRecord t = lvBoostRecordDao.get(TlvBoostRecord.class, lvBoostRecord.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(lvBoostRecord, t, new String[] { "id"},true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvBoostRecordDao.delete(lvBoostRecordDao.get(TlvBoostRecord.class, id));
	}


	/**
	 * 获取助力信息列表
	 */
	public List<LvBoostRecord> findBoostInfoList(Integer openId) {
		List<LvBoostRecord> rl = new ArrayList<LvBoostRecord>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		params.put("startTime", cal.getTime());
		cal.add(Calendar.HOUR_OF_DAY, 1);
		params.put("endTime", cal.getTime());
		params.put("openId", openId);
		List<TlvBoostRecord> l = lvBoostRecordDao.find("from TlvBoostRecord t left join fetch t.tlvBoostActivty b where t.boostTime between :startTime and :endTime and t.openId = :openId", params);
		if(l != null && l.size() > 0) {
			LvBoostRecord r = null;
			for(TlvBoostRecord t : l) {
				r = new LvBoostRecord();
				MyBeanUtils.copyProperties(t, r);
				r.setGoodsImg(t.getTlvBoostActivty().getGoodsImg());
				rl.add(r);
			}
		}
		
		return rl;
	}

	/**
	 * 挖宝记录列表
	 */
	public List<LvBoostRecord> findBoostRecordList(Integer openId, Integer type) {
		List<LvBoostRecord> rl = new ArrayList<LvBoostRecord>();
		
		if(type == 1) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("openId", openId);
			List<TlvBoostRecord> l = lvBoostRecordDao.find("from TlvBoostRecord t left join fetch t.tlvBoostActivty b where t.openId = :openId", params);
			if(l != null && l.size() > 0) {
				LvBoostRecord r = null;
				Calendar cal = Calendar.getInstance();
				for(TlvBoostRecord t : l) {
					if(t.getAssistNum().intValue() < t.getTlvBoostActivty().getAssistNum().intValue()) continue;
					r = new LvBoostRecord();
					MyBeanUtils.copyProperties(t, r);
					r.setGoodsImg(t.getTlvBoostActivty().getGoodsImg());
					r.setGoodsName(t.getTlvBoostActivty().getGoodsName());
					cal.setTime(t.getBoostTime());
					cal.add(Calendar.HOUR_OF_DAY, 1);
					cal.set(Calendar.MINUTE, 10);
					cal.set(Calendar.SECOND, 0);
					r.setOpenTime(cal.getTime());
					rl.add(r);
				}
			}
		}
		return rl;
	}

	/**
	 * 助力墙列表（男）
	 */
	@SuppressWarnings("rawtypes")
	public DataGrid dataGridAssistList(Integer openId, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		dg.setPage(Long.valueOf(ph.getPage()));
		dg.setPageSize(Long.valueOf(ph.getRows()));
		
		Map<String, Object> params = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		params.put("startTime", cal.getTime());
		cal.add(Calendar.HOUR_OF_DAY, 1);
		params.put("endTime", cal.getTime());
		String sql = " from lv_boost_record t join lv_account a on a.openId = t.openId "
				+ "where t.boostTime between :startTime and :endTime ";
		
		String orderString = " order by t.boostTime desc, a.visitNum desc, a.followNum desc";
		
		String selectSql = "select a.openId, a.headImg, a.nickName, t.id boostRecordId, t.boostTime, "
				+ "(case when exists(select * from lv_assist_log l where l.boostRecordId = t.id and l.openId = "+openId+") then 1 "
				+ "else 2 end) isAssist";
		List<Map> l = lvBoostRecordDao.findBySql2Map(selectSql + sql + orderString, params, ph.getPage(), ph.getRows());
		dg.setTotal(lvBoostRecordDao.countBySql("select count(*) " + sql, params).longValue());
		
		dg.setRows(l);
		
		return dg;
	}


}
