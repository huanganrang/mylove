package jb.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jb.absx.F;
import jb.listener.Application;
import jb.pageModel.LvAccount;
import jb.pageModel.LvBoostActivty;
import jb.pageModel.LvBoostRecord;
import jb.pageModel.LvFollow;
import jb.pageModel.LvVisit;
import jb.service.LvAccountServiceI;
import jb.service.LvBoostActivtyServiceI;
import jb.service.LvBoostRecordServiceI;
import jb.service.LvFollowServiceI;
import jb.service.LvVisitServiceI;
import jb.util.NotificationMesageUtil;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SimulationTask {
	
	private static final Logger log = Logger.getLogger(SimulationTask.class);
	public final static ExecutorService executors = Executors.newFixedThreadPool(3);
	
	private static SimulationTask instance;
	private LvAccountServiceI lvAccountService;
	private LvVisitServiceI lvVisitService;
	private LvFollowServiceI followService;
	private LvBoostActivtyServiceI boostActivtyService;
	private LvBoostRecordServiceI boostRecordService;
	
	private final static String VISIT_TIMER_PARAM = "PS01";
	private final static String FOLLOW_TIMER_PARAM = "PS02";
	private final static String BOOST_TIMER_PARAM = "PS03";
	
	public static SimulationTask getInstance() {
        if (instance == null) {
            synchronized (SimulationTask.class) {
                instance = new SimulationTask();
            }
        }
        return instance;
    }
	
	private SimulationTask() {
        if (instance != null) {
            throw new RuntimeException("A task is already running");
        }
        instance = this;
        ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(Application.context); 
        lvAccountService = app.getBean(LvAccountServiceI.class);
        lvVisitService = app.getBean(LvVisitServiceI.class);
        followService = app.getBean(LvFollowServiceI.class);
        boostActivtyService = app.getBean(LvBoostActivtyServiceI.class);
        boostRecordService = app.getBean(LvBoostRecordServiceI.class);
        init();
    }
	
	private void init() {
		executors.execute(new Runnable() {
			public void run() {
				while(true) {
					try {
						int time = getTime(VISIT_TIMER_PARAM, 3);
						// 模拟来访列表
						visitOrFollowHandle(1);
						Thread.sleep(time*60*1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		executors.execute(new Runnable() {
			public void run() {
				while(true) {
					try {
						int time = getTime(FOLLOW_TIMER_PARAM, 3);
						// 模拟关注列表
						visitOrFollowHandle(2);
						Thread.sleep(time*60*1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		executors.execute(new Runnable() {
			public void run() {
				while(true) {
					try {
						int time = getTime(BOOST_TIMER_PARAM, 10);
						// 模拟挖宝
						boostHandle();
						Thread.sleep(time*60*1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		});
	}
	
	private int getTime(String type, int defaultTime) {
		int time = defaultTime;
		try {
			String timeStr = Application.getString(type);
			if(!F.empty(timeStr)) {
				int t = Integer.valueOf(timeStr);
				if(t > 0) {
					time = t;
				} else if(t < 0) {
					Random random = new Random();
					time = random.nextInt(-t) + 1;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return time;
	}

	private void visitOrFollowHandle(int t) {
		int page = 1; 
		int pageSize = 50;
		String hql = " from TlvAccount t ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sex", "SX02"); // 女
		int gCount = lvAccountService.getCount(params);
		int totalPage = gCount%pageSize == 0 ? gCount/pageSize : gCount/pageSize + 1;
		Random random = new Random();
		
		while(true) {
			int mCount = 0;
			params.put("sex", "SX01"); // 男
			// 查找在线的非VIP的屌丝男
			List<LvAccount> mList = lvAccountService.findListByHql(hql + " where t.online = 'OL01' and (t.vipEndTime <= current_date() or t.vipLevel is null) and t.sex = :sex", params, page, pageSize);
			if(mList != null && mList.size() > 0) {
				mCount = mList.size();
				params.put("sex", "SX02"); // 女
				List<LvAccount> gList = lvAccountService.findListByHql(hql + " where t.sex = :sex", params, random.nextInt(totalPage)+1, pageSize);
				if(gList != null && gList.size() > 0) {
					for(LvAccount tm : mList) {
						LvAccount tg = gList.get(random.nextInt(gList.size()));
						
						String type = "";
						String message = "";
						if(t == 1) {
							// 插入来访纪录
							LvVisit lvVisit = new LvVisit();
							lvVisit.setOpenId(tm.getOpenId());
							lvVisit.setVisitOpenId(tg.getOpenId());
							lvVisitService.saveOrUpdate(lvVisit);
							type = "MT03";
							message = "["+tg.getNickName()+"]刚刚查看了您的资料";
						} else if(t == 2) {
							LvFollow f = new LvFollow();
							f.setToOpenId(tm.getOpenId());
							f.setFromOpenId(tg.getOpenId());
							f.setCreateTime(new Date());
							followService.add(f);
							type = "MT04";
							message = "["+tg.getNickName()+"]刚刚关注了你";
						}
						
						NotificationMesageUtil.notifMessage(tm.getOpenId() + "", "{\"openId\":"+tg.getOpenId()+", \"message\":\""+message+"\", \"type\":\""+type+"\"}");
						log.info("####tm.openId:" + tm.getOpenId() + "----- message:" +  "{\"openId\":"+tg.getOpenId()+", \"message\":\""+message+"\", \"type\":\""+type+"\"}");
						
					}
				}
			}
			
			page ++;
			if(mCount < pageSize) break;
		}
	}
	
	private void boostHandle() {
		List<LvBoostActivty> boosts = boostActivtyService.findAllList(null, null);
		
		int pageSize = 50;
		String hql = " from TlvAccount t ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sex", "SX02"); // 女
		int gCount = lvAccountService.getCount(params);
		int totalPage = gCount%pageSize == 0 ? gCount/pageSize : gCount/pageSize + 1;
		Random random = new Random();
		List<LvAccount> gList = lvAccountService.findListByHql(hql + " where t.sex = :sex", params, random.nextInt(totalPage)+1, pageSize);
		if(gList != null && gList.size() > 0
				&& boosts != null && boosts.size() > 0) {
			LvAccount tg = gList.get(random.nextInt(gList.size()));
			LvBoostActivty ba = boosts.get(random.nextInt(boosts.size()));
			while(true) {
				LvBoostRecord record = new LvBoostRecord();
				record.setActivtyId(ba.getId());
				record.setOpenId(tg.getOpenId());
				LvBoostRecord temp = boostRecordService.get(record);
				if(temp != null) {
					tg = gList.get(random.nextInt(gList.size()));
				} else {
					boostRecordService.add(record);
					break;
				}
			}
		}
		
	}
}
