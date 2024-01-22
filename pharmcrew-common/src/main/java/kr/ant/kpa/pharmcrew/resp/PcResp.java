package kr.ant.kpa.pharmcrew.resp;

import java.io.Serializable;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.validation.VALIDATION;
import lombok.Data;

@Data
public class PcResp implements Serializable {
	@Description("요청 ID, 요청마다 생성되는 값")
	private String reqId;
	@Description("응답 코드 - 성공시 200 others")
	private Integer code;
	@Description("응답 메시지 - 오류 시 메시지로 팝업이나 토스트 노출 ")
	private String message;

    public PcResp() {
        this(VALIDATION.OK);
    }

    public PcResp(VALIDATION validation) {
        this.code = validation.getCode();
        this.message = validation.getMessage();
    }
}
