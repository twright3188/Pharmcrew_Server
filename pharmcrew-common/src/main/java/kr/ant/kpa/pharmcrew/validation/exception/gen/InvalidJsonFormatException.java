package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class InvalidJsonFormatException extends AbsValidationException {

	public InvalidJsonFormatException() {
		super(VALIDATION.INVALID_JSON_FORMAT);
	}

}

