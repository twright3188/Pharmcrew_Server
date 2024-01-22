package com.bumdori.spring.annotation.validation.regex;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(PARAMETER)
public @interface HandphoneValidation {
    String regex() default "^01(?:0|1[6-9])(?:\\d{3}|\\d{4})\\d{4}$";
}
