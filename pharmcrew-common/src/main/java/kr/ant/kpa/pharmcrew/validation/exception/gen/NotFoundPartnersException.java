package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotFoundPartnersException extends AbsValidationException {

	public NotFoundPartnersException() {
		super(VALIDATION.NOT_FOUND_PARTNERS);
	}

}

