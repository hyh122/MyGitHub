package ui.statisticsDisplay.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import ui.statisticsDisplay.fragment.MineFrament;
import ui.statisticsDisplay.viewModel.Historyviewmodel;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.format.DateFormat;
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
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.BaseAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidui_sample_demo.R;

import com.j256.ormlite.dao.ForeignCollection;

import domain.dataCollect.base.MinuteSportData;
import domain.dataCollect.base.OneSport;
import domain.statisticsDisplay.ChildListItem;
import domain.statisticsDisplay.GroupListItem;


import foundation.dataService.DataCollectDataService;
import foundation.dataService.base.DataContext;
import foundation.dataService.util.DataFormat;
import foundation.dataService.util.DateService;
import foundation.dataService.util.ScreenshotTools;


  

public class HistoryActivity extends Activity {
  
	//intent的falg标签
	private int FromHistoryUI=1;
	public MyExpandableListAdapter myExpandableListadapter;  //自定义ExpandableListAdapter适配器
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		setTitle("历史记录");
		initView();
		     
	
	       
	        
	  
	          
	    }  
	
	
	//初始化类
			private void initView(){
				
				
			
				
				myExpandableListadapter = new MyExpandableListAdapter(this);
				ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.list);
				expandableListView.setAdapter(myExpandableListadapter);
				//设置item点击监听事件
				expandableListView.setOnChildClickListener(new childOnclickListener());
				

				
//				//设置item长按的监听器
//				expandableListView.setOnItemLongClickListener(new OnItemLongClickListener() {
	//
//					@Override
//					public boolean onItemLongClick(AdapterView<?> parent, View view,
//							int position, long id) {
//						// TODO Auto-generated method stub
//						Toast.makeText(
//								ExpandableList.this,
//								"你点击了" +adapter.getChildrenCount(position),
//								Toast.LENGTH_SHORT).show();
//						return false;
//					}
//				});
			}
		private class MyExpandableListAdapter implements ExpandableListAdapter {
			private Context context;
			private LayoutInflater inflater;
			private ChildListItem listitem = null;
			private List<GroupListItem> groupListItems;//存放组视图的集合
			private List<ArrayList<ChildListItem>> childChildListItems;  //存放子视图的集合
			DataCollectDataService dataCollectDataService=null;
			List<OneSport> oneSports=null;
		
			public MyExpandableListAdapter(Context context){
				super();
				this.context = context;
				inflater = LayoutInflater.from(context);
				dataCollectDataService=new DataCollectDataService();
				oneSports=dataCollectDataService.getAllOneSport();
				groupListItems=new ArrayList<GroupListItem>();
				childChildListItems=new ArrayList<ArrayList<ChildListItem>>();
				/**
				 * 装载viewModal
				 */
				loadViewModal(dealAllOneSport(oneSports));
				
//				for(int i=0;i<10;i++){
//					GroupListItem groupListItem=new GroupListItem("2014-09-0"+i);
//					groupListItems.add(groupListItem);
//					List<ChildListItem> childListItems=new ArrayList<ChildListItem>();
//					for(int j=0;j<10;j++){
//					ChildListItem childListItem=new ChildListItem(100+j*10,60+j,5+j,DateService.getDate());
//					childListItems.add(childListItem);
//					}
//					childChildListItems.add((ArrayList<ChildListItem>) childListItems);
//				}
//				

			}
			/**
			 * 
			 * @param oneSports 所有的运动记录
			 * @return 按天将所有的oneSport分割
			 */
			public List<ArrayList<OneSport>> dealAllOneSport(List<OneSport> oneSports){
				List<ArrayList<OneSport>> LoneSports=new ArrayList<ArrayList<OneSport>>();
				List<OneSport> LoneSport=new ArrayList<OneSport>();
				if(oneSports==null){
					return null;
				}
				for(int i=oneSports.size()-1;i>=0;i--){
					LoneSport.add(oneSports.get(i));
					if((i-1)>=0){
					if(oneSports.get(i).getDate().equals(oneSports.get(i-1).getDate())){
						
					}else{
						LoneSports.add((ArrayList<OneSport>) LoneSport);
						LoneSport.clear();
						
					}
					}else{
						LoneSports.add((ArrayList<OneSport>) LoneSport);
					}
				}
				return LoneSports;
				
			}
			public void loadViewModal(List<ArrayList<OneSport>> LoneSports){
				for(int i=0;i<LoneSports.size();i++){
					GroupListItem groupListItem=new GroupListItem(LoneSports.get(i).get(0).getDate());
					groupListItems.add(groupListItem);
					List<ChildListItem> childListItems=new ArrayList<ChildListItem>();
					
					for(int j=0;j<LoneSports.get(i).size();j++){
					List<MinuteSportData> minuteSportDatas=new ArrayList<MinuteSportData>();
					ForeignCollection<MinuteSportData> FminuteSportDatas=LoneSports.get(i).get(j).getMinuteSportDatas();

					Iterator ite=FminuteSportDatas.iterator();
					while(ite.hasNext()){
						MinuteSportData minuteSportData=new MinuteSportData();
						minuteSportData=(MinuteSportData) ite.next();
						//将该学生的所有选课科目情况添加到一个集合中
						minuteSportDatas.add(minuteSportData);
						
					}
					/** oneSport里面有多少条minuteSportData*/
					int size=minuteSportDatas.size();
					/** oneSport的开始时间*/
					Date startTime=LoneSports.get(i).get(j).getStartTime();
					
					int totalHeartRate=0;
					double totalSpeed=0;
					for(int k=0;k<minuteSportDatas.size();k++){
						
						
						totalHeartRate+=minuteSportDatas.get(k).getHeartRate();
						totalSpeed+=minuteSportDatas.get(k).getSpeed();
						
					}
					/** oneSport的平均心率*/
					int avgHeartRate=totalHeartRate/size;
					/** oneSport的平均速度*/
					double avgSpeed=totalSpeed/size;
					
					ChildListItem childListItem=new ChildListItem(100+j*10,avgHeartRate,avgSpeed,startTime);
					childListItems.add(childListItem);
					}
					childChildListItems.add((ArrayList<ChildListItem>) childListItems);
				}
			}
			
			//重写ExpandableListAdapter中的各个方法
			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				return groupListItems.size();
			}

			@Override
			public GroupListItem getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				return groupListItems.get(groupPosition);
			}

			@Override
			public long getGroupId(int groupPosition) {
				// TODO Auto-generated method stub
				return groupPosition;
			}

			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				return childChildListItems.get(groupPosition).size();
			}

			@Override
			public ChildListItem getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return childChildListItems.get(groupPosition).get(childPosition);
			}

			@Override
			public long getChildId(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return childPosition;
			}

			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return true;
			}
			class GroupItems{
				TextView tv_Date;
			
			}
			class ChildItems{
				TextView tv_distance;
				TextView tv_avgHeartRate;
				TextView tv_avgSpeed;
				TextView tv_time;
			}
			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				if (convertView == null) {
					convertView = inflater.inflate(R.layout.grouplistitem, null);       //绑定layout文件
				}
				GroupItems groupItems=new GroupItems();
				//绑定子项ui
				groupItems.tv_Date=(TextView)convertView.findViewById(R.id.tv_date);
				groupItems.tv_Date.setText(getGroup(groupPosition).getDate());
