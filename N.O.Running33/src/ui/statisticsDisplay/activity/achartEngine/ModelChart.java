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
		String[] titles = new String[] { "实时", "建议高于", "建议低于", };//图例  
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
					5.42,6.08});//每个序列中点的X坐标  
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
				  91,84});//序列1中点的y坐标  
	    
	    
	    
	    values.add(new double[] {
	    		80, 80, 80, 80, 80, 80,
	    		80, 80, 80, 80, 80, 80,
	    		80, 80, 80, 80, 80, 80,
	    		80, 80, 80, 80, 80, 80,
	    		80, 80, 80, 80, 80, 80,
	    		80, 80, 80, 80, 80, 80,
	    		80, 80, 80, 80, 80, 80,
	    		80, 80, 80, 80, 80, 80,
	    		80,80});//序列2中点的Y坐标  
	    		
	    
	    values.add(new double[] {120, 120, 120, 120, 120, 120,
	    		120, 120, 120, 120, 120, 120,
	    		120, 120, 120, 120, 120, 120,
	    		120, 120, 120, 120, 120, 120,
	    		120, 120, 120, 120, 120, 120,
	    		120, 120, 120, 120, 120, 120,
	    		120, 120, 120, 120, 120, 120,
	    		120, 120, 120, 120, 120, 120,
	    		120,120});//序列3中点的Y坐标  
	    
	    
	   // values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });//序列4中点的Y坐标  
	    int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN};//每个序列的颜色设置  
	    PointStyle[] styles = new PointStyle[] { PointStyle.POINT, PointStyle.POINT,  
	        PointStyle.POINT};//每个序列中点的形状设置  
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);//调用AbstractDemoChart中的方法设置renderer.  
	    int length = renderer.getSeriesRendererCount();  
	    for (int i = 0; i < length; i++) {  
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);//设置图上的点为实心  
	    }  
	    setChartSettings(renderer, "运动模型", "速度", "心率",3,8,60,140,  
	    		 Color.BLACK,Color.BLACK);//调用AbstractDemoChart中的方法设置图表的renderer属性.  
	    renderer.setXLabels(12);//设置x轴显示12个点,根据setChartSettings的最大值和最小值自动计算点的间隔  
	    renderer.setYLabels(10);//设置y轴显示10个点,根据setChartSettings的最大值和最小值自动计算点的间隔  
	    renderer.setShowGrid(true);//是否显示网格  
	    renderer.setXLabelsAlign(Align.RIGHT);//刻度线与刻度标注之间的相对位置关系  
	    renderer.setYLabelsAlign(Align.CENTER);//刻度线与刻度标注之间的相对位置关系  
	    renderer.setZoomButtonsVisible(true);//是否显示放大缩小按钮  
	    renderer.setMarginsColor(Color.argb(0,0,255,0));
	    renderer.setZoomButtonsVisible(true);//是否显示放大缩小按钮  
	    renderer.setPanLimits(new double[] { -10, 20, -10, 40 }); //设置拖动时X轴Y轴允许的最大值最小值.  
	    renderer.setZoomLimits(new double[] {  -10, 20, -10, 40 });//设置放大缩小时X轴Y轴允许的最大最小值.  
		View chart = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
				renderer,0.3f);
		return chart;
	}

}
