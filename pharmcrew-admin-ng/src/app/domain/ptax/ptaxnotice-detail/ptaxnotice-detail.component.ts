import { Component, OnInit, ViewChild } from '@angular/core';
import { Ptaxnotice } from '../ptaxnotice';
import { Location } from '@angular/common';
import { FileUploadComponent } from 'src/app/widget/file-upload/file-upload.component';
import { PtaxService } from '../ptax.service';
import { PcService } from 'src/app/pc.service';
import { RegisterPtaxnoticeReq } from '../register-ptaxnotice-req';
import { Router, ActivatedRoute } from '@angular/router';
import { UpdatePtaxnoticeReq } from '../update-ptaxnotice-req';
import { CommonService } from '../../common/common.service';
import { MemberService } from '../../member/member.service';
import { MemberListReq } from '../../member/member-list-req';
import { Member } from '../../member/member';

declare var $ :any;

@Component({
  selector: 'app-ptaxnotice-detail',
  templateUrl: './ptaxnotice-detail.component.html',
  styleUrls: ['./ptaxnotice-detail.component.css']
})
export class PtaxnoticeDetailComponent implements OnInit {
  @ViewChild(FileUploadComponent) fileUploadComponent: FileUploadComponent;
  
  notice = new Ptaxnotice();

  pharms: String[] = [];
  members: Member[] = [];

  deleteAttachFileIds: number[] = undefined;

  constructor(
    private ptaxService: PtaxService,
    private memberService: MemberService,
    private commonService: CommonService,
    private pcService: PcService,
    private router: Router,
    private route: ActivatedRoute,
    private location: Location,
  ) { }

  ngOnInit(): void {
    this.getNotice();
    this.getPharmList();
  }

  ngAfterViewInit() {
    this.pcService.initDatepicker('datepicker1');
    $('#summernote').summernote();
  }

  ngOnDestory() {
    $('#summernote').summernote('destroy');
  }

  onChangeTarget() {
    switch (this.notice.target) {
      case 'ALL':
      case 'G':
        this.notice.targetPharmName = undefined;
        this.notice.targetMemberId = undefined;
        break;
      case 'M':
        break;
    }
  }

  onChangePharmName() {
    this.members = [];
    if (this.notice.targetPharmName === 'undefined') {
      this.notice.targetPharmName = undefined;
    } else {
      this.getMemberList(1);
    }
  }

  onChangeMemberId() {
    const value: any = this.notice.targetMemberId;
    if (value === 'undefined') {
      this.notice.targetMemberId = undefined;
    }
  }

  onChangeSendType() {
    switch (this.notice.sendType) {
      case 'NONE':
      case 'I':
        this.notice.reserveDate = undefined;
        this.notice.reserveHour = undefined;
        this.notice.reserveMinute = undefined;
        break;
    }
  }

  onDeleteAttach(idx: number) {
    console.log(`onDeleteAttach - idx: ${idx}`);
    console.log(`attachFiles: ${JSON.stringify(this.notice.attachFiles)}`);
    const attachFile = this.notice.attachFiles[idx];
    console.log(`attachFile: ${attachFile}`);
    if (this.deleteAttachFileIds === undefined) {
      this.deleteAttachFileIds = [];
    }
    this.deleteAttachFileIds.push(this.notice.attachFiles[idx].fileId);
    this.notice.attachFiles.splice(idx, 1);
  }

  onViewAttach(idx: number) {
    this.ptaxService.downloadFile(this.notice.attachFiles[idx].fileId);
  }

  onBackClick() {
    this.goBack();
  }

  onDeleteClick() {
    if (!confirm('삭제하시겠습니까?'))  return;
    this.ptaxService.deleteNotice(this.notice.noticeId).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200)  this.goBack();
    })
  }

  onSaveClick() {
    if (this.notice.sendType === 'R') {
      if (this.notice.reserveDate === undefined) {
        alert('발송 일자를 선택하세요.');
        return;
      }
      if (this.notice.reserveHour === undefined) {
        alert('발송 시간을 선택하세요.');
        return;
      }
      if (this.notice.reserveMinute === undefined) {
        alert('발송 분을 선택하세요.');
        return;
      }
    }
    if (this.pcService.isEmpty(this.notice.title)) {
      alert('제목을 입력하세요.');
      return;
    }
    const body = $('#summernote').summernote('code');
    if (this.pcService.isEmpty(body)) {
      alert('내용을 입력하세요.');
      return;
    }

    if (this.notice.noticeId === undefined) {
      const req = new RegisterPtaxnoticeReq();
      req.target = this.notice.target;
      // if (this.notice.target === 'M') {
        req.targetPharmName = this.notice.targetPharmName;
        req.targetMemberId = this.notice.targetMemberId;
      // }
      req.openYn = this.notice.openYn;
      req.sendType = this.notice.sendType;
      // if (this.notice.sendType === 'R') {
        req.reserveDate = this.notice.reserveDate;
        req.reserveHour = this.notice.reserveHour;
        req.reserveMinute = this.notice.reserveMinute;
      // }
      req.osType = this.notice.os;
      req.title = this.notice.title;
      req.body = body;
      req.attachFiles = this.fileUploadComponent.files();
      this.ptaxService.registerNotice(req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      })
    } else {
      const req = new UpdatePtaxnoticeReq();
      req.target = this.notice.target;
      // if (this.notice.target === 'M') {
        req.targetPharmName = this.notice.targetPharmName;
        req.targetMemberId = this.notice.targetMemberId;
      // }
      req.openYn = this.notice.openYn;
      req.sendType = this.notice.sendType;
      // if (this.notice.sendType === 'R') {
        req.reserveDate = this.notice.reserveDate;
        req.reserveHour = this.notice.reserveHour;
        req.reserveMinute = this.notice.reserveMinute;
      // }
      req.osType = this.notice.os;
      req.title = this.notice.title;
      req.body = body;
      req.delAttachFileIds = this.deleteAttachFileIds;
      req.attachFiles = this.fileUploadComponent.files();
      this.ptaxService.updateNotice(this.notice.noticeId, req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      })
    }
  }

  private goBack() {
    localStorage.setItem('action', 'back');
    this.location.back();
  }

  private getNotice() {
    const id = +this.route.snapshot.paramMap.get('id');
    if (id !== -1) {
      this.ptaxService.notice(id).subscribe(response => {
        const resp = response.body;
        if (resp.code !== 200)  this.goBack();

        this.notice = resp.notice;

        $('#summernote').summernote('code', this.notice.body);

        if (this.notice.targetMemberId !== undefined) {
          this.getMemberList(1);
        }
      })
    }
  }

  private getPharmList() {
    this.commonService.pharmList().subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.pharms = resp.names;
      }
    })
  }

  private getMemberList(page: number) {
    const req = new MemberListReq();
    req.pharmName = this.notice.targetPharmName;
    req.page = page;
    this.memberService.memberList(req).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        if (resp.members !== undefined) {
          this.members = this.members.concat(resp.members);
          if (resp.searchCnt === req.cntPerPage) {
            this.getMemberList(page + 1);
          }
        }
      }
    })
  }

}
