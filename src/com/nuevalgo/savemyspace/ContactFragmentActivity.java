package com.nuevalgo.savemyspace;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.Adapter.ContactListAdapter;

public class ContactFragmentActivity extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
	}
	public ContactFragmentActivity()
	{	
	}
	public ContactFragmentActivity(Context c) 
	{
		this.mContext = c;
		contactList =  (ListView) ((Activity) c).findViewById(R.id.listview);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}
ListView contactList;
ContactListAdapter adapter;
Context mContext;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_contact, container, false);	
		contactList =  (ListView) view.findViewById(R.id.listview);
		adapter=new ContactListAdapter(mContext);						
		contactList.setAdapter(adapter);
		return view;
	}
}