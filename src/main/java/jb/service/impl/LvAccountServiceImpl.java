package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.LvAccountDaoI;
import jb.dao.LvAccountPhotoDaoI;
import jb.dao.LvFollowDaoI;
import jb.model.TlvAccount;
import jb.model.TlvAccountPhoto;
import jb.pageModel.DataGrid;
import jb.pageModel.LvAccount;
import jb.pageModel.LvAccountPhoto;
import jb.pageModel.LvVisit;
import jb.pageModel.PageHelper;
import jb.service.LvAccountServiceI;
import jb.service.LvVisitServiceI;
import jb.util.Constants;
import jb.util.DateUtil;
import jb.util.MD5Util;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvAccountServiceImpl extends BaseServiceImpl<LvAccount> implements LvAccountServiceI {

	@Autowired
	private LvAccountDaoI lvAccountDao;
	
	@Autowired
	private LvAccountPhotoDaoI lvAccountPhotoDao;
	
	@Autowired
	private LvFollowDaoI lvFollowDao;
	
	@Autowired
	private LvVisitServiceI lvVisitService;

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
			if (!F.empty(lvAccount.getSex())) {
				whereHql += " and t.sex = :sex";
				params.put("sex", lvAccount.getSex());
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
			MyBeanUtils.copyProperties(lvAccount, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvAccountDao.delete(lvAccountDao.get(TlvAccount.class, id));
	}

	/**
	 * 注册
	 */
	public LvAccount login(LvAccount lvAccount) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", lvAccount.getOpenId());
		params.put("password", MD5Util.md5(lvAccount.getPassword()));
		TlvAccount a = lvAccountDao.get("from TlvAccount t where t.openId = :openId and t.password = :password", params);
		if (a != null) {
			BeanUtils.copyProperties(a, lvAccount);
			return lvAccount;
		}
		return null;
	}
	
	/**
	 * 注册
	 */
	public LvAccount reg(LvAccount account) {
		TlvAccount a = new TlvAccount();
		account.setId(UUID.randomUUID().toString());
		account.setPassword(MD5Util.md5(Constants.ACCOUNT_DEFAULT_PSW));
		account.setBirthday(DateUtil.parse("2015-01-01", DateUtil.YMD_A));
		account.setCreateTime(new Date());
		account.setLastLoginTime(new Date());
		MyBeanUtils.copyProperties(account, a, true);
		lvAccountDao.save(a);
		
		return account;
	}


	/**
	 * 修改密码
	 * @throws Exception 
	 */
	public void updatePass(LvAccount lvAccount) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", lvAccount.getOpenId());
		params.put("password", MD5Util.md5(lvAccount.getOldPass()));
		TlvAccount t = lvAccountDao.get("from TlvAccount t where t.openId = :openId and t.password = :password", params);
		if (t == null) {
			throw new Exception("旧密码不正确！");
		} else {
			lvAccount.setPassword(MD5Util.md5(lvAccount.getPassword()));
			MyBeanUtils.copyProperties(lvAccount, t, new String[] {"openId", "createTime"},true);
			t.setUpdateTime(new Date());
		}
	}


	@Override
	public void editByParam(LvAccount lvAccount) throws Exception  {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", lvAccount.getOpenId());
		TlvAccount t = lvAccountDao.get("from TlvAccount t where t.openId = :openId", params);
		if (t == null) {
			throw new Exception("用户不存在！");
		} else {
			MyBeanUtils.copyProperties(lvAccount, t, new String[] {"openId", "createTime"},true);
			t.setUpdateTime(new Date());
		}
	}


	/**
	 * 个人资料查询
	 */
	public LvAccount queryPersonInfoByParam(LvAccount lvAccount) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		int isVisit = 2; // 未关注
		List<LvAccountPhoto> photoList = new ArrayList<LvAccountPhoto>();
		TlvAccount t = null;
		if(lvAccount.getByOpenId() == null) {
			params.put("openId", lvAccount.getOpenId());
			t = lvAccountDao.get("from TlvAccount t where  t.openId = :openId", params);
		} else {
			params.put("openId", lvAccount.getByOpenId());
			t = lvAccountDao.get("from TlvAccount t left join fetch t.tlvAccountPhotos photos where t.openId = :openId", params);
			
			// 当前关注状态
			params = new HashMap<String, Object>();
			params.put("fromOpenId", lvAccount.getOpenId()); 
			params.put("toOpenId", lvAccount.getByOpenId()); 
			if(lvFollowDao.count("select count(*) from TlvFollow t where t.fromOpenId = :fromOpenId and t.toOpenId = :toOpenId", params) > 0) {
				isVisit = 1; // 已关注
			}
			
			// 插入来访纪录
			LvVisit lvVisit = new LvVisit();
			lvVisit.setOpenId(lvAccount.getByOpenId());
			lvVisit.setVisitOpenId(lvAccount.getOpenId());
			lvVisitService.saveOrUpdate(lvVisit);
			
		}
		MyBeanUtils.copyProperties(t, lvAccount);
		if(t.getTlvAccountPhotos().size() > 0) {
			LvAccountPhoto photo = null;
			for(TlvAccountPhoto tPhoto : t.getTlvAccountPhotos()) { 
				photo = new LvAccountPhoto();
				MyBeanUtils.copyProperties(tPhoto, photo);
				photoList.add(photo);
			}
		}
		lvAccount.setPhotoList(photoList);
		lvAccount.setIsVisit(isVisit);
		
		return lvAccount;
	}

}
