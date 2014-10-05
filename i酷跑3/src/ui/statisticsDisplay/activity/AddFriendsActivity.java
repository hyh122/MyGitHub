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

public class AddFriendsActivity extends Activity implements IXListViewListener {
	// 可上拉刷新下拉显示更多数据的listview
	private PullRefreshListView pullRefreshListView;
	// 线程handle
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
	// 装载所有的用户
	private List<FriendsListModel> allFriendsDataList;
	// 装载过滤后的所有的用户
	private List<FriendsListModel> allFriendsDataListAfterFilter;
	/**
	 * 标记从该页面跳转到好友信息的页面
	 */
	public static int FROMADDfRIENDSACTIVITY = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends_addfriend);
		/** 使得打开界面后不自动跳出软键盘 */
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		initViews();
	}

	private void initViews() {
		systemManagerService = new SystemManageService();

		dataContext = new DataContext();
		pullRefreshListView = (PullRefreshListView) findViewById(R.id.pullRefreshListView);
		pullRefreshListView.setPullLoadEnable(true);
		// 先随机添加一些数据进去
		Friends friend = new Friends(1, "郑震培", "寂寞哥", "南区区草");
		Friends friend1 = new Friends(2, "齐秦", "齐秦", "哎呦,不错哦");
		Friends friend2 = new Friends(3, "王力宏", "王力宏", "哎呦,不错哦");
		Friends friend3 = new Friends(4, "周杰伦", "周杰伦", "哎呦,不错哦");
		Friends friend4 = new Friends(5, "张惠妹", "张惠妹", "哎呦,不错哦");
		Friends friend5 = new Friends(6, "五月天", "五月天", "哎呦,不错哦");
		Friends friend6 = new Friends(7, "汪峰", "汪峰", "哎呦,不错哦");
		Friends friend7 = new Friends(8, "苏打绿", "苏打绿", "哎呦,不错哦");
		Friends friend8 = new Friends(9, "张信哲", "张信哲", "哎呦,不错哦");
		Friends friend9 = new Friends(10, "林俊杰", "林俊杰", "哎呦,不错哦");
		Friends friend10 = new Friends(11, "罗大佑", "罗大佑", "哎呦,不错哦");

		Friends friend11 = new Friends(1, "黄耀辉", "黄耀辉", "哎呦,不错哦");
		Friends friend12 = new Friends(2, "郑震培", "郑震培", "哎呦,不错哦");
		Friends friend13 = new Friends(3, "洪小隆", "洪小隆", "哎呦,不错哦");
		Friends friend14 = new Friends(4, "林浩宇", "林浩宇", "哎呦,不错哦");
		Friends friend15 = new Friends(5, "游敏", "游敏", "哎呦,不错哦");
		Friends friend16 = new Friends(6, "黄圣依", "黄圣依", "哎呦,不错哦");
		Friends friend17 = new Friends(7, "汪涵", "汪涵", "哎呦,不错哦");
		Friends friend18 = new Friends(8, "黄渤", "黄渤", "哎呦,不错哦");
		Friends friend19 = new Friends(9, "黄晓明", "黄晓明", "哎呦,不错哦");
		Friends friend20 = new Friends(10, "黄新新", "黄新新", "哎呦,不错哦");
		Friends friend21 = new Friends(11, "黄欣兰", "黄欣兰", "哎呦,不错哦");

		Friends friend22 = new Friends(1, "黄1", "黄1", "哎呦,不错哦");
		Friends friend23 = new Friends(2, "黄2", "黄2", "哎呦,不错哦");
		Friends friend24 = new Friends(3, "黄3", "黄3", "哎呦,不错哦");
		Friends friend25 = new Friends(4, "黄4", "黄4", "哎呦,不错哦");
		Friends friend26 = new Friends(5, "黄5", "黄5", "哎呦,不错哦");
		Friends friend27 = new Friends(6, "黄6", "黄6", "哎呦,不错哦");
		Friends friend28 = new Friends(7, "黄7", "黄7", "哎呦,不错哦");
		Friends friend29 = new Friends(8, "黄8", "黄8", "哎呦,不错哦");
		Friends friend30 = new Friends(9, "黄9", "黄9", "哎呦,不错哦");
		Friends friend31 = new Friends(10, "黄10", "黄10", "哎呦,不错哦");
		Friends friend32 = new Friends(11, "黄11", "黄11", "哎呦,不错哦");
		// 模拟所有好友
		friends = new ArrayList<Friends>();
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
		friends.add(friend11);
		friends.add(friend12);
		friends.add(friend13);
		friends.add(friend14);
		friends.add(friend15);
		friends.add(friend16);
		friends.add(friend17);
		friends.add(friend18);
		friends.add(friend19);
		friends.add(friend20);
		friends.add(friend21);

		friends.add(friend22);
		friends.add(friend23);
		friends.add(friend24);
		friends.add(friend25);
		friends.add(friend26);
		friends.add(friend27);
		friends.add(friend28);
		friends.add(friend29);
		friends.add(friend30);
		friends.add(friend31);
		friends.add(friend32);

		/**
		 * 装载所有的好友
		 */
		allFriendsDataList = new ArrayList<FriendsListModel>();
		for (int i = friends.size() - 1; i >= 0; i--) {

			Friends myFriend = new Friends();
			myFriend = friends.get(i);

			FriendsListModel friendsListModal = new FriendsListModel(
					myFriend.getAnotherName(), myFriend.getPersonalWord());

			allFriendsDataList.add(friendsListModal);
		}

		// 实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();

		pullRefreshListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				Bundle bundle = new Bundle();

				String userName = ((FriendsListModel) adapter
						.getItem(position - 1)).getName();
				bundle.putString("userName", userName);
				Intent intent = new Intent(AddFriendsActivity.this,
						FriendsMessageActivity.class);
				intent.addFlags(FROMADDfRIENDSACTIVITY);

				intent.putExtras(bundle);
				startActivity(intent);

			}

		});

		/**
		 * 刚进入界面时没经过过滤(按关键字查询过滤)，
		 * 所有allFriendsDataListAfterFilter等于allFriendsDataList
		 */
		allFriendsDataListAfterFilter = new ArrayList<FriendsListModel>();

		allFriendsDataListAfterFilter = allFriendsDataList;

		sizeOfAllFriend = allFriendsDataListAfterFilter.size();

		// 下拉刷新后显示更新数据后的界面信息
		getItemsForRefresh();

		// 刚打开界面时显示的数据

		adapter = new FriendsListAdapter(this, friendsDataList);

		pullRefreshListView.setAdapter(adapter);

		pullRefreshListView.setXListViewListener(this);
		mHandler = new Handler();

		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

		// 根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
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
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<FriendsListModel> filterDateList = new ArrayList<FriendsListModel>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = allFriendsDataList;

		} else {
			filterDateList.clear();
			for (FriendsListModel sortModel : allFriendsDataList) {
				String name = sortModel.getName();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// adapter.updateListView(filterDateList);
		// 将过滤后的好友信息集合赋给allFriendsDataListAfterFilter
		allFriendsDataListAfterFilter = filterDateList;
		// 得到此时allFriendsDataListAfterFilter的大小
		sizeOfAllFriend = allFriendsDataListAfterFilter.size();
		// 刷新界面
		getItemsForRefresh();
		adapter = new FriendsListAdapter(AddFriendsActivity.this,
				friendsDataList);
		pullRefreshListView.setAdapter(adapter);
	}

	private void getItemsForRefresh() {
		// 装配到viewModal
		int i;
		// 用于动态装载信息的集合类
		friendsDataList = new ArrayList<FriendsListModel>();
		int head;
		int tail;
		if (allFriendsDataListAfterFilter.size() - 1 < 0) {
			head = -1;
		} else {
			head = allFriendsDataListAfterFilter.size() - 1;
		}
		// 每次刷新都只显示每一页10条好友信息
		if (allFriendsDataListAfterFilter.size() - 10 <= 0) {
			tail = 0;
			sizeOfAllFriend = 0;
		} else {
			tail = allFriendsDataListAfterFilter.size() - 10;
			sizeOfAllFriend = allFriendsDataListAfterFilter.size() - 10;
		}
		for (i = head; i >= tail; i--) {

			FriendsListModel friendsListModal = allFriendsDataListAfterFilter
					.get(i);

			friendsDataList.add(friendsListModal);
		}

	}

	private void geneItemsForLoadMore() {
		// 装配到viewModal
		int i;

		int head;
		int tail;
		if (sizeOfAllFriend - 1 < 0) {
			head = -1;
		} else {
			head = sizeOfAllFriend - 1;
		}
		// 当第一页的好友信息有10条了，要显示更多好友信息时，通过上拉加载更多
		if (sizeOfAllFriend - 10 <= 0) {
			tail = 0;
		} else {
			tail = sizeOfAllFriend - 10;
		}
		for (i = head; i >= tail; i--) {

			FriendsListModel friendsListModal = allFriendsDataListAfterFilter
					.get(i);

			friendsDataList.add(friendsListModal);
		}
		sizeOfAllFriend = i + 1;
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
				adapter = new FriendsListAdapter(AddFriendsActivity.this,
						friendsDataList);
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
				adapter = new FriendsListAdapter(AddFriendsActivity.this,
						friendsDataList);
				pullRefreshListView.setAdapter(adapter);
				onLoad();
			}
		}, 2000);
	}

}
