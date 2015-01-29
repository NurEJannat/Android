package com.example.privateuniversityinformation;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FilterQueryProvider;

public class ListViewCursorAdaptorActivity extends Activity {

	private UniversitiesDbAdapter dbHelper;
	 private SimpleCursorAdapter dataAdapter;
	 
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main);
	 
	  dbHelper = new UniversitiesDbAdapter(this);
	  dbHelper.open();
	 
	  //Clean all data
	  dbHelper.deleteAllUniversities();
	  //Add some data
	  dbHelper.insertSomeUniversities();
	 
	  //Generate ListView from SQLite Database
	  displayListView();
	 
	 }
	 
	 private void displayListView() {
	 
	 
	  Cursor cursor = dbHelper.fetchAllUniversities();
	 
	  // The desired columns to be bound
	  String[] columns = new String[] {
	    UniversitiesDbAdapter.COLUMN_NAME,
	    UniversitiesDbAdapter.COLUMN_ADDRESS,
	    UniversitiesDbAdapter.COLUMN_COURSES,
	    UniversitiesDbAdapter.COLUMN_STUDENTNUM,
	    UniversitiesDbAdapter.COLUMN_DESCRIPTION,
	    UniversitiesDbAdapter.COLUMN_LATITUDE,
	    UniversitiesDbAdapter.COLUMN_LONGITUDE,
	    UniversitiesDbAdapter.COLUMN_TEACHERNUM
	
	  };
	 
	  // the XML defined views which the data will be bound to
	  int[] to = new int[] { 
	    R.id.textView1,
	    R.id.textView2,
	    R.id.textView3,
	    R.id.textView4,
	    R.id.textView5,
	    R.id.textView6,
	    R.id.textView7,
	    R.id.textView8
	   
	    
	  };
	 
	  // create the adapter using the cursor pointing to the desired data 
	  //as well as the layout information
	  dataAdapter = new SimpleCursorAdapter(
	    this, R.layout.layout_main, 
	    cursor, 
	    columns, 
	    to,
	    0);
	 
	  ListView listView = (ListView) findViewById(R.id.listView1);
	  // Assign adapter to ListView
	  listView.setAdapter(dataAdapter);
	 
	 
	  listView.setOnItemClickListener(new OnItemClickListener() {
	   @Override
	   public void onItemClick(AdapterView<?> listView, View view, 
	     int position, long id) {
	   // Get the cursor, positioned to the corresponding row in the result set
	   Cursor cursor = (Cursor) listView.getItemAtPosition(position);
	 
	   // Get the state's capital from this row in the database.
	   String countryCode = 
	    cursor.getString(cursor.getColumnIndexOrThrow("name"));
	   Toast.makeText(getApplicationContext(),
	     countryCode, Toast.LENGTH_SHORT).show();
	 
	   }
	  });
	 
	  EditText myFilter = (EditText) findViewById(R.id.myFilter);
	  myFilter.addTextChangedListener(new TextWatcher() {
	 
	   public void afterTextChanged(Editable s) {
	   }
	 
	   public void beforeTextChanged(CharSequence s, int start, 
	     int count, int after) {
	   }
	 
	   public void onTextChanged(CharSequence s, int start, 
	     int before, int count) {
	    dataAdapter.getFilter().filter(s.toString());
	   }
	  });
	   
	  dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
	         public Cursor runQuery(CharSequence constraint) {
	             return dbHelper.fetchUniversitiesByName(constraint.toString());
	         }
	     });
	 
	  
	 }
}