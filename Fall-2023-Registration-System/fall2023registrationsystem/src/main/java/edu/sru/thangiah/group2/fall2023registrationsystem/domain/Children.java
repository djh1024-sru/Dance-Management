package edu.sru.thangiah.group2.fall2023registrationsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "Children")
@IdClass(ChildId.class)
public class Children {

	@Id
	private int childID;
	@Id
	private Integer activityLevel;
	@Id
	private String activityID;
	private String lastName;
	private String firstName;
	private String birthDate;
	private int age;
	private String grade;
	private String registrationDate;
	private boolean status;
	private int parentID;
	private int studioID;

	public Children(int childID, Integer activityLevel, String activityID, 
			String lastName, String firstName, String birthDate,
			int age, String grade, String registrationDate, 
			boolean status, int parentID, int studioID) {

		super();
		this.childID = childID;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDate = birthDate;
		this.age = age;
		this.grade = grade;
		this.registrationDate = registrationDate;
		this.activityID = activityID;
		this.studioID = studioID;
		this.activityLevel = activityLevel;
		this.status = status;
		this.parentID = parentID;
	}

	//getters + setters
	public int getChildID() {
		return childID;
	}

	public void setChildID(int childID) {
		this.childID = childID;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String schoolYear) {
		this.grade = schoolYear;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getActivityID() {
		return activityID;
	}

	public void setActivityID(String activityID) {
		this.activityID = activityID;
	}

	public int getStudioID() {
		return studioID;
	}

	public void setStudioID(int studioID) {
		this.studioID = studioID;
	}

	public Integer getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(Integer activityLevel) {
		this.activityLevel = activityLevel;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public Children() {
		this.activityID = " ";
		this.activityLevel = 0;
	}

	//testing purposes
	public Children(int childID2, String string, String activityID2, String lastName2, int i, String birthDate2,
			String string2, String grade2, int j, int k, boolean b, int studioID2) {
		
	}

	@Override
	public String toString() {
		return "Charges [childID = " + childID + ", lastName = " + lastName + ", firstName = " + firstName
				+ ", birthDate = " + birthDate + ", age = " + age + ", grade + " + grade + ", registrationDate = "
				+ registrationDate + "]";
	}

}