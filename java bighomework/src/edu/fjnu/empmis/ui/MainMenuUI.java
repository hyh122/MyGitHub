package edu.fjnu.empmis.ui;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import edu.fjnu.empmis.dao.EmployeeDao;
import edu.fjnu.empmis.dao.EmployeeTxtDao;
import edu.fjnu.empmis.domain.Employee;

import edu.fjnu.empmis.util.SysBaseUtil;

public class MainMenuUI implements BaseUI {
	private EmployeeDao employeeDao;

	public MainMenuUI() {
		super();
		employeeDao = new EmployeeTxtDao();
		
	}

	@Override
	public void run() {
		boolean isContinue = true;
		List<Employee> employees = null;
		Set<Employee> employeeTreeSet = null;
		while (isContinue) {
			// TODO Auto-generated method stub
			System.out
					.println("倓堊訧捅 - Employee Information - Main Menu\n====================================");
			System.out.println("1 - Print All Current Records");
			System.out.println("2 每 Print All Current Records (formatted)");
			System.out.println("3 每 Print Names and Phone Numbers");
			System.out.println("4 每 Print Names and Phone Numbers (formatted)");
			System.out.println("5 - Search for specific Record(s)");
			System.out.println("6 - Add New Records");
			System.out.println("7 每 Delete Records");
			System.out.println("\nQ - Quit");

			System.out.println("\nYour Selection: |");
			String entry = SysBaseUtil.getEntry();
			if (SysBaseUtil.isEmpty(entry)) {
				SysBaseUtil
						.pause("No selection entered. Press Enter to continue＃");
				continue;
			}
			switch (entry.toUpperCase()) {
			case "1":
				employees = employeeDao.ListEmployees();
				for (Employee employee : employees) {
					System.out.println(employee.toString());
				}
				break;
			case "2":
				employeeTreeSet = employeeDao.ListSortedEmployees();
				for (Employee employee : employeeTreeSet) {
					System.out.println(employee.sortedMessage());
				}
				break;
			case "3":
				employees = employeeDao.ListEmployees();
				for (Employee employee : employees) {
					System.out.println(employee.nameAndTelephone());
				}
				break;
			case "4":
				employeeTreeSet = employeeDao.ListSortedEmployees();
				for (Employee employee : employeeTreeSet) {
					System.out.println(employee.sortedNameAndTelephone());
				}
				break;
			case "5":SysBaseUtil.loadUI(UIType.QueryEmployeeByKeyWordUI);
				break;
			case "6":SysBaseUtil.loadUI(UIType.ADD_EMPLOYEE);
				break;
			case "7":SysBaseUtil.loadUI(UIType.DELETEEMPLOYEEUI);
				break;
			case "Q":
				isContinue = false;
				break;
			default:
				SysBaseUtil.pause("Invalid code! Press Enter to continue＃");
			}

		}
	}
}
