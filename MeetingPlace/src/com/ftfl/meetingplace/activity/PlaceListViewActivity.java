package com.ftfl.meetingplace.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ftfl.meetingplace.R;
import com.ftfl.meetingplace.adapter.Customadapter;
import com.ftfl.meetingplace.database.SQLiteDataSource;
import com.ftfl.meetingplace.database.SQLiteHelper;
import com.ftfl.meetingplace.model.MeetingPlaceInformation;
import com.ftfl.meetingplace.util.GPSTracker;


public class PlaceListViewActivity extends Activity {
	List<String> mIdList = new ArrayList<String>();

	SQLiteHelper mDBHelper;
	SQLiteDataSource mPlaceDataSource;
	ListView mListView;
	TextView mId_tv = null;
	TextView mLatitude_tv = null;
	TextView mLongitude_tv = null;
	String mId = "";
	String mLatitude = "";
	String mLongitude = "";
	GPSTracker gps;
	TextView mCurrentLocation;
	Button mBackButton;
	Integer mPosition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_list_view);
		mCurrentLocation = (TextView) findViewById(R.id.currentlocation);
		gps = new GPSTracker(PlaceListViewActivity.this);
		// check if GPS enabled
		if (gps.canGetLocation()) {
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			// \n is for new line
			mCurrentLocation.setText("Current Location "
					+ String.valueOf(latitude) + " , "
					+ String.valueOf(longitude));

		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}
		mDBHelper = new SQLiteHelper(this);
		mPlaceDataSource = new SQLiteDataSource(this);
		setListData();
		List<MeetingPlaceInformation> galery_list = mPlaceDataSource.allPlaces();
		Customadapter arrayAdapter = new Customadapter(this, galery_list);
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
					viewContact(mPosition);
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

		mListView = (ListView) findViewById(R.id.lvImageList);
		mListView.setAdapter(arrayAdapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				mPosition = position;
				dialog.show();
			}
		});

		mBackButton = (Button) findViewById(R.id.backBtn);
		mBackButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						MeetingPlaceActivity.class);
				startActivity(i);
			}
		});
	}

	public void viewContact(Integer ePosition) {
		Intent mViewIntent = new Intent(getApplicationContext(),
				MettingDetaiInfoActivity.class);
		Long eActivityId = Long.parseLong(mIdList.get(ePosition));
		mViewIntent.putExtra("id", eActivityId.toString());
		startActivity(mViewIntent);
	}

	public void editData(Integer ePosition) {
		Intent mEditIntent = new Intent(getApplicationContext(),
				UpdatePlaceInfoActivity.class);
		Long eActivityId = Long.parseLong(mIdList.get(ePosition));
		mEditIntent.putExtra("id", eActivityId.toString());
		startActivity(mEditIntent);
	}

	private void setListData() {
		SQLiteDataSource mPlaceDataSource = new SQLiteDataSource(this);
		List<MeetingPlaceInformation> galery_list = mPlaceDataSource.allPlaces();
		for (int i = 0; i < galery_list.size(); i++) {
			MeetingPlaceInformation mPlaces = galery_list.get(i);
			mIdList.add(mPlaces.getmId());

		}

	}

	public void deleteData(Integer ePosition) {
		mPlaceDataSource = new SQLiteDataSource(this);
		long eActivityId = Long.parseLong(mIdList.get(ePosition));
		mPlaceDataSource.deleteData(eActivityId);
		Intent i = new Intent(getApplicationContext(), PlaceListViewActivity.class);
		startActivity(i);
	}

}

