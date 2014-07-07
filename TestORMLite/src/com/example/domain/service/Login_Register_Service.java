package com.example.domain.service;

import java.sql.SQLException;

import com.example.dao.DataContext;
import com.example.dataModal.User;

public class Login_Register_Service implements ILogin_Register_Service {
	private DataContext dtx;

	public Login_Register_Service() {
		dtx=new DataContext();
	}
	/* (non-Javadoc)
	 * @see com.example.domain.service.ILogin_Register_Service#addUser(com.example.model.User)
	 */
	@Override
	public boolean addUser(User user){
		try {
			dtx.add(user, User.class, Integer.class);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
//	@Override
//	//根据用户名查找用户
//	public boolean findUserByName(String name){
//		boolean k=false;
//		try {
//			User user=new User();
//			user=dtx.queryById(User.class, String.class, name);
//			if(user==null){
//				k=false;
//			}
//			else{
//				k=true;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return k;
//	}
	
	@Override
	//根据用户名查找用户
	public User findUserByName(String name){
		User user=new User();
		try {
			
			user=dtx.queryById(User.class, String.class, name);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
}
