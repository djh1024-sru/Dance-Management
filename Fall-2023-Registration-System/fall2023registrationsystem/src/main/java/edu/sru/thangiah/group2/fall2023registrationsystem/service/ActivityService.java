package edu.sru.thangiah.group2.fall2023registrationsystem.service;

import java.util.List;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Activities;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;

public interface ActivityService {
	
	// get list of all activities
	List<Activities> getActivities();

	// get list of activities for specific location
	List<Activities> getActsByLocation(String locationName);

	// find activities based on instructorID
	List<Activities> findActivitiesByInstructorID(Integer instructorID);

	// find instructor by email
	Instructor findInstructorByEmail(String email);

	// delete class based on activityID, locationName, and activityLevel
	void deleteClass(String activityID, String locationName, String activityLevel);

	// update activity details
	void updateActivity(Activities activity);

	// get activity by activityID
	public Activities getActivityById(String activityID);

	// delete child based on childID, activityID, and activityLevel
	void deleteChild(int childID, String activityID, Integer activityLevel);
}