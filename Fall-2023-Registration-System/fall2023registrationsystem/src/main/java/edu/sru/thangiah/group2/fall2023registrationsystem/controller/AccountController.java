package edu.sru.thangiah.group2.fall2023registrationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.sru.thangiah.group2.fall2023registrationsystem.model.User;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.UserService;

@Controller
public class AccountController {
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// display user account details
	@GetMapping("/account")
	public String viewAccount(Model model) {
		User user = userService.getCurrentUser();
		model.addAttribute("user", user);
		return "account";
	}

	// display change password form
	@GetMapping("/changePassword")
	public String showChangePasswordForm(@RequestParam(value = "error", required = false) String error,
	        @RequestParam(value = "success", required = false) String success, Model model) {
	    if ("true".equals(error)) {
	        model.addAttribute("error", "Invalid old password");
	    }
	    if ("true".equals(success)) {
	        model.addAttribute("success", "Password updated successfully!");
	    }

	    // check for password requirements
	    if ("passwordError".equals(error)) {
	        model.addAttribute("passwordError", "Password must be at least 8 characters long and contain a special symbol and a capital letter.");
	    }

	    return "changePassword";
	}

	// update user account details
	@PostMapping("/updateAccount")
	public String updateAccount(User updateUser) {
		userService.updateUser(updateUser);
		return "redirect:/account";
	}

	// process change password request
	@PostMapping("/changePassword")
	public String changePassword(@RequestParam("oldPassword") String oldPassword,
	        @RequestParam("newPassword") String newPassword, Model model) {
	    User user = userService.getCurrentUser();

	    try {
	        // check if the new password meets the criteria
	        if (!isValidPassword(newPassword)) {
	            return "redirect:/changePassword?error=passwordError";
	        }

	        // update password for the current user
	        userService.updatePasswordForAllRoles(user.getEmail(), oldPassword, newPassword);

	        // if it's the user's first login, update the flag
	        if (user.isFirstLogin()) {
	            user.setFirstLogin(false);
	            userService.updateUser(user);
	        }
	        return "redirect:/changePassword?success=true";
	    } catch (RuntimeException e) {
	        return "redirect:/changePassword?error=true";
	    }
	}

	private boolean isValidPassword(String password) {
	    // minimum 8 characters, at least one special symbol, and one capital letter
	    String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
	    return password.matches(regex);
	}

}