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
	private List<FriendsListModel> friendsDataList;
	
	/**
	 * ��ǴӸ�ҳ����ת��������Ϣ��ҳ��
	 */
	public static int FROMFRIENDSListACTIVITY=0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends);
		/**ʹ�ô򿪽�����Զ����������*/
		getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		initViews();
	}

	private void initViews() {
		systemManagerService=new SystemManageService();
		
		dataContext=new DataContext();
		
		
//		//��������һЩ���ݽ�ȥ
//		Friends friend=new Friends(1,"֣����","��į��","��������");
//		Friends friend1=new Friends(2,"����","����","����,����Ŷ");
//		Friends friend2=new Friends(3,"������","������","����,����Ŷ");
//		Friends friend3=new Friends(4,"�ܽ���","�ܽ���","����,����Ŷ");
//		Friends friend4=new Friends(5,"�Ż���","�Ż���","����,����Ŷ");
//		Friends friend5=new Friends(6,"������","������","����,����Ŷ");
//		Friends friend6=new Friends(7,"����","����","����,����Ŷ");
//		Friends friend7=new Friends(8,"�մ���","�մ���","����,����Ŷ");
//		Friends friend8=new Friends(9,"������","������","����,����Ŷ");
//		Friends friend9=new Friends(10,"�ֿ���","�ֿ���","����,����Ŷ");
//		Friends friend10=new Friends(11,"�޴���","�޴���","����,����Ŷ");
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
		
		
		//ʵ��������תƴ����
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
		//��ӳ������   
		pullRefreshListView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {   
					
					@Override  
					public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {   
						menu.setHeaderTitle("ѡ��˵�");      
						menu.add(0, 0, 0, "�޸ı�ע");   
						menu.add(0, 1, 0, "ɾ������");      
					}   
				});    
		
		
	
		
		
		//����ˢ�º���ʾ�������ݺ�Ľ�����Ϣ
		getItemsForRefresh();
		
		//�մ򿪽���ʱ����ʾ�κ�����
				
		adapter = new FriendsListAdapter(this, friendsDataList);
		
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
		//�����˵���Ӧ����   
		@Override  
		public boolean onContextItemSelected(MenuItem item) {   
			//setTitle("����˳����˵�����ĵ�"+item.getItemId()+"����Ŀ"); 
			if(item.getItemId()==0){
				LayoutInflater inflater =getLayoutInflater();
				View layout =inflater.inflate(R.layout.activity_friends_dialog,null);
				//��ȡ������listview���ĸ�item
				final AdapterView.AdapterContextMenuInfo menuinfo;
				menuinfo=(AdapterContextMenuInfo) item.getMenuInfo();
				//����޸ĵĺ��ѱ�ע�Ѿ���������ʾһ����ʾ��
				final Builder alertDialog=new AlertDialog.Builder(this).setTitle("").setMessage("�ú��ѱ�ע�Ѿ�����").setPositiveButton("ȷ��", null) .setNegativeButton("ȡ��", null);
				final EditText et_friendsAnotherName=(EditText) layout.findViewById(R.id.et_friendsAnotherName);
				
				
				
				new AlertDialog.Builder(this).setTitle("���ѱ�ע�޸�").setView(layout)
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					
					
					String friendsAnotherName=et_friendsAnotherName.getText().toString();
					//���listview���������
					String name=((TextView) menuinfo.targetView.findViewById(R.id.name)).getText().toString();
					//���ݺ��ѵı�ע�����ҵ��ú��ѵ�id��
					int id=systemManagerService.getFriendIdByName("anotherName", name);
					Friends friend=null;
					//����id���ҵ��ú���
					try {
						friend=dataContext.queryById(Friends.class, Integer.class, id);
						if(friend!=null){
							//�����ʹ�øñ�ע�ĺ��Ѳ����ڣ����޸ı�ע�ɹ�
							if(systemManagerService.getFriendIdByName("anotherName", friendsAnotherName)==0||systemManagerService.getFriendIdByName("anotherName", friendsAnotherName)==id){
								friend.setAnotherName(friendsAnotherName);
								//���º�����Ϣ
								dataContext.update(friend, Friends.class, Integer.class);
								
								//�����б�
								//��Ϊ�Զ����pullrefreshListview��listview�Ǵ�1��ʼ�ģ�����menuinfo.positionҪ��ȥ1
								friendsDataList.get(menuinfo.position-1).setName(friendsAnotherName);
								adapter.updateListView(friendsDataList);
							}else{
								//����ʹ�øñ�ע�ĺ��Ѵ��ڣ�����ʾ�Ѿ��иñ�ע�ĺ���
								
									
							
								alertDialog.show();
								
							}
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}})
				.setNegativeButton("ȡ��",null)
				.show();
				
				
				
			}
			else if(item.getItemId()==1){
				//��ȡ�������ĸ�listview
				final AdapterView.AdapterContextMenuInfo menuinfo;
				menuinfo=(AdapterContextMenuInfo) item.getMenuInfo();
				
				//����һ��ȷ�϶Ի���
				new AlertDialog.Builder(this).setTitle("ɾ����ʾ��").setMessage("ȷ��ɾ���ú��ѣ�")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					
					
					//���listview���������
					String name=((TextView) menuinfo.targetView.findViewById(R.id.name)).getText().toString();
					//���ݺ��ѵı�ע�����ҵ��ú��ѵ�id��
					int id=systemManagerService.getFriendIdByName("anotherName", name);
					//����id��ɾ������
					systemManagerService.deleteFriendById(id);
					//���ú��Ѵ��б����Ƴ�
					friendsDataList.remove(menuinfo.position-1);
					//�����б�
					adapter.updateListView(friendsDataList);
				}})
				.setNegativeButton("ȡ��",null)
				.show();
				
			
			
				
				
			}
			return super.onContextItemSelected(item);   
		} 
		
	
//	/**
//	 * ΪListView�������
//	 * @param date
//	 * @return
//	 */
//	private List<FriendsListModel> filledData(String [] date){
//		List<FriendsListModel> mSortList = new ArrayList<FriendsListModel>();
//		
//		for(int i=0; i<date.length; i++){
//			FriendsListModel sortModel = new FriendsListModel();
//			sortModel.setName(date[i]);
//			//����ת����ƴ��
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
	 * ����������е�ֵ���������ݲ�����ListView
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
	public boolean onCreateOptionsMenu(Menu menu) {//����ϵͳ���ܲ˵�
		// TODO Auto-generated method stub
		 //���Լ�д�Ĳ˵�����ӵ�actiovbar
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
		//ÿ��ˢ�¶����ȴ����ݿ������µ�����
		try {
			friends = dataContext.queryForAll(Friends.class, Integer.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sizeOfAllFriend=friends.size();
		
		//װ�䵽viewModal
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
