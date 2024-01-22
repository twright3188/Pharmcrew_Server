package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotDeletablePartnersException extends AbsValidationException {

	public NotDeletablePartnersException() {
		super(VALIDATION.NOT_DELETABLE_PARTNERS);
	}

}

