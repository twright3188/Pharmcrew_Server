package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotFoundPtaxnoticeException extends AbsValidationException {

	public NotFoundPtaxnoticeException() {
		super(VALIDATION.NOT_FOUND_PTAXNOTICE);
	}

}

