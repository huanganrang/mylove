package jb.task;

import jb.absx.F;
import jb.listener.Application;
import jb.pageModel.LvAccount;
import jb.pageModel.LvBoostActivty;
import jb.pageModel.LvBoostRecord;
import jb.service.LvAccountServiceI;
import jb.service.LvBoostActivtyServiceI;
import jb.service.LvBoostRecordServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoostTask {
	private static final Logger log = Logger.getLogger(BoostTask.class);
	
	@Autowired
	private LvAccountServiceI lvAccountService;
	@Autowired
	private LvBoostActivtyServiceI boostActivtyService;
	@Autowired
	private LvBoostRecordServiceI boostRecordService;

	public void addBoost() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if(hour > 7 || hour < 1) {
			new Thread(new Runnable() {
				public void run() {
					taskHandle();
				}
			}).start();
		}
	}
	
	private void taskHandle() {
		log.info("addBoost start!");
		
		List<LvBoostActivty> boosts = boostActivtyService.findAllList(null, null);
		
		int pageSize = 50;
		String hql = " from TlvAccount t ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sex", "SX02"); // 女
		int gCount = lvAccountService.getCount(params);
		int totalPage = gCount%pageSize == 0 ? gCount/pageSize : gCount/pageSize + 1;
		if(totalPage == 0) return;
		Random random = new Random();
		List<LvAccount> gList = lvAccountService.findListByHql(hql + " where t.sex = :sex", params, random.nextInt(totalPage)+1, pageSize);
		if(gList != null && gList.size() > 0
				&& boosts != null && boosts.size() > 0) {
			int num = getAddBoostNum();
			for(int i=0; i<num; i++) {
				LvAccount tg = gList.get(random.nextInt(gList.size()));
				LvBoostActivty ba = boosts.get(random.nextInt(boosts.size()));
				LvBoostRecord record = new LvBoostRecord();
				record.setActivtyId(ba.getId());
				record.setOpenId(tg.getOpenId());
				LvBoostRecord temp = boostRecordService.get(record);
				if(temp == null) {
					try {
						Thread.sleep((random.nextInt(5) + 1) * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					boostRecordService.add(record);
				}
			}
		}
		
		log.info("addBoost end!");
	}

	private int getAddBoostNum() {
		int num = 5;
		try {
			String numStr = Application.getString("PS04");
			if(!F.empty(numStr)) {
				int t = Integer.valueOf(numStr);
				if(t > 5) {
					num = t;
				} else if(t < -5) {
					Random random = new Random();
					num += random.nextInt(-t - 5) + 1;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return num;
	}
}
