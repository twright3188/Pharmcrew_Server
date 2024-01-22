package kr.ant.kpa.pharmcrew.resp.member;

import java.io.Serializable;
import java.util.Date;

import com.bumdori.spring.annotation.Description;

import kr.ant.kpa.pharmcrew.resp.education.MyEdu;
import lombok.Data;


/**
* 약사회 멤버 정보
 */
@Data
public class Member implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = -1;

	////////////////////////////////////////////////////////////////////////////////////////
	//	 Member Variable
	////////////////////////////////////////////////////////////////////////////////////////

	@Description("사용자 아이디")
	private Long id;


	@Description("면허번호")
	private String license;


	@Description("이름")
	private String name;


	@Description("소속 약사회")
	private String org_0;


	@Description("소속 지부")
	private String org_1;


	@Description("소속 분회")
	private String org_2;


	@Description("프로필 이미지")
	private String profile;


	@Description("생년월일 (yyyy.MM.dd)")
	private String birth;


	@Description("전화번호")
	private String phone;


	@Description("성별")
	private String sex;

	@Description("이메일")
	private String email;

	@Description("최근 참여중이거나 참여완료된 교육")
	private MyEdu myEdu;


	@Description("현재 교육 참여 중인지 여부")
	private String isIngEdu;
	
	@Description("등록일시")
	private Date regDate;
}
