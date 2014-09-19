package ui.statisticsDisplay.activity;

import java.util.Random;

import ui.statisticsDisplay.viewModel.ShareviewModel;




import com.example.androidui_sample_demo.R;

import foundation.dataService.util.ScreenshotTools;




import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class ShareActivity  extends Activity{
	    
	 Context mContext;

     private  TextView date,step,colouis,usetime,avgheartrate,avgspeed;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle("分享 ");
		
	    step = (TextView) findViewById(R.id.tv_stepnumber);
        colouis= (TextView) findViewById(R.id.tv_colouis);
       // date= (TextView) findViewById(R.id.tv_date);
        usetime=(TextView) findViewById(R.id.tv_usetime);
        avgheartrate=(TextView) findViewById(R.id.tv_avgheartrate);
        avgspeed=(TextView) findViewById(R.id.tv_avgspeed);
        
        mContext=this;
      
        //显示数据
        display();            
	}

	public void getmodel(){
		 ShareviewModel information=new ShareviewModel();
		 information.getAvgheartrate();
		 information.getAvgspeed();
		 information.getCalouis();
//		 information.getDate();
		 information.getStep();
		 information.getUsetime();
	}

		
//	//显示当前用户的运动数据
	public void display(){
	 //显示当前用户当前的运动数据
			
		 Random random = new Random();
	        int i = Math.abs(random.nextInt())%100+1;
	        int j = Math.abs(random.nextInt())%100+1;
//	        int a = Math.abs(random.nextInt())%100+1;
	        int b = Math.abs(random.nextInt())%100+1;
	        int c = Math.abs(random.nextInt())%100+1;  
	        int d = Math.abs(random.nextInt())%100+1; 
	        
	        step.setText(i+"km");
	        colouis.setText(j+"");
//	        date.setText(a+"");
	        usetime.setText(b+"s");
	        avgheartrate.setText(c+"次/分");
	        avgspeed.setText(d+"m/s");
			}
		
		
		

	
	
	

		@Override
		
		    public boolean onCreateOptionsMenu(Menu menu)
		
		    {
		
		        MenuInflater inflater = getMenuInflater();
	
		        inflater.inflate(R.menu.menu_share, menu);
		
		        return true;
		
		    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			//this.finish();
			Intent intent = new Intent(this, HistoryActivity.class);
			            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			            startActivity(intent);
			            return true;

		case R.id.Share:
			  ScreenshotTools.takeScreenShotToEmail(mContext, ShareActivity.this);  
			break;

		}
		return super.onOptionsItemSelected(item);
	}
}
