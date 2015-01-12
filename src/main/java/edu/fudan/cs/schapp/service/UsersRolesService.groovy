package edu.fudan.cs.schapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import edu.fudan.cs.schapp.dao.UsersRolesCacheDAO
import edu.fudan.cs.schapp.model.Role
import edu.fudan.cs.schapp.model.User


/**
 * 基于角色的访问控制(RBAC)服务
 * @author Huang Zhenkun (14210240077@fudan.edu.cn)
 * @version Create Date: 2014年12月19日
 * @since 1.8
 */
@Service
class UsersRolesService {
	@Autowired
	UsersRolesCacheDAO usersRolesDao

	public User findUserById(Long id){
			usersRolesDao.findUserById(id)
	}
	
	public User findUserByName(String username){
		usersRolesDao.findUserByName(username)
	}	
	
	public List<User> findUsers(){
		usersRolesDao.findUsers()
	}
	public List<User> findUsersByRoleName(String roleName){
		usersRolesDao.findUsersByRoleName(roleName)
	}

	public void updateUser(User example){
		usersRolesDao.updateUser(example)
	}
	public void createUser(User example){
		usersRolesDao.createUser(example)
	}
	public void deleteUser(Long userId){
		usersRolesDao.deleteUser(userId)
	}
	public Role findRoleById(long id){
		usersRolesDao.findRoleById(id)
	}
	public Role findRoleByName(String name){
		usersRolesDao.findRoleByName(name)
	}
	public List<Role> findRoles(){
		usersRolesDao.findRoles()
	}

	 
}
