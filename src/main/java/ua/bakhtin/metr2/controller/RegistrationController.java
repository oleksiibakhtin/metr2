package ua.bakhtin.metr2.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ua.bakhtin.metr2.model.User;
import ua.bakhtin.metr2.service.UserService;


@Controller
@RequestMapping("/registration")
@SessionAttributes("currentUser")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	public RegistrationController () {}


	@GetMapping
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, @ModelAttribute ("registeringUser") User registeringUser) {
		modelAndView.addObject(registeringUser);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@PostMapping // ПОРЯДОК ИМЕЕТ ЗНАЧЕНИЕ!!! BindingResult должен стоять непосредственно после аргумента с @Valid
	public ModelAndView checkRegistrationForm(ModelAndView modelAndView, @ModelAttribute("currentUser") User currentUser, @Valid @ModelAttribute ("registeringUser") User registeringUser, BindingResult bindingResult) {
		if(!bindingResult.hasErrors()) {
			userService.saveUser(registeringUser);
			currentUser = userService.findByEmail(registeringUser.getEmail());
			modelAndView.addObject("currentUser", currentUser);
			modelAndView.setViewName("cabinet");
		} else {
		modelAndView.addObject(registeringUser);
		modelAndView.setViewName("registration");
		}
		return modelAndView;
	}
}