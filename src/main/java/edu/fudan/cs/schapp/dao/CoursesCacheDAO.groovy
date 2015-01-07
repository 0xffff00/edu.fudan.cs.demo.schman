package edu.fudan.cs.schapp.dao

import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Autowired

import edu.fudan.cs.schapp.model.Course
import edu.fudan.cs.schapp.model.Role
import edu.fudan.cs.schapp.model.User
import edu.fudan.cs.schapp.util.CachedDataProvider;

class CoursesCacheDAO {

	@Autowired
	CachedDataProvider cdp

 
	public List<Course> findCourses(){
		cdp.courses
	}
}
