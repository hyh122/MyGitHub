package foundation.dataService.util;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateService {
	//�õ���ǰ���ڵķ���
	public static String getStringDate(){
		//����
		 Calendar ca;
		//��
		Date currentDay;
		//�ַ���������
		String ScurrentDay;
		//�õ�һ�����ڵ�ʵ��
				ca=Calendar.getInstance();
				//����ʱ��Ϊ��ǰ����
				ca.setTime(new Date());
				//��ȡ��ǰ����
				currentDay=ca.getTime();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				//����ǰ����ת���ַ�����ʽ
				ScurrentDay=sf.format(currentDay);
		return ScurrentDay;
	}
	//�õ���ǰ���ڵķ���,����Ϊ��������
		public static Date getDate(){
//			//����
//			 Calendar ca;
//			//��
//			Date currentDay;
//			
//			//�õ�һ�����ڵ�ʵ��
//					ca=Calendar.getInstance();
//					//����ʱ��Ϊ��ǰ����
//					ca.setTime(new Date());
//					//��ȡ��ǰ����
//					currentDay=ca.getTime();
//					
//			return currentDay;
			Date   curDate   =   new   Date(System.currentTimeMillis());//��ȡ��ǰʱ��     
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
		 * @param date ����һ������
		 * @return �����ڵķ��ظ�ʽΪ00:00:00�ĸ����ڵ�Сʱ�����ӣ���
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
