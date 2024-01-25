package edu.sru.thangiah.group2.fall2023registrationsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Activities;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ActivitiesRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ChildRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.InstructorRepository;
import jakarta.transaction.Transactional;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivitiesRepository actRepo;
	@Autowired
	private InstructorRepository instructorRepo;
	@Autowired
	private ChildRepository childRepo;

	// get list of all activities
	@Override
	public List<Activities> getActivities() {
		return actRepo.findAll();
	}

	// get list of activities for specific location
	@Override
	public List<Activities> getActsByLocation(String locationName) {
		return actRepo.findActivitiesByLocationName(locationName);
	}

	// find activities based on instructorID
	@Override
	public List<Activities> findActivitiesByInstructorID(Integer instructorID) {
		return actRepo.findByInstructorID(instructorID);
	}

	// find instructor by email
	public Instructor findInstructorByEmail(String email) {
		return instructorRepo.findByEmail(email);
	}

	// delete class based on activityID, locationName, and activityLevel
	@Transactional
	public void deleteClass(String activityID, String locationName, String activityLevel) {
		actRepo.deleteByActivityIDAndLocationNameAndActivityLevel(activityID, locationName, activityLevel);
	}

	// delete child based on childID, activityID, and activityLevel
	@Transactional
	public void deleteChild(int childID, String activityID, Integer activityLevel) {
		childRepo.deleteByChildIDAndActivityIDAndActivityLevel(childID, activityID, activityLevel);
	}

	// update activity details
	@Transactional
	public void updateActivity(Activities activity) {
		actRepo.save(activity);
	}

	// get activity by activityID
	public Activities getActivityById(String activityID) {
		Optional<Activities> optionalActivity = actRepo.findById(activityID);

		return optionalActivity.orElse(null);
	}

}