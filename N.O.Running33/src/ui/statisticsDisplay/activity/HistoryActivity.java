package ui.statisticsDisplay.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ui.statisticsDisplay.fragment.MineFrament;
import ui.statisticsDisplay.viewModel.Historyviewmodel;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidui_sample_demo.R;

import foundation.dataService.util.ScreenshotTools;


  

public class HistoryActivity extends Activity {
	ListView listView;  //声明一个ListView对象  
	//intent的falg标签
	private int FromHistoryUI=1;

    private List<Historyviewmodel> mlistInfo = new ArrayList<Historyviewmodel>();  //声明一个list，动态存储要显示的信息  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		setTitle("历史记录");
		
		  listView=(ListView)this.findViewById(R.id.list_menu);    //将listView与布局对象关联  
		  dispaly();  //给信息赋值函数，
	        initView();//初始化 
	        listView.setAdapter(new ListViewAdapter(mlistInfo));     
	      //处理Item的点击事件  
//	        listView.setOnItemClickListener(new OnItemClickListener()
//	        {  
//	            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {  
//	                Historyviewmodel getObject = mlistInfo.get(position);   //通过position获取所点击的对象  
//	                int infoId = getObject.getId(); //获取信息id  
//	                String infoTitle = getObject.getTitle();    //获取信息标题  
//	                String infoDetails = getObject.getDetails();    //获取信息详情  
//	                  
//	                //Toast显示测试  
//	                Toast.makeText(HistoryActivity.this, "信息ID:"+infoId,Toast.LENGTH_SHORT).show();  
//	            }  
//	        });  
	          listView.setOnItemClickListener(new OnItemClickListener() {
				
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO 自动生成的方法存根
					Intent intent=new Intent(HistoryActivity.this,HistoryActivitybyone.class);
					startActivity(intent);
				
				}
			});
	        
	        
        //长按菜单显示  
	        listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {  
            public void onCreateContextMenu(ContextMenu conMenu, View view , ContextMenuInfo info) {  
	               // conMenu.setHeaderTitle("选择菜单");  
                   // conMenu.add(0, 0, 0, "详细信息");  
	                conMenu.add(0, 1, 1, "详细信息");  
	               // conMenu.add(0, 2, 2, "条目三");  
	            }  
	        });  
	          
	    }  
	
	
	    //长按菜单处理函数  
	    public boolean onContextItemSelected(MenuItem aItem) { 

	        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)aItem.getMenuInfo();  
	        switch (aItem.getItemId()) {  
	             case 0:  
	                 //Toast.makeText(HistoryActivity.this, "你点击了条目一",Toast.LENGTH_SHORT).show();  
	            	//LayoutInflater inflater =getLayoutInflater();
//	     			View layout =inflater.inflate(R.layout.activity_history_dialog,(ViewGroup) findViewById(R.id.dialog));
//	     			new AlertDialog.Builder(this).setTitle("运动情况单")
//	     							.setView(layout).setPositiveButton("确定", null)
//	     							.setNegativeButton("取消", null).show();
	     			//获取长按了listview的哪个item
//	    			AdapterView.AdapterContextMenuInfo mlistInfo ;
//	    			mlistInfo=(AdapterContextMenuInfo) aItem.getMenuInfo();
	    			//String date=((TextView) mlistInfo.targetView.findViewById(R.id.txt_date)).getText().toString();
	    			/**
	    			 *********
	    			 ********
	    			 ******** 得到当前用户在指定某天的运动数据//重要部分，调用domain服务
	    			 ********
	    			 ********
	    			 */
	    		
	                 return true;  
	             /**
	              * 实现分享功能界面跳转
	              */
	             case 1:  
	                // Toast.makeText(HistoryActivity.this, "你点击了条目二",Toast.LENGTH_SHORT).show();  
	            	//获取长按了哪个listview
//	     			AdapterView.AdapterContextMenuInfo menuInfo2;
//	     			mlistInfo=(AdapterContextMenuInfo) aItem.getMenuInfo();
	            	 
//	     			//获得listview上面的数据
//	    			String Date=((TextView) mlistInfo.targetView.findViewById(R.id.txt_date)).getText().toString();
//	    			String avgheartrate=((TextView) mlistInfo.targetView.findViewById(R.id.tv_avgheartrate)).getText().toString();
//	    			String avgspeed=((TextView) mlistInfo.targetView.findViewById(R.id.tv_avgspeed)).getText().toString();
	    			
	    			Intent i=new Intent(HistoryActivity.this,ShareActivity.class);
	    			//设置标签标识由历史记录界面跳转过来
//	    			i.setFlags(FromHistoryUI);
//	    			//界面间传递数据
//	    			Bundle bundle=new Bundle();
//	    			bundle.putString("Date", Date);
//	    			bundle.putString("avgheartrate", avgheartrate);
//	    			bundle.putString("avgspeed", avgspeed);
//	    			i.putExtras(bundle);
	    			startActivity(i);
	                 return true; 
	
	                 
//	             case 2:  
//	                 Toast.makeText(HistoryActivity.this, "你点击了条目三",Toast.LENGTH_SHORT).show();  
//	                 return true;  
	        }  
	        return false;  
	   }  
	      
	    
	    private void initView(){
	    	
	    	
	    	
	    	
	    	
	    }
	
	    public class ListViewAdapter extends BaseAdapter {    
	        View[] itemViews;  
	    
	        public ListViewAdapter(List<Historyviewmodel> mlistInfo) {  
	            // TODO Auto-generated constructor stub  
	            itemViews = new View[mlistInfo.size()];              
	            for(int i=0;i<mlistInfo.size();i++){  
	                Historyviewmodel getInfo=(Historyviewmodel)mlistInfo.get(i);    //获取第i个对象  
	                //调用makeItemView，实例化一个Item  
	                itemViews[i]=makeItemView(  
	                        getInfo.getTitle(), getInfo.getDetails(),getInfo.getAvatar()  
	                        );  
	            }  
	        }  
	  
	        public int getCount() {  
	            return itemViews.length;    
	        }  
	    
	        public View getItem(int position) {    
	            return itemViews[position];    
	        }    
	    
	        public long getItemId(int position) {    
	            return position;    
	        }  
	          
	        //绘制Item的函数  
	        private View makeItemView(String strTitle, String strText, int resId) {    
	            LayoutInflater inflater = (LayoutInflater) HistoryActivity.this    
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);    
	    
	            // 使用View的对象itemView与R.layout.item关联  
	            View itemView = inflater.inflate(R.layout.listview_history, null);  
	              
	            // 通过findViewById()方法实例R.layout.item内各组件  
	            TextView title = (TextView) itemView.findViewById(R.id.tv_avgheartrate);    
	            title.setText(strTitle);    //填入相应的值  
	            TextView text = (TextView) itemView.findViewById(R.id.tv_avgspeed);    
	            text.setText(strText);    
	            ImageView image = (ImageView) itemView.findViewById(R.id.img);    
	            image.setImageResource(resId);  
	              
	            return itemView;    
	        }  
	    
	          
	        public View getView(int position, View convertView, ViewGroup parent) {    
	            if (convertView == null)    
	                return itemViews[position];    
	            return convertView;  
	        }    
	    }     
	      
	    
	    
	    
	    public void dispaly(){  
	        mlistInfo.clear();  
	        

	        int a=0;
	        while(a<100){
	        Random random = new Random();
	        int i = Math.abs(random.nextInt())%100+1;
	        int j = Math.abs(random.nextInt())%100+1;
	        Historyviewmodel information = new Historyviewmodel();  
           information.setId(1000+i);  
           information.setTitle(i+"pre");  
           information.setDetails(j+"m/s");  
           information.setAvatar(R.drawable.card_title_2);  
           mlistInfo.add(information); //将新的info对象加入到信息列表中 
           a++;
           }
	    }  
	  

	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
//			Intent intent = new Intent(this, StartrunningActivity.class);
//			            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			            startActivity(intent);
//			            return true;
		
		
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	

}
