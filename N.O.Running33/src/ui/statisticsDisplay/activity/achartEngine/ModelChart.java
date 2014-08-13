package ui.statisticsDisplay.activity.achartEngine;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
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

public class ModelChart extends AbstractDemoChart {

	Context context;

	public ModelChart(Context context) {
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

	@SuppressWarnings("deprecation")
	@Override
	public View initView() {
		// TODO Auto-generated method stub
		String[] titles = new String[] { "ʵʱ", "�������", "�������", };//ͼ��  
	    List<double[]> x = new ArrayList<double[]>();  
	    for (int i = 0; i < titles.length; i++) {  
	      x.add(new double[] {6.36,5.99,5.81,5.86,5.45,4.4,
					7.68,4.31,6.74,5.64,5.39,4.79,
					5.68,3.32,6.07,6.41,3.25,6.64,
					6.86,4.00,6.98,6.79,4.53,6.36,
					5.74,5.30,7.47,6.49,4.00,6.93,
					5.33,4.68,4.33,6.25,5.35,5.98,
					7.07,6.66,3.19,6.18,5.98,4.74,
					5.15,6.90,7.07,5.24,4.64,6.73,
					5.42,6.08});//ÿ�������е��X����  
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
				  91,84});//����1�е��y����  
	    
	    
	    
	    values.add(new double[] {
	    		80, 80, 80, 80, 80, 80,
	    		80, 80, 80, 80, 80, 80,
	    		80, 80, 80, 80, 80, 80,
	    		80, 80, 80, 80, 80, 80,
	    		80, 80, 80, 80, 80, 80,
	    		80, 80, 80, 80, 80, 80,
	    		80, 80, 80, 80, 80, 80,
	    		80, 80, 80, 80, 80, 80,
	    		80,80});//����2�е��Y����  
	    		
	    
	    values.add(new double[] {120, 120, 120, 120, 120, 120,
	    		120, 120, 120, 120, 120, 120,
	    		120, 120, 120, 120, 120, 120,
	    		120, 120, 120, 120, 120, 120,
	    		120, 120, 120, 120, 120, 120,
	    		120, 120, 120, 120, 120, 120,
	    		120, 120, 120, 120, 120, 120,
	    		120, 120, 120, 120, 120, 120,
	    		120,120});//����3�е��Y����  
	    
	    
	   // values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });//����4�е��Y����  
	    int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN};//ÿ�����е���ɫ����  
	    PointStyle[] styles = new PointStyle[] { PointStyle.POINT, PointStyle.POINT,  
	        PointStyle.POINT};//ÿ�������е����״����  
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);//����AbstractDemoChart�еķ�������renderer.  
	    int length = renderer.getSeriesRendererCount();  
	    for (int i = 0; i < length; i++) {  
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);//����ͼ�ϵĵ�Ϊʵ��  
	    }  
	    setChartSettings(renderer, "�˶�ģ��", "�ٶ�", "����",3,8,60,140,  
	    		 Color.BLACK,Color.BLACK);//����AbstractDemoChart�еķ�������ͼ���renderer����.  
	    renderer.setXLabels(12);//����x����ʾ12����,����setChartSettings�����ֵ����Сֵ�Զ������ļ��  
	    renderer.setYLabels(10);//����y����ʾ10����,����setChartSettings�����ֵ����Сֵ�Զ������ļ��  
	    renderer.setShowGrid(true);//�Ƿ���ʾ����  
	    renderer.setXLabelsAlign(Align.RIGHT);//�̶�����̶ȱ�ע֮������λ�ù�ϵ  
	    renderer.setYLabelsAlign(Align.CENTER);//�̶�����̶ȱ�ע֮������λ�ù�ϵ  
	    renderer.setZoomButtonsVisible(true);//�Ƿ���ʾ�Ŵ���С��ť  
	    renderer.setMarginsColor(Color.argb(0,0,255,0));
	    renderer.setZoomButtonsVisible(true);//�Ƿ���ʾ�Ŵ���С��ť  
	    renderer.setPanLimits(new double[] { -10, 20, -10, 40 }); //�����϶�ʱX��Y����������ֵ��Сֵ.  
	    renderer.setZoomLimits(new double[] {  -10, 20, -10, 40 });//���÷Ŵ���СʱX��Y������������Сֵ.  
		View chart = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
				renderer,0.3f);
		return chart;
	}

}
