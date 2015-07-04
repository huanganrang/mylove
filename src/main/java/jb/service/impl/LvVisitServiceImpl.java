package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.LvVisitDaoI;
import jb.model.TlvVisit;
import jb.pageModel.DataGrid;
import jb.pageModel.LvVisit;
import jb.pageModel.PageHelper;
import jb.service.LvVisitServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvVisitServiceImpl extends BaseServiceImpl<LvVisit> implements LvVisitServiceI {

	@Autowired
	private LvVisitDaoI lvVisitDao;

	@Override
	public DataGrid dataGrid(LvVisit lvVisit, PageHelper ph) {
		List<LvVisit> ol = new ArrayList<LvVisit>();
		String hql = " from TlvVisit t ";
		DataGrid dg = dataGridQuery(hql, ph, lvVisit, lvVisitDao);
		@SuppressWarnings("unchecked")
		List<TlvVisit> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TlvVisit t : l) {
				LvVisit o = new LvVisit();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(LvVisit lvVisit, Map<String, Object> params) {
		String whereHql = "";	
		if (lvVisit != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(lvVisit.getAccountId())) {
				whereHql += " and t.accountId = :accountId";
				params.put("accountId", lvVisit.getAccountId());
			}		
			if (!F.empty(lvVisit.getVisitAccountId())) {
				whereHql += " and t.visitAccountId = :visitAccountId";
				params.put("visitAccountId", lvVisit.getVisitAccountId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(LvVisit lvVisit) {
		TlvVisit t = new TlvVisit();
		BeanUtils.copyProperties(lvVisit, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		lvVisitDao.save(t);
	}

	@Override
	public LvVisit get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TlvVisit t = lvVisitDao.get("from TlvVisit t  where t.id = :id", params);
		LvVisit o = new LvVisit();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(LvVisit lvVisit) {
		TlvVisit t = lvVisitDao.get(TlvVisit.class, lvVisit.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(lvVisit, t, new String[] { "id" , "createdatetime" }, true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvVisitDao.delete(lvVisitDao.get(TlvVisit.class, id));
	}

}
