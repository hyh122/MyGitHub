package ui.statisticsDisplay.activity.achartEngine;

import java.util.ArrayList;
import java.util.Arrays;
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
		return null;
	}

	@Override
	public View initView(double[] heartRate) {
		String[] titles = new String[] { "����", "����", "����" };// ͼ��
		List<double[]> x = new ArrayList<double[]>();
		List<double[]> values = new ArrayList<double[]>();// ��������
		double[] xNum = new double[heartRate.length];// ���������
		double[] ceiling = new double[heartRate.length];// ����
		double[] offline = new double[heartRate.length];// ����
		double[] heartRateCopy = Arrays.copyOf(heartRate, heartRate.length);

		for (int i = 0; i < titles.length; i++) {// �������ʶ�����ʾX�������ֵ
			for (int j = 0; j < heartRate.length; j++) {
				xNum[j] = j + 1;
			}
			x.add(xNum);
		}
		for (int i = 0; i < heartRate.length; i++) {
			ceiling[i] = 140;
			offline[i] = 120;
		}
		values.add(ceiling);// ����
		values.add(offline);// ����
		values.add(heartRate);// ����1�е��y����
		Arrays.sort(heartRateCopy);
		int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN };// ÿ�����е���ɫ����
		PointStyle[] styles = new PointStyle[] { PointStyle.POINT,
				PointStyle.POINT, PointStyle.POINT };// ÿ�������е����״����
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);// ����AbstractDemoChart�еķ�������
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);// ����ͼ�ϵĵ�Ϊʵ��
		}

		setChartSettings(renderer, "�˶�ģ��", "�ٶ�(m/s)", "����(times/min)", 0, 10,
				80, 150, Color.BLACK, Color.BLACK);// ����AbstractDemoChart�еķ�������ͼ���renderer����.

		renderer.setXLabels(13);// ����x����ʾ12����,����setChartSettings�����ֵ����Сֵ�Զ������ļ��
		renderer.setYLabels(13);// ����y����ʾ10����,����setChartSettings�����ֵ����Сֵ�Զ������ļ��
		renderer.setXLabelsAlign(Align.RIGHT);// �̶�����̶ȱ�ע֮������λ�ù�ϵ
		renderer.setYLabelsAlign(Align.CENTER);// �̶�����̶ȱ�ע֮������λ�ù�ϵ
		renderer.setYLabelsPadding(25);// �������ֺ��������λ��
		renderer.setXLabelsPadding(10);// �������ֺ��������λ��
		renderer.setXLabelsColor(Color.BLACK);
		renderer.setYLabelsColor(0, Color.BLACK);

		renderer.setShowGrid(true);// �Ƿ���ʾ����
		renderer.setMarginsColor(Color.rgb(220, 178, 167));
		renderer.setApplyBackgroundColor(true);// �����Ƿ���ʾ����ɫ
		renderer.setBackgroundColor(Color.rgb(220, 178, 167));// ���ñ���ɫRGB(221,198,194)
		renderer.setShowLabels(true);

		renderer.setMargins(new int[] { 70, 70, 50, 50 });// ����ͼ�����߿�(��/��/��/��)
		renderer.setPointSize(200);// ���õ�Ĵ�С(ͼ����ʾ�ĵ�Ĵ�С��ͼ���е�Ĵ�С���ᱻ����)
		renderer.setAxisTitleTextSize(30); // ������������ֵĴ�С
		renderer.setChartTitleTextSize(30);// ��������ͼ��������ִ�С
		renderer.setLabelsTextSize(20);// ���ÿ̶���ʾ���ֵĴ�С(XY�ᶼ�ᱻ����)
		renderer.setLegendTextSize(25);// ͼ�����ִ�С

		renderer.setPanLimits(new double[] { 0, heartRate.length,
				heartRateCopy[0] - 10,
				heartRateCopy[heartRateCopy.length - 1] + 10 }); // �����϶�ʱX��Y����������ֵ��Сֵ.
		renderer.setZoomLimits(new double[] { 0, heartRate.length,
				heartRateCopy[0] - 10,
				heartRateCopy[heartRateCopy.length - 1] + 10 });// ���÷Ŵ���СʱX��Y������������Сֵ.

		View chart = ChartFactory.getCubeLineChartView(context,
				buildDataset(titles, x, values), renderer, 0.4f);
		return chart;
	}

}
