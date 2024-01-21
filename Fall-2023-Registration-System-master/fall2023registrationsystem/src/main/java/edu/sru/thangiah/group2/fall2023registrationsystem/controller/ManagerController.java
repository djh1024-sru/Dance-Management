package edu.sru.thangiah.group2.fall2023registrationsystem.controller;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Activities;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Children;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;
import edu.sru.thangiah.group2.fall2023registrationsystem.model.User;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ChildRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.InstructorRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ParentRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.UserRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.ActivityService;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.DisplayService;

// managing operations related to the manager role
@Controller
public class ManagerController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private ParentRepository parentRepository;
	@Autowired
	private ChildRepository childRepository;

	@Autowired
	private ActivityService activityService;
	@Autowired
	private DisplayService displayService;

	// mapping for the manager dashboard
	@GetMapping("/manager")
	public String managerDashboard(Model model, @RequestParam(required = false) Integer childID) {
		// retrieve instructor and parent users for the manager dashboard
		List<User> allUsers = userRepository.findAll();
		List<User> instructorUsers = allUsers.stream().filter(user -> "INSTRUCTOR".equals(user.getRole()))
				.collect(Collectors.toList());
		List<User> parentUsers = allUsers.stream().filter(user -> "PARENT".equals(user.getRole()))
				.collect(Collectors.toList());
		List<User> activeParentUsers = parentUsers.stream().filter(user -> user.getIsActive())
                .collect(Collectors.toList());
		List<User> activeInstructorsUsers = instructorUsers.stream().filter(user -> user.getIsActive())
                .collect(Collectors.toList());
		List<User> inactiveUsers = allUsers.stream().filter(user -> !user.getIsActive())
                .collect(Collectors.toList());
		
		// displays unique children sorted by ChildID
		List<Children> allChildren = displayService.getAllChildren();
		Map<Integer, List<Children>> childrenByChildID = allChildren.stream()
	            .collect(Collectors.groupingBy(Children::getChildID));
		Map<Integer, List<Children>> sortedChildrenByChildID = childrenByChildID.entrySet().stream()
	            .sorted(Map.Entry.comparingByKey())
	            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		model.addAttribute("inactiveUsers", inactiveUsers);
		model.addAttribute("activeParentUsers", activeParentUsers);
		model.addAttribute("activeInstructorsUsers", activeInstructorsUsers);
		model.addAttribute("instructorUsers", instructorUsers);
		model.addAttribute("parentUsers", parentUsers);
		model.addAttribute("childrenByChildID", sortedChildrenByChildID);
		
		return "manager";
	}
	
	@Transactional
	@DeleteMapping("/manager/delete/{email}")
	public ResponseEntity<String> deleteUser(@PathVariable String email) {
		
		System.out.println("Deleting user with email: " + email);

		// delete user records from various repositories based on their roles
		userRepository.deleteByEmail(email);
		parentRepository.deleteByEmail(email);
		instructorRepository.deleteByEmail(email);

		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	}


	// creating a new instructor or parent page
	@GetMapping("/manager/createInstructor")
	public String createManagerPage(Model model) {
		model.addAttribute("newManager", new User());
		return "createParentInstructor";
	}

	// creating a new instructor or parent
	@PostMapping("/manager/createInstructor")
	public String createUser(@RequestParam String role, @RequestParam(required = false) String password,
			@RequestParam(required = false) Integer instructorID,
			@RequestParam(required = false) String instructorPhoneNum, @RequestParam(required = false) String lastName,
			@RequestParam(required = false) String firstName, @RequestParam(required = false) String email,
			@RequestParam(required = false) String specialty, @RequestParam(required = false) String studioA,
			@RequestParam(required = false) String studioB, @RequestParam(required = false) Integer parentID,
			@RequestParam(required = false) String parent1PhoneNum,
			@RequestParam(required = false) String parent2PhoneNum,
			@RequestParam(required = false) String primaryAddress, @RequestParam(required = false) String eContName,
			@RequestParam(required = false) String eContNum, @RequestParam(required = false) Float balance,
			@RequestParam(required = false) String parent1FirstName,
			@RequestParam(required = false) String parent1LastName,
			@RequestParam(required = false) String parent2FirstName,
			@RequestParam(required = false) String parent2LastName) {

		// password encoder for encrypting passwords
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User newUser = new User();
		newUser.setEmail(email);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setPassword(passwordEncoder.encode(password));
		newUser.setRole(role);

		// create a new instructor or parent based on the role
		if ("INSTRUCTOR".equals(role)) {
			Integer lastInstructorID = instructorRepository.findMaxInstructorID();
			instructorID = (lastInstructorID != null) ? lastInstructorID + 1 : 1;
			Instructor newInstructor = new Instructor(instructorID, lastName, firstName, email, instructorPhoneNum,
					specialty, studioA, studioB);
			newInstructor.setPassword(passwordEncoder.encode(password));
			newInstructor.setRole(role);
			instructorRepository.save(newInstructor);

		} else if ("PARENT".equals(role)) {
			Integer lastParentID = parentRepository.findMaxParentID();
			parentID = (lastParentID != null) ? lastParentID + 1 : 1;
			String parent1Name = parent1FirstName + " " + parent1LastName;
			String parent2Name = parent2FirstName + " " + parent2LastName;
			Parent newParent = new Parent(parentID, parent1Name, parent1PhoneNum, parent2Name, parent2PhoneNum, email,
					primaryAddress, eContName, eContNum, balance);
			newParent.setPassword(passwordEncoder.encode(password));
			newParent.setRole(role);
			newParent.setBalance(0.0f);
			newUser.setFirstName(parent1FirstName);
			newUser.setLastName(parent1LastName);
			parentRepository.save(newParent);
		}

		userRepository.save(newUser);

		return "redirect:/manager/UserCreationSuccess";
	}

	// creating a new child page
	@GetMapping("/manager/createChild")
	public String createChildPage(Model model) {
		List<Parent> parents = parentRepository.findAll();
		model.addAttribute("parents", parents);
		model.addAttribute("newChild", new Children());
		return "createChild";
	}

	// post mapping for creating a new child
	@PostMapping("/manager/createChild")
	public String createChild(@ModelAttribute("newChild") Children newChild, Model model,
			@RequestParam(required = false) Integer childID, @RequestParam int parentID) {

		// generate a unique child ID
		Integer lastChildID = childRepository.findMaxChildID();
		childID = (lastChildID != null) ? lastChildID + 1 : 1;
		newChild.setChildID(childID);
		newChild.setParentID(parentID);

		// set the registration date to the current date
		String currentDate = LocalDate.now().toString();
		newChild.setRegistrationDate(currentDate);

		// save the new child record
		Children savedUser = childRepository.save(newChild);
		Children newChildRecord = new Children();
		newChildRecord.setChildID(childID);
		newChildRecord.setParentID(parentID);
		newChildRecord.setRegistrationDate(currentDate);
		newChildRecord.setLastName(savedUser.getLastName());
		newChildRecord.setFirstName(savedUser.getFirstName());
		newChildRecord.setBirthDate(savedUser.getBirthDate());
		newChildRecord.setAge(savedUser.getAge());
		newChildRecord.setGrade(savedUser.getGrade());

		childRepository.save(newChildRecord);

		return "redirect:/manager/UserCreationSuccess";
	}

	// displaying the user creation success page
	@GetMapping("/manager/UserCreationSuccess")
	public String UserCreationSuccess() {
		return "UserCreationSuccess";
	}

	// manager export page
	@GetMapping("/manager/managerExport")
	public String managerExport() {
		return "managerExport";
	}
	
	@GetMapping("/manager/uploadButtons")
	public String managerUploads() {
		return "uploadButtons";
	}

	// selecting parents page
	@GetMapping("/manager/selectParent")
	public String getAllParents(Model model) {
		// retrieve and display all parents
		List<Parent> parents = parentRepository.findAll();
		model.addAttribute("parents", parents);
		return "parentSelection";
	}

	// selecting children page
	@GetMapping("/manager/childSelection")
	public String getAllChildren(Model model, @RequestParam(required = false) Integer childID) {
		// retrieve and display all children, sorted by last name
		List<Children> children = childRepository.findAll();
		children.sort(Comparator.comparing(Children::getLastName));
		Set<String> childNames = new LinkedHashSet<>();

		model.addAttribute("children", children);
		model.addAttribute("childNames", childNames);
		return "childSelection";
	}

	// mapping for deleting a class
	@GetMapping("/deleteClass/{activityID}/{locationName}/{activityLevel}")
	public String deleteClass(@PathVariable String activityID, @PathVariable String locationName,
			@PathVariable String activityLevel, RedirectAttributes redirectAttributes) {
		// delete the specified class
		activityService.deleteClass(activityID, locationName, activityLevel);
		redirectAttributes.addFlashAttribute("successMessage", "Class deleted successfully!");

		return "redirect:/manager/schedule";
	}

	// mapping for editing an activity
	@GetMapping("/editActivity/{activityID}")
	public String editActivity(@PathVariable String activityID, Model model) {
		// retrieve the activity by ID and add it to model for editing
		Activities activity = activityService.getActivityById(activityID);
		model.addAttribute("activity", activity);

		return "editActivity";
	}

	// updating an activity
	@PostMapping("/updateActivity")
	public String updateActivity(@ModelAttribute Activities activity) {
		// update the activity details
		activityService.updateActivity(activity);
		return "redirect:/manager/schedule";
	}

	// deleting a child from a class
	@GetMapping("/deleteChild/{childID}/{activityID}/{activityLevel}")
	public String deleteChild(@PathVariable int childID, @PathVariable String activityID,
			@PathVariable Integer activityLevel, RedirectAttributes redirectAttributes) {
		// delete the specified child from the class
		activityService.deleteChild(childID, activityID, activityLevel);
		redirectAttributes.addFlashAttribute("successMessage", "Child deleted successfully!");

		return "redirect:/manager/classLists";
	}
	
	
	//Deactivating a User Account and return a response
	@PutMapping("/manager/deactivate/{email}")
	public ResponseEntity<String> deactivateUser(@PathVariable String email) {
	    User user = userRepository.findByEmail(email);
	    if (user != null) {
	        user.setIsActive(false);
	        userRepository.save(user);
	        return new ResponseEntity<>("User deactivated successfully", HttpStatus.OK);
	    }
	    return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	}
	//Activating a User Account and return a response
	@PutMapping("/manager/activate/{email}")
	public ResponseEntity<String> activateUser(@PathVariable String email) {
	    User user = userRepository.findByEmail(email);
	    if (user != null) {
	        user.setIsActive(true);
	        userRepository.save(user);
	        return new ResponseEntity<>("User activated successfully", HttpStatus.OK);
	    }
	    return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	}

}