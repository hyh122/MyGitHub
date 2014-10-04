package domain.systemManaConfig;

import java.sql.SQLException;
import java.util.List;

import domain.dataCollect.OneSport;
import foundation.dataService.SystemManagerDataService;

import foundation.dataService.base.DataContext;
import foundation.dataService.util.DateService;

public class SystemManageService {
	private DataContext dataContext;
	
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private User User;
	
	

	public SystemManageService() {
		dataContext=new DataContext();
		
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void register() {
		// begin-user-code
		// TODO 自动生成的方法存根

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void logout() {
		// begin-user-code
		// TODO 自动生成的方法存根

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void alter() {
		// begin-user-code
		// TODO 自动生成的方法存根

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void login() {
		// begin-user-code
		// TODO 自动生成的方法存根

		// end-user-code
	}
	/**
	 * 根据好友名或者好友备注来查找到该好友的id
	 * @param nameType
	 * @param name
	 * @return
	 */
	public int getFriendIdByName(String nameType,String name){
		String sql = null;
		if(nameType.equals("friendName")){
			sql= "select id from T_Friends where friendName='"+name+"'";
		}else if(nameType.equals("anotherName")){
			sql= "select id from T_Friends where anotherName='"+name+"'";
		}
		int id = 0;
		try {
		
			List<String[]> ID=dataContext.queryBySql(Friends.class, Integer.class,sql);
			if(ID.size()==0){
			}	
			else{
				id=Integer.parseInt(ID.get(0)[0]);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	/**
	 * 根据好友的id号删除好友
	 * @param id
	 */
	public void deleteFriendById(int id){
		try {
			dataContext.deleteById(id, Friends.class,Integer.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
