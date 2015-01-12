package edu.fudan.cs.schapp.model;
/**
 * use adapter mode for invoking User via Java code
 * @since 1.8
 */
public interface UserAdaptee {
	public String getUsername();
	public String getMainRoleName();
}
