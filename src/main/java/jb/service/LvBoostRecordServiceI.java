package jb.service;

import java.util.List;

import jb.pageModel.LvBoostRecord;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface LvBoostRecordServiceI {

	/**
	 * 获取LvBoostRecord数据表格
	 * 
	 * @param lvBoostRecord
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(LvBoostRecord lvBoostRecord, PageHelper ph);

	/**
	 * 添加LvBoostRecord
	 * 
	 * @param lvBoostRecord
	 */
	public void add(LvBoostRecord lvBoostRecord);

	/**
	 * 获得LvBoostRecord对象
	 * 
	 * @param id
	 * @return
	 */
	public LvBoostRecord get(String id);

	/**
	 * 修改LvBoostRecord
	 * 
	 * @param lvBoostRecord
	 */
	public void edit(LvBoostRecord lvBoostRecord);

	/**
	 * 删除LvBoostRecord
	 * 
	 * @param id
	 */
	public void delete(String id);

	public List<LvBoostRecord> findBoostInfoList(Integer openId);

	public List<LvBoostRecord> findBoostRecordList(Integer openId, Integer type);

	public DataGrid dataGridAssistList(Integer openId, PageHelper ph);

	public LvBoostRecord get(LvBoostRecord record);

}
