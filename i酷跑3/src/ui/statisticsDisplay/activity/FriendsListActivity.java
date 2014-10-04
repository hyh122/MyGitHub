package ui.statisticsDisplay.activity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ui.statisticsDisplay.viewModel.FriendsListModel;


import com.example.androidui_sample_demo.R;

import domain.statisticsDisplay.CharacterParser;
import domain.statisticsDisplay.ClearEditText;
import domain.statisticsDisplay.FriendsListAdapter;
import domain.statisticsDisplay.PullRefreshListView;
import domain.statisticsDisplay.PullRefreshListView.IXListViewListener;
import domain.systemManaConfig.Friends;
import domain.systemManaConfig.SystemManageService;
import foundation.dataService.SystemManagerDataService;
import foundation.dataService.base.DataContext;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class FriendsListActivity extends Activity implements IXListViewListener{
	//可上拉刷新下拉显示更多数据的listview
	private PullRefreshListView pullRefreshListView;
	//线程handle
	private Handler mHandler;
	private int sizeOfAllFriend;
	private List<Friends> friends;

	private SystemManageService systemManagerService;
	private DataContext dataContext;
	
	private TextView dialog;
	private FriendsListAdapter adapter;
	private ClearEditText mClearEditText;
	
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<FriendsListModel> friendsDataList;
	
	/**
	 * 标记从该页面跳转到好友信息的页面
	 */
	public static int FROMFRIENDSListACTIVITY=0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends);
		/**使得打开界面后不自动跳出软键盘*/
		getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		initViews();
	}

	private void initViews() {
		systemManagerService=new SystemManageService();
		
		dataContext=new DataContext();
		
		
//		//先随机添加一些数据进去
//		Friends friend=new Friends(1,"郑震培","寂寞哥","南区区草");
//		Friends friend1=new Friends(2,"齐秦","齐秦","哎呦,不错哦");
//		Friends friend2=new Friends(3,"王力宏","王力宏","哎呦,不错哦");
//		Friends friend3=new Friends(4,"周杰伦","周杰伦","哎呦,不错哦");
//		Friends friend4=new Friends(5,"张惠妹","张惠妹","哎呦,不错哦");
//		Friends friend5=new Friends(6,"五月天","五月天","哎呦,不错哦");
//		Friends friend6=new Friends(7,"汪峰","汪峰","哎呦,不错哦");
//		Friends friend7=new Friends(8,"苏打绿","苏打绿","哎呦,不错哦");
//		Friends friend8=new Friends(9,"张信哲","张信哲","哎呦,不错哦");
//		Friends friend9=new Friends(10,"林俊杰","林俊杰","哎呦,不错哦");
//		Friends friend10=new Friends(11,"罗大佑","罗大佑","哎呦,不错哦");
//		systemManagerDataService.addFriend(friend);
//		systemManagerDataService.addFriend(friend1);
//		systemManagerDataService.addFriend(friend2);
//		systemManagerDataService.addFriend(friend3);
//		systemManagerDataService.addFriend(friend4);
//		systemManagerDataService.addFriend(friend5);
//		systemManagerDataService.addFriend(friend6);
//		systemManagerDataService.addFriend(friend7);
//		systemManagerDataService.addFriend(friend8);
//		systemManagerDataService.addFriend(friend9);
//		systemManagerDataService.addFriend(friend10);
		
		
		//实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();
		

		
		pullRefreshListView = (PullRefreshListView) findViewById(R.id.pullRefreshListView);
		pullRefreshListView.setPullLoadEnable(true);
		pullRefreshListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Bundle bundle=new Bundle();
				
				String userAnotherName=((FriendsListModel) adapter.getItem(position-1)).getName(); 
				bundle.putString("userName", userAnotherName);
				Intent intent=new Intent(FriendsListActivity.this,FriendsMessageActivity.class);
				intent.addFlags(FROMFRIENDSListACTIVITY);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
		//添加长按点击   
		pullRefreshListView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {   
					
					@Override  
					public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {   
						menu.setHeaderTitle("选择菜单");      
						menu.add(0, 0, 0, "修改备注");   
						menu.add(0, 1, 0, "删除好友");      
					}   
				});    
		
		
	
		
		
		//下拉刷新后显示更新数据后的界面信息
		getItemsForRefresh();
		
		//刚打开界面时不显示任何数据
				
		adapter = new FriendsListAdapter(this, friendsDataList);
		
		pullRefreshListView.setAdapter(adapter);

		pullRefreshListView.setXListViewListener(this);
		mHandler = new Handler();
		
		
		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
		
		//根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}
		//长按菜单响应函数   
		@Override  
		public boolean onContextItemSelected(MenuItem item) {   
			//setTitle("点击了长按菜单里面的第"+item.getItemId()+"个项目"); 
			if(item.getItemId()==0){
				LayoutInflater inflater =getLayoutInflater();
				View layout =inflater.inflate(R.layout.activity_friends_dialog,null);
				//获取长按了listview的哪个item
				final AdapterView.AdapterContextMenuInfo menuinfo;
				menuinfo=(AdapterContextMenuInfo) item.getMenuInfo();
				//如果修改的好友备注已经存在则显示一个提示框
				final Builder alertDialog=new AlertDialog.Builder(this).setTitle("").setMessage("该好友备注已经存在").setPositiveButton("确定", null) .setNegativeButton("取消", null);
				final EditText et_friendsAnotherName=(EditText) layout.findViewById(R.id.et_friendsAnotherName);
				
				
				
				new AlertDialog.Builder(this).setTitle("好友备注修改").setView(layout)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					
					
					String friendsAnotherName=et_friendsAnotherName.getText().toString();
					//获得listview上面的数据
					String name=((TextView) menuinfo.targetView.findViewById(R.id.name)).getText().toString();
					//根据好友的备注名来找到该好友的id号
					int id=systemManagerService.getFriendIdByName("anotherName", name);
					Friends friend=null;
					//根据id号找到该好友
					try {
						friend=dataContext.queryById(Friends.class, Integer.class, id);
						if(friend!=null){
							//假如该使用该备注的好友不存在，则修改备注成功
							if(systemManagerService.getFriendIdByName("anotherName", friendsAnotherName)==0||systemManagerService.getFriendIdByName("anotherName", friendsAnotherName)==id){
								friend.setAnotherName(friendsAnotherName);
								//更新好友信息
								dataContext.update(friend, Friends.class, Integer.class);
								
								//更新列表
								//因为自定义的pullrefreshListview的listview是从1开始的，所以menuinfo.position要减去1
								friendsDataList.get(menuinfo.position-1).setName(friendsAnotherName);
								adapter.updateListView(friendsDataList);
							}else{
								//假如使用该备注的好友存在，则提示已经有该备注的好友
								
									
							
								alertDialog.show();
								
							}
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}})
				.setNegativeButton("取消",null)
				.show();
				
				
				
			}
			else if(item.getItemId()==1){
				//获取长按了哪个listview
				final AdapterView.AdapterContextMenuInfo menuinfo;
				menuinfo=(AdapterContextMenuInfo) item.getMenuInfo();
				
				//弹出一个确认对话框
				new AlertDialog.Builder(this).setTitle("删除提示框").setMessage("确认删除该好友？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					
					
					//获得listview上面的数据
					String name=((TextView) menuinfo.targetView.findViewById(R.id.name)).getText().toString();
					//根据好友的备注名来找到该好友的id号
					int id=systemManagerService.getFriendIdByName("anotherName", name);
					//根据id号删除好友
					systemManagerService.deleteFriendById(id);
					//将该好友从列表中移除
					friendsDataList.remove(menuinfo.position-1);
					//更新列表
					adapter.updateListView(friendsDataList);
				}})
				.setNegativeButton("取消",null)
				.show();
				
			
			
				
				
			}
			return super.onContextItemSelected(item);   
		} 
		
	
