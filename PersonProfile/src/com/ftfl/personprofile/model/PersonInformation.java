package com.ftfl.personprofile.model;

public class PersonInformation {

	
	private int mId;

	private String mLatitude = "";
	private String mLongitude = "";
	
	private String mImagePath = "";
	
	public PersonInformation(
			String eLatitude, String eLongitude) {
		super();
		
		this.mLatitude = eLatitude;
		this.mLongitude = eLongitude;
		
	}

	public int getId() {
		return mId;
	}

	public void setId(int eId) {
		this.mId = eId;
	}

	

	

	public String getLatitude() {
		return mLatitude;
	}

	public String getLongitude() {
		return mLongitude;
	}

	public void setImage(String eImagePath) {
		this.mImagePath=eImagePath;
	}

	public String getImage() {
		return mImagePath;
	}

}
