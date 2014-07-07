package com.example.ui;



import com.example.dataModal.User;
import com.example.domain.service.ILogin_Register_Service;
import com.example.domain.service.Login_Register_Service;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private Button btn_regist;
	private EditText et_Username,et_Passwd;
	//业务实体类
	private User user;
	//业务服务类
	private ILogin_Register_Service service;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		init();
	}
	
	private void init(){
		btn_regist=(Button) findViewById(R.id.btn_res_finish);
		btn_regist.setOnClickListener(new finishListener());
		et_Username=(EditText) findViewById(R.id.et_res_username);
		et_Passwd=(EditText) findViewById(R.id.et_res_passwd);
		
		user=new User();
		service=new Login_Register_Service();
		
	}
	protected final class finishListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			user.setUsername(et_Username.getText().toString());
			user.setPassword(et_Passwd.getText().toString());
			
			service.addUser(user);
			Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
			finish();
		}
		
	}
}
