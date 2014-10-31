package edu.fjnu.empmis.dao;

import java.util.List;
import java.util.Set;

import edu.fjnu.empmis.domain.Employee;

public interface EmployeeDao {
	public void saveEmployee(Employee employee);
	public List<Employee> ListEmployees();
	public Set<Employee>  ListSortedEmployees();
	public StringBuffer queryMessageByKeyWord(String keyWord);
	public boolean checkEmployeeNo(String empNo);
	public void deleteEmployee(String empNo);
}
