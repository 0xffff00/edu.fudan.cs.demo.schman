package edu.fudan.cs.schapp.dao

import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository;

import edu.fudan.cs.schapp.model.Course
import edu.fudan.cs.schapp.model.Role
import edu.fudan.cs.schapp.model.User
import edu.fudan.cs.schapp.util.CachedDataProvider;
@Repository
class UsersRolesCacheDAO {

	@Autowired
	CachedDataProvider cdp

	public User findUserById(Long id){
		cdp.users.find {it.id=id}
	}
	public User findUserByName(String name){
		cdp.users.find {it.username=name}
	}
	public User createUser(User example){
		example.id=cdp.seq_users++
		cdp.users.add(example)
	}
	public void updateUser(User example){
		User item=findUserById(example.id)
		item.username=example.username
		item.password=example.password
		item.main_role=example.main_role
	}
	public int deleteUser(long id){
		int x=cdp.users.findIndexOf {it.id=id}
		cdp.users.remove(x)
	}
	public List<User> findUsers(){
		cdp.users
	}
	public Role findRoleById(long id){
		cdp.roles.find {it.id=id}
	}
	public List<Role> findRoles(){
		cdp.roles
	}
}
