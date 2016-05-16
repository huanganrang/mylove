package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.LvPartnerCondition;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.LvPartnerConditionServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * LvPartnerCondition管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/lvPartnerConditionController")
public class LvPartnerConditionController extends BaseController {

	@Autowired
	private LvPartnerConditionServiceI lvPartnerConditionService;


	/**
	 * 跳转到LvPartnerCondition管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/lvpartnercondition/lvPartnerCondition";
	}

	/**
	 * 获取LvPartnerCondition数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(LvPartnerCondition lvPartnerCondition, PageHelper ph) {
		return lvPartnerConditionService.dataGrid(lvPartnerCondition, ph);
	}
	/**
	 * 获取LvPartnerCondition数据表格excel
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
	public void download(LvPartnerCondition lvPartnerCondition, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(lvPartnerCondition,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加LvPartnerCondition页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		LvPartnerCondition lvPartnerCondition = new LvPartnerCondition();
		lvPartnerCondition.setId(UUID.randomUUID().toString());
		return "/lvpartnercondition/lvPartnerConditionAdd";
	}

	/**
	 * 添加LvPartnerCondition
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(LvPartnerCondition lvPartnerCondition) {
		Json j = new Json();		
		lvPartnerConditionService.add(lvPartnerCondition);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到LvPartnerCondition查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		LvPartnerCondition lvPartnerCondition = lvPartnerConditionService.get(id);
		request.setAttribute("lvPartnerCondition", lvPartnerCondition);
		return "/lvpartnercondition/lvPartnerConditionView";
	}

	/**
	 * 跳转到LvPartnerCondition修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		LvPartnerCondition lvPartnerCondition = lvPartnerConditionService.get(id);
		request.setAttribute("lvPartnerCondition", lvPartnerCondition);
		return "/lvpartnercondition/lvPartnerConditionEdit";
	}

	/**
	 * 修改LvPartnerCondition
	 * 
	 * @param lvPartnerCondition
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(LvPartnerCondition lvPartnerCondition) {
		Json j = new Json();		
		lvPartnerConditionService.edit(lvPartnerCondition);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除LvPartnerCondition
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		lvPartnerConditionService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
