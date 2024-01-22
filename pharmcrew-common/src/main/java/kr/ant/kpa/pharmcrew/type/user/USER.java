package kr.ant.kpa.pharmcrew.type.user;

import lombok.AllArgsConstructor;

/**
 * 사용자 구분
 */
@AllArgsConstructor
public enum USER {
    /**
     * 약사
     */
    M("(약사회) 회원", "Member"),
    ;

    private String name;
    private String desc;
}
