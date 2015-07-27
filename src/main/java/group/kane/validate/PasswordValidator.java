package group.kane.validate;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordValidator implements ConstraintValidator<Password, Object> {
	private String errorMessage; 
    private String password;
    private String confirmPassword;
    
    @Override
    public void initialize(final Password constraintAnnotation) {
    	errorMessage = constraintAnnotation.message(); 
        password = constraintAnnotation.password();
        confirmPassword = constraintAnnotation.confirmPassword();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context){
        boolean isValid = false;
        try {
        	String password = getValue(value, this.password);
        	String confirmPassword = getValue(value, this.confirmPassword);

            isValid = isValidFormat(password) && isSame(password, confirmPassword);
        } catch (final Exception ignore) {
        	// ignore
        }
        
        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context
            	.buildConstraintViolationWithTemplate(errorMessage)
            	.addPropertyNode(password)
            	.addBeanNode()
            	.addConstraintViolation();
        }
        return isValid;
    }

	private boolean isValidFormat(String password) {
		String checkFormat = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])";
		return Pattern.compile(checkFormat).matcher(password).find();
	}

	private boolean isSame(String password, String confirmPassword) {
		return password == null && confirmPassword == null || password != null && password.equals(confirmPassword);
	}

	private String getValue(Object obj, String fieldName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = obj.getClass();
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		return (String) field.get(obj);
	}
}