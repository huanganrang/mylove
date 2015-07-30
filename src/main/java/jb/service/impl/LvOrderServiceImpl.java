package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.LvOrderDaoI;
import jb.model.TlvOrder;
import jb.pageModel.DataGrid;
import jb.pageModel.LvOrder;
import jb.pageModel.PageHelper;
import jb.service.LvOrderServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvOrderServiceImpl extends BaseServiceImpl<LvOrder> implements LvOrderServiceI {

	@Autowired
	private LvOrderDaoI lvOrderDao;

	@Override
	public DataGrid dataGrid(LvOrder lvOrder, PageHelper ph) {
		List<LvOrder> ol = new ArrayList<LvOrder>();
		String hql = " from TlvOrder t ";
		DataGrid dg = dataGridQuery(hql, ph, lvOrder, lvOrderDao);
		@SuppressWarnings("unchecked")
		List<TlvOrder> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TlvOrder t : l) {
				LvOrder o = new LvOrder();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(LvOrder lvOrder, Map<String, Object> params) {
		String whereHql = "";	
		if (lvOrder != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(lvOrder.getVipLevel())) {
				whereHql += " and t.vipLevel = :vipLevel";
				params.put("vipLevel", lvOrder.getVipLevel());
			}		
			if (!F.empty(lvOrder.getOrderStatus())) {
				whereHql += " and t.orderStatus = :orderStatus";
				params.put("orderStatus", lvOrder.getOrderStatus());
			}		
			if (!F.empty(lvOrder.getChannel())) {
				whereHql += " and t.channel = :channel";
				params.put("channel", lvOrder.getChannel());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(LvOrder lvOrder) {
		lvOrder.setId(UUID.randomUUID().toString());
		TlvOrder t = new TlvOrder();
		BeanUtils.copyProperties(lvOrder, t);
		lvOrderDao.save(t);
	}

	@Override
	public LvOrder get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TlvOrder t = lvOrderDao.get("from TlvOrder t  where t.id = :id", params);
		LvOrder o = new LvOrder();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(LvOrder lvOrder) {
		TlvOrder t = lvOrderDao.get(TlvOrder.class, lvOrder.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(lvOrder, t, new String[] { "id" , "createdatetime" },true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvOrderDao.delete(lvOrderDao.get(TlvOrder.class, id));
	}

}
