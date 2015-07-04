package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.LvFollow;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.LvFollowServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * LvFollow管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/lvFollowController")
public class LvFollowController extends BaseController {

	@Autowired
	private LvFollowServiceI lvFollowService;


	/**
	 * 跳转到LvFollow管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/lvfollow/lvFollow";
	}

	/**
	 * 获取LvFollow数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(LvFollow lvFollow, PageHelper ph) {
		return lvFollowService.dataGrid(lvFollow, ph);
	}
	/**
	 * 获取LvFollow数据表格excel
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
	public void download(LvFollow lvFollow, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(lvFollow,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加LvFollow页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		LvFollow lvFollow = new LvFollow();
		lvFollow.setId(UUID.randomUUID().toString());
		return "/lvfollow/lvFollowAdd";
	}

	/**
	 * 添加LvFollow
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(LvFollow lvFollow) {
		Json j = new Json();		
		lvFollowService.add(lvFollow);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到LvFollow查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		LvFollow lvFollow = lvFollowService.get(id);
		request.setAttribute("lvFollow", lvFollow);
		return "/lvfollow/lvFollowView";
	}

	/**
	 * 跳转到LvFollow修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		LvFollow lvFollow = lvFollowService.get(id);
		request.setAttribute("lvFollow", lvFollow);
		return "/lvfollow/lvFollowEdit";
	}

	/**
	 * 修改LvFollow
	 * 
	 * @param lvFollow
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(LvFollow lvFollow) {
		Json j = new Json();		
		lvFollowService.edit(lvFollow);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除LvFollow
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		lvFollowService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
