package com.ftfl.icareapplication.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ftfl.icareapplication.R;
import com.ftfl.icareapplication.database.ProfileDataSource;
import com.ftfl.icareapplication.model.Profile;



public class CreateProfileActivity extends Activity implements OnClickListener {

	Button btns_save = null;
	EditText etName = null;
	EditText etAge = null;
	EditText etWeight = null;
	EditText etHeight = null;
	EditText etGender = null;
	EditText etBloodGroup = null;

	String mName = "";
	String mAge = "";
	String mWeight = "";
	String mHeight = "";
	String mGender = "";
	String mBloodGroup = "";
	private RadioGroup radioSexGroup;
	private RadioButton radioSexButton;
	private RadioButton female;
	private RadioButton male;
	TextView tvItem = null;
	String mID = "";
	Long mLId;
	ProfileDataSource mProfileDS = null;
	Profile mUpdateProfile = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_user_profile);
		
		 //Naseer Action bar
  		ActionBar bar = getActionBar();
  		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00868B")));

		etName = (EditText) findViewById(R.id.etProfileName);
		etAge = (EditText) findViewById(R.id.etProfileAge);
		etWeight = (EditText) findViewById(R.id.etProfileWeight);
		etHeight = (EditText) findViewById(R.id.etProfileHeight);
		radioSexGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		
		int selectedId = radioSexGroup.getCheckedRadioButtonId();
		
		radioSexButton = (RadioButton) findViewById(selectedId);
		
		// etGender = (EditText) findViewById(R.id.addServiceDescription);
		etBloodGroup = (EditText) findViewById(R.id.etProfileBloodGroup);
		btns_save = (Button) findViewById(R.id.btnProfileSubmit);

		btns_save.setOnClickListener(this);

		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("id");

		if (mID != null) {
			mLId = Long.parseLong(mID);

			/*
			 * get the activity which include all data from database according
			 * profileId of the clicked item.
			 */
			mProfileDS = new ProfileDataSource(this);
			mUpdateProfile = mProfileDS.singleProfileData(mLId);

			String mName = mUpdateProfile.getName();
			String mAge = mUpdateProfile.getAge();
			String mWeight = mUpdateProfile.getWeight();
			String mHeight = mUpdateProfile.getHeight();
			String mBloodGroup = mUpdateProfile.getBloodGroup();
			String mGender = mUpdateProfile.getGender();
			// set the value of database to the text field.

			etName.setText(mName);
			etAge.setText(mAge);
			etWeight.setText(mWeight);
			etHeight.setText(mHeight);
			etGender.setText(mGender);
			etBloodGroup.setText(mBloodGroup);

			/*
			 * change button name
			 */
			btns_save.setText("Update");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast toast = null;
		switch (v.getId()) {

		case R.id.btnProfileSubmit:
			
			
			mGender = radioSexButton.getText().toString();
			mName = etName.getText().toString();
			mAge = etAge.getText().toString();
			mWeight = etWeight.getText().toString();
			mHeight = etHeight.getText().toString();
			mBloodGroup = etBloodGroup.getText().toString();

			Profile profileInsert = new Profile();
			profileInsert.setName(mName);
			profileInsert.setAge(mAge);
			profileInsert.setWeight(mWeight);
			profileInsert.setHeight(mHeight);
			profileInsert.setBloodGroup(mBloodGroup);
			profileInsert.setGender(mGender);
//			if(profileInsert.getGender().equals("Male"))
//			{
//				male.setChecked(true);
//				
//				
//			}
//			else
//			{
//				female.setChecked(true);
//				profileInsert.setGender(mGender);
//			}
			
			/*
			 * if update is needed then update otherwise submit
			 */
			if (mID != null) {

				mLId = Long.parseLong(mID);

				mProfileDS = new ProfileDataSource(this);

				if (mProfileDS.updateData(mLId, profileInsert) == true) {
					toast = Toast.makeText(this, "Successfully Updated.",
							Toast.LENGTH_LONG);
					toast.show();
					// startActivity(new Intent(CreateHospitalActivity.this,
					// HospitalListActivity.class));
					finish();
				} else {
					toast = Toast.makeText(this, "Not Updated.",
							Toast.LENGTH_LONG);
					toast.show();
				}
			} else {

				mProfileDS = new ProfileDataSource(this);
				if (mProfileDS.insert(profileInsert) == true) {
					toast = Toast.makeText(this, "Successfully Saved.",
							Toast.LENGTH_LONG);
					toast.show();

					 startActivity(new Intent(CreateProfileActivity.this,
					 ProfileListHomeActivity.class));

					// finish();
				} else {
					toast = Toast.makeText(this, "Not Saved.",
							Toast.LENGTH_LONG);
					toast.show();
				}

				break;
			}
		}

	}

}
