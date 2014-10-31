package edu.fjnu.empmis.ui;

import edu.fjnu.empmis.dao.EmployeeDao;
import edu.fjnu.empmis.dao.EmployeeTxtDao;
import edu.fjnu.empmis.util.SysBaseUtil;

public class QueryEmployeeByKeyWordUI implements BaseUI {
	private EmployeeDao employeeTxtDao;

	public QueryEmployeeByKeyWordUI() {
		// TODO Auto-generated constructor stub
		employeeTxtDao = new EmployeeTxtDao();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean isContinue = true;
		while (isContinue) {
			System.out.print("Enter keyword:");
			String entry = SysBaseUtil.getEntry();
			if (SysBaseUtil.isEmpty(entry)) {
				SysBaseUtil.pause("No keyword entered ¨C try again¡­");
				continue;
			}
			StringBuffer stringBuffer = employeeTxtDao
					.queryMessageByKeyWord(entry);
			if (stringBuffer.length() != 0) {
				System.out.println(stringBuffer);
				isContinue = false;
			} else {
				System.out.println("Keyword ¨C" + entry + "- not found");
				isContinue = false;
			}

		}

	}

}
