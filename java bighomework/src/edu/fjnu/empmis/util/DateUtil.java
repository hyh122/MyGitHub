package edu.fjnu.empmis.util;

import java.util.regex.Pattern;

public class DateUtil {

	public DateUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 检查传入的日期格式是否符合现实
	 * 
	 * @param date
	 *            日期要求格式为xx-xx-xxxx,日-月-年的格式
	 * @return 布尔值
	 */
	public static boolean checkDateFormat(String date) {
		boolean isTrue = false;
		// 要求传入的日期格式为xx-xx-xxxx,日-月-年的格式
		if (Pattern.compile("\\d{2}-\\d{2}-\\d{4}").matcher(date).matches()) {
			// 以"-"分开
			String[] s = date.split("-");
			// 日的部分
			String day = s[0];
			// 月的部分
			String month = s[1];
			// 取出年的部分
			String year = s[2];

			// 假如月份符合规范01-12
			if (Pattern.compile("[0][1-9]|[1][0-2]").matcher(month).matches()) {
				// 假如月份为01,02,05,07,08,10,12则一个月有31天
				if (Pattern.compile("[0][13578]|[1][02]").matcher(month)
						.matches()) {
					if (Pattern.compile("[0][1-9]|[1][0-9]|[2][0-9]|[3][0-1]")
							.matcher(day).matches()) {
						isTrue = true;
					}
				}
				// 假如是二月份则要判断是否为润年
				else if (Pattern.compile("[0][2]").matcher(month).matches()) {
					if (checkLeapYear(year)) {// 假如是润年
						if (Pattern.compile("[0][1-9]|[1][0-9]|[2][0-9]")
								.matcher(day).matches()) {
							isTrue = true;
						}
					} else {
						if (Pattern.compile("[0][1-9]|[1][0-9]|[2][0-8]")
								.matcher(day).matches()) {
							isTrue = true;
						}
					}
				}
				// 其他的月份为30天
				else {
					if (Pattern.compile("[0][1-9]|[1][0-9]|[2][0-9]|[3][0]")
							.matcher(day).matches()) {
						isTrue = true;
					}
				}

			}
		}
		return isTrue;
	}

	public static boolean checkLeapYear(String year) {
		int intYear = Integer.parseInt(year);
		if (intYear % 4 == 0 && intYear % 100 != 0) {
			return true;
		}
		if (intYear % 400 == 0) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// String year="2012";
		// System.out.println(checkLeapYear(year));

		String date = "10-10-2014";
		System.out.println(checkDateFormat(date));

	}
}
