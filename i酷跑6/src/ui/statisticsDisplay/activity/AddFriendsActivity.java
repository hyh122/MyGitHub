package ui.statisticsDisplay.activity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ui.statisticsDisplay.viewModel.FriendsListModel;

import com.example.androidui_sample_demo.R;
import com.example.androidui_sample_demo.R.drawable;

import domain.statisticsDisplay.CharacterParser;
import domain.statisticsDisplay.ClearEditText;
import domain.statisticsDisplay.FriendsListAdapter;
import domain.statisticsDisplay.PullRefreshListView;
import domain.statisticsDisplay.PullRefreshListView.IXListViewListener;
import domain.systemManaConfig.Friends;
import domain.systemManaConfig.SystemManageService;
import domain.systemManaConfig.User;
import foundation.dataService.SystemManagerDataService;
import foundation.dataService.base.DataContext;
import foundation.dataService.util.ImageTools;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

public class AddFriendsActivity extends Activity implements IXListViewListener {
	// ������ˢ��������ʾ�������ݵ�listview
	private PullRefreshListView pullRefreshListView;
	// �߳�handle
	private Handler mHandler;
	private FrameLayout frameLayout;
	private int sizeOfAllFriend;
	private List<Friends> friends;
	private List<User> users;
	//private SystemManageService systemManagerService;
	private DataContext dataContext;

	private TextView dialog;
	private FriendsListAdapter adapter;
	private ClearEditText mClearEditText;

	/**
	 * ����ת����ƴ������
	 */
	private CharacterParser characterParser;

	private List<FriendsListModel> friendsDataList;
	
