package kr.ant.kpa.pharmcrew.type.survey;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 설문 문제 타입<br>
 *     주관식 객관식 타입 정
 */
@Getter
@AllArgsConstructor
public enum SURVEY_ANSWER {
    /**
     * 객관식
     */
	O("객관식", "objective"),
	/**
	 * 주관식
	 */
	S("주관식", "subjective"),
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
