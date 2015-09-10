package jb.dao;

import java.util.HashMap;

import jb.model.TlvAccountPhoto;

/**
 * LvAccountPhoto数据库操作类
 * 
 * @author John
 * 
 */
public interface LvAccountPhotoDaoI extends BaseDaoI<TlvAccountPhoto> {

	/**
	 * 统计相片数
	 * @param businessType
	 * @param businessIds
	 * @return
	 */
	public HashMap<Integer,Integer> getCountPhotoNum(String... openIds);
}
