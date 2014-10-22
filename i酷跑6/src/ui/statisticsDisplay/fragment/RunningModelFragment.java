package ui.statisticsDisplay.fragment;

import java.util.ArrayList;
import java.util.List;
import ui.statisticsDisplay.activity.MainActivity;
import ui.statisticsDisplay.activity.SettingActivity;
import ui.statisticsDisplay.activity.achartEngine.IDemoChart;
import ui.statisticsDisplay.activity.achartEngine.ModelChart;
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
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.example.androidui_sample_demo.R;

import foundation.dataService.util.LoadingDialog;
import foundation.webservice.help.Model;
import foundation.webservice.help.RungeKutta;

public class RunningModelFragment extends Fragment {
	private IDemoChart lineChart;
	private Context context;
	private LinearLayout line_chart;
	private double[] heartRate;
	private LoadingDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		context = (MainActivity) getActivity();
		Bundle bundle = this.getArguments();
//		Toast.makeText(getActivity(), "ģ�͸�����...", Toast.LENGTH_SHORT).show();
		String s = null;
		s = bundle.getString("model");// ����menuFragment��Ϣ
		if (!s.equals("null")) {
			Model model = JSON.parseObject(s, Model.class);
			List<Double> encodes = new ArrayList<Double>();
			encodes.add(model.getParameter().getA1());
			encodes.add(model.getParameter().getA2());
			encodes.add(model.getParameter().getA3());
			encodes.add(model.getParameter().getA4());
			encodes.add(model.getParameter().getA5());
			heartRate = RungeKutta.calcuFitness(encodes);
			for (int i = 0; i < heartRate.length; i++) {
				heartRate[i] += 80;
			}
		} else {
			heartRate = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_runningmodel, null);
		getActivity().setTitle("�˶�ģ��");
		line_chart = (LinearLayout) view.findViewById(R.id.line_chart);
		lineChart = new ModelChart(context);// ʵ����ModelChart
		View viewChart = lineChart.initView(heartRate);
		if (checkHeartRate(heartRate)) {
			Toast.makeText(getActivity(), "���سɹ�", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getActivity(), "����ʧ��", Toast.LENGTH_SHORT).show();
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
			Intent intent = new Intent(getActivity(), SettingActivity.class);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
