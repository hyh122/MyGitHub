package ui.statisticsDisplay.viewModel;


import ui.statisticsDisplay.Activity.MainActivity;
import ui.statisticsDisplay.viewModel.achartEngine.IDemoChart;
import ui.statisticsDisplay.viewModel.achartEngine.LineChart;

import com.example.androidui_sample_demo.R;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DiscoverFragment extends Fragment{
    private IDemoChart lineChart;
    private Context context;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context=(MainActivity)getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_discover, null);
		LinearLayout linearLayout= (LinearLayout) view.findViewById(R.id.line_chart);
		lineChart=new LineChart(context);
		View viewChart=lineChart.initView();
		linearLayout.addView(viewChart);
		return view;
	}
}
