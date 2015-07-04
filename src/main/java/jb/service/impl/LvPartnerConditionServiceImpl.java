package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.LvPartnerConditionDaoI;
import jb.model.TlvPartnerCondition;
import jb.pageModel.DataGrid;
import jb.pageModel.LvPartnerCondition;
import jb.pageModel.PageHelper;
import jb.service.LvPartnerConditionServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvPartnerConditionServiceImpl extends BaseServiceImpl<LvPartnerCondition> implements LvPartnerConditionServiceI {

	@Autowired
	private LvPartnerConditionDaoI lvPartnerConditionDao;

	@Override
	public DataGrid dataGrid(LvPartnerCondition lvPartnerCondition, PageHelper ph) {
		List<LvPartnerCondition> ol = new ArrayList<LvPartnerCondition>();
		String hql = " from TlvPartnerCondition t ";
		DataGrid dg = dataGridQuery(hql, ph, lvPartnerCondition, lvPartnerConditionDao);
		@SuppressWarnings("unchecked")
		List<TlvPartnerCondition> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TlvPartnerCondition t : l) {
				LvPartnerCondition o = new LvPartnerCondition();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(LvPartnerCondition lvPartnerCondition, Map<String, Object> params) {
		String whereHql = "";	
		if (lvPartnerCondition != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(lvPartnerCondition.getAccountId())) {
				whereHql += " and t.accountId = :accountId";
				params.put("accountId", lvPartnerCondition.getAccountId());
			}		
			if (!F.empty(lvPartnerCondition.getAge())) {
				whereHql += " and t.age = :age";
				params.put("age", lvPartnerCondition.getAge());
			}		
			if (!F.empty(lvPartnerCondition.getAddress())) {
				whereHql += " and t.address = :address";
				params.put("address", lvPartnerCondition.getAddress());
			}		
			if (!F.empty(lvPartnerCondition.getHeight())) {
				whereHql += " and t.height = :height";
				params.put("height", lvPartnerCondition.getHeight());
			}		
			if (!F.empty(lvPartnerCondition.getWeight())) {
				whereHql += " and t.weight = :weight";
				params.put("weight", lvPartnerCondition.getWeight());
			}		
			if (!F.empty(lvPartnerCondition.getEducation())) {
				whereHql += " and t.education = :education";
				params.put("education", lvPartnerCondition.getEducation());
			}		
			if (!F.empty(lvPartnerCondition.getMonthIncome())) {
				whereHql += " and t.monthIncome = :monthIncome";
				params.put("monthIncome", lvPartnerCondition.getMonthIncome());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(LvPartnerCondition lvPartnerCondition) {
		TlvPartnerCondition t = new TlvPartnerCondition();
		BeanUtils.copyProperties(lvPartnerCondition, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		lvPartnerConditionDao.save(t);
	}

	@Override
	public LvPartnerCondition get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TlvPartnerCondition t = lvPartnerConditionDao.get("from TlvPartnerCondition t  where t.id = :id", params);
		LvPartnerCondition o = new LvPartnerCondition();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(LvPartnerCondition lvPartnerCondition) {
		TlvPartnerCondition t = lvPartnerConditionDao.get(TlvPartnerCondition.class, lvPartnerCondition.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(lvPartnerCondition, t, new String[] { "id" , "createdatetime" }, true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvPartnerConditionDao.delete(lvPartnerConditionDao.get(TlvPartnerCondition.class, id));
	}

}
