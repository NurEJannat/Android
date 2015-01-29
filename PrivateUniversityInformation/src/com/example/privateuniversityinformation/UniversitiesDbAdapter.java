package com.example.privateuniversityinformation;

import java.io.File;
import java.io.FileOutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;

public class UniversitiesDbAdapter  {

	public static final String COLUMN_ID_FIELD = "_id";
	 public static final String COLUMN_NAME = "name";
	 public static final String COLUMN_ADDRESS = "address";
	 public static final String COLUMN_COURSES = "courses";
	 public static final String COLUMN_STUDENTNUM = "studentnum";
	 public static final String COLUMN_DESCRIPTION  = "description";
		public static final String COLUMN_LATITUDE  = "latitude";
		public static final String COLUMN_LONGITUDE  = "longitude";
		public static final String COLUMN_TEACHERNUM  = "teachernum";
	 
	 private static final String TAG = "UniversitiesDbAdapter";
	 private DatabaseHelper mDbHelper;
	 private SQLiteDatabase mDb;
	 
	 private static final String DATABASE_NAME = "PrivateUniversity";
	 private static final String SQLITE_TABLE = "University";
	 private static final int DATABASE_VERSION = 1;
	 
	 private final Context mCtx;
	 
	 private static final String DATABASE_CREATE =
	  "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
			  COLUMN_ID_FIELD + " integer PRIMARY KEY autoincrement," +
			  COLUMN_NAME + "," +
			  COLUMN_ADDRESS + "," +
			  COLUMN_COURSES + "," +
			  COLUMN_STUDENTNUM + "," +
			  COLUMN_DESCRIPTION + "," +
			  COLUMN_LATITUDE + "," +
			  COLUMN_LONGITUDE + "," +
			  COLUMN_TEACHERNUM + "," +
	  " UNIQUE (" + COLUMN_NAME +"));";
	
	
	 
	 private static class DatabaseHelper extends SQLiteOpenHelper {
		 
		  DatabaseHelper(Context context) {
		   super(context, DATABASE_NAME, null, DATABASE_VERSION);
		  }
		 
		 
		  @Override
		  public void onCreate(SQLiteDatabase db) {
		   Log.w(TAG, DATABASE_CREATE);
		   db.execSQL(DATABASE_CREATE);
		  }
		 
		  @Override
		  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		   Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
		     + newVersion + ", which will destroy all old data");
		   db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
		   onCreate(db);
		  }
		 }
		 
		 public UniversitiesDbAdapter(Context ctx) {
		  this.mCtx = ctx;
		 }
		 
		 public UniversitiesDbAdapter open() throws SQLException {
		  mDbHelper = new DatabaseHelper(mCtx);
		  mDb = mDbHelper.getWritableDatabase();
		  return this;
		 }
		 
		 public void close() {
		  if (mDbHelper != null) {
		   mDbHelper.close();
		  }
		 }
		 
		 public long createUniversity(String name, String address, 
		   String courses, String studentnum, String description,
			String latitude,
			String longitude ,
			String TeacherNumber ) {
		 
		  ContentValues initialValues = new ContentValues();
		  initialValues.put(COLUMN_NAME, name);
		  initialValues.put(COLUMN_ADDRESS, address);
		  initialValues.put(COLUMN_COURSES, courses);
		  initialValues.put(COLUMN_STUDENTNUM, studentnum);
		  initialValues.put(COLUMN_DESCRIPTION, description);
		  initialValues.put(COLUMN_LATITUDE, latitude);
		  initialValues.put(COLUMN_LONGITUDE, latitude);
		  initialValues.put(COLUMN_TEACHERNUM, TeacherNumber);
		 
		  return mDb.insert(SQLITE_TABLE, null, initialValues);
		 }
		 
		 public boolean deleteAllUniversities() {
		 
		  int doneDelete = 0;
		  doneDelete = mDb.delete(SQLITE_TABLE, null , null);
		  Log.w(TAG, Integer.toString(doneDelete));
		  return doneDelete > 0;
		 
		 }
		 
		 public Cursor fetchUniversitiesByName(String inputText) throws SQLException {
		  Log.w(TAG, inputText);
		  Cursor mCursor = null;
		  if (inputText == null  ||  inputText.length () == 0)  {
		   mCursor = mDb.query(SQLITE_TABLE, new String[] {COLUMN_ID_FIELD,
				   COLUMN_NAME,COLUMN_ADDRESS,COLUMN_COURSES, COLUMN_STUDENTNUM,COLUMN_DESCRIPTION,COLUMN_LATITUDE,COLUMN_LONGITUDE,COLUMN_TEACHERNUM}, 
		     null, null, null, null, null);
		 
		  }
		  else {
		   mCursor = mDb.query(true, SQLITE_TABLE, new String[] {COLUMN_ID_FIELD,
				   COLUMN_NAME,COLUMN_ADDRESS,COLUMN_COURSES, COLUMN_STUDENTNUM,COLUMN_DESCRIPTION,COLUMN_LATITUDE,COLUMN_LONGITUDE,COLUMN_TEACHERNUM}, 
				   COLUMN_NAME + " like '%" + inputText + "%'", null,
		     null, null, null, null);
		  }
		  if (mCursor != null) {
		   mCursor.moveToFirst();
		  }
		  return mCursor;
		 
		 }
		 
		 public Cursor fetchAllUniversities() {
		 
		  Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {COLUMN_ID_FIELD,
				  COLUMN_NAME, COLUMN_ADDRESS, COLUMN_COURSES,COLUMN_STUDENTNUM,COLUMN_DESCRIPTION,COLUMN_LATITUDE,COLUMN_LONGITUDE,COLUMN_TEACHERNUM}, 
		    null, null, null, null, null);
		 
		  if (mCursor != null) {
		   mCursor.moveToFirst();
		  }
		  return mCursor;
		 }
		 
		 public void insertSomeUniversities() {
		
			 createUniversity("Ahsanullah University Of Science  and Technology","Tejgaon","EEE,CSE,MPE,CE,BBA","2000","good","23.009","74.098","500");
	createUniversity("ASA University Bangladesh","Sonargaon","EEE,CSE,MPE,CE,BBA","2000","good","23.009","74.098","500");
	createUniversity("EAST-WEST University Bangladesh","Badda","EEE,CSE,MPE,CE,BBA","2000","good","23.009","74.098","500");
	createUniversity("WORLD University Bangladesh","Panthopoth","EEE,CSE,MPE,CE,BBA","2000","good","23.009","74.098","500");
	createUniversity("SOUTH-EAST University Bangladesh","Mohakhali","EEE,CSE,MPE,CE,BBA","2000","good","23.009","74.098","500");
	createUniversity("NORTH-SOUTH University Bangladesh","Bosundhora","EEE,CSE,MPE,CE,BBA","2000","good","23.009","74.098","500");
	createUniversity("UTTORA University Bangladesh","Uttora","EEE,CSE,MPE,CE,BBA","2000","good","23.009","74.098","500");
	createUniversity("BANGLADESH University ","Tejgaon","EEE,CSE,MPE,CE,BBA","2000","good","23.009","74.098","500");
	createUniversity("DAFFODIL University Bangladesh","Baily Road","EEE,CSE,MPE,CE,BBA","2000","good","23.009","74.098","500");
	createUniversity("UNITED University Bangladesh","Tejgaon","EEE,CSE,MPE,CE,BBA","2000","good","23.009","74.098","500");
		  
		 }

}
