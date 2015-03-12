package com.ftfl.icareapplication.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ftfl.icareapplication.R;
import com.ftfl.icareapplication.database.DoctorDataSource;
import com.ftfl.icareapplication.model.DoctorProfile;



public class DoctorShowDetailActivity extends Activity {

	TextView tvName = null;
	TextView tvSpeciality = null;
	TextView tvPhone = null;
	TextView tvEmail = null;
	TextView tvChamber = null;

	ImageView mBtnCall;
	ImageView mBtnEmail;
	ImageView mBtnSMS;
	DoctorDataSource mHospitalDS = null;
	DoctorProfile mUpdateHospital = null;
	String mName;
	String mSpeciality;
	String mEmail;
	String mChamber;
	String mPhone;
	String mID = "";
	Long mLId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_detail_view);
		
		 //Naseer Action bar
  		ActionBar bar = getActionBar();
  		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00868B")));

		tvName = (TextView) findViewById(R.id.viewDoctorName);
		tvSpeciality = (TextView) findViewById(R.id.viewDoctorSpecility);
		tvPhone = (TextView) findViewById(R.id.viewDoctorPhone);
		tvEmail = (TextView) findViewById(R.id.viewDoctorEmail);
		tvChamber = (TextView) findViewById(R.id.viewDoctorChamber);
		// tvBloodGroup = (TextView) findViewById(R.id.viewProfileBirthGroup);
		mBtnCall = (ImageView) findViewById(R.id.view_img_call);
		mBtnEmail = (ImageView) findViewById(R.id.view_img_Email);
		mBtnSMS = (ImageView) findViewById(R.id.view_img_message);
		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("id");

		if (mID != null) {
			mLId = Long.parseLong(mID);

			/*
			 * get the activity which include all data from database according
			 * hospitalId of the clicked item.
			 */
			mHospitalDS = new DoctorDataSource(this);
			mUpdateHospital = mHospitalDS.singleDoctorProfileData(mLId);

			mName = mUpdateHospital.getDoctorName();
			mSpeciality = mUpdateHospital.getSpeciality();
			mPhone = mUpdateHospital.getPhone();
			mEmail = mUpdateHospital.getEmail();
			mChamber = mUpdateHospital.getChamber();

			// set the value of database to the text field.

			tvName.setText(mName);
			tvSpeciality.setText(mSpeciality);
			tvPhone.setText(mPhone);
			tvEmail.setText(mEmail);
			tvChamber.setText(mChamber);

		}
		mBtnCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ mPhone));
				startActivity(intent);

			}
		});

		mBtnEmail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri
						.fromParts("mailto", mEmail, null));
				// emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EXTRA_SUBJECT");
				startActivity(Intent
						.createChooser(emailIntent, "Send email..."));

			}
		});
		mBtnSMS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {

					Intent smsIntent = new Intent(Intent.ACTION_VIEW);
					smsIntent.putExtra("address", mPhone);
					smsIntent.setType("vnd.android-dir/mms-sms");
					startActivity(smsIntent);

				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "SMS faild!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});
	}


}
