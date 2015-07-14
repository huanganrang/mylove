package jb.util.easemob;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import jb.listener.TokenListener;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class HuanxinUtil {
	
	private static final Logger log = Logger.getLogger(HuanxinUtil.class);
	
	public static final String APPKEY = "liantangwuyou#lovepeng";
	public static final String CLIENT_ID = "YXA6WZ59wCiXEeWmHqmc9AagDA";
	public static final String CLIENT_SECRET = "YXA6Z7bJfm1OyfuDY1guU_aejIXcqFQ";
	
//	public static final String APPKEY = "xuwenming-1987#mylove";
//	public static final String CLIENT_ID = "YXA6EY3BkCn0EeW2L8HnJ8THWg";
//	public static final String CLIENT_SECRET = "YXA6A3FIOChWKlYr06y76HoXT5YkPp4";
	
	/**
     * 忽视证书HostName
     */
    private static HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
        public boolean verify(String s, SSLSession sslsession) {
            System.out.println("WARNING: Hostname is not matched for cert.");
            return true;
        }
    };
    
	/**
	 * 发起http请求获取返回结果
	 * 
	 * @param requestUrl 请求地址
	 * @return
	 */
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);

			httpUrlConn.setRequestMethod(requestMethod.toUpperCase());
			
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();

		} catch (Exception e) {
		}
		return buffer.toString();
	}
	
	/**
	 * 发送https请求
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return 返回环信服务器响应的信息
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr, boolean oauth) {
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod.toUpperCase());
			if(oauth) {
				httpUrlConn.setRequestProperty("Content-Type", "application/json");
				httpUrlConn.setRequestProperty("Authorization", "Bearer " + TokenListener.accessToken.getToken());
			}

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return buffer.toString();
	}
	
	/**
	 * 获取access_token
	 * @return
	 */
	public static AccessToken getAccessToken() {
		AccessToken accessToken = null;

		String[] keys = APPKEY.split("#");
		String requestUrl = "https://a1.easemob.com/"+keys[0]+"/"+keys[1]+"/token";
		String params = "{\"grant_type\": \"client_credentials\",\"client_id\": \""+CLIENT_ID+"\",\"client_secret\": \""+CLIENT_SECRET+"\"}";
		JSONObject jsonObject = JSONObject.parseObject(httpsRequest(requestUrl, "POST", params, false));
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getIntValue("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败", e);
			}
		}
		return accessToken;
	}
	
	/**
	 * 环信注册用户
	 * @return
	 */
	public static String createUser(String username, String password) {
		
		String[] keys = APPKEY.split("#");
		String requestUrl = "https://a1.easemob.com/"+keys[0]+"/"+keys[1]+"/users";
		String params = "{\"username\": \""+username+"\",\"password\": \""+password+"\"}";
		String response = httpsRequest(requestUrl, "POST", params, true);
		log.info(response);
		return response;
	}
	
	/**
	 * 环信重置用户密码
	 * @return
	 */
	public static String resetPass(String username, String password) {
		
		String[] keys = APPKEY.split("#");
		String requestUrl = "https://a1.easemob.com/"+keys[0]+"/"+keys[1]+"/"+username+"/password";
		String params = "{\"newpassword\": \""+password+"\"}";
		String response = httpsRequest(requestUrl, "PUT", params, true);
		log.info(response);
		return response;
	}
}
