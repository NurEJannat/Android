package com.ftfl.cartooncharacters.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ftfl.cartooncharacters.model.PersonProfile;



public class PersonDataSource {
	
	// Database fields
			private SQLiteDatabase mProfileDatabase;
			private SQLiteHelper mProfileDbHelper;
			PersonProfile mProfile;
			List<PersonProfile> mPerson = new ArrayList<PersonProfile>();
			public PersonDataSource(Context context) {
			mProfileDbHelper = new SQLiteHelper(context);
			}
			/*
			* open a method for writable database
			*/
			public void open() throws SQLException {
			mProfileDatabase = mProfileDbHelper.getWritableDatabase();
			}
			/*
			* close database connection
			*/
			public void close() {
			mProfileDbHelper.close();
			}
			/*
			* insert data into the database.
			*/
			public boolean insert(PersonProfile person) {
			this.open();
			ContentValues cv = new ContentValues();
			cv.put(SQLiteHelper.COLUMN_DATE, person.getPlaceDate());
			cv.put(SQLiteHelper.COLUMN_TIME, person.getPlaceTime());
			cv.put(SQLiteHelper.COLUMN_LATITUDE, person.getPlaceLatitude());
			cv.put(SQLiteHelper.COLUMN_LONGITUDE, person.getPlaceLongitude());
			cv.put(SQLiteHelper.COLUMN_DESCRIPTION, person.getPlaceDescription());
			cv.put(SQLiteHelper.COLUMN_IMAGE, person.getPlaceImage());
			
			Long check = mProfileDatabase.insert(SQLiteHelper.TABLE_NAME, null, cv);
			mProfileDatabase.close();
			this.close();
			if (check < 0)
			return false;
			else
			return true;
			}
			// Updating database by id
			public boolean updateData(long eActivityId, PersonProfile person) {
			this.open();
			ContentValues cvUpdate = new ContentValues();
			cvUpdate.put(SQLiteHelper.COLUMN_DATE, person.getPlaceDate());
			cvUpdate.put(SQLiteHelper.COLUMN_TIME, person.getPlaceTime());
			cvUpdate.put(SQLiteHelper.COLUMN_LATITUDE, person.getPlaceLatitude());
			cvUpdate.put(SQLiteHelper.COLUMN_LONGITUDE,
			person.getPlaceLongitude());
			cvUpdate.put(SQLiteHelper.COLUMN_DESCRIPTION,
			person.getPlaceDescription());
			cvUpdate.put(SQLiteHelper.COLUMN_IMAGE, person.getPlaceImage());
			int check = mProfileDatabase.update(SQLiteHelper.TABLE_NAME, cvUpdate,
			SQLiteHelper.COLUMN_ID + "=" + eActivityId, null);
			mProfileDatabase.close();
			this.close();
			if (check == 0)
			return false;
			else
			return true;
			}
			// delete data form database.
			public boolean deleteData(long eActivityId) {
			this.open();
			try {
			mProfileDatabase.delete(SQLiteHelper.TABLE_NAME,
			SQLiteHelper.COLUMN_ID + "=" + eActivityId, null);
			} catch (Exception ex) {
			Log.e("ERROR", "data insertion problem");
			return false;
			}
			this.close();
			return true;
			}
			/*
			* using cursor for display data from database.
			*/
			public PersonProfile singleHospital(String id) {
			this.open();
			Cursor mCursor = mProfileDatabase.query(SQLiteHelper.TABLE_NAME,
			new String[] { SQLiteHelper.COLUMN_ID,
			SQLiteHelper.COLUMN_DATE,
			SQLiteHelper.COLUMN_TIME,
			SQLiteHelper.COLUMN_LATITUDE,
			SQLiteHelper.COLUMN_LONGITUDE,
			SQLiteHelper.COLUMN_DESCRIPTION,
			SQLiteHelper.COLUMN_IMAGE,
			}, SQLiteHelper.COLUMN_ID + "= '" + id + "' ", null, null,
			null, null);
			if (mCursor != null) {
			if (mCursor.moveToFirst()) {
			do {
			String mActivityId = mCursor.getString(mCursor.getColumnIndex("id"));
			String mActivityDate = mCursor.getString(mCursor.getColumnIndex("date"));
			String mActivityTime = mCursor.getString(mCursor.getColumnIndex("time"));
			String mActivityLatitude = mCursor.getString(mCursor.getColumnIndex("latitude"));
			String mActivityLongitude = mCursor.getString(mCursor.getColumnIndex("longitude"));
			String mActivityDescription = mCursor.getString(mCursor.getColumnIndex("description"));
			byte[] mImage = mCursor.getBlob(mCursor.getColumnIndex("image"));
			
			mProfile = new PersonProfile(mActivityId,
			mActivityDate, mActivityTime, mActivityLatitude,
			mActivityLongitude, mActivityDescription, mImage);
			} while (mCursor.moveToNext());
			}
			mCursor.close();
			}
			this.close();
			return mProfile;
			}
			/*
			* create a Diet chart of ICareProfile. Here the data of the database
			* according to the given id is set to the Diet chart and return a Diet
			* chart object.
			*/
			public PersonProfile updateActivityData(long eActivityId) {
			this.open();
			PersonProfile UpdateActivity;
			Cursor mUpdateCursor = mProfileDatabase.query(SQLiteHelper.TABLE_NAME,
			new String[] { SQLiteHelper.COLUMN_ID,
			SQLiteHelper.COLUMN_DATE,
			SQLiteHelper.COLUMN_TIME,
			SQLiteHelper.COLUMN_LATITUDE,
			SQLiteHelper.COLUMN_LONGITUDE,
			SQLiteHelper.COLUMN_DESCRIPTION,
			SQLiteHelper.COLUMN_IMAGE,
			}, SQLiteHelper.COLUMN_ID + "= '" + eActivityId + "' ",
			null, null, null, null);
			mUpdateCursor.moveToFirst();
			String mPlaceId = mUpdateCursor.getString(mUpdateCursor.getColumnIndex("id"));
			String mPlaceDate = mUpdateCursor.getString(mUpdateCursor.getColumnIndex("date"));
			String mPlaceTime = mUpdateCursor.getString(mUpdateCursor.getColumnIndex("time"));
			String mActivityLatitude = mUpdateCursor.getString(mUpdateCursor.getColumnIndex("latitude"));
			String mActivityLongitude = mUpdateCursor.getString(mUpdateCursor.getColumnIndex("longitude"));
			String mActivityDescription = mUpdateCursor.getString(mUpdateCursor.getColumnIndex("description"));
			byte[] mImage = mUpdateCursor.getBlob(mUpdateCursor.getColumnIndex("image"));
			mUpdateCursor.close();
			
			UpdateActivity = new PersonProfile(mPlaceId, mPlaceDate,
			mPlaceTime, mActivityLatitude, mActivityLongitude,
			mActivityDescription, mImage);
			this.close();
			return UpdateActivity;
			}
			/*
			* using cursor for display data from database.
			*/
			public List<PersonProfile> allPerson() {
			this.open();
			Cursor mCursor = mProfileDatabase.query(SQLiteHelper.TABLE_NAME,
			new String[] { SQLiteHelper.COLUMN_ID,
			SQLiteHelper.COLUMN_DATE,
			SQLiteHelper.COLUMN_TIME,
			SQLiteHelper.COLUMN_LATITUDE,
			SQLiteHelper.COLUMN_LONGITUDE,
			SQLiteHelper.COLUMN_DESCRIPTION,
			SQLiteHelper.COLUMN_IMAGE,
			}, null, null, null, null, null);
			if (mCursor != null) {
			if (mCursor.moveToFirst()) {
			do {
			String mPlaceId = mCursor.getString(mCursor.getColumnIndex("id"));
			String mPlaceDate = mCursor.getString(mCursor.getColumnIndex("date"));
			String mPlaceTime = mCursor.getString(mCursor.getColumnIndex("time"));
			String mPlaceLatitude = mCursor.getString(mCursor.getColumnIndex("latitude"));
			String mPlaceLongitude = mCursor.getString(mCursor.getColumnIndex("longitude"));
			String mPlaceDescription = mCursor.getString(mCursor.getColumnIndex("description"));
			byte[] mImage = mCursor.getBlob(mCursor.getColumnIndex("image"));
			
			mPerson.add(new PersonProfile(mPlaceId, mPlaceDate,mPlaceTime, mPlaceLatitude,
			mPlaceLongitude, mPlaceDescription, mImage));
			} while (mCursor.moveToNext());
			}
			mCursor.close();
			}
			this.close();
			return mPerson;
			}

}
