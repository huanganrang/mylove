package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.LvVisit;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.LvVisitServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * LvVisit管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/lvVisitController")
public class LvVisitController extends BaseController {

	@Autowired
	private LvVisitServiceI lvVisitService;


	/**
	 * 跳转到LvVisit管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/lvvisit/lvVisit";
	}

	/**
	 * 获取LvVisit数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(LvVisit lvVisit, PageHelper ph) {
		return lvVisitService.dataGrid(lvVisit, ph);
	}
	/**
	 * 获取LvVisit数据表格excel
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
	public void download(LvVisit lvVisit, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(lvVisit,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加LvVisit页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		LvVisit lvVisit = new LvVisit();
		lvVisit.setId(UUID.randomUUID().toString());
		return "/lvvisit/lvVisitAdd";
	}

	/**
	 * 添加LvVisit
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(LvVisit lvVisit) {
		Json j = new Json();		
		lvVisitService.add(lvVisit);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到LvVisit查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		LvVisit lvVisit = lvVisitService.get(id);
		request.setAttribute("lvVisit", lvVisit);
		return "/lvvisit/lvVisitView";
	}

	/**
	 * 跳转到LvVisit修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		LvVisit lvVisit = lvVisitService.get(id);
		request.setAttribute("lvVisit", lvVisit);
		return "/lvvisit/lvVisitEdit";
	}

	/**
	 * 修改LvVisit
	 * 
	 * @param lvVisit
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(LvVisit lvVisit) {
		Json j = new Json();		
		lvVisitService.edit(lvVisit);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除LvVisit
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		lvVisitService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
