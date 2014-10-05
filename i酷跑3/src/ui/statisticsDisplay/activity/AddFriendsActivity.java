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
	// ������ˢ��������ʾ�������ݵ�listview
	private PullRefreshListView pullRefreshListView;
	// �߳�handle
	private Handler mHandler;

	private int sizeOfAllFriend;
	private List<Friends> friends;

	private SystemManageService systemManagerService;
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
		systemManagerService = new SystemManageService();

		dataContext = new DataContext();
		pullRefreshListView = (PullRefreshListView) findViewById(R.id.pullRefreshListView);
		pullRefreshListView.setPullLoadEnable(true);
		// ��������һЩ���ݽ�ȥ
		Friends friend = new Friends(1, "֣����", "��į��", "��������");
		Friends friend1 = new Friends(2, "����", "����", "����,����Ŷ");
		Friends friend2 = new Friends(3, "������", "������", "����,����Ŷ");
		Friends friend3 = new Friends(4, "�ܽ���", "�ܽ���", "����,����Ŷ");
		Friends friend4 = new Friends(5, "�Ż���", "�Ż���", "����,����Ŷ");
		Friends friend5 = new Friends(6, "������", "������", "����,����Ŷ");
		Friends friend6 = new Friends(7, "����", "����", "����,����Ŷ");
		Friends friend7 = new Friends(8, "�մ���", "�մ���", "����,����Ŷ");
		Friends friend8 = new Friends(9, "������", "������", "����,����Ŷ");
		Friends friend9 = new Friends(10, "�ֿ���", "�ֿ���", "����,����Ŷ");
		Friends friend10 = new Friends(11, "�޴���", "�޴���", "����,����Ŷ");

		Friends friend11 = new Friends(1, "��ҫ��", "��ҫ��", "����,����Ŷ");
		Friends friend12 = new Friends(2, "֣����", "֣����", "����,����Ŷ");
		Friends friend13 = new Friends(3, "��С¡", "��С¡", "����,����Ŷ");
		Friends friend14 = new Friends(4, "�ֺ���", "�ֺ���", "����,����Ŷ");
		Friends friend15 = new Friends(5, "����", "����", "����,����Ŷ");
		Friends friend16 = new Friends(6, "��ʥ��", "��ʥ��", "����,����Ŷ");
		Friends friend17 = new Friends(7, "����", "����", "����,����Ŷ");
		Friends friend18 = new Friends(8, "�Ʋ�", "�Ʋ�", "����,����Ŷ");
		Friends friend19 = new Friends(9, "������", "������", "����,����Ŷ");
		Friends friend20 = new Friends(10, "������", "������", "����,����Ŷ");
		Friends friend21 = new Friends(11, "������", "������", "����,����Ŷ");

		Friends friend22 = new Friends(1, "��1", "��1", "����,����Ŷ");
		Friends friend23 = new Friends(2, "��2", "��2", "����,����Ŷ");
		Friends friend24 = new Friends(3, "��3", "��3", "����,����Ŷ");
		Friends friend25 = new Friends(4, "��4", "��4", "����,����Ŷ");
		Friends friend26 = new Friends(5, "��5", "��5", "����,����Ŷ");
		Friends friend27 = new Friends(6, "��6", "��6", "����,����Ŷ");
		Friends friend28 = new Friends(7, "��7", "��7", "����,����Ŷ");
		Friends friend29 = new Friends(8, "��8", "��8", "����,����Ŷ");
		Friends friend30 = new Friends(9, "��9", "��9", "����,����Ŷ");
		Friends friend31 = new Friends(10, "��10", "��10", "����,����Ŷ");
		Friends friend32 = new Friends(11, "��11", "��11", "����,����Ŷ");
		// ģ�����к���
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
		 * װ�����еĺ���
		 */
		allFriendsDataList = new ArrayList<FriendsListModel>();
		for (int i = friends.size() - 1; i >= 0; i--) {

			Friends myFriend = new Friends();
			myFriend = friends.get(i);

			FriendsListModel friendsListModal = new FriendsListModel(
					myFriend.getAnotherName(), myFriend.getPersonalWord());

			allFriendsDataList.add(friendsListModal);
		}

		// ʵ��������תƴ����
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
		 * �ս������ʱû��������(���ؼ��ֲ�ѯ����)��
		 * ����allFriendsDataListAfterFilter����allFriendsDataList
		 */
		allFriendsDataListAfterFilter = new ArrayList<FriendsListModel>();

		allFriendsDataListAfterFilter = allFriendsDataList;

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
	 * ����������е�ֵ���������ݲ�����ListView
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
