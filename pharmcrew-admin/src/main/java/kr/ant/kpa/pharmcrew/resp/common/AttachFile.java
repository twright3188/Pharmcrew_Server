package kr.ant.kpa.pharmcrew.resp.common;

import com.bumdori.spring.annotation.Description;
import lombok.Data;

import java.io.Serializable;

@Data
public class AttachFile implements Serializable {
    /**
     * 파일 ID
     */
    @Description("파일 ID")
    private Long fileId;
    /**
     * 원본 파일명
     */
    @Description("원본 파일명")
    private String orgName;
    /**
     * 파일 접근 URL
     */
    @Description("파일 접근 URL")
    private String url;
}
