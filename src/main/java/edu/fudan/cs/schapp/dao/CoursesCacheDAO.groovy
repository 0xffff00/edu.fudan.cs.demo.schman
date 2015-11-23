package edu.fudan.cs.schapp.dao

import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository;

import edu.fudan.cs.schapp.model.Course
import edu.fudan.cs.schapp.model.CourseSelection
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
	
	public void incStuNumForCourse(long courseId,int increment){		
	   Course item=findCourseById(courseId)
	   item.numStudents+=increment
		
   }
	public void deleteCourse(long id){
		int x=cdp.courses.findIndexOf {it.id==id}
		if (x>=0)
			cdp.courses.remove(x)
	}
	
	public List<Course> findCourses(){
		cdp.courses
	}
	public List<Course> findCoursesReserved(long studentId){		
		cdp.courseSelections.findAll{it.student.id==studentId}.collect{it.course}
		
	}
	public List<Course> findCoursesUnreserved(long studentId){		
		def cs=cdp.courseSelections.findAll{it.student.id==studentId}.collect{it.course}
		cdp.courses.minus(cs)		
	}
	public List<User> findStudentReserved(long courseId){		
		cdp.courseSelections.findAll{it.course.id==courseId}.collect{it.student}		
	}
	
	public List<Course> findCoursesTaught(long teacherId){
		cdp.courses.findAll{it.teacher?.id==teacherId}
	}
	public List<CourseSelection> findCourseSelectionsByCourse(long courseId){
		cdp.courseSelections.findAll{it.course?.id==courseId}
		
	}
	public List<CourseSelection> findCourseSelectionsByStudent(long studentId){
		cdp.courseSelections.findAll{it.student?.id==studentId}
		
	}
	public void createCourseSelection(long userId,long courseId){
		def user=cdp.users.find{it.id==userId}
		def course=findCourseById(courseId)
		def example=new CourseSelection(student:user,course:course)
		createCourseSelection(example)
	}
	public void createCourseSelection(CourseSelection example){
		example.id=cdp.seq_courseSelections++
		cdp.courseSelections.add(example)		
	}
	public void updateCourseSelection(CourseSelection example){
		CourseSelection item 
		if (example.id) 
			item=cdp.courseSelections.find{it.id==example.id}
		else
			item=cdp.courseSelections.find{it.student==example.student && it.course==example.course}
		item.score=example.score
		item.grade=example.grade
	}
	public void deleteCourseSelection(User student,Course course){
		int x=cdp.courseSelections.findIndexOf {it.student==student && it.course==course}
		if (x>=0)
			cdp.courseSelections.remove(x)
	}
	public void deleteCourseSelection(long studentId,long courseId){
		int x=cdp.courseSelections.findIndexOf {it.student.id==studentId && it.course.id==courseId}
		if (x>=0)
			cdp.courseSelections.remove(x)
	}
	 
}
