package foundation.speed.kalmanFilter;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.File.TxtFileUtil;

import domain.dataCollect.speed.SpeedData;
import foundation.speed.acCollect.AcData;
import foundation.speed.acCollect.AcTimeOut;

import android.R.array;
import android.text.format.Time;

public class KalmanService implements AcTimeOut {
	private VcTimeOut speedService = null;
	private ArrayList<AcData> acDatas = new ArrayList<AcData>();
	private Boolean half = true;
	private Kalman k = new Kalman();
	private double Vx = 0,Vy = 0,Vz = 0,V=0;
	private double ax_last = 0,ay_last = 0,az_last = 0,distance = 0;
	private Boolean oneMinute = false;
	private Boolean firstSecornd = true;
	SimpleDateFormat df;
	public KalmanService(){	
	};
	public void setI(VcTimeOut v){
		this.speedService = v;
	}
	@Override
	public void caculate(ArrayList<AcData> a) {
		// TODO Auto-generated method stub
		acDatas = a;
		filter();
		sendData();
	}
	public void filter(){
//		判断是否为前50个数据
		if(firstSecornd){
			AcData temp;
			for(int i = 1; i < acDatas.size(); i++){
				temp = acDatas.get(i);
				k.filter(temp.Ax, temp.Ay, temp.Az, temp.n, ax_last, ay_last, az_last);
				ax_last = temp.getAxlast();
				ay_last = temp.getAylast();
				az_last = temp.getAzlast();
				Vx += k.getVx();
				Vy += k.getVy();
				Vz += k.getVz();
				double V = Math.sqrt(Vx*Vx+Vy*Vy+Vz*Vz);
				distance += V;
			}
			firstSecornd = false;
		}
		else{	
			AcData temp;
			for(int i = 0; i < acDatas.size(); i++){
				temp = acDatas.get(i);
				k.filter(temp.Ax, temp.Ay, temp.Az, temp.n, ax_last, ay_last, az_last);
				ax_last = temp.getAxlast();
				ay_last = temp.getAylast();
				az_last = temp.getAzlast();
				Vx += k.getVx();
				Vy += k.getVy();
				Vz += k.getVz();
				V = Math.sqrt(Vx*Vx+Vy*Vy+Vz*Vz);
				distance += V;
				TxtFileUtil.writeSpeed("KaSe - 距离："+distance+"\n");
			}
		}
	}

	public void sendData(){
		if(oneMinute){
			DecimalFormat df1 = new DecimalFormat("0.00");
			Date now =new Date();
			DecimalFormat df2 = new DecimalFormat("0.000");
			double temp = Double.valueOf(df2.format(distance/1000));
			SpeedData s = new SpeedData(now,Double.valueOf(df1.format(V)),temp);
			TxtFileUtil.writeSpeed("KaSe - send data_v to SpSe"+"\n");
			speedService.getSpeed(s);
			distance = 0;//路程设为0
		}
		oneMinute = !oneMinute;
	}
}
