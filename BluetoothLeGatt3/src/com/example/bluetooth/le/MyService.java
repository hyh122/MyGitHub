package com.example.bluetooth.le;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.File.TxtFileUtil;
import com.entity.HeartRate;
import com.tools.DateService;

import android.content.Intent;



public class MyService{
	//存放一分钟的全部心率数据
	private List<Integer> heartRates=new ArrayList<Integer>();;
	//存放每一分钟的最佳心率数据
	private List<HeartRate> minuteHeartRates=new ArrayList<HeartRate>();
	//时间
	private Date collectTime;

	//新建文件
	private File f=new File("/sdcard/HeartRates.txt"/**文件路径名**/);
	
	private boolean flag=false;
	
	//设置每分钟的全部心率数据集合
	public void setHeartRates(){
		//取得当前一分钟的全部的心率集合
		heartRates.addAll(BluetoothLeService.getHeartRates());
	}
	
	
	
	 public MyService() {
//		 final Thread thread = new Thread(run);
//		 TimerTask task=new TimerTask(){
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					thread.run();
//			
//				}
//	     };
//	     UpdateTimer=new Timer();
//	     UpdateTimer.schedule(task, 60000, 60000);//第一次更新时间为一分钟，频率为一分钟
	}


	
	
	Runnable run = new Runnable() {

		@Override
		public void run() {
			while(true){
			 synchronized (this){
				try {
					Thread.currentThread().sleep(60000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//休息一分钟 
				collectTime=DateService.getDate();
				TxtFileUtil.appendToFile("start:"+collectTime+"\r\n",f);
				//取得当前一分钟的所有的心率
				setHeartRates();
				
				flag=true;
				
		}
		}
		}
	};
	Runnable run2 = new Runnable() {

		@Override
		public void run() {
			while (true) {
				
			
				if(flag){
					for(int i=0;i<heartRates.size();i++){
						TxtFileUtil.appendToFile(heartRates.get(i)+"\r\n",f);
						}
					//求出这一分钟的最佳心率
				    getBestHeartRate();
				
					flag=false;
				}
		}
		}
	};

	
	//开启线程
	public void startThread(){
	Thread thread = new Thread(run);
	Thread thread2 = new Thread(run2);
	thread.start();
	thread2.start();
	}
	
	//求最佳心率值
	public void getBestHeartRate(){
		 //将心率集合由小到大排序
		 Collections.sort(heartRates); 
		
       	 int sum = 0;
       	 //去掉最大心率和最小心率求出心率总和
   	     for(int i=1;i<heartRates.size()-1;i++){
   	            sum += heartRates.get(i).doubleValue();
   	     }
   	     //求出平均心率值
   	    int avg=sum/(heartRates.size()-2);
   	    TxtFileUtil.appendToFile("ave:"+avg+"\r\n",f);
   	    HeartRate heartRate=new HeartRate(avg, collectTime);
		//添加到分钟心率中
   	    minuteHeartRates.add(heartRate);
   	    //清空本分钟的所有心率集合
   	    heartRates.clear();
		
		
		
	}
}
