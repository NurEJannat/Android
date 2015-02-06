package com.ftfl.personprofile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperClass extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "personprofile.db";
	public static final String PROFILE_TABLE_NAME = "person";
	public static final String PROFILE_COLUMN_ID = "id";
	

	public static final String PROFILE_COLUMN_LATITUDE = "latitude";
	public static final String PROFILE_COLUMN_LONGITUDE = "longitude";
	
	public static final String PROFILE_COLUMN_IMAGE_PATH = "imagepath";

	public DatabaseHelperClass(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE restaurant"
				+ "(id INTEGER PRIMARY KEY,  latitude TEXT,longitude TEXT,imagepath TEXT)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS restaurant");
		onCreate(db);
	}

}
