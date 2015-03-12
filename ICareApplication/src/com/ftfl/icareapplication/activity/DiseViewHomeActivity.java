package com.ftfl.icareapplication.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftfl.icareapplication.R;
import com.ftfl.icareapplication.database.DoctorDataSource;
import com.ftfl.icareapplication.database.ICareDietDataSource;
import com.ftfl.icareapplication.database.MedicalHistoryDataSourc;
import com.ftfl.icareapplication.database.ProfileDataSource;
import com.ftfl.icareapplication.database.VaccinationDataSourc;
import com.ftfl.icareapplication.model.DoctorProfile;
import com.ftfl.icareapplication.model.Profile;



public class DiseViewHomeActivity extends Activity {
	ICareDietDataSource mDietDataSource = null;
	// ICareActivityDietChart mProfile = null;

	ImageView imgDietChaet = null;
	ImageView imgDoctor = null;
	ImageView imgMediccal = null;
	ImageView imgVaccination = null;

	TextView tvName = null;
	TextView tvAge = null;
	TextView tvWeight = null;
	TextView tvHeight = null;
	TextView tvBloodGroup = null;
	TextView tvGender = null;

	
	DoctorDataSource mDoctorDataSource = null;
	DoctorProfile mDoctorProfile = null;
	ProfileDataSource mHospitalDS = null;
	MedicalHistoryDataSourc mMedicalDs=null;
	VaccinationDataSourc mVaccineDs=null;
	ICareDietDataSource mDietDS=null;
	Profile mUpdateHospital = null;
	List<String> mIdList = new ArrayList<String>();
	String mID = "";
	Long mLId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		
		 //Naseer Action bar
  		ActionBar bar = getActionBar();
  		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00868B")));
  		
		mDoctorDataSource = new DoctorDataSource(this);
		mMedicalDs = new MedicalHistoryDataSourc(this);
		mVaccineDs = new VaccinationDataSourc(this);
		mDietDS= new ICareDietDataSource(this);
		
		tvName = (TextView) findViewById(R.id.viewProfileName);
		tvAge = (TextView) findViewById(R.id.viewProfileAge);
		tvGender = (TextView) findViewById(R.id.viewProfileGender);
		tvBloodGroup = (TextView) findViewById(R.id.viewProfileBirthGroup);

		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("id");

		if (mID != null) {
			mLId = Long.parseLong(mID);

			/*
			 * get the activity which include all data from database according
			 * hospitalId of the clicked item.
			 */
			mHospitalDS = new ProfileDataSource(this);
			mUpdateHospital = mHospitalDS.singleProfileData(mLId);
			
			String mName = mUpdateHospital.getName();
			String mAge = mUpdateHospital.getAge();
			String mGender = mUpdateHospital.getGender();
			String mBloodGroup = mUpdateHospital.getBloodGroup();

			// set the value of database to the text field.

			tvName.setText(mName);
			tvAge.setText(mAge);
			tvGender.setText(mGender);
			tvBloodGroup.setText(mBloodGroup);

			mDietDataSource = new ICareDietDataSource(this);
			imgDietChaet = (ImageView) findViewById(R.id.imageViewDietChart);
			imgDietChaet.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
				
					if (mDietDS.isEmpty()) {
						Intent i = new Intent(DiseViewHomeActivity.this,
								CreateDietChartActivity.class);
						startActivity(i);
					} 
					else {
						Intent i = new Intent(DiseViewHomeActivity.this,
								DietListActivity.class);
						startActivity(i);
					}


				}
			});

			imgDoctor = (ImageView) findViewById(R.id.ImageViewDoctor);
			imgDoctor.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					
					if (mDoctorDataSource.isEmpty()) {
						Intent i = new Intent(DiseViewHomeActivity.this,
								CreateDoctorActivity.class);
						startActivity(i);
					} 
					else {
						Intent i = new Intent(DiseViewHomeActivity.this,
								DoctorListViewActivity.class);
						startActivity(i);
					}
				}
			});

			imgMediccal = (ImageView) findViewById(R.id.ImageViewMedical);
			imgMediccal.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					if (mMedicalDs.isEmpty()) {
						Intent i = new Intent(DiseViewHomeActivity.this,
								CreateMedicalHistory.class);
						startActivity(i);
					} 
					else {
						Intent i = new Intent(DiseViewHomeActivity.this,
								MedicalHistoryListViewActivity.class);
						startActivity(i);
					}
				
				}
			});
			// super.onCreate(savedInstanceState);
			// setContentView(R.layout.activity_home);
			//
			imgVaccination = (ImageView) findViewById(R.id.imageViewVeccination);
			imgVaccination.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					if (mVaccineDs.isEmpty()) {
						Intent i = new Intent(DiseViewHomeActivity.this,
								CreateVaccinationProfile.class);
						startActivity(i);
					} 
					else {
						Intent i = new Intent(DiseViewHomeActivity.this,
								VaccinationListActivity.class);
						startActivity(i);
					}
					

				}
			});
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_profile_actionbar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addProfilemanu:
			startActivity(new Intent(DiseViewHomeActivity.this,
					CreateProfileActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
