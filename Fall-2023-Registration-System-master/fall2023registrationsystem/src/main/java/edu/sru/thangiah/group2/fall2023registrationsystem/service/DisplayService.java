package edu.sru.thangiah.group2.fall2023registrationsystem.service;

import java.util.List;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Children;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Location;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;

public interface DisplayService {
	
	// get list of all locations
	List<Location> getAllLocs();

	// get list of all instructors
	List<Instructor> getAllInstructs();

	// get list of all children
	List<Children> getAllChildren();

	//get list of parents with their balances
	List<Parent> getParentBalance();
}