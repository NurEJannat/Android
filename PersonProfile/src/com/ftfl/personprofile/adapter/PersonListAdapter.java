package com.ftfl.personprofile.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.ftfl.personprofile.R;
import com.ftfl.personprofile.model.PersonInformation;

public class PersonListAdapter extends ArrayAdapter<PersonInformation>{
	private final Activity context;
	private static ArrayList<PersonInformation> per_info_obj;;

	static class ViewHolder {
		public TextView latitude,longitude;
		public ImageView image;
	}

	/*
	 * private ArrayList<String> names; private ArrayList<String> times; private
	 * ArrayList<String> menus;
	 */

	

	public PersonListAdapter(Activity context,
			ArrayList<PersonInformation> res_info_obj) {
		super(context, R.layout.activity_show_profile, per_info_obj);
		this.context = context;
		this.per_info_obj = res_info_obj;
		/*
		 * this.names = names; this.times = times; this.menus = menus;
		 */
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (convertView == null) {
			// LayoutInflater
			// inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.activity_show_profile, parent,
					false);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.latitude = (TextView) rowView
					.findViewById(R.id.res_name);
			viewHolder.longitude = (TextView) rowView
					.findViewById(R.id.res_addresss);
			
			viewHolder.image = (ImageView) rowView
					.findViewById(R.id.restaurantImage);
			
			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		holder.latitude.setText(per_info_obj.get(position).getLatitude());
		holder.longitude.setText(per_info_obj.get(position).getLongitude());
		//setPic(holder, position);
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 8;

		/*Bitmap bitmap= BitmapFactory.decodeFile(res_info_obj.get(position).getImage(),options);

		  
		       
		    	
				holder.image.setImageBitmap(bitmap);
		    	bitmap.recycle();
		    	*/
		
		
		return rowView;
	}
	

	
}
