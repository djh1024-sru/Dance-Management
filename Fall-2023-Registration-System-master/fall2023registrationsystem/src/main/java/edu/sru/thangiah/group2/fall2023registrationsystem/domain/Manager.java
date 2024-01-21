package edu.sru.thangiah.group2.fall2023registrationsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Manager")
public class Manager {

	@Id
	private String managerID;
	private String lastName;
	private String firstName;
	private String email;
	private String managerPhoneNum;
	private String password;
	private String role;

	public Manager(String managerID, String lastName,
			String firstName, String email, String managerPhoneNum) {
		super();
		this.managerID = managerID;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.managerPhoneNum = managerPhoneNum;
	}

	//getters + setters
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

	public String getManagerID() {
		return managerID;
	}

	public void setManagerID(String string) {
		this.managerID = string;
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

	public String getManagerPhoneNum() {
		return managerPhoneNum;
	}

	public void setManagerPhoneNum(String managerPhoneNum) {
		this.managerPhoneNum = managerPhoneNum;
	}

	public Manager() {
		// default constructor
	}

	@Override
	public String toString() {
		return "Manager [userID = " + managerID + ", lastName = " + lastName + ", firstName = " + firstName
				+ ", email = " + email + ", phoneNum = " + managerPhoneNum + "]";
	}
}