package edu.fjnu.empmis.ui;

import edu.fjnu.empmis.exception.EmployeeMISException;

public class UIFactory {
	private static final UIFactory FACTORY=new UIFactory();
	
	public static UIFactory getInstance(){
		return FACTORY;
	}
	private UIFactory(){
		
	}
	public static BaseUI getComponent(String uiType){
		BaseUI ui=null;
		if(uiType.equals(UIType.MAIN_MENU)){
			ui= new MainMenuUI();
		}else if(uiType.equals(UIType.ADD_EMPLOYEE)){
			ui=new AddEmployeeUI();
		}
		else if(uiType.equals(UIType.QueryEmployeeByKeyWordUI)){
			ui=new QueryEmployeeByKeyWordUI();
		}else if(uiType.equals(UIType.DELETEEMPLOYEEUI)){
			ui=new DeleteEmployeeUI();
		}
		if(ui==null){
			throw new EmployeeMISException("UI部件不存在,部件名:"+uiType);
		}
		
		return ui;
	}
	
	
}
