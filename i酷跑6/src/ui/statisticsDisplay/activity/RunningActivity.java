package ui.statisticsDisplay.activity;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.File.TxtFileUtil;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.Geometry;
import com.baidu.mapapi.map.Graphic;
import com.baidu.mapapi.map.GraphicsOverlay;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.Symbol;
import com.baidu.mapapi.map.Symbol.Color;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.example.androidui_sample_demo.R;

import domain.dataCollect.CurrentSportData;
import domain.dataCollect.DataCollectService;
import domain.dataCollect.IDataCollectTimeOut;
import foundation.ble.BLEService;
import foundation.dataService.DataCollectDataService;
import foundation.dataService.util.DateService;
import foundation.speed.acCollect.AcBaseService;
import foundation.speed.acCollect.AcRootService.MyBinder;

@SuppressWarnings({ "unused", "unused" })
public class RunningActivity extends Activity implements IDataCollectTimeOut {

	private Button btn_finish_running;
	private Chronometer timer;// �����ʱ��
	private TextView tv_heartRates, tv_speeds, tv_distance, tv_calorie;
	private boolean speedFlag = false;
	// ҵ�������
	private DataCollectService dataCollectService;
	// �ļ�
	private File f;

	private Handler mainHandler;
	private TextView timeLable;

	private CurrentSportData curSportData = new CurrentSportData();
	private Handler mHandler;
	private Thread clockThread;
	// private AcBaseService mAcCollectService;
	public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";
	private String mDeviceAddress;
	private BLEService mBluetoothLeService;

	private Toast mToast;
	private BMapManager mBMapManager;
	private MapView mMapView = null;
	private MapController mMapController = null;
	LocationListener mLocationListener = null;
	private LocationClient mLocClient;
	private LocationData mLocData;
	// ��λͼ��
	private LocationOverlay myLocationOverlay = null;

	private boolean isRequest = false;// �Ƿ��ֶ���������λ
	private boolean isFirstLoc = true;// �Ƿ��״ζ�λ
	
	protected CountDownTimer brewCountDownTimer;
	public MediaPlayer mediaPlayer = new MediaPlayer();

	// private PopupOverlay mPopupOverlay = null;// ��������ͼ�㣬����ڵ�ʱʹ��
	// private View viewCache;
	private BDLocation location;

