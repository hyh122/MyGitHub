package ui.statisticsDisplay.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ui.statisticsDisplay.viewModel.Historybyoneviewmodel;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidui_sample_demo.R;


  

public class HistoryActivitybyone extends Activity {
	ListView listView;  //声明一个ListView对象  
	//intent的falg标签
	private int FromHistoryUI=1;
    private List<Historybyoneviewmodel> mlistInfo = new ArrayList<Historybyoneviewmodel>();  //声明一个list，动态存储要显示的信息  
	private Object position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historybyone);
		getActionBar().setDisplayHomeAsUpEnabled(true);	
		setTitle("历史记录");
		
		listView=(ListView)this.findViewById(R.id.list_menu);    //将listView与布局对象关联  
		dispaly();  //给信息赋值函数，
	    initView();//初始化 
	    listView.setAdapter(new ListViewAdapter(mlistInfo));     
	      //处理Item的点击事件  

	    listView.setOnItemClickListener(new OnItemClickListener() {	
				@Override
		public void onItemClick(AdapterView<?> arg0, View arg1,
		     int arg2, long arg3) {
					// TODO 自动生成的方法存根
					Intent intent=new Intent(HistoryActivitybyone.this,ShareActivity.class);
					startActivity(intent);
				
				}
			});
	          /*
	           * 
	           * 长按Item
	           */
	        listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {  
            public void onCreateContextMenu(ContextMenu conMenu, View view , ContextMenuInfo info) {  
	               // conMenu.setHeaderTitle("选择菜单");  
                   conMenu.add(0, 0, 0, "删除");  
	             //   conMenu.add(0, 1, 1, "分享");  
	               // conMenu.add(0, 2, 2, "条目三");  
	            }  
	        });    
	    }  
	    //长按菜单处理函数  
		public boolean onContextItemSelected(MenuItem aItem) {  
	        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)aItem.getMenuInfo();  
	        switch (aItem.getItemId()) {  
	             case 0:  
	            
	            	// mlistInfo.clear(); 
	            	 mlistInfo.remove(position); 
	            	 listView.invalidate();
	                 return true;  
	            
	           
 
	        }  
	        return false;  
	   }  
	      
	    
	    private void initView(){
	    	 
	    	
	    	
	    	
	    	
	    }
	
	    public class ListViewAdapter extends BaseAdapter {    
	        View[] itemViews;  
	    
	        public ListViewAdapter(List<Historybyoneviewmodel> mlistInfo) {  
	            // TODO Auto-generated constructor stub  
	            itemViews = new View[mlistInfo.size()];              
	            for(int i=0;i<mlistInfo.size();i++){  
	                Historybyoneviewmodel getInfo=(Historybyoneviewmodel)mlistInfo.get(i);    //获取第i个对象  
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
	            LayoutInflater inflater = (LayoutInflater) HistoryActivitybyone.this    
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);    
	    
	            // 使用View的对象itemView与R.layout.item关联  
	            View itemView = inflater.inflate(R.layout.listview_historybyone, null);  
	              
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
	         //int i=0;
//	        while(i<10){  
//	            Historybyoneviewmodel information = new Historybyoneviewmodel();  
//	            information.setId(1000+i);  
//	            information.setTitle(i+"pre");  
//	            information.setDetails(i+"m/s");  
//	            information.setAvatar(R.drawable.card_title_2);  
//	            mlistInfo.add(information); //将新的info对象加入到信息列表中  
//	            i++;  
//	        } 
	        int a=0;
	        while(a<100){
	        Random random = new Random();
	        int i = Math.abs(random.nextInt())%100+1;
	        int j = Math.abs(random.nextInt())%100+1;
	       Historybyoneviewmodel information = new Historybyoneviewmodel();  
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
