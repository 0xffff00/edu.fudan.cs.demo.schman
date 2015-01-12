package edu.fudan.cs.schapp.model

class Course {

	Long id
	
	String name
	String code
	String location //上课地点
	User teacher
	Integer numStudents	//选课人数 
	Integer credit		//学分
	List<User> students
	
}
