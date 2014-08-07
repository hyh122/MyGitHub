package ui.statisticsDisplay.Activity;

import java.util.ArrayList;
import java.util.List;

import ui.statisticsDisplay.viewModel.RunningRecordAdapt;

import com.example.androidui_sample_demo.R;

import domain.statisticsDisplay.RunningRecord;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class PersonalrunningrecordActivity extends Activity {

	private ListView list_running_record;
	private List<RunningRecord> runningRecord_list;
	private PersonalrunningrecordActivity PersonalrunningrecordActivity;
	private RunningRecordAdapt runningRecordAdapt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_runningrecord);
		PersonalrunningrecordActivity = this;
		list_running_record = (ListView) findViewById(R.id.list_running_record);
		// 显示ListView
		initList();
		showByMyBaseAdapter();
	}

	public void initList() {

		// 连接数据库是，这边改成查询函数
		runningRecord_list = new ArrayList<RunningRecord>();
		runningRecord_list.add(new RunningRecord("2014-5-11 19:47:30", "3km"));
		runningRecord_list.add(new RunningRecord("2014-5-12 19:20:40", "4km"));

	}
	public void showByMyBaseAdapter() {
		runningRecordAdapt = new RunningRecordAdapt(this, runningRecord_list);
		list_running_record.setAdapter(runningRecordAdapt);
	}

}
