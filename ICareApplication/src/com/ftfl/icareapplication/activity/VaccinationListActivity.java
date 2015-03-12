package com.ftfl.icareapplication.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ftfl.icareapplication.R;
import com.ftfl.icareapplication.database.DatabaseSQLiteHelper;
import com.ftfl.icareapplication.database.VaccinationDataSourc;
import com.ftfl.icareapplication.model.Vaccination;




public class VaccinationListActivity extends Activity {
	VaccinationDataSourc mVaccineDataSource = null;
	List<Vaccination> mProfileList = new ArrayList<Vaccination>();
	List<String> mNamesList = new ArrayList<String>();
	List<String> mIdList = new ArrayList<String>();
	ListView mListView = null;
	Integer mPosition = 0;
	ListView mListViewTwo;
	
	DatabaseSQLiteHelper mDBHelper;
	Vaccination dailyDietChart;
	int id_To_Update = 0;
	String mDate;
	TextView textDate;
	TextView tvDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vactionation_historylistview);

		
		 //Naseer Action bar
  		ActionBar bar = getActionBar();
  		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00868B")));
  		
  		
		mDBHelper = new DatabaseSQLiteHelper(this);	
		mVaccineDataSource = new VaccinationDataSourc(this);
		final String[] option = new String[] { "View Detail", "Edit Data",
				"Delete Data" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, option);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Select Option");
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.e("Selected Item", String.valueOf(which));
				if (which == 0) {
					viewData(mPosition);
				}
				if (which == 1) {
					editData(mPosition);
				}

				if (which == 2) {
					deleteData(mPosition);
				}
			}

		});
		final AlertDialog dialog = builder.create();

		setListData();
		mListView = (ListView) findViewById(R.id.lvAllVaccineChart);

		ArrayAdapter<String> mProfileAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mNamesList);

		mListView.setAdapter(mProfileAdapter);

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mPosition = position;
				dialog.show();
				// startActivity(new Intent(ProfileListHomeActivity.this,
				// DiseViewHomeActivity.class));
			}
		});
		
	}

	private void setListData() {
		VaccinationDataSourc profiledata = new VaccinationDataSourc(this);
		mProfileList = profiledata.iCareDoctorProfilesList();
		for (int i = 0; i < mProfileList.size(); i++) {
			Vaccination mProfile = mProfileList.get(i);
			mIdList.add(mProfile.getId());
			mNamesList.add(mProfile.getVaccineName());
		}

	}

	public void viewData(Integer ePosition) {
		Intent mEditIntent = new Intent(getApplicationContext(),
				VaccinationShowProfileActivity.class);
		Long id = Long.parseLong(mIdList.get(ePosition));
		mEditIntent.putExtra("id", id.toString());
		startActivity(mEditIntent);
	}

	public void editData(Integer ePosition) {
		Intent mEditIntent = new Intent(getApplicationContext(),
				CreateVaccinationProfile.class);
		Long id = Long.parseLong(mIdList.get(ePosition));
		mEditIntent.putExtra("id", id.toString());
		startActivity(mEditIntent);

	}

	public void deleteData(Integer ePosition) {
		mVaccineDataSource = new VaccinationDataSourc(this);

		Long mId = Long.parseLong(mIdList.get(ePosition));
		mVaccineDataSource.deleteData(mId);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vacciemanu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addvaccineProfilemanu:
			startActivity(new Intent(VaccinationListActivity.this,
					CreateVaccinationProfile.class));
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}


}
