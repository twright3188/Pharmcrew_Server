package kr.ant.kpa.pharmcrew.type.user;

import lombok.AllArgsConstructor;

/**
 * 관리자 상태
 */
@AllArgsConstructor
public enum ADMIN_STATE {
    /**
     * 정상
     */
    N("정상", "Normal"),
    ;

    private String name;
    private String desc;
}
