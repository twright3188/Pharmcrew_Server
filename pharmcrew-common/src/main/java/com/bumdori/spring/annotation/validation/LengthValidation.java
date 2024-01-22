package com.bumdori.spring.annotation.validation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(PARAMETER)
public @interface LengthValidation {

	int min() default -1;
	int max() default -1;
}
