package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotFoundNoticeException extends AbsValidationException {

	public NotFoundNoticeException() {
		super(VALIDATION.NOT_FOUND_NOTICE);
	}

}

