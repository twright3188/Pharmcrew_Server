package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class UserLoginFailException extends AbsValidationException {

	public UserLoginFailException() {
		super(VALIDATION.USER_LOGIN_FAIL);
	}

}

