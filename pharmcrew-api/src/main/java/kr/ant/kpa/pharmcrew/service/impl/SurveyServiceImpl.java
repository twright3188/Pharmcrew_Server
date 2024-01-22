package kr.ant.kpa.pharmcrew.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bumdori.util.MapUtils;
import com.bumdori.util.StringUtils;

import kr.ant.kpa.pharmcrew.Constants;
import kr.ant.kpa.pharmcrew.converter.SurveyConverter;
import kr.ant.kpa.pharmcrew.db.dao.MemberDao;
import kr.ant.kpa.pharmcrew.db.dao.SurveyDao;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberSurveyVo;
import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyAnswerChoiceVo;
import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyQuestionVo;
import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyVo;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.survey.Question;
import kr.ant.kpa.pharmcrew.resp.survey.QuestionsResp;
import kr.ant.kpa.pharmcrew.resp.survey.SurveysResp;
import kr.ant.kpa.pharmcrew.service.SurveyService;
import kr.ant.kpa.pharmcrew.validation.exception.BadRequestException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotFoundSurveyException;

@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {

	@Autowired
	private SurveyDao surveyDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private SurveyConverter converter;

	@Transactional("transactionManager")

	/**
	*	1.설문 목록
	* @param		page - 요청할 페이지 : 1 에서 시작
	* @param		count - 페이지당 카운트: Default=30개
	**/
	public SurveysResp getGetSurveyList(Integer page, Integer count, Long sessionId ) {
		SurveysResp resp =  new SurveysResp();
		Map<String, Object> param = new HashMap<String, Object>();
		
		// 기본 숫자 지정
		if (count == null) {
			count = Constants.LIST_DEFAULT_COUNT;
		}
		// 페이징  
		MapUtils.pagingMaridDb(param, page, count);

		MapUtils.put(param, "isProgress", true);		// 진행 중인 것 : 날짜가 유효한 것 
		MapUtils.put(param, "is_open", "Y");		// 진행 중인 것 : 날짜가 유효한 것
		MapUtils.put(param, "withMine", true);		// 나의 진행 여부 확인 용
		MapUtils.put(param, "member_id", sessionId);		// 사용자 진행 여부 확인 용
		MapUtils.put(param, "orderby", "ORIGIN.reg_dt desc");		// 진행 중인 것 : 날짜가 유효한 것
		
		List<SurveyVo> surveyVos = surveyDao.listSurvey(param);
		for (SurveyVo surveyVo : surveyVos) {
			resp.addSurveys(converter.convertSurvey(surveyVo));
		}
		
		return resp;
	}

	/**
	*	2.설문 문항 목록 요청
	* @param		surveyId - 설문 아이디
	**/
	public QuestionsResp getGetQuestions(Long surveyId, Long sessionId ) 
			throws NotFoundSurveyException {
		QuestionsResp resp =  new QuestionsResp();
		Map<String, Object> param = new HashMap<String, Object>();
		
		MapUtils.put(param, "survey_id", surveyId);
		SurveyVo surveyVo = surveyDao.selectSurvey(param);
		if (surveyVo == null) {
			throw new NotFoundSurveyException();
		}
		
		resp.setSurvey(converter.convertSurvey(surveyVo));
		
		param.clear();
		MapUtils.put(param, "survey_id", surveyId);	
		MapUtils.put(param, "withMine", true);			// 나의 진행 및 답변 정보
		MapUtils.put(param, "member_id", sessionId);	// 사용자 진행 여부 확인 용
		List<SurveyQuestionVo> questionVos = surveyDao.listSurveyQuestion(param);
		
		for (SurveyQuestionVo questionVo : questionVos) {
			Question question = converter.convertQuestion(questionVo);
			
			// 내가 대답을 했는지? 결과를 오픈할 것인지? 확인
			Boolean isNeedAnswer = "Y".equals(surveyVo.getOpen_result()) && !StringUtils.isEmpty(questionVo.getMyAnswer());
			
			// 객관식 예시 추출
			if ("O".equals(questionVo.getAnswer_div())) {
				param.clear();
				MapUtils.put(param, "survey_id", questionVo.getSurvey_id());
				MapUtils.put(param, "question_idx", questionVo.getQuestion_idx());
				
				List<SurveyAnswerChoiceVo> choiceVos = surveyDao.listSurveyAnswerChoice(param);
				
				// 정답 비율 확인 - 전체 정답 카운트 하기 
				float totalAnswerCount = 0;
				for (SurveyAnswerChoiceVo choiceVo : choiceVos) {
					totalAnswerCount += choiceVo.getAnswer_cnt();
				}
				
				for (SurveyAnswerChoiceVo choiceVo : choiceVos) {
					question.addExamples(converter.convertChoice(choiceVo, (int)(isNeedAnswer? (totalAnswerCount==0? 0:((choiceVo.getAnswer_cnt()*100)/totalAnswerCount)) : 0 )) );
				}
			}
			resp.addQuestions(question);
		}
		
		return resp;
	}

	/**
	*	3.설문 응답 등록
	* @param		surveyId - 설문 아이디
	* @param		questNo - 질문 번호 목록
	* @param		answer - 답변 목록
	 * @throws NotFoundSurveyException 
	**/
	public PcResp postPutSurvey(Long surveyId, Integer[] questNo, String[] answer, Long sessionId ) 
			throws NotFoundSurveyException {
		PcResp resp =  new PcResp();
		Map<String, Object> param = new HashMap<String, Object>();

		MapUtils.put(param, "survey_id", surveyId);
		SurveyVo surveyVo = surveyDao.selectSurvey(param);
		if (surveyVo == null) {
			throw new NotFoundSurveyException();
		}
		
		// 이미 등록했으면 등록 안되기
		
		
		List<SurveyQuestionVo> questionVos = surveyDao.listSurveyQuestion(param);
		if (questionVos.size() != questNo.length ) {
			// 오류 - 숫자가 틀려서 오류 임
			throw new BadRequestException("문항 항목 수가 틀립니다");
		}
		if (questionVos.size() != answer.length) {
			// 오류 - 숫자가 틀려서 오류 임
			throw new BadRequestException("답변 항목 수가 틀립니다");
		}
		
		List<MemberSurveyVo> answerVos = new ArrayList<MemberSurveyVo>();
		
		// 정답 등록 - 이미 했으면 다시 엎어 쓰기
		for (int i = 0; i < questionVos.size(); i++) {
			MemberSurveyVo answerVo = new MemberSurveyVo();
			answerVo.setMember_id(sessionId);
			answerVo.setSurvey_id(surveyId);
			answerVo.setQuestion_idx(questNo[i]);
			answerVo.setAnswer(answer[i]);
			
			answerVos.add(answerVo);
		}
		
		param.clear();
		MapUtils.put(param, "survey_id", surveyId);
		MapUtils.put(param, "answers", answerVos);
		// 사용자 설문에 등록하기 
		memberDao.insertMemberSurveyList(param);
		// 질문에 답변한 숫자 업데이트 
		surveyDao.updateSurveyQuestionAnswer(param);
		
		// 각 항목에 답변한 숫자 업데이트 
		surveyDao.updateSurveyAnswerChoice(param);		
		
		return resp;
	}

}