//				RL_tem=(RelativeLayout)convertView.findViewById(R.id.item);
//				convertView.setTag(it);
				return convertView;

			}

			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				
				if (convertView == null) {
					convertView = inflater.inflate(R.layout.childlistitem, null);       //绑定layout文件
				}
				ChildItems childItems = new ChildItems();
				//绑定子项ui
				childItems.tv_distance =(TextView)convertView.findViewById(R.id.tv_distance);
				childItems.tv_avgHeartRate = (TextView)convertView.findViewById(R.id.tv_avgHeartRate);
				childItems.tv_avgSpeed = (TextView)convertView.findViewById(R.id.tv_avgSpeed);
				childItems.tv_time = (TextView)convertView.findViewById(R.id.tv_time);

			
				
				childItems.tv_distance.setText(""+getChild(groupPosition, childPosition).getDistance());
				childItems.tv_avgHeartRate.setText(""+getChild(groupPosition, childPosition).getAvgHeartRate());
				childItems.tv_avgSpeed.setText(""+DataFormat.setDataFormat(getChild(groupPosition, childPosition).getAvgSpeed(),"0.0"));
				childItems.tv_time.setText(DateService.getDateOfMinFormat(getChild(groupPosition, childPosition).getTime()));

				return convertView;

			}

			@Override
			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				// TODO Auto-generated method stub
				return true;
			}


			@Override
			public void registerDataSetObserver(DataSetObserver observer) {
				// TODO Auto-generated method stub
				
			}


			@Override
			public void unregisterDataSetObserver(DataSetObserver observer) {
				// TODO Auto-generated method stub
				
			}


			@Override
			public boolean areAllItemsEnabled() {
				// TODO Auto-generated method stub
				return false;
			}


			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}


			@Override
			public void onGroupExpanded(int groupPosition) {
				// TODO Auto-generated method stub
				
			}


			@Override
			public void onGroupCollapsed(int groupPosition) {
				// TODO Auto-generated method stub
				
			}


			@Override
			public long getCombinedChildId(long groupId, long childId) {
				// TODO Auto-generated method stub
				return 0;
			}


			@Override
			public long getCombinedGroupId(long groupId) {
				// TODO Auto-generated method stub
				return 0;
			}

		};
		//设置item点击的监听器
		private class childOnclickListener implements OnChildClickListener{

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(
				HistoryActivity.this,
				"你点击了"+myExpandableListadapter.getChild(groupPosition, childPosition),
				Toast.LENGTH_SHORT).show();
				return false;
			}
			
		}
			
		


		
		
//		expandableListView.setOnChildClickListener(new OnChildClickListener() {
	//
//			@Override
//			public boolean onChildClick(ExpandableListView parent, View v,
//					int groupPosition, int childPosition, long id) {
	//
//				Toast.makeText(
//						ExpandableList.this,
//						"你点击了"+adapter.getChild(groupPosition, childPosition),
//						Toast.LENGTH_SHORT).show();
	//
//				return false;
//			}
//		});

	
	
	
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
