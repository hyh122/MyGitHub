package edu.fjnu.empmis;

import edu.fjnu.empmis.exception.EmployeeMISException;
import edu.fjnu.empmis.ui.UIType;
import edu.fjnu.empmis.util.SysBaseUtil;

public class SysLoader {
/**
 * 主调代码要少，可读性要强
 */
	public static void main(String[] args){
		try{
		SysBaseUtil.checkFile();
		
		SysBaseUtil.loadUI(UIType.MAIN_MENU);
		}
		catch(EmployeeMISException e){
			System.out.println(e.getMessage());
		}finally{
			System.out.println("thank use!");
		}
	}
}
