package edu.sru.thangiah.group2.fall2023registrationsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Activities;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ChildRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.InstructorRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ParentRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.service.ActivityService;

@Controller
public class DisplayController {

	@Autowired
	private InstructorRepository instructRepo;

	@Autowired
	private ChildRepository childRepo;

	@Autowired
	private ParentRepository parentRepo;

	@Autowired
	private ActivityService activityService;
	
	// display all activities for admin, manager, instructor
	@GetMapping("/schedule")
	public ModelAndView getAllActivitiesAdmin() {
		ModelAndView actMav = new ModelAndView("schedule");
		
		// get activities by location for each location
		List<Activities> slipActivities = activityService.getActsByLocation("Slippery Rock");
		List<Activities> groveCityActivities = activityService.getActsByLocation("Grove City");
		List<Activities> mercerActivities = activityService.getActsByLocation("Mercer");
		List<Activities> sharonActivities = activityService.getActsByLocation("Sharon");

		actMav.addObject("SlipperyRockActivities", slipActivities);
		actMav.addObject("GroveCityActivities", groveCityActivities);
		actMav.addObject("MercerActivities", mercerActivities);
		actMav.addObject("SharonActivities", sharonActivities);

		return actMav;
	}

	// display all activities for manager
	@GetMapping("/manager/schedule")
	public ModelAndView getAllActivitiesManager() {
		ModelAndView actMav = new ModelAndView("managerSchedule");
		
		// get activities by location for each location
		List<Activities> slipActivities = activityService.getActsByLocation("Slippery Rock");
		List<Activities> groveCityActivities = activityService.getActsByLocation("Grove City");
		List<Activities> mercerActivities = activityService.getActsByLocation("Mercer");
		List<Activities> sharonActivities = activityService.getActsByLocation("Sharon");

		actMav.addObject("SlipperyRockActivities", slipActivities);
		actMav.addObject("GroveCityActivities", groveCityActivities);
		actMav.addObject("MercerActivities", mercerActivities);
		actMav.addObject("SharonActivities", sharonActivities);

		return actMav;
	}

	// display all children for manager
	@GetMapping("/manager/classLists")
	public ModelAndView getAllChildrenManager() {
		ModelAndView mav = new ModelAndView("managerClassLists");
		mav.addObject("children", childRepo.findAll());
		return mav;
	}

	// display all children for instructor
	@GetMapping("/instructor/classLists")
	public ModelAndView getAllChildrenInstructor() {
		ModelAndView mav = new ModelAndView("classLists");
		mav.addObject("children", childRepo.findAll());
		return mav;
	}

	// display balance of all parents for the manager
	@GetMapping("/manager/checkBalance")
	public ModelAndView getParentBalance() {
		ModelAndView mav = new ModelAndView("parentBalance");
		mav.addObject("balance", parentRepo.findAll());
		return mav;
	}
}