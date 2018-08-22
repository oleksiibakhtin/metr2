package ua.bakhtin.metr2.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.bakhtin.metr2.enums.EstateStatusType;
import ua.bakhtin.metr2.model.Estate;
import ua.bakhtin.metr2.model.Types;
import ua.bakhtin.metr2.model.User;
import ua.bakhtin.metr2.service.EstateService;

@Controller
@RequestMapping("/cabinet")
@SessionAttributes("currentUser")
public class CabinetController {
	
	private EstateService estateService;
	
	public CabinetController() {}
	
	@Autowired
	public CabinetController(EstateService estateService){
		this.estateService = estateService;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
	@GetMapping
	public ModelAndView goCabinetPage(
										ModelAndView modelAndView, 
										@SessionAttribute(name="currentUser", required=false) User currentUser, 
										Types types
									) {
		
		if (currentUser != null) {
			List <Estate> myEstateSet = estateService.getEstatesByUserId(currentUser.getId());
			modelAndView.addObject("myEstateSet", myEstateSet);
			modelAndView.addObject("types", types);
			modelAndView.setViewName("cabinet");
		} else {
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping ("/edit")
	public ModelAndView editEstate (
									ModelAndView modelAndView,
									Types types, 
									@ModelAttribute ("estate") @Valid Estate editingEstate, 
									BindingResult bindingResult,
									RedirectAttributes redirectAttributes
									) {
		
		if(!bindingResult.hasErrors() && editingEstate.getStatusType().equals(EstateStatusType.APPLICATION.toString())) {
			System.out.println("В КОНТРОЛЛЕРЕ editingEstate в ТОПе = " + editingEstate.getInTop());
			estateService.updateEstate(editingEstate);
			modelAndView.setViewName("redirect:/cabinet");
		} else {
			redirectAttributes.addFlashAttribute("newEstate", editingEstate);
			modelAndView.setViewName("redirect:/cabinet/addnew");
		}
		return modelAndView;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	@GetMapping ("/delete-estate/{id}")
	public ModelAndView deleteEstate (
										ModelAndView modelAndView, 
										@SessionAttribute(name="currentUser", required=false) User currentUser, 
										@PathVariable("id") Long id
									) {
		if (currentUser != null && estateService.getEstateById(id).getUser().getId() == currentUser.getId()) {
			estateService.deleteEstate(id);
			modelAndView.setViewName("redirect:/cabinet");
		} else {
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/go-add-new-estate")
	public ModelAndView goCabinetAddNewEstate(
												ModelAndView modelAndView, 
												@SessionAttribute("currentUser") User currentUser
											) {
		if (currentUser.getName() != null) {
			modelAndView.setViewName("redirect:/cabinet/addnew");
		} else {
			modelAndView.setViewName("redirect:/");
		}
		return modelAndView;
	}
	
}
