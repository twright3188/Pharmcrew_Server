package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotFoundAuthCodeException extends AbsValidationException {

	public NotFoundAuthCodeException() {
		super(VALIDATION.NOT_FOUND_AUTH_CODE);
	}

}

