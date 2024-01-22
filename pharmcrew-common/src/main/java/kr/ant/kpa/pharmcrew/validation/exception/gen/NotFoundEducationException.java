package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotFoundEducationException extends AbsValidationException {

	public NotFoundEducationException() {
		super(VALIDATION.NOT_FOUND_EDUCATION);
	}

}

