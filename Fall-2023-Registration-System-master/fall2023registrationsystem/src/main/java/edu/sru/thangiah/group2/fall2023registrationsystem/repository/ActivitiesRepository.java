package edu.sru.thangiah.group2.fall2023registrationsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Activities;

//repository interface
public interface ActivitiesRepository extends JpaRepository<Activities, String> {
	
	// finds activities by location name
	@Query("SELECT a FROM Activities a WHERE a.locationName = :locationName")
    List<Activities> findActivitiesByLocationName(@Param("locationName") String locationName);

	// finds activities by instructorID
	List<Activities> findByInstructorID(Integer instructorID);

	// deletes activities by activityID, location name, activity level
	void deleteByActivityIDAndLocationNameAndActivityLevel(String activityID, String locationName, String activityLevel);
}