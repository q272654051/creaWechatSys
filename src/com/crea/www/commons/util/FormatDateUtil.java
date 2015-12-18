package com.crea.www.commons.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * <p style="color:blue">
 * 获取指定时间格式
 * </p>
 * 
 * @author moon
 * 
 */
public class FormatDateUtil {

	/**
	 * 获取指定的时间格式:yyyy-mm-dd
	 * 
	 * @return
	 */
	public static String getDateFormatYMD() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return format.format(date);

	}

	/**
	 * 获取指定的时间格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getDateFormatYMDHMS() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return format.format(date);

	}

	/**
	 * 时间戳:yyyyMMdd
	 * 
	 * @return
	 */
	public static String getTimestamp() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		return format.format(date);

	}

	/**
	 * 时间戳:yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String getTimestampFull() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return format.format(date);

	}

	/**
	 * 自定义样式时间
	 * 
	 * @return
	 */
	public static String getUserDateFormat(String style) {
		SimpleDateFormat format = new SimpleDateFormat(style);
		Date date = new Date();
		return format.format(date);

	}

	/**
	 * 中国样式的时间
	 * 
	 * @return
	 */
	public static String getChinesStyleTime() {
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG,
				Locale.CHINA);// GERMANY
		return df.format(new Date());
	}

	/**
	 * 获取多少个月以前的时间
	 * 
	 * @return
	 */
	public static String getProphaseMouthTime(int month) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化对象
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -month); // 得到前N个月
		return sdf.format(calendar.getTime());
	}

	/**
	 * 获取当前月的第一天
	 */
	public static String getfirstDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 获取当前月第一天：
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String first = format.format(c.getTime());
		return first;
	}

	/**
	 * 获取当前月的最后一天
	 */
	public static String getlastDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 获取当前月最后一天
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH,
				ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = format.format(ca.getTime());
		return last;
	}

	/**
	 * 获取当年的第一天
	 * 
	 * @param year
	 * @return
	 */
	public static int getCurrYearFirst() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		int FirstYear = ca.get(Calendar.YEAR);
		return FirstYear;
	}

	/**
	 * 获取当年的最后一天
	 * 
	 * @param year
	 * @return
	 */
	public static int getCurrYearLast() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar currCal = Calendar.getInstance();
		int LastYear = currCal.get(Calendar.YEAR);
		return LastYear;
	}

	/**
	 * @param String
	 *            转yyyy-MM-dd格式的时间.
	 * @return
	 */
	public static Date dateFormatString(String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * @param String
	 *            转yyyy-MM-dd HH:mm:ss格式的时间.
	 * @return
	 */
	public static Date date2FormatString(String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(dateStr);
			System.out.println("date=====:" + date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	// ------------------------------------------------------

	/**
	 * 日期转换成短字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToShortStr(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(date);
		return str;
	}

	/**
	 * 日期转换成长字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToLongStr(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(date);
		return str;
	}

	/**
	 * 字符串转换成短日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToShortDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 字符串转换成长日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToLongDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return format.parse(format.format(date));

	}
	
	/**
	 * 
	 * @return 获取周   日期 (周一至周日)
	 */
	public static List getWeek(){
	    List<Date> date_list = new ArrayList<Date>();
	    Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK)-2;
        for(int i = day; i > 0; i--){
            Calendar c1 = Calendar.getInstance();
            c1.set(Calendar.DATE,c1.get(Calendar.DATE)-i); 
            date_list.add(c1.getTime());
        }
        Calendar c2 = Calendar.getInstance();
        int day1 = c2.get(Calendar.DAY_OF_WEEK)-1;
        date_list.add(c.getTime());
        for(int i = 7-(day1); i > 0; i--){
            c2.set(Calendar.DATE,c2.get(Calendar.DATE)+1); 
            date_list.add(c2.getTime());
        }
        return date_list;
	}
	
	/**
	 * 
	 * @return 获取当年月份
	 */
	public static List getMonth(){
	    List<String> date_list = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        String year = c.get(Calendar.YEAR)+"";
        for(int i = 1; i < 13; i++){
            if(i<10){
                date_list.add(year+"-0"+i);
            }else{
                date_list.add(year+"-"+i);
            }
        }
        return date_list;
	}
	
	/**
	 * 
	 * @return 
	 */
	public static List getDay(){
	    List<String> date_list = new ArrayList<String>();
	    Calendar c = Calendar.getInstance();
	    String year = c.get(Calendar.YEAR)+"";
	    String month = (c.get(Calendar.MONTH)+1)+"";
	    for(int i = 1; i <= c.getMaximum(Calendar.DAY_OF_MONTH); i++){
	        if(i < 10){
	            String date = year + "-" +month+"-"+"0"+i;
	            date_list.add(date);
	        }else{
	            String date = year + "-" +month+"-"+i;
	            date_list.add(date);
	        }
	    }
	    return date_list;
	}
	
	public static List getYear(){
	    List<String> date_list = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        for(int i = 0; i < 4; i++){
           date_list.add((year-i)+""); 
        }
        return date_list;
	}
}
