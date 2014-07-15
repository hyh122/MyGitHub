package com.example.App;

import foundation.dataService.base.DataHelper;

import android.app.Application;

public class TestORMLiteApp extends Application {
	
	public static final String DATAFILENAME="demo1.db";
	public static DataHelper DATAHELPER;
	
	@Override
	public void onCreate() {
		super.onCreate();
		// 初始化全局变量
		DATAHELPER = new DataHelper(getApplicationContext(), DATAFILENAME);
	}
}