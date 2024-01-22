package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotFoundSurveyException extends AbsValidationException {

	public NotFoundSurveyException() {
		super(VALIDATION.NOT_FOUND_SURVEY);
	}

}

