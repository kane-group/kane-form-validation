package group.kane.validate;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
@Password
public class UserRegistForm {
	
	@UserName
	String userName;

	@EmailAddress
	String emailAddress;
	
	String password;
	String confirmPassword;
}
