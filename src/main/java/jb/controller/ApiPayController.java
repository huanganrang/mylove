package jb.controller;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;

public class ApiPayController {

	public void pingPay() {
		Pingpp.apiKey = "sk_live_qQ79MS6Z7gydDnLLT78mGkbO";
		Map<String, Object> chargeParams = new HashMap<String, Object>();
	    chargeParams.put("order_no",  "M93846349833");
	    chargeParams.put("amount", 100);
	    Map<String, String> app = new HashMap<String, String>();
	    app.put("id", "app_9WHWH0rDC8q10qfn");
	    chargeParams.put("app",app);
	    chargeParams.put("channel","alipay");
	    chargeParams.put("currency","cny");
	    chargeParams.put("client_ip","182.92.78.254");
	    chargeParams.put("subject","钻石vip开通");
	    chargeParams.put("body","赠送一年的VIP功能");
	    Charge c;
		try {
			c = Charge.create(chargeParams);
			 System.out.println(new Gson().toJson(c));
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			e.printStackTrace();
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		} catch (ChannelException e) {
			e.printStackTrace();
		}
	   
	}
}
