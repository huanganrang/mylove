package jb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

public final class DateUtil {
	
	private static final Logger log = Logger.getLogger(DateUtil.class);
	
	public static final String YMD_A = "yyyy-MM-dd";
	public static final String YMD_B = "yyyy/MM/dd";
	public static final String YMD_C = "yyyy.MM.dd";
	public static final String YMDHMS_A = "yyyy-MM-dd HH:mm:ss";
	public static final String YMDHMS_B = "yyyy/MM/dd HH:mm:ss";
	public static final String YMDHMS_C = "yyyy.MM.dd HH:mm:ss";
	public static final String YMDHMSS_A = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String YMDHMSS_B = "yyyy/MM/dd HH:mm:ss.SSS";
	public static final String YMDHMSS_C = "yyyy.MM.dd HH:mm:ss.SSS";

	public static final String FMT_ZH_DAY = "yyyy-MM-dd";
	public static final String FMT_ZH_HOUR = "yyyy-MM-dd HH";
	public static final String FMT_ZH_MIN = "yyyy-MM-dd HH:mm";
	public static final String FMT_ZH_SEC = "yyyy-MM-dd HH:mm:ss";
	public static final String FMT_ZH_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

	public static Date parse(String str) {
		String date_str = str.trim();
		Date date = null;
		if (date_str.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$"))
			date = parse(date_str, "yyyy-MM-dd");
		else if (date_str.matches("^\\d{4}/\\d{1,2}/\\d{1,2}$"))
			date = parse(date_str, "yyyy/MM/dd");
		else if (date_str
				.matches("^\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}$"))
			date = parse(date_str, "yyyy-MM-dd HH:mm:ss");
		else if (date_str
				.matches("^\\d{4}/\\d{1,2}/\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}$"))
			date = parse(date_str, "yyyy/MM/dd HH:mm:ss");
		else if (date_str
				.matches("^\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}$"))
			date = parse(date_str, "yyyy-MM-dd HH:mm:ss.SSS");
		else if (date_str
				.matches("^\\d{4}/\\d{1,2}/\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}$")) {
			date = parse(date_str, "yyyy/MM/dd HH:mm:ss.SSS");
		}
		return date;
	}
	

	/**
	 * 将日期字符串转化成日期类型
	 * 
	 * @param date
	 * @param fmt
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String date, String fmt) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			Date newdate = sdf.parse(date);
			return newdate;
		} catch (ParseException e) {
			log.error("Util>>DateUtil.parse>>" + date + ">>" + fmt+"-->>" +e.getMessage());
			return null;
		}
	}	

	public static String format(Date date, String fmt) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		return sdf.format(date);
	}

	public static Date start(Date time) {
		if (time != null) {
			return parse(format(time, "yyyy-MM-dd"), "yyyy-MM-dd");
		}
		return null;
	}

	public static Date end(Date time) {
		if (time != null) {
			return new Date(start(new Date(time.getTime() + 86400000L))
					.getTime() - 1000L);
		}
		return null;
	}

	public static Date day(Date time, Integer offset) {
		Date date = new Date(time.getTime() + offset.intValue() * 86400000L);
		return date;
	}

	public static Integer week(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return Integer.valueOf(calendar.get(7) - 1);
	}

	public static Integer days(Date start, Date end) {
		Date date_start = start(start);
		Date date_end = start(end);
		return Integer.valueOf((int) ((date_end.getTime() - date_start
				.getTime()) / 86400000L));
	}
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isToday(Date date) {
		Date today = new Date();
		Date start = start(today);
		Date end = end(today);

		return (date.getTime() >= start.getTime())
				&& (date.getTime() < end.getTime());
	}
	
	/**
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTimeToString(Long time){
		String dateString = "00:00:00";
		if(time!=null &&  time > 0){
			Long hours = time/60/60/1000;
			time = time - hours*60*60*1000;
			Long mis = time/60/1000; 
			time = time - mis*60*1000;
			Long seconds = time%1000==0?time/1000:time/1000+1;
			if(seconds == 60L){
				mis = mis+1L;
				seconds=0L;
			}
			if(mis == 60L){
				hours = hours+1L;
				mis = 0L;
			}
			dateString = getId(hours.toString())+":"+ getId(mis.toString())+":"+ getId(seconds.toString());	
		}
		return dateString; 
	}
	
	
	/**
	 * 相除(进位).
	 * @param times
	 * @param costMode
	 * @return
	 */
	public static Long formatTimesToString(Long times,Long costMode){
		Long seconds = 0L;
		if(times > 0 && costMode > 0){
			seconds = times%costMode==0?times/costMode:times/costMode+1;
		}else{
			seconds = times;
		}
		return seconds; 
	}
	
	
	/**
	 * 取时间的秒数 (进位).
	 * @param time
	 * @return
	 */
	public static Long formatSecondsTimeToString(Long time){
		Long seconds = 0L;
		if(time > 0){
			seconds = time%1000==0?time/1000:time/1000+1;
		}
		return seconds; 
	}
	
	public static String getId(String id) {
		String buID = "";
		if (id.length() == 1) {
			buID = "0" + id;
		}else{
			buID = id;
		}
		return buID;
	}
	
	/**
	 *  返回 该日期的开始处
	 * @param date
	 * @param hours
	 * @param Mm
	 * @return
	 */
	public static Date getDayStartMm(Date date,int hours,int Mm){
		if(date==null)
			return null;
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		
		if(hours != 0){
			int hour = Calendar.HOUR_OF_DAY + hours+1;
			c.set(Calendar.HOUR_OF_DAY,hour);
		} 
		if(Mm !=  0){
			c.add(Calendar.MINUTE, Mm);  
			int MINUTE = c.get(Calendar.MINUTE);    
			c.set(Calendar.MINUTE,MINUTE);
		} 
		return c.getTime();
	}
	
	/**
	 * 返回 该日期的开始处
	 * @param date
	 * @return
	 */
	public static Date getDayStart(Date date,int hours){
		if(date==null)
			return null;
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		
		if(hours > 0){
			c.set(Calendar.HOUR_OF_DAY, hours);
		}else{
			c.set(Calendar.HOUR_OF_DAY, 0);
		}
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 返回 该日期的开始处
	 * @param date
	 * @return
	 */
	public static Date getDayStart(Date date){
		if(date==null)
			return null;
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	/**
	 * 返回 该日期的结束处
	 * @param date
	 * @return
	 */
	public static Date getDayEnd(Date date){
		if(date==null)
			return null;
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);		
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}
	
	

	
	/**
	 * 给Date加s秒
	 * @param date
	 * @param s
	 * @return
	 */
	public static Date addSecondToDate(Date date, int s) {  
	    Calendar calendar = Calendar.getInstance();  
	    calendar.setTime(date);  
	    calendar.add(Calendar.SECOND, s);  
	    return calendar.getTime();  
	}
	
	/**
	 * 给Date加m分钟
	 * @param date
	 * @param m
	 * @return
	 */
	public static Date addMinuteToDate(Date date, int m) {  
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);  
		calendar.add(Calendar.MINUTE, m);  
		return calendar.getTime();  
	}
	
	/**'
	 * 给Date加h小时
	 * @param date
	 * @param m
	 * @return
	 */
	public static Date addHourToDate(Date date, int h) {  
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);  
		calendar.add(Calendar.HOUR, h);  
		return calendar.getTime();  
	}
	
