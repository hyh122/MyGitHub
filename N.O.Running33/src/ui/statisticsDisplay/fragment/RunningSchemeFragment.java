package ui.statisticsDisplay.fragment;

import ui.statisticsDisplay.activity.MainActivity;
import ui.statisticsDisplay.activity.SettingActivity;
import ui.statisticsDisplay.activity.achartEngine.ComprehensiveChart;
import ui.statisticsDisplay.activity.achartEngine.HeartRareChart;
import ui.statisticsDisplay.activity.achartEngine.IDemoChart;
import ui.statisticsDisplay.activity.achartEngine.SpeedControlChart;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.androidui_sample_demo.R;

public class RunningSchemeFragment extends Fragment {
	private IDemoChart lineChart;
	private Spinner sp_select_time;
	private Context context;
	private TextView txt_speed_control,txt_heartrate_chart,txt_comprehensive,txt_title;
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
		View view = inflater.inflate(R.layout.fragment_runningscheme, null);
		getActivity().setTitle("运动方案");
		initView(view);
		return view;
	}

	public void initView(View view)
	{
		txt_speed_control=(TextView) view.findViewById(R.id.txt_speed_control);
		txt_heartrate_chart=(TextView) view.findViewById(R.id.txt_heartrate_chart);
		txt_comprehensive=(TextView) view.findViewById(R.id.txt_comprehensive);
		txt_speed_control.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				line_chart.removeAllViews();
				txt_speed_control.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_bg_press));
				txt_heartrate_chart.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_bg));
				txt_comprehensive.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_bg));
				txt_title.setText("速度控制图");
				lineChart=new SpeedControlChart(context);
				
				View viewChart=lineChart.initView();
				
				line_chart.addView(viewChart);
			}
		});
		txt_heartrate_chart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_chart.removeAllViews();
				txt_speed_control.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_bg));
				txt_heartrate_chart.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_bg_press));
				txt_comprehensive.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_bg));
				txt_title.setText("心率图");
				lineChart=new HeartRareChart(context);
				View viewChart=lineChart.initView();
				line_chart.addView(viewChart);
			}
		});
		
		txt_comprehensive.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				line_chart.removeAllViews();
				txt_speed_control.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_bg));
				txt_heartrate_chart.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_bg));
				txt_comprehensive.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_bg_press));
				txt_title.setText("综合分析");
				lineChart=new ComprehensiveChart(context);
				View viewChart=lineChart.initView();
				line_chart.addView(viewChart);
			}
		});
		
		txt_title=(TextView)view.findViewById(R.id.txt_title);
		sp_select_time=(Spinner) view.findViewById(R.id.sp_select_time);
		String[] mItems = getResources().getStringArray(R.array.select_time_array);
		ArrayAdapter<String> adapt_select_time = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,mItems); 
		sp_select_time.setAdapter(adapt_select_time);
		line_chart= (LinearLayout) view.findViewById(R.id.line_chart);
		lineChart=new SpeedControlChart(context);
		View viewChart=lineChart.initView();
		line_chart.addView(viewChart);
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
