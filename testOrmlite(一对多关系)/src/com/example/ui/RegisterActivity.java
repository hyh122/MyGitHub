package com.example.ui;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.dataModal.SelectCourse;
import com.example.dataModal.User;
import com.example.domain.service.ILogin_Register_Service;
import com.example.domain.service.Login_Register_Service;
import com.example.domain.service.disDate;
import com.j256.ormlite.dao.ForeignCollection;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private Button btn_regist;

	private EditText et_Username,et_Passwd,et_Title,et_Score;
	//业务实体类
	private User user;
	private SelectCourse sc,sc2;
	private List<SelectCourse> Lsc;
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
		et_Title=(EditText) findViewById(R.id.et_res_title);
		et_Score=(EditText) findViewById(R.id.et_res_score);
		
		Lsc=new ArrayList<SelectCourse>();
		user=new User();
		sc=new SelectCourse();
		
		sc2=new SelectCourse();
		
		
		service=new Login_Register_Service();
		User user2=service.findUserByName("y");
		ForeignCollection<SelectCourse> scs=user2.getSCs();
		Iterator ite=scs.iterator();
		while(ite.hasNext()){
			SelectCourse sc3=new SelectCourse();
			sc3=(SelectCourse) ite.next();
			Lsc.add(sc3);
			
		}
		String s = null;
		for(int i=0;i<Lsc.size();i++){
			s=Lsc.get(i).getCoursename();
		}
		et_Title.setText(s);
		
		
		Log.e("test", Lsc.get(0).getCoursename());
		
	}
	protected final class finishListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
//			sc.setCoursename(et_Title.getText().toString());
//			sc.setScore(Float.valueOf(et_Score.getText().toString()));
			user.setUsername(et_Username.getText().toString());
			user.setPassword(et_Passwd.getText().toString());
			user.setDate(disDate.getDate2());
			sc.setCoursename("math");
			sc.setScore(77);
			sc.setUser(user);
			sc2.setCoursename("physics");
			sc2.setScore(78);
			sc2.setUser(user);
			
			service.addUser(user);
			service.addSubject(sc);
			service.addSubject(sc2);
			
//			user.setSc(sc);
//			
//			
//			service.addSubject(sc);
			//service.addSubject(sc2);
			
//			user.setSCs(Lsc);
			
		
			
//			service.addUser(user);
			Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
			finish();
		}
		
	}
}
