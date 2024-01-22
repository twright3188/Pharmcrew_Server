import { Injectable } from '@angular/core';
import { HttpService } from 'src/app/http.service';
import { SurveyListReq } from './survey-list-req';
import { SurveyListResp } from './survey-list-resp';
import { PcResp } from 'src/app/pc-resp';
import { RegistSurveyReq } from './regist-survey-req';
import { SurveyResp } from './survey-resp';
import { UpdateSurveyReq } from './update-survey-req';
import { SurveyAnswerListResp } from './survey-answer-list-resp';

@Injectable({
  providedIn: 'root'
})
export class SurveyService extends HttpService {
  registSurvey(req: RegistSurveyReq) {
    return this.post<PcResp>('/surveys', req);
  }
  surveyList(req: SurveyListReq) {
    return this.get<SurveyListResp>('/surveys', req);
  }
  survey(surveyId: number) {
    return this.get<SurveyResp>(`/surveys/${surveyId}`);
  }
  updateSurvey(surveyId: number, req: UpdateSurveyReq) {
    return this.put<PcResp>(`/surveys/${surveyId}`, req);
  }
  deleteSurvey(surveyId: number) {
    return this.delete<PcResp>(`/surveys/${surveyId}`);
  }
  surveyAnswerList(surveyId: number, questionIdx: number) {
    return this.get<SurveyAnswerListResp>(`/surveys/${surveyId}/questions/${questionIdx}/answers`);
  }
}
