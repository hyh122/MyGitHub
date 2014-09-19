package domain.statisticsDisplay;

import java.util.Date;

public class ChildListItem {
	private float distance;
	private int avgHeartRate;
	private double avgSpeed;
	private Date time;
	
	public ChildListItem() {
		super();
	}
	
	public ChildListItem(float distance, int avgHeartRate, double avgSpeed,
			Date time) {
		super();
		this.distance = distance;
		this.avgHeartRate = avgHeartRate;
		this.avgSpeed = avgSpeed;
		this.time = time;
	}

	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public int getAvgHeartRate() {
		return avgHeartRate;
	}
	public void setAvgHeartRate(int avgHeartRate) {
		this.avgHeartRate = avgHeartRate;
	}
	public double getAvgSpeed() {
		return avgSpeed;
	}
	public void setAvgSpeed(double avgSpeed) {
		this.avgSpeed = avgSpeed;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
	


	
	
}
