package edu.fudan.cs.schapp.model

class User {

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
	
	
}
class Student extends User{
	
}