package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotEduStartTimeException extends AbsValidationException {

	public NotEduStartTimeException() {
		super(VALIDATION.NOT_EDU_START_TIME);
	}

}

