package edu.sru.thangiah.group2.fall2023registrationsystem.domain;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Activities")
public class Activities {

	@Id
	private String activityID;
	private String locationName;
	private String weekDay;
	private LocalTime startTime;
	private LocalTime endTime;
	private String activityLevel;
	private Integer instructorID;

	public Activities(String activityID, String locationName,
			String weekDay, LocalTime startTime, LocalTime endTime,
			String activityLevel, Integer instructorID) {

		super();
		this.activityID = activityID;
		this.locationName = locationName;
		this.weekDay = weekDay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.activityLevel = activityLevel;
		this.instructorID = instructorID;
	}

	// getters + setters
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getActivityID() {
		return activityID;
	}

	public void setActivityID(String activityID) {
		this.activityID = activityID;
	}

	public String getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

	public Integer getInstructorID() {
		return instructorID;
	}

	public void setInstructorID(Integer instructorID) {
		this.instructorID = instructorID;
	}

	public Activities() {
		// default constructor
	}

	@Override
	public String toString() {
		return "Location [activityID = " + activityID + ", locationName = " + locationName + ", weekDay = " + weekDay
				+ ", startTime = " + startTime + ", endTime = " + endTime + "]";
	}
}