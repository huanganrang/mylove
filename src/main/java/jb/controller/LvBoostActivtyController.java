package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.LvBoostActivty;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.LvBoostActivtyServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

/**
 * LvBoostActivty管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/lvBoostActivtyController")
public class LvBoostActivtyController extends BaseController {

	@Autowired
	private LvBoostActivtyServiceI lvBoostActivtyService;


	/**
	 * 跳转到LvBoostActivty管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/lvboostactivty/lvBoostActivty";
	}

	/**
	 * 获取LvBoostActivty数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(LvBoostActivty lvBoostActivty, PageHelper ph) {
		return lvBoostActivtyService.dataGrid(lvBoostActivty, ph);
	}
	/**
	 * 获取LvBoostActivty数据表格excel
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
	public void download(LvBoostActivty lvBoostActivty, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(lvBoostActivty,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加LvBoostActivty页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		LvBoostActivty lvBoostActivty = new LvBoostActivty();
		lvBoostActivty.setId(UUID.randomUUID().toString());
		return "/lvboostactivty/lvBoostActivtyAdd";
	}

	/**
	 * 添加LvBoostActivty
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(LvBoostActivty lvBoostActivty, @RequestParam MultipartFile goodsImgFile, HttpServletRequest request) {
		Json j = new Json();		
		lvBoostActivty.setGoodsImg(uploadFile(request, "activty", goodsImgFile));
		lvBoostActivtyService.add(lvBoostActivty);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到LvBoostActivty查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		LvBoostActivty lvBoostActivty = lvBoostActivtyService.get(id);
		request.setAttribute("lvBoostActivty", lvBoostActivty);
		return "/lvboostactivty/lvBoostActivtyView";
	}

	/**
	 * 跳转到LvBoostActivty修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		LvBoostActivty lvBoostActivty = lvBoostActivtyService.get(id);
		request.setAttribute("lvBoostActivty", lvBoostActivty);
		return "/lvboostactivty/lvBoostActivtyEdit";
	}

	/**
	 * 修改LvBoostActivty
	 * 
	 * @param lvBoostActivty
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(LvBoostActivty lvBoostActivty, @RequestParam MultipartFile goodsImgFile, HttpServletRequest request) {
		Json j = new Json();	
		lvBoostActivty.setGoodsImg(uploadFile(request, "activty", goodsImgFile));
		lvBoostActivtyService.edit(lvBoostActivty);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除LvBoostActivty
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		lvBoostActivtyService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
