package domain.systemManaConfig;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import domain.dataCollect.OneSport;
import foundation.dataService.SystemManagerDataService;

import foundation.dataService.base.DataContext;
import foundation.dataService.util.DateService;

public class SystemManageService {
	private DataContext dataContext;
	private  User currentLoginedUser;
	private Friends friend;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	
	


	

	public SystemManageService() {
		dataContext=new DataContext();
		
	}
//����user�������islogin�ֶ������ҵ�ǰ��¼���û�
	public User getCurrentLoginedUser() {
		List<User> users = null;
		try {
			/*
			 * �����ѯ������
			 */
			QueryBuilder<User, String> queryBuilder = dataContext.getDao(
					User.class,String.class).queryBuilder();
			// ��ѯ����Ϊdate������OneSport
			queryBuilder.where().eq(User.ISLOGIN, true);//��ʾΪtrue��Ϊ��ǰ��¼���û�
			// prepare our sql statement
			PreparedQuery<User> query = queryBuilder.prepare();
			users =dataContext.query(User.class,String.class, query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users.get(0);
	}
//���ݺ��ѵ�email���������ĸ��û����ҵ�����
	public Friends gerFriend(String email,User user){
		List<Friends> friends = null;

		try {
			/*
			 * �����ѯ������
			 */
			QueryBuilder<Friends, Integer> queryBuilder = dataContext.getQueryBuilder(
					Friends.class, Integer.class);
			// ��ѯ����Ϊdate,��count�ε�OneSport
			queryBuilder.where().eq(Friends.EMAIL, email).and()
					.eq(Friends.ONEANDMOREUSER, user);

			// prepare our sql statement
			PreparedQuery<Friends> query = queryBuilder.prepare();
			friends = dataContext.query(Friends.class, Integer.class, query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(friends.size()!=0){
		return friends.get(0);
		}else{
		return null;
		}
	}


	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void register() {
		// begin-user-code
		// TODO �Զ����ɵķ������

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void logout() {
		// begin-user-code
		// TODO �Զ����ɵķ������

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void alter() {
		// begin-user-code
		// TODO �Զ����ɵķ������

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void login() {
		// begin-user-code
		// TODO �Զ����ɵķ������

		// end-user-code
	}
	/**
	 * ���ݺ��������ߺ��ѱ�ע�����ҵ��ú��ѵ�id
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
	 * ���ݺ��ѵ�id��ɾ������
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
		// TODO �Զ����ɵķ������

	}

}
