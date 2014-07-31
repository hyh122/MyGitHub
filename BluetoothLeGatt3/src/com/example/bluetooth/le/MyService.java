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
	//���һ���ӵ�ȫ����������
	private List<Integer> heartRates=new ArrayList<Integer>();;
	//���ÿһ���ӵ������������
	private List<HeartRate> minuteHeartRates=new ArrayList<HeartRate>();
	//ʱ��
	private Date collectTime;

	//�½��ļ�
	private File f=new File("/sdcard/HeartRates.txt"/**�ļ�·����**/);
	
	private boolean flag=false;
	
	//����ÿ���ӵ�ȫ���������ݼ���
	public void setHeartRates(){
		//ȡ�õ�ǰһ���ӵ�ȫ�������ʼ���
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
//	     UpdateTimer.schedule(task, 60000, 60000);//��һ�θ���ʱ��Ϊһ���ӣ�Ƶ��Ϊһ����
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
				}//��Ϣһ���� 
				collectTime=DateService.getDate();
				TxtFileUtil.appendToFile("start:"+collectTime+"\r\n",f);
				//ȡ�õ�ǰһ���ӵ����е�����
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
					//�����һ���ӵ��������
				    getBestHeartRate();
				
					flag=false;
				}
		}
		}
	};

	
	//�����߳�
	public void startThread(){
	Thread thread = new Thread(run);
	Thread thread2 = new Thread(run2);
	thread.start();
	thread2.start();
	}
	
	//���������ֵ
	public void getBestHeartRate(){
		 //�����ʼ�����С��������
		 Collections.sort(heartRates); 
		
       	 int sum = 0;
       	 //ȥ��������ʺ���С������������ܺ�
   	     for(int i=1;i<heartRates.size()-1;i++){
   	            sum += heartRates.get(i).doubleValue();
   	     }
   	     //���ƽ������ֵ
   	    int avg=sum/(heartRates.size()-2);
   	    TxtFileUtil.appendToFile("ave:"+avg+"\r\n",f);
   	    HeartRate heartRate=new HeartRate(avg, collectTime);
		//��ӵ�����������
   	    minuteHeartRates.add(heartRate);
   	    //��ձ����ӵ��������ʼ���
   	    heartRates.clear();
		
		
		
	}
}
