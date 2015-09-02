package jb.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jb.listener.Application;
import jb.pageModel.LvAccount;
import jb.pageModel.LvFollow;
import jb.pageModel.LvVisit;
import jb.service.LvAccountServiceI;
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
	
	public static SimulationTask getInstance() {
        if (instance == null) {
            synchronized (SimulationTask.class) {
                instance = new SimulationTask();
            }
        }
        return instance;
    }
	
	public SimulationTask() {
        if (instance != null) {
            throw new RuntimeException("A task is already running");
        }
        instance = this;
        ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(Application.context); 
        lvAccountService = app.getBean(LvAccountServiceI.class);
        lvVisitService = app.getBean(LvVisitServiceI.class);
        followService = app.getBean(LvFollowServiceI.class);
        init();
    }
	
	private void init() {
		executors.execute(new Runnable() {
			public void run() {
				while(true) {
					try {
						// 模拟来访列表
						visitOrFollowHandle(1);
						Thread.sleep(10*60*1000);
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
						// 模拟关注列表
						visitOrFollowHandle(2);
						Thread.sleep(10*60*1000);
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
						// 模拟挖宝
						//boostHandle();
						Thread.sleep(10*60*1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
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
						
						String type = "";
						String message = "";
						boolean sendFlag = false;
						if(t == 1) {
							// 插入来访纪录
							LvVisit lvVisit = new LvVisit();
							lvVisit.setOpenId(tm.getOpenId());
							lvVisit.setVisitOpenId(tg.getOpenId());
							lvVisitService.saveOrUpdate(lvVisit);
							type = "MT03";
							message = "["+tg.getNickName()+"]刚刚查看了您的资料";
							sendFlag = true;
						} else if(t == 2) {
							LvFollow f = new LvFollow();
							f.setToOpenId(tm.getOpenId());
							f.setFromOpenId(tg.getOpenId());
							f.setCreateTime(new Date());
							int r = followService.add(f);
							type = "MT04";
							message = "["+tg.getNickName()+"]刚刚关注了你";
							if(r == 1) {
								sendFlag = true;
							}
						}
						
						if(sendFlag) {
							NotificationMesageUtil.notifMessage(tm.getOpenId() + "", "{\"openId\":"+tg.getOpenId()+", \"message\":\""+message+"\", \"type\":\""+type+"\"}");
							log.info("####tm.openId:" + tm.getOpenId() + "----- message:" +  "{\"openId\":"+tg.getOpenId()+", \"message\":\""+message+"\", \"type\":\""+type+"\"}");
						}
						
					}
				}
			}
			
			page ++;
			if(mCount < pageSize) break;
		}
	}
}
