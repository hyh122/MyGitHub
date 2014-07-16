package ui.dataCollectDemo;

import com.example.datacollectdemo.R;

import domain.entity.MinuteSportData;
import domain.entity.OneSport;
import domain.service.DataCollectService;
import domain.service.disDate;
import foundation.dataService.DataCollectDataService;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	private TextView tv_disData;
	private Button btn_startRun,btn_disData;
	//
	//ҵ��ʵ����
	private MinuteSportData minuteSportData;
	private OneSport oneSport;
	
	//���ݷ�����
	private DataCollectDataService dataService;
	//ҵ�������
	private DataCollectService dataCollectService;
	
	private Thread clockThread;
	private Handler mHandler;
	//���Ƶڼ����˶�
	private int i=1;
	//���Ƶڼ��η����˶�
	private int j=1;
	//��Ϊ��̬
	private static final int firstPick=0;//��ʾ��ʼ�ܲ�
	
	private static final int refreshPick=2;//��ʾ����(ÿ���Ӹ���һ��)
	private static final int endPick=3;//��ʾ�ܲ�����
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
		  //��handler�н�����Ϣ,����UI
		    mHandler = new Handler(){  
		              public void handleMessage(Message msg) {
		            	switch(msg.what){
		            	case firstPick:
		            		oneSport=new OneSport();
		            		oneSport.setId(i);
		            		oneSport.setCount(i);
		        			oneSport.setDate(disDate.getDate());
		        			oneSport.setStartTime(disDate.getDate2());
		        			
		        			minuteSportData=new MinuteSportData();
		            		minuteSportData.setNumber(j);
		        			minuteSportData.setCollectTime(disDate.getDate2());
		        			minuteSportData.setOneSport(oneSport);
		            		j++;
		            		break;
		           
		            	case refreshPick:
		            		
		            		minuteSportData.setHeartRate(70);
		            		minuteSportData.setSpeed(3.2);
		            		dataService.addMinuteSportData(minuteSportData);
		            		

		            		minuteSportData=new MinuteSportData();
		            		minuteSportData.setNumber(j);
		        			minuteSportData.setCollectTime(disDate.getDate2());
		        			minuteSportData.setOneSport(oneSport);
		        			j++;
		            		
		            		break;
		            	case endPick:
		            		minuteSportData.setHeartRate(60);
		            		minuteSportData.setSpeed(2.2);
		            		dataService.addMinuteSportData(minuteSportData);
		            		
		            		oneSport.setEndTime(disDate.getDate2());
		            		dataService.addOneSportData(oneSport);
		            		i++;
		            		j=1;
		            		
		            		break;
//		            	case 1:
//		            		/*
//		            		 * �����ʼ�ܲ�������ʵ����һ��oneSportʵ���࣬�����¿�ʼʱ�䣬�ڼ����˶�������
//		            		 *ͬʱʵ������һ���ӵ�minuteSportDataʵ����,�����²ɼ�ʱ�䣬�ڼ��Σ������Ĵ��˶�����
//		            		 */
//		            		oneSport=new OneSport();
//		            		oneSport.setId(1);
//		            		oneSport.setCount(1);
//		        			oneSport.setDate(disDate.getDate());
//		        			oneSport.setStartTime(disDate.getDate2());
//		            		minuteSportData=new MinuteSportData();
//		            		minuteSportData.setNumber(j);
//		        			minuteSportData.setCollectTime(disDate.getDate2());
//		        			minuteSportData.setOneSport(oneSport);
//		        			break;
//		            	case 2:
//		            		/*
//		            		 * ��һ���Ӻ󣬰ѵ�һ���ӵ����ʺ��ٶ�ֵ��ӽ�ȥ����������ݿ��У���ʵ������һ���ӵ�minuteSportDataʵ����
//		            		 */
//		            		minuteSportData.setHeartRate(70);
//		            		minuteSportData.setSpeed(3.2);
//		            		dataService.addMinuteSportData(minuteSportData);
//		            		minuteSportData=new MinuteSportData();
//		            		minuteSportData.setNumber(j);
//		        			minuteSportData.setCollectTime(disDate.getDate2());
//		        			minuteSportData.setOneSport(oneSport);
//		        			break;
//		            	case 3:
//		            		minuteSportData.setHeartRate(60);
//		            		minuteSportData.setSpeed(2.2);
//		            		dataService.addMinuteSportData(minuteSportData);
//		            		minuteSportData=new MinuteSportData();
//		            		minuteSportData.setNumber(j);
//		        			minuteSportData.setCollectTime(disDate.getDate2());
//		        			minuteSportData.setOneSport(oneSport);
//		        			break;
//		            	case 4:
//		            		minuteSportData.setHeartRate(70);
//		            		minuteSportData.setSpeed(3.2);
//		            		dataService.addMinuteSportData(minuteSportData);
//		            		/*
//		            		 * �������ֻ�˶��ķ��ӣ������ķ��Ӻ󣬼���onesport��endtime�����������ݿ���
//		            		 */
//		            		oneSport.setEndTime(disDate.getDate2());
//		            		dataService.addOneSportData(oneSport);
//		            		break;
		            	}
		            
		              };  
		          };
		   
	}
	private void init(){
		tv_disData=(TextView) findViewById(R.id.tv_disData);
		btn_startRun=(Button) findViewById(R.id.btn_startRun);
		btn_startRun.setOnClickListener(this);
		btn_disData=(Button) findViewById(R.id.btn_disData);
		btn_disData.setOnClickListener(this);
		//ʵ����ʵ����ͷ�����
		
		
		dataService=new DataCollectDataService();
		dataCollectService=new DataCollectService();
		
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btn_startRun:
			
			
			
			
			
			
			 /* �߳�����Clock�������߳�����Ϊ"Clock" */
		      clockThread = new Thread(new Runnable() {
		           @Override
		           public void run() {
		               while (true) {
		                   try {
		                	 /*
		                	  * ���̵߳�������ÿ��һ��������һ�Σ���һ�����ڵ��˶�������ӵ����ݿ���
		                	  */
		                	   Message message = new Message();
		                	   if(i<=3){
		                       if(j==1){
		                        message.what=firstPick;
		                       }else if(j==4){
		                         message.what=endPick;
		                       }else{
		                    	   message.what=refreshPick;
		                       }
		                	   }
		                       mHandler.sendMessage(message);//������Ϣ 
		                       Thread.currentThread().sleep(60000);//��Ϣһ����
		                       
		                	   /*
			                	  * ���̵߳�������ÿ��һ��������һ�Σ���һ�����ڵ��˶�������ӵ����ݿ���
			                	  */
			                       
//			                       Message message = new Message();
//			                       message.what=j;
//			                       mHandler.sendMessage(message);//������Ϣ 
//			                       Thread.currentThread().sleep(60000);//��Ϣһ����
//			                       j++;
//		                       
		                     
		                       
		                   
		                       
		                       
		                     
		                       
		                       //Log.e("test", "lost  time " + i);
		                   } catch (InterruptedException e) {
		                       e.printStackTrace();
		                   }
		               }
		           }

		       });

		       clockThread.start(); /* �����߳� */
		       break;
		case R.id.btn_disData:
			int maxNum=dataService.getMaxSportNum(disDate.getDate());
			Log.e("hello", maxNum+"");
			tv_disData.setText(maxNum+"");
			
			break;

		}
		
	}
	
}
