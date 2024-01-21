package edu.sru.thangiah.group2.fall2023registrationsystem.controller;

import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Admin;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Manager;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;
import edu.sru.thangiah.group2.fall2023registrationsystem.model.User;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.AdminRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.InstructorRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ManagerRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ParentRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.UserRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.DatabaseBackupService;

@Controller
public class AdminController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private InstructorRepository instructorRepository;

	@Autowired
	private ParentRepository parentRepository;

	// display the admin dashboard with user role breakdown
	@GetMapping("/admin")
	public String adminDashboard(Model model) {
		List<User> allUsers = userRepository.findAll();
		List<User> adminUsers = allUsers.stream().filter(user -> "ADMIN".equals(user.getRole()))
				.collect(Collectors.toList());
		List<User> managerUsers = allUsers.stream().filter(user -> "MANAGER".equals(user.getRole()))
				.collect(Collectors.toList());
		List<User> instructorUsers = allUsers.stream().filter(user -> "INSTRUCTOR".equals(user.getRole()))
				.collect(Collectors.toList());
		List<User> parentUsers = allUsers.stream().filter(user -> "PARENT".equals(user.getRole()))
				.collect(Collectors.toList());
		List<User> activeParentUsers = parentUsers.stream().filter(user -> user.getIsActive())
                .collect(Collectors.toList());
		List<User> activeManagerUsers = managerUsers.stream().filter(user -> user.getIsActive())
                .collect(Collectors.toList());
		List<User> activeInstructorsUsers = instructorUsers.stream().filter(user -> user.getIsActive())
                .collect(Collectors.toList());
		List<User> inactiveUsers = allUsers.stream().filter(user -> !user.getIsActive())
                .collect(Collectors.toList());
		model.addAttribute("inactiveUsers", inactiveUsers);
		model.addAttribute("activeParentUsers", activeParentUsers);
		model.addAttribute("activeManagerUsers", activeManagerUsers);
		model.addAttribute("activeInstructorsUsers", activeInstructorsUsers);
		model.addAttribute("adminUsers", adminUsers);
		model.addAttribute("managerUsers", managerUsers);
		model.addAttribute("instructorUsers", instructorUsers);
		model.addAttribute("parentUsers", parentUsers);
		return "admin";
	}

	// display the form for creating an admin user
	@GetMapping("/admin/createUser-Admin")
	public String createAdminPage(Model model) {
		model.addAttribute("newAdmin", new User());
		return "createUser-Admin";
	}

	// process the form submission for creating a user
	@PostMapping("/admin/create")
	public String createUser(@RequestParam String role, @RequestParam(required = false) String password,
			@RequestParam(required = false) String adminID, @RequestParam(required = false) String managerID,
			@RequestParam(required = false) Integer instructorID,
			@RequestParam(required = false) String instructorPhoneNum, @RequestParam(required = false) String lastName,
			@RequestParam(required = false) String firstName, @RequestParam(required = false) String email,
			@RequestParam(required = false) String adminPhoneNum,
			@RequestParam(required = false) String managerPhoneNum, @RequestParam(required = false) String specialty,
			@RequestParam(required = false) String studioA, @RequestParam(required = false) String studioB,
			@RequestParam(required = false) Integer parentID, @RequestParam(required = false) String parent1PhoneNum,
			@RequestParam(required = false) String parent2PhoneNum,
			@RequestParam(required = false) String primaryAddress, @RequestParam(required = false) String eContName,
			@RequestParam(required = false) String eContNum, @RequestParam(required = false) Float balance,
			@RequestParam(required = false) String parent1FirstName,
			@RequestParam(required = false) String parent1LastName,
			@RequestParam(required = false) String parent2FirstName,
			@RequestParam(required = false) String parent2LastName) {

		// create a new instance for password hashing
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		// create a new User entity and set common attributes
		User newUser = new User();
		newUser.setEmail(email);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setPassword(passwordEncoder.encode(password));
		newUser.setRole(role);

		// check the role and create and save the corresponding entity
		if ("ADMIN".equals(role)) {
			adminID = generateAdminID(firstName);
			Admin newAdmin = new Admin(adminID, lastName, firstName, email, adminPhoneNum);
			newAdmin.setPassword(passwordEncoder.encode(password));
			newAdmin.setRole(role);
			adminRepository.save(newAdmin);

		} else if ("MANAGER".equals(role)) {
			managerID = generateManagerID(email);
			Manager newManager = new Manager(managerID, lastName, firstName, email, managerPhoneNum);
			newManager.setPassword(passwordEncoder.encode(password));
			newManager.setRole(role);
			managerRepository.save(newManager);

		} else if ("INSTRUCTOR".equals(role)) {
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

		// save the new user
		userRepository.save(newUser);

		return "redirect:/admin/UserCreationSuccess";
	}

	// display a success page after user creation
	@GetMapping("/admin/UserCreationSuccess")
	public String UserCreationSuccess() {
		return "UserCreationSuccess";
	}

	// display the admin export page
	@GetMapping("/admin/adminExport")
	public String adminExport() {
		return "adminExport";
	}

	// generate admin ID based on the first name
	private String generateAdminID(String firstName) {
		return firstName + "1";
	}

	// generate manager ID based on the email (using the part before '@')
	private String generateManagerID(String email) {
		return email.split("@")[0];
	}

	// transactional method to handle user deletion
	@Transactional
	@DeleteMapping("/admin/delete/{email}")
	public ResponseEntity<String> deleteUser(@PathVariable String email) {
		// check if the user to be deleted is the admin; if so, forbid the deletion
		if ("admin@studio.com".equals(email)) {
			return new ResponseEntity<>("AdminDelete", HttpStatus.FORBIDDEN);
		}

		System.out.println("Deleting user with email: " + email);

		// delete user records from various repositories based on their roles
		userRepository.deleteByEmail(email);
		adminRepository.deleteByEmail(email);
		parentRepository.deleteByEmail(email);
		instructorRepository.deleteByEmail(email);
		managerRepository.deleteByEmail(email);

		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	}

	// display a success page after user deletion
	@GetMapping("/admin/user-deleted-success")
	public String userDeletedSuccess() {
		return "user-deleted-success";
	}

	// transactional method to reset the password for a user
	@Transactional
	@PostMapping("/admin/resetPassword/{email}")
	public ResponseEntity<String> resetPassword(@PathVariable String email) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			// reset the password to a default value
			user.setPassword(passwordEncoder.encode("1234"));
			user.setFirstLogin(true);
			userRepository.save(user);
			return new ResponseEntity<>("Password reset successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	}

	// backup the database and return the response
	@PostMapping("/admin/backup")
	public ResponseEntity<Map<String, String>> backupDatabase() {
		Map<String, String> response = DatabaseBackupService.backup();
		if ("success".equals(response.get("status"))) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(500).body(response);
		}
	}
	
	//Deactivating a User Account and return a response
	@PutMapping("/admin/deactivate/{email}")
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
	@PutMapping("/admin/activate/{email}")
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