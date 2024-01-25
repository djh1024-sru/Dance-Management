package edu.sru.thangiah.group2.fall2023registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Location;

//repository interface
public interface LocationRepository extends JpaRepository<Location, Integer> {
	
}