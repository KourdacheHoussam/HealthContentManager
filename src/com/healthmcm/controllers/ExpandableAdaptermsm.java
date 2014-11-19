package com.healthmcm.controllers;


import com.example.healthmcm.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ExpandableAdaptermsm extends BaseAdapter implements OnClickListener{
    private String []	mArray;
    private Context 	mContext;
    private int 		pagination;
    private int 		current_page;
    private LayoutInflater	mInflater;
	public ExpandableAdaptermsm(Context _context, String[] _array, int _pagination) {
		mArray=_array;
        mContext=_context;
        pagination=_pagination;
        current_page=1;
        mInflater =LayoutInflater.from(_context);
	}
	
	private static class ViewHolder {
		public TextView		text;
		public ImageView	icon;
		public Button		button;
	}

	public int getCount() {
		return Math.min(pagination*current_page,mArray.length);
	}

	public Object getItem(int position) {
		return mArray[position];
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item, null);		
			holder		= new ViewHolder();

			holder.button = (Button) convertView.findViewById(R.id.button);
			
			holder.text = (TextView)  convertView.findViewById(R.id.text);
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			convertView.setTag(holder);
		
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		if(position==pagination*current_page-1)
		{
			holder.button.setVisibility(View.VISIBLE);
			holder.text.setVisibility(View.GONE);
			holder.icon.setVisibility(View.GONE);
			holder.button.setOnClickListener(this);
		
		 }
		else
		{
			holder.button.setVisibility(View.GONE);
			holder.text.setVisibility(View.VISIBLE);
			holder.icon.setVisibility(View.VISIBLE);
			
			holder.text.setText(mArray[position]);	
			holder.icon.setImageResource(R.drawable.ic_menu_move_down);
		}
		return convertView;
	}
	public void onClick(View v) {
		current_page++;
	 String l =  ((TextView)v).getText().toString();
//	 Toast toast=Toast.makeText("", l, Toast.LENGTH_SHORT);
  //     toast.show();
		
		notifyDataSetChanged();
	}

}
