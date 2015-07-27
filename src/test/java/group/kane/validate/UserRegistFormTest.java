package group.kane.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

public class UserRegistFormTest {
	@Test
	public void testValid() throws Exception {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        
        UserRegistForm form = new UserRegistForm();
        form.setUserName("12345678901234567890");
        form.setEmailAddress("email@address.com");
        form.setPassword("pass1A");
        form.setConfirmPassword("pass1A");
        
        Set<ConstraintViolation<UserRegistForm>> validate = validator.validate(form);
        validate.stream().forEach(System.out::println);
        
	}
	

}
