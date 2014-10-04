package domain.dataCollect.heart;



import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.File.TxtFileUtil;

import domain.dataCollect.DataCollectService;

import foundation.ble.BLEService;

import foundation.ble.ITimeout;
import foundation.dataService.DataCollectDataService;



import android.content.Intent;



public class HeartRateService implements ITimeout{
	//�ļ�
  	private File file=new File("/sdcard/HeartRates.txt"/**�ļ�·����**/);
	private BLEService bleService=null;
	
	//���ÿһ���ӵ������������
	private List<HeartRateData> minuteHeartRates=null;
	
	private IHeartRateTimeOut listener=null;
	
	public void setListener(IHeartRateTimeOut listener) {
		this.listener = listener;
	 }
	
	
	public HeartRateService(List<HeartRateData> minuteHeartRates,
			IHeartRateTimeOut listener) {
		super();
		this.minuteHeartRates = minuteHeartRates;
		this.listener = listener;
	}



	

	public HeartRateService() {
		
		bleService=new BLEService();
		
		
		 minuteHeartRates=new ArrayList<HeartRateData>();
		 bleService.setLis(this);
		 
		 
	 }

	//��ʼ
	public void start(){
		bleService.work();
	}
	//�����ɼ����õ��������ʼ���
	public List<HeartRateData> stop(){
		//heartTimerTask.stop();
		
		List<HeartRateData> heartRateDataList=new ArrayList<HeartRateData>();
		heartRateDataList.addAll(minuteHeartRates);
		//minuteHeartRates.clear();
	  	return heartRateDataList;
		
		
	}
	//***set,get����****//
	


	public List<HeartRateData> getMinuteHeartRates() {
		return minuteHeartRates;
		
	}


	public void setMinuteHeartRates(List<HeartRateData> minuteHeartRates) {
		this.minuteHeartRates = minuteHeartRates;
	}
	
	//���������ֵ
	public HeartRateData getMinuteHeartRateData(Date collectTime,List<Integer> heartRates){
		HeartRateData heartRateData=null;
		int size=heartRates.size();
		
		//���ɼ�ʱ�䲻Ϊ��,�����ʼ��ϲ�Ϊ�ղŽ�������������
		if(collectTime!=null){
		 //��ȥ���������ֵ���ܺ�
		 int sum = 0;
		 //ȥƽ��ֵ
		 int avg=0;
		
		
		 if(size==0){
			 if(minuteHeartRates.size()==0){
				 avg=0;
			 }
			 else{
			 //��������û������ֵ�򿽱���һ���ӵ�����ֵ��Ϊ��һ���ӵ�����ֵ
			 avg=minuteHeartRates.get(minuteHeartRates.size()-1).getHeartRate();
			 }
		 }
		 else if(size==1){
			 avg=heartRates.get(0);
		 }else if(size==2){
			 sum=heartRates.get(0)+heartRates.get(1);
			 avg=sum/2;
		 }else{
			 int n1=size/8+1;//ȥ��������ֵ����
			 int n2=n1/2+1;//ȥ����ͷ�����±�
			 int n3=size-(n1-n2);//ȥ����β�����±�
			 
			 int lastSize=size-n1;//ȥ�������ʵ��ܸ���
		
			 for(int i=n2;i<n3;i++){
				 sum += heartRates.get(i).doubleValue();
		
			 }
			 //���ƽ������ֵ
			 avg=sum/lastSize;
		 }
		 
	
		 heartRateData=new HeartRateData(collectTime, avg);
		 
		}
		return heartRateData;
	}
		 
		 
	@Override
	public void onTimeout(Date collectTime,List<Integer> heartRates) {
		// TODO Auto-generated method stub
		
		
		List<Integer> LheartRates=new ArrayList<Integer>();
		LheartRates.addAll(heartRates);
		for(int i=0;i<LheartRates.size();i++){
		TxtFileUtil.appendToFile("onTimeout"+LheartRates.get(i)+"\r\n", file);
		}
		
		//�����һ���ӵ��������
		HeartRateData heartRateData=getMinuteHeartRateData(collectTime,LheartRates);
		
		//��ӵ�����������
		if(heartRateData!=null){
		minuteHeartRates.add(heartRateData);
		}
		
		//������һ������ֵ��֪ͨ�ϲ�
		listener.HOnTimeOut(heartRateData);
		
	}
}
		 
	
		
		

	

		 
		
		 
   	    
   	    
		
		
		




