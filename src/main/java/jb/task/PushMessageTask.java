package jb.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
		new Thread(new Runnable() {
			public void run() {
				taskHandle();
			}
		}).start();
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
		
		List<BaseData> bd = basedataService.getBaseDatas("MQ");
		if(bd == null || bd.size() == 0) {
			bd = new ArrayList<BaseData>();
			createDefault(bd);
		}
		while(true) {
			int mCount = 0;
			params.put("sex", "SX01"); // 男
			// 查找非VIP的屌丝男
//			List<TlvAccount> mList = accountDao.find(hql + " where (t.vipEndTime <= sysdate() or t.vipLevel is null) t.sex = :sex", params, page, pageSize);
			List<LvAccount> mList = lvAccountService.findListByHql(hql + " where (t.vipEndTime <= current_date() or t.vipLevel is null) and t.sex = :sex", params, page, pageSize);
			if(mList != null && mList.size() > 0) {
				mCount = mList.size();
				params.put("sex", "SX02"); // 女
				List<LvAccount> gList = lvAccountService.findListByHql(hql + " where t.sex = :sex", params, random.nextInt(totalPage)+1, pageSize);
				if(gList != null && gList.size() > 0) {
					for(LvAccount tm : mList) {
						LvAccount tg = gList.get(random.nextInt(gList.size()));
						
						String message = bd.get(random.nextInt(bd.size())).getDescription()
								.replaceAll("\\{year\\}", DateUtil.format(tg.getBirthday(), "yyyy"))
								.replaceAll("\\{age\\}", DateUtil.getAgeByBirthday(tg.getBirthday()) + "")
								.replaceAll("\\{height\\}", (tg.getHeight() == null ? 165 : tg.getHeight()) + "");
						NotificationMesageUtil.notifMessage(tm.getOpenId() + "", "{\"openId\":"+tg.getOpenId()+", \"message\":\""+message+"\", \"type\":\"MT01\"}");
						log.info("####tm.openId:" + tm.getOpenId() + "----- message:" +  "{\"openId\":"+tg.getOpenId()+", \"message\":\""+message+"\", \"type\":\"MT01\"}");
					}
				}
			}
			
			page ++;
			if(mCount < pageSize) break;
		}
		
		log.info("pushMessage end!");
	}

	private void createDefault(List<BaseData> bd) {
		BaseData b = new BaseData();
		b.setDescription("找个合适的人真不容易。聊聊？");
		bd.add(b);
	}
	
}
