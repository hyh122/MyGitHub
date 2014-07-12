package com.example.dataModal;

 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="T_User")
public class User {
	
	
		@DatabaseField(id=true)//主码
		private String username;
		@DatabaseField(canBeNull=false)//非空
		private String password;
		@DatabaseField(canBeNull=false)
		private Date date;
		//所选课程,一对多关系的"一"这边的映射,在SelectCourse表也要对User做对应的"多"那边的映射
		@ForeignCollectionField(eager=false)
		ForeignCollection<SelectCourse> SCs;

		 
		
		public User() {
			super();
		}

		public User(String username, String password, Date date) {
			super();
			this.username = username;
			this.password = password;
			this.date = date;
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

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public ForeignCollection<SelectCourse> getSCs() {
			return SCs;
		}

		public void setSCs(List<SelectCourse> lsc) {
			SCs = (ForeignCollection<SelectCourse>) lsc;
		}
		
	

	


		
		
}
