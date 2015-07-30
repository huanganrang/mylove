package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.LvOrder;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.LvOrderServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * LvOrder管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/lvOrderController")
public class LvOrderController extends BaseController {

	@Autowired
	private LvOrderServiceI lvOrderService;


	/**
	 * 跳转到LvOrder管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/lvorder/lvOrder";
	}

	/**
	 * 获取LvOrder数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(LvOrder lvOrder, PageHelper ph) {
		return lvOrderService.dataGrid(lvOrder, ph);
	}
	/**
	 * 获取LvOrder数据表格excel
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
	public void download(LvOrder lvOrder, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(lvOrder,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加LvOrder页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		LvOrder lvOrder = new LvOrder();
		lvOrder.setId(UUID.randomUUID().toString());
		return "/lvorder/lvOrderAdd";
	}

	/**
	 * 添加LvOrder
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(LvOrder lvOrder) {
		Json j = new Json();		
		lvOrderService.add(lvOrder);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到LvOrder查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		LvOrder lvOrder = lvOrderService.get(id);
		request.setAttribute("lvOrder", lvOrder);
		return "/lvorder/lvOrderView";
	}

	/**
	 * 跳转到LvOrder修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		LvOrder lvOrder = lvOrderService.get(id);
		request.setAttribute("lvOrder", lvOrder);
		return "/lvorder/lvOrderEdit";
	}

	/**
	 * 修改LvOrder
	 * 
	 * @param lvOrder
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(LvOrder lvOrder) {
		Json j = new Json();		
		lvOrderService.edit(lvOrder);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除LvOrder
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		lvOrderService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
