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
		String[] titles = new String[] { "心率" };
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(new double[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 });
		}
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 
				  96,94,99,98,90,84, 
				  80,98,72,94,81,88,
				  78,92,90,84,87,84,
				  91,97,100,88,94,96,
				  78,80,95,81,102,92,
				  77,92,77,88,87,104,
				  91,83,95,94,84,84,
				  74,93,88,81,73,96,
				  91,84 });

		//int[] colors = new int[] { Color.BLUE };
		XYSeriesRenderer r = new XYSeriesRenderer(); 
		r.setColor(Color.GREEN);
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
		
		setChartSettings(renderer, "", "时间", "心率", 0, 12.5, 50, 140,
				Color.BLACK, Color.BLACK);
		renderer.setLabelsTextSize(20);
		renderer.setXLabels(10);
		renderer.setYLabels(10);
		renderer.setBarSpacing(1);//设置间距
		renderer.setChartValuesTextSize(20);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		//renderer.setZoomButtonsVisible(true);
		renderer.setPanLimits(new double[] { 0, 20, 50, 140 });
		renderer.setZoomLimits(new double[] { 0, 20, 50, 140 });
		//renderer.setGridColor(Color.WHITE);
		 renderer.setZoomButtonsVisible(true);//是否显示放大缩小按钮  
		renderer.setMarginsColor(Color.argb(0,0,255,0));
		XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
		XYSeries series = dataset.getSeriesAt(0);
		series.addAnnotation("Vacation", 6, 30);
		View chart = ChartFactory.getLineChartView(context, dataset, renderer);
		return chart;
	}
}
