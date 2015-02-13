package com.ftfl.meetingplace.activity;


import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ftfl.meetingplace.R;
import com.ftfl.meetingplace.database.SQLiteDataSource;
import com.ftfl.meetingplace.model.MeetingPlaceInformation;



public class MettingDetaiInfoActivity extends Activity {
	String mID = "";
	Long mLId;
	SQLiteDataSource mImageDS = null;
	MeetingPlaceInformation mViewContact = null;
	EditText mEtName;
	EditText mEtMail;
	EditText mEtPhone;
	ImageButton mBtnCall;
	ImageButton mBtnEmail;
	ImageButton mBtnSMS;
	ImageButton mBtnAddContact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_detail);
		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("id");
		mLId = Long.parseLong(mID);
		mImageDS = new SQLiteDataSource(this);
		mViewContact = mImageDS.singlePlaceData(mLId);
		mEtName = (EditText) this.findViewById(R.id.etContact);
		mEtMail = (EditText) this.findViewById(R.id.etEmail);
		mEtPhone = (EditText) this.findViewById(R.id.etPhone);
		mBtnCall = (ImageButton) findViewById(R.id.ibCall);
		mBtnEmail = (ImageButton) findViewById(R.id.ibMail);
		mBtnSMS = (ImageButton) findViewById(R.id.ibChat);
		mBtnAddContact = (ImageButton) findViewById(R.id.ibAddContact);

		final String mName = mViewContact.getmContactName();
		final String mEmail = mViewContact.getmContactMail();
		final String mPhone = mViewContact.getmContactPhone();
		mEtName.setText(mName);
		mEtName.setClickable(false);
		mEtName.setFocusable(false);
		mEtMail.setText(mEmail);
		mEtMail.setClickable(false);
		mEtMail.setFocusable(false);
		mEtPhone.setText(mPhone);
		mEtPhone.setClickable(false);
		mEtPhone.setFocusable(false);
		mBtnCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ mPhone));
				startActivity(intent);

			}
		});
		mBtnEmail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri
						.fromParts("mailto", mEmail, null));
				// emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EXTRA_SUBJECT");
				startActivity(Intent
						.createChooser(emailIntent, "Send email..."));

			}
		});
		mBtnSMS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {

					Intent smsIntent = new Intent(Intent.ACTION_VIEW);
					smsIntent.putExtra("address", mPhone);
					smsIntent.setType("vnd.android-dir/mms-sms");
					startActivity(smsIntent);

				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "SMS faild!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});
		mBtnAddContact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
				int rawContactInsertIndex = ops.size();

				ops.add(ContentProviderOperation
						.newInsert(RawContacts.CONTENT_URI)
						.withValue(RawContacts.ACCOUNT_TYPE, null)
						.withValue(RawContacts.ACCOUNT_NAME, null).build());
				ops.add(ContentProviderOperation
						.newInsert(Data.CONTENT_URI)
						.withValueBackReference(Data.RAW_CONTACT_ID,
								rawContactInsertIndex)
						.withValue(Data.MIMETYPE,
								StructuredName.CONTENT_ITEM_TYPE)
						.withValue(StructuredName.DISPLAY_NAME, mName) // Name
																		// of
																		// the
																		// person
						.build());
				ops.add(ContentProviderOperation
						.newInsert(Data.CONTENT_URI)
						.withValueBackReference(
								ContactsContract.Data.RAW_CONTACT_ID,
								rawContactInsertIndex)
						.withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
						.withValue(Phone.NUMBER, mPhone) // Number of the person
						.withValue(Phone.TYPE, Phone.TYPE_MOBILE).build()); // Type
																			// of
																			// mobile
																			// number
				try {
					ContentProviderResult[] res = getContentResolver()
							.applyBatch(ContactsContract.AUTHORITY, ops);

					Toast.makeText(getApplicationContext(),
							"Successfully  Contract Added !!!!!!!",
							Toast.LENGTH_LONG).show();
				} catch (RemoteException e) {
					// error
				} catch (OperationApplicationException e) {
					// error
				}
			}
		});

	}

}
