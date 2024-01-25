package edu.sru.thangiah.group2.fall2023registrationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// handle custom 403 errors
@Controller
public class ErrorController {

	@RequestMapping("/403")
	public String handle403() {
		return "403.html";
	}
}