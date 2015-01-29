package com.example.privateuniversityinformation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;



public class SplashScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Thread time = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					Intent i = new Intent(SplashScreenActivity.this,
							UniversityHomeActivity.class);

					startActivity(i);
					finish();
				}

			}
		});
		time.start();
	}

}


