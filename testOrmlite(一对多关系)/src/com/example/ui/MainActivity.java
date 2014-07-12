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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	private Button btn_impStu,btn_impSc,btn_query;
	private TextView tv_s1;
	
	//服务类
	private ILogin_Register_Service service;
	//实体类
	private User s1,s2,s3;
	private SelectCourse s11,s12,s21,s22,s31,s32;
	
	private List<SelectCourse> ls1,ls2,ls3;
	private ForeignCollection<SelectCourse> scs;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	private void init(){
		btn_impStu=(Button) findViewById(R.id.btn_importStudent);
		btn_impStu.setOnClickListener(this);
		btn_impSc=(Button) findViewById(R.id.btn_importSelectCourse);
		btn_impSc.setOnClickListener(this);
		btn_query=(Button) findViewById(R.id.btn_query);
		btn_query.setOnClickListener(this);
		
		tv_s1=(TextView) findViewById(R.id.tv_s1);

		
		//实例化服务类
		service=new Login_Register_Service();
		//实例化实体类
		s1=new User("s1","1",disDate.getDate2());
		s2=new User("s2","2",disDate.getDate2());
		s3=new User("s3","3",disDate.getDate2());
		
		s11=new SelectCourse(1,"math",65,s1);
		s12=new SelectCourse(1,"englisg",75,s1);
		s21=new SelectCourse(1,"math",85,s2);
		s22=new SelectCourse(1,"english",95,s2);
		s31=new SelectCourse(1,"math",45,s3);
		s32=new SelectCourse(1,"english",95,s3);
		
		ls1=new ArrayList<SelectCourse>();
		ls2=new ArrayList<SelectCourse>();
		ls3=new ArrayList<SelectCourse>();
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btn_importStudent:
			service.addUser(s1);
			service.addUser(s2);
			service.addUser(s3);
			break;
		case R.id.btn_importSelectCourse:
			service.addSubject(s11);
			service.addSubject(s12);
			service.addSubject(s21);
			service.addSubject(s22);
			service.addSubject(s31);
			service.addSubject(s32);
			break;
		case R.id.btn_query:
			//根据学生名字找到要找的学生
			User u=service.findUserByName("s2");
			//取出该学生的选课ForeignCollection
			scs=u.getSCs();
			Iterator ite=scs.iterator();
			while(ite.hasNext()){
				SelectCourse sc3=new SelectCourse();
				sc3=(SelectCourse) ite.next();
				//将该学生的所有选课科目情况添加到一个集合中
				ls1.add(sc3);
				
			}
			String s="";
			for(int i=0;i<ls1.size();i++){
				s+=ls1.get(i).getCoursename()+" "+ls1.get(i).getScore()+" ";
			}
			tv_s1.setText(u.getUsername()+":"+s);
			s="";
			break;
		}
		
	}
}
