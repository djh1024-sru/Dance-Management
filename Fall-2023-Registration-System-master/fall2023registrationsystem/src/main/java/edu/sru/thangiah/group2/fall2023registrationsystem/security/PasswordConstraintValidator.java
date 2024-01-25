package edu.sru.thangiah.group2.fall2023registrationsystem.security;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
        // set up password validator with length rule (at least 8 characters)
		PasswordValidator validator = new PasswordValidator(Arrays.asList(new LengthRule(4, 256)));

		// validate password
		RuleResult result = validator.validate(new PasswordData(password));

		if (result.isValid()) {
			return true;
		}

        // if password is invalid, collect error messages and customize the constraint violation message
		List<String> messages = validator.getMessages(result);
		String messageTemplate = messages.stream().collect(Collectors.joining(","));
		context.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation()
				.disableDefaultConstraintViolation();
		return false;
	}
}
