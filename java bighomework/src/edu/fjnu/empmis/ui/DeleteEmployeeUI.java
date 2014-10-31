package edu.fjnu.empmis.ui;

import java.util.regex.Pattern;

import edu.fjnu.empmis.dao.EmployeeDao;
import edu.fjnu.empmis.dao.EmployeeTxtDao;
import edu.fjnu.empmis.exception.InvalidEmployeePropertyException;
import edu.fjnu.empmis.util.SysBaseUtil;

public class DeleteEmployeeUI implements BaseUI {
	private EmployeeDao employeeTxtDao;

	public DeleteEmployeeUI() {
		// TODO Auto-generated constructor stub
		employeeTxtDao = new EmployeeTxtDao();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean isContinue = true;
		while (isContinue) {
			System.out
					.println("Enter employee’s 3 digit payroll number to enable record deletion:");
			String entryOfEmpNo = SysBaseUtil.getEntry();
			try {
				checkEmploeeNo(entryOfEmpNo);
			} catch (Exception e) {
				SysBaseUtil.pause(e.getMessage());
				continue;
			}
			System.out.println("Confirm record deletion, (y)es or (n)o, y:");
			String entryOfYOrN = SysBaseUtil.getEntry();
			if (entryOfYOrN.equalsIgnoreCase("Y")) {
				if (!employeeTxtDao.checkEmployeeNo(entryOfEmpNo)) {

					System.out.println("employee records for" + entryOfEmpNo
							+ "not found!!");
					isContinue = false;
				} else {
					employeeTxtDao.deleteEmployee(entryOfEmpNo);
					System.out.println("Record deleted.");
					System.out.println("\nDelete another?");
					String entryOfYOrN2 = SysBaseUtil.getEntry();
					if (entryOfYOrN2.equalsIgnoreCase("Y")) {
						continue;

					} else {
						isContinue = false;
					}
				}
			} else {
				isContinue = false;
			}

		}
	}

	/**
	 * 检查输入的员工号是否符合要求
	 * 
	 * @param empNo
	 */
	private void checkEmploeeNo(String empNo) {
		// 假如输入的值为空
		if (SysBaseUtil.isEmpty(empNo)) {
			throw new InvalidEmployeePropertyException(
					"No payroll number entered – try again");
		}
		// 假如输入的值多于三位数
		else if (empNo.length() != 3) {
			throw new InvalidEmployeePropertyException(
					"Payroll number can contain only numerical characters");
		}
		/**
		 * 使用正则表达式来判断字符串是否由数字组成
		 */
		// 假如输入的三个值不都是数字
		else if (!Pattern.compile("[0-9]+").matcher(empNo).matches()) {

			throw new InvalidEmployeePropertyException(
					"Payroll number can contain only numerical characters");
		}

	}
}
