import { Survey } from './survey';
import { SurveyQuestions } from './survey-questions';
import { PcResp } from 'src/app/pc-resp';

export class SurveyResp extends PcResp {
    survey: Survey;
    questions: SurveyQuestions;
}
