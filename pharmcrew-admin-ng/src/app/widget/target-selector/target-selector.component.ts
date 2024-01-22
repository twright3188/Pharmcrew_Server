import { Component, OnInit, Output, EventEmitter, ViewChild } from '@angular/core';
import { NewsService } from 'src/app/domain/news/news.service';
import { EducationService } from 'src/app/domain/education/education.service';
import { SurveyService } from 'src/app/domain/survey/survey.service';
import { NoticeListReq } from 'src/app/domain/news/notice-list-req';
import { PaginationComponent } from '../pagination/pagination.component';
import { EducationListReq } from 'src/app/domain/education/education-list-req';
import { SurveyListReq } from 'src/app/domain/survey/survey-list-req';

declare var $ :any;

@Component({
  selector: 'widget-target-selector',
  templateUrl: './target-selector.component.html',
  styleUrls: ['./target-selector.component.css']
})
export class TargetSelectorComponent implements OnInit {
  @ViewChild('pagination') pagination: PaginationComponent;
  
  @Output() onSelect = new EventEmitter<number>();

  moveType: string = undefined;

  keyword: string = undefined;
  page = 1;
  targets: Target[] = [];

  moveId: number = undefined;

  constructor(
    private newsService: NewsService,
    private educationService: EducationService,
    private surveyService: SurveyService,
  ) { }

  ngOnInit(): void {
  }

  onSubmitSearch() {
    this.page = 1;
    this.getTargetList();
  }

  onPageClick(page: number) {
    this.page = page;
    this.getTargetList();
  }

  onSelectTargetClick() {
    this.onSelect.emit(this.moveId);
    this.onClosePopupClick();
  }

  onClosePopupClick() {
    $('.popup_wrap').css('display', 'none');
    $('.black_overlay').css('display', 'none');
  }

  setType(moveType: string) {
    this.moveType = moveType;
  }

  show() {
    $('#push_select').css('display', 'block');
    $('.popup_wrap').css('width', '700px').css('height', '800px').css('margin-left', '-350px').css('margin-top', '-400px');
    $('#fade').css('display', 'block');
    this.onSubmitSearch();
  }

  private getTargetList() {
    switch (this.moveType) {
      case 'NO': {
        const req = new NoticeListReq();
        req.keyword = this.keyword;
        req.isOpen = 'Y';
        req.page = this.page;
        req.cntPerPage = 10;
        this.newsService.noticeList(req).subscribe(response => {
          const resp = response.body;
          if (resp.code === 200) {
            this.targets = [];
            resp.notices.forEach(notice => {
              const target = new Target();
              target.id = notice.noticeId;
              target.title = notice.title;
              this.targets.push(target);
            });

            this.pagination.setData(this.page, resp.searchCnt, req.cntPerPage);
          }
        });
      }
        break;
      case 'ED': {
        const req = new EducationListReq();
        req.isOpen = 'Y';
        req.keyword = this.keyword;
        req.page = this.page;
        req.cntPerPage = 10;
        this.educationService.educationList(req).subscribe(response => {
          const resp = response.body;
          if (resp.code === 200) {
            this.targets = [];
            resp.educations.forEach(education => {
              const target = new Target();
              target.id = education.educationId;
              target.title = education.title;
              this.targets.push(target);
            });

            this.pagination.setData(this.page, resp.searchCnt, req.cntPerPage);
          }
        });
      }
        break;
      case 'SU': {
        const req = new SurveyListReq();
        req.isOpen = 'Y';
        req.keyword = this.keyword;
        req.page = this.page;
        req.cntPerPage = 10;
        this.surveyService.surveyList(req).subscribe(response => {
          const resp = response.body;
          if (resp.code === 200) {
            this.targets = [];
            resp.surveys.forEach(survey => {
              const target = new Target();
              target.id = survey.surveyId;
              target.title = survey.title;
              this.targets.push(target);
            });

            this.pagination.setData(this.page, resp.searchCnt, req.cntPerPage);
          }
        })
      }
        break;
    }
  }

}

export class Target {
  id: number;
  title: string;
}
