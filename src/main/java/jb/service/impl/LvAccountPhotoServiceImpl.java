package jb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jb.absx.F;
import jb.dao.LvAccountPhotoDaoI;
import jb.model.TlvAccountPhoto;
import jb.pageModel.DataGrid;
import jb.pageModel.LvAccountPhoto;
import jb.pageModel.PageHelper;
import jb.service.LvAccountPhotoServiceI;
import jb.util.MyBeanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LvAccountPhotoServiceImpl extends BaseServiceImpl<LvAccountPhoto> implements LvAccountPhotoServiceI {

	@Autowired
	private LvAccountPhotoDaoI lvAccountPhotoDao;

	@Override
	public DataGrid dataGrid(LvAccountPhoto lvAccountPhoto, PageHelper ph) {
		List<LvAccountPhoto> ol = new ArrayList<LvAccountPhoto>();
		String hql = " from TlvAccountPhoto t ";
		DataGrid dg = dataGridQuery(hql, ph, lvAccountPhoto, lvAccountPhotoDao);
		@SuppressWarnings("unchecked")
		List<TlvAccountPhoto> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TlvAccountPhoto t : l) {
				LvAccountPhoto o = new LvAccountPhoto();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(LvAccountPhoto lvAccountPhoto, Map<String, Object> params) {
		String whereHql = "";	
		if (lvAccountPhoto != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(lvAccountPhoto.getPhotoImg())) {
				whereHql += " and t.photoImg = :photoImg";
				params.put("photoImg", lvAccountPhoto.getPhotoImg());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(LvAccountPhoto lvAccountPhoto) {
		TlvAccountPhoto t = new TlvAccountPhoto();
		BeanUtils.copyProperties(lvAccountPhoto, t);
		t.setId(UUID.randomUUID().toString());
		//t.setCreatedatetime(new Date());
		lvAccountPhotoDao.save(t);
	}

	@Override
	public LvAccountPhoto get(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TlvAccountPhoto t = lvAccountPhotoDao.get("from TlvAccountPhoto t  where t.id = :id", params);
		LvAccountPhoto o = new LvAccountPhoto();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(LvAccountPhoto lvAccountPhoto) {
		TlvAccountPhoto t = lvAccountPhotoDao.get(TlvAccountPhoto.class, lvAccountPhoto.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(lvAccountPhoto, t, new String[] { "id" , "createdatetime" }, true);
			//t.setModifydatetime(new Date());
		}
	}

	@Override
	public void delete(String id) {
		lvAccountPhotoDao.delete(lvAccountPhotoDao.get(TlvAccountPhoto.class, id));
	}

}
