package edu.fjnu.empmis.domain;

import edu.fjnu.empmis.util.FormatUtil;

public class Employee implements Comparable<Employee> {
	// 员工号
	private String employeeNo;
	private String telephoneNumber;
	private String lastName;
	private String firshName;
	private String initial;
	private String departmentNumber;
	// 职位
	private String jobTitle;
	// 雇佣日期
	private String dateOfHiring;

	public Employee() {
		super();
	}

	public Employee(String employeeNo, String telephoneNumber, String lastName,
			String firshName, String initial, String departmentNumber,
			String jobTitle, String dateOfHiring) {
		super();
		this.employeeNo = employeeNo;
		this.telephoneNumber = telephoneNumber;
		this.lastName = lastName;
		this.firshName = firshName;
		this.initial = initial;
		this.departmentNumber = departmentNumber;
		this.jobTitle = jobTitle;
		this.dateOfHiring = dateOfHiring;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirshName() {
		return firshName;
	}

	public void setFirshName(String firshName) {
		this.firshName = firshName;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getDepartmentNumber() {
		return departmentNumber;
	}

	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDateOfHiring() {
		return dateOfHiring;
	}

	public void setDateOfHiring(String dateOfHiring) {
		this.dateOfHiring = dateOfHiring;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.employeeNo.hashCode() + this.telephoneNumber.hashCode()
				+ this.lastName.hashCode() + this.firshName.hashCode()
				+ this.initial.hashCode() + this.departmentNumber.hashCode()
				+ this.jobTitle.hashCode() + this.dateOfHiring.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		System.out.println("equals() is invoked!");
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof Employee))
			return false;
		Employee otherEmployee = (Employee) obj;
		return this.employeeNo.equals(otherEmployee.getEmployeeNo())
				&& this.telephoneNumber.equals(otherEmployee.telephoneNumber)
				&& this.lastName.equals(otherEmployee.lastName)
				&& this.firshName.equals(otherEmployee.firshName)
				&& this.initial.equals(otherEmployee.initial)
				&& this.departmentNumber.equals(otherEmployee.departmentNumber)
				&& this.jobTitle.equals(otherEmployee.jobTitle)
				&& this.dateOfHiring.equals(otherEmployee.dateOfHiring);

	}

	@Override
	public int compareTo(Employee otherEmployee) {

		return this.employeeNo.compareTo(otherEmployee.getEmployeeNo());

	}

	@Override
	public String toString() {
		return employeeNo + ":" + telephoneNumber + ":" + lastName + ":"
				+ firshName + ":" + initial + ":" + departmentNumber + ":"
				+ jobTitle + ":" + dateOfHiring;
	}

	public String sortedMessage() {

		return FormatUtil.getRegularBytesOfString(lastName, 12)
				+ FormatUtil.getRegularBytesOfString(firshName, 12)
				+ FormatUtil.getRegularBytesOfString(initial, 6)
				+ FormatUtil.getRegularBytesOfString(employeeNo, 8)
				+ FormatUtil.getRegularBytesOfString(telephoneNumber, 12)
				+ FormatUtil.getRegularBytesOfString(departmentNumber, 12)
				+ FormatUtil.getRegularBytesOfString(jobTitle, 18)
				+ FormatUtil.getRegularBytesOfString(dateOfHiring, 12);
	}

	public String nameAndTelephone() {
		return lastName + ":" + firshName + ":" + telephoneNumber;
	}

	public String sortedNameAndTelephone() {
		return FormatUtil.getRegularBytesOfString(lastName, 12)
				+ FormatUtil.getRegularBytesOfString(firshName, 12)

				+ FormatUtil.getRegularBytesOfString(telephoneNumber, 12);

	}

}
