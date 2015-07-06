package jb.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import jb.absx.F;
import jb.interceptors.TokenManage;
import jb.pageModel.Bshoot;
import jb.pageModel.BshootToSquare;
import jb.pageModel.DataGrid;
import jb.pageModel.Json;
import jb.pageModel.LvAccount;
import jb.pageModel.LvVisit;
import jb.pageModel.PageHelper;
import jb.pageModel.SessionInfo;
import jb.pageModel.User;
import jb.pageModel.UserAttention;
import jb.service.BshootCollectServiceI;
import jb.service.BshootServiceI;
import jb.service.BshootToSquareServiceI;
import jb.service.LvAccountServiceI;
import jb.service.LvVisitServiceI;
import jb.service.UserAttentionServiceI;
import jb.service.UserServiceI;
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
 * Bshoot管理控制器
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
	private UserServiceI userService;
	
	@Autowired
	private TokenManage tokenManage;
	
	@Autowired
	private BshootServiceI bshootService;
	
	@Autowired
	private BshootCollectServiceI bshootCollectService;
	
	@Autowired
	private UserAttentionServiceI userAttentionService;
	
	@Autowired
	private BshootToSquareServiceI bshootToSquareService;
	
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
	 * 最近来访
	 * @param lvAccount
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/headImgUpload")
	public Json headImgUpload(LvAccount lvAccount, @RequestParam MultipartFile headImgFile, HttpServletRequest request) {
		Json j = new Json();
		try {
			uploadFile(request, lvAccount, headImgFile);
			lvAccount.setAuditStatus("AD02");
			accountService.editByParam(lvAccount);
			j.setSuccess(true);
			j.setMsg("头像上传成功");
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
	
	private void uploadFile(HttpServletRequest request,LvAccount lvAccount,MultipartFile headImageFile){
		if(headImageFile==null||headImageFile.isEmpty())
			return;
		String realPath = request.getSession().getServletContext().getRealPath("/"+Constants.UPLOADFILE_IMAGE+"/"+lvAccount.getOpenId());  
		File file = new File(realPath);
		if(!file.exists())
			file.mkdir();
		String suffix = headImageFile.getOriginalFilename().substring(headImageFile.getOriginalFilename().lastIndexOf("."));
		String fileName = System.currentTimeMillis() + StringUtil.getRandomNumber(4) + suffix;		
		 try {
			FileUtils.copyInputStreamToFile(headImageFile.getInputStream(), new File(realPath, fileName));
			lvAccount.setHeadImg(Constants.UPLOADFILE_IMAGE+"/"+lvAccount.getOpenId()+"/"+fileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private SessionInfo getSessionInfo(HttpServletRequest request){
		SessionInfo s = tokenManage.getSessionInfo(request);
		return s;		
	}
	
	/**
	 * 关注用户
	 * @param ua
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/user_attention")
	public Json userAttention(UserAttention ua,HttpServletRequest request) {
		Json j = new Json();		
		SessionInfo s = getSessionInfo(request);
		ua.setUserId(s.getId());		
		int r = userAttentionService.add(ua);
		if(r==-1){
			j.setSuccess(false);
			j.setMsg("已经关注！");
		}else{
			j.setSuccess(true);
			j.setMsg("成功！");
			addMessage("MT01",ua.getAttUserId(),ua.getUserId());
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
	@RequestMapping("/user_disattention")
	public Json disUserAttention(UserAttention ua,HttpServletRequest request) {
		Json j = new Json();
		SessionInfo s = getSessionInfo(request);
		ua.setUserId(s.getId());
		int r = userAttentionService.deleteUa(ua);
		if(r==-1){
			j.setSuccess(false);
			j.setMsg("已经取消！");
		}else{
			j.setSuccess(true);
			j.setMsg("成功！");
		}
		return j;
	}
	
	/**
	 * 我的首页
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@ResponseBody
	@RequestMapping("/user_index")
	public Json userIndex(HttpServletRequest request) {
		Json j = new Json();
		String userId = request.getParameter("userId");
		SessionInfo s = getSessionInfo(request);
		if(F.empty(userId)){
			userId = s.getId();
		}
		Map map = userService.userIndex(userId);
		if(s!=null){
			if(userAttentionService.get(s.getId(), userId)!=null){
				map.put("attred", Constants.GLOBAL_BOOLEAN_TRUE);
			}else{
				map.put("attred", Constants.GLOBAL_BOOLEAN_FALSE);
			}
		}else{
			map.put("attred", Constants.GLOBAL_NOT_LOGIN);
		}
		if(map == null){
			j.setSuccess(false);
			j.setMsg("不存在该用户");
		}else{
			j.setSuccess(true);
			j.setObj(map);
		}
		return j;
	}
	
	/**
	 * 用户信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/user_info")
	public Json userInfo(HttpServletRequest request) {
		Json j = new Json();
		SessionInfo s = getSessionInfo(request);
		User user = userService.get(s.getId());
		if(user == null){
			j.setSuccess(false);
			j.setMsg("不存在该用户");
		}else{
			user.setPwd(null);
			j.setSuccess(true);
			j.setObj(user);
		}
		return j;
	}
	
	/**
	 * 添加Bshoot
	 * 
	 * @return
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public Json uploadBshoot(Bshoot bshoot,@RequestParam MultipartFile[] movies,@RequestParam MultipartFile[] icons, HttpServletRequest request) {
		Json j = new Json();		
		SessionInfo s = getSessionInfo(request);
		String realPath = request.getSession().getServletContext().getRealPath("/"+Constants.UPLOADFILE+"/"+s.getName());  
		File file = new File(realPath);
		bshoot.setId(UUID.randomUUID().toString());
		bshoot.setUserId(s.getId());
		if(!file.exists())
			file.mkdir();
		
		for(MultipartFile f : movies){
			String suffix = f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf("."));
			String fileName = bshoot.getId()+suffix;
			bshoot.setBsStream(s.getName()+"/"+fileName);
			 try {
				FileUtils.copyInputStreamToFile(f.getInputStream(), new File(realPath, fileName));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		for(MultipartFile f : icons){
			String suffix = f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf("."));
			String fileName = bshoot.getId()+suffix;
			bshoot.setBsIcon(s.getName()+"/"+fileName);
			 try {
				FileUtils.copyInputStreamToFile(f.getInputStream(), new File(realPath, fileName));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		bshootService.add(bshoot);	
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}
	
	/**
	 * 我的美拍
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/user_mybshoots")
	@ResponseBody
	public DataGrid dataGridMyBs(Bshoot bshoot, PageHelper ph,HttpServletRequest request) {
		if(F.empty(bshoot.getUserId())){
			SessionInfo s = getSessionInfo(request);
			bshoot.setUserId(s.getId());
		}
		DataGrid dg = bshootService.dataGrid(bshoot, ph,1);
		
		return dg;
	}
	

	/**
	 * 我的转发
	 * @param bshoot
	 * @param ph
	 * @param request
	 * @return
	 */
	@RequestMapping("/user_mytranspond")
	@ResponseBody
	public DataGrid dataGridMytranspond(Bshoot bshoot, PageHelper ph,HttpServletRequest request) {
		if(F.empty(bshoot.getUserId())){
			SessionInfo s = getSessionInfo(request);
			bshoot.setUserId(s.getId());
		}
		DataGrid dg = bshootService.dataGrid(bshoot, ph,2);
		
		return dg;
	}
	
	/**
	 * 我关注好友
	 * @param bshoot
	 * @param ph
	 * @param request
	 * @return
	 */
	@RequestMapping("/user_myattruser")
	@ResponseBody
	public DataGrid dataGridMyattruser(UserAttention userAttention, PageHelper ph,HttpServletRequest request) {
		if(F.empty(userAttention.getUserId())){
			SessionInfo s = getSessionInfo(request);
			userAttention.setUserId(s.getId());
		}
		DataGrid dg = userAttentionService.dataGridUser(userAttention, ph);
		return dg;
	}
	
	/**
	 * 我的粉丝
	 * @param bshoot
	 * @param ph
	 * @param request
	 * @return
	 */
	@RequestMapping("/user_myattreduser")
	@ResponseBody
	public DataGrid dataGridMyattreduser(UserAttention userAttention, PageHelper ph,HttpServletRequest request) {
		if(F.empty(userAttention.getUserId())){
			SessionInfo s = getSessionInfo(request);
			userAttention.setAttUserId(s.getId());
		}else{
			userAttention.setAttUserId(userAttention.getUserId());
		}
		userAttention.setUserId(null);
		DataGrid dg = userAttentionService.dataGridUser(userAttention, ph);
		return dg;
	}
	
	@RequestMapping("/user_bshootToSquare")
	@ResponseBody
	public Json bshootToSquare(BshootToSquare bshootToSquare, HttpServletRequest request) {
		Json j = new Json();		
		int i = bshootToSquareService.addFromUser(bshootToSquare);
		if(i==1){
			j.setSuccess(true);
			j.setMsg("添加成功！");		
		}else if(i==-1){
			j.setSuccess(false);
			j.setMsg("在审核中或已经上传到广场");	
		}else{
			j.setSuccess(false);
			j.setMsg("失败");
		}
		return j;
	}
}
