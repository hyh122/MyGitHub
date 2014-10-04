package ui.statisticsDisplay.activity;

import java.sql.SQLException;

import ui.statisticsDisplay.viewModel.FriendsListModel;

import com.example.androidui_sample_demo.R;

import domain.systemManaConfig.Friends;
import domain.systemManaConfig.SystemManageService;

import foundation.dataService.base.DataContext;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class FriendsMessageActivity extends Activity implements
		android.view.View.OnClickListener {
	private DataContext dataContext;
	private SystemManageService sysManagerService;
	private Button btn_addFriend;
	private Friends friend;
	private String Name;

	private int flag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends_message);
		/** 使得打开界面后不自动跳出软键盘 */
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		init();
	}

	public void init() {
		btn_addFriend = (Button) findViewById(R.id.btn_addFriend);
		btn_addFriend.setOnClickListener(this);
		dataContext = new DataContext();
		sysManagerService = new SystemManageService();

		/** 接收从上一个界面传过来的数据 */
		// 判断是从哪个界面跳转过来的
		Bundle bundle = this.getIntent().getExtras();
		Name = bundle.getString("userName");

		flag = this.getIntent().getFlags();

		// 假如是从好友列表界面跳转过来的
		if (flag == FriendsListActivity.FROMFRIENDSListACTIVITY) {
			// 假如该用户是我的好友则按钮显示为删除好友
			btn_addFriend.setText("删除该好友");

		}
		// 假如是从添加好友界面跳转过来的
		if (flag == AddFriendsActivity.FROMADDfRIENDSACTIVITY) {
			if (sysManagerService.getFriendIdByName("friendName", Name) == 0) {
				// 假如该用户不是我的好友
				friend = new Friends(1, Name, Name, "哎呦,不错哦");
			} else {
				// 假如该用户是我的好友则按钮显示为删除好友
				btn_addFriend.setText("删除该好友");
			}
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_addFriend:
			if (btn_addFriend.getText().equals("加为好友")) {
				try {
					dataContext.add(friend, Friends.class, Integer.class);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Toast.makeText(getApplication(), "添加好友成功", Toast.LENGTH_LONG)
						.show();
			} else {
				int id;
				if (flag == FriendsListActivity.FROMFRIENDSListACTIVITY) {
					// 根据好友的备注名来找到该好友的id号
					id = sysManagerService.getFriendIdByName("anotherName",
							Name);
				} else {
					// 根据好友的用户名来找到该好友的id号
					id = sysManagerService.getFriendIdByName("friendName",
							Name);

				}
				// 根据id号删除好友
				sysManagerService.deleteFriendById(id);
				Toast.makeText(getApplication(), "成功删除好友", Toast.LENGTH_LONG)
						.show();
			}
			break;
		}
	}

}
