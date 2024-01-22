package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotFoundMemberNotiException extends AbsValidationException {

	public NotFoundMemberNotiException() {
		super(VALIDATION.NOT_FOUND_MEMBER_NOTI);
	}

}

