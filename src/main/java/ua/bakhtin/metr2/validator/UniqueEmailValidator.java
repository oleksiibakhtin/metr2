package ua.bakhtin.metr2.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ua.bakhtin.metr2.service.UserService;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{
	
	@Autowired
	private UserService userService;
	
	public UniqueEmailValidator() {}
	
	@Override
    public void initialize(UniqueEmail uniqueEmail) {
    }
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (userService == null) { // да, знаю, что наколхозил
    		return true;
    	}
		return email != null && userService.findByEmail(email) == null;
	}

}
