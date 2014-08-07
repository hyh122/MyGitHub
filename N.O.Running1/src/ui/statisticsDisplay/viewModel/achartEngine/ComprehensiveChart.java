package ui.statisticsDisplay.viewModel.achartEngine;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.view.View;

public class ComprehensiveChart extends AbstractDemoChart {

	Context context;

	public ComprehensiveChart(Context context) {
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
		String[] titles = new String[] { "Comprehensive" };
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(new double[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 });
		}
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 75, 100, 132, 135, 134, 140, 138, 135, 135,
				134, 100, 80 });
		int[] colors = new int[] { Color.BLUE, Color.RED };
		PointStyle[] styles = new PointStyle[] { PointStyle.POINT,
				PointStyle.POINT };
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer(2);
		setRenderer(renderer, colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer r = (XYSeriesRenderer) renderer
					.getSeriesRendererAt(i);
			r.setLineWidth(3f);
		}
		setChartSettings(renderer, "", "", "", 0, 11, 50, 170, Color.LTGRAY,
				Color.LTGRAY);
		renderer.setXLabels(12);
		renderer.setYLabels(10);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		// renderer.setZoomButtonsVisible(true);
		renderer.setPanLimits(new double[] { 0, 20, 50, 170 });
		renderer.setZoomLimits(new double[] { 0, 20, 50, 170 });
		// renderer.setZoomRate(1.05f);
		renderer.setMarginsColor(Color.argb(0, 255, 204, 204));
		renderer.setLabelsColor(Color.WHITE);
		renderer.setXLabelsColor(Color.GRAY);
		renderer.setYLabelsColor(0, colors[0]);
		renderer.setYLabelsColor(1, colors[1]);

		renderer.setYTitle("Speed", 1);
		renderer.setYAxisAlign(Align.RIGHT, 1);
		renderer.setYLabelsAlign(Align.LEFT, 1);

		XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
		values.clear();
		values.add(new double[] { 0, 3.2, 3.4, 3.6, 4.0, 4.0, 4.0, 4.0, 3.9,
				3.4, 3.2, 0 });
		addXYSeries(dataset, new String[] { "Speed" }, x, values, 1);

		View chart = ChartFactory.getCubeLineChartView(context, dataset,
				renderer, 0.3f);
		return chart;
	}

}
