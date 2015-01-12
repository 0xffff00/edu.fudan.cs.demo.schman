package edu.fudan.cs.schapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import edu.fudan.cs.schapp.dao.CoursesCacheDAO
import edu.fudan.cs.schapp.dao.UsersRolesCacheDAO
import edu.fudan.cs.schapp.model.Course
import edu.fudan.cs.schapp.model.Role
import edu.fudan.cs.schapp.model.User


/**
 * 
 * @author Huang Zhenkun (14210240077@fudan.edu.cn)
 * @version Create Date: 2015年1月8日
 * @since groovy 2.3
 */
@Service
class CoursesService {
	@Autowired
	CoursesCacheDAO coursesDao

	public User findCourseById(long id){
		coursesDao.findCourseById(id)
	}
	
	public List<Course> findCourses(){
		coursesDao.findCourses()
	}

	public void updateCourse(Course example){
		coursesDao.updateCourse(example)
	}
	public void createCourse(Course example){
		coursesDao.createCourse(example)
	}
	public void deleteCourse(Long id){
		coursesDao.deleteCourse(id)
	}
		 
}
