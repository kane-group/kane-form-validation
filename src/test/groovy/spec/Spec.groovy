package spec

import group.kane.validate.UserRegistForm

import javax.validation.Validation
import javax.validation.Validator

import spock.lang.Specification
import spock.lang.Unroll;

class Spec extends Specification {

	@Unroll
	def "name = #name, email = #email, pass = #pass, confirmPass = #cPassのとき invalid数 = #num"() {
		
		setup:
		def validator = Validation.buildDefaultValidatorFactory().getValidator()
		def form = new UserRegistForm(userName:name, emailAddress:email, password:pass, confirmPassword:cPass)

		expect:
		validator.validate(form).size() == num

		where:
		name                    | email        | pass  | cPass || num
		"12345678901234567890"  | "e@mail.com" | "Pa2" | "Pa2" || 0
		"123456789012345678901" | "e@mail.com" | "Pa2" | "Pa2" || 1
		"12345678901234567890"  | "e_mail.com" | "Pa2" | "Pa2" || 1
		"12345678901234567890"  | "e@mail.com" | "Pa1" | "Pa2" || 1
		"12345678901234567890"  | "e@mail.com" | "Pas" | "Pas" || 1
		""                      | ""           |  ""   | ""    || 3
		null                    | null         |  null | null  || 3
	}
}
