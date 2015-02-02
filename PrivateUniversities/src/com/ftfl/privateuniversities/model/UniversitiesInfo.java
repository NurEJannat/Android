package com.ftfl.privateuniversities.model;

public class UniversitiesInfo {

	
	Integer mId = 0;
	String mName = null;
	String mAddress = null;
	String mDescription = null;
	double mLatitude = 0.0;
	double mLongitude = 0.0;
	String mCourses = null;
	String mTeacher = null;
	
	public UniversitiesInfo(Integer eId, String eName, String eAddress,
			String eDescription, double eLatitude, double eLongitude,
			String eCourses, String eTeacher) {
		super();
		
		
		this.mId = eId;
		this.mName = eName;
		this.mAddress = eAddress;
		this.mDescription = eDescription;
		this.mLatitude = eLatitude;
		this.mLongitude = eLongitude;
		this.mCourses = eCourses;
		this.mTeacher = eTeacher;
	}

	public Integer getId() {
		return mId;
	}

	public void setId(Integer eId) {
		this.mId = eId;
	}

	public String getName() {
		return mName;
	}

	

	public String getAddress() {
		return mAddress;
	}

	

	public String getDescription() {
		return mDescription;
	}

	

	public double getLatitude() {
		return mLatitude;
	}

	

	public double getLongitude() {
		return mLongitude;
	}

	

	public String getCourses() {
		return mCourses;
	}

	

	public String getTeacher() {
		return mTeacher;
	}

	
}
