package ui.statisticsDisplay.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import ui.statisticsDisplay.viewModel.FriendsListModel;
import ui.statisticsDisplay.viewModel.FriendsMessageModal;

import com.baidu.location.f;
import com.example.androidui_sample_demo.R;

import domain.systemManaConfig.Friends;
import domain.systemManaConfig.SystemManageService;
import domain.systemManaConfig.User;

import foundation.dataService.base.DataContext;
import foundation.dataService.util.DateService;
import foundation.dataService.util.ImageTools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class FriendsMessageActivity extends Activity implements
		android.view.View.OnClickListener {
	private static final int TAKE_PICTURE = 0;
	private static final int CHOOSE_PICTURE = 1;
	private static final int SCALE = 5;// ��Ƭ��С����
	// ���ݿ������
	private DataContext dataContext;
	// ҵ�������
	private SystemManageService sysManagerService;
	private Button btn_addFriend, btn_alterName;
	private TextView tv_anotherName, tv_sex, tv_age, tv_city, tv_lastRunTime,
			tv_userName, tv_acountnum, tv_birthday, tv_personalword;
	private Friends friendOfCurrentUser;
	private ImageButton ib_portrait;
	// ���ͼƬ���ֽ���
	private byte[] portrait = null;
	// ���ѱ�ʵ����
	private Friends friend;
	private String email;
	// �û���ʵ����
	private User user;
	// viewmodal
	private FriendsMessageModal friendsMessageModal;

	// ��־���ĸ�������ת�����ı�ǩ
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
		btn_alterName = (Button) findViewById(R.id.btn_altername);
		btn_alterName.setOnClickListener(this);
		tv_anotherName = (TextView) findViewById(R.id.tv_anothername);
		tv_sex = (TextView) findViewById(R.id.tv_sex);
		tv_city = (TextView) findViewById(R.id.tv_city);
		tv_lastRunTime = (TextView) findViewById(R.id.tv_lastrunninttime);
		tv_userName = (TextView) findViewById(R.id.tv_username);
		tv_acountnum = (TextView) findViewById(R.id.tv_acountnum);
		tv_birthday = (TextView) findViewById(R.id.tv_birthday);
		tv_personalword = (TextView) findViewById(R.id.tv_personalword);
		tv_age = (TextView) findViewById(R.id.tv_age);

		ib_portrait = (ImageButton) findViewById(R.id.ib_portrait);
		ib_portrait.setOnClickListener(this);

		dataContext = new DataContext();
		sysManagerService = new SystemManageService();
		friendsMessageModal = new FriendsMessageModal();

		/** ���մ���һ�����洫���������� */
		// �ж��Ǵ��ĸ�������ת������
		Bundle bundle = this.getIntent().getExtras();
		email = bundle.getString("email");

		flag = this.getIntent().getFlags();
		
		friendOfCurrentUser = sysManagerService.gerFriend(email, sysManagerService.getCurrentLoginedUser());

		try {
			// ȡ���û���Ϣ
			user = dataContext.queryById(User.class, String.class, email);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// �����ǴӺ����б������ת������
		if (flag == FriendsListActivity.FROMFRIENDSListACTIVITY) {
			// ������û����ҵĺ�����ť��ʾΪɾ������
			btn_addFriend.setText("ɾ���ú���");

			String anotherName = bundle.getString("anotherName");

			friendsMessageModal = initModal(user, anotherName);
			loadView(friendsMessageModal);

		}
		// �����Ǵ���Ӻ��ѽ�����ת������
		if (flag == AddFriendsActivity.FROMADDfRIENDSACTIVITY) {
			
			
			friendsMessageModal = initModal(user);
			loadView(friendsMessageModal);
			if (friendOfCurrentUser == null) {
				// ������û������ҵĺ���
				// friend = new Friends(1, Name, Name, "����,����Ŷ");
			} else {
				// ������û����ҵĺ�����ť��ʾΪɾ������
				btn_addFriend.setText("ɾ���ú���");
			}
		}

	}

	// ��ʼ��viewModal
	public FriendsMessageModal initModal(User user, String anotherName) {
		FriendsMessageModal friendsMessageModal = new FriendsMessageModal();

		friendsMessageModal.setProtrait(user.getProtrait());
		friendsMessageModal.setAcountNum(user.getEmail());
		friendsMessageModal.setAge(22);
		friendsMessageModal.setAnotherName(anotherName);
		friendsMessageModal.setBirthday(user.getBirthday());
		friendsMessageModal.setCity(user.getCity());
		friendsMessageModal.setLastRunTime(new Date());
		friendsMessageModal.setPersonalWord(user.getPersonalword());
		friendsMessageModal.setSex(user.getSex());
		friendsMessageModal.setUserName(user.getNickName());
		return friendsMessageModal;
	}

	// ��ʼ��viewModal
	public FriendsMessageModal initModal(User user) {
		FriendsMessageModal friendsMessageModal = new FriendsMessageModal();
		friendsMessageModal.setProtrait(user.getProtrait());
		friendsMessageModal.setAcountNum(user.getEmail());
		friendsMessageModal.setAge(22);
		friendsMessageModal.setAnotherName(user.getNickName());
		friendsMessageModal.setBirthday(user.getBirthday());
		friendsMessageModal.setCity(user.getCity());
		friendsMessageModal.setLastRunTime(new Date());
		friendsMessageModal.setPersonalWord(user.getPersonalword());
		friendsMessageModal.setSex(user.getSex());
		friendsMessageModal.setUserName(user.getNickName());
		return friendsMessageModal;
	}

	// װ�ؽ���
	public void loadView(FriendsMessageModal viewModal) {
		Bitmap bmProtrait = ImageTools.byteToBitmap(viewModal.getProtrait());
		ib_portrait.setImageBitmap(ImageTools.zoomBitmap(bmProtrait, 200, 210));
		tv_anotherName.setText(viewModal.getAnotherName());
		tv_sex.setText(viewModal.getSex());
		tv_age.setText(viewModal.getAge() + "");
		tv_city.setText(viewModal.getCity());
		tv_lastRunTime.setText(DateService.changeDateFormat(viewModal
				.getLastRunTime()));
		tv_userName.setText(viewModal.getUserName());
		tv_acountnum.setText(viewModal.getAcountNum());
		tv_birthday.setText(viewModal.getBirthday());
		tv_personalword.setText(viewModal.getPersonalWord());

	}

	/**
	 * ��user�����Ϣװ�ص�friend����
	 */
	public Friends loadToFriend(User user) {
	
		Friends friend = new Friends();
		friend.setAnotherName(user.getNickName());
		friend.setEmail(user.getEmail());
		friend.setNickName(user.getNickName());
		friend.setOneAndOneUser(user);
		friend.setPersonalWord(user.getPersonalword());
		friend.setProtrait(user.getProtrait());
		friend.setOneAndMoreUser(sysManagerService.getCurrentLoginedUser());

		return friend;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_addFriend:
			if (btn_addFriend.getText().equals("��Ϊ����")) {
				String message = null;
				User currentLoginedUser=sysManagerService.getCurrentLoginedUser();
				if(currentLoginedUser.getEmail().equals(this.user.getEmail())){
					message="��������Լ�Ϊ����";
				}else{
				friend = loadToFriend(this.user);
				try {
					dataContext.add(friend, Friends.class, Integer.class);
					message="��Ӻ��ѳɹ�";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				Toast.makeText(getApplication(), message, Toast.LENGTH_LONG)
						.show();
			} else {

				// ����id��ɾ������
				try {
					dataContext.delete(friendOfCurrentUser, Friends.class,
							Integer.class);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Toast.makeText(getApplication(), "�ɹ�ɾ������", Toast.LENGTH_LONG)
						.show();
			}
			break;
		case R.id.btn_altername:

			break;
		case R.id.ib_portrait:

			// showPicturePicker(FriendsMessageActivity.this);
			break;
		}
	}

	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// super.onActivityResult(requestCode, resultCode, data);
	// if (resultCode == RESULT_OK) {
	// switch (requestCode) {
	// case TAKE_PICTURE:
	// // �������ڱ��ص�ͼƬȡ������С����ʾ�ڽ�����
	// Bitmap bitmap = BitmapFactory.decodeFile(Environment
	// .getExternalStorageDirectory() + "/image.jpg");
	// Bitmap newBitmap = ImageTools.zoomBitmap(bitmap,
	// bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE);
	// // ����Bitmap�ڴ�ռ�ýϴ�������Ҫ�����ڴ棬����ᱨout of memory�쳣
	// bitmap.recycle();
	//
	// // ���������ͼƬ��ʾ�ڽ����ϣ������浽����
	// ib_portrait.setImageBitmap(newBitmap);
	// ImageTools.savePhotoToSDCard(newBitmap, Environment
	// .getExternalStorageDirectory().getAbsolutePath(),
	// String.valueOf(System.currentTimeMillis()));
	// portrait = ImageTools.bitmapToBytes(newBitmap);
	//
	// break;
	//
	// case CHOOSE_PICTURE:
	// ContentResolver resolver = getContentResolver();
	// // ��Ƭ��ԭʼ��Դ��ַ
	// Uri originalUri = data.getData();
	// try {
	// // ʹ��ContentProviderͨ��URI��ȡԭʼͼƬ
	// Bitmap photo = MediaStore.Images.Media.getBitmap(resolver,
	// originalUri);
	// if (photo != null) {
	// // Ϊ��ֹԭʼͼƬ�������ڴ��������������Сԭͼ��ʾ��Ȼ���ͷ�ԭʼBitmapռ�õ��ڴ�
	// Bitmap smallBitmap = ImageTools.zoomBitmap(photo, 200,
	// 210);
	// // Bitmap smallBitmap = ImageTools.zoomBitmap(photo,
	// // photo.getWidth() / SCALE, photo.getHeight()
	// // / SCALE);
	// // �ͷ�ԭʼͼƬռ�õ��ڴ棬��ֹout of memory�쳣����
	// photo.recycle();
	// ib_portrait.setImageBitmap(smallBitmap);
	// portrait = ImageTools.bitmapToBytes(smallBitmap);
	// }
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// break;
	//
	// default:
	// break;
	// }
	// }
	// }
	//
	// public void showPicturePicker(Context context) {
	// AlertDialog.Builder builder = new AlertDialog.Builder(context);
	// builder.setTitle("ͼƬ��Դ");
	// builder.setNegativeButton("ȡ��", null);
	// builder.setItems(new String[] { "����", "���" },
	// new DialogInterface.OnClickListener() {
	//
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// switch (which) {
	// case TAKE_PICTURE:
	// Intent openCameraIntent = new Intent(
	// MediaStore.ACTION_IMAGE_CAPTURE);
	// Uri imageUri = Uri.fromFile(new File(Environment
	// .getExternalStorageDirectory(), "image.jpg"));
	// // ָ����Ƭ����·����SD������image.jpgΪһ����ʱ�ļ���ÿ�����պ����ͼƬ���ᱻ�滻
	// openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
	// imageUri);
	// startActivityForResult(openCameraIntent,
	// TAKE_PICTURE);
	// break;
	//
	// case CHOOSE_PICTURE:
	// Intent openAlbumIntent = new Intent(
	// Intent.ACTION_GET_CONTENT);
	// openAlbumIntent.setType("image/*");
	// startActivityForResult(openAlbumIntent,
	// CHOOSE_PICTURE);
	// break;
	//
	// default:
	// break;
	// }
	// }
	// });
	// builder.create().show();
	// }

}
