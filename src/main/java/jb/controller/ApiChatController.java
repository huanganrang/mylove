package jb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jb.pageModel.BaseData;
import jb.pageModel.Json;
import jb.pageModel.LvAccount;
import jb.service.BasedataServiceI;
import jb.service.LvAccountServiceI;
import jb.util.DateUtil;
import jb.util.NotificationMesageUtil;

import org.apache.log4j.Logger;
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
@RequestMapping("/api/apiChatController")
public class ApiChatController extends BaseController {
	private static final Logger log = Logger.getLogger(ApiChatController.class);
	
	@Autowired
	private LvAccountServiceI accountService;
	
	@Autowired
	private BasedataServiceI basedataService;
	
	/**
	 * 获取消息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMessage")
	public Json getMessage(Integer openId, String type) {
		Json j = new Json();
		try {
			Random random = new Random();
			LvAccount ga = null;
			if(openId != null) {
				ga = accountService.get(openId);
			} else {
				int pageSize = 10;
				String hql = " from TlvAccount t ";
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sex", "SX02"); // 女
				int gCount = accountService.getCount(params);
				int totalPage = gCount%pageSize == 0 ? gCount/pageSize : gCount/pageSize + 1;
				List<LvAccount> gList = accountService.findListByHql(hql + " where t.sex = :sex", params, random.nextInt(totalPage)+1, pageSize);
				ga = gList.get(random.nextInt(gList.size()));
			}
			
			List<BaseData> bd = new ArrayList<BaseData>();
			List<BaseData> mqData = basedataService.getBaseDatas("MQ");
			List<BaseData> vqData = basedataService.getBaseDatas("VQ");
			if("MQ".equals(type)) {
				if(mqData == null || mqData.size() == 0) {
					BaseData b = new BaseData();
					b.setDescription("找个合适的人真不容易。聊聊？");
					bd.add(b);
				} else {
					bd.addAll(mqData);
				}
			} else if("VQ".equals(type)) {
				bd.addAll(vqData);
			} else {
				bd.addAll(mqData);
				bd.addAll(vqData);
			}
			
			BaseData d = bd.get(random.nextInt(bd.size()));
			String message = "";
			String duration = "";
			String mtype = "";
			if("MQ".equals(d.getBasetypeCode())) {
				message = d.getDescription()
						.replaceAll("\\{year\\}", DateUtil.format(ga.getBirthday(), "yyyy"))
						.replaceAll("\\{age\\}", DateUtil.getAgeByBirthday(ga.getBirthday()) + "")
						.replaceAll("\\{height\\}", (ga.getHeight() == null ? 165 : ga.getHeight()) + "");
				mtype = "MT01";
			} else if("VQ".equals(d.getBasetypeCode())) {
				message = d.getIcon();
				duration = d.getName();
				mtype = "MT02";
			}
			
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("openId", ga.getOpenId());
			m.put("message", message);
			m.put("type", mtype);
			m.put("duration", duration);
			
			j.setObj(m);
			j.setSuccess(true);
			j.setMsg("消息获取成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 一键打招呼列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/testPush")
	public Json testPush(Integer openId, String type) {
		Json j = new Json();
		try {
			int page = 1; 
			int pageSize = 50;
			String hql = " from TlvAccount t ";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("sex", "SX02"); // 女
//			int gCount = accountDao.count("select count(*) "+hql+" where t.sex = :sex", params).intValue();
			int gCount = accountService.getCount(params);
			int totalPage = gCount%pageSize == 0 ? gCount/pageSize : gCount/pageSize + 1;
			Random random = new Random();
			
			List<BaseData> bd = new ArrayList<BaseData>();
			List<BaseData> mqData = basedataService.getBaseDatas("MQ");
			List<BaseData> vqData = basedataService.getBaseDatas("VQ");
			
			if("MQ".equals(type)) {
				if(mqData == null || mqData.size() == 0) {
					BaseData b = new BaseData();
					b.setDescription("找个合适的人真不容易。聊聊？");
					bd.add(b);
				} else {
					bd.addAll(mqData);
				}
			} else if("VQ".equals(type)) {
				bd.addAll(vqData);
			} else {
				bd.addAll(mqData);
				bd.addAll(vqData);
			}
			
			if(bd.size() > 0) {
				while(true) {
					int mCount = 0;
					params.put("sex", "SX01"); // 男
					// 查找非VIP的屌丝男
	//				List<TlvAccount> mList = accountDao.find(hql + " where (t.vipEndTime <= sysdate() or t.vipLevel is null) t.sex = :sex", params, page, pageSize);
					List<LvAccount> mList = null;
					if(openId == null) {
						mList = accountService.findListByHql(hql + " where (t.vipEndTime <= current_date() or t.vipLevel is null) and t.sex = :sex", params, page, pageSize);
					} else {
						params.put("openId", openId); // 男
						mList = accountService.findListByHql(hql + " where t.sex = :sex and t.openId = :openId", params, page, pageSize);
					}
					if(mList != null && mList.size() > 0) {
						mCount = mList.size();
						params = new HashMap<String, Object>();
						params.put("sex", "SX02"); // 女
						List<LvAccount> gList = accountService.findListByHql(hql + " where t.sex = :sex", params, random.nextInt(totalPage)+1, pageSize);
						if(gList != null && gList.size() > 0) {
							for(LvAccount tm : mList) {
								LvAccount tg = gList.get(random.nextInt(gList.size()));
								
								BaseData d = bd.get(random.nextInt(bd.size()));
								String message = "";
								String duration = "";
								String mtype = "";
								if("MQ".equals(d.getBasetypeCode())) {
									message = d.getDescription()
											.replaceAll("\\{year\\}", DateUtil.format(tg.getBirthday(), "yyyy"))
											.replaceAll("\\{age\\}", DateUtil.getAgeByBirthday(tg.getBirthday()) + "")
											.replaceAll("\\{height\\}", (tg.getHeight() == null ? 165 : tg.getHeight()) + "");
									mtype = "MT01";
								} else if("VQ".equals(d.getBasetypeCode())) {
									message = d.getIcon();
									duration = d.getName();
									mtype = "MT02";
								}
								
								NotificationMesageUtil.notifMessage(tm.getOpenId() + "", "{\"openId\":"+tg.getOpenId()+", \"message\":\""+message+"\", \"type\":\""+mtype+"\", \"duration\":\""+duration+"\"}");
								log.info("####tm.openId:" + tm.getOpenId() + "----- message:" +  "{\"openId\":"+tg.getOpenId()+", \"message\":\""+message+"\", \"type\":\""+mtype+"\", \"duration\":\""+duration+"\"}");
							}
						}
					}
					
					page ++;
					if(mCount < pageSize) break;
				}
			}
			j.setSuccess(true);
			j.setMsg("推送成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
}
