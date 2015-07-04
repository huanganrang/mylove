﻿package jb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jb.pageModel.Colum;
import jb.pageModel.LvAccount;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.PageHelper;
import jb.service.LvAccountServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * LvAccount管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/lvAccountController")
public class LvAccountController extends BaseController {

	@Autowired
	private LvAccountServiceI lvAccountService;


	/**
	 * 跳转到LvAccount管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/lvaccount/lvAccount";
	}

	/**
	 * 获取LvAccount数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(LvAccount lvAccount, PageHelper ph) {
		return lvAccountService.dataGrid(lvAccount, ph);
	}
	/**
	 * 获取LvAccount数据表格excel
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
	public void download(LvAccount lvAccount, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(lvAccount,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加LvAccount页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		LvAccount lvAccount = new LvAccount();
		lvAccount.setId(UUID.randomUUID().toString());
		return "/lvaccount/lvAccountAdd";
	}

	/**
	 * 添加LvAccount
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(LvAccount lvAccount) {
		Json j = new Json();		
		lvAccountService.add(lvAccount);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到LvAccount查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		LvAccount lvAccount = lvAccountService.get(id);
		request.setAttribute("lvAccount", lvAccount);
		return "/lvaccount/lvAccountView";
	}

	/**
	 * 跳转到LvAccount修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		LvAccount lvAccount = lvAccountService.get(id);
		request.setAttribute("lvAccount", lvAccount);
		return "/lvaccount/lvAccountEdit";
	}

	/**
	 * 修改LvAccount
	 * 
	 * @param lvAccount
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(LvAccount lvAccount) {
		Json j = new Json();		
		lvAccountService.edit(lvAccount);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除LvAccount
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		lvAccountService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
