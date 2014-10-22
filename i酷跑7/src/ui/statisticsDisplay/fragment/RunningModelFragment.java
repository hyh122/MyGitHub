package ui.statisticsDisplay.fragment;

import java.util.Collections;
import java.util.List;

import ui.statisticsDisplay.activity.MainActivity;
import ui.statisticsDisplay.activity.achartEngine.IDemoChart;
import ui.statisticsDisplay.activity.achartEngine.ModelChart;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.androidui_sample_demo.R;

import foundation.dataService.util.ListSort;
import foundation.dataService.util.LoadingDialog;
import foundation.webservice.help.MinuteSportData;
import foundation.webservice.help.OneSport;


public class RunningModelFragment extends Fragment {
	private IDemoChart lineChart;
	private Context context;
	private LinearLayout line_chart;
	private double[] heartRate=null,speed=null;
	private LoadingDialog dialog=null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		context = (MainActivity) getActivity();
		Bundle bundle = this.getArguments();
//		Toast.makeText(getActivity(), "模型更新中...", Toast.LENGTH_SHORT).show();
		String s = null;
		s = bundle.getString("oneSport");// 接受menuFragment信息
		if (!s.equals("null")) {
			OneSport oneSport = JSON.parseObject(s, OneSport.class);
//			List<OneSport> oneSports=model.getUser().getOneSports();
//			List<MinuteSportData> minuteSportDatas=oneSports.get(0).getMinuteSportData();
//			List<Double> encodes = new ArrayList<Double>();
//			encodes.add(model.getParameter().getA1());
//			encodes.add(model.getParameter().getA2());
//			encodes.add(model.getParameter().getA3());
//			encodes.add(model.getParameter().getA4());
//			encodes.add(model.getParameter().getA5());
//			heartRate = RungeKutta.calcuFitness(encodes);
//			for (int i = 0; i < heartRate.length; i++) {
//				heartRate[i] += 80;
//			}
//			heartRate = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			calModel(oneSport);
		} else {
			heartRate = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		}
	}
	// 一个速度对应多个心率
	@SuppressWarnings("unchecked")
	public void calModel(OneSport oneSport){
		int size = 0;
		size = oneSport.getMinuteSportData().size();
		double[] speed = new double[size];
		double[] heartRate = new double[size];
		double[][] temp = new double[size][];
		List<MinuteSportData> minuteSportDatas = oneSport.getMinuteSportData();
		Collections.sort(minuteSportDatas, new ListSort());

		for (int i = 0; i < size; i++) {
			speed[i] = minuteSportDatas.get(i).getSpeed();
			heartRate[i] = minuteSportDatas.get(i).getHeartRate();
		}
		this.heartRate = heartRate;
		this.speed = speed;
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_runningmodel, null);
		getActivity().setTitle("运动模型");
		line_chart = (LinearLayout) view.findViewById(R.id.line_chart);
		lineChart = new ModelChart(context);// 实例化ModelChart
		View viewChart = lineChart.initView(heartRate,speed);
		if (checkHeartRate(heartRate)) {
			Toast.makeText(getActivity(), "加载成功", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
		}
		line_chart.addView(viewChart);

		return view;
	}

	public boolean checkHeartRate(double[] heartRate) {

		for (int i = 0; i < heartRate.length; i++) {
			if (heartRate[i] != 0) {
				return true;
			}
		}
		return false;
	}

//	@Override
//	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//		// TODO Auto-generated method stub
//
//		menu.clear();
//		inflater = getActivity().getMenuInflater();
//		inflater.inflate(R.menu.menu_main, menu);
//		super.onCreateOptionsMenu(menu, inflater);
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// TODO Auto-generated method stub
//		switch (item.getItemId()) {
//		case R.id.setting:
//			Intent intent = new Intent(getActivity(), SettingActivity.class);
//			startActivity(intent);
//			RunningModelFragment.this.onDestroy();
//			break;
//		}
//		return super.onOptionsItemSelected(item);
//	}

}
