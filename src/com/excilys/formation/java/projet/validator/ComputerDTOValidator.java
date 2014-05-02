package com.excilys.formation.java.projet.validator;

import com.excilys.formation.java.projet.dto.ComputerDTO;

import java.util.regex.*;

public class ComputerDTOValidator {

	private int codeError;
	public ComputerDTOValidator() {
		setCodeError(0);
	}
	
	public int getCodeError() {
		return codeError;
	}

	private void setCodeError(int codeError) {
		this.codeError = codeError;
	}

	public int testComputerDTO(ComputerDTO dto){
		ValidatorMessage vm = new ValidatorMessage();
		testName(dto.getName(), 0);                                               
		if(testDate(dto.getIntroduced(), 1) & testDate(dto.getDiscontinued(), 2))  
			testDateBeforeDate(dto.getIntroduced(), dto.getDiscontinued(), 3);        
		testName(dto.getCompany(), 4);     
		vm.setCodeError(codeError);
		return codeError;
	}
	
	private void testDateBeforeDate(String introduced, String discontinued, int i) {
		boolean valid = true;
		if(!valid) {
			setCodeError(getCodeError() + (2^i));
		}
		
	}
	private boolean testDate(String date, int i) {
		boolean valid = Pattern.matches("^(19|20)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", date);
		if(!valid) {
			setCodeError(getCodeError() + (2^i));
		}
		return valid;
	}
	private void testName(String name, int i) {
		boolean valid = true;
		if( name!=null && !("".equals(name))) valid = Pattern.matches("([0-9]|[a-zA-Z]|[-_ ]){2,20}", name);
		else valid = false;
		if(!valid) {
			setCodeError(getCodeError() + (2^i));
		}
	}
	
}
