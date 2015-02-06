package com.ftfl.personprofile.activity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ftfl.personprofile.R;
import com.ftfl.personprofile.database.PersonInfoDataSource;
import com.ftfl.personprofile.model.PersonInformation;



public class InsertPersonActivity extends Activity {

	
	private EditText mLatitudeEditText;
	private EditText mLongitudeEditText;
	
	private Button mButtonEditText;


	private int mId;

	private String mLatitude = "";
	private String mLongitude = "";
	
   /* Camera*/
	static final int REQUEST_IMAGE_CAPTURE = 1;
	ImageView mIvPhotoView = null;
	String mCurrentPhotoPath;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		initialize();
	}

	public void insertButtonClicked(View view) {
		getData();

		PersonInformation per_info_obj = new PersonInformation( mLatitude, mLongitude);
		per_info_obj.setImage(mCurrentPhotoPath);
		PersonInfoDataSource res_data_source_obj = new PersonInfoDataSource(
				this);
		res_data_source_obj.insertRestaurantInfo(per_info_obj);
		Intent intent = new Intent(InsertPersonActivity.this,
				PersonHomeActivity.class);
		startActivity(intent);

	}

	public void initialize() {
		
		//mLatitudeEditText = (EditText) findViewById(R.id.latitude_edittext);
		//mLongitudeEditText = (EditText) findViewById(R.id.longitude_edittext);
	
		mButtonEditText = (Button) findViewById(R.id.buttonSubmit);
		
		mIvPhotoView = (ImageView) findViewById(R.id.ivPhotoView);
		
	
	}

	public void getData() {

		
		mLatitude = mLatitudeEditText.getText().toString();
		mLongitude = mLongitudeEditText.getText().toString();
		
	}
  /*Capture Image*/
	
	
	public void dispatchTakePictureIntent(View v) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// Ensure that there's a camera activity to handle the intent
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			// Create the File where the photo should go
			File photoFile = null;
			try 
			{
				photoFile = createImageFile();
			} 
			catch (IOException ex) 
			{
				Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT)
						.show();
			}
			// Continue only if the File was successfully created
			if (photoFile != null) 
			{
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(photoFile));
				startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			}
		}
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) 
		{
			setPic();
			galleryAddPic();
		}
	}
	
	/**
	 * If user wants to load photo into gallery
	 */
	private void galleryAddPic() 
	{
	    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
	    File f = new File(mCurrentPhotoPath);
	    Uri contentUri = Uri.fromFile(f);
	    mediaScanIntent.setData(contentUri);
	    this.sendBroadcast(mediaScanIntent);
	}


	private void setPic() {
		// Get the dimensions of the View
		int targetW = mIvPhotoView.getWidth();
		int targetH = mIvPhotoView.getHeight();

		// Get the dimensions of the bitmap
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;

		// Determine how much to scale down the image
		int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

		// Decode the image file into a Bitmap sized to fill the View
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);// bmOptions
		mIvPhotoView.setImageBitmap(bitmap);
	}

	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(imageFileName, /* prefix */
				".jpg", /* suffix */
				storageDir /* directory */
		);

		// Save a file: path for use with ACTION_VIEW intents
		
		/**************************
		|---- You will get the image location from this variable which you will save into database
		*/
		mCurrentPhotoPath = image.getAbsolutePath();
		return image;
	}

	
}

