package ui.statisticsDisplay.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.File.TxtFileUtil;
import com.alibaba.fastjson.JSON;
import com.example.androidui_sample_demo.R;

import foundation.dataService.util.ImageTools;
import foundation.dataService.util.LoadingDialog;
import foundation.webservice.ConnetNet;
import foundation.webservice.MemoWebPara;
import foundation.webservice.WebServiceDelegate;
import foundation.webservice.WebServiceUtils;
import foundation.webservice.help.User;

public class PersonalInfoActivity extends Activity implements
		WebServiceDelegate {
	// ��ѡ�����
	private RadioGroup group;
	private RadioButton rbM, rbF;
	private ImageButton ib_portrait;
	private static final int TAKE_PICTURE = 0;
	private static final int CHOOSE_PICTURE = 1;
	private static final int SCALE = 5;// ��Ƭ��С����
	private EditText niciNaeme, sex, height, weight;
	private boolean webFlag = false;
	private int sexFlag = 0;
	private String email, password;
	private WebServiceUtils webService;
	private LoadingDialog dialog;

	private int year, monthOfYear, dayOfMonth, hourOfDay, minute;
	private TextView birthday;
	// �����ʾ��һ������ѡ����
	private Button btn_birthDate;
	// ���水ť
	private Button btn_save;
	private User user = null;
	private String skip = null;
	private byte[] portrait = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		dialog = new LoadingDialog(this);
		// ʹ�ô򿪽�����Զ����������
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setTitle("������Ϣ");
		webFlag = ConnetNet.isConnect(getApplicationContext());
		webService = new WebServiceUtils(MemoWebPara.SM_NAMESPACE,
				MemoWebPara.USER_URL, this);
		user = new User();
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("key");
		skip = bundle.getString("skip");
		if (skip.equals("fromRegister")) {
			String email = bundle.getString("email");
			String password = bundle.getString("password");
			user.setEmail(email);
			user.setPassword(password);
		} else if (skip.equals("fromMineFrament")
				|| skip.equals("fromStartRunning")) {
			// ��ȡ�������ϢString loginFlag = null;
			String loginFlag = null;
			try {
				loginFlag = TxtFileUtil.readTxtFile(TxtFileUtil.loginFlag);// ��ȡ�û�email
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (loginFlag != null) {
				String[] str = loginFlag.split(",");
				webFlag = ConnetNet.isConnect(getApplicationContext());
				webService = new WebServiceUtils(MemoWebPara.SM_NAMESPACE,
						MemoWebPara.USER_URL, this);
				HashMap<String, Object> args = new HashMap<String, Object>();
				user.setEmail(str[1].toString().trim());
				args.put("email", str[1].toString().trim());
				webService.callWebService("getUserInfo", args, String.class);
				dialog.show();
			}
		}
		initView();

		this.findViewById(R.id.ib_portrait).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						showPicturePicker(PersonalInfoActivity.this);
					}
				});
		group = (RadioGroup) findViewById(R.id.radioGroup);
		rbM = (RadioButton) findViewById(R.id.radioMale);
		rbF = (RadioButton) findViewById(R.id.radioFemale);

		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == rbF.getId()) {
					sexFlag = 1;
				}
			}
		});

		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		monthOfYear = calendar.get(Calendar.MONTH);
		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);

		btn_birthDate.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				/**
				 * ʵ����һ��DatePickerDialog�Ķ���
				 * �ڶ���������һ��DatePickerDialog.OnDateSetListener�����ڲ���
				 * �����û�ѡ������ڵ��done����������onDateSet����
				 */
				DatePickerDialog datePickerDialog = new DatePickerDialog(
						PersonalInfoActivity.this,
						new DatePickerDialog.OnDateSetListener() {
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								birthday.setText(+year + "-"
										+ (monthOfYear + 1) + "-" + dayOfMonth);

							}
						}, year, monthOfYear, dayOfMonth);
				datePickerDialog.updateDate(1990, 0, 01);

				datePickerDialog.show();
			}

		});

		btn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (niciNaeme.getText().toString().trim().equals("")) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"��������Ϊ��", Toast.LENGTH_SHORT);
					toast.show();
				} else if (height.getText().toString().trim().equals("")) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"��߲���Ϊ��", Toast.LENGTH_SHORT);
					toast.show();
				} else if (weight.getText().toString().trim().equals("")) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"���ز���Ϊ��", Toast.LENGTH_SHORT);
					toast.show();
				} else {
					if (skip.equals("fromRegister")) {
						user.setNickName(niciNaeme.getText() + "");
						user.setBirthday(birthday.getText() + "");
						user.setHeight(Double.parseDouble(height.getText() + ""));
						user.setWeight(Double.parseDouble(weight.getText() + ""));
						if (sexFlag == 0) {
							user.setSex("��");
						} else {
							user.setSex("Ů");
						}
						HashMap<String, Object> args = new HashMap<String, Object>();
						user.setProtrait(portrait);
						String strUser = JSON.toJSONString(user);
						args.put("user", strUser);
						webService.callWebService("register", args,
								boolean.class);
						 dialog.show();
					} else if (skip.equals("fromMineFrament")
							|| skip.equals("fromStartRunning")) {

						user.setNickName(niciNaeme.getText() + "");
						user.setBirthday(birthday.getText() + "");
						user.setHeight(Double.parseDouble(height.getText() + ""));
						user.setWeight(Double.parseDouble(weight.getText() + ""));
						if (sexFlag == 0) {
							user.setSex("��");
						} else {
							user.setSex("Ů");
						}
						user.setPassword(password);
						HashMap<String, Object> args = new HashMap<String, Object>();
						user.setProtrait(portrait);
						String strUser = JSON.toJSONString(user);
						args.put("user", strUser);
						webService.callWebService("updateUserInfo", args,
								boolean.class);
						skip = "fromRegister";
						 dialog.setTitle("������Ϣ������");
						 dialog.show();
					} else {

					}
				}

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case TAKE_PICTURE:
				// �������ڱ��ص�ͼƬȡ������С����ʾ�ڽ�����
				Bitmap bitmap = BitmapFactory.decodeFile(Environment
						.getExternalStorageDirectory() + "/image.jpg");
				Bitmap newBitmap = ImageTools.zoomBitmap(bitmap,
						bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE);
				// ����Bitmap�ڴ�ռ�ýϴ�������Ҫ�����ڴ棬����ᱨout of memory�쳣
				bitmap.recycle();

				// ���������ͼƬ��ʾ�ڽ����ϣ������浽����
				ib_portrait.setImageBitmap(newBitmap);
				ImageTools.savePhotoToSDCard(newBitmap, Environment
						.getExternalStorageDirectory().getAbsolutePath(),
						String.valueOf(System.currentTimeMillis()));
				portrait = ImageTools.bitmapToBytes(newBitmap);
				break;

			case CHOOSE_PICTURE:
				ContentResolver resolver = getContentResolver();
				// ��Ƭ��ԭʼ��Դ��ַ
				Uri originalUri = data.getData();
				try {
					// ʹ��ContentProviderͨ��URI��ȡԭʼͼƬ
					Bitmap photo = MediaStore.Images.Media.getBitmap(resolver,
							originalUri);
					if (photo != null) {
						// Ϊ��ֹԭʼͼƬ�������ڴ��������������Сԭͼ��ʾ��Ȼ���ͷ�ԭʼBitmapռ�õ��ڴ�
						Bitmap smallBitmap = ImageTools.zoomBitmap(photo, 200,
								210);
						// Bitmap smallBitmap = ImageTools.zoomBitmap(photo,
						// photo.getWidth() / SCALE, photo.getHeight()
						// / SCALE);
						// �ͷ�ԭʼͼƬռ�õ��ڴ棬��ֹout of memory�쳣����
						photo.recycle();
						ib_portrait.setImageBitmap(smallBitmap);
						portrait = ImageTools.bitmapToBytes(smallBitmap);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
		}
	}

	public void showPicturePicker(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("ͼƬ��Դ");
		builder.setNegativeButton("ȡ��", null);
		builder.setItems(new String[] { "����", "���" },
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case TAKE_PICTURE:
							Intent openCameraIntent = new Intent(
									MediaStore.ACTION_IMAGE_CAPTURE);
							Uri imageUri = Uri.fromFile(new File(Environment
									.getExternalStorageDirectory(), "image.jpg"));
							// ָ����Ƭ����·����SD������image.jpgΪһ����ʱ�ļ���ÿ�����պ����ͼƬ���ᱻ�滻
							openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
									imageUri);
							startActivityForResult(openCameraIntent,
									TAKE_PICTURE);
							break;

						case CHOOSE_PICTURE:
							Intent openAlbumIntent = new Intent(
									Intent.ACTION_GET_CONTENT);
							openAlbumIntent.setType("image/*");
							startActivityForResult(openAlbumIntent,
									CHOOSE_PICTURE);
							break;

						default:
							break;
						}
					}
				});
		builder.create().show();
	}

	private void initView() {
		ib_portrait = (ImageButton) findViewById(R.id.ib_portrait);
		niciNaeme = (EditText) findViewById(R.id.et_name);
		btn_birthDate = (Button) findViewById(R.id.btn_birthdate);
		btn_save = (Button) findViewById(R.id.btn_save);
		birthday = (TextView) findViewById(R.id.tv_showbirth);
		height = (EditText) findViewById(R.id.et_height);
		weight = (EditText) findViewById(R.id.et_weight);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();

		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void handleException(Object ex) {
		Toast toast = Toast.makeText(getApplicationContext(), "������������",
				Toast.LENGTH_SHORT);
		toast.show();
	}

	@Override
	public void handleResultOfWebService(String methodName, Object result) {
		
		if (webFlag == true) {
			if (skip.equals("fromRegister")) {
				Boolean flag = (Boolean) result;
				if (flag) {

					Toast toast = Toast.makeText(getApplicationContext(),
							"���������Ϣ�ɹ�", Toast.LENGTH_SHORT);
					toast.show();
					TxtFileUtil.writeLoginFlag("login," + user.getEmail());// ����½��־������д���ļ�
					dialog.dismiss();
					Intent mainIntent = new Intent(PersonalInfoActivity.this,
							MainActivity.class);
					PersonalInfoActivity.this.startActivity(mainIntent);
					PersonalInfoActivity.this.finish();
				} else {
					Toast toast = Toast.makeText(getApplicationContext(),
							"���������Ϣ�ɹ�ʧ��", Toast.LENGTH_SHORT);
					toast.show();
				}
			} else if (skip.equals("fromMineFrament")
					|| skip.equals("fromStartRunning")) {
				
				User user = JSON.parseObject((String) result, User.class);
				portrait = user.getProtrait();
				if (portrait != null) {
					Bitmap bmProtrait = ImageTools.byteToBitmap(portrait);
					ib_portrait.setImageBitmap(ImageTools.zoomBitmap(
							bmProtrait, 200, 210));
				}
				password = user.getPassword();
				niciNaeme.setText(user.getNickName().toString());
				if (user.getSex().equals("��")) {
					rbM.setChecked(true);
					rbF.setChecked(false);
				} else {
					rbM.setChecked(false);
					rbF.setChecked(true);
				}
				birthday.setText(user.getBirthday().toString());
				height.setText(String.valueOf(user.getHeight()));
				weight.setText(String.valueOf(user.getWeight()));
				dialog.dismiss();
			}
		}

	}

}
