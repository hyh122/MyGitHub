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
		/** ʹ�ô򿪽�����Զ���������� */
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		init();
	}

	public void init() {
		btn_addFriend = (Button) findViewById(R.id.btn_addFriend);
		btn_addFriend.setOnClickListener(this);
		dataContext = new DataContext();
		sysManagerService = new SystemManageService();

		/** ���մ���һ�����洫���������� */
		// �ж��Ǵ��ĸ�������ת������
		Bundle bundle = this.getIntent().getExtras();
		Name = bundle.getString("userName");

		flag = this.getIntent().getFlags();

		// �����ǴӺ����б������ת������
		if (flag == FriendsListActivity.FROMFRIENDSListACTIVITY) {
			// ������û����ҵĺ�����ť��ʾΪɾ������
			btn_addFriend.setText("ɾ���ú���");

		}
		// �����Ǵ���Ӻ��ѽ�����ת������
		if (flag == AddFriendsActivity.FROMADDfRIENDSACTIVITY) {
			if (sysManagerService.getFriendIdByName("friendName", Name) == 0) {
				// ������û������ҵĺ���
				friend = new Friends(1, Name, Name, "����,����Ŷ");
			} else {
				// ������û����ҵĺ�����ť��ʾΪɾ������
				btn_addFriend.setText("ɾ���ú���");
			}
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_addFriend:
			if (btn_addFriend.getText().equals("��Ϊ����")) {
				try {
					dataContext.add(friend, Friends.class, Integer.class);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Toast.makeText(getApplication(), "��Ӻ��ѳɹ�", Toast.LENGTH_LONG)
						.show();
			} else {
				int id;
				if (flag == FriendsListActivity.FROMFRIENDSListACTIVITY) {
					// ���ݺ��ѵı�ע�����ҵ��ú��ѵ�id��
					id = sysManagerService.getFriendIdByName("anotherName",
							Name);
				} else {
					// ���ݺ��ѵ��û������ҵ��ú��ѵ�id��
					id = sysManagerService.getFriendIdByName("friendName",
							Name);

				}
				// ����id��ɾ������
				sysManagerService.deleteFriendById(id);
				Toast.makeText(getApplication(), "�ɹ�ɾ������", Toast.LENGTH_LONG)
						.show();
			}
			break;
		}
	}

}
