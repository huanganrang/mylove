package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.LvAddress;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.LvAddressServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * LvAddress管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/lvAddressController")
public class LvAddressController extends BaseController {

	@Autowired
	private LvAddressServiceI lvAddressService;


	/**
	 * 跳转到LvAddress管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/lvaddress/lvAddress";
	}

	/**
	 * 获取LvAddress数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(LvAddress lvAddress, PageHelper ph) {
		return lvAddressService.dataGrid(lvAddress, ph);
	}
	/**
	 * 获取LvAddress数据表格excel
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
	public void download(LvAddress lvAddress, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(lvAddress,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加LvAddress页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		LvAddress lvAddress = new LvAddress();
		lvAddress.setId(UUID.randomUUID().toString());
		return "/lvaddress/lvAddressAdd";
	}

	/**
	 * 添加LvAddress
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(LvAddress lvAddress) {
		Json j = new Json();		
		lvAddressService.add(lvAddress);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到LvAddress查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		LvAddress lvAddress = lvAddressService.get(id);
		request.setAttribute("lvAddress", lvAddress);
		return "/lvaddress/lvAddressView";
	}

	/**
	 * 跳转到LvAddress修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		LvAddress lvAddress = lvAddressService.get(id);
		request.setAttribute("lvAddress", lvAddress);
		return "/lvaddress/lvAddressEdit";
	}

	/**
	 * 修改LvAddress
	 * 
	 * @param lvAddress
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(LvAddress lvAddress) {
		Json j = new Json();		
		lvAddressService.edit(lvAddress);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除LvAddress
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		lvAddressService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
