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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.ftfl.icareapplication.R;
import com.ftfl.icareapplication.database.ProfileDataSource;
import com.ftfl.icareapplication.model.Profile;





public class ProfileListHomeActivity extends Activity{

	ProfileDataSource mProfileDS = null;
	List<Profile> mProfileList = new ArrayList<Profile>();
	List<String> mNamesList = new ArrayList<String>();
	List<String> mIdList = new ArrayList<String>();
	ListView mListView = null;
	Integer mPosition = 0;
	ImageView imgEmergencyCall, mAbout,mCareCenter,mImportantNotes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview_user_profile);

		
		 //Naseer Action bar
  		ActionBar bar = getActionBar();
  		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00868B"))); 
  		
  		
		setListData();
		mListView = (ListView) findViewById(R.id.lv_profilelistview);

		ArrayAdapter<String> mProfileAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mNamesList);

		mListView.setAdapter(mProfileAdapter);

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mPosition = position;
				viewData(mPosition);
				// startActivity(new Intent(ProfileListHomeActivity.this,
				// DiseViewHomeActivity.class));
			}
		});

		imgEmergencyCall = (ImageView) findViewById(R.id.imgVEmergencyCall);
		imgEmergencyCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(ProfileListHomeActivity.this,
						EmergencyCallActivity.class);
				startActivity(i);
			}
		});
		mAbout = (ImageView) findViewById(R.id.img_About);
		mAbout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(ProfileListHomeActivity.this,
						GeneralHealthInfo.class);
				startActivity(i);
			}
		});
		
		mCareCenter = (ImageView) findViewById(R.id.img_carecenter);
		mCareCenter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(ProfileListHomeActivity.this,
						CareCenterActivity.class);
				startActivity(i);
			}
		});
		
		mImportantNotes = (ImageView) findViewById(R.id.img_importantnotes);
		mImportantNotes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(ProfileListHomeActivity.this,
						ImportantNotesListViewActivity.class);
				startActivity(i);
			}
		});
	}

	private void setListData() {
		ProfileDataSource profiledata = new ProfileDataSource(this);
		mProfileList = profiledata.iCareProfilesList();
		for (int i = 0; i < mProfileList.size(); i++) {
			Profile mProfile = mProfileList.get(i);
			mIdList.add(mProfile.getId());
			mNamesList.add(mProfile.getName());
		}

	}

	public void viewData(Integer ePosition) {
		Intent mEditIntent = new Intent(getApplicationContext(),
				DiseViewHomeActivity.class);
		Long id = Long.parseLong(mIdList.get(ePosition));
		mEditIntent.putExtra("id", id.toString());
		startActivity(mEditIntent);
	}

	public void editData(Integer ePosition) {
		Intent mEditIntent = new Intent(getApplicationContext(),
				CreateProfileActivity.class);
		Long id = Long.parseLong(mIdList.get(ePosition));
		mEditIntent.putExtra("id", id.toString());
		startActivity(mEditIntent);

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dise_view_actionbar, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addProfilemanu:
			startActivity(new Intent(ProfileListHomeActivity.this,
					CreateProfileActivity.class));
			return true;
		case R.id.ViewProfilemanu:
			// viewData();
			startActivity(new Intent(ProfileListHomeActivity.this,
					ProfileListActivity.class));
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
