package com.ftfl.personprofile.activity;



import com.ftfl.personprofile.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	
	Button register;
	Button retrive;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		register = (Button) findViewById(R.id.button1);
		retrive = (Button) findViewById(R.id.button2);
		
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				  Intent in = new Intent( MainActivity.this, InsertPersonActivity.class);

				  startActivity(in);
			}
		});
	 
		retrive.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				  Intent in = new Intent( MainActivity.this, ShowProfileActivity.class);

				  startActivity(in);
			}
	 
	
});
}
}