package kr.ant.kpa.pharmcrew.resp.ptax;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.common.AttachFile;
import lombok.Data;

import java.util.List;

@Data
public class Ptaxqna {
    private Long qnaId;
    private String memberName;
    private String pharmName;
    private String title;
    private String body;
    private String isAnswered;
    private String ansTitle;
    private String ansBody;
    private Long questionDt;
    private String adminName;
    private Long answerDt;
    /**
     * 문의 첨부 파일 리스트
     */
    @Description("문의 첨부 파일 리스트")
    private List<AttachFile> qAttachFiles;
    /**
     * 답변 첨부 파일 리스트
     */
    @Description("답변 첨부 파일 리스트")
    private List<AttachFile> aAttachFiles;
}
