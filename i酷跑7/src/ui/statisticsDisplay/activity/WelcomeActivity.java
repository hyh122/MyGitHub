package ui.statisticsDisplay.activity;

import ui.systemManaConfig.activity.LoginActivity;

import com.File.TxtFileUtil;
import com.example.androidui_sample_demo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeActivity extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 200; // �ӳ�����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				String loginFlag = null;
				try {
					loginFlag = TxtFileUtil.readTxtFile(TxtFileUtil.loginFlag);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (loginFlag == null) {
					Intent mainIntent = new Intent(WelcomeActivity.this,
							LoginActivity.class);
					WelcomeActivity.this.startActivity(mainIntent);
					WelcomeActivity.this.finish();
				}
				String[] str = loginFlag.split(",");
				if (str[0].equals("login")) {// �ѵ�¼ֱ������������
					Intent mainIntent = new Intent(WelcomeActivity.this,
							MainActivity.class);
					WelcomeActivity.this.startActivity(mainIntent);
					WelcomeActivity.this.finish();
				} else {
					Intent mainIntent = new Intent(WelcomeActivity.this,
							LoginActivity.class);
					WelcomeActivity.this.startActivity(mainIntent);
					WelcomeActivity.this.finish();
				}
			}
		}, SPLASH_DISPLAY_LENGHT);
	}

}
