package jb.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jb.listener.Application;
import jb.pageModel.BaseData;
import jb.pageModel.LvAccount;
import jb.service.BasedataServiceI;
import jb.service.LvAccountServiceI;
import jb.util.DateUtil;
import jb.util.NotificationMesageUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushMessageTask {
	private static final Logger log = Logger.getLogger(PushMessageTask.class);
	
	@Autowired
	private LvAccountServiceI lvAccountService;
	
	@Autowired
	private BasedataServiceI basedataService;

	public void pushMessage() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		// 
		if((hour >= 11 && hour <= 15) || (hour >= 18 && hour <= 23)) {
			new Thread(new Runnable() {
				public void run() {
					taskHandle();
				}
			}).start();
		}
	}
	
	private void taskHandle() {
		log.info("pushMessage start!");
		int page = 1; 
		int pageSize = 50;
		String hql = " from TlvAccount t ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sex", "SX02"); // 女
//		int gCount = accountDao.count("select count(*) "+hql+" where t.sex = :sex", params).intValue();
		int gCount = lvAccountService.getCount(params);
		int totalPage = gCount%pageSize == 0 ? gCount/pageSize : gCount/pageSize + 1;
		Random random = new Random();
		
		
		String mt = Application.getString("SV300");
		List<BaseData> bd = new ArrayList<BaseData>();
		List<BaseData> mqData = basedataService.getBaseDatas("MQ");
		List<BaseData> vqData = basedataService.getBaseDatas("VQ");
		
		if("MQ".equals(mt.trim())) {
			if(mqData == null || mqData.size() == 0) {
				createDefault(bd);
			} else {
				bd.addAll(mqData);
			}
		} else if("VQ".equals(mt.trim())) {
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
	//			List<TlvAccount> mList = accountDao.find(hql + " where (t.vipEndTime <= sysdate() or t.vipLevel is null) t.sex = :sex", params, page, pageSize);
				List<LvAccount> mList = lvAccountService.findListByHql(hql + " where t.online = 'OL01' and (t.vipEndTime <= current_date() or t.vipLevel is null) and t.sex = :sex", params, page, pageSize);
				if(mList != null && mList.size() > 0) {
					mCount = mList.size();
					params.put("sex", "SX02"); // 女
					List<LvAccount> gList = lvAccountService.findListByHql(hql + " where t.sex = :sex", params, random.nextInt(totalPage)+1, pageSize);
					if(gList != null && gList.size() > 0) {
						for(LvAccount tm : mList) {
							LvAccount tg = gList.get(random.nextInt(gList.size()));
							BaseData d = bd.get(random.nextInt(bd.size()));
							String message = "";
							String duration = "";
							String type = "";
							if("MQ".equals(d.getBasetypeCode())) {
								message = d.getDescription()
										.replaceAll("\\{year\\}", DateUtil.format(tg.getBirthday(), "yyyy"))
										.replaceAll("\\{age\\}", DateUtil.getAgeByBirthday(tg.getBirthday()) + "")
										.replaceAll("\\{height\\}", (tg.getHeight() == null ? 165 : tg.getHeight()) + "");
								type = "MT01";
							} else if("VQ".equals(d.getBasetypeCode())) {
								message = d.getIcon();
								duration = d.getName();
								type = "MT02";
							}
							
							NotificationMesageUtil.notifMessage(tm.getOpenId() + "", "{\"openId\":"+tg.getOpenId()+", \"message\":\""+message+"\", \"type\":\""+type+"\", \"duration\":\""+duration+"\"}");
							log.info("####tm.openId:" + tm.getOpenId() + "----- message:" +  "{\"openId\":"+tg.getOpenId()+", \"message\":\""+message+"\", \"type\":\""+type+"\", \"duration\":\""+duration+"\"}");
						}
					}
				}
				
				page ++;
				if(mCount < pageSize) break;
			}
		}
		
		log.info("pushMessage end!");
	}

	private void createDefault(List<BaseData> bd) {
		BaseData b = new BaseData();
		b.setDescription("找个合适的人真不容易。聊聊？");
		bd.add(b);
	}
	
}
