package edu.sru.thangiah.group2.fall2023registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Admin;

//repository interface
public interface AdminRepository extends JpaRepository<Admin, String> {
	
	// find admin by email
	Admin findByEmail(String email);

	// check if admin exists with given email
	boolean existsByEmail(String email);

	// delete admin by email
	@Modifying
	@Transactional
	void deleteByEmail(String email);
}