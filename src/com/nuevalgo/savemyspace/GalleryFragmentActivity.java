package com.nuevalgo.savemyspace;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.Adapter.GalleryListAdapter;

public class GalleryFragmentActivity extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
	}
	public GalleryFragmentActivity()
	{	
	}ListView galleryList;
	GalleryListAdapter adapter;
	Context mContext;
	
	public GalleryFragmentActivity(Context c)
	{
		this.mContext = c;
		galleryList =  (ListView) ((Activity) c).findViewById(R.id.listviewg);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_gallery, container, false);	
		galleryList =  (ListView) view.findViewById(R.id.listviewg);
		adapter=new GalleryListAdapter(mContext);						
		galleryList.setAdapter(adapter);
		return view;
	}
	
	public void deleteFiles(){
		adapter.deleteFiles();
	}
	
}