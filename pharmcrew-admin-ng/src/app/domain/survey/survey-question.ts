import { SurveyAnswerChoice } from './survey-answer-choice';

export class SurveyQuestion {
    question: string;
    idx: number;
    answerType = 'S';
    answerCnt: number;
    oAnswerCnt = 5;
    answerChoices: SurveyAnswerChoice[];
}
