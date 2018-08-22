package ua.bakhtin.metr2.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.bakhtin.metr2.enums.EstateStatusType;
import ua.bakhtin.metr2.enums.UserType;
import ua.bakhtin.metr2.model.Estate;
import ua.bakhtin.metr2.model.SearchingCondition;
import ua.bakhtin.metr2.model.Types;
import ua.bakhtin.metr2.model.User;
import ua.bakhtin.metr2.service.EstateService;
import ua.bakhtin.metr2.service.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes("currentUser")
public class HomeController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private EstateService estateService;
	
	private Long pagesCount = 1L;
	private static int ITEMS_ON_PAGE = 5;
	private List<Estate> allPublishedEstatesList;
	private List<Estate> foundEstates;
	
	public HomeController() {}
	
	@ModelAttribute("currentUser")
	public static final User setCurrentUser () {
		System.out.println("setCurrentUser ()");
			return new User();
		}
	
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/")
	public ModelAndView showHomePage (
										ModelAndView modelAndView, 
										@ModelAttribute ("searchingCondition") SearchingCondition searchingCondition, 
										Integer pageNum, Types types
									) {
		
		if(searchingCondition.getOperationType() == null) {
			foundEstates = null;
			long estatesCount = estateService.countByStatusType (EstateStatusType.PUBLICATION.toString());
			if(allPublishedEstatesList == null || allPublishedEstatesList.size() != estatesCount) {
				allPublishedEstatesList = estateService.getPublishedSortedEstates(EstateStatusType.PUBLICATION.toString());
			}
			modelAndView.addObject("estateList", allPublishedEstatesList.subList(0, (int) (estatesCount > ITEMS_ON_PAGE ? ITEMS_ON_PAGE : (estatesCount != 0 ? estatesCount : 0))));
	        pagesCount = estatesCount <= ITEMS_ON_PAGE ? 1 : (estatesCount%ITEMS_ON_PAGE == 0 ? estatesCount/ITEMS_ON_PAGE : estatesCount/ITEMS_ON_PAGE+1);
	        modelAndView.addObject("searchingCondition", new SearchingCondition());
		} else {
			System.out.println("Режим поиска");
			foundEstates = estateService.getSearchingSortedEstates(searchingCondition);
			long foundEstatesCount = foundEstates.size();
			pagesCount = foundEstatesCount <= ITEMS_ON_PAGE ? 1 : (foundEstatesCount%ITEMS_ON_PAGE == 0 ? foundEstatesCount/ITEMS_ON_PAGE : foundEstatesCount/ITEMS_ON_PAGE+1);
			modelAndView.addObject("estateList", foundEstates.subList(0, (int) (foundEstatesCount > ITEMS_ON_PAGE ? ITEMS_ON_PAGE : (foundEstatesCount != 0 ? foundEstatesCount : 0))));
			modelAndView.addObject("searchingCondition", searchingCondition);
		}
		
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("authorizingUser", new User());
        modelAndView.addObject("types", types);
		modelAndView.setViewName("home");
		
		return modelAndView;
	}
	
	// ---------------------------------------------------------------------------------------------------------------------------------------------
		@PostMapping("/search")
		public ModelAndView search (
									ModelAndView modelAndView, 
									@ModelAttribute ("searchingCondition") SearchingCondition searchingCondition, 
									Types types, 
									RedirectAttributes redirectAttributes
									) {
			
			redirectAttributes.addFlashAttribute("searchingCondition", searchingCondition);
			modelAndView.setViewName("redirect:/");
			return modelAndView;	
		}
	
	// ---------------------------------------------------------------------------------------------------------------------------------------------
		@GetMapping("/{pageNum}")
		public ModelAndView showHomePageNumber (
			ModelAndView modelAndView,
			User authorizingUser,
			@PathVariable Integer pageNum,
			@ModelAttribute ("searchingCondition") SearchingCondition searchingCondition,
			Types types
			) {
        
		long estatesCount;
		if (foundEstates == null) {
			estatesCount = estateService.countByStatusType (EstateStatusType.PUBLICATION.toString());
	        if(allPublishedEstatesList == null || allPublishedEstatesList.size() != estatesCount) {
				allPublishedEstatesList = estateService.getPublishedSortedEstates(EstateStatusType.PUBLICATION.toString());
			}
	        Integer getFrom = pageNum*ITEMS_ON_PAGE-ITEMS_ON_PAGE;
	        Integer getTo = (int) (pageNum*ITEMS_ON_PAGE > estatesCount ? estatesCount : pageNum*ITEMS_ON_PAGE);
	        		
	        modelAndView.addObject("estateList", allPublishedEstatesList.subList(getFrom, getTo));
	        
		} else {
			estatesCount=foundEstates.size();
			Integer getFrom = pageNum*ITEMS_ON_PAGE-ITEMS_ON_PAGE;
	        Integer getTo = (int) (pageNum*ITEMS_ON_PAGE > estatesCount ? estatesCount : pageNum*ITEMS_ON_PAGE);
	        modelAndView.addObject("estateList", foundEstates.subList(getFrom, getTo));
		}
		
		pagesCount = estatesCount <= ITEMS_ON_PAGE ? 1 : (estatesCount%ITEMS_ON_PAGE == 0 ? estatesCount/ITEMS_ON_PAGE : estatesCount/ITEMS_ON_PAGE+1);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("authorizingUser", new User());
        modelAndView.addObject("searchingCondition", searchingCondition);
        modelAndView.addObject("types", types);
        modelAndView.setViewName("home");
		
		return modelAndView;
	}
	
	// ---------------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/") //авторизация
	public ModelAndView authorization(ModelAndView modelAndView, @ModelAttribute("currentUser") User currentUser, User authorizingUser, Types types) {
		
		currentUser = userService.findByEmail(authorizingUser.getEmail());
		if (currentUser == null) {
			currentUser = userService.findByPhone(authorizingUser.getEmail());
		}
		
		if (currentUser != null && currentUser.getPassword().equals(authorizingUser.getPassword())) {
			if (currentUser.getRole().equals(UserType.ADMIN.toString())) {
				modelAndView.setViewName("admin");
			} else {
			List <Estate> myEstateSet = estateService.getEstatesByUserId(currentUser.getId());
			modelAndView.addObject("myEstateSet", myEstateSet);
			modelAndView.addObject("types", types);
			modelAndView.setViewName("cabinet");
			}
		} else {
			modelAndView.setViewName("redirect:/");
		}
		modelAndView.addObject("currentUser", currentUser);
		return modelAndView;
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/goregistration") //просто переход к регистрации
	public ModelAndView goRegistationPage(ModelAndView modelAndView) {
		modelAndView.setViewName("redirect:/registration");
		return modelAndView;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------
	@GetMapping ("/doexit") // выход
	public ModelAndView exit(ModelAndView modelAndView, SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------
	@GetMapping("/gocabinet")
	public ModelAndView goCabinetPage(ModelAndView modelAndView, @SessionAttribute(name="currentUser", required=false) User currentUser) {
		if (currentUser != null) {
		if (currentUser.getRole().equals("admin")) {
			modelAndView.setViewName("redirect:/admin");
		} else {
		modelAndView.setViewName("redirect:/cabinet");
		}
		} else {
			modelAndView.setViewName("redirect:/");
		}
		
		return modelAndView;
	}
	
	
}
