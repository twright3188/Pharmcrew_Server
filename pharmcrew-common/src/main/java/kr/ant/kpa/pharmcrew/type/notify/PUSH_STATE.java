package kr.ant.kpa.pharmcrew.type.notify;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 푸시 상태
 */
@Getter
@AllArgsConstructor
public enum PUSH_STATE {
    /**
     * 미전송
     */
    R("미전송", "Reserve"),
    /**
     * 전송
     */
    S("전송", "Send"),
    ;

    /**
     * 이름
     */
    private String name;
    /**
     * 설명
     */
    private String desc;
}
