package ui.statisticsDisplay.activity;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.androidui_sample_demo.R;

public class PersonalInfoActivity extends Activity {
	//单选框组件
		private RadioGroup group;
		private RadioButton rb;
		private EditText et_height,et_weight,et_heartrate;
	 
	    private int year, monthOfYear, dayOfMonth, hourOfDay, minute;
	    private TextView tv_showbirth;
		//点击显示出一个日期选择器
		private Button btn_birthDate;
		//保存按钮
		private Button btn_save;
		
		
	    
	    
	    
	    
	    
	    
	    
	    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle("个人信息");
		initView();
		
//
//	    rb=(RadioButton)findViewById(group.getCheckedRadioButtonId());
//	    group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//		
//			@Override
//			public void onCheckedChanged(RadioGroup group, int checkedId) {
//				// TODO Auto-generated method stub
//				//获取变更后的选中项的ID
//		        int radioButtonId = group.getCheckedRadioButtonId();
//			         //根据ID获取RadioButton的实例
//			        // rb=(RadioButton) findViewById(radioButtonId);
//			         
//				}
//			});

	    
	    
	    Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        
	    
        
        btn_birthDate.setOnClickListener(new OnClickListener()
        {
            public void onClick(View view)
            {
                /**
                 * 实例化一个DatePickerDialog的对象
                 * 第二个参数是一个DatePickerDialog.OnDateSetListener匿名内部类，当用户选择好日期点击done会调用里面的onDateSet方法
                 */
                DatePickerDialog datePickerDialog = new DatePickerDialog(PersonalInfoActivity.this, new DatePickerDialog.OnDateSetListener()
                {
                    public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth)
                    {
                        tv_showbirth.setText( + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        
                    }
                }, year, monthOfYear, dayOfMonth);
                datePickerDialog.updateDate(1990, 0, 01);
                
                datePickerDialog.show();
            }

		
        });
        
        btn_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show(); 
			}
		});
		
		
		
		
	}

	
	
	
	
	private void initView(){
		btn_birthDate=(Button) findViewById(R.id.btn_birthdate);
		btn_save=(Button) findViewById(R.id.btn_save);
		//找到textview
		tv_showbirth=(TextView) findViewById(R.id.tv_showbirth);
		//找到edittext
		et_height=(EditText) findViewById(R.id.et_height);
		et_weight=(EditText) findViewById(R.id.et_weight);
		et_heartrate=(EditText) findViewById(R.id.et_heartrate);
	}

	
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();

		}
		return super.onOptionsItemSelected(item);
	}

}
