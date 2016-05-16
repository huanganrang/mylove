package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.LvNotify;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.LvNotifyServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * LvNotify管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/lvNotifyController")
public class LvNotifyController extends BaseController {

	@Autowired
	private LvNotifyServiceI lvNotifyService;


	/**
	 * 跳转到LvNotify管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/lvnotify/lvNotify";
	}

	/**
	 * 获取LvNotify数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(LvNotify lvNotify, PageHelper ph) {
		return lvNotifyService.dataGrid(lvNotify, ph);
	}
	/**
	 * 获取LvNotify数据表格excel
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
	public void download(LvNotify lvNotify, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(lvNotify,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加LvNotify页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		LvNotify lvNotify = new LvNotify();
		lvNotify.setId(UUID.randomUUID().toString());
		return "/lvnotify/lvNotifyAdd";
	}

	/**
	 * 添加LvNotify
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(LvNotify lvNotify) {
		Json j = new Json();		
		lvNotifyService.add(lvNotify);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到LvNotify查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		LvNotify lvNotify = lvNotifyService.get(id);
		request.setAttribute("lvNotify", lvNotify);
		return "/lvnotify/lvNotifyView";
	}

	/**
	 * 跳转到LvNotify修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		LvNotify lvNotify = lvNotifyService.get(id);
		request.setAttribute("lvNotify", lvNotify);
		return "/lvnotify/lvNotifyEdit";
	}

	/**
	 * 修改LvNotify
	 * 
	 * @param lvNotify
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(LvNotify lvNotify) {
		Json j = new Json();		
		lvNotifyService.edit(lvNotify);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除LvNotify
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		lvNotifyService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
