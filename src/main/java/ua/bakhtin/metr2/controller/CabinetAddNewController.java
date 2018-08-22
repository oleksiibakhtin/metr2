package ua.bakhtin.metr2.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ua.bakhtin.metr2.enums.EstateStatusType;
import ua.bakhtin.metr2.model.Estate;
import ua.bakhtin.metr2.model.Types;
import ua.bakhtin.metr2.model.User;
import ua.bakhtin.metr2.service.EstateService;

@Controller
//@Validated // @Validated - над классом для валидации @RequestParam и @PathVariable!!!!!!!
@SessionAttributes("currentUser")
@RequestMapping("/cabinet/addnew")
public class CabinetAddNewController {

@Autowired
private EstateService estateService;

public CabinetAddNewController() {}
	
	@GetMapping
	public ModelAndView showCabinetAddNew(
											ModelAndView modelAndView, 
											@SessionAttribute (name="currentUser", required=false) User currentUser, 
											Types types, 
											@ModelAttribute ("newEstate") Estate newEstate
										) {
		if (currentUser != null) {
			modelAndView.addObject("newEstate", newEstate);
			modelAndView.addObject("types", types);
			modelAndView.setViewName("cabinet-add-new");
		} else {
			modelAndView.setViewName("redirect:/");
		}
		
		return modelAndView;
	}
	
	@PostMapping
	public ModelAndView addNewEstate( //порядок аргументов имеет значение????
			ModelAndView modelAndView,
			Types types,
			@Valid
			@ModelAttribute ("newEstate") Estate newEstate,
			BindingResult newEstateBindingResult,
			@RequestParam("photos") MultipartFile[] estatePhotos,
			@SessionAttribute(name="currentUser", required=false) User currentUser
			) {
		System.out.println("newEstate.getId(): " + newEstate.getId());
		
		if(!newEstateBindingResult.hasErrors()) {
		newEstate.setDate(LocalDateTime.now());
		if (estateService.getEstateById(newEstate.getId()) != null) {
			System.out.println("Обновление существующего");
			estateService.updateEstate(newEstate);
		} else {
			System.out.println("Создание нового");
			newEstate.setStatusType(EstateStatusType.APPLICATION.toString());
			estateService.saveEstate(newEstate, currentUser, estatePhotos);
		}
		modelAndView.setViewName("redirect:/cabinet");
		return modelAndView;
		}
			modelAndView.addObject("types", types);
			modelAndView.addObject(newEstate);
			modelAndView.setViewName("cabinet-add-new");
		return modelAndView;
	}
	
}
