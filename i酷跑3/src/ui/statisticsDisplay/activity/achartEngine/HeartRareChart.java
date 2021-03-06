package ui.statisticsDisplay.activity.achartEngine;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.view.View;

public class HeartRareChart extends AbstractDemoChart {

	Context context;

	public HeartRareChart(Context context) {
		this.context = context;

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Intent execute(Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		String[] titles = new String[] { "心率值" };
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(new double[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 });
		}
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 
				  136,134,139,138,130,124, 
				  120,138,112,134,121,128,
				  118,132,130,124,127,124,
				  131,137,140,128,134,136,
				  118,120,135,121,142,132,
				  117,132,117,128,127,144,
				  131,123,135,134,124,124,
				  114,133,128,121,113,136,
				  131,124 });

		//int[] colors = new int[] { Color.BLUE };
		XYSeriesRenderer r = new XYSeriesRenderer(); 
		r.setColor(Color.rgb(255,0,0));
		r.setLineWidth(3);
	//	PointStyle[] styles = new PointStyle[] { PointStyle.DIAMOND };
	//	r.setPointStyle(styles[0]); 
	//	r.setPointStrokeWidth(3);
	//	r.setFillPoints(false);
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.addSeriesRenderer(r);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer seriesRenderer=((XYSeriesRenderer) renderer.getSeriesRendererAt(i));
			seriesRenderer.setDisplayChartValues(true);	
		}
		
		setChartSettings(renderer, "", "时间", "心率", 0, 12.5, 50, 160,
				Color.BLACK, Color.BLACK);
		//renderer.setLabelsTextSize(20);
		renderer.setXLabels(10);
		renderer.setYLabels(10);
		renderer.setBarSpacing(1);//设置间距
		renderer.setChartValuesTextSize(20);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		//renderer.setZoomButtonsVisible(true);
		renderer.setPanLimits(new double[] { 0, 40, 60, 170 });
		renderer.setZoomLimits(new double[] { 0, 40, 60, 170 });
		//renderer.setGridColor(Color.WHITE);
		// renderer.setZoomButtonsVisible(true);//是否显示放大缩小按钮  
	
		renderer.setMarginsColor(Color.argb(5,222, 178, 167));
		 renderer.setApplyBackgroundColor(true);//设置是否显示背景色  
		 renderer.setBackgroundColor(Color.argb(5,222, 178, 167));//设置背景色 
		    
		 renderer.setMargins(new int[] { 50, 70, 50, 10 });//设置图表的外边框(上/左/下/右) 
		 renderer.setPointSize(1000);//设置点的大小(图上显示的点的大小和图例中点的大小都会被设置)
		 renderer.setAxisTitleTextSize(38); //设置轴标题文字的大小  
		 renderer.setChartTitleTextSize(38);//?设置整个图表标题文字大小  
		 renderer.setLabelsTextSize(38);//设置刻度显示文字的大小(XY轴都会被设置)  
		 renderer.setLegendTextSize(38);//图例文字大小 
		    
		XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
		XYSeries series = dataset.getSeriesAt(0);
		//series.addAnnotation("Vacation", 6, 30);
		View chart = ChartFactory.getLineChartView(context, dataset, renderer);
		return chart;
	}
}