//	/**
//	 * 为ListView填充数据
//	 * @param date
//	 * @return
//	 */
//	private List<FriendsListModel> filledData(String [] date){
//		List<FriendsListModel> mSortList = new ArrayList<FriendsListModel>();
//		
//		for(int i=0; i<date.length; i++){
//			FriendsListModel sortModel = new FriendsListModel();
//			sortModel.setName(date[i]);
//			//汉字转换成拼音
//			String pinyin = characterParser.getSelling(date[i]);
//			String sortString = pinyin.substring(0, 1).toUpperCase();
//			
//		
//			mSortList.add(sortModel);
//		}
//		return mSortList;
//		
//	}
	
	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * @param filterStr
	 */
	private void filterData(String filterStr){
		List<FriendsListModel> filterDateList = new ArrayList<FriendsListModel>();
		
		if(TextUtils.isEmpty(filterStr)){
			filterDateList = friendsDataList;
		}else{
			filterDateList.clear();
			for(FriendsListModel sortModel : friendsDataList){
				String name = sortModel.getName();
				if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
					filterDateList.add(sortModel);
				}
			}
		}
		
	
		
		adapter.updateListView(filterDateList);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {//创建系统功能菜单
		// TODO Auto-generated method stub
		 //将自己写的菜单项添加到actiovbar
		 MenuInflater inflater = getMenuInflater();

		 inflater.inflate(R.menu.menu_addfriend, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.addfriend:
			Intent intent=new Intent(FriendsListActivity.this,AddFriendsActivity.class);
			startActivity(intent);
			break;
		
		
	}
		return super.onOptionsItemSelected(item);
}
	private void getItemsForRefresh() {
		//每次刷新都得先从数据库获得最新的数据
		try {
			friends = dataContext.queryForAll(Friends.class, Integer.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sizeOfAllFriend=friends.size();
		
		//装配到viewModal
		int i;
		friendsDataList=new ArrayList<FriendsListModel>();
		int head;
		int tail;
		if(friends.size()-1<0){
			head=-1;
		}else{
			head=friends.size()-1;//7
		}
		
		if(friends.size()-5<=0){
			tail=0;
			sizeOfAllFriend=0;
		}else{
			tail=friends.size()-5;//3
			sizeOfAllFriend=friends.size()-5;//3
		}
		for(i=head;i>=tail;i--){
			
			
			Friends myFriend=new Friends();
			myFriend=friends.get(i);
			
			
			FriendsListModel friendsListModal=new FriendsListModel(myFriend.getAnotherName(),myFriend.getPersonalWord());
			

			friendsDataList.add(friendsListModal);
		}
		
	}
	private void geneItemsForLoadMore() {
		
		//装配到viewModal
		int i;
		
		int head;
		int tail;
		if(sizeOfAllFriend-1<0){
			head=-1;
		}else{
			head=sizeOfAllFriend-1;
		}
		if(sizeOfAllFriend-5<=0){
			tail=0;
		}else{
			tail=sizeOfAllFriend-5;
		}
		for(i=head;i>=tail;i--){
			
			
			Friends myFriend=new Friends();
			myFriend=friends.get(i);
			
			
			FriendsListModel friendsListModal=new FriendsListModel(myFriend.getAnotherName(),myFriend.getPersonalWord());
			

			friendsDataList.add(friendsListModal);
		}
		sizeOfAllFriend=i+1;
	}

	private void onLoad() {
		pullRefreshListView.stopRefresh();
		pullRefreshListView.stopLoadMore();
		pullRefreshListView.setRefreshTime("刚刚");
	}
	
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				
				
				getItemsForRefresh();
				adapter = new FriendsListAdapter(FriendsListActivity.this,friendsDataList);
				pullRefreshListView.setAdapter(adapter);
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				geneItemsForLoadMore();
				adapter.notifyDataSetChanged();
				adapter = new FriendsListAdapter(FriendsListActivity.this,friendsDataList);
				pullRefreshListView.setAdapter(adapter);
				onLoad();
			}
		}, 2000);
	}

}
