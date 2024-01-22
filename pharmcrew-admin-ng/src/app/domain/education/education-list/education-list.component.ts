import { Component, OnInit, ViewChild } from '@angular/core';
import { OrganizeSelectorComponent } from 'src/app/widget/organize-selector/organize-selector.component';
import { PaginationComponent } from 'src/app/widget/pagination/pagination.component';
import { EducationService } from '../education.service';
import { EducationListReq } from '../education-list-req';
import { Education } from '../education';
import { PcService } from 'src/app/pc.service';
import { Router } from '@angular/router';
import { EduAttend } from '../edu-attend';
import { EduAttendListReq } from '../edu-attend-list-req';
import { UpdateEduAttendApprovalReq } from '../update-edu-attend-approval-req';

declare var $ :any;

@Component({
  selector: 'app-education-list',
  templateUrl: './education-list.component.html',
  styleUrls: ['./education-list.component.css']
})
export class EducationListComponent implements OnInit {
  @ViewChild('organizeSelector') organizeSelector: OrganizeSelectorComponent;
  @ViewChild('pagination') pagination: PaginationComponent;

  @ViewChild('attendPagination') attendPagination: PaginationComponent;

  type: string;

  req = new EducationListReq();
  educations: Education[] = undefined;

  selEducationId: number = undefined;

  attendListReq: EduAttendListReq = undefined;
  attends: EduAttend[] = undefined;

  attendModify = false;
  attendCheckAll = false;
  attendChecks: boolean[] = [];

  constructor(
    private educationService: EducationService,
    private pcService: PcService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    console.log(`url: ${this.router.url}`);
    switch (this.router.url) {
      case '/educations':
        this.type = 'E';
        this.attendListReq = new EduAttendListReq();
        break;
      case '/eduapprovals':
        this.type = 'A';
        this.attendListReq = new EduAttendListReq();
        this.attendListReq.type = 'ALL';
        break;
    }
    // this.educationList();
  }
  
  ngAfterViewInit() {
    this.pcService.initDatepicker('datepicker1');
    this.pcService.initDatepicker('datepicker2');
    this.restoreCondition();
    this.educationList();
  }

  onSubmitSearch() {
    this.req.page = 1;
    this.req.organizeId = this.organizeSelector.organizeId();
    this.educationList();
  }

  onClickItem(id: number) {
    this.backupCondition();
    switch (this.type) {
      case 'E':
        this.router.navigate(['/educations', id]);
        break;
      case 'A':
        this.router.navigate(['/eduapprovals', id]);
        break;
    }
  }

  onApproveCntClick(educationId: number) {
    this.attendModify = false;

    this.selEducationId = educationId;

    this.attendListReq.keyword = undefined;
    this.attendListReq.page = 1;
    this.attends = undefined;
    this.attendList();
    $('#ist02_popup').css('display', 'block');
    $('.popup_wrap').css('width', '700px').css('height', '800px').css('margin-left', '-350px').css('margin-top', '-400px');
    $('#fade').css('display', 'block');
  }

  onSubmitApproveSearch() {
    this.onAttendListPageClick(1);
  }

  onAttendCheckAll(value: boolean) {
    this.attendCheckAll = value;
    for (let i = 0; i < this.attends.length; i++) {
      this.attendChecks[i] = this.attendCheckAll;
    }
  }

  onAttendCheck(index: number, value: boolean) {
    this.attendChecks[index] = value;
    for (let i = 0; i < this.attendChecks.length; i++) {
      if (i === index) {
        continue;
      }
      if (this.attendChecks[i] !== this.attendChecks[index]) {
        this.attendCheckAll = false;
        return;
      }
    }
    this.attendCheckAll = this.attendChecks[index];
  }

  onAttendApprovalClick(approval: string) {
    const attendIds: number[] = [];
    for (let i = 0; i < this.attendChecks.length; i++) {
      if (this.attendChecks[i]) {
        attendIds.push(this.attends[i].attendId);
      }
    }
    if (attendIds.length === 0) {
      alert('승인 처리할 항목을 선택하세요.');
      return;
    }

    const req = new UpdateEduAttendApprovalReq();
    req.attendIds = attendIds;
    req.approval = approval;
    this.educationService.updateEduAttendApproval(this.selEducationId, req).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.educationList();

        this.onClosePopupClick();
      }
    })
  }

  onAttendModifyClick() {
    this.attendModify = true;
  }

  onClosePopupClick() {
    $('.popup_wrap').css('display', 'none');
    $('.black_overlay').css('display', 'none');
  }

  onPageClick(page: number) {
    this.req.page = page;
    this.educationList();
  }

  onAttendListPageClick(page: number) {
    this.attendListReq.page = page;
    this.attendList();
  }

  diffStr(startDt: number, endDt?: number) {
    if (endDt === undefined)  return '-';
    return this.pcService.diffStr(startDt, endDt);
  }

  private educationList() {
    this.educationService.educationList(this.req).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.educations = resp.educations;

        this.pagination.setData(this.req.page, resp.searchCnt, this.req.cntPerPage);
      }
    })
  }

  private attendList() {
    this.educationService.eduAttendList(this.selEducationId, this.attendListReq).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.attends = resp.attends;

        this.attendPagination.setData(this.attendListReq.page, resp.searchCnt, this.req.cntPerPage);

        this.attendCheckAll = false;
        if (this.attends !== undefined) {
          this.attendChecks = new Array(this.attends.length);
        }
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
