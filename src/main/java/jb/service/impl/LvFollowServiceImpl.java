package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.dao.LvFollowDaoI;
import jb.model.TlvFollow;
import jb.pageModel.DataGrid;
import jb.pageModel.LvFollow;
import jb.pageModel.PageHelper;
import jb.service.LvFollowServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvFollowServiceImpl extends BaseServiceImpl<LvFollow> implements LvFollowServiceI {

	@Autowired
	private LvFollowDaoI lvFollowDao;

	@Override
	public DataGrid dataGrid(LvFollow lvFollow, PageHelper ph) {
		List<LvFollow> ol = new ArrayList<LvFollow>();
		String hql = " from TlvFollow t ";
		DataGrid dg = dataGridQuery(hql, ph, lvFollow, lvFollowDao);
		@SuppressWarnings("unchecked")
		List<TlvFollow> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TlvFollow t : l) {
				LvFollow o = new LvFollow();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(LvFollow lvFollow, Map<String, Object> params) {
		String whereHql = "";	
		if (lvFollow != null) {
		}	
		return whereHql;
	}

	@Override
	public void add(LvFollow lvFollow) {
		TlvFollow t = new TlvFollow();
		BeanUtils.copyProperties(lvFollow, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		lvFollowDao.save(t);
	}

	@Override
	public LvFollow get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TlvFollow t = lvFollowDao.get("from TlvFollow t  where t.id = :id", params);
		LvFollow o = new LvFollow();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(LvFollow lvFollow) {
		TlvFollow t = lvFollowDao.get(TlvFollow.class, lvFollow.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(lvFollow, t, new String[] { "id" , "createdatetime" }, true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvFollowDao.delete(lvFollowDao.get(TlvFollow.class, id));
	}

}
