package jb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jb.pageModel.Json;
import jb.pageModel.LvAccount;
import jb.service.LvBoostActivtyServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 挖宝管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiBoostController")
public class ApiBoostController extends BaseController {
	
	@Autowired
	private LvBoostActivtyServiceI boostActivtyService;
	
	/**
	 * 宝贝-活动列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/activtyList")
	public Json activtyList() {
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("登陆成功！");
		j.setObj(boostActivtyService.findAllList());
		return j;
	}
}
