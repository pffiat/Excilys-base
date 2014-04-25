package com.excilys.formation.java.projet.validator;

public class ValidatorMessage {

	private boolean valid;
	private int codeError;
	public ValidatorMessage() {
		valid = true;
		setCodeError(0);
	}
	
	public boolean isValid() {
		if(codeError != 0) valid = false;
		return valid;
	}
	public void setValid(int codeError) {
		if(codeError != 0) valid = false;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public int getCodeError() {
		return codeError;
	}

	public void setCodeError(int codeError) {
		setValid(codeError);
		this.codeError = codeError;
	}
	

}
