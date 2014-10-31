package edu.fjnu.empmis.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import edu.fjnu.empmis.domain.Employee;

import edu.fjnu.empmis.util.FileUtil;
import edu.fjnu.empmis.util.SysBaseUtil;

public class EmployeeTxtDao implements EmployeeDao {
	private FileUtil fileUtil;
	private File file=new File(SysBaseUtil.FILEPATH);


	public EmployeeTxtDao() {
		fileUtil = new FileUtil();
	}

	@Override
	public void saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		try {
			fileUtil.writeToFile(file, employee.getEmployeeNo(),
					employee.getTelephoneNumber(),employee.getLastName(),
					employee.getFirshName(), employee.getInitial(),
					employee.getDepartmentNumber(), employee.getJobTitle(),
					employee.getDateOfHiring());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Employee> ListEmployees() {
		List<Employee> employees=new ArrayList<Employee>();
		
		
		try {
			// 将读到的String类型的信息按行划分成若干条信息
			List<String> messageByLine = fileUtil.readFileByLine(file);
			//取出每一行的信息装配到一个Employee类中,并添加到集合中
			for (int i = 0; i < messageByLine.size(); i++) {
				Employee employee=new Employee();
				String OneEmpMsg=messageByLine.get(i);
				// 按照分号分割字符串
				String str[] =OneEmpMsg.toString().trim().split(":");
				employee.setEmployeeNo(str[0]);
				employee.setTelephoneNumber(str[1]);
				employee.setLastName(str[2]);
				employee.setFirshName(str[3]);
				employee.setInitial(str[4]);
				employee.setDepartmentNumber(str[5]);
				employee.setJobTitle(str[6]);
				employee.setDateOfHiring(str[7]);
				employees.add(employee);
			}

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employees;
	}

	@Override
	public Set<Employee> ListSortedEmployees() {
		TreeSet<Employee> employeeTreeSet = new TreeSet<Employee>();

		// 将读到的String类型的信息按行划分成若干条信息
		List<Employee> listEmployees =new ArrayList<Employee>();
		listEmployees= ListEmployees();
		

		for (int i = 0; i < listEmployees.size(); i++) {
			Employee employee=new Employee(); 
			// 将map集合的信息装载到Employee类中
			employee = listEmployees.get(i);
			// 添加到集合中
			employeeTreeSet.add(employee);
		}

		return employeeTreeSet;
	}
	/**
	 * 根据关键字查询员工信息
	 * 
	 * @param file
	 * @param keyWords
	 *            关键字
	 * @return
	 */
	@Override
	public StringBuffer queryMessageByKeyWord(String keyWord) {
		// 将读到的String类型的信息按行划分成若干条信息
		List<String> messageByLine = new ArrayList<String>();
		StringBuffer stringBuffer = new StringBuffer();
		try {
			messageByLine = fileUtil.readFileByLine(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < messageByLine.size(); i++) {
			if (ContainsKeyword(messageByLine.get(i), keyWord)) {
				stringBuffer.append(messageByLine.get(i) + "\n");
			}

		}
		return stringBuffer;

	}
	@Override
	public boolean checkEmployeeNo(String empNo) {
		// TODO Auto-generated method stub
		List<Employee> employees=ListEmployees();
		for(Employee employee:employees){
			if(empNo.equals(employee.getEmployeeNo())){
				return true;
			}
		}
		return false;
	}
	@Override
	public void deleteEmployee(String empNo) {
		// TODO Auto-generated method stub
		List<Employee> employees=ListEmployees();
		for(int i=0;i<employees.size();i++){
			if(empNo.equals(employees.get(i).getEmployeeNo())){
				employees.remove(i);
				break;
			}
		}
		File file=new File(SysBaseUtil.FILEPATH);
		
		try {
			OutputStream fileOutputStream=new FileOutputStream(file);
			PrintStream printStream=new PrintStream(fileOutputStream);
			for(int i=0;i<employees.size();i++){
				printStream.println(employees.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 根据关键字查询,不要求大小写一样，只要字母一样就可以,不用大小写也一样
	 * 
	 * @param docObject
	 * @param keyword
	 * @return
	 */
	public boolean ContainsKeyword(String str, String keyword) {
		if (str.toUpperCase().toString().indexOf(keyword.toUpperCase(), 0) >= 0)
			return true;
		return false;
	}

	

	

}
