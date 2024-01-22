import { Component, OnInit, ViewChild } from '@angular/core';
import { Survey } from '../survey';
import { SurveyService } from '../survey.service';
import { SurveyQuestions } from '../survey-questions';
import { OrganizeSelectorComponent } from 'src/app/widget/organize-selector/organize-selector.component';
import { SurveyQuestion } from '../survey-question';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { SurveyAnswerChoice } from '../survey-answer-choice';
import { PcService } from 'src/app/pc.service';
import { RegistSurveyReq } from '../regist-survey-req';
import { UpdateSurveyReq } from '../update-survey-req';
import { PaginationComponent } from 'src/app/widget/pagination/pagination.component';
import { SurveyAnswerListReq } from '../survey-answer-list-req';
import { SurveyAnswer } from '../survey-answer';

declare var $ :any;

@Component({
  selector: 'app-survey-detail',
  templateUrl: './survey-detail.component.html',
  styleUrls: ['./survey-detail.component.css']
})
export class SurveyDetailComponent implements OnInit {
  @ViewChild('organizeSelector') organizeSelector: OrganizeSelectorComponent;
  @ViewChild('answerPagination') answerPagination: PaginationComponent;
  
  survey = new Survey();
  questions = new SurveyQuestions();

  modify = false;

  answerQuestionIdx: number = undefined;
  answerReq = new SurveyAnswerListReq();
  answers: SurveyAnswer[] = undefined;
  answerCnt: number = 0;

  constructor(
    private surveyService: SurveyService,
    private pcService: PcService,
    private route: ActivatedRoute,
    private location: Location,
  ) { }

  ngOnInit(): void {
    this.getSurvey();
  }
  
  ngAfterViewInit() {
    this.pcService.initDatepicker('datepicker1');
    this.pcService.initDatepicker('datepicker2');
  }

  onChangeStartDate(value: string) {
    console.log(`startDate: ${this.survey.startDate}, value: ${value}`);
    if (this.survey.endDate !== undefined) {
      const startDate = new Date(value.replace('.', '-'));
      const endDate = new Date(this.survey.endDate.replace('.', '-'));
      const diff = endDate.getTime() - startDate.getTime();
      if (diff < 0) {
        alert('시작일이 종료일 이전입니다.');
        this.pcService.set('datepicker1', this.survey.startDate);
        return;
      }
    }
    this.survey.startDate = value;
  }

  onChangeEndDate(value: string) {
    console.log(`endDate: ${this.survey.endDate}, value: ${value}`);
    if (this.survey.startDate !== undefined) {
      const startDate = new Date(this.survey.startDate.replace('.', '-'));
      const endDate = new Date(value.replace('.', '-'));
      const diff = endDate.getTime() - startDate.getTime();
      if (diff < 0) {
        alert('종료일이 시작일 이전입니다.');
        this.pcService.set('datepicker2', this.survey.endDate);
        return;
      }
    }
    this.survey.endDate = value;
  }

  onAddQuestionClick() {
    const question = new SurveyQuestion();
    question.idx = this.questions.surveyQuestions.length + 1;
    this.questions.surveyQuestions.push(question);
  }

  onDeleteQuestionClick(idx: number) {
    this.questions.surveyQuestions.splice(idx, 1);
    for (let i = idx; i < this.questions.surveyQuestions.length; i++) {
      this.questions.surveyQuestions[i].idx = i;
    }
  }

  onChangeAnswerType(idx: number) {
    const question = this.questions.surveyQuestions[idx];
    switch (question.answerType) {
      case 'S':
        question.answerChoices = undefined;
        break;
      case 'O':
        question.answerChoices = [];
        for (let i = 0; i < question.oAnswerCnt; i++) {
          const answer = new SurveyAnswerChoice();
          answer.answerNo = i + 1;
          question.answerChoices.push(answer);
        }
        break;
    }
  }

  onSelectOAnswerCnt(idx: number) {
    console.log(`idx: ${idx}`);
    const question = this.questions.surveyQuestions[idx];
    console.log(`oAnswerCnt: ${question.oAnswerCnt}, answerChoices.length: ${question.answerChoices.length}`);
    if (question.oAnswerCnt > question.answerChoices.length) {
      for (let i = question.answerChoices.length; i < question.oAnswerCnt; i++) {
        console.log(`i: ${i} - add`);
        const answer = new SurveyAnswerChoice();
        answer.answerNo = i + 1;
        question.answerChoices.push(answer);
      }
    } else if (question.oAnswerCnt < question.answerChoices.length) {
      question.answerChoices.splice(question.oAnswerCnt);
    }
  }

  onBackClick() {
    this.goBack();
  }

