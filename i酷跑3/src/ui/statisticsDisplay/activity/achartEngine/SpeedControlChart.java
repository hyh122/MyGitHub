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
		
		String[] titles = new String[] { "速度值"};//图例  
	    List<double[]> values = new ArrayList<double[]>();  
	    values.add(new double[] {6.36,5.99,5.81,5.86,5.45,4.4,
				7.68,4.31,6.74,5.64,5.39,4.79});//第一种柱子的数值  
//	    values.add(new double[] {5.68,3.32,6.07,6.41,3.25,6.64,
//				6.86,4.00,6.98,6.79,4.53,6.36});//第二中柱子的数值  
	    
	    int[] colors = new int[] { Color.rgb(105,105,105)};//两种柱子的颜色  
	    
	    XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);//调用AbstractDemoChart中的方法构建renderer.  
	    setChartSettings(renderer, " ", "时间", "速度", 0.5,  
	        12.5, 0, 10, Color.BLACK, Color.BLACK);//调用AbstractDemoChart中的方法设置renderer的一些属性. 
	    
	   renderer.getSeriesRendererAt(0).setDisplayChartValues(true);//设置柱子上是否显示数量值  
	 //   renderer.getSeriesRendererAt(1).setDisplayChartValues(true);//设置柱子上是否显示数量值  
	    renderer.setXLabels(12);//X轴的近似坐标数  
	    renderer.setYLabels(5);//Y轴的近似坐标数  
	    renderer.setXLabelsAlign(Align.LEFT);//刻度线与X轴坐标文字左侧对齐  
	    renderer.setYLabelsAlign(Align.LEFT);//Y轴与Y轴坐标文字左对齐  
	    renderer.setPanEnabled(true, false);//允许左右拖动,但不允许上下拖动.  
	    // renderer.setZoomEnabled(false);  
	    renderer.setZoomRate(1.1f);//放大的倍率  
	    renderer.setBarSpacing(0.9f);//柱子间宽度  
	    renderer.setMarginsColor(Color.argb(2,222, 178, 167));
	    
	    
	   
	    
	    renderer.setApplyBackgroundColor(true);//设置是否显示背景色  
	    renderer.setBackgroundColor(Color.argb(2,222, 178, 167));//设置背景色 
	    
	    renderer.setMargins(new int[] { 50, 70, 50, 10 });//设置图表的外边框(上/左/下/右) 
	    renderer.setPointSize(200);//设置点的大小(图上显示的点的大小和图例中点的大小都会被设置)
	    renderer.setAxisTitleTextSize(38); //设置轴标题文字的大小  
	    renderer.setChartTitleTextSize(38);//?设置整个图表标题文字大小  
	    renderer.setLabelsTextSize(38);//设置刻度显示文字的大小(XY轴都会被设置)  
	    renderer.setLegendTextSize(40);//图例文字大小 
	    
	    renderer.setPanLimits(new double[] {0, 11,0,50 }); //设置拖动时X轴Y轴允许的最大值最小值.  
	    renderer.setZoomLimits(new double[] {0, 11,0,50 });//设置放大缩小时X轴Y轴允许的最大最小值.  
//	    renderer.setZoomButtonsVisible(true);//是否显示放大缩小按钮  
		 View view=ChartFactory.getBarChartView(context, buildBarDataset(titles, values), renderer, Type.DEFAULT);
		 return view;
		    

		
		
	}

}
