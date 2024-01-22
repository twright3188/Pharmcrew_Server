package kr.ant.kpa.pharmcrew.type.notify;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 푸시 타겟 카테고리<br>
 *     마케팅 수신 동의/푸시 on/off 등 설정에 따라 보내지 않아야 한다.
 */
@Getter
@AllArgsConstructor
public enum PUSH_CATEGORY {
    /**
     * 광고
     */
    AD("광고", "Advertise"),
    NT("알림", "Notice"),
    PT("팜텍스", "PharmTax")
    ;

    private String name;
    private String desc;
}
