package domain.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class disDate {
	//�õ���ǰ���ڵķ���
	public static String getDate(){
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
		public static Date getDate2(){
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
		
	
}
