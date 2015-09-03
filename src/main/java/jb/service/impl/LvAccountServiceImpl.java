package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import jb.absx.F;
import jb.dao.LvAccountDaoI;
import jb.dao.LvAccountPhotoDaoI;
import jb.dao.LvFollowDaoI;
import jb.dao.LvNotifyDaoI;
import jb.listener.Application;
import jb.model.TlvAccount;
import jb.model.TlvAccountPhoto;
import jb.pageModel.AccountSearch;
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
	
	@Autowired
	private LvNotifyDaoI lvNotifyDao;

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
			if (lvAccount.getOpenId() != null) {
				whereHql += " and t.openId = :openId";
				params.put("openId", lvAccount.getOpenId());
			}		
			if (!F.empty(lvAccount.getNickName())) {
				whereHql += " and t.nickName = :nickName";
				params.put("nickName", lvAccount.getNickName());
			}		
			if (!F.empty(lvAccount.getSex())) {
				whereHql += " and t.sex = :sex";
				params.put("sex", lvAccount.getSex());
			}		
			if (!F.empty(lvAccount.getAuditStatus())) {
				whereHql += " and t.auditStatus = :auditStatus";
				params.put("auditStatus", lvAccount.getAuditStatus());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(LvAccount account) {
		account.setId(UUID.randomUUID().toString());
		account.setPassword(MD5Util.md5(Constants.ACCOUNT_DEFAULT_PSW));
		account.setHxPassword(Constants.ACCOUNT_DEFAULT_PSW);
		account.setCreateTime(new Date());
		account.setLastLoginTime(new Date());
		TlvAccount t = new TlvAccount();
		BeanUtils.copyProperties(account, t);
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
			MyBeanUtils.copyProperties(lvAccount, t, new String[] { "id" , "createTime" },true);
			t.setUpdateTime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvAccountDao.delete(lvAccountDao.get(TlvAccount.class, id));
	}

	/**
	 * 登录
	 */
	public LvAccount login(LvAccount lvAccount) {
		Map<String, Object> params = new HashMap<String, Object>();
		String loginName = lvAccount.getLoginName() == null ? lvAccount.getOpenId().toString() : lvAccount.getLoginName();
		String where = " where t.password = :password";
		params.put("password", MD5Util.md5(lvAccount.getPassword()));
		try {
			if(loginName.length() <= 10) {
				Integer openId = Integer.valueOf(loginName);
				params.put("openId", openId);
				params.put("loginName", loginName);
				where += " and (t.openId = :openId or t.loginName = :loginName)";
			} else {
				where += " and t.loginName = :loginName";
				params.put("loginName", loginName);
			}
		} catch(Exception e) {
			//System.out.println(e.getMessage());
			where += " and t.loginName = :loginName";
			params.put("loginName", loginName);
		}
		TlvAccount a = lvAccountDao.get("from TlvAccount t " + where, params);
		if (a != null) {
			BeanUtils.copyProperties(a, lvAccount);
			return lvAccount;
		}
		return null;
	}
	
	/**
	 * 注册
	 * @throws Exception 
	 */
	public LvAccount reg(LvAccount account) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		String loginName = account.getLoginName();
		String where = " where 1=1";
		try {
			if(loginName.length() <= 10) {
				Integer openId = Integer.valueOf(loginName);
				params.put("openId", openId);
				params.put("loginName", loginName);
				where += " and (t.openId = :openId or t.loginName = :loginName)";
			} else {
				where += " and t.loginName = :loginName";
				params.put("loginName", loginName);
			}
		} catch(Exception e) {
			//System.out.println(e.getMessage());
			where += " and t.loginName = :loginName";
			params.put("loginName", loginName);
		}
		params.put("loginName", account.getLoginName());
		if(lvAccountDao.count("select count(*) from TlvAccount t " + where, params) > 0) {
			throw new Exception("账号已被注册！");
		}
		
		if(F.empty(account.getNickName())) {
			account.setNickName(account.getLoginName());
		}
		TlvAccount a = new TlvAccount();
		account.setId(UUID.randomUUID().toString());
		account.setHxPassword(account.getPassword());
		account.setPassword(MD5Util.md5(account.getPassword()));
		account.setBirthday(DateUtil.getBirthdayByAge(account.getAge()));
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
	 * 开通VIP
	 */
	public void registerVip(LvAccount lvAccount) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", lvAccount.getOpenId());
		TlvAccount t = lvAccountDao.get("from TlvAccount t where t.openId = :openId", params);
		Date now = DateUtil.parse(DateUtil.format(new Date(), DateUtil.YMD_A), DateUtil.YMD_A);
		if(!F.empty(t.getVipLevel())) {
			if(now.getTime() > t.getVipEndTime().getTime()) {
				lvAccount.setVipOpenTime(now);
				if("VP01".equals(lvAccount.getVipLevel())) {
					lvAccount.setVipEndTime(DateUtil.addYearToDate(now, 1));
				} else if("VP02".equals(lvAccount.getVipLevel())){
					lvAccount.setVipEndTime(DateUtil.addMonthToDate(now, 3));
				} else {
					lvAccount.setVipEndTime(DateUtil.addMonthToDate(now, 1));
				}
			} else {
				if("VP01".equals(t.getVipLevel())) {
					if("VP01".equals(lvAccount.getVipLevel())) {
						lvAccount.setVipEndTime(DateUtil.addYearToDate(t.getVipEndTime(), 1));
					} else if("VP02".equals(lvAccount.getVipLevel())) {
						lvAccount.setVipLevel(t.getVipLevel());
						lvAccount.setVipEndTime(DateUtil.addMonthToDate(t.getVipEndTime(), 3));
					} else {
						lvAccount.setVipLevel(t.getVipLevel());
						lvAccount.setVipEndTime(DateUtil.addMonthToDate(t.getVipEndTime(), 1));
					}
					
				} else if("VP02".equals(t.getVipLevel())){
					if("VP01".equals(lvAccount.getVipLevel())) {
						lvAccount.setVipEndTime(DateUtil.addYearToDate(t.getVipEndTime(), 1));
					} else if("VP02".equals(lvAccount.getVipLevel())) {
						lvAccount.setVipEndTime(DateUtil.addMonthToDate(t.getVipEndTime(), 3));
					} else {
						lvAccount.setVipLevel(t.getVipLevel());
						lvAccount.setVipEndTime(DateUtil.addMonthToDate(t.getVipEndTime(), 1));
					}
				} else {
					if("VP01".equals(lvAccount.getVipLevel())) {
						lvAccount.setVipEndTime(DateUtil.addYearToDate(t.getVipEndTime(), 1));
					} else if("VP02".equals(lvAccount.getVipLevel())) {
						lvAccount.setVipEndTime(DateUtil.addMonthToDate(t.getVipEndTime(), 3));
					} else {
						lvAccount.setVipEndTime(DateUtil.addMonthToDate(t.getVipEndTime(), 1));
					}
				}
			}
		} else {
			lvAccount.setVipOpenTime(now);
			if("VP01".equals(lvAccount.getVipLevel())) {
				lvAccount.setVipEndTime(DateUtil.addYearToDate(now, 1));
			} else if("VP02".equals(lvAccount.getVipLevel())){
				lvAccount.setVipEndTime(DateUtil.addMonthToDate(now, 3));
			} else {
				lvAccount.setVipEndTime(DateUtil.addMonthToDate(now, 1));
			}
		}
		
		MyBeanUtils.copyProperties(lvAccount, t, new String[] {"openId", "createTime"},true);
		t.setUpdateTime(new Date());
	}

	/**
	 * 个人资料查询
	 */
	public LvAccount queryPersonInfoByParam(LvAccount lvAccount) {
		LvAccount a = new LvAccount();
		
		if(lvAccount.getByOpenId() == null) {
			a = this.get(lvAccount.getOpenId());
		} else {
			Map<String, Object> params = new HashMap<String, Object>();
			a = this.get(lvAccount.getByOpenId());
			
			// 获取被查看用户的相册
			List<LvAccountPhoto> photoList = new ArrayList<LvAccountPhoto>();
			params.put("openId", lvAccount.getByOpenId());
			List<TlvAccountPhoto> l = lvAccountPhotoDao.find("from TlvAccountPhoto t where t.openId = :openId order by t.createTime desc", params);
			if(l != null && l.size() > 0) {
				LvAccountPhoto photo = null;
				for(TlvAccountPhoto tPhoto : l) { 
					photo = new LvAccountPhoto();
					MyBeanUtils.copyProperties(tPhoto, photo);
					photoList.add(photo);
				}
			}
			a.setPhotoList(photoList);
			
			// 当前关注状态
			String isVisit = Constants.GLOBAL_BOOLEAN_FALSE; // 未关注
			params = new HashMap<String, Object>();
			params.put("fromOpenId", lvAccount.getOpenId()); 
			params.put("toOpenId", lvAccount.getByOpenId()); 
			if(lvFollowDao.count("select count(*) from TlvFollow t where t.fromOpenId = :fromOpenId and t.toOpenId = :toOpenId", params) > 0) {
				isVisit = Constants.GLOBAL_BOOLEAN_TRUE; // 已关注
			}
			a.setIsVisit(Integer.valueOf(isVisit));
			
			// 当前打招呼状态
			String isNotify = Constants.GLOBAL_BOOLEAN_FALSE; // 未打招呼
			params = new HashMap<String, Object>();
			params.put("openId", lvAccount.getOpenId()); 
			params.put("notifyOpenId", lvAccount.getByOpenId()); 
			if(lvNotifyDao.count("select count(*) from TlvNotify t where t.openId = :openId and t.notifyOpenId = :notifyOpenId", params) > 0) {
				isNotify = Constants.GLOBAL_BOOLEAN_TRUE; // 已打招呼
			}
			a.setIsNotify(Integer.valueOf(isNotify));
			
			// 插入来访纪录
			LvVisit lvVisit = new LvVisit();
			lvVisit.setOpenId(lvAccount.getByOpenId());
			lvVisit.setVisitOpenId(lvAccount.getOpenId());
			lvVisitService.saveOrUpdate(lvVisit);
			
			// 更改被查看用户的来访数量
			a.setVisitNum(a.getVisitNum() + 1);
			this.edit(a);
		}
		
		a.setAge(DateUtil.getAgeByBirthday(a.getBirthday()));
		return a;
	}


	@Override
	public LvAccount get(Integer openId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openId);
		TlvAccount t = lvAccountDao.get("from TlvAccount t where t.openId = :openId", params);
		if(t == null) return null;
		LvAccount a = new LvAccount();
		MyBeanUtils.copyProperties(t, a, true);
		
		return a;
	}

	/**
	 * 首页用户查询搜索
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DataGrid dataGridAccount_search(AccountSearch search, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		dg.setPage(Long.valueOf(ph.getPage()));
		dg.setPageSize(Long.valueOf(ph.getRows()));
		
		LvAccount a = get(Integer.valueOf(search.getOpenId()));
		String sql = "select openId, headImg, birthday, sex, vipLevel, vipOpenTime, vipEndTime, nickName, "
				+ "round(6378.138*2*asin(sqrt(pow(sin(("+a.getLatitude()+"*pi()/180-latitude*pi()/180)/2),2)+cos("+a.getLatitude()+"*pi()/180)*cos(latitude*pi()/180)*pow(sin(("+a.getLongitude()+"*pi()/180-longitude*pi()/180)/2),2)))*1000) as distance"
				+ " from lv_account ";
		
		Map<String, Object> params = new HashMap<String, Object>();
		String whereHql = " where sex = :sex and openId != :openId ";
		params.put("sex", "SX01".equals(a.getSex()) ? "SX02" : "SX01"); // 男查女、女查男
		params.put("openId", Integer.valueOf(search.getOpenId())); 
		if("2".equals(search.getSearchType())) {
			if(!F.empty(a.getLastLoginArea())) {
				String searchArea = "";
				String[] areas = a.getLastLoginArea().split("_");
				if(areas.length == 1) {
					searchArea += areas[0];
				} else {
					searchArea += areas[0] + "_" + areas[1];
				}
				whereHql += " and lastLoginArea like :searchAreaCode ";
				params.put("searchAreaCode", "%%" + searchArea + "%%");
			}
		} else if("3".equals(search.getSearchType())) {
			whereHql += " and openId = :searchOpenId ";
			params.put("searchOpenId", Integer.valueOf(search.getSearchOpenId()));
		}
		
		String orderString = " order by visitNum desc, followNum desc";
//		List<TlvAccount> l = lvAccountDao.find(sql + whereHql + orderString, params, ph.getPage(), ph.getRows());
		List<Map> l = lvAccountDao.findBySql2Map(sql + whereHql + orderString, params, ph.getPage(), ph.getRows());
		dg.setTotal(lvAccountDao.count("select count(*) from TlvAccount " + whereHql, params));
		
//		List<Map<String, Object>> al = new ArrayList<Map<String, Object>>();
		if (l != null && l.size() > 0) {
			for (Map m : l) {
//				LvAccount o = new LvAccount();
//				MyBeanUtils.copyProperties(t, o, true);
//				o.setAge(DateUtil.getAgeByBirthday(o.getBirthday()));
//				map = BeanToMapUtil.convertBean(o, new String[]{"openId", "headImg", "age", "sex", "vipLevel", "vipOpenTime", "vipEndTime", "nickName"});
//				al.add(map);
				m.put("age", DateUtil.getAgeByBirthday((Date)m.get("birthday")));
				m.put("distance", m.get("distance") == null ? -1 : m.get("distance"));
			}
		}
		dg.setRows(l);
		
		return dg;
	}


	/**
	 * 获取一键打招呼列表
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getSayHelloList() {
		List<Map> r = new ArrayList<Map>();
		int pageSize = 50;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sex", "SX02"); // 女
		int gCount = this.getCount(params);
		int totalPage = gCount%pageSize == 0 ? gCount/pageSize : gCount/pageSize + 1;
		Random random = new Random();
		List<Map> gList = lvAccountDao.findBySql2Map("select t.openId, t.nickName, t.headImg, t.auditStatus from lv_account t where t.sex = :sex", params, random.nextInt(totalPage)+1, pageSize);
//		List<LvAccount> gList = this.findListByHql("from TlvAccount t where t.sex = :sex", params, random.nextInt(totalPage)+1, pageSize);
		if(gList != null && gList.size() > 0) {
			int size = Integer.valueOf(Application.getString("SV100"));
			for(int i=0; i<size; i++) {
				r.add(gList.get(random.nextInt(gList.size())));
			}
		}
		
		return r;
	}


	@Override
	public int getCount(Map<String, Object> params) {
		Long l = lvAccountDao.count("select count(*) from TlvAccount t where t.sex = :sex", params);
		return l == null ? 0 : l.intValue();
	}


	@Override
	public List<LvAccount> findListByHql(String hql,
			Map<String, Object> params, int page, int rows) {
		List<LvAccount> ol = new ArrayList<LvAccount>();
		List<TlvAccount> l = lvAccountDao.find(hql, params, page, rows);
		if (l != null && l.size() > 0) {
			for (TlvAccount t : l) {
				LvAccount o = new LvAccount();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		return ol;
	}

}
