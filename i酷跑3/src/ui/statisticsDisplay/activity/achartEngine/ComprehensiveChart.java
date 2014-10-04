package ui.statisticsDisplay.activity.achartEngine;

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
		// TODO Auto-generated method stub
				String[] titles = new String[] { "ʵʱ"};//ͼ��  
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
			    		136,134,139,138,130,124, 
						  120,138,112,134,121,128,
						  118,132,130,124,127,124,
						  131,137,140,128,134,136,
						  118,120,135,121,142,132,
						  117,132,117,128,127,144,
						  131,123,135,134,124,124,
						  114,133,128,121,113,136,
						  131,124});//����1�е��y����  
			    
			    
			    
//			    values.add(new double[] {
//			    		80, 80, 80, 80, 80, 80,
//			    		80, 80, 80, 80, 80, 80,
//			    		80, 80, 80, 80, 80, 80,
//			    		80, 80, 80, 80, 80, 80,
//			    		80, 80, 80, 80, 80, 80,
//			    		80, 80, 80, 80, 80, 80,
//			    		80, 80, 80, 80, 80, 80,
//			    		80, 80, 80, 80, 80, 80,
//			    		80,80});//����2�е��Y����  
//			    		
//			    
//			    values.add(new double[] {110, 110, 110, 110, 110, 110,
//			    		110, 110, 110, 110, 110, 110,
//			    		110, 110, 110, 110, 110, 110,
//			    		110, 110, 110, 110, 110, 110,
//			    		110, 110, 110, 110, 110, 110,
//			    		110, 110, 110, 110, 110, 110,
//			    		110, 110, 110, 110, 110, 110,
//			    		110, 110, 110, 110, 110, 110,
//			    		110,110});//����3�е��Y����  
//			    
			    
			   // values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });//����4�е��Y����  
			    int[] colors = new int[] {Color.BLUE};//ÿ�����е���ɫ����  
			    PointStyle[] styles = new PointStyle[] { PointStyle.POINT};//ÿ�������е����״����  
			    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);//����AbstractDemoChart�еķ�������renderer.  
			    int length = renderer.getSeriesRendererCount();  
			    for (int i = 0; i < length; i++) {  
			      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);//����ͼ�ϵĵ�Ϊʵ��  
			    }  
			    setChartSettings(renderer, "", "�ٶ�", "����",3,8,60,160,  
			    		 Color.BLACK,Color.BLACK);//����AbstractDemoChart�еķ�������ͼ���renderer����.  
			    renderer.setXLabels(12);//����x����ʾ12����,����setChartSettings�����ֵ����Сֵ�Զ������ļ��  
			    renderer.setYLabels(10);//����y����ʾ10����,����setChartSettings�����ֵ����Сֵ�Զ������ļ��  
			    renderer.setShowGrid(true);//�Ƿ���ʾ����  
			    renderer.setXLabelsAlign(Align.RIGHT);//�̶�����̶ȱ�ע֮������λ�ù�ϵ  
			    renderer.setYLabelsAlign(Align.CENTER);//�̶�����̶ȱ�ע֮������λ�ù�ϵ  
			 //   renderer.setZoomButtonsVisible(true);//�Ƿ���ʾ�Ŵ���С��ť  
			    renderer.setMarginsColor(Color.argb(0,0,255,0));
			    renderer.setMarginsColor(Color.argb(5,222, 178, 167));
			    
			    renderer.setApplyBackgroundColor(true);//�����Ƿ���ʾ����ɫ  
			    renderer.setBackgroundColor(Color.argb(5,222, 178, 167));//���ñ���ɫ 
			    
			    renderer.setMargins(new int[] { 50, 70, 50, 10 });//����ͼ�����߿�(��/��/��/��) 
			    renderer.setPointSize(2000);//���õ�Ĵ�С(ͼ����ʾ�ĵ�Ĵ�С��ͼ���е�Ĵ�С���ᱻ����)
			    renderer.setAxisTitleTextSize(38); //������������ֵĴ�С  
			    renderer.setChartTitleTextSize(38);//?��������ͼ��������ִ�С  
			    renderer.setLabelsTextSize(38);//���ÿ̶���ʾ���ֵĴ�С(XY�ᶼ�ᱻ����)  
			    renderer.setLegendTextSize(38);//ͼ�����ִ�С 
			    
			    renderer.setPanLimits(new double[] {0,11,0,50 }); //�����϶�ʱX��Y����������ֵ��Сֵ.  
			    renderer.setZoomLimits(new double[] {0,11,0,50});//���÷Ŵ���СʱX��Y������������Сֵ.  
				View chart = ChartFactory.getCubeLineChartView(context, buildDataset(titles, x, values),
						renderer,0.3f);
				return chart;
	}

}
