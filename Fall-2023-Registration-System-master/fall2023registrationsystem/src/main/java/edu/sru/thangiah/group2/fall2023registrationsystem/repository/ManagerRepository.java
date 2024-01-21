package edu.sru.thangiah.group2.fall2023registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Manager;

//repository interface
public interface ManagerRepository extends JpaRepository<Manager, String> {
	
	// finds manager by email
	Manager findByEmail(String email);

	// check if manager exists by email
	boolean existsByEmail(String email);

	// deletes manager by email
	@Modifying
	@Transactional
	void deleteByEmail(String email);
}