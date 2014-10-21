package foundation.speed.acCollect;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class AcRootService extends Service {
	public SensorManager mySensorManager;
	public GravityBaseListener sl;
	private MyBinder myBinder = new MyBinder();
	@Override
	public void onCreate() {
		super.onCreate();
		startService();
		myOnCreate();
	}

	public void startService(){
		sl = new GravityBaseListener(this);
		mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		Sensor sensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//		1、SensorManager.SENSOR_DELAY_FASTEST
//		频率:95hz—105Hz之间
//		2、SensorManager.SENSOR_DELAY_GAME
//		频率:49hz—51Hz之间
//		3、SensorManager.SENSOR_DELAY_NORMAL
//		频率:5Hz
//		4、SensorManager.SENSOR_DELAY_UI
//		频率:16hz—18Hz之间
		mySensorManager.registerListener(sl,sensor,SensorManager.SENSOR_DELAY_GAME);
//		创建存储数据
	}
	public void endService() {
	    if (mySensorManager != null && sl != null) {
	    	mySensorManager.unregisterListener(sl);
			sl = null;
			mySensorManager = null;
	    }
	}
	@Override
	public void onDestroy() {
		endService();
		myOnDestroy();
		super.onDestroy();
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return myBinder;
	}
	public class GravityBaseListener implements SensorEventListener {
		public AcRootService father;
		public double ax = 0,ay = 0,az = 0;
	    public float gravity[] = new float[3];
		public GravityBaseListener(AcRootService father) {
			this.father = father;
		}
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			
		}
		@Override
		public void onSensorChanged(SensorEvent event) {
			float alpha = (float) 0.8;

//			用低通滤波器分离出重力加速度
			gravity[0]  = alpha * gravity[0] + (1 - alpha) * event.values[0];
			gravity[1]  = alpha * gravity[1] + (1 - alpha) * event.values[1];
			gravity[2]  = alpha * gravity[2] + (1 - alpha) * event.values[2];

//			用高通滤波器剔除重力干扰
			ax = ((double)(event.values[0] - gravity[0]));
			ay = ((double)(event.values[1] - gravity[1]));
			az = ((double)(event.values[2] - gravity[2]));
			updateData(ax, ay, az);
		}
	}
    public class MyBinder extends Binder {
    	public AcRootService getService() {
            return AcRootService.this;
        }
    }
	public void updateData(double ax, double ay, double az) {
		// TODO Auto-generated method stub
	}

	public void myOnCreate() {
		// TODO Auto-generated method stub
		
	}
	public void myOnDestroy() {
		// TODO Auto-generated method stub
		
	}
}

