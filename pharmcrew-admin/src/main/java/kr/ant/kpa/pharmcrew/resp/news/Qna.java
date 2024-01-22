package kr.ant.kpa.pharmcrew.resp.news;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.common.AttachFile;
import kr.ant.kpa.pharmcrew.resp.common.Organize2;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Qna implements Serializable {
    /**
     * QNA ID
     */
    @Description("QNA ID")
    private Long qnaId;
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
     * 문의 제목
     */
    @Description("문의 제목")
    private String title;
    /**
     * 문의 내용
     */
    @Description("문의 내용")
    private String body;
    /**
     * 문의 첨부 파일
     */
    @Description("문의 첨부 파일")
    private AttachFile attachFile;
    /**
     * 질문자명
     */
    @Description("질문자명")
    private String memberName;
    /**
     * 질문 일시
     */
    @Description("질문 일시")
    private Long questionDt;
    /**
     * 답변 여부<br>
     *     Y: 완료, N: 대기
     */
    @Description("답변 여부<br>" +
            "Y: 완료, N: 대기")
    private String isAnswerd;
    /**
     * 답변 제목
     */
    @Description("답변 제목")
    private String answerTitle;
    /**
     * 답변 내용
     */
    @Description("답변 내용")
    private String answerBody;
    /**
     * 답변자명
     */
    @Description("답변자명")
    private String adminName;
    /**
     * 답변 일시
     */
    @Description("답변 일시")
    private Long answerDt;

}