	// Code to manage Service lifecycle.
	private final ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName componentName,
				IBinder service) {
			mBluetoothLeService = ((BLEService.LocalBinder) service)
					.getService();
			if (!mBluetoothLeService.initialize()) {

				finish();
			}
			// Automatically connects to the device upon successful start-up
			// initialization.
			mBluetoothLeService.connect(mDeviceAddress);
		}

		@Override
		public void onServiceDisconnected(ComponentName componentName) {
			mBluetoothLeService = null;
		}
	};

	// private CurrentSportData currentSportData;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// ʹ�õ�ͼsdkǰ���ȳ�ʼ��BMapManager�����������setContentView()ǰ�ȳ�ʼ��
		mBMapManager = new BMapManager(this);

		// ��һ��������API key,
		// �ڶ��������ǳ����¼���������������ͨ�������������Ȩ��֤����ȣ���Ҳ���Բ��������ص��ӿ�
		mBMapManager.init("VcqTz3XpXz8413haxdvLM39F",
				new MKGeneralListenerImpl());

		setContentView(R.layout.activity_running);
		// �½��ļ�
		f = new File("/sdcard/HeartRates.txt"/** �ļ�·���� **/
		);
		// �����ļ�
		try {
			TxtFileUtil.createFile(f);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final Intent intent = getIntent();

		mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);

		// ����BluetoothLeService
		Intent gattServiceIntent = new Intent(this, BLEService.class);
		// ����activity��BluetoothLeService��
		bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
		dataCollectService = new DataCollectService();// ʵ����DataCollectService
		dataCollectService.setDataCollectTimeOut(this);// ���ü�����ΪUI
		btn_finish_running = (Button) findViewById(R.id.btn_finish_running);
		tv_heartRates = (TextView) findViewById(R.id.tv_Heartrates);
		tv_speeds = (TextView) findViewById(R.id.tv_speeds);
		tv_distance = (TextView) findViewById(R.id.tv_distance);
		tv_calorie = (TextView) findViewById(R.id.tv_calorie);

		timer = (Chronometer) findViewById(R.id.timer);
		timer.setBase(SystemClock.elapsedRealtime());
		bindSpeedService();
		dataCollectService.start(DateService.getDate());// ������ʼ�ܲ�
		timer.start();// �����ʱ����ʼ
		startBrew();
		initModel();// ��ʼ��ģ��

		/**
		 * �����˶�
		 */
		btn_finish_running.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				unBindSpeedService();
				try {
					dataCollectService.stop(DateService.getDate());
				} catch (Exception e) {

				}
				RunningActivity.this.finish();
			}
		});

		// ��handler�н�����Ϣ,����UI
		mHandler = new Handler() {
			public void handleMessage(Message msg) {
				updateView(curSportData);
			}

		};

		mMapView = (MapView) findViewById(R.id.bmapView); // ��ȡ�ٶȵ�ͼ�ؼ�ʵ��
		mMapController = mMapView.getController(); // ��ȡ��ͼ������
		mMapController.enableClick(true); // ���õ�ͼ�Ƿ���Ӧ����¼�
		mMapController.setZoom(18); // ���õ�ͼ���ż���
		mMapView.setBuiltInZoomControls(true); // ��ʾ�������ſؼ�

		mLocData = new LocationData();

		// ʵ������λ����LocationClient����������߳�������
		mLocClient = new LocationClient(getApplicationContext());

		/**
		 * ���ö�λ����
		 */
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // ��GPRS
		option.setAddrType("all");// ���صĶ�λ���������ַ��Ϣ
		option.setCoorType("bd09ll");// ���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02
		option.setScanSpan(2000); // ���÷���λ����ļ��ʱ��Ϊ2000ms
		option.disableCache(false);// ��ֹ���û��涨λ

		mLocClient.setLocOption(option);
		mLocClient.start(); // ���ô˷�����ʼ��λ

		// ��λͼ���ʼ��
		myLocationOverlay = new LocationOverlay(mMapView);
		// ���ö�λ����
		myLocationOverlay.setData(mLocData);

		// myLocationOverlay.setMarker(getResources().getDrawable(R.drawable.location_arrows));

		// ��Ӷ�λͼ��
		mMapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableCompass();

		// ע��λ�ü�����
		mLocClient.registerLocationListener(new BDLocationListener() {

			@Override
			public void onReceiveLocation(BDLocation locationChange) {
				// TODO Auto-generated method stub
				if (locationChange == null) {
					return;
				}
				if (location == null) {
					location = locationChange;
					mLocData.latitude = location.getLatitude();
					mLocData.longitude = location.getLongitude();
					// �������ʾ��λ����Ȧ����accuracy��ֵΪ0����
					mLocData.accuracy = location.getRadius();
					mLocData.direction = location.getDerect();

					// ����λ�������õ���λͼ����
					myLocationOverlay.setData(mLocData);
					// ����ͼ������ִ��ˢ�º���Ч
					mMapView.refresh();

					mMapController.animateTo(new GeoPoint((int) (location
							.getLatitude() * 1e6), (int) (location
							.getLongitude() * 1e6)));
				}
				GraphicsOverlay graphicsOverlay = new GraphicsOverlay(mMapView);
				mMapView.getOverlays().add(graphicsOverlay);
				graphicsOverlay.setData(drawLine(locationChange.getLatitude(),
						locationChange.getLongitude(), location.getLatitude(),
						location.getLongitude()));
				mMapView.refresh();
				location = locationChange;
			}

			@Override
			public void onReceivePoi(BDLocation location) {
			}

		});

		mLocationListener = new LocationListener() {

			@Override
			public void onLocationChanged(Location arg0) {
				// TODO Auto-generated method stub
				if (location != null) {
					// �������
					// showToast("locationChanged " + arg0.getAltitude() + ":"
					// + arg0.getLongitude());
					GraphicsOverlay graphicsOverlay = new GraphicsOverlay(
							mMapView);
					mMapView.getOverlays().add(graphicsOverlay);
					graphicsOverlay.setData(drawLine(arg0.getLatitude(),
							arg0.getLongitude(), location.getLatitude(),
							location.getLongitude()));
					mMapView.refresh();
				}
			}

			@Override
			public void onProviderDisabled(String arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderEnabled(String arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub

			}

		};

		// �޸Ķ�λ���ݺ�ˢ��ͼ����Ч
		mMapView.refresh();

	}

	// /* �߳�����Clock�������߳�����Ϊ"Clock" */
	// clockThread = new Thread(new Runnable() {
	// @Override
	// public void run() {
	// /*
	// * ���̵߳�������ÿ��һ��������һ��
	// */
	// Message message = new Message();
	// mHandler.sendMessage(message);//������Ϣ
	// }
	//
	// });
	// clockThread.start(); /* �����߳� */
	//
	// }
	/**
	 * ��ʼģ��
	 */
	private void bindSpeedService() {
		Intent it = new Intent(RunningActivity.this, AcBaseService.class);
		bindService(it, conn, Context.BIND_AUTO_CREATE);
	}

	private void unBindSpeedService() {
		if (speedFlag == true) {
			unbindService(conn);
			speedFlag = false;
		}
	}

	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// mAcCollectService = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			MyBinder binder = (MyBinder) service;
			// mAcCollectService = (AcBaseService)binder.getService();
			speedFlag = true;
		}
	};

	protected void initModel() {
		tv_heartRates.setText("0");
		tv_speeds.setText("0");
		tv_distance.setText("0");
		tv_calorie.setText("0");
	}

	// ����UI
	protected void updateView(CurrentSportData currentSportData) {
		tv_heartRates.setText(currentSportData.getCurrentHeartRate() + "");
		tv_speeds.setText(currentSportData.getCurrentSpeed() + "");
		tv_calorie.setText(currentSportData.getTotalCalorie() + "");
		tv_distance.setText(currentSportData.getDistance() + "");
	}

	@Override
	public void DOnTimeOut(CurrentSportData currentSportData) {
		// ����UI
		this.curSportData = currentSportData;
		// clockThread.run();
		Message message = new Message();
		mHandler.sendMessage(message);// ������Ϣ
	}

	@Override
	protected void onResume() {
		// MapView������������Activityͬ������activity����ʱ�����MapView.onPause()
		mMapView.onResume();
		super.onResume();
		// registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
		if (mBluetoothLeService != null) {
			final boolean result = mBluetoothLeService.connect(mDeviceAddress);

		}
	}

	@Override
	protected void onPause() {
		// MapView������������Activityͬ������activity����ʱ�����MapView.onPause()
		mMapView.onPause();
		super.onPause();

	}

	@Override
	protected void onDestroy() {
		// MapView������������Activityͬ������activity����ʱ�����MapView.destroy()
		mMapView.destroy();

		// �˳�Ӧ�õ���BMapManager��destroy()����
		if (mBMapManager != null) {
			mBMapManager.destroy();
			mBMapManager = null;
		}

		// �˳�ʱ���ٶ�λ
		if (mLocClient != null) {
			mLocClient.stop();
		}
		super.onDestroy();

		unbindService(mServiceConnection);
		mBluetoothLeService = null;
		
		stopBrew();
		mediaPlayer.stop();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			// Intent intent = new Intent(this, StartrunningActivity.class);
			// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// startActivity(intent);
			// return true;

		}
		return super.onOptionsItemSelected(item);

	}
	
	/** * �������ߣ�������״̬���ͼ״̬�仯 * * @return ���߶��� */
	public Graphic drawLine(double mLat1, double mLon1, double mLat2,
			double mLon2) {
		int lat = (int) (mLat1 * 1E6);
		int lon = (int) (mLon1 * 1E6);
		GeoPoint pt1 = new GeoPoint(lat, lon);
		lat = (int) (mLat2 * 1E6);
		lon = (int) (mLon2 * 1E6);
		GeoPoint pt2 = new GeoPoint(lat, lon);
		// ������
		Geometry lineGeometry = new Geometry();
		// �趨���ߵ�����
		GeoPoint[] linePoints = new GeoPoint[2];
		linePoints[0] = pt1;
		linePoints[1] = pt2;
		lineGeometry.setPolyLine(linePoints);
		// �趨��ʽ
		Symbol lineSymbol = new Symbol();
		Symbol.Color lineColor = lineSymbol.new Color();
		lineColor.red = 255;
		lineColor.green = 0;
		lineColor.blue = 0;
		lineColor.alpha = 255;
		lineSymbol.setLineSymbol(lineColor, 5);
		// ����Graphic����
		Graphic lineGraphic = new Graphic(lineGeometry, lineSymbol);
		return lineGraphic;
	}

	/**
	 * ��λ�ӿڣ���Ҫʵ����������
	 * 
	 * 
	 * 
	 */
	public class BDLocationListenerImpl implements BDLocationListener {

		/**
		 * �����첽���صĶ�λ�����������BDLocation���Ͳ���
		 */
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null) {
				return;
			}

			RunningActivity.this.location = location;

			mLocData.latitude = location.getLatitude();
			mLocData.longitude = location.getLongitude();
			// �������ʾ��λ����Ȧ����accuracy��ֵΪ0����
			mLocData.accuracy = location.getRadius();
			mLocData.direction = location.getDerect();

			// ����λ�������õ���λͼ����
			myLocationOverlay.setData(mLocData);
			// ����ͼ������ִ��ˢ�º���Ч
			mMapView.refresh();

			if (isFirstLoc || isRequest) {
				mMapController.animateTo(new GeoPoint((int) (location
						.getLatitude() * 1e6),
						(int) (location.getLongitude() * 1e6)));

				// showPopupOverlay(location);

				isRequest = false;
			}

			isFirstLoc = false;
		}

		/**
		 * �����첽���ص�POI��ѯ�����������BDLocation���Ͳ���
		 */
		@Override
		public void onReceivePoi(BDLocation poiLocation) {

		}

	}

	/**
	 * �����¼���������������ͨ�������������Ȩ��֤�����
	 * 
	 * 
	 */
	public class MKGeneralListenerImpl implements MKGeneralListener {

		/**
		 * һЩ����״̬�Ĵ�����ص�����
		 */
		@Override
		public void onGetNetworkState(int iError) {
			if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
				showToast("���������������");
			}
		}

		/**
		 * ��Ȩ�����ʱ����õĻص�����
		 */
		@Override
		public void onGetPermissionState(int iError) {
			if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
				showToast("API KEY����, ���飡");
			}
		}

	}

	private class LocationOverlay extends MyLocationOverlay {

		public LocationOverlay(MapView arg0) {
			super(arg0);
		}

		@Override
		protected boolean dispatchTap() {
			// showPopupOverlay(location);
			return super.dispatchTap();
		}

		@Override
		public void setMarker(Drawable arg0) {
			super.setMarker(arg0);
		}

	}

	/**
	 * ��ʾToast��Ϣ
	 * 
	 * @param msg
	 */
	private void showToast(String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(msg);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	/**
	 * 
	 * @param view
	 * @return
	 */
	public static Bitmap getBitmapFromView(View view) {
		view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		view.buildDrawingCache();
		Bitmap bitmap = view.getDrawingCache();
		return bitmap;
	}
	
	/*
	 * ��ʼ��ʱ
	 */
public void startBrew() {
		

		Bundle bundle=this.getIntent().getExtras();
		int brewTime=bundle.getInt("time");
		// Create a new CountDownTimer to track the brew time
		brewCountDownTimer = new CountDownTimer(brewTime * 60 * 1000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
//				brewTimeLabel.setText(String
//						.valueOf(millisUntilFinished / 1000) + "s");
			}

			@Override
			public void onFinish() {
				//isBrewing = false;
				// setBrewCount(brewCount + 1);

				// brewTimeLabel.setText("Brew Up!");
				
				
				unBindSpeedService();
				try {
					dataCollectService.stop(DateService.getDate());
				} catch (Exception e) {

				}
				new Handler().postDelayed(new Runnable(){    
				    public void run() {    
				    
				    	timer.stop();   
				    }    
				 }, 1000);  
				Session session=Session.getSession();
				session.put("player",mediaPlayer);
				
				ring();
				stopBrew();
				//startBrew.setText("Start");
				Intent intent=new Intent(RunningActivity.this,AlertActivity.class);
				startActivity(intent);
		
			}
		};

		brewCountDownTimer.start();

		//startBrew.setText("Stop");
		//isBrewing = true;
	}
/*
 * ֹͣ��ʱ
 */
	public void stopBrew() {
		if (brewCountDownTimer != null) {
			brewCountDownTimer.cancel();
		}

		//isBrewing = false;
		//startBrew.setText("Start");
	}
	/*
	 * Ŀ��ʱ�䵽����ϵͳ��Ĭ������
	 */
	public boolean ring() {
		Uri ringToneUri = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
		try {
			mediaPlayer.setDataSource(this, ringToneUri);

			final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
			if (audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL) != 0) {
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
				mediaPlayer.setLooping(false);
				mediaPlayer.prepare();
				mediaPlayer.start();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
