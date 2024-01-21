package edu.sru.thangiah.group2.fall2023registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;

//repository interface
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
	
	// finds maximum instructorID to assign new instructor with new ID
	@Query("SELECT MAX(i.instructorID) FROM Instructor i")
	Integer findMaxInstructorID();
	
	// finds instructor by email
	Instructor findByEmail(String email);

	// check if instructor exists by email
	boolean existsByEmail(String email);

	// deletes instructor by email
	@Modifying
	@Transactional
	void deleteByEmail(String email);
}