package com.example.privateuniversityinformation;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UniversityHomeActivity extends Activity {

	
	Button aust;
	Button nsu;
	Button asau;
	Button bu;
	Button ewu;
	Button wu;
	Button seu;
	Button uub;
	Button dfu;
	Button uiu;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.universities_main);
		
		aust = (Button) findViewById(R.id.button1);
		nsu = (Button) findViewById(R.id.button5);
		asau = (Button) findViewById(R.id.button2);
		dfu = (Button) findViewById(R.id.button9);
		uiu = (Button) findViewById(R.id.button10);
		bu = (Button) findViewById(R.id.button3);
		ewu = (Button) findViewById(R.id.button4);
		wu = (Button) findViewById(R.id.button6);
		seu = (Button) findViewById(R.id.button7);
		uub = (Button) findViewById(R.id.button8);
		
		
		aust.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					  Intent in = new Intent( UniversityHomeActivity.this, ListViewCursorAdaptorActivity.class);

					  startActivity(in);
				}
			});
		 
		nsu.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					  Intent in = new Intent( UniversityHomeActivity.this, ListViewCursorAdaptorActivity.class);

					  startActivity(in);
				}
		 
		
	});
		
		asau.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				  Intent in = new Intent( UniversityHomeActivity.this, ListViewCursorAdaptorActivity.class);

				  startActivity(in);
			}
	 
	
});
		dfu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				  Intent in = new Intent( UniversityHomeActivity.this, ListViewCursorAdaptorActivity.class);

				  startActivity(in);
			}
	 
	
});
		uiu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				  Intent in = new Intent( UniversityHomeActivity.this, ListViewCursorAdaptorActivity.class);

				  startActivity(in);
			}
	 
	
});
		bu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				  Intent in = new Intent( UniversityHomeActivity.this, ListViewCursorAdaptorActivity.class);

				  startActivity(in);
			}
	 
	
});
		ewu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				  Intent in = new Intent( UniversityHomeActivity.this, ListViewCursorAdaptorActivity.class);

				  startActivity(in);
			}
	 
	
});

		wu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				  Intent in = new Intent( UniversityHomeActivity.this, ListViewCursorAdaptorActivity.class);

				  startActivity(in);
			}
	 
	
});

		seu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				  Intent in = new Intent( UniversityHomeActivity.this, ListViewCursorAdaptorActivity.class);

				  startActivity(in);
			}
	 
	
});

		uub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				  Intent in = new Intent( UniversityHomeActivity.this, ListViewCursorAdaptorActivity.class);

				  startActivity(in);
			}
	 
	
});

	}
		}


	