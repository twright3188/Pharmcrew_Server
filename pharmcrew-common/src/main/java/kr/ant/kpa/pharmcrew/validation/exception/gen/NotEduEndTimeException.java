package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotEduEndTimeException extends AbsValidationException {

	public NotEduEndTimeException() {
		super(VALIDATION.NOT_EDU_END_TIME);
	}

}

