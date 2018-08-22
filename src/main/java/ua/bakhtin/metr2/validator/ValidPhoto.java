package ua.bakhtin.metr2.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target ({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention (RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhotoValidator.class)
public @interface ValidPhoto {
	String message() default "Фотография не соответствует требованиям";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}