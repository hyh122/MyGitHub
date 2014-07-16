package domain.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class disDate {
	//得到当前日期的方法
	public static String getDate(){
		//日历
		 Calendar ca;
		//天
		Date currentDay;
		//字符串的日期
		String ScurrentDay;
		//得到一个日期的实例
				ca=Calendar.getInstance();
				//设置时间为当前日期
				ca.setTime(new Date());
				//获取当前日期
				currentDay=ca.getTime();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				//将当前日期转成字符串格式
				ScurrentDay=sf.format(currentDay);
		return ScurrentDay;
	}
	//得到当前日期的方法,保存为日期类型
		public static Date getDate2(){
//			//日历
//			 Calendar ca;
//			//天
//			Date currentDay;
//			
//			//得到一个日期的实例
//					ca=Calendar.getInstance();
//					//设置时间为当前日期
//					ca.setTime(new Date());
//					//获取当前日期
//					currentDay=ca.getTime();
//					
//			return currentDay;
			Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前时间     
			return curDate;
			
		}
		
	
}
