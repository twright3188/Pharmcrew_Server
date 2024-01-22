import { SurveyAnswer } from './survey-answer';
import { PcResp } from 'src/app/pc-resp';

export class SurveyAnswerListResp extends PcResp {
    surveyAnswers: SurveyAnswer[];
    searchCnt: number;
}
