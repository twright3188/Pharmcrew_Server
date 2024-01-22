package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class AlreadyQrStartException extends AbsValidationException {

	public AlreadyQrStartException() {
		super(VALIDATION.ALREADY_QR_START);
	}

}

