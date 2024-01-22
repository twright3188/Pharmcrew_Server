package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.education.EduDetailResp;
import kr.ant.kpa.pharmcrew.resp.education.EduListResp;
import kr.ant.kpa.pharmcrew.resp.education.EduMineListResp;
import kr.ant.kpa.pharmcrew.resp.education.MyEduResp;
import kr.ant.kpa.pharmcrew.validation.exception.gen.AlreadyFinishEduException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.AlreadyProgressEduException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.AlreadyQrStartException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidEvalStateException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.InvalidQrStartException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotEduStartTimeException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundEducationException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundQrException;

public interface EducationService {

	/**
	*	1.교육 목록
	* @param		page	요청할 페이지 : 1 에서 시작
	* @param		count	페이지당 카운트: Default=30개
	**/
	EduListResp getGetEduList(Integer page, Integer count, Long sessionId );

	/**
	*	2.교육 상세
	* @param		eduId	교육 아이디
	**/
	EduDetailResp getGetEduDetail(Long eduId, Long sessionId )
			throws NotFoundEducationException ;

	/**
	*	3.나의 교육 목록
	* @param		year	검색할 연도
	**/
	EduMineListResp getGetEduMine(Integer year, Long sessionId );

	/**
	*	4.출석, 퇴실 등록
	* @param		qrcode	촬영한 QR 코드 정보
	**/
	MyEduResp postPutEduAttend(String qrcode, Long sessionId )
			throws NotFoundQrException, InvalidQrStartException, AlreadyQrStartException, NotEduStartTimeException, AlreadyFinishEduException, AlreadyProgressEduException ;

	/**
	*	6.교육 평가, 수정하기
	* @param		eduId	교육 아이디
	* @param		courseId	강의 아이디 - 나의 교육에서 주는 정보
	* @param		star	교육 평가 별점 (1~5)
	* @param		body	교육 평가 내용
	**/
	PcResp postEvalEdu(Long eduId, Long courseId, Integer star, String body, Long sessionId )
			throws NotFoundEducationException, InvalidEvalStateException ;

}
