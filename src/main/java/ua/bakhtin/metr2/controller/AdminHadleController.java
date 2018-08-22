package ua.bakhtin.metr2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ua.bakhtin.metr2.enums.EstateStatusType;
import ua.bakhtin.metr2.enums.UserType;
import ua.bakhtin.metr2.model.Estate;
import ua.bakhtin.metr2.model.Types;
import ua.bakhtin.metr2.model.User;
import ua.bakhtin.metr2.service.EstateService;

@Controller
@RequestMapping("/admin/handle")
@SessionAttributes("currentUser")
public class AdminHadleController {
	
	@Autowired
	private EstateService estateService;
	
	public AdminHadleController() {}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@GetMapping
	public ModelAndView goAdminHandlePage(ModelAndView modelAndView, @SessionAttribute(name="currentUser", required=false) User currentUser, Estate inputEstate, Types types) {
		if (currentUser!=null && currentUser.getRole().equals(UserType.ADMIN.toString())) {
			List <Estate> inputEstatesList = estateService.getInputEstates();
			modelAndView.addObject("inputEstatesList", inputEstatesList);
			modelAndView.addObject("inputEstate", inputEstate);
			modelAndView.addObject("types", types);
			modelAndView.setViewName("admin-handle");
		} else {
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping
	public ModelAndView approveEstates(ModelAndView modelAndView, Estate inputEstate,  @SessionAttribute(name="currentUser", required=false) User currentUser) {
		if (currentUser != null && currentUser.getRole().equals(UserType.ADMIN.toString())) {
			inputEstate.setStatusType(EstateStatusType.PUBLICATION.toString());
			System.out.println("В контроллере inputEstate IN_TOP = " + inputEstate.getInTop());
			estateService.updateEstate(inputEstate);
			modelAndView.setViewName("redirect:/admin/handle");
		} else {
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@GetMapping ("/delete-estate/{id}")
	public ModelAndView deleteEstate (ModelAndView modelAndView, 
			@SessionAttribute(name="currentUser", required=false) User currentUser, 
			@PathVariable("id") Long id) {
		System.out.println("Удаление " + id);
		if (estateService.getEstateById(id).getUser().getId() == currentUser.getId() || currentUser.getRole().equals(UserType.ADMIN.toString())) {
			estateService.deleteEstate(id);
			
			modelAndView.setViewName("redirect:/admin/handle");
		} else {
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
}
