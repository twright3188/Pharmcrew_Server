package kr.ant.kpa.pharmcrew.resp.news;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.common.AttachFile;
import kr.ant.kpa.pharmcrew.resp.common.Organize2;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Notice implements Serializable {
    /**
     * 공지 ID
     */
    @Description("공지 ID")
    private Long noticeId;
//    /**
//     * 조직 ID 리스트
//     */
//    @Description("조직 ID 리스트")
//    private List<Long> organizeIds;
//    /**
//     * 조직명
//     */
//    @Description("조직명")
//    private String organizeName;
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
     * 노출 여부<br>
     *     Y(es): 노출, N(o): 비노출
     */
    @Description("노출 여부<br>" +
            "Y(es): 노출, N(o): 비노출")
    private String isOpen;
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
    /**
     * 조회 수
     */
    @Description("조회 수")
    private Long viewCnt;
    /**
     * 첨부 파일 리스트
     */
    @Description("첨부 파일 리스트")
    private List<AttachFile> attachFiles;
}
