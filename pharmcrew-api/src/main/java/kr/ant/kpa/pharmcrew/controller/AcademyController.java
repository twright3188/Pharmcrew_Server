package kr.ant.kpa.pharmcrew.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;

import kr.ant.kpa.pharmcrew.resp.academy.DocDetailResp;
import kr.ant.kpa.pharmcrew.resp.academy.DocListResp;
import kr.ant.kpa.pharmcrew.resp.academy.VideoDetailResp;
import kr.ant.kpa.pharmcrew.resp.academy.VideoListResp;
import kr.ant.kpa.pharmcrew.service.AcademyService;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundInfoException;

@Controller("7.Academy")
public class AcademyController {

	private static final Logger logger = LoggerFactory.getLogger(AcademyController.class);

	@Autowired
	private AcademyService academyService;

	////////////////////////////////////////////////////////////////////////////////////////
	//	1. 문서 목록 조회
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/academy/doc", method=RequestMethod.GET, name="1. 문서 목록 조회")
	@Session(required=true)
	@Description("Page=79.0 , 학술정보 PDF 목록 조회한다. : 0.8")
	@Histories({
			@History(date="2020-06-19", description="자동 구성")
	})
	public @ResponseBody DocListResp getDocList(@RequestParam(value="searchKey", required=false) @Description("검색어") String searchKey,
			@RequestParam(value="page", required=true) @Description("요청할 페이지 : 1 에서 시작") Integer page,
			@RequestParam(value="count", required=false) @Description("페이지당 카운트: Default=30개") Integer count,
			HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		DocListResp resp = academyService.getDocList(searchKey, page, count, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	2. 문서 상세 조회
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/academy/doc/{id}", method=RequestMethod.GET, name="2. 문서 상세 조회")
	@Session(required=false)
	@Description("Page=80.0 , 학술정보 PDF 상세 조회 : 0.8")
	@Histories({
			@History(date="2020-06-19", description="자동 구성")
	})
	public @ResponseBody DocDetailResp getDocDetail(@PathVariable("id") @Description("문서 아이디") Long id ) 
			throws NotFoundInfoException {

		DocDetailResp resp = academyService.getDocDetail(id);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	3. 동영상 목록 조회
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/academy/video", method=RequestMethod.GET, name="3. 동영상 목록 조회")
	@Session(required=true)
	@Description("Page=81.0 , 학술정보 동영상 목록 조회 : 0.8")
	@Histories({
			@History(date="2020-06-19", description="자동 구성")
	})
	public @ResponseBody VideoListResp getVideoList(@RequestParam(value="searchKey", required=false) @Description("검색어") String searchKey,
			@RequestParam(value="page", required=true) @Description("요청할 페이지 : 1 에서 시작") Integer page,
			@RequestParam(value="count", required=false) @Description("페이지당 카운트: Default=30개") Integer count,
			HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		VideoListResp resp = academyService.getVideoList(searchKey, page, count, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	4. 동영상 상세 조회
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/academy/video/{id}", method=RequestMethod.GET, name="4. 동영상 상세 조회")
	@Session(required=false)
	@Description("Page=81.0 , 학술정보 동영상 상세 조회 : 0.8")
	@Histories({
			@History(date="2020-06-19", description="자동 구성")
	})
	public @ResponseBody VideoDetailResp getVideoDetail(@PathVariable("id") @Description("동영상 아이디") Long id ) 
			throws NotFoundInfoException {

		VideoDetailResp resp = academyService.getVideoDetail(id);

		return resp;
	}

}
