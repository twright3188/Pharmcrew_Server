package kr.ant.kpa.pharmcrew.sms;

import kr.ant.kpa.pharmcrew.sms.exception.SmsException;

public interface SmsSender {
    void send(String to, String body) throws SmsException;
}
