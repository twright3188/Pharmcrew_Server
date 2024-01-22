package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class AlreadyProgressEduException extends AbsValidationException {

	public AlreadyProgressEduException() {
		super(VALIDATION.ALREADY_PROGRESS_EDU);
	}

}

