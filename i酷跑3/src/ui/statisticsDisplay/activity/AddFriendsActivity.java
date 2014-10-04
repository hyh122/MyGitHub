package ui.statisticsDisplay.activity;

import java.sql.SQLException;
import java.util.ArrayList;
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
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

public class AddFriendsActivity extends Activity implements IXListViewListener{
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
	//刚进入界面时只不显示用户列表
	private List<FriendsListModel> firstFriendsDataList;
	private List<FriendsListModel> friendsDataList;
	
	/**
	 * 标记从该页面跳转到好友信息的页面
	 */
	public static int FROMADDfRIENDSACTIVITY=1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends_addfriend);
		/**使得打开界面后不自动跳出软键盘*/
		getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		initViews();
	}

	private void initViews() {
		systemManagerService=new SystemManageService();
	
		dataContext=new DataContext();
		pullRefreshListView = (PullRefreshListView) findViewById(R.id.pullRefreshListView);
		pullRefreshListView.setPullLoadEnable(true);
		//先随机添加一些数据进去
		Friends friend=new Friends(1,"郑震培","寂寞哥","南区区草");
		Friends friend1=new Friends(2,"齐秦","齐秦","哎呦,不错哦");
		Friends friend2=new Friends(3,"王力宏","王力宏","哎呦,不错哦");
		Friends friend3=new Friends(4,"周杰伦","周杰伦","哎呦,不错哦");
		Friends friend4=new Friends(5,"张惠妹","张惠妹","哎呦,不错哦");
		Friends friend5=new Friends(6,"五月天","五月天","哎呦,不错哦");
		Friends friend6=new Friends(7,"汪峰","汪峰","哎呦,不错哦");
		Friends friend7=new Friends(8,"苏打绿","苏打绿","哎呦,不错哦");
		Friends friend8=new Friends(9,"张信哲","张信哲","哎呦,不错哦");
		Friends friend9=new Friends(10,"林俊杰","林俊杰","哎呦,不错哦");
		Friends friend10=new Friends(11,"罗大佑","罗大佑","哎呦,不错哦");
		//模拟所有好友
		friends=new ArrayList<Friends>();
		friends.add(friend);
		friends.add(friend1);
		friends.add(friend2);
		friends.add(friend3);
		friends.add(friend4);
		friends.add(friend5);
		friends.add(friend6);
		friends.add(friend7);
		friends.add(friend8);
		friends.add(friend9);
		friends.add(friend10);
		
		
		//实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();
		

		
		
		pullRefreshListView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				Bundle bundle=new Bundle();
				
				String userName=((FriendsListModel) adapter.getItem(position-1)).getName(); 
				bundle.putString("userName", userName);
				Intent intent=new Intent(AddFriendsActivity.this,FriendsMessageActivity.class);
				intent.addFlags(FROMADDfRIENDSACTIVITY);
				
				intent.putExtras(bundle);
				startActivity(intent);
				
			               
			           
			}
			   
		});
		
			
		
		sizeOfAllFriend=friends.size();
		
		//下拉刷新后显示更新数据后的界面信息
		getItemsForRefresh();
		
		//刚打开界面时不显示任何数据
				firstFriendsDataList=new ArrayList<FriendsListModel>();
				adapter = new FriendsListAdapter(this, firstFriendsDataList);
		
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
		
	
	
	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * @param filterStr
	 */
	private void filterData(String filterStr){
		List<FriendsListModel> filterDateList = new ArrayList<FriendsListModel>();
		
		if(TextUtils.isEmpty(filterStr)){
			filterDateList = firstFriendsDataList;
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

	private void getItemsForRefresh() {
		//装配到viewModal
		int i;
		friendsDataList=new ArrayList<FriendsListModel>();
		int head;
		int tail;
		if(friends.size()-1<0){
			head=-1;
		}else{
			head=friends.size()-1;
		}
		
		if(friends.size()-5<=0){
			tail=0;
			sizeOfAllFriend=0;
		}else{
			tail=friends.size()-5;
			sizeOfAllFriend=friends.size()-5;
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
				adapter = new FriendsListAdapter(AddFriendsActivity.this,friendsDataList);
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
				adapter = new FriendsListAdapter(AddFriendsActivity.this,friendsDataList);
				pullRefreshListView.setAdapter(adapter);
				onLoad();
			}
		}, 2000);
	}
	

}
