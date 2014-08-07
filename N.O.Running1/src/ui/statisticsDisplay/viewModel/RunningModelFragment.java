package ui.statisticsDisplay.viewModel;


import ui.statisticsDisplay.Activity.MainActivity;
import ui.statisticsDisplay.Activity.SettingActivity;
import ui.statisticsDisplay.viewModel.achartEngine.IDemoChart;
import ui.statisticsDisplay.viewModel.achartEngine.ModelChart;
import ui.statisticsDisplay.viewModel.achartEngine.SpeedControlChart;

import com.example.androidui_sample_demo.R;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class RunningModelFragment extends Fragment {
	private IDemoChart lineChart;
	private Context context;
	private LinearLayout line_chart;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		context=(MainActivity)getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_runningmodel, null);
		getActivity().setTitle("运动模型");
		line_chart= (LinearLayout) view.findViewById(R.id.line_chart);
		lineChart=new ModelChart(context);
		View viewChart=lineChart.initView();
		line_chart.addView(viewChart);
		return view;
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		menu.clear();
		inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.setting:
			Intent intent=new Intent(getActivity(),SettingActivity.class);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
