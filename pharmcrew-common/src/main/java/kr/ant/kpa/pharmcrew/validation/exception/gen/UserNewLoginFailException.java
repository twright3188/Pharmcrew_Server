package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class UserNewLoginFailException extends AbsValidationException {

	public UserNewLoginFailException() {
		super(VALIDATION.USER_NEWLOGIN_FAIL);
	}

}

