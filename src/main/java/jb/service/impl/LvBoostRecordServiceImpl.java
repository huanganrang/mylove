package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.LvBoostRecordDaoI;
import jb.model.TlvBoostRecord;
import jb.pageModel.LvBoostRecord;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;
import jb.service.LvBoostRecordServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jb.util.MyBeanUtils;

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
		}	
		return whereHql;
	}

	@Override
	public void add(LvBoostRecord lvBoostRecord) {
		TlvBoostRecord t = new TlvBoostRecord();
		BeanUtils.copyProperties(lvBoostRecord, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
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
	public void edit(LvBoostRecord lvBoostRecord) {
		TlvBoostRecord t = lvBoostRecordDao.get(TlvBoostRecord.class, lvBoostRecord.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(lvBoostRecord, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvBoostRecordDao.delete(lvBoostRecordDao.get(TlvBoostRecord.class, id));
	}

}
