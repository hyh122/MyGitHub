package ui.statisticsDisplay.activity.achartEngine;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

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
		
		String[] titles = new String[] { "�ٶ�ֵ"};//ͼ��  
	    List<double[]> values = new ArrayList<double[]>();  
	    values.add(new double[] {6.36,5.99,5.81,5.86,5.45,4.4,
				7.68,4.31,6.74,5.64,5.39,4.79});//��һ�����ӵ���ֵ  
//	    values.add(new double[] {5.68,3.32,6.07,6.41,3.25,6.64,
//				6.86,4.00,6.98,6.79,4.53,6.36});//�ڶ������ӵ���ֵ  
	    
	    int[] colors = new int[] { Color.rgb(105,105,105)};//�������ӵ���ɫ  
	    
	    XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);//����AbstractDemoChart�еķ�������renderer.  
	    setChartSettings(renderer, " ", "ʱ��", "�ٶ�", 0.5,  
	        12.5, 0, 10, Color.BLACK, Color.BLACK);//����AbstractDemoChart�еķ�������renderer��һЩ����. 
	    
	   renderer.getSeriesRendererAt(0).setDisplayChartValues(true);//�����������Ƿ���ʾ����ֵ  
	 //   renderer.getSeriesRendererAt(1).setDisplayChartValues(true);//�����������Ƿ���ʾ����ֵ  
	    renderer.setXLabels(12);//X��Ľ���������  
	    renderer.setYLabels(5);//Y��Ľ���������  
	    renderer.setXLabelsAlign(Align.LEFT);//�̶�����X����������������  
	    renderer.setYLabelsAlign(Align.LEFT);//Y����Y���������������  
	    renderer.setPanEnabled(true, false);//���������϶�,�������������϶�.  
	    // renderer.setZoomEnabled(false);  
	    renderer.setZoomRate(1.1f);//�Ŵ�ı���  
	    renderer.setBarSpacing(0.9f);//���Ӽ���  
	    renderer.setMarginsColor(Color.argb(2,222, 178, 167));
	    
	    
	   
	    
	    renderer.setApplyBackgroundColor(true);//�����Ƿ���ʾ����ɫ  
	    renderer.setBackgroundColor(Color.argb(2,222, 178, 167));//���ñ���ɫ 
	    
	    renderer.setMargins(new int[] { 50, 70, 50, 10 });//����ͼ�����߿�(��/��/��/��) 
	    renderer.setPointSize(200);//���õ�Ĵ�С(ͼ����ʾ�ĵ�Ĵ�С��ͼ���е�Ĵ�С���ᱻ����)
	    renderer.setAxisTitleTextSize(38); //������������ֵĴ�С  
	    renderer.setChartTitleTextSize(38);//?��������ͼ��������ִ�С  
	    renderer.setLabelsTextSize(38);//���ÿ̶���ʾ���ֵĴ�С(XY�ᶼ�ᱻ����)  
	    renderer.setLegendTextSize(40);//ͼ�����ִ�С 
	    
	    renderer.setPanLimits(new double[] {0, 11,0,50 }); //�����϶�ʱX��Y����������ֵ��Сֵ.  
	    renderer.setZoomLimits(new double[] {0, 11,0,50 });//���÷Ŵ���СʱX��Y������������Сֵ.  
//	    renderer.setZoomButtonsVisible(true);//�Ƿ���ʾ�Ŵ���С��ť  
		 View view=ChartFactory.getBarChartView(context, buildBarDataset(titles, values), renderer, Type.DEFAULT);
		 return view;
		    

		
		
	}

}
