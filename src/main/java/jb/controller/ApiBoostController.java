package jb.controller;

import javax.servlet.http.HttpServletRequest;

import jb.pageModel.Json;
import jb.pageModel.LvAddress;
import jb.pageModel.LvAssistLog;
import jb.pageModel.LvBoostActivty;
import jb.pageModel.LvBoostRecord;
import jb.pageModel.PageHelper;
import jb.service.LvAddressServiceI;
import jb.service.LvAssistLogServiceI;
import jb.service.LvBoostActivtyServiceI;
import jb.service.LvBoostRecordServiceI;

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
	
	@Autowired
	private LvBoostRecordServiceI boostRecordService;
	
	@Autowired
	private LvAssistLogServiceI assistLogService;
	
	@Autowired
	private LvAddressServiceI addressService;
	
	/**
	 * 助力墙列表（男）
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/assistList")
	public Json assistList(Integer openId, PageHelper ph) {
		Json j = new Json();
		try {
			j.setSuccess(true);
			j.setMsg("宝贝-活动列表查询成功！");
			j.setObj(boostRecordService.dataGridAssistList(openId, ph));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 查看助力（男）
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addBoostVisitNum")
	public Json addBoostVisitNum(String boostRecordId) {
		Json j = new Json();
		try {
			LvBoostRecord r = boostRecordService.get(boostRecordId);
			r.setVisitNum(r.getVisitNum() + 1);
			boostRecordService.edit(r);
			j.setSuccess(true);
			j.setMsg("查看助力成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 为她助力（男）
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/assist")
	public Json assist(LvAssistLog log) {
		Json j = new Json();
		try {
			if(assistLogService.add(log) == 1) {
				j.setSuccess(true);
				j.setMsg("为她助力(男)成功！");
			} else {
				j.setMsg("已经助力过，不可重复助力！");
			}
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	
	/**
	 * 助力成功
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addBoostAssistNum")
	public Json addBoostAssistNum(String boostRecordId) {
		Json j = new Json();
		try {
			LvBoostRecord r = boostRecordService.get(boostRecordId);
			r.setAssistNum(r.getAssistNum() + 1);
			boostRecordService.edit(r);
			j.setSuccess(true);
			j.setMsg("助力成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 宝贝-活动列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/activtyList")
	public Json activtyList(Integer openId, Integer hourOfDay) {
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("宝贝-活动列表查询成功！");
		j.setObj(boostActivtyService.findAllList(openId, hourOfDay));
		return j;
	}
	
	/**
	 * 开始挖宝
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addBoost")
	public Json addBoost(LvBoostRecord br) {
		Json j = new Json();
		try {
			boostRecordService.add(br);
			j.setSuccess(true);
			j.setMsg("挖宝成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		
		return j;
	}
	
	/**
	 * 商品详情
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/activtyDetail")
	public Json activtyDetail(LvBoostActivty ba, HttpServletRequest request) {
		Json j = new Json();
		try {
			j.setSuccess(true);
			j.setMsg("商品详情查询成功！");
			j.setObj(boostActivtyService.getActivtyDetail(ba.getId()));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		
		return j;
	}
	
	/**
	 * 助力信息列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/boostInfo")
	public Json boostInfo(Integer openId) {
		Json j = new Json();
		try {
			j.setSuccess(true);
			j.setMsg("助力信息列表查询成功！");
			j.setObj(boostRecordService.findBoostInfoList(openId));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		
		return j;
	}
	
	/**
	 * 挖宝记录列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/boostRecord")
	public Json boostRecord(Integer openId, Integer type) {
		Json j = new Json();
		try {
			j.setSuccess(true);
			j.setMsg("挖宝记录列表查询成功！");
			j.setObj(boostRecordService.findBoostRecordList(openId, type));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		
		return j;
	}
	
	/**
	 * 收货地址查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addressInfo")
	public Json addressInfo(Integer openId) {
		Json j = new Json();
		try {
			j.setSuccess(true);
			j.setMsg("收货地址查询成功！");
			j.setObj(addressService.getByOpenId(openId));
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		
		return j;
	}
	
	/**
	 * 新增或修改收货地址
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editAddress")
	public Json editAddress(LvAddress address) {
		Json j = new Json();
		try {
			addressService.saveOrUpdate(address);
			j.setSuccess(true);
			j.setMsg("收货地址修改成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		
		return j;
	}
}
