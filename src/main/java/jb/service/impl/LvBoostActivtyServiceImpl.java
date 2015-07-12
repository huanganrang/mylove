package jb.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.LvBoostActivtyDaoI;
import jb.model.TlvBoostActivty;
import jb.pageModel.DataGrid;
import jb.pageModel.LvBoostActivty;
import jb.pageModel.PageHelper;
import jb.service.LvBoostActivtyServiceI;
import jb.util.DateUtil;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvBoostActivtyServiceImpl extends BaseServiceImpl<LvBoostActivty> implements LvBoostActivtyServiceI {

	@Autowired
	private LvBoostActivtyDaoI lvBoostActivtyDao;

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
			if (!F.empty(lvBoostActivty.getGoodsImg())) {
				whereHql += " and t.goodsImg = :goodsImg";
				params.put("goodsImg", lvBoostActivty.getGoodsImg());
			}		
			if (!F.empty(lvBoostActivty.getGoodsDetailImg())) {
				whereHql += " and t.goodsDetailImg = :goodsDetailImg";
				params.put("goodsDetailImg", lvBoostActivty.getGoodsDetailImg());
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
	public List<LvBoostActivty> findAllList() {
		List<LvBoostActivty> lb = new ArrayList<LvBoostActivty>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		params.put("startTime", DateUtil.parse(DateUtil.format(cal.getTime(), "HH:mm:ss"), "HH:mm:ss"));
		cal.set(Calendar.HOUR_OF_DAY, 1);
		params.put("endTime", DateUtil.parse(DateUtil.format(cal.getTime(), "HH:mm:ss"), "HH:mm:ss"));
		
		List<TlvBoostActivty> l = lvBoostActivtyDao.find("from TlvBoostActivty t where t.startTime <= :startTime and t.endTime >= :endTime", params);
		if(l != null && l.size() > 0) {
			for(TlvBoostActivty t : l) {
				LvBoostActivty b = new LvBoostActivty();
				MyBeanUtils.copyProperties(t, b, true);
				lb.add(b);
			}
		}
		return lb;
	}
	

}
