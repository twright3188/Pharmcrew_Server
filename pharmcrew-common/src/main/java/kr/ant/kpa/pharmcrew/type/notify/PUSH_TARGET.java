package kr.ant.kpa.pharmcrew.type.notify;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 푸시 타겟 타입<br>
 *     클라이언트에서 탭 시 이동해야 할 타겟
 */
@Getter
@AllArgsConstructor
public enum PUSH_TARGET {
    /**
     * 테스트
     */
    T_("테스트", "Test"),
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
