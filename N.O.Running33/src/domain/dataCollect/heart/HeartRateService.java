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
	//���һ���ӵ�ȫ����������
	private List<Integer> heartRates=new ArrayList<Integer>();;
	//���ÿһ���ӵ������������
	private List<HeartRateData> minuteHeartRates=new ArrayList<HeartRateData>();
	//ʱ��
	private Date collectTime;
	//�½��ļ�
	private File f=new File("/sdcard/HeartRates.txt"/**�ļ�·����**/);
	
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
	
	//����ÿ���ӵ�ȫ���������ݼ���
	public void setHeartRates(){
		//ȡ�õ�ǰһ���ӵ�ȫ�������ʼ���
		heartRates.addAll(BLEService.getHeartRates());
	}
	//���������ֵ
	public void setBestHeartRate(){
		 //��ȥ���������ֵ���ܺ�
		 int sum = 0;
		 //ȥƽ��ֵ
		 int avg;
		 int size=heartRates.size();
		
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
			 TxtFileUtil.appendToFile("lastSize:"+lastSize+"\r\n",f);
			 for(int i=n2;i<n3;i++){
				 sum += heartRates.get(i).doubleValue();
				 TxtFileUtil.appendToFile("2."+heartRates.get(i)+"\r\n",f);
			 }
			 //���ƽ������ֵ
			 avg=sum/lastSize;
		 }
		 
		 TxtFileUtil.appendToFile("ave:"+avg+"\r\n",f);
		 HeartRateData heartRate=new HeartRateData(collectTime, avg);
		 
		 //������һ������ֵ��֪ͨ�ϲ�
		 listener.HOnTimeOut(heartRate);
		 
		 //��ӵ�����������
		 minuteHeartRates.add(heartRate);
		 //��ձ����ӵ��������ʼ���
		 heartRates.clear();
		 
	}
	@Override
	public void onTimeout(Date collectTime) {
		// TODO Auto-generated method stub
		if(flag==0){
		this.collectTime=collectTime;
		TxtFileUtil.appendToFile("start:"+this.collectTime+"\r\n",f);
		//ȡ�õ�ǰһ���ӵ����е�����
		setHeartRates();
		TxtFileUtil.appendToFile("size:"+heartRates.size()+"\r\n",f);
		
		for(int i=0;i<heartRates.size();i++){
			TxtFileUtil.appendToFile(heartRates.get(i)+"\r\n",f);
		}
		//�����һ���ӵ��������
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

	

		 
		
		 
   	    
   	    
		
		
		




