package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class UntreatedExceptionException extends AbsValidationException {

	public UntreatedExceptionException() {
		super(VALIDATION.UNTREATED_EXCEPTION);
	}

}

