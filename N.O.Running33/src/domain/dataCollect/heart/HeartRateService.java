package domain.dataCollect.heart;



import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.File.TxtFileUtil;

import foundation.ble.BLEService;
import foundation.ble.ITimeout;



import android.content.Intent;



public class HeartRateService implements ITimeout{
	//存放一分钟的全部心率数据
	private List<Integer> heartRates=new ArrayList<Integer>();;
	//存放每一分钟的最佳心率数据
	private List<HeartRateData> minuteHeartRates=new ArrayList<HeartRateData>();
	//时间
	private Date collectTime;
	//新建文件
	private File f=new File("/sdcard/HeartRates.txt"/**文件路径名**/);
	
	private IHeartRateTimeOut listener=null;
	
	private int flag=0;

	 public void setListener(IHeartRateTimeOut listener) {
		this.listener = listener;
	 }


	public HeartRateService() {
		 BLEService.setLis(this);
		 
	 }
	public void start(){
		 BLEService.work();
	}
	public void stop(){
		flag=1;
	}
	
	//设置每分钟的全部心率数据集合
	public void setHeartRates(){
		//取得当前一分钟的全部的心率集合
		heartRates.addAll(BLEService.getHeartRates());
	}
	//求最佳心率值
	public void setBestHeartRate(){
		 //求去除后的心率值的总和
		 int sum = 0;
		 //去平均值
		 int avg;
		 int size=heartRates.size();
		
		 if(size==0){
			 if(minuteHeartRates.size()==0){
				 avg=0;
			 }
			 else{
			 //如果这分钟没有心率值则拷贝上一分钟的心率值作为这一分钟的心率值
			 avg=minuteHeartRates.get(minuteHeartRates.size()-1).getHeartRate();
			 }
		 }
		 else if(size==1){
			 avg=heartRates.get(0);
		 }else if(size==2){
			 sum=heartRates.get(0)+heartRates.get(1);
			 avg=sum/2;
		 }else{
			 int n1=size/8+1;//去除的心率值个数
			 int n2=n1/2+1;//去除的头部的下标
			 int n3=size-(n1-n2);//去除的尾部的下标
			 
			 int lastSize=size-n1;//去除后心率的总个数
			 TxtFileUtil.appendToFile("lastSize:"+lastSize+"\r\n",f);
			 for(int i=n2;i<n3;i++){
				 sum += heartRates.get(i).doubleValue();
				 TxtFileUtil.appendToFile("2."+heartRates.get(i)+"\r\n",f);
			 }
			 //求出平均心率值
			 avg=sum/lastSize;
		 }
		 
		 TxtFileUtil.appendToFile("ave:"+avg+"\r\n",f);
		 HeartRateData heartRate=new HeartRateData(collectTime, avg);
		 
		 //解析到一个心率值后通知上层
		 listener.HOnTimeOut(heartRate);
		 
		 //添加到分钟心率中
		 minuteHeartRates.add(heartRate);
		 //清空本分钟的所有心率集合
		 heartRates.clear();
		 
	}
	@Override
	public void onTimeout(Date collectTime) {
		// TODO Auto-generated method stub
		if(flag==0){
		this.collectTime=collectTime;
		TxtFileUtil.appendToFile("start:"+this.collectTime+"\r\n",f);
		//取得当前一分钟的所有的心率
		setHeartRates();
		TxtFileUtil.appendToFile("size:"+heartRates.size()+"\r\n",f);
		
		for(int i=0;i<heartRates.size();i++){
			TxtFileUtil.appendToFile(heartRates.get(i)+"\r\n",f);
		}
		//求出这一分钟的最佳心率
		setBestHeartRate();
		}
		
	}
	public List<HeartRateData> getHeartRateDataList(){
		List<HeartRateData> heartRateDataList=new ArrayList<HeartRateData>();
		heartRateDataList.addAll(minuteHeartRates);
		//minuteHeartRates.clear();
	  	return heartRateDataList;
	} 
}

	

		 
		
		 
   	    
   	    
		
		
		




