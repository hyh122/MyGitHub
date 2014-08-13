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
//		1��SensorManager.SENSOR_DELAY_FASTEST
//		Ƶ��:95hz��105Hz֮��
//		2��SensorManager.SENSOR_DELAY_GAME
//		Ƶ��:49hz��51Hz֮��
//		3��SensorManager.SENSOR_DELAY_NORMAL
//		Ƶ��:5Hz
//		4��SensorManager.SENSOR_DELAY_UI
//		Ƶ��:16hz��18Hz֮��
		mySensorManager.registerListener(sl,sensor,SensorManager.SENSOR_DELAY_GAME);
//		�����洢����
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

//			�õ�ͨ�˲���������������ٶ�
			gravity[0]  = alpha * gravity[0] + (1 - alpha) * event.values[0];
			gravity[1]  = alpha * gravity[1] + (1 - alpha) * event.values[1];
			gravity[2]  = alpha * gravity[2] + (1 - alpha) * event.values[2];

//			�ø�ͨ�˲����޳���������
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

