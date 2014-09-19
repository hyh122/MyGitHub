package foundation.dataService.util;

import java.text.DecimalFormat;

public class DataFormat {
		//设置数据的显示格式,设为静态方法
		public static String setDataFormat(double x,String format){
			// 设置数据格式,format为要显示的数据格式,比如:0.00,0.000.......
			
			DecimalFormat df = new DecimalFormat(format);
			String num = df.format(x);
			return num;
		}
}
