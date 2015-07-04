package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.LvAccountDaoI;
import jb.model.TlvAccount;
import jb.pageModel.DataGrid;
import jb.pageModel.LvAccount;
import jb.pageModel.PageHelper;
import jb.service.LvAccountServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvAccountServiceImpl extends BaseServiceImpl<LvAccount> implements LvAccountServiceI {

	@Autowired
	private LvAccountDaoI lvAccountDao;

	@Override
	public DataGrid dataGrid(LvAccount lvAccount, PageHelper ph) {
		List<LvAccount> ol = new ArrayList<LvAccount>();
		String hql = " from TlvAccount t ";
		DataGrid dg = dataGridQuery(hql, ph, lvAccount, lvAccountDao);
		@SuppressWarnings("unchecked")
		List<TlvAccount> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TlvAccount t : l) {
				LvAccount o = new LvAccount();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(LvAccount lvAccount, Map<String, Object> params) {
		String whereHql = "";	
		if (lvAccount != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(lvAccount.getLoginName())) {
				whereHql += " and t.loginName = :loginName";
				params.put("loginName", lvAccount.getLoginName());
			}		
			if (!F.empty(lvAccount.getNickName())) {
				whereHql += " and t.nickName = :nickName";
				params.put("nickName", lvAccount.getNickName());
			}		
			if (!F.empty(lvAccount.getPassword())) {
				whereHql += " and t.password = :password";
				params.put("password", lvAccount.getPassword());
			}		
				
			if (!F.empty(lvAccount.getMobile())) {
				whereHql += " and t.mobile = :mobile";
				params.put("mobile", lvAccount.getMobile());
			}		
			if (!F.empty(lvAccount.getQq())) {
				whereHql += " and t.qq = :qq";
				params.put("qq", lvAccount.getQq());
			}		
			if (!F.empty(lvAccount.getAddress())) {
				whereHql += " and t.address = :address";
				params.put("address", lvAccount.getAddress());
			}		
		
		}	
		return whereHql;
	}

	@Override
	public void add(LvAccount lvAccount) {
		TlvAccount t = new TlvAccount();
		BeanUtils.copyProperties(lvAccount, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		lvAccountDao.save(t);
	}

	@Override
	public LvAccount get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TlvAccount t = lvAccountDao.get("from TlvAccount t  where t.id = :id", params);
		LvAccount o = new LvAccount();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(LvAccount lvAccount) {
		TlvAccount t = lvAccountDao.get(TlvAccount.class, lvAccount.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(lvAccount, t, new String[] { "id" , "createdatetime" }, true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvAccountDao.delete(lvAccountDao.get(TlvAccount.class, id));
	}

}
