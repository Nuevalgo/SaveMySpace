package com.Adapter;

import java.util.ArrayList;
import java.util.HashSet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.nuevalgo.savemyspace.Image;
import com.nuevalgo.savemyspace.R;


public class GridAdapter extends BaseAdapter implements OnClickListener {	
	private static LayoutInflater inflater=null;
	private Context activity;
	static String posClicked;
	WindowManager wm;
	int width,height;	
	ViewHolder holder;
	ArrayList<Image> arrayList;
	ArrayList<String> filePathDelete = new ArrayList<String>();
	int index;
	public GridAdapter(Context a,ArrayList<Image> arraylist) {		
		activity=a;		
		arrayList = arraylist;
		wm = (WindowManager)activity.getSystemService(Context.WINDOW_SERVICE);
		height = wm.getDefaultDisplay().getHeight();
		width=wm.getDefaultDisplay().getWidth();
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);			
	}
	public static class ViewHolder{		
	ImageView picturesView ;
	CheckBox check;
		public int position;  		
	}
	public int getCount() { 
		return arrayList.size();
	}
	public Object getItem(int position) {
		return position;
	}
	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;		
		if(convertView == null){	
			vi = inflater.inflate(R.layout.gallery_grid_details,parent,false);
			holder=new ViewHolder();
			holder. picturesView = (ImageView)vi.findViewById(R.id.categoryImg);			
			holder. check = (CheckBox)vi.findViewById(R.id.check);		
			vi.setTag(holder);
		}
		else
		{       
			holder = (ViewHolder)vi.getTag();
		}
		System.out.println("sss2ssssd=====sd.legth======"+arrayList.get(position));
		 Bitmap myBitmap = BitmapFactory.decodeFile(arrayList.get(position).path);
	  holder.picturesView.setImageBitmap(myBitmap);
	  holder.check.setTag(Integer.toString(position));
		holder.check.setOnClickListener(this);
		return vi;
	}

	public static String getPosition(){
		return posClicked;
	}

	public void onClick(View v) {	
	
		switch (v.getId()) {
		case R.id.check:
			CheckBox viewprof = (CheckBox) v;
			String viewp = (String) viewprof.getTag();
			if (viewprof.isPressed()) {				
				posClicked	=	viewp;
				int pos=Integer.parseInt(posClicked);
				if(viewprof.isChecked())
				{
					addToDeletePath(arrayList.get(pos).path);
				}
				else
				{
					removeFromDeletePath(arrayList.get(pos).path);	
				}
				
			}
			break;
		
		}
	}
	
	private void addToDeletePath(String path) {
		if(checkDuplicate(path)==false)
		filePathDelete .add(path);
	}
	private void removeFromDeletePath(String path) {
		
		filePathDelete .remove(path);
	}
	public  boolean checkDuplicate(String path) {
		if (filePathDelete == null)
			return false;
		for (int i = 0; i < filePathDelete.size(); i++) {

			if (filePathDelete.get(i) == path) {
				return true;
			}
		}
		return false;
	}
	
	public void deleteFiles(){
		for (int i = 0; i < filePathDelete.size(); i++) {
			try{
			String path = filePathDelete.get(i);
			javaxt.io.File nfile = new javaxt.io.File(path);
			nfile.delete();
			}catch(Exception e){
				
			}
		}
	}
}
