package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.LvFeedback;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.LvFeedbackServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * LvFeedback管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/lvFeedbackController")
public class LvFeedbackController extends BaseController {

	@Autowired
	private LvFeedbackServiceI lvFeedbackService;


	/**
	 * 跳转到LvFeedback管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/lvfeedback/lvFeedback";
	}

	/**
	 * 获取LvFeedback数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(LvFeedback lvFeedback, PageHelper ph) {
		return lvFeedbackService.dataGrid(lvFeedback, ph);
	}
	/**
	 * 获取LvFeedback数据表格excel
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
	public void download(LvFeedback lvFeedback, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(lvFeedback,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加LvFeedback页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		LvFeedback lvFeedback = new LvFeedback();
		lvFeedback.setId(UUID.randomUUID().toString());
		return "/lvfeedback/lvFeedbackAdd";
	}

	/**
	 * 添加LvFeedback
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(LvFeedback lvFeedback) {
		Json j = new Json();		
		lvFeedbackService.add(lvFeedback);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到LvFeedback查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		LvFeedback lvFeedback = lvFeedbackService.get(id);
		request.setAttribute("lvFeedback", lvFeedback);
		return "/lvfeedback/lvFeedbackView";
	}

	/**
	 * 跳转到LvFeedback修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		LvFeedback lvFeedback = lvFeedbackService.get(id);
		request.setAttribute("lvFeedback", lvFeedback);
		return "/lvfeedback/lvFeedbackEdit";
	}

	/**
	 * 修改LvFeedback
	 * 
	 * @param lvFeedback
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(LvFeedback lvFeedback) {
		Json j = new Json();		
		lvFeedbackService.edit(lvFeedback);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除LvFeedback
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		lvFeedbackService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