	/**
	 * 给Date加d天
	 * @param date
	 * @param d
	 * @return
	 */
	public static Date addDayToDate(Date date, int d) {  
		return DateUtils.addDays(date, d);  
	}
	
	/**
	 * 给Date加m月
	 * @param date
	 * @param m
	 * @return
	 */
	public static Date addMonthToDate(Date date, int m) {  
		return DateUtils.addMonths(date, m);  
	}
	
	/**
	 * 给Date加y年
	 * @param date
	 * @param y
	 * @return
	 */
	public static Date addYearToDate(Date date, int y) {  
		return DateUtils.addYears(date, y);  
	}
	
	/**
	 * 给Date加w周
	 * @param date
	 * @param w
	 * @return
	 */
	public static Date addWeekToDate(Date date, int w) {  
		return DateUtils.addWeeks(date, w);  
	}	
	
	
	/**
	 * 获取时间轴时间集合
	 * @param format
	 * @param i
	 * @return
	 */
	public static List<String> getTimeAxisList(Date startDate, int dateDiff) {
		List<String> timeAxisList = new ArrayList<String>();
		Calendar calDate = Calendar.getInstance();
		calDate.setTime(startDate);
		timeAxisList.add(DateUtil.format(startDate, DateUtil.YMD_A));
		for(int i=0; i<dateDiff; i++) {
			calDate.add(Calendar.DAY_OF_MONTH, 1);
			if(!timeAxisList.contains(DateUtil.format(calDate.getTime(), DateUtil.YMD_A))) {
				timeAxisList.add(DateUtil.format(calDate.getTime(), DateUtil.YMD_A));
			}
		}
		return timeAxisList;
	}
	
	
	public static Date getAppointDate(){
		Calendar calendar = Calendar.getInstance();  
		calendar.set(2019, 11, 31);
		return calendar.getTime();  
		
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String startTime = "2014-09-04";
		//System.out.println(DateUtil.parse(startTime,"yyyy-MM-dd"));
//		System.out.println(9223372036854775807L);
		//System.out.println(formatTimeToString(59200L));
		//System.out.println(formatCdrTimeToString(69200L));
		System.out.println(DateUtil.format(DateUtil.getDayEnd(getAppointDate()), DateUtil.YMDHMS_A));
	}

	
	
	
	
}