package kr.ant.kpa.pharmcrew.controller;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bumdori.spring.annotation.Description;
import com.bumdori.spring.annotation.Histories;
import com.bumdori.spring.annotation.History;
import com.bumdori.spring.annotation.Session;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxNewsDetailResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxNewsListResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxQnaDetailResp;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxQnaListResp;
import kr.ant.kpa.pharmcrew.service.PtaxService;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundInfoException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundNoticeException;

@Controller("8.Ptax")
public class PtaxController {

	private static final Logger logger = LoggerFactory.getLogger(PtaxController.class);

	@Autowired
	private PtaxService ptaxService;

	////////////////////////////////////////////////////////////////////////////////////////
	//	1. 1:1 문의 목록
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/ptax/qna/list", method=RequestMethod.GET, name="1. 1:1 문의 목록")
	@Session(required=true)
	@Description("Page=26.0 , 팜택스 고객 1:1 문의 목록을 조회한다. : v0.8")
	@Histories({
			@History(date="2020-06-19", description="자동 구성")
	})
	public @ResponseBody PtaxQnaListResp getQnaList(@RequestParam(value="page", required=true) @Description("요청할 페이지 : 1 에서 시작") Integer page,
			@RequestParam(value="count", required=false) @Description("페이지당 카운트: Default=30개") Integer count,
			HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		PtaxQnaListResp resp = ptaxService.getQnaList(page, count, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	2. 1:1 문의 상세
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/ptax/qna/{qnaId}", method=RequestMethod.GET, name="2. 1:1 문의 상세")
	@Session(required=true)
	@Description("Page=27.0 , 팜택스 1:1 문의 상세 조회 : v0.8")
	@Histories({
			@History(date="2020-06-19", description="자동 구성")
	})
	public @ResponseBody PtaxQnaDetailResp getQnaDetal(@PathVariable("qnaId") @Description("질문 아이디") Long qnaId,
			HttpSession session ) 
					throws NotFoundInfoException {

		Long sessionId = (Long) session.getAttribute("memberId");

		PtaxQnaDetailResp resp = ptaxService.getQnaDetal(qnaId, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	3. 1:1 문의 등록
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/ptax/qna", method=RequestMethod.POST, name="3. 1:1 문의 등록")
	@Session(required=true)
	@Description("Page=29.0 , 팜택스 1:1 문의하기를 등록한다. : v0.8")
	@Histories({
			@History(date="2020-06-19", description="자동 구성")
	})
	public @ResponseBody PcResp postUpdateQna(@RequestParam(value="title", required=true) @Description("문의 타이틀") String title,
			@RequestParam(value="body", required=true) @Description("문의 내용") String body,
			@RequestParam(value="file1", required=false) @Description("첨부파일 1") MultipartFile file1,
			@RequestParam(value="file2", required=false) @Description("첨부파일 2") MultipartFile file2,
			@RequestParam(value="file3", required=false) @Description("첨부파일 3") MultipartFile file3,
			HttpSession session ) 
					throws NotFoundInfoException, FailSaveFileException {

		Long sessionId = (Long) session.getAttribute("memberId");

		PcResp resp = ptaxService.postUpdateQna(title, body, file1, file2, file3, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	4. 공지 목록
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/ptax/news", method=RequestMethod.GET, name="4. 공지 목록")
	@Session(required=true)
	@Description("Page=30.0 , 팜택스 공지 목록 조회 : v0.8")
	@Histories({
			@History(date="2020-06-19", description="자동 구성")
	})
	public @ResponseBody PtaxNewsListResp getNewsList(@RequestParam(value="page", required=true) @Description("요청할 페이지 : 1 에서 시작") Integer page,
			@RequestParam(value="count", required=false) @Description("페이지당 카운트: Default=30개") Integer count,
			HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		PtaxNewsListResp resp = ptaxService.getNewsList(page, count, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	5. 공지 상세
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/ptax/news/{id}", method=RequestMethod.GET, name="5. 공지 상세")
	@Session(required=true)
	@Description("Page=31.0 , 팜택스 공지 상세 : v0.8")
	@Histories({
			@History(date="2020-06-19", description="자동 구성")
	})
	public @ResponseBody PtaxNewsDetailResp getNewsDetail(@PathVariable("id") @Description("팜텍스 공지 아이디") Long id,
			HttpSession session ) 
					throws NotFoundInfoException {

		Long sessionId = (Long) session.getAttribute("memberId");

		PtaxNewsDetailResp resp = ptaxService.getNewsDetail(id, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	5-1. 공지 상세 WEB
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/web/ptax/news/{id}", method=RequestMethod.GET)
	@Session(required=false)
	public ModelAndView getWebBoardDetail(@PathVariable("id") @Description("요청 아이디") Long id,
			HttpServletResponse response) 
					throws NotFoundInfoException  {
		
		String body = ptaxService.getGetNewsDetailBody(id);
		
		ModelAndView mav = new ModelAndView("/detail/board");
		
		mav.addObject("detail", body);
		
		return mav;
	}
}
