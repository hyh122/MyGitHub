package ui.statisticsDisplay.fragment;

import java.util.ArrayList;
import java.util.List;

import ui.statisticsDisplay.activity.MainActivity;
import ui.statisticsDisplay.activity.achartEngine.ComprehensiveChart;
import ui.statisticsDisplay.activity.achartEngine.HeartRareChart;
import ui.statisticsDisplay.activity.achartEngine.IDemoChart;
import ui.statisticsDisplay.activity.achartEngine.SpeedControlChart;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.androidui_sample_demo.R;

import foundation.webservice.help.Model;
import foundation.webservice.help.RungeKutta;

public class RunningSchemeFragment extends Fragment {
	private IDemoChart chart;
	private Spinner sp_select_time;
	private Context context;
	private TextView txt_speed_control, txt_heartrate_chart, txt_comprehensive,
			txt_title;
	private LinearLayout line_chart;
	private double[] speed;
	private double[] heartRate;
	private double[] hsData;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		context = (MainActivity) getActivity();
		Bundle bundle = this.getArguments();
		String s=null;
		s = bundle.getString("model");// ����menuFragment��Ϣ
		Toast.makeText(getActivity(), "����������...", Toast.LENGTH_SHORT).show();
		if (!s.equals("null")) {
			Model model = JSON.parseObject(s, Model.class);
			List<Double> encodes = new ArrayList<Double>();
			encodes.add(model.getParameter().getA1());
			encodes.add(model.getParameter().getA2());
			encodes.add(model.getParameter().getA3());
			encodes.add(model.getParameter().getA4());
			encodes.add(model.getParameter().getA5());
			speed = new double[model.getSchemes().size()];
			for (int i = 0; i < speed.length; i++) {
				speed[i] = model.getSchemes().get(i).getBestSpeed();
			}
			heartRate = RungeKutta.calcuFitness(encodes, speed);
			hsData = RungeKutta.calcuFitness(encodes);
			for (int i = 0; i < heartRate.length; i++) {
				heartRate[i] += 80;
				hsData[i] += 80;
			}
			Toast.makeText(getActivity(), "���سɹ�", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getActivity(), "����ʧ��", Toast.LENGTH_SHORT).show();
			speed = new double[] { 0 };
			heartRate = new double[] { 0};
			hsData = new double[]{0};
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_runningscheme, null);
		getActivity().setTitle("�˶�����");
		initView(view);
		return view;
	}

	public void initView(View view) {
		txt_speed_control = (TextView) view
				.findViewById(R.id.txt_speed_control);
		txt_heartrate_chart = (TextView) view
				.findViewById(R.id.txt_heartrate_chart);
		txt_comprehensive = (TextView) view
				.findViewById(R.id.txt_comprehensive);
		txt_speed_control.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {
				line_chart.removeAllViews();
				txt_speed_control.setBackgroundDrawable(context.getResources()
						.getDrawable(R.drawable.text_bg_press));
				txt_heartrate_chart.setBackgroundDrawable(context
						.getResources().getDrawable(R.drawable.text_bg));
				txt_comprehensive.setBackgroundDrawable(context.getResources()
						.getDrawable(R.drawable.text_bg));
				txt_title.setText("�ٶȿ���ͼ");
				chart = new SpeedControlChart(context);

				View viewChart = chart.initView(speed);
				line_chart.addView(viewChart);
			}
		});
		txt_heartrate_chart.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				line_chart.removeAllViews();
				txt_speed_control.setBackgroundDrawable(context.getResources()
						.getDrawable(R.drawable.text_bg));
				txt_heartrate_chart.setBackgroundDrawable(context
						.getResources().getDrawable(R.drawable.text_bg_press));
				txt_comprehensive.setBackgroundDrawable(context.getResources()
						.getDrawable(R.drawable.text_bg));
				txt_title.setText("����ͼ");
				chart = new HeartRareChart(context);
				View viewChart = chart.initView(heartRate);
				line_chart.addView(viewChart);
			}
		});

		txt_comprehensive.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {
				line_chart.removeAllViews();
				txt_speed_control.setBackgroundDrawable(context.getResources()
						.getDrawable(R.drawable.text_bg));
				txt_heartrate_chart.setBackgroundDrawable(context
						.getResources().getDrawable(R.drawable.text_bg));
				txt_comprehensive.setBackgroundDrawable(context.getResources()
						.getDrawable(R.drawable.text_bg_press));
				txt_title.setText("�ۺϷ���");
				chart = new ComprehensiveChart(context);
				View viewChart = chart.initView(hsData);
				line_chart.addView(viewChart);
			}
		});

		txt_title = (TextView) view.findViewById(R.id.txt_title);
		// // sp_select_time=(Spinner) view.findViewById(R.id.sp_select_time);
		String[] mItems = getResources().getStringArray(
				R.array.select_time_array);
		@SuppressWarnings("unused")
		ArrayAdapter<String> adapt_select_time = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item, mItems);
		// sp_select_time.setAdapter(adapt_select_time);
		line_chart = (LinearLayout) view.findViewById(R.id.line_chart);
		chart = new SpeedControlChart(context);
		View viewChart = chart.initView(speed);
		line_chart.addView(viewChart);
	}

//	@Override
//	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//		menu.clear();
//		inflater = getActivity().getMenuInflater();
//		inflater.inflate(R.menu.menu_main, menu);
//		super.onCreateOptionsMenu(menu, inflater);
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case R.id.setting:
//			Intent intent = new Intent(getActivity(), SettingActivity.class);
//			startActivity(intent);
//			RunningSchemeFragment.this.onDestroy();
//			break;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
