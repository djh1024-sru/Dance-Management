package edu.sru.thangiah.group2.fall2023registrationsystem.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Children;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;
import edu.sru.thangiah.group2.fall2023registrationsystem.model.User;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ChildRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.ParentService;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.UserService;

@Controller
public class ParentController {

	@Autowired
	private ChildRepository childRepository;

	@Autowired
	private ParentService parentService;
	@Autowired
	private UserService userService;

	// mapping for the parent dashboard
	@GetMapping("/parent")
	public String parentDashboard() {
		return "parent";
	}

	// selecting a child for displaying activities
	@GetMapping("/parent/selectChild")
	public String selectChild(Model model) {
		User currentUser = userService.getCurrentUser();
		// find logged in parent data by email
		Parent parentData = parentService.findParentByEmail(currentUser.getEmail());
		model.addAttribute("parentData", parentData);

		// get parentID + children associated with it
		Integer parentID = parentData.getParentID();
		List<Children> children = childRepository.findByParentID(parentID);

		Set<String> childNames = new HashSet<>();

		model.addAttribute("children", children);
		model.addAttribute("childNames", childNames);

		return "selectChild";
	}

	// mapping for displaying child activities
	@GetMapping("/parent/childActivities")
	public String childActivities(Model model, @RequestParam int childID) {
		// find distinct children by childID
		List<Children> children = childRepository.findDistinctByChildID(childID);
		model.addAttribute("children", children);
		return "childActivities";

	}
}