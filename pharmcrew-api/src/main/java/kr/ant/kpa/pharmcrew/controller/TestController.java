package kr.ant.kpa.pharmcrew.controller;

import javax.servlet.http.HttpSession;

import com.bumdori.spring.annotation.validation.EnumValidation;
import com.bumdori.spring.annotation.validation.LengthValidation;
import com.bumdori.util.MapUtils;
import com.bumdori.util.StringUtils;
import kr.ant.kpa.pharmcrew.data.facade.education.EducationFacade;
import kr.ant.kpa.pharmcrew.data.facade.news.NoticeFacade;
import kr.ant.kpa.pharmcrew.data.facade.survey.SurveyFacade;
import kr.ant.kpa.pharmcrew.db.dao.NewsDao;
import kr.ant.kpa.pharmcrew.db.dao.PtaxDao;
import kr.ant.kpa.pharmcrew.db.vo.news.QnaVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxNoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxQnaVo;
import kr.ant.kpa.pharmcrew.type.news.MOVE;
import kr.ant.kpa.pharmcrew.validation.exception.BadRequestException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bumdori.fcm.AbsFcmSender;
import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;

import kr.ant.kpa.pharmcrew.fcm.FcmSender;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.test.FileUploadResp;
import kr.ant.kpa.pharmcrew.service.TestService;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller("9.Test")
//@RequestMapping(value = "/test")
public class TestController {
	
	@Autowired
	private TestService service;

	@Autowired
	private NoticeFacade noticeFacade;
	@Autowired
	private EducationFacade educationFacade;
	@Autowired
	private SurveyFacade surveyFacade;

	@Autowired
	private NewsDao newsDao;
	@Autowired
	private PtaxDao ptaxDao;

	@Autowired
    private final FcmSender fcmSender;

    @GetMapping(value = "/test/hello")
    public TestResp hello() {
        return new TestResp(200, "ok");
    }

