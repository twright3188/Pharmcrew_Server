package kr.ant.kpa.pharmcrew.resp.academy;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.common.AttachFile;
import kr.ant.kpa.pharmcrew.resp.common.Organize2;
import lombok.Data;

import java.io.Serializable;

@Data
public class Doc implements Serializable {
    /**
     * 문서 ID
     */
    @Description("문서 ID")
    private Long docId;
    /**
     * 조직
     */
    @Description("조직")
    private Organize2 organize;
    /**
     * 제목
     */
    @Description("제목")
    private String title;
    /**
     * 내용
     */
    @Description("내용")
    private String body;
    /**
     * 문서 파일
     */
    @Description("문서 파일")
    private AttachFile docFile;
    /**
     * 노출 여부<br>
     *     Y: 노출, N: 비노출
     */
    @Description("노출 여부<br>" +
            "Y: 노출, N: 비노출")
    private String openYn;
    /**
     * 조회 수
     */
    @Description("조회 수")
    private Long viewCnt;
    /**
     * 등록자 명
     */
    @Description("등록자 명")
    private String regName;
    /**
     * 등록 일시
     */
    @Description("등록 일시")
    private Long regDt;
}
