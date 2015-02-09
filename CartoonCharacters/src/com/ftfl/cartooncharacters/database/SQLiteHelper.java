package com.ftfl.cartooncharacters.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class SQLiteHelper extends SQLiteOpenHelper {
	
	// Database Name
	public static final String DATABASE_NAME = "personprofile.db";
	private static final int DATABASE_VERSION = 1;
	
	// Table 
	public static final String TABLE_NAME = "profile";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_TIME = "time";
	public static final String COLUMN_LATITUDE = "latitude";
	public static final String COLUMN_LONGITUDE = "longitude";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_IMAGE = "image";
	
	// Database creation sql statement
	private static final String TABLE_CREATE_PROFILE = "create table "
	+ TABLE_NAME + "(" + COLUMN_ID
	+ " integer primary key autoincrement, " + COLUMN_DATE
	+ " text not null," + COLUMN_TIME + " text not null,"
	+ COLUMN_LATITUDE + " text not null,"
	+ COLUMN_LONGITUDE + " text not null," + COLUMN_DESCRIPTION
	+ " text not null ," + COLUMN_IMAGE
	+ " BLOB not null);";
	
	public SQLiteHelper(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
	database.execSQL(TABLE_CREATE_PROFILE);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	Log.w(SQLiteHelper.class.getName(), "Upgrading database from version "
	+ oldVersion + " to " + newVersion
	+ ", which will destroy all old data");
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	onCreate(db);
	}
	}

