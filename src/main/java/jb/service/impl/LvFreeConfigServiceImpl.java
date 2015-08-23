package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.LvFreeConfigDaoI;
import jb.model.TlvFreeConfig;
import jb.pageModel.DataGrid;
import jb.pageModel.LvFreeConfig;
import jb.pageModel.PageHelper;
import jb.service.LvFreeConfigServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvFreeConfigServiceImpl extends BaseServiceImpl<LvFreeConfig> implements LvFreeConfigServiceI {

	@Autowired
	private LvFreeConfigDaoI lvFreeConfigDao;

	@Override
	public DataGrid dataGrid(LvFreeConfig lvFreeConfig, PageHelper ph) {
		List<LvFreeConfig> ol = new ArrayList<LvFreeConfig>();
		String hql = " from TlvFreeConfig t ";
		DataGrid dg = dataGridQuery(hql, ph, lvFreeConfig, lvFreeConfigDao);
		@SuppressWarnings("unchecked")
		List<TlvFreeConfig> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TlvFreeConfig t : l) {
				LvFreeConfig o = new LvFreeConfig();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(LvFreeConfig lvFreeConfig, Map<String, Object> params) {
		String whereHql = "";	
		if (lvFreeConfig != null) {
			whereHql += " where 1=1 ";
			if(lvFreeConfig.getOpenId() != null) {
				whereHql += " and t.openId = :openId";
				params.put("openId", lvFreeConfig.getOpenId());
			}
			if (!F.empty(lvFreeConfig.getFtype())) {
				whereHql += " and t.ftype = :ftype";
				params.put("ftype", lvFreeConfig.getFtype());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(LvFreeConfig lvFreeConfig) {
		TlvFreeConfig t = new TlvFreeConfig();
		BeanUtils.copyProperties(lvFreeConfig, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		lvFreeConfigDao.save(t);
	}

	@Override
	public LvFreeConfig get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TlvFreeConfig t = lvFreeConfigDao.get("from TlvFreeConfig t  where t.id = :id", params);
		LvFreeConfig o = new LvFreeConfig();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(LvFreeConfig lvFreeConfig) {
		TlvFreeConfig t = lvFreeConfigDao.get(TlvFreeConfig.class, lvFreeConfig.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(lvFreeConfig, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvFreeConfigDao.delete(lvFreeConfigDao.get(TlvFreeConfig.class, id));
	}


	@Override
	public List<LvFreeConfig> findAllByParam(LvFreeConfig free) {
		List<LvFreeConfig> ol = new ArrayList<LvFreeConfig>();
		String hql = " from TlvFreeConfig t ";
		Map<String, Object> params = new HashMap<String, Object>();
		String where = whereHql(free, params);
		List<TlvFreeConfig> l = lvFreeConfigDao.find(hql + where, params);
		if (l != null && l.size() > 0) {
			for (TlvFreeConfig t : l) {
				LvFreeConfig o = new LvFreeConfig();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		return ol;
	}


	@Override
	public int freeNumUsed(LvFreeConfig free) {
		int usedNum = 1;
		Map<String, Object> params = new HashMap<String, Object>();
		String where = whereHql(free, params);
		TlvFreeConfig t = lvFreeConfigDao.get(" from TlvFreeConfig t " + where, params);
		if(t != null) {
			usedNum += t.getUsedNum();
			free.setUsedNum(usedNum);
			free.setId(t.getFtype());
			this.edit(free);
		} else {
			free.setUsedNum(usedNum);
			this.add(free);
		}
		return usedNum;
	}

}
