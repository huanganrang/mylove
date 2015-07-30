package jb.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jb.listener.Application;
import jb.pageModel.Json;
import jb.pageModel.LvOrder;
import jb.service.LvOrderServiceI;
import jb.util.DateUtil;
import jb.util.IpUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;

@Controller
@RequestMapping("/api/apiPayController")
public class ApiPayController {
	
	@Autowired
	private LvOrderServiceI orderService;
	
	@ResponseBody
	@RequestMapping("/pay")
	public Json pay(LvOrder order, HttpServletRequest request) {
		Json j = new Json();
		try {
			String appId = order.getAppId();
			Date now = new Date();
			order.setCreatetime(now);
			order.setOrderStatus("OS02");
			orderService.add(order);
			
			order = orderService.get(order.getId());
			
			Pingpp.apiKey = Application.getString("SV200");
			Map<String, Object> chargeParams = new HashMap<String, Object>();
		    chargeParams.put("order_no",  DateUtil.format(now, "yyyyMMdd") + order.getOrderNo());
		    chargeParams.put("amount", (long)(order.getAmount()*100));
		    Map<String, String> app = new HashMap<String, String>();
		    app.put("id", appId); // app_9WHWH0rDC8q10qfn
		    chargeParams.put("app",app);
		    chargeParams.put("channel", order.getChannel());
		    chargeParams.put("currency", "cny");
		    chargeParams.put("client_ip", IpUtil.getIp(request));
		    chargeParams.put("subject", "钻石vip开通");
		    String body = "";
		    if("VP01".equals(order.getVipLevel())) {
		    	body = "钻石VIP年付，提供一年的VIP特权服务";
		    } else if("VP02".equals(order.getVipLevel())) {
		    	body = "钻石VIP季付，提供一个季度的VIP特权服务";
		    }
		    chargeParams.put("body", body);
		    Charge c = Charge.create(chargeParams);
		    
		    j.setObj(c);
		    j.success();
		    j.setMsg("发起支付成功");
		} catch (Exception e) {
			e.printStackTrace();
			j.fail();
			j.setMsg(e.getMessage());
		} 
	   
		return j;
	}
	
}
