package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class InvalidAccountStateException extends AbsValidationException {

	public InvalidAccountStateException() {
		super(VALIDATION.INVALID_ACCOUNT_STATE);
	}

}

