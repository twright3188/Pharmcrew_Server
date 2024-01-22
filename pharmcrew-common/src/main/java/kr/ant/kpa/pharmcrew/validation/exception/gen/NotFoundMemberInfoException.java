package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotFoundMemberInfoException extends AbsValidationException {

	public NotFoundMemberInfoException() {
		super(VALIDATION.NOT_FOUND_MEMBER_INFO);
	}

}

