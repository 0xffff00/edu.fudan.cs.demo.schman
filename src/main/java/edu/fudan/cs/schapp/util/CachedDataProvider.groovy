package edu.fudan.cs.schapp.util

import org.springframework.stereotype.Repository;

import edu.fudan.cs.schapp.model.Course
import edu.fudan.cs.schapp.model.Role
import edu.fudan.cs.schapp.model.User
@Repository
class CachedDataProvider {
	List<Role> roles
	List<User> users
	List<Course> courses
	int seq_roles=40,seq_users=40,seq_courses=69
	public CachedDataProvider() {

		def admin=new Role(id:1,name:'admin')
		def teacher=new Role(id:2,name:'teacher')
		def student=new Role(id:3,name:'student')
		roles=[
			new Role(id:1,name:'admin'),
			new Role(id:2,name:'teacher'),
			new Role(id:3,name:'student')
		]

		users=[
			new User(id:1,username:'admin',password:'1',main_role:admin),
			new User(id:1,username:'teacher01',password:'1',main_role:teacher),
			new User(id:1,username:'teacher02',password:'1',main_role:teacher),
			new User(id:1,username:'teacher03',password:'1',main_role:teacher),
			new User(id:1,username:'teacher04',password:'1',main_role:teacher),
			new User(id:1,username:'teacher05',password:'1',main_role:teacher),
			new User(id:1,username:'student01',password:'1',main_role:student),
			new User(id:1,username:'student02',password:'1',main_role:student),
			new User(id:1,username:'student03',password:'1',main_role:student),
			new User(id:1,username:'student04',password:'1',main_role:student),
			new User(id:1,username:'student05',password:'1',main_role:student),
		]
	}
}
