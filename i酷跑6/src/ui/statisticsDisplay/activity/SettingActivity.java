package ui.statisticsDisplay.activity;

import com.example.androidui_sample_demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class SettingActivity  extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_setting);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle("�趨 ");
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();

		}
		return super.onOptionsItemSelected(item);
	}
}
