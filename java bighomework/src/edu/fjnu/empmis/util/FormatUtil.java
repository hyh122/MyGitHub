package edu.fjnu.empmis.util;

public class FormatUtil {
	/**
	 * 传入一个字符串让该将该字符串转换成固定占用几个字节空间的字符串
	 * 
	 * @param s
	 *            传入的字符串
	 * @param num
	 *            字节数
	 * @return 改变所占字节数后的字符串
	 */
	public static String getRegularBytesOfString(String s, int num) {

		int length = s.length();
		for (int i = length; i < num; i++) {
			s += " ";
		}

		return s;
	}
}