	// װ�����е��û�
	private List<FriendsListModel> allFriendsDataList;
	// װ�ع��˺�����е��û�
	private List<FriendsListModel> allFriendsDataListAfterFilter;
	/**
	 * ��ǴӸ�ҳ����ת��������Ϣ��ҳ��
	 */
	public static int FROMADDfRIENDSACTIVITY = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends_addfriend);
		/** ʹ�ô򿪽�����Զ���������� */
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		initViews();
	}

	private void initViews() {
		//systemManagerService = new SystemManageService();

		dataContext = new DataContext();
		frameLayout=(FrameLayout) findViewById(R.id.frameLayoutOfAddFriends);
		pullRefreshListView = (PullRefreshListView) findViewById(R.id.pullRefreshListView);
		//��listview��Ϊ���ɼ�
		pullRefreshListView.setVisibility(View.GONE);
		pullRefreshListView.setPullLoadEnable(true);
		
//		//ģ������
//		users=new ArrayList<User>();
//		User user=new User();
//		user.setBirthday("1994-06-08");
//		user.setCity("����");
//		user.setEmail("1215605211@qq.com");
//		user.setHeight(176);
//		user.setNickName("��ҫ��");
//		user.setPassword("123456");
//		user.setPersonalword("���ܲ������ز�");
//		user.setProtrait(ImageTools.drawableToBytes(getResources().getDrawable(R.drawable.start_running_title)));
//		user.setSex("��");
//		user.setWeight(66);
//		user.setLogin(true);
//		
//		User user2=new User();
//		user2.setBirthday("1994-06-08");
//		user2.setCity("����");
//		user2.setEmail("2454703958@qq.com");
//		user2.setHeight(176);
//		user2.setNickName("֣����");
//		user2.setPassword("123456");
//		user2.setPersonalword("����ʦ����ѧ��������");
//		user2.setProtrait(ImageTools.drawableToBytes(getResources().getDrawable(R.drawable.card_title_2)));
//		user2.setSex("��");
//		user2.setWeight(66);
//		user2.setLogin(false);
//		try {
//		
//		dataContext.add(user, User.class, String.class);
//		dataContext.add(user2, User.class, String.class);
//		} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		}

		//ģ������
		users=new ArrayList<User>();
		try {
			users=dataContext.queryForAll(User.class, String.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//װ�����е��û���Ϣ��modal
		loadUsersToModal(users);
		// ʵ��������תƴ����
		characterParser = CharacterParser.getInstance();

		pullRefreshListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				Bundle bundle = new Bundle();

				String email = ((FriendsListModel) adapter
						.getItem(position - 1)).getEmail();
				bundle.putString("email", email);
				Intent intent = new Intent(AddFriendsActivity.this,
						FriendsMessageActivity.class);
				intent.addFlags(FROMADDfRIENDSACTIVITY);

				intent.putExtras(bundle);
				startActivity(intent);

			}

		});
		
		

		/**
		 * �ս������ʱû��������(���ؼ��ֲ�ѯ����)��
		 * ����allFriendsDataListAfterFilter����allFriendsDataList
		 */
		allFriendsDataListAfterFilter = new ArrayList<FriendsListModel>();

		

		sizeOfAllFriend = allFriendsDataListAfterFilter.size();

		// ����ˢ�º���ʾ�������ݺ�Ľ�����Ϣ
		getItemsForRefresh();

		// �մ򿪽���ʱ��ʾ������

		adapter = new FriendsListAdapter(this, friendsDataList);

		pullRefreshListView.setAdapter(adapter);

		pullRefreshListView.setXListViewListener(this);
		mHandler = new Handler();

		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

		// �������������ֵ�ĸı�����������
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// ������������ֵΪ�գ�����Ϊԭ�����б�����Ϊ���������б�
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
	 * װ�����е��û���Ϣ��viewModal
	 */
	public void loadUsersToModal(List<User> users){
		/**
		 * װ�����еĺ���
		 */
		allFriendsDataList = new ArrayList<FriendsListModel>();
		for (int i = users.size() - 1; i >= 0; i--) {

			User user = new User();
			user= users.get(i);

			FriendsListModel friendsListModal = new FriendsListModel(
					user.getNickName(), user.getEmail(),user.getPersonalword(),user.getProtrait());

			allFriendsDataList.add(friendsListModal);
		}

	}

	/**
	 * ����������е�ֵ���������ݲ�����ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		//��listview��Ϊ�ɼ�
		pullRefreshListView.setVisibility(View.VISIBLE);
		//������ͼ��Ϊ���ɼ�
		frameLayout.setBackground(null);
		List<FriendsListModel> filterDateList = new ArrayList<FriendsListModel>();
		List<FriendsListModel> nullDataList=new ArrayList<FriendsListModel>();
		if (TextUtils.isEmpty(filterStr)) {
			//��listv��Ϊ���ɼ�
			pullRefreshListView.setVisibility(View.GONE);
			//���ñ���ͼ
			frameLayout.setBackground(getResources().getDrawable(R.drawable.start_running_title));
			filterDateList = nullDataList;

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
		// �����˺�ĺ�����Ϣ���ϸ���allFriendsDataListAfterFilter
		allFriendsDataListAfterFilter = filterDateList;
		// �õ���ʱallFriendsDataListAfterFilter�Ĵ�С
		sizeOfAllFriend = allFriendsDataListAfterFilter.size();
		// ˢ�½���
		getItemsForRefresh();
		adapter = new FriendsListAdapter(AddFriendsActivity.this,
				friendsDataList);
		pullRefreshListView.setAdapter(adapter);
	}

	private void getItemsForRefresh() {
		// װ�䵽viewModal
		int i;
		// ���ڶ�̬װ����Ϣ�ļ�����
		friendsDataList = new ArrayList<FriendsListModel>();
		int head;
		int tail;
		if (allFriendsDataListAfterFilter.size() - 1 < 0) {
			head = -1;
		} else {
			head = allFriendsDataListAfterFilter.size() - 1;
		}
		// ÿ��ˢ�¶�ֻ��ʾÿһҳ10��������Ϣ
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
		// װ�䵽viewModal
		int i;

		int head;
		int tail;
		if (sizeOfAllFriend - 1 < 0) {
			head = -1;
		} else {
			head = sizeOfAllFriend - 1;
		}
		// ����һҳ�ĺ�����Ϣ��10���ˣ�Ҫ��ʾ���������Ϣʱ��ͨ���������ظ���
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
		pullRefreshListView.setRefreshTime("�ո�");
	}

	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {

				getItemsForRefresh();
				adapter.updateListView(friendsDataList);
//				adapter = new FriendsListAdapter(AddFriendsActivity.this,
//						friendsDataList);
//				pullRefreshListView.setAdapter(adapter);
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
				adapter.updateListView(friendsDataList);
//				adapter = new FriendsListAdapter(AddFriendsActivity.this,
//						friendsDataList);
//				pullRefreshListView.setAdapter(adapter);
				onLoad();
			}
		}, 2000);
	}

}
