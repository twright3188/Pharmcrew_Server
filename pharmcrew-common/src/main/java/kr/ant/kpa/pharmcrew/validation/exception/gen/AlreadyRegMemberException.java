package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class AlreadyRegMemberException extends AbsValidationException {

	public AlreadyRegMemberException() {
		super(VALIDATION.ALREADY_REG_MEMBER);
	}

}

