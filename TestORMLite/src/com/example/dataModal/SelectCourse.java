package com.example.dataModal;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="T_SelectCourse")
public class SelectCourse {
	//自增长Id
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField(canBeNull=false)
	private String coursename;
	@DatabaseField(canBeNull=false)
	private float score;
	@DatabaseField(foreign=true)//允许为空,如果不允许为空加canBeNull=false
	private User user;
	
	
	public SelectCourse() {
		super();
	}
	
	public SelectCourse(int id, String coursename, float score, User user) {
		super();
		this.id = id;
		this.coursename = coursename;
		this.score = score;
		this.user = user;
	}

	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
