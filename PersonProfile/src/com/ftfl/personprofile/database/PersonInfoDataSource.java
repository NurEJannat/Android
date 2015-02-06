package com.ftfl.personprofile.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ftfl.personprofile.model.PersonInformation;

public class PersonInfoDataSource {

	private DatabaseHelperClass dbHelper;

	public PersonInfoDataSource(Context context) {
		dbHelper = new DatabaseHelperClass(context);
	}

	// Create diet
	public long insertRestaurantInfo(PersonInformation resInfoObj) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		
		contentValues.put(DatabaseHelperClass.PROFILE_COLUMN_LATITUDE,
				resInfoObj.getLatitude());
		contentValues.put(DatabaseHelperClass.PROFILE_COLUMN_LONGITUDE,
				resInfoObj.getLongitude());
		
		contentValues.put(DatabaseHelperClass.PROFILE_COLUMN_IMAGE_PATH,
				resInfoObj.getImage());

		return db.insert(dbHelper.PROFILE_TABLE_NAME, null, contentValues);

	}
	
	public ArrayList<PersonInformation> getPersonList() {
			ArrayList<PersonInformation> res_list  = new ArrayList<PersonInformation>();
			
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			Cursor result = db.rawQuery("select * from person ", null);
			if (result.moveToFirst()) {
			do {
			int id = result.getInt(0);
			
			String latitude = result.getString(3);
			String longitude = result.getString(4);
			
			String image_path= result.getString(10);
			
			PersonInformation perInfo = new PersonInformation( latitude, longitude);
			perInfo.setId(id);
			perInfo.setImage(image_path);
			res_list.add(perInfo);
			} while (result.moveToNext());
			}
			return res_list;
			}
	
	public PersonInformation getPersonById(int eId) {
		PersonInformation personObj = null;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from person where id='" + eId + "'",
		null);
		if (result.moveToFirst()) {
		do {
			int id = result.getInt(0);
		
			String latitude = result.getString(3);
			String longitude = result.getString(4);
			
			String image_path= result.getString(10);
			
			personObj = new PersonInformation( latitude, longitude);
			personObj.setId(id);
			personObj.setImage(image_path);
			
		} while (result.moveToNext());
		}
		return personObj;
		}
	
	public long editPerson(Integer id, PersonInformation perInfoObj) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		
		contentValues.put(DatabaseHelperClass.PROFILE_COLUMN_LATITUDE,
				perInfoObj.getLatitude());
		contentValues.put(DatabaseHelperClass.PROFILE_COLUMN_LONGITUDE,
				perInfoObj.getLongitude());
		
		return db.update("person", contentValues, "id = ? ",
		new String[] { Integer.toString(id) });
		// return true;
		}
	 public void deletePerson (Integer id)
	   {
	      SQLiteDatabase db = dbHelper.getWritableDatabase();
	      db.delete("person", 
	      "id = ? ", 
	      new String[] { Integer.toString(id) });
	   }
}
