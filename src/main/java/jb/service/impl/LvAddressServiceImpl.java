package jb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.LvAddressDaoI;
import jb.model.TlvAddress;
import jb.pageModel.DataGrid;
import jb.pageModel.LvAddress;
import jb.pageModel.PageHelper;
import jb.service.LvAddressServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvAddressServiceImpl extends BaseServiceImpl<LvAddress> implements LvAddressServiceI {

	@Autowired
	private LvAddressDaoI lvAddressDao;

	@Override
	public DataGrid dataGrid(LvAddress lvAddress, PageHelper ph) {
		List<LvAddress> ol = new ArrayList<LvAddress>();
		String hql = " from TlvAddress t ";
		DataGrid dg = dataGridQuery(hql, ph, lvAddress, lvAddressDao);
		@SuppressWarnings("unchecked")
		List<TlvAddress> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TlvAddress t : l) {
				LvAddress o = new LvAddress();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(LvAddress lvAddress, Map<String, Object> params) {
		String whereHql = "";	
		if (lvAddress != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(lvAddress.getConsignee())) {
				whereHql += " and t.consignee = :consignee";
				params.put("consignee", lvAddress.getConsignee());
			}		
			if (!F.empty(lvAddress.getMobile())) {
				whereHql += " and t.mobile = :mobile";
				params.put("mobile", lvAddress.getMobile());
			}		
			if (!F.empty(lvAddress.getProvince())) {
				whereHql += " and t.province = :province";
				params.put("province", lvAddress.getProvince());
			}		
			if (!F.empty(lvAddress.getCity())) {
				whereHql += " and t.city = :city";
				params.put("city", lvAddress.getCity());
			}		
			if (!F.empty(lvAddress.getDistrict())) {
				whereHql += " and t.district = :district";
				params.put("district", lvAddress.getDistrict());
			}		
			if (!F.empty(lvAddress.getAddress())) {
				whereHql += " and t.address = :address";
				params.put("address", lvAddress.getAddress());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(LvAddress lvAddress) {
		TlvAddress t = new TlvAddress();
		BeanUtils.copyProperties(lvAddress, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		t.setCreateTime(new Date());
		lvAddressDao.save(t);
	}

	@Override
	public LvAddress get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TlvAddress t = lvAddressDao.get("from TlvAddress t  where t.id = :id", params);
		LvAddress o = new LvAddress();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(LvAddress lvAddress) {
		TlvAddress t = lvAddressDao.get(TlvAddress.class, lvAddress.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(lvAddress, t, new String[] { "id" , "createdatetime" },true);
			t.setUpdateTime(new Date());
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvAddressDao.delete(lvAddressDao.get(TlvAddress.class, id));
	}


	/**
	 * 根据openId查询收货地址
	 */
	public LvAddress getByOpenId(Integer openId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openId);
		TlvAddress t = lvAddressDao.get("from TlvAddress t where t.openId = :openId", params);
		LvAddress o = null;
		if(t != null) {
			o = new LvAddress();
			BeanUtils.copyProperties(t, o);
		}
		return o;
	}


	@Override
	public void saveOrUpdate(LvAddress address) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", address.getOpenId());
		TlvAddress t = lvAddressDao.get("from TlvAddress t where t.openId = :openId", params);
		if(t != null) {
			MyBeanUtils.copyProperties(address, t, new String[] {"id", "openId"},true);
		} else {
			add(address);
		}
	}

}
