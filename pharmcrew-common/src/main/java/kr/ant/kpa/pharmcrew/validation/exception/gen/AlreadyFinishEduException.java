package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class AlreadyFinishEduException extends AbsValidationException {

	public AlreadyFinishEduException() {
		super(VALIDATION.ALREADY_FINISH_EDU);
	}

}

