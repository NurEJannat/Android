package com.ftfl.privateuniversities.database;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_NAME = "universities";
	public static final String COLUMNL_ID_FIELD = "_id";
	public static final String COLUMNL_NAME_FIELD = "name";
	public static final String COLUMNL_ADDRESS_FIELD = "address";
	public static final String COLUMNL_DESCRIPTION_FIELD = "description";
	public static final String COLUMNL_LATITUDE_FIELD = "latitude";
	public static final String COLUMNL_LONGITUDE_FIELD = "longitude";
	public static final String COLUMNL_COURSES_FIELD = "courses";
	public static final String COLUMNL_TEACHER_FIELD = "teacher";

	private static final String DATABASE_NAME = "PrivateUniversity.db";
	
	   public SQLiteHelper(Context context)
	   {
	      super(context, DATABASE_NAME , null, 1);
	   }

	   @Override
	   public void onCreate(SQLiteDatabase db) {
	      // TODO Auto-generated method stub
	      db.execSQL(
	      "create table universities " +
	      "(id integer primary key, name text,address text,description text, latitude text,longitude text, courses text,teacher text)"
	      );
	   }

	   @Override
	   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	      // TODO Auto-generated method stub
	      db.execSQL("DROP TABLE IF EXISTS universities");
	      onCreate(db);
	      
	      
	   }

	   public boolean insertContact  (String name, String address, String description, String latitude,String longitude, String courses,String teacher)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();

	      contentValues.put("name", name);
	      contentValues.put("address", address);
	      contentValues.put("description", description);	
	      contentValues.put("latitude", latitude);
	      contentValues.put("longitude", longitude);
	      contentValues.put("courses", courses);
	      contentValues.put("teacher", teacher);
	 

	      db.insert("universities", null, contentValues);
	      return true;
	   }
	   public Cursor getData(int id){
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from universities where id="+id+"", null );
	      return res;
	   }
	   public int numberOfRows(){
	      SQLiteDatabase db = this.getReadableDatabase();
	      int numRows = (int) DatabaseUtils.queryNumEntries(db, COLUMNL_NAME_FIELD);
	      return numRows;
	   }
	   public boolean updateContact (Integer id, String name, String address, String description, String latitude,String longitude, String courses,String teacher)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();
	      contentValues.put("name", name);
	      contentValues.put("address", address);
	      contentValues.put("description", description);	
	      contentValues.put("latitude", latitude);
	      contentValues.put("longitude", longitude);
	      contentValues.put("courses", courses);
	      contentValues.put("teacher", teacher);

	      db.update("universities", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
	      return true;
	   }

	   public Integer deleteContact (Integer id)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      return db.delete("universities", 
	      "id = ? ", 
	      new String[] { Integer.toString(id) });
	   }
	   public ArrayList getAllCotacts()
	   {
	      ArrayList array_list = new ArrayList();
	      //hp = new HashMap();
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from universities", null );
	      res.moveToFirst();
	      while(res.isAfterLast() == false){
	      array_list.add(res.getString(res.getColumnIndex(COLUMNL_NAME_FIELD)));
	      res.moveToNext();
	      }
	   return array_list;
	   }
	}




