package jb.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import jb.interceptors.TokenManage;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.LvAccount;
import jb.pageModel.LvAccountPhoto;
import jb.pageModel.LvFollow;
import jb.pageModel.LvVisit;
import jb.pageModel.PageHelper;
import jb.service.LvAccountPhotoServiceI;
import jb.service.LvAccountServiceI;
import jb.service.LvFollowServiceI;
import jb.service.LvVisitServiceI;
import jb.util.Constants;
import jb.util.StringUtil;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 个人中心管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiAccountController")
public class ApiAccountController extends BaseController {
	
	@Autowired
	private LvAccountServiceI accountService;
	
	@Autowired
	private LvVisitServiceI lvVisitService;
	
	@Autowired
	private LvAccountPhotoServiceI photoService;
	
	@Autowired
	private LvFollowServiceI followService;
	
	@Autowired
	private TokenManage tokenManage;
	
	/**
	 * 用户登录
	 * 
	 * @param user
	 *            用户对象
	 * @param session
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/login")
	public Json login(LvAccount lvAccount, HttpServletRequest request) {
		Json j = new Json();
		LvAccount a = accountService.login(lvAccount);
		if (a != null) {
			j.setSuccess(true);
			j.setMsg("登陆成功！");

			/*SessionInfo sessionInfo = new SessionInfo();
			BeanUtils.copyProperties(u, sessionInfo);*/
			String tid = tokenManage.buildToken(a.getId(), a.getNickName());
			j.setObj(tid);
		} else {
			j.setMsg("用户名或密码错误！");
		}
		return j;
	}
	
	/**
	 * 用户注册
	 * @param lvAccount
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/register")
	public Json register(LvAccount lvAccount, HttpServletRequest request) {
		Json j = new Json();
		try {
			lvAccount = accountService.reg(lvAccount);			
			j.setSuccess(true);
			j.setMsg("注册成功");
			String tid = tokenManage.buildToken(lvAccount.getId(),lvAccount.getNickName());
			j.setObj(tid);
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 修改密码
	 * @param lvAccount
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updatePass")
	public Json updatePass(LvAccount lvAccount, HttpServletRequest request) {
		Json j = new Json();
		try {
			accountService.updatePass(lvAccount);			
			j.setSuccess(true);
			j.setMsg("密码修改成功");
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * vip开通
	 * @param lvAccount
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/registerVip")
	public Json registerVip(LvAccount lvAccount, HttpServletRequest request) {
		Json j = new Json();
		try {
			lvAccount.setVipOpenTime(new Date());
			accountService.editByParam(lvAccount);			
			j.setSuccess(true);
			j.setMsg("会员开通成功");
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 最近来访
	 * @param lvAccount
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/recentlyAccount")
	public Json recentlyAccount(LvVisit lvVisit, HttpServletRequest request) {
		Json j = new Json();
		try {
			j.setSuccess(true);
			j.setObj(lvVisitService.queryAllVisitAccount(lvVisit.getOpenId()));
			j.setMsg("最近来访查询成功");
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 头像上传
	 * @param lvAccount
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/headImgUpload")
	public Json headImgUpload(LvAccount lvAccount, @RequestParam MultipartFile headImgFile, HttpServletRequest request) {
		Json j = new Json();
		try {
			String headImg = uploadFile(request, lvAccount.getOpenId(), headImgFile);
			if(headImg == null) {
				j.setMsg("头像上传失败");
			} else {
				lvAccount.setHeadImg(headImg);
				lvAccount.setAuditStatus("AD02");
				accountService.editByParam(lvAccount);
				j.setSuccess(true);
				j.setMsg("头像上传成功");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 我的相册上传
	 * @param lvAccount
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/photoImgUpload")
	public Json photoImgUpload(LvAccountPhoto photo, @RequestParam MultipartFile photoImgFile, HttpServletRequest request) {
		Json j = new Json();
		try {
			String photoImg = uploadFile(request, photo.getOpenId(), photoImgFile);
			if(photoImg == null) {
				j.setMsg("相册上传失败");
			} else {
				photo.setPhotoImg(photoImg);
				photo.setCreateTime(new Date());
				photoService.add(photo);
				j.setSuccess(true);
				j.setMsg("相册上传成功");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 关注我的/我关注的用户列表
	 * @param lvAccount
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/dataGridFollowAccount")
	public Json dataGridFollowAccount(LvFollow lvFollow, PageHelper ph, HttpServletRequest request) {
		Json j = new Json();
		try {
			DataGrid dg = followService.dataGridAccount(lvFollow, ph);
			j.setSuccess(true);
			j.setObj(dg);
			j.setMsg("关注用户列表查询成功");
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 个人相册列表
	 * @param lvAccount
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/dataGridPhoto")
	public Json dataGridPhoto(LvAccountPhoto photo, PageHelper ph, HttpServletRequest request) {
		Json j = new Json();
		try {
			ph.setSort("createTime");
			ph.setOrder("desc");
			DataGrid dg = photoService.dataGrid(photo, ph);
			j.setSuccess(true);
			j.setObj(dg);
			j.setMsg("个人相册列表查询成功");
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 个人资料查看/查看他人资料
	 * @param lvAccount
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/personInfo")
	public Json personInfo(LvAccount lvAccount, HttpServletRequest request) {
		Json j = new Json();
		try {
			lvAccount = accountService.queryPersonInfoByParam(lvAccount);			
			j.setSuccess(true);
			j.setObj(lvAccount);
			j.setMsg("个人资料查询成功");
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	private String uploadFile(HttpServletRequest request,Integer openId, MultipartFile headImageFile){
		if(headImageFile==null||headImageFile.isEmpty())
			return null;
		String realPath = request.getSession().getServletContext().getRealPath("/"+Constants.UPLOADFILE_IMAGE+"/"+openId);  
		File file = new File(realPath);
		if(!file.exists())
			file.mkdir();
		String suffix = headImageFile.getOriginalFilename().substring(headImageFile.getOriginalFilename().lastIndexOf("."));
		String fileName = System.currentTimeMillis() + StringUtil.getRandomNumber(4) + suffix;		
		 try {
			FileUtils.copyInputStreamToFile(headImageFile.getInputStream(), new File(realPath, fileName));
			return Constants.UPLOADFILE_IMAGE+"/"+openId+"/"+fileName;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
