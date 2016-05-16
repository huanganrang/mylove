package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.LvFreeConfig;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.LvFreeConfigServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * LvFreeConfig管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/lvFreeConfigController")
public class LvFreeConfigController extends BaseController {

	@Autowired
	private LvFreeConfigServiceI lvFreeConfigService;


	/**
	 * 跳转到LvFreeConfig管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/lvfreeconfig/lvFreeConfig";
	}

	/**
	 * 获取LvFreeConfig数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(LvFreeConfig lvFreeConfig, PageHelper ph) {
		return lvFreeConfigService.dataGrid(lvFreeConfig, ph);
	}
	/**
	 * 获取LvFreeConfig数据表格excel
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
	public void download(LvFreeConfig lvFreeConfig, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(lvFreeConfig,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加LvFreeConfig页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		LvFreeConfig lvFreeConfig = new LvFreeConfig();
		lvFreeConfig.setId(UUID.randomUUID().toString());
		return "/lvfreeconfig/lvFreeConfigAdd";
	}

	/**
	 * 添加LvFreeConfig
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(LvFreeConfig lvFreeConfig) {
		Json j = new Json();		
		lvFreeConfigService.add(lvFreeConfig);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到LvFreeConfig查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		LvFreeConfig lvFreeConfig = lvFreeConfigService.get(id);
		request.setAttribute("lvFreeConfig", lvFreeConfig);
		return "/lvfreeconfig/lvFreeConfigView";
	}

	/**
	 * 跳转到LvFreeConfig修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		LvFreeConfig lvFreeConfig = lvFreeConfigService.get(id);
		request.setAttribute("lvFreeConfig", lvFreeConfig);
		return "/lvfreeconfig/lvFreeConfigEdit";
	}

	/**
	 * 修改LvFreeConfig
	 * 
	 * @param lvFreeConfig
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(LvFreeConfig lvFreeConfig) {
		Json j = new Json();		
		lvFreeConfigService.edit(lvFreeConfig);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除LvFreeConfig
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		lvFreeConfigService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
