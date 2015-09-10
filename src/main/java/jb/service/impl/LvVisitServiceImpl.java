package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.dao.LvAccountDaoI;
import jb.dao.LvAccountPhotoDaoI;
import jb.dao.LvVisitDaoI;
import jb.model.TlvAccount;
import jb.model.TlvVisit;
import jb.pageModel.DataGrid;
import jb.pageModel.LvAccount;
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
	
	@Autowired
	private LvAccountDaoI lvAccountDao;
	@Autowired
	private LvAccountPhotoDaoI lvAccountPhotoDao;

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
		}	
		return whereHql;
	}

	@Override
	public void add(LvVisit lvVisit) {
		TlvVisit t = new TlvVisit();
		MyBeanUtils.copyProperties(lvVisit, t, true);
		t.setId(UUID.randomUUID().toString());
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


	@Override
	public void saveOrUpdate(LvVisit lvVisit) {
		
		lvVisit.setCreateTime(new Date());
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", lvVisit.getOpenId());
		params.put("visitOpenId", lvVisit.getVisitOpenId());
		// 存在更新来访时间
		if(lvVisitDao.count("select count(*) from TlvVisit t where t.openId = :openId and t.visitOpenId = :visitOpenId", params) > 0) {
			TlvVisit t = lvVisitDao.get("from TlvVisit t where t.openId = :openId and t.visitOpenId = :visitOpenId", params);
			MyBeanUtils.copyProperties(lvVisit, t, new String[] {"id"},true);
		} else { // 不存在则插入新的纪录
			this.add(lvVisit);
		}
	}

	/**
	 * 根据用户openId查询所有的关注用户列表
	 */
	public List<LvAccount> queryAllVisitAccount(Integer openId) {
		List<LvAccount> result = new ArrayList<LvAccount>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openId);
//		List<TlvVisit> list = lvVisitDao.find("from TlvVisit t left join fetch t.visitTlvAccount visitAccount where t.openId = :openId", params);
		List<TlvAccount> list = lvAccountDao.find("select a from TlvVisit v, TlvAccount a where v.visitOpenId = a.openId and v.openId = :openId", params);
		if(list != null && list.size() > 0) {
			for(TlvAccount t : list) {
				LvAccount account = new LvAccount();
				MyBeanUtils.copyProperties(t, account, true);
				result.add(account);
			}
		}
		return result;
	}


	@Override
	public DataGrid dataGridAccount(LvVisit lvVisit, PageHelper ph) {
		List<LvAccount> al = new ArrayList<LvAccount>();
		ph.setSort("createTime");
		ph.setOrder("desc");
		DataGrid dg = new DataGrid();
		dg.setPage(Long.valueOf(ph.getPage()));
		dg.setPageSize(Long.valueOf(ph.getRows()));
		
		String hql = "select a from TlvVisit t, TlvAccount a where t.visitOpenId = a.openId and t.openId = :openId ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", lvVisit.getOpenId());
		List<TlvAccount> l = lvAccountDao.find(hql + orderHql(ph), params, ph.getPage(), ph.getRows());
		dg.setTotal(lvAccountDao.count("select count(*) " + hql.substring(8) , params));
		if (l != null && l.size() > 0) {
			String[] openIds = new String[l.size()];
			int i = 0;
			for (TlvAccount t : l) {
				LvAccount a = new LvAccount();
				MyBeanUtils.copyProperties(t, a, true);
				al.add(a);
				openIds[i++] = t.getOpenId().toString();
			}
			HashMap<Integer,Integer> photoNums = lvAccountPhotoDao.getCountPhotoNum(openIds);
			for(LvAccount a : al) {
				Integer num = photoNums.get(a.getOpenId());
				if(num != null)
				a.setPhotoNum(num);
			}
		}
		dg.setRows(al);
		return dg;
	}

}
