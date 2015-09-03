package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.dao.LvNotifyDaoI;
import jb.model.TlvNotify;
import jb.pageModel.DataGrid;
import jb.pageModel.LvNotify;
import jb.pageModel.PageHelper;
import jb.service.LvNotifyServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvNotifyServiceImpl extends BaseServiceImpl<LvNotify> implements LvNotifyServiceI {

	@Autowired
	private LvNotifyDaoI lvNotifyDao;

	@Override
	public DataGrid dataGrid(LvNotify lvNotify, PageHelper ph) {
		List<LvNotify> ol = new ArrayList<LvNotify>();
		String hql = " from TlvNotify t ";
		DataGrid dg = dataGridQuery(hql, ph, lvNotify, lvNotifyDao);
		@SuppressWarnings("unchecked")
		List<TlvNotify> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TlvNotify t : l) {
				LvNotify o = new LvNotify();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(LvNotify lvNotify, Map<String, Object> params) {
		String whereHql = "";	
		if (lvNotify != null) {
			whereHql += " where 1=1 ";
			if (lvNotify.getOpenId() != null) {
				whereHql += " and t.openId = :openId";
				params.put("openId", lvNotify.getOpenId());
			}		
			if (lvNotify.getNotifyOpenId() != null) {
				whereHql += " and t.notifyOpenId = :notifyOpenId";
				params.put("notifyOpenId", lvNotify.getNotifyOpenId());
			}		
		}	
		return whereHql;
	}

	@Override
	public int add(LvNotify lvNotify) {
		if(get(lvNotify.getOpenId(), lvNotify.getNotifyOpenId()) != null) {
			return -1;
		}
		
		TlvNotify t = new TlvNotify();
		BeanUtils.copyProperties(lvNotify, t);
		t.setId(UUID.randomUUID().toString());
		t.setCreateTime(new Date());
		lvNotifyDao.save(t);
		
		return 1;
	}
	
	public LvNotify get(Integer openId, Integer notifyOpenId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openId);
		params.put("notifyOpenId", notifyOpenId);		
		TlvNotify t = lvNotifyDao.get("from TlvNotify t  where t.openId = :openId and t.notifyOpenId = :notifyOpenId", params);
		if(t == null)
			return null;
		LvNotify n = new LvNotify();
		BeanUtils.copyProperties(t, n);
		return n;
	}

	@Override
	public LvNotify get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TlvNotify t = lvNotifyDao.get("from TlvNotify t  where t.id = :id", params);
		LvNotify o = new LvNotify();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(LvNotify lvNotify) {
		TlvNotify t = lvNotifyDao.get(TlvNotify.class, lvNotify.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(lvNotify, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvNotifyDao.delete(lvNotifyDao.get(TlvNotify.class, id));
	}

}
