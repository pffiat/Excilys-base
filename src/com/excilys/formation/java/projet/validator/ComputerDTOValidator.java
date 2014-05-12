package com.excilys.formation.java.projet.validator;

import com.excilys.formation.java.projet.dto.ComputerDto;

import java.util.Date;
import java.util.regex.*;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ComputerDTOValidator implements Validator{

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return ComputerDto.class.isAssignableFrom(clazz);
	}


	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
		ComputerDto computerDto = (ComputerDto) target;

		if (testDate(computerDto.getIntroduced())) {
			if (testDate(computerDto.getDiscontinued())) {
				if( ! testDateBeforeDate(computerDto.getIntroduced(), computerDto.getDiscontinued() )){
					errors.reject("introduced.after.discontinued");
				}
			} else {
				errors.rejectValue("introducedDate", "not.a.date");
			}
		} else {
			errors.rejectValue("introducedDate", "not.a.date"); 
			if ( ! testDate(computerDto.getDiscontinued())) {
				errors.rejectValue("introducedDate", "not.a.date");
			}			
		}
		
		if (computerDto.getCompany_id() < 0) {
			errors.rejectValue("companyId", "negativevalue");
		} 
		else if (computerDto.getCompany_id() > 43) {
			errors.rejectValue("companyId", "too.much");
		}



	}

	public ComputerDTOValidator() {}

	@SuppressWarnings("deprecation")
	private boolean testDateBeforeDate(String introduced, String discontinued) {
		boolean valid = false;
		if(new Date(introduced).getTime() < new Date(discontinued).getTime()) {
			valid = true;
		}
		return valid;
	}

	private boolean testDate(String date) {
		boolean valid = Pattern.matches("^(19|20)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", date);
		return valid;
	}

}
