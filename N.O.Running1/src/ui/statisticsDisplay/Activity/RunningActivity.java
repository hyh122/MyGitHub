package ui.statisticsDisplay.Activity;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.File.TxtFileUtil;

import com.example.androidui_sample_demo.R;


import domain.dataCollect.CurrentSportData;
import domain.dataCollect.DataCollectService;
import domain.dataCollect.IDataCollectTimeOut;
import foundation.ble.BLEService;
import foundation.dataService.DataCollectDataService;
import foundation.dataService.util.DateService;

@SuppressWarnings({ "unused", "unused" })
public class RunningActivity extends Activity implements IDataCollectTimeOut{
	private Timer UpdateTimer;//更新时间表
	private Button btn_finish_running;
	private Chronometer timer;//界面计时器
	private TextView tv_heartRates,tv_speeds,tv_distance,tv_calorie;

	//业务服务类
	private  DataCollectService dataCollectService;
	 //文件
	private File f;
	
	private Handler mainHandler;
	private TextView timeLable;
	
	private CurrentSportData curSportData=new CurrentSportData();
    private Handler mHandler;
    private Thread clockThread;
	
	public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";
	private String mDeviceAddress;
	private BLEService mBluetoothLeService;
	
	 // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BLEService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
              
                finish();
            }
            // Automatically connects to the device upon successful start-up initialization.
            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };
    
	//private CurrentSportData currentSportData;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_running);
		//新建文件
		 f=new File("/sdcard/HeartRates.txt"/**文件路径名**/);
		//创建文件
		try {
			TxtFileUtil.createFile(f);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final Intent intent = getIntent();
       
        mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);

       
       

   
        //启动BluetoothLeService
        Intent gattServiceIntent = new Intent(this, BLEService.class);
        //将该activity与BluetoothLeService绑定
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
        dataCollectService=new DataCollectService();
		dataCollectService.setDataCollectTimeOut(this);//设置监听者为UI
		
		btn_finish_running=(Button) findViewById(R.id.btn_finish_running);
		tv_heartRates=(TextView) findViewById(R.id.tv_Heartrates);
		tv_speeds=(TextView) findViewById(R.id.tv_speeds);
		tv_distance=(TextView) findViewById(R.id.tv_distance);
		tv_calorie=(TextView) findViewById(R.id.tv_calorie);
		
		timer=(Chronometer) findViewById(R.id.timer);
	    timer.setBase(SystemClock.elapsedRealtime());
		timer.start();//界面计时器开始
		
		initModel();//初始化模型

	   /**
	    * 结束运动
	    */ 
		btn_finish_running.setOnClickListener(new OnClickListener() {	
		public void onClick(View arg0) {
			try{
				dataCollectService.stop(DateService.getDate());
			}
			catch(Exception e){
				
			}
			RunningActivity.this.finish();
		}
	});
		
		//在handler中接收信息,更新UI
	    mHandler = new Handler(){  
	              public void handleMessage(Message msg) {
	            	  updateView(curSportData);
	          	}
		            
        };  
    
        /* 线程体是Clock对象本身，线程名字为"Clock" */
	      clockThread = new Thread(new Runnable() {
	           @Override
	           public void run() {
	            
	                   /*
					  * 该线程的作用是每隔一分钟提醒一次，把一分钟内的运动数据添加到数据库中
					  */
					   Message message = new Message();
					   mHandler.sendMessage(message);//传递信息 
					   
	           }
	           
	      });
	      
	      clockThread.start(); /* 启动线程 */
					
					 
					  
}
	/**
	 * 初始模型
	 */
	protected void initModel(){
		tv_heartRates.setText("0");
		tv_speeds.setText("0");
		tv_distance.setText("0");
		tv_calorie.setText("0");	
	}
	 //更新UI
	protected void updateView(CurrentSportData currentSportData){
		tv_heartRates.setText(currentSportData.getCurrentHeartRate()+"");
		tv_speeds.setText(currentSportData.getCurrentSpeed()+"");
		tv_calorie.setText(currentSportData.getTotalCalorie()+"");
		tv_distance.setText(currentSportData.getDistance()+"");
	}
	

	@Override
	public void DOnTimeOut(CurrentSportData currentSportData) {
		// 更新UI
		curSportData=currentSportData;
		clockThread.run();
		
	}
	@Override
    protected void onResume() {
        super.onResume();
       // registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
           
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindService(mServiceConnection);
        mBluetoothLeService = null;

    }
}
