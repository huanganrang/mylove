package jb.service;

import jb.pageModel.LvAccountPhoto;
import jb.pageModel.DataGrid;
import jb.pageModel.PageHelper;

/**
 * 
 * @author John
 * 
 */
public interface LvAccountPhotoServiceI {

	/**
	 * 获取LvAccountPhoto数据表格
	 * 
	 * @param lvAccountPhoto
	 *            参数
	 * @param ph
	 *            分页帮助类
	 * @return
	 */
	public DataGrid dataGrid(LvAccountPhoto lvAccountPhoto, PageHelper ph);

	/**
	 * 添加LvAccountPhoto
	 * 
	 * @param lvAccountPhoto
	 */
	public void add(LvAccountPhoto lvAccountPhoto);

	/**
	 * 获得LvAccountPhoto对象
	 * 
	 * @param id
	 * @return
	 */
	public LvAccountPhoto get(String id);

	/**
	 * 修改LvAccountPhoto
	 * 
	 * @param lvAccountPhoto
	 */
	public void edit(LvAccountPhoto lvAccountPhoto);

	/**
	 * 删除LvAccountPhoto
	 * 
	 * @param id
	 */
	public void delete(String id);

}
