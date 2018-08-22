package ua.bakhtin.metr2.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import ua.bakhtin.metr2.service.UserService;

public class UniquePhoneValidator implements ConstraintValidator<UniquePhone, String>{
	
	@Autowired
	private UserService userService;
	
	public UniquePhoneValidator() {}

	@Override
    public void initialize(UniquePhone unique) {
    }
	
	@Override
	public boolean isValid(String phone, ConstraintValidatorContext context) {
		if (userService == null) { // да, знаю, что наколхозил
    		return true;
    	}
		return phone != null && userService.findByPhone(phone) == null;
	}

}
