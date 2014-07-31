package com.entity;

import java.util.Date;

public class HeartRate {
	private Integer heartRate;
	private Date collecTime;
	public Integer getHeartRate() {
		return heartRate;
	}
	
	public HeartRate() {
		super();
	}

	public HeartRate(Integer heartRate, Date collecTime) {
		super();
		this.heartRate = heartRate;
		this.collecTime = collecTime;
	}

	public void setHeartRate(Integer heartRate) {
		this.heartRate = heartRate;
	}
	public Date getCollecTime() {
		return collecTime;
	}
	public void setCollecTime(Date collecTime) {
		this.collecTime = collecTime;
	}
	
	
}