    @GetMapping(value = "/test/sendFcm")
	@Session(required = false)
	@ResponseBody
    public TestResp sendFcm() {
        fcmSender.send(
                new AbsFcmSender.FcmData()
                        .setTitle("title")
                        .setBody("body")
						.setImgUrl("http://amytech.cafe24.com/PharmCrew/2020/05/21/13/09/1590034143178.jpg")
//						.addData("title", "title")
//						.addData("body", "body")
//						.addData("imgUrl", "http://amytech.cafe24.com/PharmCrew/2020/05/21/13/09/1590034143178.jpg")
                        .setRegistrationToken(
//								"fsSKDRK24kdulWuSmviF6V:APA91bGXUdUxIwYNKcF5Dgh-z7L95YWu6--CrdxsE-I_AOwXPd_r07-X_gIpH7B8YYeqlXVhRb8-rPOOfe6QAuqQkboSjqUYJ4otdE4YCsqTPwvbd_fMpHy2xexBnSR-2pDtBMN94o7x"	// gsungsue iOS
								"ezYo1Il-DUBrjWpKSBP0f5:APA91bHXIjN5bQaTXSlITl3V8CJ3PO5yAE3fO2skEZJ33c_k3XZHXtiIbJZtxm-xjXEpZYLZp1yYpxQOL_qa3FPQEZT5EjKqsNJu5fKhqc-4NIofgMI3VbfDI0bDz77qHxMKynu-j1bE"	// dhchoi iOS
//								"dgt-52jIQgyJT_NzyehiWe:APA91bFxzzxVhFJ5GeO9V9q9HGQS1ELrPxA5XD-05fCnQbZUKG2OxCRYheh4nDXSc-9GntJFE0aPhEmT-B2jolael-YJp1X_KB7mzfIKKPw0aMSzAagicoHlbk8vfZwJMTSeR3viRksz"	// dhchoi Android
						)
        );
        return new TestResp(200, "ok");
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestResp {
        private Integer code;
        private String message;
    }
    
    
    @RequestMapping(value="/test/uploaFile", method=RequestMethod.POST, name="1.파일 등록 테스트")
	@Session(required=false)
	@Description("Page=null , 파일을 등록한다.")
	@Histories({
			@History(date="2020-05-20", description="자동 구성")
	})
	public @ResponseBody FileUploadResp postUploadFile(@RequestParam(value="image", required=true) @Description("등록 파일") MultipartFile image ) 
					throws FailSaveFileException {


    	FileUploadResp resp = service.postUploadFile(image);

		return resp;
	}


	////////////////////////////////////////////////////////////////////////////////////////
	//	100. 사용자 푸시 전송
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/test/push/send/member", method=RequestMethod.POST, name="100. 사용자 푸시 전송")
	@Session(required=true)
	@Description("Page=null , 사용자 자신에게 푸시를 전송한다.")
	@Histories({
			@History(date="2020-06-11", description="자동 구성")
	})
	public @ResponseBody PcResp postSendPushMember(@RequestParam(value="os", required=false) @Description("요청 OS - A:android, I:iOS - 없으면 전체") String os,
			@RequestParam(value="title", required=true) @Description("전송할 메시지 타이틀") String title,
			@RequestParam(value="body", required=true) @Description("전송할 메시 내용") String body,
			@RequestParam(value="imageUrl", required=false) @Description("푸시에 보여질 이미지 링크") String imageUrl,
			@RequestParam(value="imageFile", required=false) @Description("푸시에 보여질 이미지 파일") MultipartFile imageFile,
			@RequestParam(value="category", required=true) @Description("AD: 광고, NT: 알림") String category,
			@RequestParam(value="moveType", required=true) @Description("WU(웹), NO(공지사항), ED(교육), SU(설문)") String moveType,
			@RequestParam(value="moveId", required=false) @Description("이동할 ID(공지사항ID, 교육ID, 설문ID)") Long moveId,
			@RequestParam(value="moveUrl", required=false) @Description("이동할 URL") String moveUrl,
			HttpSession session )
					throws FailSaveFileException {

		Long sessionId = (Long) session.getAttribute("memberId");

		PcResp resp = service.postSendPushMember(os, title, body, imageUrl, imageFile, category, moveType, moveId, moveUrl, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	101. 토큰으로 푸시 전송
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/test/push/send/token", method=RequestMethod.POST, name="101. 토큰으로 푸시 전송")
	@Session(required=true)
	@Description("Page=null , 토큰으로 푸시를 전송한다.")
	@Histories({
			@History(date="2020-06-11", description="자동 구성")
	})
	public @ResponseBody PcResp postSendPushToken(@RequestParam(value="token", required=true) @Description("푸시 수신할 토큰") String token,
			@RequestParam(value="os", required=true) @Description("요청 OS - A:android, I:iOS") String os,
			@RequestParam(value="title", required=true) @Description("전송할 메시지 타이틀") String title,
			@RequestParam(value="body", required=true) @Description("전송할 메시 내용") String body,
			@RequestParam(value="imageUrl", required=false) @Description("푸시에 보여질 이미지 링크") String imageUrl,
			@RequestParam(value="imageFile", required=false) @Description("푸시에 보여질 이미지 파일") MultipartFile imageFile,
			@RequestParam(value="category", required=true) @Description("AD: 광고, NT: 알림") String category,
			@RequestParam(value="moveType", required=true) @Description("WU(웹), NO(공지사항), ED(교육), SU(설문)") String moveType,
			@RequestParam(value="moveId", required=false) @Description("이동할 ID(공지사항ID, 교육ID, 설문ID)") Long moveId,
			@RequestParam(value="moveUrl", required=false) @Description("이동할 URL") String moveUrl,
			HttpSession session ) 
					throws FailSaveFileException {

		Long sessionId = (Long) session.getAttribute("memberId");

		PcResp resp = service.postSendPushToken(token, os, title, body, imageUrl, imageFile, category, moveType, moveId, moveUrl, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	103. 면허번호로 전송
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/test/push/send/license", method=RequestMethod.POST, name="103. 면허번호로 전송")
	@Session(required=false)
	@Description("Page=null , 면허번호로 푸시 전송한다.")
	@Histories({
			@History(date="2020-06-11", description="자동 구성")
	})
	public @ResponseBody PcResp postSendPushLicense(@RequestParam(value="licenseNo", required=false) @Description("전송할 멤버 라이선스 번호") String licenseNo,
			@RequestParam(value="title", required=true) @Description("전송할 메시지 타이틀") String title,
			@RequestParam(value="body", required=true) @Description("전송할 메시 내용") String body,
			@RequestParam(value="imageUrl", required=false) @Description("푸시에 보여질 이미지 링크") String imageUrl,
			@RequestParam(value="imageFile", required=false) @Description("푸시에 보여질 이미지 파일") MultipartFile imageFile,
			@RequestParam(value="category", required=true) @Description("AD: 광고, NT: 알림") String category,
			@RequestParam(value="moveType", required=true) @Description("WU(웹), NO(공지사항), ED(교육), SU(설문)") String moveType,
			@RequestParam(value="moveId", required=false) @Description("이동할 ID(공지사항ID, 교육ID, 설문ID)") Long moveId,
			@RequestParam(value="moveUrl", required=false) @Description("이동할 URL") String moveUrl) 
					throws FailSaveFileException {

		PcResp resp = service.postSendPushLicense(licenseNo, title, body, imageUrl, imageFile, category, moveType, moveId, moveUrl);

		return resp;
	}

	@RequestMapping(value = "/test/pushs", method = RequestMethod.POST, name = "999. 푸시 전송")
	@ResponseBody
	@Session(required = false)
	@Description("테스트 푸시를 전송한다.")
	@Histories({
			@History(date = "2020-07-05", description = "완료")
	})
	public PcResp sendPush(
			@RequestParam("registrationToken") @Description("푸시 토큰") String[] registrationToken,
			@RequestParam("title") @Description("제목") @LengthValidation(max = 30) String title,
			@RequestParam("body") @Description("내용") @LengthValidation(max = 150) String body,
			@RequestParam("imgUrl") @Description("이미지 URL<br>" +
					"샘플: http://amytech.cafe24.com/PharmCrew/2020/05/21/13/09/1590034143178.jpg") String imgUrl,
			@RequestParam("categoryType") @Description("이동 카테고리 타입<br>" +
					"AD: 광고, NT: 알림, PT: 팜텍스공지") @EnumValidation({"AD", "NT", "PT"}) String categoryType,
			@RequestParam("moveType") @Description("이동 타입<br>" +
					"WU: 웹, NO: 공지, ED: 교육, SU: 설문, PT: 팜텍스공지, NQ: 공지 문의, EQ: 교육 문의, PQ: 팜텍스공지 문의") @EnumValidation({"NONE", "WU", "NO", "ED", "SU", "PT", "NQ", "EQ", "PQ"}) String moveType,
			@RequestParam(value = "moveId", required = false) @Description("이동 ID<br>" +
					"moveType이 NO일 때 공지 ID, ED일 때 교육 ID, SU일 때 설문 ID, PT일 때 팜텍스공지 ID, NQ일 때 공지 문의 ID, EQ일 때 교육 문의 ID, PQ일 때 팜텍스공지 문의 ID로 필수") Long moveId,
			@RequestParam(value = "moveUrl", required = false) @Description("이동 URL<br>" +
					"moveType이 WU일 때 필수") @LengthValidation(max = 200) String moveUrl
	) throws NotFoundNoticeException, NotFoundEducationException, NotFoundSurveyException, NotFoundPtaxnoticeException, NotFoundQnaException, NotFoundPtaxqnaException {
    	if ("NONE".equals(moveType))	moveType = null;
    	if (moveType != null) {
    		switch (MOVE.valueOf(moveType)) {
				case WU:
					if ("PT".equals(categoryType))
						throw new BadRequestException("Required String parameter 'categoryType' is not 'AD' or 'NT'");
					if (StringUtils.isEmpty(moveUrl))
						throw new BadRequestException("Required Long parameter 'moveUrl' is not present - 'moveType' is 'WU'");
					break;
				case NO:
					if ("PT".equals(categoryType))
						throw new BadRequestException("Required String parameter 'categoryType' is not 'AD' or 'NT'");
					if (moveId == null)
						throw new BadRequestException("Required Long parameter 'moveId' is not present - 'moveType' is 'NO'");
					if (noticeFacade.get(moveId) == null)
						throw new NotFoundNoticeException();
					break;
				case ED:
					if ("PT".equals(categoryType))
						throw new BadRequestException("Required String parameter 'categoryType' is not 'AD' or 'NT'");
					if (moveId == null)
						throw new BadRequestException("Required Long parameter 'moveId' is not present - 'moveType' is 'ED'");
					if (educationFacade.get(moveId) == null)
						throw new NotFoundEducationException();
					break;
				case SU:
					if ("PT".equals(categoryType))
						throw new BadRequestException("Required String parameter 'categoryType' is not 'AD' or 'NT'");
					if (moveId == null)
						throw new BadRequestException("Required Long parameter 'moveId' is not present - 'moveType' is 'SU'");
					if (surveyFacade.get(moveId) == null)
						throw new NotFoundSurveyException();
					break;
				case PT: {
					if (!"PT".equals(categoryType))
						throw new BadRequestException("Required String parameter 'categoryType' is not 'PT");
					if (moveId == null)
						throw new BadRequestException("Required Long parameter 'moveId' is not present - 'moveType' is 'SU'");
					Map<String, Object> param = new HashMap<>();
					MapUtils.put(param, "notice_id", moveId);
					PtaxNoticeVo ptaxNoticeVo = ptaxDao.selectPtaxNotice(param);
					if (ptaxNoticeVo == null)
						throw new NotFoundPtaxnoticeException();
				}
					break;
				case NQ:
				case EQ: {
					if ("PT".equals(categoryType))
						throw new BadRequestException("Required String parameter 'categoryType' is not 'AD' or 'NT'");
					if (moveId == null)
						throw new BadRequestException("Required Long parameter 'moveId' is not present - 'moveType' is 'SU'");
					Map<String, Object> param = new HashMap<>();
					MapUtils.put(param, "qna_id", moveId);
					QnaVo qnaVo = newsDao.selectQna(param);
					if (qnaVo == null)	throw new NotFoundQnaException();
				}
					break;
				case PQ: {
					if (!"PT".equals(categoryType))
						throw new BadRequestException("Required String parameter 'categoryType' is not 'PT");
					if (moveId == null)
						throw new BadRequestException("Required Long parameter 'moveId' is not present - 'moveType' is 'SU'");
					Map<String, Object> param = new HashMap<>();
					MapUtils.put(param, "qna_id", moveId);
					PtaxQnaVo qnaVo = ptaxDao.selectPtaxQna(param);
					if (qnaVo == null)	throw new NotFoundPtaxqnaException();
				}
					break;
			}
		}

    	fcmSender.send(new AbsFcmSender.FcmData()
				.addData("title", title)
				.addData("body", body)
				.addData("imgUrl", imgUrl)
				.addData("push_id", "1")
				.addData("move_div", moveType)
				.addData("move_url", moveUrl)
				.addData("move_id", moveId != null ? moveId.toString(): null)
				.setRegistrationToken(registrationToken)
		);

    	return new PcResp();
	}

}
