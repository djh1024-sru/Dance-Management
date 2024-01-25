package edu.sru.thangiah.group2.fall2023registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import edu.sru.thangiah.group2.fall2023registrationsystem.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	// find user by email
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);
	
	// check if user exists by email
	public boolean existsByEmail(String email);
	
	// deletes user by email
	@Modifying
	@Transactional
	void deleteByEmail(String email);
}