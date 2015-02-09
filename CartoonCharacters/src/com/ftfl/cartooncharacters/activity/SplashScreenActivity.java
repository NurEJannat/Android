package com.ftfl.cartooncharacters.activity;



import com.ftfl.cartooncharacters.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;





public class SplashScreenActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_splash_screen);
	
	Thread time = new Thread(new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				Intent i = new Intent(SplashScreenActivity.this,
						MainScreenActivity.class);

				startActivity(i);
				finish();
			}

		}
	});
	time.start();
}

}
