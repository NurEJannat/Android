package com.ftfl.icareapplication.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ftfl.icareapplication.R;
import com.ftfl.icareapplication.adapter.CareCenterAdapter;


public class CareCenterActivity extends Activity {

	 ListView listCare;
	  String[] mCareName = {
	    "Central Hospital",
	    "United  Hospital",
	    "Islami Bank Hospital ",
	    "Square  Hospital",
	    
	  } ;

	  
	  String[] mLatitude = {
			    "28.81",
			    "26.997",
			    "23.30",
			    "22.802",
			   
			  } ;
	  
	  String[] mLongitude = {
			    "94.59",
			    "96.461",
			    "89.19",
			    "91.760",
			   	    
			  } ;
	
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_carecentermain);
	    
	  //Naseer Action bar
	  		ActionBar bar = getActionBar();
	  		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00868B")));
	  		
	    CareCenterAdapter adapter = new
	    		CareCenterAdapter(CareCenterActivity.this, mCareName, mLatitude, mLongitude);
	    listCare=(ListView)findViewById(R.id.listCare);
	    listCare.setAdapter(adapter);
	    listCare.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	                @Override
	                public void onItemClick(AdapterView<?> parent, View view,
	                                        int position, long id) {
	                    Toast.makeText(CareCenterActivity.this, "You Clicked at " +mCareName[+ position], Toast.LENGTH_SHORT).show();
	                }
	            });
	  }

}
