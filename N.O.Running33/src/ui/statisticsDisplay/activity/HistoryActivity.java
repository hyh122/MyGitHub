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
	ListView listView;  //����һ��ListView����  
	//intent��falg��ǩ
	private int FromHistoryUI=1;

    private List<Historyviewmodel> mlistInfo = new ArrayList<Historyviewmodel>();  //����һ��list����̬�洢Ҫ��ʾ����Ϣ  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		setTitle("��ʷ��¼");
		
		  listView=(ListView)this.findViewById(R.id.list_menu);    //��listView�벼�ֶ������  
		  dispaly();  //����Ϣ��ֵ������
	        initView();//��ʼ�� 
	        listView.setAdapter(new ListViewAdapter(mlistInfo));     
	      //����Item�ĵ���¼�  
//	        listView.setOnItemClickListener(new OnItemClickListener()
//	        {  
//	            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {  
//	                Historyviewmodel getObject = mlistInfo.get(position);   //ͨ��position��ȡ������Ķ���  
//	                int infoId = getObject.getId(); //��ȡ��Ϣid  
//	                String infoTitle = getObject.getTitle();    //��ȡ��Ϣ����  
//	                String infoDetails = getObject.getDetails();    //��ȡ��Ϣ����  
//	                  
//	                //Toast��ʾ����  
//	                Toast.makeText(HistoryActivity.this, "��ϢID:"+infoId,Toast.LENGTH_SHORT).show();  
//	            }  
//	        });  
	          listView.setOnItemClickListener(new OnItemClickListener() {
				
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO �Զ����ɵķ������
					Intent intent=new Intent(HistoryActivity.this,HistoryActivitybyone.class);
					startActivity(intent);
				
				}
			});
	        
	        
        //�����˵���ʾ  
	        listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {  
            public void onCreateContextMenu(ContextMenu conMenu, View view , ContextMenuInfo info) {  
	               // conMenu.setHeaderTitle("ѡ��˵�");  
                   // conMenu.add(0, 0, 0, "��ϸ��Ϣ");  
	                conMenu.add(0, 1, 1, "��ϸ��Ϣ");  
	               // conMenu.add(0, 2, 2, "��Ŀ��");  
	            }  
	        });  
	          
	    }  
	
	
	    //�����˵�������  
	    public boolean onContextItemSelected(MenuItem aItem) { 

	        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)aItem.getMenuInfo();  
	        switch (aItem.getItemId()) {  
	             case 0:  
	                 //Toast.makeText(HistoryActivity.this, "��������Ŀһ",Toast.LENGTH_SHORT).show();  
	            	//LayoutInflater inflater =getLayoutInflater();
//	     			View layout =inflater.inflate(R.layout.activity_history_dialog,(ViewGroup) findViewById(R.id.dialog));
//	     			new AlertDialog.Builder(this).setTitle("�˶������")
//	     							.setView(layout).setPositiveButton("ȷ��", null)
//	     							.setNegativeButton("ȡ��", null).show();
	     			//��ȡ������listview���ĸ�item
//	    			AdapterView.AdapterContextMenuInfo mlistInfo ;
//	    			mlistInfo=(AdapterContextMenuInfo) aItem.getMenuInfo();
	    			//String date=((TextView) mlistInfo.targetView.findViewById(R.id.txt_date)).getText().toString();
	    			/**
	    			 *********
	    			 ********
	    			 ******** �õ���ǰ�û���ָ��ĳ����˶�����//��Ҫ���֣�����domain����
	    			 ********
	    			 ********
	    			 */
	    		
	                 return true;  
	             /**
	              * ʵ�ַ����ܽ�����ת
	              */
	             case 1:  
	                // Toast.makeText(HistoryActivity.this, "��������Ŀ��",Toast.LENGTH_SHORT).show();  
	            	//��ȡ�������ĸ�listview
//	     			AdapterView.AdapterContextMenuInfo menuInfo2;
//	     			mlistInfo=(AdapterContextMenuInfo) aItem.getMenuInfo();
	            	 
//	     			//���listview���������
//	    			String Date=((TextView) mlistInfo.targetView.findViewById(R.id.txt_date)).getText().toString();
//	    			String avgheartrate=((TextView) mlistInfo.targetView.findViewById(R.id.tv_avgheartrate)).getText().toString();
//	    			String avgspeed=((TextView) mlistInfo.targetView.findViewById(R.id.tv_avgspeed)).getText().toString();
	    			
	    			Intent i=new Intent(HistoryActivity.this,ShareActivity.class);
	    			//���ñ�ǩ��ʶ����ʷ��¼������ת����
//	    			i.setFlags(FromHistoryUI);
//	    			//����䴫������
//	    			Bundle bundle=new Bundle();
//	    			bundle.putString("Date", Date);
//	    			bundle.putString("avgheartrate", avgheartrate);
//	    			bundle.putString("avgspeed", avgspeed);
//	    			i.putExtras(bundle);
	    			startActivity(i);
	                 return true; 
	
	                 
//	             case 2:  
//	                 Toast.makeText(HistoryActivity.this, "��������Ŀ��",Toast.LENGTH_SHORT).show();  
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
	                Historyviewmodel getInfo=(Historyviewmodel)mlistInfo.get(i);    //��ȡ��i������  
	                //����makeItemView��ʵ����һ��Item  
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
	          
	        //����Item�ĺ���  
	        private View makeItemView(String strTitle, String strText, int resId) {    
	            LayoutInflater inflater = (LayoutInflater) HistoryActivity.this    
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);    
	    
	            // ʹ��View�Ķ���itemView��R.layout.item����  
	            View itemView = inflater.inflate(R.layout.listview_history, null);  
	              
	            // ͨ��findViewById()����ʵ��R.layout.item�ڸ����  
	            TextView title = (TextView) itemView.findViewById(R.id.tv_avgheartrate);    
	            title.setText(strTitle);    //������Ӧ��ֵ  
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
           mlistInfo.add(information); //���µ�info������뵽��Ϣ�б��� 
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
