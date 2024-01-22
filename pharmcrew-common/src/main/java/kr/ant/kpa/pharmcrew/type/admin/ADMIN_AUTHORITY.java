package kr.ant.kpa.pharmcrew.type.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 관리자 권한
 */
@Getter
@AllArgsConstructor
public enum ADMIN_AUTHORITY {
    S("슈퍼 관리자", "Super Admin"),
    A("관리자", "Admin"),
    O("조직 관리자", "Organize")
    ;

    private String name;
    private String desc;
}
