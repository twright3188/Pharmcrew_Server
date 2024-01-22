package kr.ant.kpa.pharmcrew.type.news;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QNA_TARGET {
    N("공지", "Notice"),
    E("교육", "Education"),
    ;

    private String name;
    private String desc;
}
