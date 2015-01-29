package com.example.privateuniversityinformation;


import android.app.Activity;
import android.os.Bundle;



import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;




public class LocationMapActivity extends Activity{

	 
	 // Google Map
    private GoogleMap googleMap;
	
	// latitude and longitude
	double latitude = 23.7000;
	double longitude =90.3750 ;
	 
	
	
    static final LatLng Dhaka = new LatLng(21 , 57);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		
	
	try { 
        if (googleMap == null) {
           googleMap = ((MapFragment) getFragmentManager().
           findFragmentById(R.id.map)).getMap();
        }
     googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
     Marker TP = googleMap.addMarker(new MarkerOptions().
     position(Dhaka).title("BCC"));

  } catch (Exception e) {
     e.printStackTrace();
  }

	
		
	
}}
