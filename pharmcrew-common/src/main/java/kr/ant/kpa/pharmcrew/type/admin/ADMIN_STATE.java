package kr.ant.kpa.pharmcrew.type.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 관리자 상태
 */
@Getter
@AllArgsConstructor
public enum ADMIN_STATE {
    /**
     * 일반
     */
    N("일반", "Normal"),
    S("중지", "Stop"),
    ;

    private String name;
    private String desc;
}
