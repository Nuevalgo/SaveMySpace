package com.nuevalgo.savemyspace;

import java.util.Locale;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends FragmentActivity {

	
	private MyAdapter mAdapter;
	ViewPager mViewPager;
	GalleryFragmentActivity galleryActivity;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		galleryActivity = new GalleryFragmentActivity(MainActivity.this);
		mAdapter = new MyAdapter(getSupportFragmentManager(), galleryActivity);
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mAdapter);

	}

	
	public  class MyAdapter extends FragmentPagerAdapter {
		GalleryFragmentActivity gallery;
		public MyAdapter(FragmentManager fm) {
			super(fm);
		}
		public MyAdapter(FragmentManager fm, GalleryFragmentActivity gallery) {
			this(fm);
			this.gallery = gallery;
		}

		@Override
		public int getCount() {
			return 1;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return this.gallery;
//			case 1:
//				return new ContactFragmentActivity(MainActivity.this);
			
			default:
				return null;
			}
		}
		 @Override  
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);			
			}
			return null;
		}
	}

public void showpopup(View v) {
	initiatePopupWindow();
}
private PopupWindow pwindo;

private void initiatePopupWindow() {
try {
// We need to get the instance of the LayoutInflater
LayoutInflater inflater = (LayoutInflater) MainActivity.this
.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
View layout = inflater.inflate(R.layout.share_popup,
(ViewGroup) findViewById(R.id.popup_element));
pwindo = new PopupWindow(layout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
pwindo.showAtLocation(layout, Gravity.NO_GRAVITY,this.getPopUpLeft(),106);
pwindo.setBackgroundDrawable(new BitmapDrawable());
pwindo.setOutsideTouchable(true);
pwindo.setFocusable(true);
} catch (Exception e) {
e.printStackTrace();
}
}

public int getPopUpLeft() {
	Display display = getWindowManager().getDefaultDisplay();
	//Point size = new Point();
	//display.getSize(size);
	int width = display.getWidth();
	//int height = display.getHeight();
	Log.d("Space", width+"");
	return width - 280;
}

public void delete(View v)
{
	this.galleryActivity.deleteFiles();
	pwindo.dismiss();
	mViewPager.setAdapter(mAdapter);
}
public void cancel(View v)
{
	pwindo.dismiss();
}
	}
	
	

