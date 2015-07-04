package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.LvAccountPhoto;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.LvAccountPhotoServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * LvAccountPhoto管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/lvAccountPhotoController")
public class LvAccountPhotoController extends BaseController {

	@Autowired
	private LvAccountPhotoServiceI lvAccountPhotoService;


	/**
	 * 跳转到LvAccountPhoto管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/lvaccountphoto/lvAccountPhoto";
	}

	/**
	 * 获取LvAccountPhoto数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(LvAccountPhoto lvAccountPhoto, PageHelper ph) {
		return lvAccountPhotoService.dataGrid(lvAccountPhoto, ph);
	}
	/**
	 * 获取LvAccountPhoto数据表格excel
	 * 
	 * @param user
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 */
	@RequestMapping("/download")
	public void download(LvAccountPhoto lvAccountPhoto, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(lvAccountPhoto,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加LvAccountPhoto页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		LvAccountPhoto lvAccountPhoto = new LvAccountPhoto();
		lvAccountPhoto.setId(UUID.randomUUID().toString());
		return "/lvaccountphoto/lvAccountPhotoAdd";
	}

	/**
	 * 添加LvAccountPhoto
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(LvAccountPhoto lvAccountPhoto) {
		Json j = new Json();		
		lvAccountPhotoService.add(lvAccountPhoto);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到LvAccountPhoto查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		LvAccountPhoto lvAccountPhoto = lvAccountPhotoService.get(id);
		request.setAttribute("lvAccountPhoto", lvAccountPhoto);
		return "/lvaccountphoto/lvAccountPhotoView";
	}

	/**
	 * 跳转到LvAccountPhoto修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		LvAccountPhoto lvAccountPhoto = lvAccountPhotoService.get(id);
		request.setAttribute("lvAccountPhoto", lvAccountPhoto);
		return "/lvaccountphoto/lvAccountPhotoEdit";
	}

	/**
	 * 修改LvAccountPhoto
	 * 
	 * @param lvAccountPhoto
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(LvAccountPhoto lvAccountPhoto) {
		Json j = new Json();		
		lvAccountPhotoService.edit(lvAccountPhoto);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除LvAccountPhoto
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		lvAccountPhotoService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
