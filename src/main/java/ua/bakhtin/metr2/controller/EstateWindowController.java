package ua.bakhtin.metr2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ua.bakhtin.metr2.model.Estate;
import ua.bakhtin.metr2.service.EstateService;

@Controller
@RequestMapping(value="/estate-window")

public class EstateWindowController {
	
	@Autowired
	private EstateService estateService;
	
	public EstateWindowController () {}
	
	public EstateService getEstateService() {
		return estateService;
	}
	
	public void setEstateService(EstateService estateService) {
		this.estateService = estateService;
	}
	
	@GetMapping (value="/{id}")
	public ModelAndView showEsateWindow (ModelAndView modelAndView, @PathVariable(required=true, name="id") Long id) {
		Estate showingEstate = estateService.getEstateById(id);
		modelAndView.addObject("showingEstate", showingEstate);
		modelAndView.setViewName("estate-window");
		System.out.println("id " + id);
		return modelAndView;
	}
	
}
