package kr.ant.kpa.pharmcrew.type.news;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MOVE {
    WU("웹", "Web URL"),
    NO("공지사항", "공지사항 상세화면 ID"),	// Notice
    NQ("공지문의", "Notice Qna"),
    ED("교육", "교육 상세화면 ID"),			// Education
    EQ("교육문의", "Education Qna"),
    SU("설문", "설문 상세화면 ID"),			// Survey
    PT("팜텍스", "Pharmtax notice"),
    PQ("팜텍스공지문의", "Pharmtax notice Qna")
    
    ;

    private String name;
    private String desc;
}
