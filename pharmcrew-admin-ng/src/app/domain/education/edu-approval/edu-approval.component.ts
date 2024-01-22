import { Component, OnInit, ViewChild } from '@angular/core';
import { EducationService } from '../education.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Education } from '../education';
import { EducationCoursess } from '../education-courses';
import { PaginationComponent } from 'src/app/widget/pagination/pagination.component';
import { EducationCourse } from '../education-course';
import { EduAttend } from '../edu-attend';
import { EduAttendListReq } from '../edu-attend-list-req';

declare var $ :any;

@Component({
  selector: 'app-edu-approval',
  templateUrl: './edu-approval.component.html',
  styleUrls: ['./edu-approval.component.css']
})
export class EduApprovalComponent implements OnInit {
  @ViewChild('approvalPagination') approvalPagination: PaginationComponent;
  @ViewChild('pagination') pagination: PaginationComponent;
  
  education = new Education();
  coursess = new EducationCoursess();

  selCourse: EducationCourse = undefined;

  evalCnt: number = undefined;
  evalStarAvg: number = undefined;

  // education.authType === 'A': 교유 전체
  // eudcaiton.authType === 'P': 강의 개별
  approvalsReq = new EduAttendListReq();
  approvals: EduAttend[] = undefined;

  // 후기
  evalsReq: EduAttendListReq = new EduAttendListReq();
  evals: EduAttend[] = undefined;

  constructor(
    private educationService: EducationService,
    private route: ActivatedRoute,
    private location: Location,
  ) { }

  ngOnInit(): void {
    this.approvalsReq.type = 'A'; // 인증

    this.evalsReq.type = 'E';
    
    this.getEducation();
  }

  // education.authType === 'A'
  onApprovalDetailClick() {
    this.approvalsReq.keyword = undefined;
    this.approvalsReq.page = 1;
    this.approvalList();
    $('#ist01_popup').css('display', 'block');
    $('.popup_wrap').css('width', '500px').css('height', '700px').css('margin-left', '-250px').css('margin-top', '-350px');
    $('#fade').css('display', 'block');
  }

  onSubmitApprovalsSearch() {
    this.onApprovalPageClick(1);
  }

  // 팝업
  onApprovalPageClick(page: number) {
    this.approvalsReq.page = page;
    this.approvalList();
  }

  onClosePopupClick() {
    $('.popup_wrap').css('display', 'none');
    $('.black_overlay').css('display', 'none');
  }

  // education.authType === 'P'
  onCourseChange() {
    console.log(`evalsReq.courseId: ${this.evalsReq.courseId}`);
    this.coursess.coursess.forEach(courses => {
      courses.forEach(course => {
        let tmp: any = this.evalsReq.courseId;
        if (course.courseId === +tmp) {
          console.log(`course.courseId: ${course.courseId}`);
          this.selCourse = course;
          return;
        }
      });
    });
    console.log(`selCourse: ${JSON.stringify(this.selCourse)}`);
    this.evalList();
  }
  // onTabClick(dayIdx: number, courseIdx: number) {
  //   console.log(`dayIdx: ${dayIdx}, courseIdx: ${courseIdx}`);
  //   this.selCourse = this.coursess.coursess[dayIdx][courseIdx];

  //   this.evalsReq.courseId = this.coursess.coursess[dayIdx][courseIdx].courseId;
  //   this.evalList();
  // }

  // education.authType === 'P'
  onAttendListClick() {
    this.approvalsReq = new EduAttendListReq();
    this.approvalsReq.type = 'ALL'; // 참여
    this.approvalsReq.courseId = this.selCourse.courseId;
    this.approvalList();
    $('#ist01_popup').css('display', 'block');
    $('.popup_wrap').css('width', '500px').css('height', '700px').css('margin-left', '-250px').css('margin-top', '-350px');
    $('#fade').css('display', 'block');
  }

  // 후기 리스트 페이징
  onPageClick(page: number) {
    this.evalsReq.page = page;
    this.evalList();
  }

  private getEducation() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.educationService.education(id).subscribe(response => {
      const resp = response.body;
      if (resp.code !== 200)  this.goBack();

      this.education = resp.education;
      this.coursess = resp.coursess;

      if (this.education.authType === 'P') {
        this.selCourse = this.coursess.coursess[0][0];
        this.evalsReq.courseId = this.selCourse.courseId;
      }

      this.evalList();
    });
  }

  // 후기 리스트
  private evalList() {
    this.educationService.eduAttendList(this.education.educationId, this.evalsReq).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.evals = resp.attends;

        this.pagination.setData(this.evalsReq.page, resp.searchCnt, this.evalsReq.cntPerPage);

        if (resp.evalCnt !== undefined) {
          this.evalCnt = resp.evalCnt;
          this.evalStarAvg = resp.evalStarAvg;
        }
      }
    });
  }

  // educaiton.authType === 'A': 인증 리스트
  // education.authType === 'P': 참여 리스트
  private approvalList() {
    this.educationService.eduAttendList(this.education.educationId, this.approvalsReq).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.approvals = resp.attends;

        this.approvalPagination.setData(this.approvalsReq.page, resp.searchCnt, this.approvalsReq.cntPerPage);
        
        if (resp.evalCnt !== undefined) {
          this.evalCnt = resp.evalCnt;
          this.evalStarAvg = resp.evalStarAvg;
        }
      }
    });
  }

  private goBack() {
    localStorage.setItem('action', 'back');
    this.location.back();
  }

}
