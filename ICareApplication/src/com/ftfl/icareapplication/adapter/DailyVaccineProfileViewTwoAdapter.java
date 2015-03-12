package com.ftfl.icareapplication.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftfl.icareapplication.R;
import com.ftfl.icareapplication.model.Vaccination;



public class DailyVaccineProfileViewTwoAdapter extends ArrayAdapter<Vaccination>{
	
	TextView tvName, tvTime, tvDate;
	ImageView imageAlarm;
	
	private final Activity context;
	List<Vaccination> allDailyDietChart;
	
	public DailyVaccineProfileViewTwoAdapter(Activity context, List<Vaccination> allDailyDietChart) {
		super(context, R.layout.activity_dietlist_view,allDailyDietChart);
		this.context = context;
		this.allDailyDietChart = allDailyDietChart;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Vaccination mDietChart;
		mDietChart = allDailyDietChart.get(position);
		
		LayoutInflater inflater = context.getLayoutInflater();
		
		View rowView = inflater.inflate(R.layout.activity_dietlist_view, null, true);
		
		tvName = (TextView) rowView.findViewById(R.id.viewEvent);
		tvName.setText(mDietChart.getVaccineName().toString());
		
		tvTime = (TextView) rowView.findViewById(R.id.viewTime);
		tvTime.setText(mDietChart.getTime().toString());
		
		tvDate = (TextView) rowView.findViewById(R.id.viewManu);
		tvDate.setText(mDietChart.getDate().toString());
		
		String mAlarm = mDietChart.getVaccineAlarm();
		if(mAlarm!=" "){
		
		imageAlarm = (ImageView) rowView.findViewById(R.id.imageAlarm);
	
		}
		
		return rowView;
	}

}
