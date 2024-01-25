package edu.sru.thangiah.group2.fall2023registrationsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Activities;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Instructor;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Transactions;
import edu.sru.thangiah.group2.fall2023registrationsystem.model.User;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.AdminRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.InstructorRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ManagerRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ParentRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.UserRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.ActivityService;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.ParentService;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.UserService;
import jakarta.servlet.http.HttpSession;

// handles user registration, login, and dashboard redirection
@Controller
public class LogonController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ParentService parentService;
	@Autowired
	private ActivityService actService;

	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private ParentRepository parentContactRepository;
	@Autowired
	private AdminRepository adminRepository;
	private static final int PASSWORDSIZE = 5;

	// mapping for the home page
	@GetMapping("/")
	public String viewHomePage() {
		return "index";
	}

	// mapping for the contact us page
	@GetMapping("/contactUs")
	public String viewContactPage() {
		return "contactUs";
	}

	 // mapping for displaying the registration form
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}

	// processing user registration
	@PostMapping("/process_register")
	public String processRegister(User user, BindingResult bindingResult, Model model) {
		// registration validation logic
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}

		String email = user.getEmail();
        // check if user with the given email already exists
		if (userRepository.findByEmail(email) != null) {
			model.addAttribute("error", "A user with this email already exists");
			model.addAttribute("user", new User());
			return "signup_form";
		}

        // set first login to true by default, requires user to reset password
		user.setFirstLogin(true);

        // determine user role based on email
		String role = determineUserRole(email);
		user.setRole(role);

        // encrypt user password
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

        // save the user
		userRepository.save(user);
		return "registration_success";
	}

	// helper method to determine user role based on email
	private String determineUserRole(String email) {
		if (emailExistsInTable("manager", email)) {
			return "MANAGER";
		} else if (emailExistsInTable("instructor", email)) {
			return "INSTRUCTOR";
		} else if (emailExistsInTable("parentcontact", email)) {
			return "PARENT";
		} else if (emailExistsInTable("admin", email)) {
			return "ADMIN";
		} else {
			return "USER";
		}
	}

    // helper method to check if an email exists in a specific table
	public boolean emailExistsInTable(String tableName, String email) {
		boolean exists = false;
		switch (tableName.toLowerCase()) {
		case "manager":
			exists = managerRepository.existsByEmail(email);
			break;
		case "instructor":
			exists = instructorRepository.existsByEmail(email);
			break;
		case "parentcontact":
			exists = parentContactRepository.existsByEmail(email);
			break;
		case "admin":
			exists = adminRepository.existsByEmail(email);
			break;
		}
		return exists;
	}

	// mapping for the instructor dashboard
	@GetMapping("/instructor")
	public String instructorDashboard() {
		return "instructor";
	}

    // redirecting to the appropriate dashboard based on user role
	@GetMapping("/dashboards")
	public String redirectToDashboard(Model model, HttpSession session) {
		User currentUser = userService.getCurrentUser();

		if (currentUser != null) {
			model.addAttribute("user", currentUser);
			String role = currentUser.getRole();

            // redirect to the appropriate dashboard based on the user's role
			switch (role) {
			case "ADMIN":
				return "forward:/admin";
			case "MANAGER":
				return "forward:/manager";
			case "PARENT":
				Parent parentData = parentService.findParentByEmail(currentUser.getEmail());
				model.addAttribute("parentData", parentData);

				if (parentData != null) {
					Integer parentID = parentData.getParentID();
					List<Transactions> transactions = parentService.getParentTransactions(parentID);
					model.addAttribute("transactions", transactions);

					float balance = parentData.getBalance();
					model.addAttribute("balance", balance);
				}
				return "forward:/parent";
			case "INSTRUCTOR":
				Instructor instructData = actService.findInstructorByEmail(currentUser.getEmail());
				model.addAttribute("instructData", instructData);

				if (instructData != null) {
					Integer instructorID = instructData.getInstructorID();
					List<Activities> activities = actService.findActivitiesByInstructorID(instructorID);
					model.addAttribute("activities", activities);
				}

				return "forward:/instructor";
			}
		} else {

			return "forward:/";
		}
		return "forward:/";
	}

	// mapping for login success
	@GetMapping("/loginSuccess")
	public String loginSuccess(Model model, RedirectAttributes redirectAttributes) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		User user = userService.getCurrentUser();
		System.out.println("Inside loginSuccess method");
		System.out.println("Current User: " + user);
		System.out.println("Current User Email: " + user.getEmail());
	    model.addAttribute("user", user);

	    // Check if the user account is active
	    if (!user.getIsActive()) {
	        redirectAttributes.addFlashAttribute("loginError", "Your account has been deactivated. Please contact the administrator.");
	        return "redirect:/error";  // Redirect to the error page
	    }

	    Boolean isFirstLogin = user.isFirstLogin();
		System.out.println("isFirstLogin is: " + isFirstLogin);
	    if (isFirstLogin == null || isFirstLogin) {
	        // Redirect to change password page if it's the user's first login
	        return "redirect:/changePassword";
	    }

	    // Redirect to the appropriate dashboard
	    return "forward:/dashboards";
	}
}