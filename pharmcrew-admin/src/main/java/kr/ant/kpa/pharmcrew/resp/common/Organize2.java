package kr.ant.kpa.pharmcrew.resp.common;

import com.bumdori.spring.annotation.Description;
import lombok.Data;

import java.util.List;

@Data
public class Organize2 {
    /**
     * 아이디 리스트
     */
    @Description("아이디 리스트")
    private List<Long> ids;
    /**
     * 이름 리스트
     */
    @Description("이름 리스트")
    private List<String> names;
}
