package edu.sru.thangiah.group2.fall2023registrationsystem.domain;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Instructor")
public class Instructor {

	@Id
	private Integer instructorID;

	private String lastName;
	private String firstName;
	private String email;
	private String instructorPhoneNum;
	private String specialty;
	private String studioA;
	private String studioB;
	private String password;
	private String role;

	public Instructor(Integer instructorID, String lastName,
			String firstName, String email, String instructorPhoneNum,
			String specialty, String studioA, String studioB) {
		super();
		this.instructorID = instructorID;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.instructorPhoneNum = instructorPhoneNum;
		this.specialty = specialty;
		this.studioA = studioA;
		this.studioB = studioB;
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

	public Integer getInstructorID() {
		return instructorID;
	}

	public void setInstructorID(Integer instructorID) {
		this.instructorID = instructorID;
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

	public String getInstructorPhoneNum() {
		return instructorPhoneNum;
	}

	public void setInstructorPhoneNum(String instructorPhoneNum) {
		this.instructorPhoneNum = instructorPhoneNum;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getStudioA() {
		return studioA;
	}

	public void setStudioA(String studioA) {
		this.studioA = studioA;
	}

	public String getStudioB() {
		return studioB;
	}

	public void setStudioB(String studioB) {
		this.studioB = studioB;
	}

	public Instructor() {
		// default constructor
	}

	@Override
	public String toString() {
		return "Instructor [instructorID = " + instructorID + ", lastName = " + lastName + ", firstName = " + firstName
				+ ", email = " + email + ", phoneNumber = " + instructorPhoneNum + ", specialty = " + specialty
				+ ", studioA = " + studioA + ", studioB = " + studioB + "]";
	}
}