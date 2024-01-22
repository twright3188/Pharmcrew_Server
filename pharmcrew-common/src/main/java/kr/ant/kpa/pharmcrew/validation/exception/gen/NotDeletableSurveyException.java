package kr.ant.kpa.pharmcrew.validation.exception.gen;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import kr.ant.kpa.pharmcrew.validation.exception.AbsValidationException;

public class NotDeletableSurveyException extends AbsValidationException {

	public NotDeletableSurveyException() {
		super(VALIDATION.NOT_DELETABLE_SURVEY);
	}

}

