package foundation.speed.acCollect;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.File.TxtFileUtil;


import android.content.Intent;
import android.text.style.StyleSpan;

public class AcBaseService extends AcRootService {
	private static AcTimeOut kalmanService = null;
	private static int i = 0, m = 1;
	private static Boolean flag = false;
	private static MyIfoTask myIfoTask;
	private static Timer myIfoTimer;
	private static AcData acData_temp;
	public  static ArrayList<AcData> acDataList;
	public  static Boolean normalExit = false;
	@Override
	public void myOnCreate(){
//		预估可能的元素数量，避免不必要扩容，提升效率
		acDataList = new ArrayList<AcData>(30);
//		start();
	}
	@Override
	public void updateData(double ax, double ay, double az){
		if(flag){
			if(i == 0)
				acData_temp = new AcData();
			acData_temp.setAcceleration(i, ax, ay, az);
			i++;
			if(i == 50){
				i = 0;
				m++;
				acDataList.add(acData_temp);
			}
		}
	}
	@Override
	public void myOnDestroy(){
		flag = false;
		myIfoTask.cancel();
		myIfoTimer.purge();
		myIfoTimer.cancel();
		if(!normalExit)
			TxtFileUtil.writeSpeed("AcSe----------【Abnormal Exit】----------"+"\n\n");
	}
	public static void setI(AcTimeOut k){
		kalmanService = k;
	}
	public static class MyIfoTask extends TimerTask{    
	    public void run(){
	    	flag = false;
	    	i = 0;
	    	acDataList.add(acData_temp);
//			深层拷贝，防止因引用而出现的一些问题
			ArrayList<AcData> temp = new ArrayList<AcData>(30);
			temp.addAll((ArrayList<AcData>)acDataList);
			TxtFileUtil.writeSpeed("AcSe - send data_a to KaSe"+"\n");
			kalmanService.caculate((ArrayList<AcData>)temp.clone());
	    	acDataList.clear();
	    	flag = true;
	    }
	}
	public static void start(){
		TxtFileUtil.writeSpeed("AcSe-------------【START】-------------"+"\n");
		flag = true;
		myIfoTask = new MyIfoTask();
		myIfoTimer = new Timer();
		myIfoTimer.schedule(myIfoTask, 30*1000, 30*1000);
	}
	public static void stop(){
//		acDataList.add(acData_temp);
//    	myservice.caculate(acDataList.clone());
		flag = false;
		myIfoTask.cancel();
		myIfoTimer.purge();
		myIfoTimer.cancel();
		normalExit = true;
		TxtFileUtil.writeSpeed("AcSe-------------【 STOP 】-------------"+"\n");
	}
}
