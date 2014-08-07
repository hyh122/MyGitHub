package ui.statisticsDisplay.viewModel;

import java.util.ArrayList;
import java.util.List;

import ui.statisticsDisplay.Activity.PersonalInfoActivity;
import ui.statisticsDisplay.Activity.PersonaldataActivity;
import ui.statisticsDisplay.Activity.PersonalrunningrecordActivity;


import com.example.androidui_sample_demo.R;



import android.app.Fragment;
import android.app.LocalActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;

public class PersonalFragment extends Fragment {
	@SuppressWarnings("deprecation")
	LocalActivityManager manager = null; // Activity管理
	TextView tvTab1, tvTab2, tvTab3; // 顶部标签
	TabHost tabHost = null;
	List<View> listViews;
	private ViewPager pager = null; // 分页

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_personal, null);
		getActivity().setTitle("档案");
		// 管理当前的Activity
		manager = new LocalActivityManager(getActivity(), true);
		manager.dispatchCreate(savedInstanceState);
		// 找到标签管理
		tabHost = (TabHost)view.findViewById(R.id.tabhost);
		tabHost.setup();
		// 使其管理当前的Activity
		tabHost.setup(manager);

		// 找到xml上的分页
		pager = (ViewPager) view.findViewById(R.id.viewpager);

		// 用来存放三个不同的activity页面
		listViews = new ArrayList<View>();

		// 通过意图来获取要被添加的三个Activity的页面，然后加入的list中
		Intent intent_main = new Intent(getActivity(), PersonaldataActivity.class);
		listViews.add(getView("FirstActivity", intent_main));

		Intent intent_main2 = new Intent(getActivity(),
				PersonalrunningrecordActivity.class);
		listViews.add(getView("SecondActivity", intent_main2));

		// 这里开始是来设置顶部标签的颜色，字体，内容等信息
		RelativeLayout tabIndicator1 = (RelativeLayout) LayoutInflater.from(
				getActivity()).inflate(R.layout.tabwidget, null);
		// 这里可以设置标签栏的样式，自定义样式时用
		tvTab1 = (TextView) tabIndicator1.findViewById(R.id.tv_title);
		tvTab1.setText("个人数据");
		tvTab1.setTextColor(this.getResources().getColor(R.color.main_red));
		RelativeLayout tabIndicator2 = (RelativeLayout) LayoutInflater.from(
				getActivity()).inflate(R.layout.tabwidget, null);
		tvTab2 = (TextView) tabIndicator2.findViewById(R.id.tv_title);
		tvTab2.setText("运动记录");

		tabHost.addTab(tabHost.newTabSpec("A").setIndicator(tabIndicator1)
				.setContent(intent_main));
		tabHost.addTab(tabHost.newTabSpec("B").setIndicator(tabIndicator2)
				.setContent(intent_main2));
		// 设置监听
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				if ("A".equals(tabId)) {
					tvTab1.setTextColor(getActivity().getResources().getColor(
							R.color.main_red));
					tvTab2.setTextColor(getActivity().getResources().getColor(
							R.color.grey));
					pager.setCurrentItem(0);

				}
				if ("B".equals(tabId)) {
					tvTab1.setTextColor(getActivity().getResources().getColor(
							R.color.grey));
					tvTab2.setTextColor(getActivity().getResources().getColor(
							R.color.main_red));
					pager.setCurrentItem(1);

				}
			}
		});
		// 设置每一页要显示的内容
		pager.setAdapter(new MyPageAdapter(listViews));
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				tabHost.setCurrentTab(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		menu.clear();
		inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.menu_personal, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.personal_setting:
			Intent intent = new Intent(getActivity(),
					PersonalInfoActivity.class);
			startActivity(intent);
			break;
		case R.id.personal_image:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private class MyPageAdapter extends PagerAdapter {

		private List<View> list;

		private MyPageAdapter(List<View> list) {
			this.list = list;
		}

		// 移走之前的页面
		@Override
		public void destroyItem(ViewGroup view, int position, Object arg2) {
			ViewPager pViewPager = ((ViewPager) view);
			pViewPager.removeView(list.get(position));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return list.size();
		}

		// 加入新的页面
		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			ViewPager pViewPager = ((ViewPager) view);
			pViewPager.addView(list.get(position));
			return list.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}

	}

	@SuppressWarnings("deprecation")
	private View getView(String id, Intent intent) {
		Log.d("EyeAndroid", "getView() called! id = " + id);
		return manager.startActivity(id, intent).getDecorView();
	}

}
