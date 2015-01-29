package com.example.privateuniversityinformation;

public class University {
	Integer id = 0;
	
	String UniversityName = null;
	 String Adderss = null;
	 String Courses = null;
	 String StudentsNumber = null;
	 String description = null;
		double latitude = 0.0;
		double longitude = 0.0;
		String TeacherNumber = null;
	
		
		public University(String UniversityName, String Adderss, String Courses, String description,
				double latitude, double longitude, String StudentsNumber, String TeacherNumber) {
			
			this.UniversityName = UniversityName;
			this.Adderss = Adderss;
			this.Courses = Courses;
			this.description = description;
			this.latitude = latitude;
			this.longitude = longitude;
			this.StudentsNumber = StudentsNumber;
			this.TeacherNumber = TeacherNumber;
		}

		public University(Integer id, String UniversityName, String Adderss,String description,
				String Courses, double latitude, double longitude,
				String StudentsNumber, String TeacherNumber) {
			
			this.id = id;
			this.UniversityName = UniversityName;
			this.Adderss = Adderss;
			this.Courses = Courses;
			this.description = description;
			this.latitude = latitude;
			this.longitude = longitude;
			this.StudentsNumber = StudentsNumber;
			this.TeacherNumber = TeacherNumber;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
		public String getUniversityName() {
			return UniversityName;
		}
		public void setUniversityName(String universityName) {
			UniversityName = universityName;
		}
		public String getAdderss() {
			return Adderss;
		}
		public void setAdderss(String adderss) {
			Adderss = adderss;
		}
		public String getCourses() {
			return Courses;
		}
		public void setCourses(String courses) {
			Courses = courses;
		}
		public String getStudentsNumber() {
			return StudentsNumber;
		}
		public void setStudentsNumber(String studentsNumber) {
			StudentsNumber = studentsNumber;
		}
	
	public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public double getLatitude() {
			return latitude;
		}
		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}
		public double getLongitude() {
			return longitude;
		}
		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}
		public String getTeacherNumber() {
			return TeacherNumber;
		}
		public void setTeacherNumber(String teacherNumber) {
			TeacherNumber = teacherNumber;
		}
	
	  
	

}
