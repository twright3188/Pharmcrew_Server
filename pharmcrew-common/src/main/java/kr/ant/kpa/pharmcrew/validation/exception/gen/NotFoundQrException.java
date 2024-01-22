package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotFoundQrException extends AbsValidationException {

	public NotFoundQrException() {
		super(VALIDATION.NOT_FOUND_QR);
	}

}

