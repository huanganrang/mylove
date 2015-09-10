package jb.dao.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jb.dao.LvAccountPhotoDaoI;
import jb.model.TlvAccountPhoto;

import org.springframework.stereotype.Repository;

@Repository
public class LvAccountPhotoDaoImpl extends BaseDaoImpl<TlvAccountPhoto> implements LvAccountPhotoDaoI {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public HashMap<Integer, Integer> getCountPhotoNum(String... openIds) {
		String in = "";
		for(String id : openIds){
			in += ",'"+id+"'";
		}		
		String countSql = "select count(*) as num,openId from lv_account_photo where openId in ("+in.substring(1)+") group by openId";
		Map<String, Object> params = new HashMap<String, Object>();
		//params.put("businessId",businessIds);
		List<Map> results = findBySql2Map(countSql, params);
		Map countMap = new HashMap();
		for(Map map : results){
			countMap.put(map.get("openId"),((BigInteger)map.get("num")).intValue());
		}
		return (HashMap<Integer, Integer>)countMap;
	}

}
