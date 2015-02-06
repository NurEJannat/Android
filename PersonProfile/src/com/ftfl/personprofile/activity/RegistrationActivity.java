package com.ftfl.personprofile.activity;



import com.ftfl.personprofile.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RegistrationActivity extends Activity {

	
	Button mRegister;
	Button mRetrive;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		mRegister = (Button) findViewById(R.id.btnReg);
		mRetrive = (Button) findViewById(R.id.btnRet);
		
		mRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				  Intent in = new Intent( RegistrationActivity.this, InsertPersonActivity.class);

				  startActivity(in);
			}
		});
	 
		mRetrive.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				  Intent in = new Intent( RegistrationActivity.this, ShowProfileActivity.class);

				  startActivity(in);
			}
	 
	
});
}
}