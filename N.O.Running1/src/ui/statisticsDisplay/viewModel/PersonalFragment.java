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
	LocalActivityManager manager = null; // Activity����
	TextView tvTab1, tvTab2, tvTab3; // ������ǩ
	TabHost tabHost = null;
	List<View> listViews;
	private ViewPager pager = null; // ��ҳ

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
		getActivity().setTitle("����");
		// ����ǰ��Activity
		manager = new LocalActivityManager(getActivity(), true);
		manager.dispatchCreate(savedInstanceState);
		// �ҵ���ǩ����
		tabHost = (TabHost)view.findViewById(R.id.tabhost);
		tabHost.setup();
		// ʹ�����ǰ��Activity
		tabHost.setup(manager);

		// �ҵ�xml�ϵķ�ҳ
		pager = (ViewPager) view.findViewById(R.id.viewpager);

		// �������������ͬ��activityҳ��
		listViews = new ArrayList<View>();

		// ͨ����ͼ����ȡҪ����ӵ�����Activity��ҳ�棬Ȼ������list��
		Intent intent_main = new Intent(getActivity(), PersonaldataActivity.class);
		listViews.add(getView("FirstActivity", intent_main));

		Intent intent_main2 = new Intent(getActivity(),
				PersonalrunningrecordActivity.class);
		listViews.add(getView("SecondActivity", intent_main2));

		// ���￪ʼ�������ö�����ǩ����ɫ�����壬���ݵ���Ϣ
		RelativeLayout tabIndicator1 = (RelativeLayout) LayoutInflater.from(
				getActivity()).inflate(R.layout.tabwidget, null);
		// ����������ñ�ǩ������ʽ���Զ�����ʽʱ��
		tvTab1 = (TextView) tabIndicator1.findViewById(R.id.tv_title);
		tvTab1.setText("��������");
		tvTab1.setTextColor(this.getResources().getColor(R.color.main_red));
		RelativeLayout tabIndicator2 = (RelativeLayout) LayoutInflater.from(
				getActivity()).inflate(R.layout.tabwidget, null);
		tvTab2 = (TextView) tabIndicator2.findViewById(R.id.tv_title);
		tvTab2.setText("�˶���¼");

		tabHost.addTab(tabHost.newTabSpec("A").setIndicator(tabIndicator1)
				.setContent(intent_main));
		tabHost.addTab(tabHost.newTabSpec("B").setIndicator(tabIndicator2)
				.setContent(intent_main2));
		// ���ü���
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
		// ����ÿһҳҪ��ʾ������
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

		// ����֮ǰ��ҳ��
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

		// �����µ�ҳ��
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
