package edu.fudan.cs.schapp.dao

import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository;

import edu.fudan.cs.schapp.model.Course
import edu.fudan.cs.schapp.model.Role
import edu.fudan.cs.schapp.model.User
import edu.fudan.cs.schapp.util.CachedDataProvider;
@Repository
class CoursesCacheDAO {

	@Autowired
	CachedDataProvider cdp

 
	public Course findCourseById(long id){
		cdp.courses.find{it.id==id}
	}
		
	public void createCourse(Course example){
		example.id=cdp.seq_courses++
		cdp.courses.add(example)
		
	}
	public void updateCourse(Course example){
		 
		Course item=findCourseById(example.id)
		item.name=example.name
		item.code=example.code
		item.location=example.location
		item.teacher=example.teacher
		item.credit=example.credit
		 
	}
	public void deleteCourse(long id){
		int x=cdp.courses.findIndexOf {it.id==id}
		if (x>=0)
			cdp.courses.remove(x)
	}
	
	public List<Course> findCourses(){
		cdp.courses
	}
}
