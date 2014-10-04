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
	//��ѡ�����
		private RadioGroup group;
		private RadioButton rb;
		private EditText et_height,et_weight,et_heartrate;
	 
	    private int year, monthOfYear, dayOfMonth, hourOfDay, minute;
	    private TextView tv_showbirth;
		//�����ʾ��һ������ѡ����
		private Button btn_birthDate;
		//���水ť
		private Button btn_save;
		
		
	    
	    
	    
	    
	    
	    
	    
	    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle("������Ϣ");
		initView();
		
//
//	    rb=(RadioButton)findViewById(group.getCheckedRadioButtonId());
//	    group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//		
//			@Override
//			public void onCheckedChanged(RadioGroup group, int checkedId) {
//				// TODO Auto-generated method stub
//				//��ȡ������ѡ�����ID
//		        int radioButtonId = group.getCheckedRadioButtonId();
//			         //����ID��ȡRadioButton��ʵ��
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
                 * ʵ����һ��DatePickerDialog�Ķ���
                 * �ڶ���������һ��DatePickerDialog.OnDateSetListener�����ڲ��࣬���û�ѡ������ڵ��done����������onDateSet����
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
				// TODO �Զ����ɵķ������
				Toast.makeText(getApplicationContext(), "����ɹ�", Toast.LENGTH_SHORT).show(); 
			}
		});
		
		
		
		
	}

	
	
	
	
	private void initView(){
		btn_birthDate=(Button) findViewById(R.id.btn_birthdate);
		btn_save=(Button) findViewById(R.id.btn_save);
		//�ҵ�textview
		tv_showbirth=(TextView) findViewById(R.id.tv_showbirth);
		//�ҵ�edittext
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
