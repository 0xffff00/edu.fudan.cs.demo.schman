package edu.fudan.cs.schapp.service

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import edu.fudan.cs.schapp.dao.CoursesCacheDAO
import edu.fudan.cs.schapp.dao.UsersRolesCacheDAO
import edu.fudan.cs.schapp.model.Course
import edu.fudan.cs.schapp.model.CourseSelection;
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

	public Course findCourseById(long id){
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
	
	public List<Course> findCoursesUnreserved(long studentId){		
		coursesDao.findCoursesUnreserved(studentId)
	}
	public List<Course> findCoursesReserved(long studentId){
		coursesDao.findCoursesReserved(studentId)
	}
	public List<Course> findCoursesTaught(long teacherId){
		coursesDao.findCoursesTaught(teacherId)
	}
	public void reserveCourse(long studentId,long courseId){		
		coursesDao.createCourseSelection(studentId,courseId)
		coursesDao.incStuNumForCourse(courseId,1)
	}
	public void dropCourse(long studentId,long courseId){
		coursesDao.deleteCourseSelection(studentId,courseId)
		coursesDao.incStuNumForCourse(courseId,-1)
	}
	
	public List<CourseSelection> findCourseSelectionsByCourse(long courseId){
		coursesDao.findCourseSelectionsByCourse(courseId)
		
	}
	public List<CourseSelection> findCourseSelectionsByStudent(long studentId){
		coursesDao.findCourseSelectionsByStudent(studentId)
		
	}
	public void updateCourseSelections(List<CourseSelection> examples){
		examples.each { updateCourseSelection(it)}
	}
	public void updateCourseSelection(CourseSelection example){
		coursesDao.updateCourseSelection(example)
	}
}
