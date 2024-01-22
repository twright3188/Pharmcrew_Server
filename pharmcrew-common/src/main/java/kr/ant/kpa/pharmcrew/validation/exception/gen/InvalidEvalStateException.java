package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class InvalidEvalStateException extends AbsValidationException {

	public InvalidEvalStateException() {
		super(VALIDATION.INVALID_EVAL_STATE);
	}

}

