package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class InvalidAccountException extends AbsValidationException {

	public InvalidAccountException() {
		super(VALIDATION.INVALID_ACCOUNT);
	}

}

