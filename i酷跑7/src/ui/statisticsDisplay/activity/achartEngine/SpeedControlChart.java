package ui.statisticsDisplay.activity.achartEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.view.View;

public class SpeedControlChart extends AbstractDemoChart {

	Context context;

	public SpeedControlChart(Context context) {
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
	public View initView(double[] speed) {
		String[] titles = new String[] { "�ٶ�ֵ" };// ͼ��
		List<double[]> values = new ArrayList<double[]>();
		double[] speedCopy = Arrays.copyOf(speed, speed.length);
		
		values.add(speed);// ���ӵ���ֵ
		Arrays.sort(speedCopy);
		
		int[] colors = new int[] { Color.rgb(118, 123, 138) };// �������ӵ���ɫ
		XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);// ����AbstractDemoChart�еķ�������renderer.
		setChartSettings(renderer, " ", "ʱ��(min)", "�ٶ�(m/s)", 0, 11, 0, 7, Color.BLACK,
				Color.BLACK);// ����AbstractDemoChart�еķ�������renderer��һЩ����.
		renderer.getSeriesRendererAt(0).setDisplayChartValues(false);// �����������Ƿ���ʾ����ֵ
		// renderer.getSeriesRendererAt(1).setDisplayChartValues(true);//�����������Ƿ���ʾ����ֵ
		/**����XY�����*/
		renderer.setXLabels(12);// X��ĵ����
		renderer.setYLabels(10);// Y��ĵ����
		renderer.setXLabelsColor(Color.BLACK);
		renderer.setYLabelsColor(0, Color.BLACK);
		renderer.setXLabelsAlign(Align.LEFT);// �̶�����X����������������
		renderer.setYLabelsAlign(Align.RIGHT);// Y����Y���������������
		renderer.setYLabelsPadding(15);// �������ֺ��������λ��
		renderer.setXLabelsPadding(10);// �������ֺ��������λ��
		/**�Ŵ��϶���һЩ��������*/
		renderer.setPanEnabled(true, true);// ���������϶�,���������϶�.
		renderer.setZoomEnabled(false);
		renderer.setPanLimits(new double[] { 0, speed.length+2, 0, speedCopy[speedCopy.length-1]+1 }); // �����϶�ʱX��Y����������ֵ��Сֵ.
		renderer.setZoomLimits(new double[] { 0, speed.length+2, 0, speedCopy[speedCopy.length-1]+1 });// ���÷Ŵ���СʱX��Y������������Сֵ.
		renderer.setZoomRate(1.3f);// �Ŵ�ı���
		renderer.setBarSpacing(0.7f);// ���Ӽ���
		/**����ɫ��ͼ��λ�ã������С��������*/
		renderer.setMarginsColor(Color.rgb(220, 178, 167));
		renderer.setApplyBackgroundColor(true);// �����Ƿ���ʾ����ɫ
		renderer.setBackgroundColor(Color.rgb(220, 178, 167));// ���ñ���ɫ
		renderer.setMargins(new int[] { 50, 70, 50, 10 });// ����ͼ�����߿�(��/��/��/��)
		renderer.setPointSize(200);// ���õ�Ĵ�С(ͼ����ʾ�ĵ�Ĵ�С��ͼ���е�Ĵ�С���ᱻ����)
		renderer.setAxisTitleTextSize(30); // ������������ֵĴ�С
		renderer.setChartTitleTextSize(30);// ��������ͼ��������ִ�С
		renderer.setLabelsTextSize(20);// ���ÿ̶���ʾ���ֵĴ�С(XY�ᶼ�ᱻ����)25
		renderer.setLegendTextSize(25);// ͼ�����ִ�С

		View view = ChartFactory.getBarChartView(context,
				buildBarDataset(titles, values), renderer, Type.DEFAULT);
		return view;
	}

	@Override
	public View initView(double[] heartRate, double[] speed) {
		// TODO Auto-generated method stub
		return null;
	}

}
