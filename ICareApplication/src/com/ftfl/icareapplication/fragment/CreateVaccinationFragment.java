package com.ftfl.icareapplication.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ftfl.icareapplication.R;





@SuppressLint("NewApi")
public class CreateVaccinationFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_create_vaccination, container, false);
		
//		 Button button = (Button) rootView.findViewById(R.id.button1);
//			   button.setOnClickListener(new OnClickListener()
//			   {
//			             @Override
//			             public void onClick(View v)
//			             {
//			                // do something
//			            	 
//			            	 Test ldf = new Test ();
//			           	  Bundle args = new Bundle();
//			           	  args.putString("YourKey", "YourValue");
//			           	  ldf.setArguments(args);
//
//			           	  //Inflate the fragment
//			           	  getFragmentManager().beginTransaction().add(R.id.container, ldf).commit();
//			             } 
//			   }); 
        
        return rootView;
	}
  /*public void click(View v){
	  GrowthFragment ldf = new GrowthFragment ();
	  Bundle args = new Bundle();
	  args.putString("YourKey", "YourValue");
	  ldf.setArguments(args);

	  //Inflate the fragment
	  getFragmentManager().beginTransaction().add(R.id.container, ldf).commit();
  }*/

}
