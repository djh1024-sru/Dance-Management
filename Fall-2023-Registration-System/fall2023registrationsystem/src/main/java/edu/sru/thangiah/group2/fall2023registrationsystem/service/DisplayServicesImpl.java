package edu.sru.thangiah.group2.fall2023registrationsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Children;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Location;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ChildRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.InstructorRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.LocationRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ParentRepository;

@Service
public class DisplayServicesImpl implements DisplayService {

	@Autowired
	private LocationRepository locationRepo;
	@Autowired
	private InstructorRepository instructorRepo;
	@Autowired
	private ChildRepository childRepo;
	@Autowired
	private ParentRepository parentRepo;

	// get list of all locations
	@Override
	public List<Location> getAllLocs() {
		return locationRepo.findAll();
	}
	
	// get list of all instructors
	@Override
	public List<Instructor> getAllInstructs() {
		return instructorRepo.findAll();
	}

	// get list of all children
	@Override
	public List<Children> getAllChildren() {
		return childRepo.findAll();
	}

	//get list of parents with their balances
	@Override
	public List<Parent> getParentBalance() {
		return parentRepo.findAll();
	}

}