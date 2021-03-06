/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.bluetooth.le;

import android.app.Activity;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import com.File.TxtFileUtil;
import com.entity.HeartRate;
import com.tools.DateService;

/**
 * For a given BLE device, this Activity provides the user interface to connect, display data,
 * and display GATT services and characteristics supported by the device.  The Activity
 * communicates with {@code BluetoothLeService}, which in turn interacts with the
 * Bluetooth LE API.
 */
public class DeviceControlActivity extends Activity implements OnClickListener,IHeartRateTimeOut{
    private final static String TAG = DeviceControlActivity.class.getSimpleName();
  
    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";
    //文件
	private File f;
  
    private String mDeviceName;
    private String mDeviceAddress;

    private BLEService mBluetoothLeService;
    
    private Button btn_displayHeartRates;
    private TextView tv_displayHeartRates;
    private HeartRateService service;
    
    private HeartRate heartRateData=new HeartRate();
    private Handler mHandler;
    private Thread clockThread;
    
    // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BLEService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
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



   

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gatt_services_characteristics);
        //新建文件
		 f=new File("/sdcard/HeartRates.txt"/**文件路径名**/);
		//创建文件
		try {
			TxtFileUtil.createFile(f);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btn_displayHeartRates=(Button) findViewById(R.id.btn_displayHeartRates);
		btn_displayHeartRates.setOnClickListener(this);
		tv_displayHeartRates=(TextView) findViewById(R.id.tv_displayHeartRates);
        
        
		final Intent intent = getIntent();
        mDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
        mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);

       
       

        getActionBar().setTitle(mDeviceName);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        //启动BluetoothLeService
        Intent gattServiceIntent = new Intent(this, BLEService.class);
        //将该activity与BluetoothLeService绑定
        
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
        
        service=new HeartRateService();
        service.start();
        service.setListener(this);
        
        
        //在handler中接收信息,更新UI
	    mHandler = new Handler(){  
	              public void handleMessage(Message msg) {
	            	  tv_displayHeartRates.setText(heartRateData.getHeartRate()+"");
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
	                       
                  
	                       
	                   
	                       
	                       
	                     
	                       

    @Override
    protected void onResume() {
        super.onResume();
       // registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null) {
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBluetoothLeService!=null){
        unbindService(mServiceConnection);
        mBluetoothLeService = null;
       }
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btn_displayHeartRates:
		//	TxtFileUtil.appendToFile("点击"+"\r\n",f);
//			unbindService(mServiceConnection);
//			mBluetoothLeService = null;
			//取得当前阶段的心率集合
//			List<Integer> HeartRates=service.getHeartRates();
//			for(int i=0;i<HeartRates.size();i++){
//			TxtFileUtil.appendToFile(HeartRates.get(i)+"\r\n",f);
//			}
//			//清空心率集合
//			service.clearHeartRates();
			service.stop();
			unbindService(mServiceConnection);
			mBluetoothLeService = null;
			break;
		
		}
		
	}

	@Override
	public void HOnTimeOut(HeartRate heartRateData) {
		// TODO Auto-generated method stub
		
		this.heartRateData=heartRateData;
		clockThread.run();
	}

 
  


}
