package edu.fjnu.test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Pattern;

import edu.fjnu.empmis.domain.Employee;


import edu.fjnu.empmis.ui.MainMenuUI;
import edu.fjnu.empmis.util.FileUtil;

public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		File file = new File("G:" + File.separator + "filetest.txt");
//		
//		MessageDealService messageDealService=new MessageDealService();
//		TreeSet<Employee> employeeTreeSet=new TreeSet<Employee>();
//		TreeSet<NameTeleNumberModal> nameTeleNumberModalTreeSet=new TreeSet<NameTeleNumberModal>();
//	
//			employeeTreeSet=messageDealService.getAllMessageOrdered(file);
//			for(Employee employee:employeeTreeSet){
//				System.out.println(employee);
//
//			}
//			
//			nameTeleNumberModalTreeSet=messageDealService.getNameAndTeleOrdered(file);
//			for(NameTeleNumberModal employee:nameTeleNumberModalTreeSet){
//				System.out.println(employee);
//
//			}
//			
//		System.out.println(messageDealService.getAllMessage(file));
//		System.out.println(messageDealService.getNameAndTelePhone(file));
//		System.out.println(messageDealService.queryMessageByKeyWords(file, "jones"));
//		Employee employee=new Employee("101", "08-01928375", "zhang", "san", "y", "09", "boss", "12-02-2013");
//		messageDealService.addEmployee(file, employee);
//		Menu menu=new Menu();
//		menu.display();
		
//		Scanner scanner=new Scanner(System.in);
//		String input=scanner.next();
//		System.out.println("your selection: |");
//		switch(input){
//		
//		case "1":System.out.println("nihao");break;
//		case "2":System.out.println("hello");break;
//		default:System.out.println("Invalid code! Press Enter to continue…");
//		}
//		System.out.println("Press Enter to continue...");
//	
		
		StringBuffer stringbuffer=new StringBuffer();
		if(stringbuffer.length()==0){
			System.out.println("ni");
		}
		
		/**
		 * 正则表达式验证
		 */
		String telephone="a  b";
		String standard="[a-zA-Z]*\\s*|\\s*[a-zA-Z]*|\\s*[a-zA-Z]*\\s*|[a-zA-Z]*\\s*[a-zA-Z]*";
//		if(Pattern.compile("[0]{1}[2-8]{1}-[1-9]{1}[0-9]{7}").matcher(telephone).matches()){
//			System.out.println("符合规范");
//		}
		if(Pattern.compile(standard).matcher(telephone).matches()){
			System.out.println("符合规范");
		}
		
			}
}
