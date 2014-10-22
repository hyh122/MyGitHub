package ui.statisticsDisplay.activity.achartEngine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
		String[] titles = new String[] { "����ֵ" };
		List<double[]> x = new ArrayList<double[]>();
		for (int i = 0; i < titles.length; i++) {
			x.add(new double[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 });
		}
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 136, 134, 139, 138, 130, 124, 120, 138, 112,
				134, 121, 128, 118, 132, 130, 124, 127, 124, 131, 137, 140,
				128, 134, 136, 118, 120, 135, 121, 142, 132, 117, 132, 117,
				128, 127, 144, 131, 123, 135, 134, 124, 124, 114, 133, 128,
				121, 113, 136, 131, 124 });

		// int[] colors = new int[] { Color.BLUE };
		XYSeriesRenderer r = new XYSeriesRenderer();
		r.setColor(Color.rgb(255, 0, 0));
		r.setLineWidth(3);
		// PointStyle[] styles = new PointStyle[] { PointStyle.DIAMOND };
		// r.setPointStyle(styles[0]);
		// r.setPointStrokeWidth(3);
		// r.setFillPoints(false);
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.addSeriesRenderer(r);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer seriesRenderer = ((XYSeriesRenderer) renderer
					.getSeriesRendererAt(i));
			seriesRenderer.setDisplayChartValues(true);
		}

		setChartSettings(renderer, "", "ʱ��", "����", 0, 12.5, 50, 160,
				Color.BLACK, Color.BLACK);
		// renderer.setLabelsTextSize(20);
		renderer.setXLabels(10);
		renderer.setYLabels(10);
		renderer.setBarSpacing(1);// ���ü��
		renderer.setChartValuesTextSize(20);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);
		// renderer.setZoomButtonsVisible(true);
		renderer.setPanLimits(new double[] { 0, 40, 60, 170 });
		renderer.setZoomLimits(new double[] { 0, 40, 60, 170 });
		// renderer.setGridColor(Color.WHITE);
		// renderer.setZoomButtonsVisible(true);//�Ƿ���ʾ�Ŵ���С��ť

		renderer.setMarginsColor(Color.argb(5, 222, 178, 167));
		renderer.setApplyBackgroundColor(true);// �����Ƿ���ʾ����ɫ
		renderer.setBackgroundColor(Color.argb(5, 222, 178, 167));// ���ñ���ɫ

		renderer.setMargins(new int[] { 50, 70, 50, 10 });// ����ͼ�����߿�(��/��/��/��)
		renderer.setPointSize(1000);// ���õ�Ĵ�С(ͼ����ʾ�ĵ�Ĵ�С��ͼ���е�Ĵ�С���ᱻ����)
		renderer.setAxisTitleTextSize(38); // ������������ֵĴ�С
		renderer.setChartTitleTextSize(38);// ?��������ͼ��������ִ�С
		renderer.setLabelsTextSize(38);// ���ÿ̶���ʾ���ֵĴ�С(XY�ᶼ�ᱻ����)
		renderer.setLegendTextSize(38);// ͼ�����ִ�С

		XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
		XYSeries series = dataset.getSeriesAt(0);
		// series.addAnnotation("Vacation", 6, 30);
		View chart = ChartFactory.getLineChartView(context, dataset, renderer);
		return chart;
	}

	@Override
	public View initView(double[] heartRate) {
		// TODO Auto-generated method stub
		String[] titles = new String[] { "����ֵ" };
		List<double[]> x = new ArrayList<double[]>();
		double[] num = new double[heartRate.length];
		List<double[]> values = new ArrayList<double[]>();
		double[] heartRateCopy = Arrays.copyOf(heartRate, heartRate.length);

		for (int i = 0; i < titles.length; i++) {
			for (int j = 0; j < heartRate.length; j++) {
				num[j] = j + 1;
			}
			x.add(num);
		}
		values.add(heartRate);
		Arrays.sort(heartRateCopy);

		XYSeriesRenderer r = new XYSeriesRenderer();
		r.setColor(Color.rgb(200, 10, 20));
		r.setLineWidth(2);

		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.addSeriesRenderer(r);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer seriesRenderer = ((XYSeriesRenderer) renderer
					.getSeriesRendererAt(i));
			// seriesRenderer.setDisplayChartValues(true);�Ƿ���ʾ���ϵĵ�
		}

		setChartSettings(renderer, "", "ʱ��(min)", "����(times/min)", 0, 12.5, 70,
				130, Color.BLACK, Color.BLACK);

		renderer.setXLabels(13);
		renderer.setYLabels(13);
		renderer.setBarSpacing(1);// ���ü��
		// renderer.setChartValuesTextSize(20);
		renderer.setXLabelsAlign(Align.RIGHT);// �̶�����̶ȱ�ע֮������λ�ù�ϵ
		renderer.setYLabelsAlign(Align.CENTER);// �̶�����̶ȱ�ע֮������λ�ù�ϵ
		renderer.setYLabelsPadding(25);// �������ֺ��������λ��
		renderer.setXLabelsPadding(10);// �������ֺ��������λ��
		renderer.setXLabelsColor(Color.BLACK);
		renderer.setYLabelsColor(0, Color.BLACK);
		renderer.setPanLimits(new double[] { 0, heartRate.length,
				heartRateCopy[0] - 10,
				heartRateCopy[heartRateCopy.length - 1] + 10 });
		renderer.setZoomLimits(new double[] { 0, heartRate.length,
				heartRateCopy[0] - 10,
				heartRateCopy[heartRateCopy.length - 1] + 10 });

		renderer.setShowGrid(true);// �Ƿ���ʾ����
		renderer.setMarginsColor(Color.rgb(220, 178, 167));
		renderer.setApplyBackgroundColor(true);// �����Ƿ���ʾ����ɫ
		renderer.setBackgroundColor(Color.rgb(220, 178, 167));// ���ñ���ɫRGB(221,198,194)
		renderer.setShowLabels(true);

		renderer.setMargins(new int[] { 50, 70, 50, 10 });// ����ͼ�����߿�(��/��/��/��)
		renderer.setPointSize(200);// ���õ�Ĵ�С(ͼ����ʾ�ĵ�Ĵ�С��ͼ���е�Ĵ�С���ᱻ����)
		renderer.setAxisTitleTextSize(30); // ������������ֵĴ�С
		renderer.setChartTitleTextSize(30);// ��������ͼ��������ִ�С
		renderer.setLabelsTextSize(20);// ���ÿ̶���ʾ���ֵĴ�С(XY�ᶼ�ᱻ����)
		renderer.setLegendTextSize(25);// ͼ�����ִ�С

		XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
		XYSeries series = dataset.getSeriesAt(0);
		// series.addAnnotation("Vacation", 6, 30);
		View chart = ChartFactory.getCubeLineChartView(context, dataset,
				renderer, 0.4f);
		return chart;
	}
}
