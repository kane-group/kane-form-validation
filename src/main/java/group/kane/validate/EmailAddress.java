package group.kane.validate;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Documented
@Constraint(validatedBy = { })
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@NotEmpty
@Email
public @interface EmailAddress {

	String message() default "{validation.EmailAddress.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({ FIELD, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
    	EmailAddress[] value();
    }
}