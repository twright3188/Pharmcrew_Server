package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class InvalidQrStartException extends AbsValidationException {

	public InvalidQrStartException() {
		super(VALIDATION.INVALID_QR_START);
	}

}

