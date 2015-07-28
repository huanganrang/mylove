package jb.util;

import jb.android.push.NotificationManager;

import org.androidpn.server.xmpp.XmppServer;

public class NotificationMesageUtil {
	public static boolean notifMessage(String openId,String message){
		try{
			NotificationManager notif = (NotificationManager) XmppServer
					.getInstance().getBean("notificationManager");
			notif.sendNotifcationToUser("1234567890", openId, "title", message,
					"uri");
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
