package kr.ant.kpa.pharmcrew.sms.exception;

import lombok.Data;

@Data
public class SmsException extends Exception {
    private String message;
}
