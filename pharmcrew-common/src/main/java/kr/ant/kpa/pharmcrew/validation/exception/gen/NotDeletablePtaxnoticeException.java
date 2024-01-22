package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotDeletablePtaxnoticeException extends AbsValidationException {

	public NotDeletablePtaxnoticeException() {
		super(VALIDATION.NOT_DELETABLE_PTAXNOTICE);
	}

}

