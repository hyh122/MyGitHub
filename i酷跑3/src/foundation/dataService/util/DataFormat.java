package foundation.dataService.util;

import java.text.DecimalFormat;

public class DataFormat {
		//�������ݵ���ʾ��ʽ,��Ϊ��̬����
		public static String setDataFormat(double x,String format){
			// �������ݸ�ʽ,formatΪҪ��ʾ�����ݸ�ʽ,����:0.00,0.000.......
			
			DecimalFormat df = new DecimalFormat(format);
			String num = df.format(x);
			return num;
		}
		/**
		 * ����һ�����ڸ�ʽΪ��0000-00-00�������ڽ�����ݲ���ȥ��
		 * @param date
		 * @return
		 */
		public static String setDateFormatForMonthAndDay(String date){
			String dateForMonthAndDay;
			dateForMonthAndDay=date.substring(5);
			return dateForMonthAndDay;
			
		}
}