  onDeleteClick() {
    if (!confirm('삭제하시겠습니까?'))  return;
    this.surveyService.deleteSurvey(this.survey.surveyId).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200)  this.goBack();
    });
  }

  onModifyClick() {
    this.modify = true;
  }

  onSaveClick() {
    if (this.survey.startDate === undefined) {
      alert('설문 시작일을 선택하세요.');
      return;
    }
    // if (this.survey.endDate === undefined) {
    //   alert('설문 종료일을 선택하세요.');
    //   return;
    // }
    if (this.pcService.isEmpty(this.survey.title)) {
      alert('설문 제목을 입력하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.survey.detail)) {
      alert('설문 상세를 입력하세요.');
      return;
    }
    for (let i = 0; i < this.questions.surveyQuestions.length; i++) {
      const question = this.questions.surveyQuestions[i];
      if (this.pcService.isEmpty(question.question)) {
        alert(`${question.idx}번 질문을 입력하세요.`);
        return;
      }
      switch (question.answerType) {
        case 'O':
          for (let j = 0; j < question.answerChoices.length; j++) {
            const answer = question.answerChoices[j];
            if (this.pcService.isEmpty(answer.answer)) {
              alert(`${question.idx}번 질문의 답변 항목 ${answer.answerNo}번을 입력하세요.`);
              return;
            }
          }
          break;
      }
    }

    if (this.survey.surveyId === undefined) {
      const req = new RegistSurveyReq();
      req.startDate = this.survey.startDate;
      req.endDate = this.survey.endDate;
      req.isOpen = this.survey.isOpen;
      req.openResult = this.survey.openResult;
      req.organizeId = this.organizeSelector.organizeId();
      req.title = this.survey.title;
      req.detail = this.survey.detail;
      req.questionsJson = JSON.stringify(this.questions);
      this.surveyService.registSurvey(req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      });
    } else {
      const req = new UpdateSurveyReq();
      req.startDate = this.survey.startDate;
      req.endDate = this.survey.endDate;
      req.isOpen = this.survey.isOpen;
      req.openResult = this.survey.openResult;
      req.organizeId = this.organizeSelector.organizeId();
      req.title = this.survey.title;
      req.detail = this.survey.detail;
      req.questionsJson = JSON.stringify(this.questions);
      this.surveyService.updateSurvey(this.survey.surveyId, req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      });
    }
  }

  onOpenSAnswers(idx: number) {
    $('#answer_pop').css('display', 'block');
    // $('.popup_wrap').css('width', '500px').css('height', '750px').css('margin-left', '-250px').css('margin-top', '-375px');  // survey-detail.component.css로 이동
    $('#fade').css('display', 'block');

    this.answers = undefined;

    this.answerQuestionIdx = idx;
    this.getSurveyAnswerList();
  }

  onCloseSAnswers() {
    $('.popup_wrap').css('display', 'none');
    $('.black_overlay').css('display', 'none');
  }

  onAnswerPageClick(page: number) {
    this.answerReq.page = page;
    this.getSurveyAnswerList();
  }

  state(): string {
    if (this.survey.surveyId !== undefined) {
      const startDate = new Date(this.survey.startDate.replace('.', '-'));
      let endDate = undefined;
      if (this.survey.endDate !== undefined) {
        endDate = new Date(this.survey.endDate.replace('.', '-'));
      }
      const today = new Date();
      today.setHours(0, 0, 0, 0);
      // console.log(`startDate: ${startDate}, endDate: ${endDate}, today: ${today}`);
      if (today < startDate) {
        return '대기';
      } else if (today > endDate) {
        return '완료';
      }
      return '진행 중';
    }
    return undefined;
  }

  private getSurvey() {
    const id = +this.route.snapshot.paramMap.get('id');
    if (id !== -1) {
      this.surveyService.survey(id).subscribe(response => {
        const resp = response.body;
        if (resp.code !== 200)  this.goBack();

        this.survey = resp.survey;
        this.questions = resp.questions;
        console.log(`survey: ${this.survey}, questions: ${this.questions}`);

        if (this.survey.organize !== undefined) {
          this.organizeSelector.setOrganizeIds(this.survey.organize.ids);
        }
      });
    } else {
      this.questions.surveyQuestions = [];

      this.onAddQuestionClick();
    }
  }

  private getSurveyAnswerList() {
    this.surveyService.surveyAnswerList(this.survey.surveyId, this.answerQuestionIdx).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.answers = resp.surveyAnswers;
        this.answerCnt = resp.searchCnt;

        this.answerPagination.setData(this.answerReq.page, resp.searchCnt, this.answerReq.cntPerPage);
      }
    });
  }

  private goBack() {
    localStorage.setItem('action', 'back');
    this.location.back();
  }

}
