package com.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nuevalgo.savemyspace.R;
public class ContactListAdapter extends BaseAdapter  implements OnClickListener{	
	/*
	 * developer :sanu
	 * date :10-8-2013
	 * time :2.24 pm
	 */
	public View row;
	private static LayoutInflater inflater=null;
	static String posClicked;
	ViewHolder holder;	
	Context weakContext;
	public ContactListAdapter(Context context) {
					inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);				
		
	}
	public int getCount() {
		return 10;
	}
	public Object getItem(int position) {
		return position;
	}
	public long getItemId(int position) {
		return position;
	} 
	public static class ViewHolder{
		

		public int position;  
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;
		if(convertView == null){			
			vi = inflater.inflate(R.layout.contact_list_details,parent, false);
			holder=new ViewHolder();			
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
	public static String getPosition(){
		return posClicked;
	}
}