package ui.statisticsDisplay.viewModel;


import ui.community.MyfriendFrament;
import ui.statisticsDisplay.Activity.StartrunningActivity;
import ui.statisticsDisplay.Activity.MainActivity;

import com.example.androidui_sample_demo.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MenuFragment extends Fragment {
	private  MenuFragment menuFragment;
	private FragmentManager fragmentManager;
	private LinearLayout home,personal_info,start_running,running_scheme,setting,
						running_model,myfriend,history,exit;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		menuFragment=this;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_menu, null);
		
		
		
		
		
		personal_info=(LinearLayout) view.findViewById(R.id.personal_info);
		personal_info.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				fragmentManager=menuFragment.getActivity().getFragmentManager();
				PersonalFragment personalFragment=new PersonalFragment();
				fragmentManager.beginTransaction().replace(R.id.content_frame, 
						personalFragment).commit();
				((MainActivity)menuFragment.getActivity()).mDrawerLayout.closeDrawers();
			}
		});
		
	
		
		home=(LinearLayout) view.findViewById(R.id.home);
		home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fragmentManager=menuFragment.getActivity().getFragmentManager();
				StartrunningActivity startrunningActivity=new StartrunningActivity();
				fragmentManager.beginTransaction().replace(R.id.content_frame, startrunningActivity).commit();
				((MainActivity)menuFragment.getActivity()).mDrawerLayout.closeDrawers();
			}
		});
		
		
		
		
		
		history=(LinearLayout) view.findViewById(R.id.history);
		history.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fragmentManager=menuFragment.getActivity().getFragmentManager();
				HistoryFragment historyFragment=new HistoryFragment();
				fragmentManager.beginTransaction().replace(R.id.content_frame, historyFragment).commit();
				((MainActivity)menuFragment.getActivity()).mDrawerLayout.closeDrawers();
			}
		});
		
		
		
		
		
		
		
		
		running_scheme=(LinearLayout) view.findViewById(R.id.running_scheme);
		running_scheme.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fragmentManager=menuFragment.getActivity().getFragmentManager();
				RunningSchemeFragment runningSchemeFragment=new RunningSchemeFragment();
				fragmentManager.beginTransaction().replace(R.id.content_frame, runningSchemeFragment).commit();
				((MainActivity)menuFragment.getActivity()).mDrawerLayout.closeDrawers();
			}
		});
		
		
		
		
		
		
		
		
		
		
		running_model=(LinearLayout) view.findViewById(R.id.running_model);
		running_model.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fragmentManager=menuFragment.getActivity().getFragmentManager();
				RunningModelFragment runningModelFragment=new RunningModelFragment();
				fragmentManager.beginTransaction().replace(R.id.content_frame, 
						runningModelFragment).commit();
				((MainActivity)menuFragment.getActivity()).mDrawerLayout.closeDrawers();
			}
		});
		
		myfriend=(LinearLayout) view.findViewById(R.id.myfriend);
		myfriend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				fragmentManager=menuFragment.getActivity().getFragmentManager();
				MyfriendFrament myfriendlFragment=new MyfriendFrament();
				fragmentManager.beginTransaction().replace(R.id.content_frame, 
						myfriendlFragment).commit();
				((MainActivity)menuFragment.getActivity()).mDrawerLayout.closeDrawers();
			}
		});
	
		
		
		
		
		exit=(LinearLayout) view.findViewById(R.id.exit);
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.exit(0);// ÍË³ö³ÌÐò
			
			}
		});
		
		
		
		
		
		
		
		return view;
	}
}
