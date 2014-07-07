package com.example.App;

import com.example.dao.DataHelper;

import android.app.Application;

public class TestORMLiteApp extends Application {
	
	public static final String DATAFILENAME="test.db";
	public static DataHelper DATAHELPER;
	
	@Override
	public void onCreate() {
		super.onCreate();
		// 初始化全局变量
		DATAHELPER = new DataHelper(getApplicationContext(), DATAFILENAME);
	}
}
