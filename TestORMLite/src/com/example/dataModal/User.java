package com.example.dataModal;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName="T_User")
public class User {
	
	
		@DatabaseField(id=true)
		private String username;//�û�������Ϊ����
		@DatabaseField(canBeNull=false,columnName="hello")
		private String password;//����
		
		public User() {
			super();
		}
		public User(int userID, String username, String password) {
			super();
			
			this.username = username;
			this.password = password;
		}
	
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}

		
}
