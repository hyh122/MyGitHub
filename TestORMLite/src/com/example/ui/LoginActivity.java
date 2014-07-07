package com.example.ui;

import com.example.dataModal.User;
import com.example.domain.service.ILogin_Register_Service;
import com.example.domain.service.Login_Register_Service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{
	private Button btn_Login,btn_Register;
	private EditText et_Username,et_Passwd;
	private User user;
	private ILogin_Register_Service service;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		init();
	}
	private void init(){
		btn_Login=(Button) findViewById(R.id.btn_login);
		btn_Login.setOnClickListener(this);
		btn_Register=(Button) findViewById(R.id.btn_login_regist);
		btn_Register.setOnClickListener(this);
		et_Username=(EditText) findViewById(R.id.et_login_username);
		et_Passwd=(EditText) findViewById(R.id.et_login_passwd);
		
		user=new User();
		service=new Login_Register_Service();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btn_login_regist:
			Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(intent);
		case R.id.btn_login:
			user=service.findUserByName(et_Username.getText().toString());
			if(user==null){
			Toast.makeText(LoginActivity.this, "该用户不存在", Toast.LENGTH_SHORT).show();
			}
			else{
			if(user.getPassword().equals(et_Passwd.getText().toString())){
			Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
			}
			else{
			Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
			}
			}
		}
		
	}

	

	
}
