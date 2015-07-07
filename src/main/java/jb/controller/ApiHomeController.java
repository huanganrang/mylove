package jb.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import jb.pageModel.Json;
import jb.pageModel.LvFollow;
import jb.service.LvAccountPhotoServiceI;
import jb.service.LvAccountServiceI;
import jb.service.LvFollowServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * home管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiHomeController")
public class ApiHomeController extends BaseController {
	
	@Autowired
	private LvAccountServiceI accountService;
	
	@Autowired
	private LvAccountPhotoServiceI photoService;
	
	@Autowired
	private LvFollowServiceI followService;
	
	/**
	 * 关注用户
	 * @param ua
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/accountFollow")
	public Json accountFollow(LvFollow f, HttpServletRequest request) {
		Json j = new Json();
		try {
			f.setCreateTime(new Date());
			int r = followService.add(f);
			if(r == -1) {
				j.setMsg("已经关注！");
			} else {
				j.setSuccess(true);
				j.setMsg("关注成功！");
			}
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	
	
	/**
	 * 取消关注用户
	 * @param ua
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cancelAccountFollow")
	public Json cancelAccountFollow(LvFollow f, HttpServletRequest request) {
		Json j = new Json();
		try {
			int r = followService.delete(f);
			if(r == -1) {
				j.setMsg("已经取消！");
			} else {
				j.setSuccess(true);
				j.setMsg("取消关注成功！");
			}
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
}
