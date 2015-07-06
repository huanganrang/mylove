package jb.util;

import java.util.Random;

public class StringUtil {
	
	/**
	 * 获取指定长度的随机数
	 * @param length
	 * @return
	 */
	public static String getRandomNumber(int length) {
		Random random = new Random();
		String result="";
		for(int i=0;i<6;i++){
			result+=random.nextInt(10);
		}
		return result;
	}
}
