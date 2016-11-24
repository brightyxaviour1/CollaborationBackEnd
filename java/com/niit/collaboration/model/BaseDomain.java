package com.niit.collaboration.model;

import org.springframework.stereotype.Component;
import javax.persistence.Transient;

@Component
public class BaseDomain {

	@Transient
	public String errorCodes;

	@Transient
	public String errorMessage;

	public String getErrorCodes() {
		return errorCodes;
	}

	public void setErrorCodes(String errorCodes) {
		this.errorCodes = errorCodes;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
