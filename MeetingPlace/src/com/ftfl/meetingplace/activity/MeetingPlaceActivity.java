package com.ftfl.meetingplace.activity;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ftfl.meetingplace.R;



public class MeetingPlaceActivity extends Activity {

	Button mBtnRegister;
	Button mBtnRetrieve;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);

		mBtnRegister = (Button) findViewById(R.id.regBtn);
		mBtnRetrieve = (Button) findViewById(R.id.viewBtn);

		mBtnRegister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),
						InsertPlaceInfoActivity.class);
				startActivity(i);

			}
		});

		mBtnRetrieve.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						PlaceListViewActivity.class);
				startActivity(i);

			}
		});

	}

}

