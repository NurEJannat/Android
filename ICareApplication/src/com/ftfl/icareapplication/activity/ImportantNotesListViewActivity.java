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
import android.widget.ListView;

import com.ftfl.icareapplication.R;
import com.ftfl.icareapplication.database.ImportantNotesDataSource;
import com.ftfl.icareapplication.model.ImportentNotes;



public class ImportantNotesListViewActivity extends Activity{
	ImportantNotesDataSource mNotesDS = null;
	List<ImportentNotes> mNotesList = new ArrayList<ImportentNotes>();
	List<String> mTitelesList = new ArrayList<String>();
	List<String> mIdList = new ArrayList<String>();
	ListView mListView = null;
	Integer mPosition = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_importent_note_listview);
		
		 //Naseer Action bar
  		ActionBar bar = getActionBar();
  		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00868B")));
		
		final String[] option = new String[] { "View Detail","Edit Data", "Delete Data" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, option);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Select Option");
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.e("Selected Item", String.valueOf(which));
				if (which == 0) {
					viewData(mPosition);
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

		
		setListData();
		mListView = (ListView) findViewById(R.id.lv_Notesistview);
		
		ArrayAdapter<String> mProfileAdapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, mTitelesList);
		
		mListView.setAdapter(mProfileAdapter);
		
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mPosition = position;
				dialog.show();
				//startActivity(new Intent(ProfileListHomeActivity.this, DiseViewHomeActivity.class));
			}
		});
	}
	
	private void setListData() {
		ImportantNotesDataSource profiledata = new ImportantNotesDataSource(this);
		mNotesList = profiledata.iCareImportantNotesList();
		for (int i = 0; i < mNotesList.size(); i++) {
			ImportentNotes mProfile = mNotesList.get(i);
			mIdList.add(mProfile.getId());
			mTitelesList.add(mProfile.getNotesTitel());
		}
		
	}
	public void viewData(Integer ePosition)
	{
		Intent mEditIntent = new Intent(getApplicationContext(),
				importantNotesDetailShowActivity.class);
		Long id = Long.parseLong(mIdList.get(ePosition));
		mEditIntent.putExtra("id", id.toString());
		startActivity(mEditIntent);
	}
	public void editData(Integer ePosition)
	{
		Intent mEditIntent = new Intent(getApplicationContext(),
				CreateImportentNotes.class);
		Long id = Long.parseLong(mIdList.get(ePosition));
		mEditIntent.putExtra("id", id.toString());
		startActivity(mEditIntent);
		
	}
	

	public void deleteData(Integer ePosition)
	{
		mNotesDS = new ImportantNotesDataSource(this);
		
		Long mId = Long.parseLong(mIdList.get(ePosition));
		mNotesDS.deleteData(mId);

	 } 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_important_notes, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
        case R.id.addImportantNotesemanu:
        	startActivity(new Intent(ImportantNotesListViewActivity.this, CreateImportentNotes.class));
            return true;
        

        
        default:
            return super.onOptionsItemSelected(item);
    }
	}


}
