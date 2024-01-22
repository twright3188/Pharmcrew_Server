package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotFoundPtaxqnaException extends AbsValidationException {

	public NotFoundPtaxqnaException() {
		super(VALIDATION.NOT_FOUND_PTAXQNA);
	}

}

