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
	//业务实体类
	private MinuteSportData minuteSportData;
	private OneSport oneSport;
	
	//数据服务类
	private DataCollectDataService dataService;
	//业务服务类
	private DataCollectService dataCollectService;
	
	private Thread clockThread;
	private Handler mHandler;
	//控制第几次运动
	private int i=1;
	//控制第几次分钟运动
	private int j=1;
	//设为静态
	private static final int firstPick=0;//提示开始跑步
	
	private static final int refreshPick=2;//提示更新(每分钟更新一次)
	private static final int endPick=3;//提示跑步结束
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
		  //在handler中接收信息,更新UI
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
//		            		 * 点击开始跑步后，立即实例化一个oneSport实体类，并记下开始时间，第几次运动，日期
//		            		 *同时实例化第一分钟的minuteSportData实体类,并记下采集时间，第几次，属于哪次运动数据
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
//		            		 * 过一分钟后，把第一分钟的心率和速度值添加进去，并存进数据库中，并实例化下一分钟的minuteSportData实体类
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
//		            		 * 假设这次只运动四分钟，则在四分钟后，记下onesport的endtime，并存入数据库中
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
		//实例化实体类和服务类
		
		
		dataService=new DataCollectDataService();
		dataCollectService=new DataCollectService();
		
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btn_startRun:
			
			
			
			
			
			
			 /* 线程体是Clock对象本身，线程名字为"Clock" */
		      clockThread = new Thread(new Runnable() {
		           @Override
		           public void run() {
		               while (true) {
		                   try {
		                	 /*
		                	  * 该线程的作用是每隔一分钟提醒一次，把一分钟内的运动数据添加到数据库中
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
		                       mHandler.sendMessage(message);//传递信息 
		                       Thread.currentThread().sleep(60000);//休息一分钟
		                       
		                	   /*
			                	  * 该线程的作用是每隔一分钟提醒一次，把一分钟内的运动数据添加到数据库中
			                	  */
			                       
//			                       Message message = new Message();
//			                       message.what=j;
//			                       mHandler.sendMessage(message);//传递信息 
//			                       Thread.currentThread().sleep(60000);//休息一分钟
//			                       j++;
//		                       
		                     
		                       
		                   
		                       
		                       
		                     
		                       
		                       //Log.e("test", "lost  time " + i);
		                   } catch (InterruptedException e) {
		                       e.printStackTrace();
		                   }
		               }
		           }

		       });

		       clockThread.start(); /* 启动线程 */
		       break;
		case R.id.btn_disData:
			int maxNum=dataService.getMaxSportNum(disDate.getDate());
			Log.e("hello", maxNum+"");
			tv_disData.setText(maxNum+"");
			
			break;

		}
		
	}
	
}
