package edu.fudan.cs.schapp.dao

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

import edu.fudan.cs.schapp.util.SqlBuilder



@Repository
class UsersRolesDAO extends BaseDAO {

	public Map findUserById(long id){
		jdbcTemplate.queryForMap("SELECT * FROM sys_user WHERE id=?",id)
	}
	public Map findUserByIdWithMainRole(long id){
		jdbcTemplate.queryForMap("""
			SELECT u.*,r.name main_role,r.title main_role_title  
			FROM sys_user u,sys_role r 
			WHERE u.main_role_id=r.id AND u.id=?
			"""
				,id)
	}
	public Map findUserByName(String username){
		jdbcTemplate.queryForMap("SELECT * FROM sys_user WHERE username=?",username)
	}
	public Map findUserByNameWithMainRole(String username){
		jdbcTemplate.queryForMap("""
			SELECT u.*,r.name main_role,r.title main_role_title  
			FROM sys_user u,sys_role r 
			WHERE u.main_role_id=r.id AND u.username=?
			"""
				,username)
	}

	public List<Map> findUsersByRole(long roleId){
		jdbcTemplate.queryForList("SELECT * FROM sys_user WHERE role_id=?",roleId)
	}
	public List<Map> findUsers(){
		jdbcTemplate.queryForList("SELECT * FROM sys_user")
	}

	public List<Map> findUsersWithMainRole(){
		def sql="""
			SELECT u.*,r.name main_role,r.title main_role_title  
			FROM sys_user u,sys_role r 
			WHERE u.main_role_id=r.id AND r.id<9000
			"""
		jdbcTemplate.queryForList(sql)
	}

	public List<Map> findRoles(){
		jdbcTemplate.queryForList("SELECT * FROM sys_roles")
	}

	public void updateUser(Map change,Long userId){
		def crits=SqlBuilder.buildNamedParamsContentInUpdateSql(change)
		if (!crits) return
		def sql="""
			UPDATE sys_user SET 
			$crits
			WHERE id= $userId
		"""
		npJdbcTemplate.update(sql, change)
	}

	public void createUser(Map example){
		String sql="""
			INSERT INTO sys_user(username,password,section,phone,email,main_role_id)
			VALUES(:username,:password,:section,:phone,:email,:main_role_id)			
			"""
		npJdbcTemplate.update(sql, example)
	}
	
	public void deleteUser(Long userId){
		String sql="DELETE FROM sys_user WHERE id=$userId"
		jdbcTemplate.update(sql)
	}
	
	public List<Map> findRolesByUserId(long userId){
		String sql="""
		SELECT r.*
		FROM sys_user u,sys_role r,sys_user_role ur
		WHERE ur.role_id=r.id AND ur.user_id=u.id
		AND u.id = $userId
		"""
		jdbcTemplate.queryForList(sql)
	}


	public List<Map> findPermsByUserId(long userId){
		String sql="""
		SELECT p.*
		FROM sys_user u,sys_role r,sys_role_perm rp,sys_perm p
		WHERE u.main_role_id=r.id AND r.id=rp.role_id AND rp.perm_id=p.id 
		AND u.id = $userId
		"""
		jdbcTemplate.queryForList(sql)
	}
}
