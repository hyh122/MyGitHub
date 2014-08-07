package ui.statisticsDisplay.Activity;


import ui.SystemManaConfig.Activity.MusicActivity;

import ui.statisticsDisplay.viewModel.StartRunningFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.androidui_sample_demo.R;

import domain.dataCollect.DataCollectService;
import foundation.dataService.util.DateService;

public class StartrunningActivity extends Fragment {
	private Context context;
	
	private ImageButton image_presonal_info;
	
	private Button btn_start_running;
	
	private ImageButton image_presonal_setting;
	
	private ImageButton image_music;
	
	private FragmentManager fragmentManager;
	

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		context=(MainActivity)getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, null);
		getActivity().setTitle("N.O.Running");
	
		
		/**
		 * ������Ϣ����
		 */
		image_presonal_info=(ImageButton) view.findViewById(R.id.image_presonal_info);
		image_presonal_info.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),PersonalInfoActivity.class);
				startActivity(intent);
			}
		});

		/**
		 *�����ʼ�ܲ�
		 */
		btn_start_running=(Button) view.findViewById(R.id.btn_start_running);
		btn_start_running.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
						Intent intent=new Intent(getActivity(),DeviceScanActivity.class);
					    startActivity(intent);
					   
			
				
			}
		});
		
		/**
		 *����
		 */
		image_music=(ImageButton) view.findViewById(R.id.image_music);
		image_music.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),MusicActivity.class);
				startActivity(intent);
			}
		});
		/**
		 *����Ŀ��
		 */
		image_presonal_setting=(ImageButton) view.findViewById(R.id.image_presonal_setting);
		image_presonal_setting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),PersonaldataActivity.class);
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
		inflater.inflate(R.menu.menu_home, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.about:
			break;
		case R.id.setting:
			Intent intent=new Intent(getActivity(),SettingActivity.class);
			startActivity(intent);
			break;
		case R.id.start_running:
			fragmentManager=getActivity().getFragmentManager();
			StartRunningFragment startrunningFragment=new StartRunningFragment();
			fragmentManager.beginTransaction().replace(R.id.content_frame, startrunningFragment).commit();
			((MainActivity)getActivity()).mDrawerLayout.closeDrawers();
			break;
			
		}
		return super.onOptionsItemSelected(item);
	}
}
