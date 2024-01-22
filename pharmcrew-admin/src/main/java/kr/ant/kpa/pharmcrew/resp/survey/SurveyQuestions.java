package kr.ant.kpa.pharmcrew.resp.survey;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SurveyQuestions implements Serializable {
    private List<SurveyQuestion> surveyQuestions;
}
