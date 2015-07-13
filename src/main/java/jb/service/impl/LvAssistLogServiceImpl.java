package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.LvAssistLogDaoI;
import jb.model.TlvAssistLog;
import jb.pageModel.DataGrid;
import jb.pageModel.LvAssistLog;
import jb.pageModel.PageHelper;
import jb.service.LvAssistLogServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvAssistLogServiceImpl extends BaseServiceImpl<LvAssistLog> implements LvAssistLogServiceI {

	@Autowired
	private LvAssistLogDaoI lvAssistLogDao;

	@Override
	public DataGrid dataGrid(LvAssistLog lvAssistLog, PageHelper ph) {
		List<LvAssistLog> ol = new ArrayList<LvAssistLog>();
		String hql = " from TlvAssistLog t ";
		DataGrid dg = dataGridQuery(hql, ph, lvAssistLog, lvAssistLogDao);
		@SuppressWarnings("unchecked")
		List<TlvAssistLog> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TlvAssistLog t : l) {
				LvAssistLog o = new LvAssistLog();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(LvAssistLog lvAssistLog, Map<String, Object> params) {
		String whereHql = "";	
		if (lvAssistLog != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(lvAssistLog.getBoostRecordId())) {
				whereHql += " and t.boostRecordId = :boostRecordId";
				params.put("boostRecordId", lvAssistLog.getBoostRecordId());
			}		
		}	
		return whereHql;
	}

	@Override
	public int add(LvAssistLog lvAssistLog) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("boostRecordId", lvAssistLog.getBoostRecordId());
		params.put("openId", lvAssistLog.getOpenId());
		if(lvAssistLogDao.count("select count(*) from TlvAssistLog t where t.boostRecordId = :boostRecordId and t.openId = :openId", params) > 0) {
			return -1;
		}
		TlvAssistLog t = new TlvAssistLog();
		BeanUtils.copyProperties(lvAssistLog, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		t.setAssistTime(new Date());
		lvAssistLogDao.save(t);
		
		return 1;
	}

	@Override
	public LvAssistLog get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TlvAssistLog t = lvAssistLogDao.get("from TlvAssistLog t  where t.id = :id", params);
		LvAssistLog o = new LvAssistLog();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(LvAssistLog lvAssistLog) {
		TlvAssistLog t = lvAssistLogDao.get(TlvAssistLog.class, lvAssistLog.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(lvAssistLog, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvAssistLogDao.delete(lvAssistLogDao.get(TlvAssistLog.class, id));
	}

}
