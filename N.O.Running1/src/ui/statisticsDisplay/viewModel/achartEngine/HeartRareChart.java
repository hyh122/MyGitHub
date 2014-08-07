package ui.statisticsDisplay.viewModel.achartEngine;

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
		String[] titles = new String[] { "HeartRare" };
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(new double[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 });
		}
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 75, 100, 132, 135, 134, 140, 138, 135, 135,
				134, 100, 80 });

		//int[] colors = new int[] { Color.BLUE };
		XYSeriesRenderer r = new XYSeriesRenderer(); 
		r.setColor(Color.RED);
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
		
		setChartSettings(renderer, "", "", "", 0, 12.5, 50, 170,
				Color.LTGRAY, Color.LTGRAY);
		renderer.setLabelsTextSize(20);
		renderer.setXLabels(10);
		renderer.setYLabels(10);
		renderer.setChartValuesTextSize(20);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		//renderer.setZoomButtonsVisible(true);
		renderer.setPanLimits(new double[] { 0, 20, 50, 170 });
		renderer.setZoomLimits(new double[] { 0, 20, 50, 170 });
		//renderer.setGridColor(Color.WHITE);
		renderer.setMarginsColor(Color.argb(0, 255, 204, 204));
		XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
		XYSeries series = dataset.getSeriesAt(0);
		series.addAnnotation("Vacation", 6, 30);
		View chart = ChartFactory.getLineChartView(context, dataset, renderer);
		return chart;
	}
}
