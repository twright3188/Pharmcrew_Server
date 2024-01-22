package kr.ant.kpa.pharmcrew.converter;

import org.springframework.stereotype.Component;

import com.bumdori.util.DateUtils;

import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyAnswerChoiceVo;
import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyQuestionVo;
import kr.ant.kpa.pharmcrew.db.vo.survey.SurveyVo;
import kr.ant.kpa.pharmcrew.resp.survey.Choice;
import kr.ant.kpa.pharmcrew.resp.survey.Question;
import kr.ant.kpa.pharmcrew.resp.survey.Survey;

@Component("surveyConverter")
public class SurveyConverter {

	//@Autowired
	//private 서비스클래스 서비스이름 (ActionService service);

	////////////////////////////////////////////////////////////////////////////////////////
	//	1.설문 목록
	////////////////////////////////////////////////////////////////////////////////////////

	public Survey convertSurvey(SurveyVo vo) {

		Survey o = new Survey();

		o.setId(vo.getSurvey_id()  );
		o.setTitle(vo.getTitle()  );
		o.setDetail(vo.getDetail());
		o.setShowResult(vo.getOpen_result());
		o.setIsComplete(vo.getIsComplete()  );
		if (vo.getStart_date() != null) {
			o.setStartDate(DateUtils.getStringFromDate(vo.getStart_date(), "yyyy.MM.dd"));
		}
		if (vo.getEnd_date() != null) {
			o.setEndDate(DateUtils.getStringFromDate(vo.getEnd_date(), "yyyy.MM.dd"));
		} else {
			o.setEndDate("완료시 까지");
		}

		return o;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	2.설문 문항 목록 요청
	////////////////////////////////////////////////////////////////////////////////////////

	public Question convertQuestion(SurveyQuestionVo vo) {

		Question o = new Question();

		o.setId(vo.getSurvey_id() );
		o.setIdx(vo.getQuestion_idx()  );
		o.setQuestion(vo.getQuestion()  );
		o.setType(vo.getAnswer_div()  );
		o.setTotalCount(vo.getAnswer_cnt());
		
		o.setAnswer(vo.getMyAnswer()  );
		//  예시는 다시 
//		o.setExamples(vo.get  );

		return o;
	}

	public Choice convertChoice(SurveyAnswerChoiceVo vo, int result) {

		Choice o = new Choice();

		o.setNo(vo.getAnswer_no());
		o.setAnswer(vo.getAnswer());
		o.setResult(result);		// 질문 답변 비율 

		return o;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	3.설문 응답 등록
	////////////////////////////////////////////////////////////////////////////////////////

}
