package edu.sru.thangiah.group2.fall2023registrationsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Children;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ChildRepository;

@Service
public class ChildService {

	@Autowired
	private ChildRepository childRepository;

	// update child assignment details
	public void updateChildAssignment(Children childAssignment) {
		
		// find distinct children by childID
		List<Children> childrenList = childRepository.findDistinctByChildID(childAssignment.getChildID());

		for (Children child : childrenList) {
			child.setActivityID(childAssignment.getActivityID());
			child.setActivityLevel(childAssignment.getActivityLevel());
			child.setStudioID(childAssignment.getStudioID());
		}
		childRepository.saveAll(childrenList);
	}
}