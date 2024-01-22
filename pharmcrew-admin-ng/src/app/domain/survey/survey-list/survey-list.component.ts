import { Component, OnInit, ViewChild } from '@angular/core';
import { SurveyService } from '../survey.service';
import { SurveyListReq } from '../survey-list-req';
import { Survey } from '../survey';
import { OrganizeSelectorComponent } from 'src/app/widget/organize-selector/organize-selector.component';
import { PaginationComponent } from 'src/app/widget/pagination/pagination.component';
import { PcService } from 'src/app/pc.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-survey-list',
  templateUrl: './survey-list.component.html',
  styleUrls: ['./survey-list.component.css']
})
export class SurveyListComponent implements OnInit {
  @ViewChild('organizeSelector') organizeSelector: OrganizeSelectorComponent;
  @ViewChild('pagination') pagination: PaginationComponent;
  
  req = new SurveyListReq();
  surveys: Survey[] = undefined;

  constructor(
    private surveyService: SurveyService,
    private pcService: PcService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    // this.surveyList();
  }
  
  ngAfterViewInit() {
    this.pcService.initDatepicker('datepicker1');
    this.pcService.initDatepicker('datepicker2');
    this.restoreCondition();
    this.surveyList();
  }

  onSubmitSearch() {
    this.req.page = 1;
    this.req.organizeId = this.organizeSelector.organizeId();
    this.surveyList();
  }

  onClickItem(id: number) {
    this.backupCondition();
    this.router.navigate(['/surveys', id]);
  }

  onPageClick(page: number) {
    this.req.page = page;
    this.surveyList();
  }

  private surveyList() {
    this.surveyService.surveyList(this.req).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.surveys = resp.surveys;

        this.pagination.setData(this.req.page, resp.searchCnt, this.req.cntPerPage);
      }
    });
  }

  private backupCondition() {
    localStorage.setItem('search', JSON.stringify(this.req));
    this.organizeSelector.backupCondition();
  }

  private restoreCondition() {
    if (localStorage.getItem('action') === 'back') {
      this.req = JSON.parse(localStorage.getItem('search'));
      this.organizeSelector.restoreCondition();
      localStorage.removeItem('action');
    }
  }

}
