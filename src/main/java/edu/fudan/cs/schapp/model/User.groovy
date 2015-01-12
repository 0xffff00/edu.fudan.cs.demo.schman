package edu.fudan.cs.schapp.model

class User implements UserAdaptee{

	Long id
	String code
	String username
	String password
	String department
	String major
	Role main_role
	@Override
	public String toString() {
		code+'@'+username
	}
	@Override
	public String getMainRoleName() {
		main_role?.name
	}
	
	
}
