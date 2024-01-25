package edu.sru.thangiah.group2.fall2023registrationsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin {

	@Id
	private String adminID;

	private String lastName;
	private String firstName;
	private String email;
	private String adminPhoneNum;
	private String password;
	private String role;

	public Admin(String adminID, String lastName, String firstName,
			String email, String adminPhoneNum) {
		super();
		this.adminID = adminID;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.adminPhoneNum = adminPhoneNum;
	}

	// getters + setters
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdminPhoneNum() {
		return adminPhoneNum;
	}

	public void setAdminPhoneNum(String adminPhoneNum) {
		this.adminPhoneNum = adminPhoneNum;
	}

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public Admin() {
		// default constructor
	}

	@Override
	public String toString() {
		return "Admin [userID = " + adminID + ", lastName = " + lastName + ", firstName = " + firstName + ", email = "
				+ email + ", phoneNum = " + adminPhoneNum + "]";
	}
}