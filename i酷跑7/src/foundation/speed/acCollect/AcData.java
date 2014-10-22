package foundation.speed.acCollect;

import java.util.ArrayList;
import java.util.List;

public class AcData{
	public double []Ax,Ay,Az;
	public int n;
	public AcData(){
		Ax = new double[50];
		Ay = new double[50];
		Az = new double[50];
	}
	public Boolean setAcceleration(int i,double ax,double ay,double az){
		if(Ax!=null){
		Ax[i] = ax;
		Ay[i] = ay;
		Az[i] = az;
		this.n = i+1;
		return true;
		}
		return false;
	}
public double[] getAx(){
	return Ax;
}
	public double getAxlast(){
		return Ax[n-1];
	}
	public double getAylast(){
		return Ay[n-1];
	}
	public double getAzlast(){
		return Az[n-1];
	}
}