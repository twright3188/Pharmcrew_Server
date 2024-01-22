package kr.ant.kpa.pharmcrew.type.education;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EDUCATION_AUTH {
    A("교육 전체", "All"),
    P("강의 개별", ""),
    ;

    private String name;
    private String desc;
}
