package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.LvFeedbackDaoI;
import jb.model.TlvFeedback;
import jb.pageModel.DataGrid;
import jb.pageModel.LvFeedback;
import jb.pageModel.PageHelper;
import jb.service.LvFeedbackServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvFeedbackServiceImpl extends BaseServiceImpl<LvFeedback> implements LvFeedbackServiceI {

	@Autowired
	private LvFeedbackDaoI lvFeedbackDao;

	@Override
	public DataGrid dataGrid(LvFeedback lvFeedback, PageHelper ph) {
		List<LvFeedback> ol = new ArrayList<LvFeedback>();
		String hql = " from TlvFeedback t ";
		DataGrid dg = dataGridQuery(hql, ph, lvFeedback, lvFeedbackDao);
		@SuppressWarnings("unchecked")
		List<TlvFeedback> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TlvFeedback t : l) {
				LvFeedback o = new LvFeedback();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(LvFeedback lvFeedback, Map<String, Object> params) {
		String whereHql = "";	
		if (lvFeedback != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(lvFeedback.getAccountId())) {
				whereHql += " and t.accountId = :accountId";
				params.put("accountId", lvFeedback.getAccountId());
			}		
			if (!F.empty(lvFeedback.getContactWay())) {
				whereHql += " and t.contactWay = :contactWay";
				params.put("contactWay", lvFeedback.getContactWay());
			}		
			if (!F.empty(lvFeedback.getContent())) {
				whereHql += " and t.content = :content";
				params.put("content", lvFeedback.getContent());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(LvFeedback lvFeedback) {
		TlvFeedback t = new TlvFeedback();
		BeanUtils.copyProperties(lvFeedback, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		lvFeedbackDao.save(t);
	}

	@Override
	public LvFeedback get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TlvFeedback t = lvFeedbackDao.get("from TlvFeedback t  where t.id = :id", params);
		LvFeedback o = new LvFeedback();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(LvFeedback lvFeedback) {
		TlvFeedback t = lvFeedbackDao.get(TlvFeedback.class, lvFeedback.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(lvFeedback, t, new String[] { "id" , "createdatetime" }, true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvFeedbackDao.delete(lvFeedbackDao.get(TlvFeedback.class, id));
	}

}
