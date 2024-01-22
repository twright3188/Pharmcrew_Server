package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class InvalidMemberException extends AbsValidationException {

	public InvalidMemberException() {
		super(VALIDATION.INVALID_MEMBER);
	}

}

