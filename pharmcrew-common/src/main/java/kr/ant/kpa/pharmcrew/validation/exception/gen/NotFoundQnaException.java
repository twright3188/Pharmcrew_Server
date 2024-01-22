package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotFoundQnaException extends AbsValidationException {

	public NotFoundQnaException() {
		super(VALIDATION.NOT_FOUND_QNA);
	}

}

