package ui.statisticsDisplay.fragment;


import ui.statisticsDisplay.activity.HistoryActivity;
import ui.statisticsDisplay.activity.PersonalInfoActivity;
import ui.statisticsDisplay.activity.SettingActivity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.androidui_sample_demo.R;


public class MineFrament extends Fragment {
	private LinearLayout history;
	private LinearLayout personal_info;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_mine, null);
		getActivity().setTitle("我");
		
		
		/**
		 * 	跳转到历史记录界面
		 */
		 personal_info=(LinearLayout)view.findViewById(R.id.tv_personal_info);
		 personal_info.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
						Intent intent=new Intent(getActivity(),PersonalInfoActivity.class);
						startActivity(intent);
					
			}
			});
		
			/**
			 * 	跳转到个人信息
			 */
				history=(LinearLayout)view.findViewById(R.id.tv_history);
				history.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
							Intent intent=new Intent(getActivity(),HistoryActivity.class);
							startActivity(intent);
						
				}
				});
			
			
			
			
			
			
		
		return view;
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		menu.clear();
		inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.setting:
			Intent intent=new Intent(getActivity(),SettingActivity.class);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
