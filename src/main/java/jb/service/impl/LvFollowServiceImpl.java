package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.dao.BaseDaoI;
import jb.dao.LvAccountPhotoDaoI;
import jb.dao.LvFollowDaoI;
import jb.model.TlvAccount;
import jb.model.TlvFollow;
import jb.pageModel.DataGrid;
import jb.pageModel.LvAccount;
import jb.pageModel.LvFollow;
import jb.pageModel.PageHelper;
import jb.service.LvAccountServiceI;
import jb.service.LvFollowServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvFollowServiceImpl extends BaseServiceImpl<LvFollow> implements LvFollowServiceI {

	@Autowired
	private LvFollowDaoI lvFollowDao;
	
	@Autowired
	private LvAccountServiceI accountService;
	
	@Autowired
	private LvAccountPhotoDaoI lvAccountPhotoDao;

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
	public int add(LvFollow lvFollow) {
		LvFollow f = get(lvFollow.getFromOpenId(), lvFollow.getToOpenId());
		if(f != null) {
			lvFollow.setId(f.getId());
			this.edit(lvFollow);
			return -1;
		}
		TlvFollow t = new TlvFollow();
		BeanUtils.copyProperties(lvFollow, t);
		t.setId(UUID.randomUUID().toString());
		lvFollowDao.save(t);
		
		LvAccount a = accountService.get(lvFollow.getToOpenId());
		a.setFollowNum(a.getFollowNum() + 1);
		accountService.edit(a);
		
		return 1;
	}
	
	public LvFollow get(Integer fromOpenId, Integer toOpenId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fromOpenId", fromOpenId);
		params.put("toOpenId", toOpenId);		
		TlvFollow t = lvFollowDao.get("from TlvFollow t  where t.fromOpenId = :fromOpenId and t.toOpenId = :toOpenId", params);
		if(t == null)
			return null;
		LvFollow f = new LvFollow();
		BeanUtils.copyProperties(t, f);
		return f;
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

	/**
	 * 关注我的/我关注的用户列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public DataGrid dataGridAccount(LvFollow lvFollow, PageHelper ph) {
		List<LvAccount> al = new ArrayList<LvAccount>();
		
		DataGrid dg = dataGridByType(ph, lvFollow, lvFollowDao);
		List<TlvAccount> l = dg.getRows();
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


	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DataGrid dataGridByType(PageHelper ph, LvFollow lvFollow,
			BaseDaoI dao) {
		ph.setSort("createTime");
		ph.setOrder("desc");
		
		DataGrid dg = new DataGrid();
		dg.setPage(Long.valueOf(ph.getPage()));
		dg.setPageSize(Long.valueOf(ph.getRows()));
		
		String hql = "select a from TlvAccount a ,TlvFollow t  ";
		Map<String, Object> params = new HashMap<String, Object>();
		//我关注的用户
		if(lvFollow.getFromOpenId() != null){
			hql +="where a.openId = t.toOpenId and t.fromOpenId = :openId";
			params.put("openId", lvFollow.getFromOpenId());
		//关注我的用户
		}else if(lvFollow.getToOpenId() != null){
			hql +="where a.openId = t.fromOpenId and t.toOpenId = :openId";
			params.put("openId", lvFollow.getToOpenId());
		}		
		List<TlvAccount> l = dao.find(hql   + orderHql(ph), params, ph.getPage(), ph.getRows());
		dg.setTotal(dao.count("select count(*) " + hql.substring(8) , params));
		dg.setRows(l);
		return dg;
	}

	/**
	 * 取消关注
	 */
	public int delete(LvFollow f) {
		f = get(f.getFromOpenId(), f.getToOpenId());
		if(f == null) return -1;
		
		delete(f.getId());
		
		LvAccount a = accountService.get(f.getToOpenId());
		a.setFollowNum(a.getFollowNum() - 1);
		accountService.edit(a);
		
		return 1;
	}

}
