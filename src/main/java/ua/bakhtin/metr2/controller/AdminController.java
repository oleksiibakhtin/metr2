package ua.bakhtin.metr2.controller;

import java.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ua.bakhtin.metr2.enums.EstateStatusType;
import ua.bakhtin.metr2.enums.UserType;
import ua.bakhtin.metr2.model.Estate;
import ua.bakhtin.metr2.model.User;

@Controller
@RequestMapping("/admin")
@SessionAttributes ("currentUser")
public class AdminController {
	
	public AdminController() {}
	
	@GetMapping
	public ModelAndView goAdminPage(ModelAndView modelAndView, @SessionAttribute (name="currentUser", required=false) User currentUser, Estate newEstate) {
		if (currentUser!=null && currentUser.getRole().equals(UserType.ADMIN.toString())) {
			modelAndView.addObject("newEstate", newEstate);
			modelAndView.setViewName("admin");
		} else {
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	@GetMapping("/gohandle")
	public ModelAndView goAdminHandlePage(ModelAndView modelAndView, @SessionAttribute (name="currentUser", required=false) User currentUser) {
		if (currentUser!=null && currentUser.getRole().equals(UserType.ADMIN.toString())) {
			modelAndView.setViewName("redirect:/admin/handle");
		} else {
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	
	@GetMapping("/goall")
	public ModelAndView goAdminAllPage(ModelAndView modelAndView, @SessionAttribute (name="currentUser", required=false) User currentUser) {
		if (currentUser!=null && currentUser.getRole().equals(UserType.ADMIN.toString())) {
			modelAndView.setViewName("redirect:/admin/all");
		} else {
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	
	@GetMapping("/goacconts")
	public ModelAndView goAdminDirectionPage(ModelAndView modelAndView, @SessionAttribute (name="currentUser", required=false) User currentUser) {
		if (currentUser!=null && currentUser.getRole().equals(UserType.ADMIN.toString())) {
			modelAndView.setViewName("redirect:/admin/acconts");
		} else {
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	@PostMapping(params="/goaddnew")
	public ModelAndView addEstateByHand(ModelAndView modelAndView, @SessionAttribute(name="currentUser", required=false) User currentUser, Estate newEstate) {
		if (currentUser.getRole().equals(UserType.ADMIN.toString())) {
			newEstate.setStatusType(EstateStatusType.PUBLICATION.toString());
			newEstate.setDate(LocalDateTime.now());
			modelAndView.setViewName("redirect:admin");
		} else {
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
}
