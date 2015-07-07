package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.LvAreaDaoI;
import jb.model.TlvArea;
import jb.pageModel.DataGrid;
import jb.pageModel.LvArea;
import jb.pageModel.PageHelper;
import jb.service.LvAreaServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvAreaServiceImpl extends BaseServiceImpl<LvArea> implements LvAreaServiceI {

	@Autowired
	private LvAreaDaoI lvAreaDao;

	@Override
	public DataGrid dataGrid(LvArea lvArea, PageHelper ph) {
		List<LvArea> ol = new ArrayList<LvArea>();
		String hql = " from TlvArea t ";
		DataGrid dg = dataGridQuery(hql, ph, lvArea, lvAreaDao);
		@SuppressWarnings("unchecked")
		List<TlvArea> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TlvArea t : l) {
				LvArea o = new LvArea();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(LvArea lvArea, Map<String, Object> params) {
		String whereHql = "";	
		if (lvArea != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(lvArea.getCode())) {
				whereHql += " and t.code = :code";
				params.put("code", lvArea.getCode());
			}		
			if (!F.empty(lvArea.getName())) {
				whereHql += " and t.name = :name";
				params.put("name", lvArea.getName());
			}		
			if (!F.empty(lvArea.getParentCode())) {
				whereHql += " and t.parentCode = :parentCode";
				params.put("parentCode", lvArea.getParentCode());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(LvArea lvArea) {
		TlvArea t = new TlvArea();
		BeanUtils.copyProperties(lvArea, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		lvAreaDao.save(t);
	}

	@Override
	public LvArea get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TlvArea t = lvAreaDao.get("from TlvArea t  where t.id = :id", params);
		LvArea o = new LvArea();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(LvArea lvArea) {
		TlvArea t = lvAreaDao.get(TlvArea.class, lvArea.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(lvArea, t, new String[] { "id" , "createdatetime" }, false);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvAreaDao.delete(lvAreaDao.get(TlvArea.class, id));
	}


	@Override
	public List<LvArea> queryAllList() {
		List<LvArea> ol = new ArrayList<LvArea>();
		
		List<TlvArea> l = lvAreaDao.find("from TlvArea t order by t.code");
		if (l != null && l.size() > 0) {
			for (TlvArea t : l) {
				LvArea o = new LvArea();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		return ol;
	}

}
