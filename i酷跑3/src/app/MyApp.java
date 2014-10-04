package app;

import foundation.dataService.base.DataHelper;
import android.app.Application;

public class MyApp extends Application {
	
	public static final String DATAFILENAME="ikupao.db";
	public static DataHelper DATAHELPER;
	@Override
	public void onCreate() {
		super.onCreate();
		// ��ʼ��ȫ�ֱ���
		DATAHELPER = new DataHelper(getApplicationContext(), DATAFILENAME);
	}
}
