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
import android.os.IBinder;
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
import com.tools.DateService;

/**
 * For a given BLE device, this Activity provides the user interface to connect, display data,
 * and display GATT services and characteristics supported by the device.  The Activity
 * communicates with {@code BluetoothLeService}, which in turn interacts with the
 * Bluetooth LE API.
 */
public class DeviceControlActivity extends Activity implements OnClickListener{
    private final static String TAG = DeviceControlActivity.class.getSimpleName();
    private Timer UpdateTimer;//����ʱ���
    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";
    //�ļ�
	private File f;
  
    private String mDeviceName;
    private String mDeviceAddress;

    private BluetoothLeService mBluetoothLeService;
    
    private Button btn_displayHeartRates;
    private TextView tv_displayHeartRates;
    private MyService service;


    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";
    
    //���ʷ����uuid
    public final static UUID UUID_HEART_RATE_SERVICE=
            UUID.fromString(SampleGattAttributes.HEART_RATE_SERVICE);
    //���ʲ�����UUID
    public final static UUID UUID_HEART_RATE_MEASUREMENT =
            UUID.fromString(SampleGattAttributes.HEART_RATE_MEASUREMENT);
    
    // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
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
        //�½��ļ�
		 f=new File("/sdcard/HeartRates.txt"/**�ļ�·����**/);
		//�����ļ�
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
        //����BluetoothLeService
        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        //����activity��BluetoothLeService��
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
        
        service=new MyService();
       // service.startThread();
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
        unbindService(mServiceConnection);
        mBluetoothLeService = null;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btn_displayHeartRates:
			TxtFileUtil.appendToFile("���"+"\r\n",f);
			//ȡ�õ�ǰ�׶ε����ʼ���
//			List<Integer> HeartRates=service.getHeartRates();
//			for(int i=0;i<HeartRates.size();i++){
//			TxtFileUtil.appendToFile(HeartRates.get(i)+"\r\n",f);
//			}
//			//������ʼ���
//			service.clearHeartRates();
			break;
		}
		
	}

 
  


}
