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
import com.bumdori.spring.annotation.validation.EnumValidation;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.news.MyQnaDetailResp;
import kr.ant.kpa.pharmcrew.resp.news.MyQnaResp;
import kr.ant.kpa.pharmcrew.resp.news.NewsDetailResp;
import kr.ant.kpa.pharmcrew.resp.news.NewsListResp;
import kr.ant.kpa.pharmcrew.service.NewsService;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundEducationException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundNoticeException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundQnaException;

@Controller("4.News")
public class NewsController {

	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	private NewsService newsService;

	////////////////////////////////////////////////////////////////////////////////////////
	//	1.소식방 목록
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/news/list", method=RequestMethod.GET, name="1.소식방 목록")
	@Session(required=true)
	@Description("Page=null , 소식방 목록을 요청한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody NewsListResp getGetNewsList(@RequestParam(value="page", required=true) @Description("요청할 페이지 : 1 에서 시작") Integer page,
			@RequestParam(value="count", required=false) @Description("페이지당 카운트: Default=30개") Integer count,
			HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		NewsListResp resp = newsService.getGetNewsList(page, count, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	2.소식방 상세
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/news/{newsId}", method=RequestMethod.GET, name="2.소식방 상세")
	@Session(required=true)
	@Description("Page=null , 소식방 상세 정보를 요청한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody NewsDetailResp getGetNewsDetail(@PathVariable("newsId") @Description("소식 아이디") Long newsId,
			HttpSession session )
					throws NotFoundNoticeException {

		Long sessionId = (Long) session.getAttribute("memberId");

		NewsDetailResp resp = newsService.getGetNewsDetail(newsId, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	2-1. 소식방 상세 WEB
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/web/news/{newsId}", method=RequestMethod.GET)
	@Session(required=false)
	public ModelAndView getWebBoardDetail(@PathVariable("newsId") @Description("요청 아이디") Long newsId,
			HttpServletResponse response)
					throws NotFoundNoticeException  {
		
		String body = newsService.getGetNewsDetailBody(newsId);
		
		ModelAndView mav = new ModelAndView("/detail/board");
		
		mav.addObject("detail", body);
		
		return mav;
	}
	////////////////////////////////////////////////////////////////////////////////////////
	//	3.문의하기 등록
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/news/qna", method=RequestMethod.POST, name="3.문의하기 등록")
	@Session(required=true)
	@Description("Page=null , 소식방의 문의하기를 등록한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody PcResp postUpdateNewsQna(@RequestParam(value="id", required=true) @Description("소식 or 교육 아이디") Long id,
			@RequestParam(value="category", required=true) @EnumValidation({"E", "N"}) @Description("E: 교육, N : 소식") String category,
			@RequestParam(value="title", required=true) @Description("문의 타이틀") String title,
			@RequestParam(value="body", required=true) @Description("문의 내용") String body,
			@RequestParam(value="file", required=false) @Description("첨부파일") MultipartFile file,
			HttpSession session ) 
					throws FailSaveFileException, NotFoundNoticeException, NotFoundEducationException {

		Long sessionId = (Long) session.getAttribute("memberId");

		PcResp resp = newsService.postUpdateNewsQna(id, category, title, body, file, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	4.나의 문의 목록
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/news/mine", method=RequestMethod.GET, name="4.나의 문의 목록")
	@Session(required=true)
	@Description("Page=null , 나의 문의 목록을 요청한다.")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody MyQnaResp getGetMyQna(@RequestParam(value="page", required=true) @Description("요청할 페이지 : 1 에서 시작") Integer page,
			@RequestParam(value="count", required=false) @Description("페이지당 카운트: Default=30개") Integer count,
			HttpSession session ) {

		Long sessionId = (Long) session.getAttribute("memberId");

		MyQnaResp resp = newsService.getGetMyQna(page, count, sessionId);

		return resp;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	5.나의 문의 상세
	////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(value="/news/mine/{qnaId}", method=RequestMethod.GET, name="5.나의 문의 상세")
	@Session(required=true)
	@Description("Page=null , 나의 문의 상세 정보 요청")
	@Histories({
			@History(date="2020-05-18", description="자동 구성"),
			@History(date="2020-05-18", description="1차 구성 완료")
	})
	public @ResponseBody MyQnaDetailResp getGetMyQnaDetail(@PathVariable("qnaId") @Description("질문 아이디") Long qnaId,
			HttpSession session ) 
					throws NotFoundQnaException {

		Long sessionId = (Long) session.getAttribute("memberId");

		MyQnaDetailResp resp = newsService.getGetMyQnaDetail(qnaId, sessionId);

		return resp;
	}

}
