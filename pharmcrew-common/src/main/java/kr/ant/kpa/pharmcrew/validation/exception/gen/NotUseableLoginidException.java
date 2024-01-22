package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotUseableLoginidException extends AbsValidationException {

	public NotUseableLoginidException() {
		super(VALIDATION.NOT_USEABLE_LOGINID);
	}

}

