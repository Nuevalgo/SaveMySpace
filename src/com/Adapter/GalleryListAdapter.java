package com.Adapter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nuevalgo.savemyspace.Image;
import com.nuevalgo.savemyspace.R;
public class GalleryListAdapter extends BaseAdapter  implements OnClickListener{	
	/*
	 * developer :sanu
	 * date :10-8-2013
	 * time :2.24 pm
	 */
	public View row;
	private  LayoutInflater inflater=null;
	String posClicked;
	ViewHolder holder;	
	Context weakContext;
	ArrayList<Image> imagesGreaterThanOneYear = new ArrayList<Image>();  
	ArrayList<Image> imagesGreaterThanOneMonth = new ArrayList<Image>();  
	ArrayList<Image> imagesLessThanOneMonth = new ArrayList<Image>();  
	ArrayList<Image> images = new ArrayList<Image>();  
	File[] listFile;
	GridAdapter greaterThanYearAdapter, greaterThanMonthAdapter, lessThanMonthAdapter;
	public GalleryListAdapter(Context context) {
		this.weakContext = context;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);				
		File file= new File(Environment.getExternalStorageDirectory()+"/Downloads/");
		
		if (file.isDirectory())
		{
			listFile = file.listFiles();


			for (int i = 0; i < listFile.length; i++)
			{
				Image img = new Image();
				img.path = listFile[i].getAbsolutePath();
				javaxt.io.File nfile = new javaxt.io.File(listFile[i]);
				img.lastAccessedDate = nfile.getLastModifiedTime();
				images.add(img);
			}
		}
		
		sortByLastModified();
		groupByDate();
	}
	
	public void sortByLastModified(){

		Collections.sort(images, new Comparator<Image>() {
			public int compare(Image o1, Image o2) {
				return o1.lastAccessedDate.compareTo(o2.lastAccessedDate);
			}

		});
	}
	
	public void groupByDate(){
		for (Image img : images){
			Date oneYearBack = new Date();
			oneYearBack.setYear(oneYearBack.getYear()-1);
			Date oneMonthBack = new Date();
			oneMonthBack.setMonth(oneMonthBack.getMonth()-1);
			Date afterOneMonth = new Date();
			if(img.lastAccessedDate.compareTo(oneYearBack)<1){
				imagesGreaterThanOneYear.add(img);
			}else if(img.lastAccessedDate.compareTo(oneMonthBack)<1){
				imagesGreaterThanOneMonth.add(img);
			}else if(img.lastAccessedDate.compareTo(afterOneMonth)<=1){
				imagesLessThanOneMonth.add(img);
			}
		}
		    
	}
	public int getCount() {
		return 3;
	}
	public Object getItem(int position) {
		return position;
	}
	public long getItemId(int position) {
		return position;
	} 
	public static class ViewHolder{

TextView labelText;
		public int position;  
	}
	com.nuevalgo.savemyspace.NonScrollableGridView gridView;
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;
		if(convertView == null){			
			vi = inflater.inflate(R.layout.gallery_list_details,parent, false);
			holder=new ViewHolder();	
			gridView = (com.nuevalgo.savemyspace.NonScrollableGridView)vi. findViewById(R.id.grid_view);	
			holder. labelText = (TextView)vi.findViewById(R.id.toplabel);	
			if(position ==0) 
			{
				greaterThanYearAdapter = new GridAdapter(weakContext, imagesGreaterThanOneYear);
			gridView.setAdapter(greaterThanYearAdapter);
			holder. labelText  .setText("One year before");
			}
			else if(position ==1)
			{
				greaterThanMonthAdapter = new GridAdapter(weakContext, imagesGreaterThanOneMonth);
			gridView.setAdapter(greaterThanMonthAdapter);
			holder. labelText  .setText("One month before");
			}
			else if (position ==2)
			{
				lessThanMonthAdapter = new GridAdapter(weakContext, imagesLessThanOneMonth);
			gridView.setAdapter(lessThanMonthAdapter);	
			holder. labelText  .setText("less than one month");
			}
			vi.setTag(holder);						
		}
		else
		{                                                                                                                                                                                                   
			holder = (ViewHolder)vi.getTag();
		}

		return vi;
	} 
	public void onClick(View v) 
	{}
	public  String getPosition(){
		return posClicked;
	}
	public void deleteFiles(){
		if(greaterThanYearAdapter!= null) 
			greaterThanYearAdapter.deleteFiles();
		if(greaterThanMonthAdapter!= null)
			greaterThanMonthAdapter.deleteFiles();
		if(lessThanMonthAdapter!= null)
			lessThanMonthAdapter.deleteFiles();
	}
}