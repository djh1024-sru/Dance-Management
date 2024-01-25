package edu.sru.thangiah.group2.fall2023registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;

//repository interface
public interface ParentRepository extends JpaRepository<Parent, Integer> {

	// finds maximum parentID to assign new parent with new ID
	@Query("SELECT MAX(p.parentID) FROM Parent p")
	Integer findMaxParentID();

	// find parent by email
	Parent findByEmail(String primaryemail);

	// check if parent exists by email
	boolean existsByEmail(String email);

	// deletes parent by email
	@Modifying
	@Transactional
	void deleteByEmail(String email);
}