package kr.ant.kpa.pharmcrew.resp.session;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

@Data
public class PostSessionResp extends PcResp {
	
	@Description("이름")
	private String name;
	@Description("면허번호 ")
	private String licenseNo;
	
}
