package com.bridz.model;

public class UserDetails {

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	/**
	 * @return the firstName
	 */
	public synchronized final String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public synchronized final void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public synchronized final String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public synchronized final void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the userName
	 */
	public synchronized final String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public synchronized final void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public synchronized final String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public synchronized final void setPassword(String password) {
		this.password = password;
	}
	
}
