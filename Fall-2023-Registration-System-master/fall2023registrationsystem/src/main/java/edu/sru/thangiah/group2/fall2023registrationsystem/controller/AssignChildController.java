package edu.sru.thangiah.group2.fall2023registrationsystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Children;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ChildRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.ChildService;

@Controller
public class AssignChildController {

	@Autowired
	private ChildRepository childRepos;

	@Autowired
	private ChildService childService;

	// display the child assignment form for a specific child
	@GetMapping("/childAssignment")
	public String showChildAssign(Model model, @RequestParam int childID) {
		// retrieve children with the specified childID
		List<Children> childrenList = childRepos.findByChildID(childID);

		if (!childrenList.isEmpty()) {
			 // get the first child from the list
			Children child = childrenList.get(0);
			model.addAttribute("child", child);
			return "childAssignment";
		} else {
			// no children found for the given childID
			System.out.println("No children found for childID: " + childID);
			return "errorPage";
		}
	}

	// process the child assignment form submission
	@PostMapping("/submitAssignment")
	public String processAssignment(@ModelAttribute Children childAssignment, @RequestParam String activityID,
			@RequestParam Integer activityLevel, @RequestParam int studioID, @RequestParam("childID") int childID,
			Model model, RedirectAttributes redirectAttributes) {

		// check if the child is already assigned to the specified activity
		List<Children> existingChildren = childRepos.findByChildIDAndActivityID(childID, activityID);

		if (existingChildren.isEmpty()) {
			// retrieve children with the specified childID
			List<Children> childrenList = childRepos.findByChildID(childID);

			if (!childrenList.isEmpty()) {
				Children children = childrenList.get(0);
				
				 // update child assignment details
				children.setActivityID(activityID);
				children.setActivityLevel(activityLevel);
				children.setStudioID(studioID);

				// set the current date as the registration date
				String currentDate = LocalDate.now().toString();
				children.setRegistrationDate(currentDate);

				// save the updated child assignment
				childRepos.save(children);

				// update child assignment in the service
				model.addAttribute("children", children);
				childService.updateChildAssignment(childAssignment);
				redirectAttributes.addFlashAttribute("successMessage",
						"Student added to " + activityID + " " + activityLevel + " class successfully!");

				return "redirect:/childAssignment?childID=" + childID;
			} else {
				 // redirect with error message if there is an issue
				redirectAttributes.addFlashAttribute("errorMessage", "Error adding child.");
				return "redirect:/childAssignment?childID=" + childID;
			}
		} else {
			 // redirect with error message if the child is already enrolled in the specified activity
			redirectAttributes.addFlashAttribute("errorMessage",
					"Child already enrolled in the " + activityID + " " + activityLevel + " class.");
			return "redirect:/childAssignment?childID=" + childID;
		}
	}

}