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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ftfl.icareapplication.R;
import com.ftfl.icareapplication.adapter.MedicalHistoryCustomAdapter;
import com.ftfl.icareapplication.database.DatabaseSQLiteHelper;
import com.ftfl.icareapplication.database.MedicalHistoryDataSourc;
import com.ftfl.icareapplication.model.MedicalHistory;



public class MedicalHistoryListViewActivity extends Activity {

	List<String> mIdList = new ArrayList<String>();

	DatabaseSQLiteHelper mDBHelper;
	MedicalHistoryDataSourc mPlaceDataSource;
	ListView mListView;

	TextView mId_tv = null;

	String mId = "";
	String mLatitude = "";
	String mLongitude = "";

	TextView mCurrentLocation;
	Button mBackButton;
	Integer mPosition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medicalhistory_list_view);
		
		 //Naseer Action bar
  		ActionBar bar = getActionBar();
  		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00868B")));
  		
  		
		mDBHelper = new DatabaseSQLiteHelper(this);
		mPlaceDataSource = new MedicalHistoryDataSourc(this);
		setListData();
		List<MedicalHistory> galery_list = mPlaceDataSource
				.iCareMedicalProfilesList();
		MedicalHistoryCustomAdapter arrayAdapter = new MedicalHistoryCustomAdapter(
				this, galery_list);
		final String[] option = new String[] { "View Contact Details",
				"Edit Data", "Delete Data" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, option);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Select Option");
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				Log.e("Selected Item", String.valueOf(which));
				if (which == 0) {
					// viewContact(mPosition);
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

		mListView = (ListView) findViewById(R.id.lvMedicalHistoryList);
		mListView.setAdapter(arrayAdapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				mPosition = position;
				dialog.show();
			}
		});

	}

	public void viewContact(Integer ePosition) {
		Intent mViewIntent = new Intent(getApplicationContext(),
				CreateMedicalHistory.class);
		Long eActivityId = Long.parseLong(mIdList.get(ePosition));
		mViewIntent.putExtra("id", eActivityId.toString());
		startActivity(mViewIntent);
	}

	public void editData(Integer ePosition) {
		Intent mEditIntent = new Intent(getApplicationContext(),
				CreateMedicalHistory.class);
		Long eActivityId = Long.parseLong(mIdList.get(ePosition));
		mEditIntent.putExtra("id", eActivityId.toString());
		startActivity(mEditIntent);
	}

	private void setListData() {
		MedicalHistoryDataSourc mPlaceDataSource = new MedicalHistoryDataSourc(
				this);
		List<MedicalHistory> galery_list = mPlaceDataSource
				.iCareMedicalProfilesList();
		for (int i = 0; i < galery_list.size(); i++) {
			MedicalHistory mPlaces = galery_list.get(i);
			mIdList.add(mPlaces.getId());

		}

	}

	public void deleteData(Integer ePosition) {
		mPlaceDataSource = new MedicalHistoryDataSourc(this);
		long eActivityId = Long.parseLong(mIdList.get(ePosition));
		mPlaceDataSource.deleteData(eActivityId);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.medicalhistorylistmanu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addMedicalmanu:
			startActivity(new Intent(MedicalHistoryListViewActivity.this,
					CreateMedicalHistory.class));
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
