package com.ftfl.icareapplication.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract.Profile;
import android.view.Menu;
import android.view.MenuItem;

import com.ftfl.icareapplication.R;
import com.ftfl.icareapplication.database.ProfileDataSource;



public class SplashScreenActivity extends Activity {
	ProfileDataSource mDataSource = null;
	Profile mProfile = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		//Naseer Action bar
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00868B")));
		
		
		                 //Nipa code for actionbar
		//ActionBar actionBar = getActionBar();
		//actionBar.setDisplayShowHomeEnabled(false);
		//actionBar.setDisplayShowTitleEnabled(false);
		//actionBar.setDisplayUseLogoEnabled(false);
		
		//actionBar.setCustomView(R.layout.custom_action_bar1);
		
		//actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM    | ActionBar.DISPLAY_SHOW_HOME);
		
		mDataSource = new ProfileDataSource(this);

		Thread background = new Thread() {
			public void run() {

				try {
					// Thread will sleep for 3 seconds
					sleep(2 * 1000);

					// After 2 seconds redirect to another intent
					// Intent i=new
					// Intent(getBaseContext(),FTFLICareProfileActivity.class);

					// Bellow code will do the same thing....
					
					
					if (mDataSource.isEmpty()) {
						Intent i = new Intent(SplashScreenActivity.this,
								CreateProfileActivity.class);
						startActivity(i);
					} 
					else {
						Intent i = new Intent(SplashScreenActivity.this,
								ProfileListHomeActivity.class);
						startActivity(i);
					}

					// Remove activity
					finish(); // so that, it will not get back in the previous
								// file.

				} catch (Exception e) {

				}
			}
		};

		// start thread
		background.start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
