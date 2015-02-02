package com.ftfl.privateuniversities.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ftfl.privateuniversities.R;
import com.ftfl.privateuniversities.constants.UniversityConstant;
import com.ftfl.privateuniversities.database.SQLiteHelper;
import com.ftfl.privateuniversities.model.UniversitiesInfo;



public class DisplayUniversityActivity extends Activity{

	private SQLiteHelper mydb ;
	TextView mName ;
	TextView mAddress;
	TextView mDescription;
	TextView mLatitude;
	TextView mLongitude;
	TextView mCourses;
	TextView mTeacher;


	int id_To_Update = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.activity_display_university);
	   
	   mName = (TextView) findViewById(R.id.etName);
	   mAddress = (TextView) findViewById(R.id.etAddress);
	   mDescription = (TextView) findViewById(R.id.etDesc);
	   mLatitude = (TextView) findViewById(R.id.etLat);
	   mLongitude = (TextView) findViewById(R.id.etLongt);
	   mCourses = (TextView) findViewById(R.id.etCourses);
	   mTeacher = (TextView) findViewById(R.id.etTeacher);
	  


	   mydb = new SQLiteHelper(this);

	   Bundle extras = getIntent().getExtras(); 
	   if(extras !=null)
	   {
	      int Value = extras.getInt("id");
	      if(Value>0){
	         //means this is the view part not the add contact part.
	         Cursor rs = mydb.getData(Value);
	         id_To_Update = Value;
	         rs.moveToFirst();
	         String nam = rs.getString(rs.getColumnIndex(SQLiteHelper.COLUMNL_NAME_FIELD));
	         String add = rs.getString(rs.getColumnIndex(SQLiteHelper.COLUMNL_ADDRESS_FIELD));
	         String des = rs.getString(rs.getColumnIndex(SQLiteHelper.COLUMNL_DESCRIPTION_FIELD));
	         String lati = rs.getString(rs.getColumnIndex(SQLiteHelper.COLUMNL_LATITUDE_FIELD));
	         String lon = rs.getString(rs.getColumnIndex(SQLiteHelper.COLUMNL_LONGITUDE_FIELD));
	         String cou = rs.getString(rs.getColumnIndex(SQLiteHelper.COLUMNL_COURSES_FIELD));
	         String tec = rs.getString(rs.getColumnIndex(SQLiteHelper.COLUMNL_TEACHER_FIELD));
	        
	         if (!rs.isClosed()) 
	         {
	            rs.close();
	         }
	         Button b = (Button)findViewById(R.id.button1);
	         b.setVisibility(View.INVISIBLE);

	         mName.setText((CharSequence)nam);
	         mName.setFocusable(false);
	         mName.setClickable(false);

	         mAddress.setText((CharSequence)add);
	         mAddress.setFocusable(false); 
	         mAddress.setClickable(false);

	         mDescription.setText((CharSequence)des);
	         mDescription.setFocusable(false);
	         mDescription.setClickable(false);

	         mLatitude.setText((CharSequence)lati);
	         mLatitude.setFocusable(false); 
	         mLatitude.setClickable(false);

	         mLongitude.setText((CharSequence)lon);
	         mLongitude.setFocusable(false);
	         mLongitude.setClickable(false);
	         
	         mCourses.setText((CharSequence)cou);
	         mCourses.setFocusable(false); 
	         mCourses.setClickable(false);

	         mTeacher.setText((CharSequence)tec);
	         mTeacher.setFocusable(false);
	         mTeacher.setClickable(false);
	        
	        
	        }
	   }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	   // Inflate the menu; this adds items to the action bar if it is present.
	   Bundle extras = getIntent().getExtras(); 
	   if(extras !=null)
	   {
	      int Value = extras.getInt("id");
	      if(Value>0){
	         getMenuInflater().inflate(R.menu.display_university, menu);
	      }
	      else{
	         getMenuInflater().inflate(R.menu.main, menu);
	      }
	   }
	   return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) 
	{ 
	   super.onOptionsItemSelected(item); 
	   switch(item.getItemId()) 
	{ 
	   case R.id.Edit: 
	   Button b = (Button)findViewById(R.id.button1);
	   b.setVisibility(View.VISIBLE);
	  
	   mName.setEnabled(true);
	   mName.setFocusableInTouchMode(true);
	   mName.setClickable(true);

	   mAddress.setEnabled(true);
	   mAddress.setFocusableInTouchMode(true);
	   mAddress.setClickable(true);

	   mDescription.setEnabled(true);
	   mDescription.setFocusableInTouchMode(true);
	   mDescription.setClickable(true);

	   mLatitude.setEnabled(true);
	   mLatitude.setFocusableInTouchMode(true);
	   mLatitude.setClickable(true);

	   mLongitude.setEnabled(true);
	   mLongitude.setFocusableInTouchMode(true);
	   mLongitude.setClickable(true);
	   
	   mCourses.setEnabled(true);
	   mCourses.setFocusableInTouchMode(true);
	   mCourses.setClickable(true);

	   mTeacher.setEnabled(true);
	   mTeacher.setFocusableInTouchMode(true);
	   mTeacher.setClickable(true);
	   
	 

	   return true; 
	   case R.id.Delete:

	   AlertDialog.Builder builder = new AlertDialog.Builder(this);
	   builder.setMessage(R.string.deleteName)
	  .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
	      public void onClick(DialogInterface dialog, int id) {
	         mydb.deleteContact(id_To_Update);
	         Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();  
	         Intent intent = new Intent(getApplicationContext(),com.ftfl.privateuniversities.activity.UniversitiesActivity.class);
	         startActivity(intent);
	      }
	   })
	  .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
	      public void onClick(DialogInterface dialog, int id) {
	         // User cancelled the dialog
	      }
	   });
	   AlertDialog d = builder.create();
	   d.setTitle("Are you sure");
	   d.show();

	   return true;
	   default: 
	   return super.onOptionsItemSelected(item); 

	   } 
	} 

	public void run(View view)
	{	
	   Bundle extras = getIntent().getExtras();
	   if(extras !=null)
	   {
	      int Value = extras.getInt("id");
	      if(Value>0){
	         if(mydb.updateContact(id_To_Update,mName.getText().toString(), mAddress.getText().toString(), mDescription.getText().toString(), mLatitude.getText().toString(), mLongitude.getText().toString(), mCourses.getText().toString(), mTeacher.getText().toString())){
	            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();	
	            Intent intent = new Intent(getApplicationContext(),com.ftfl.privateuniversities.activity.UniversitiesActivity.class);
	            startActivity(intent);
	          }		
	         else{
	            Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();	
	         }
			 }
	      else{
	         if(mydb.insertContact(mName.getText().toString(), mAddress.getText().toString(), mDescription.getText().toString(), mLatitude.getText().toString(), mLongitude.getText().toString(), mCourses.getText().toString(), mTeacher.getText().toString())){
	            Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();	
	         }		
	         else{
	            Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();	
	         }
	         Intent intent = new Intent(getApplicationContext(),com.ftfl.privateuniversities.activity.UniversitiesActivity.class);
	         startActivity(intent);
	         }
	   }
	}
	
	public void viewMap(View view) {
		Intent intent = new Intent(DisplayUniversityActivity.this,
				PhotoPickerActivity.class);
		Bundle bundle = new Bundle();

		startActivity(intent);

	
	}


}
