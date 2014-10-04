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
	//������ˢ��������ʾ�������ݵ�listview
	private PullRefreshListView pullRefreshListView;
	//�߳�handle
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
	//�ս������ʱֻ����ʾ�û��б�
	private List<FriendsListModel> firstFriendsDataList;
	private List<FriendsListModel> friendsDataList;
	
	/**
	 * ��ǴӸ�ҳ����ת��������Ϣ��ҳ��
	 */
	public static int FROMADDfRIENDSACTIVITY=1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends_addfriend);
		/**ʹ�ô򿪽�����Զ����������*/
		getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		initViews();
	}

	private void initViews() {
		systemManagerService=new SystemManageService();
	
		dataContext=new DataContext();
		pullRefreshListView = (PullRefreshListView) findViewById(R.id.pullRefreshListView);
		pullRefreshListView.setPullLoadEnable(true);
		//��������һЩ���ݽ�ȥ
		Friends friend=new Friends(1,"֣����","��į��","��������");
		Friends friend1=new Friends(2,"����","����","����,����Ŷ");
		Friends friend2=new Friends(3,"������","������","����,����Ŷ");
		Friends friend3=new Friends(4,"�ܽ���","�ܽ���","����,����Ŷ");
		Friends friend4=new Friends(5,"�Ż���","�Ż���","����,����Ŷ");
		Friends friend5=new Friends(6,"������","������","����,����Ŷ");
		Friends friend6=new Friends(7,"����","����","����,����Ŷ");
		Friends friend7=new Friends(8,"�մ���","�մ���","����,����Ŷ");
		Friends friend8=new Friends(9,"������","������","����,����Ŷ");
		Friends friend9=new Friends(10,"�ֿ���","�ֿ���","����,����Ŷ");
		Friends friend10=new Friends(11,"�޴���","�޴���","����,����Ŷ");
		//ģ�����к���
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
		
		
		//ʵ��������תƴ����
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
		
		//����ˢ�º���ʾ�������ݺ�Ľ�����Ϣ
		getItemsForRefresh();
		
		//�մ򿪽���ʱ����ʾ�κ�����
				firstFriendsDataList=new ArrayList<FriendsListModel>();
				adapter = new FriendsListAdapter(this, firstFriendsDataList);
		
		pullRefreshListView.setAdapter(adapter);

		pullRefreshListView.setXListViewListener(this);
		mHandler = new Handler();
		   
		
		
		
		
		
		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
		
		//�������������ֵ�ĸı�����������
		mClearEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//������������ֵΪ�գ�����Ϊԭ�����б�����Ϊ���������б�
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
		//װ�䵽viewModal
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
		//װ�䵽viewModal
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
		pullRefreshListView.setRefreshTime("�ո�");
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
