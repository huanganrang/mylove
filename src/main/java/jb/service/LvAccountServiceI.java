package jb.service;

import java.util.List;
import java.util.Map;

import jb.pageModel.AccountSearch;
import jb.pageModel.DataGrid;
import jb.pageModel.LvAccount;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface LvAccountServiceI {

	/**
	 * 获取LvAccount数据表格
	 * 
	 * @param lvAccount
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(LvAccount lvAccount, PageHelper ph);

	/**
	 * 添加LvAccount
	 * 
	 * @param lvAccount
	 */
	public void add(LvAccount lvAccount);

	/**
	 * 获得LvAccount对象
	 * 
	 * @param id
	 * @return
	 */
	public LvAccount get(String id);

	/**
	 * 修改LvAccount
	 * 
	 * @param lvAccount
	 */
	public void edit(LvAccount lvAccount);

	/**
	 * 删除LvAccount
	 * 
	 * @param id
	 */
	public void delete(String id);

	public LvAccount reg(LvAccount lvAccount) throws Exception;

	public LvAccount login(LvAccount lvAccount);

	public void updatePass(LvAccount lvAccount) throws Exception ;

	public void editByParam(LvAccount lvAccount) throws Exception ;

	public LvAccount queryPersonInfoByParam(LvAccount lvAccount);

	public LvAccount get(Integer openId);

	public DataGrid dataGridAccount_search(AccountSearch search, PageHelper ph);

	public void registerVip(LvAccount lvAccount);

	@SuppressWarnings("rawtypes")
	public List<Map> getSayHelloList();
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public int getCount(Map<String, Object> params);

	public List<LvAccount> findListByHql(String hql, Map<String, Object> params, int page, int rows);

	public void syncHxAccount();
}
