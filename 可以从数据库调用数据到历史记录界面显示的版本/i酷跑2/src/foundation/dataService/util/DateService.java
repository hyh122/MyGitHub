package foundation.dataService.util;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateService {
	//得到当前日期的方法
	public static String getStringDate(){
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
		public static Date getDate(){
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
		public static String changeDateFormat(Date date){
			String currentDay;
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			return currentDay=sf.format(date);
		}
		public static String getMin(){
			SimpleDateFormat sf = new SimpleDateFormat("hh:mm:ss");
			return sf.format(getDate());
		}
		/**
		 * 
		 * @param date 传入一个日期
		 * @return 将日期的返回格式为00:00:00的该日期的小时，分钟，妙
		 */
		public static String getDateOfMinFormat(Date date){
			String disHour="";
			String disminute ="";
			String disSecond ="";
			  if(date.getHours()<10){
				  disHour="0"+date.getHours();
			  }else{
				  disHour=""+date.getHours();
			  }
			  if(date.getMinutes()<10){
				  disminute="0"+date.getMinutes();
			  }else{
				  disminute=""+date.getMinutes();
			  }
			  if(date.getSeconds()<10){
				  disSecond="0"+date.getSeconds();
			  }else{
				  disSecond=""+date.getSeconds();
			  }
				  
		      return disHour+":"+disminute+":"+disSecond;
		}
	
}
