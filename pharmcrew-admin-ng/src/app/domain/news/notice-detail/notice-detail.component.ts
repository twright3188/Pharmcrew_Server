import { Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { NewsService } from '../news.service';
import { Notice } from '../notice';
import { OrganizeSelectorComponent } from 'src/app/widget/organize-selector/organize-selector.component';
import { FileUploadComponent } from 'src/app/widget/file-upload/file-upload.component';
import { PcService } from 'src/app/pc.service';
import { RegistNoticeReq } from '../regist-notice-req';
import { UpdateNoticeReq } from '../update-notice-req';

declare var $ :any;

@Component({
  selector: 'news-notice-detail',
  templateUrl: './notice-detail.component.html',
  styleUrls: ['./notice-detail.component.css']
})
export class NoticeDetailComponent implements OnInit {
  @ViewChild('organizeSelector') organizeSelector: OrganizeSelectorComponent;
  @ViewChild(FileUploadComponent) fileUploadComponent: FileUploadComponent;
  
  notice: Notice = new Notice();

  deleteAttachFileIds: number[] = undefined;

  constructor(
    private newsService: NewsService,
    private pcService: PcService,
    private route: ActivatedRoute,
    private location: Location,
  ) { }

  ngOnInit(): void {
    this.getNotice();
  }

  ngAfterViewInit() {
    $('#summernote').summernote();
  }

  ngOnDestory() {
    $('#summernote').summernote('destroy');
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
    this.newsService.downloadFile(this.notice.attachFiles[idx].fileId);
  }

  onBackClick() {
    this.goBack();
  }

  onDeleteClick() {
    if (!confirm('삭제하시겠습니까?'))  return;
    this.newsService.deleteNotice(this.notice.noticeId).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200)  this.goBack();
    })
  }

  onSaveClick() {
    console.log(`notice: ${JSON.stringify(this.notice)}`);
    if (this.notice.isOpen === undefined) {
      alert('노출 여부를 선택하세요.');
      return;
    }
    if (this.notice.title === undefined) {
      alert('제목을 입력하세요.');
      return;
    }
    const body = $('#summernote').summernote('code');
    if (this.pcService.isEmpty(body)) {
      alert('내용을 입력하세요.')
      return;
    }

    if (this.notice.noticeId === undefined) {
      const req = new RegistNoticeReq();
      req.organizeId = this.organizeSelector.organizeId();
      req.title = this.notice.title;
      req.body = body;
      req.attachFiles = this.fileUploadComponent.files();
      req.isOpen = this.notice.isOpen;
      this.newsService.registNotice(req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      });
    } else {
      const req = new UpdateNoticeReq();
      req.organizeId = this.organizeSelector.organizeId();
      req.title = this.notice.title;
      req.body = body;
      req.delAttachFileIds = this.deleteAttachFileIds;
      req.attachFiles = this.fileUploadComponent.files();
      req.isOpen = this.notice.isOpen;
      this.newsService.updateNotice(this.notice.noticeId, req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      })
    }
  }

  private getNotice() {
    const id = +this.route.snapshot.paramMap.get('id');
    if (id !== -1) {
      this.newsService.notice(id).subscribe(response => {
        let resp = response.body;
        if (resp.code !== 200)  this.goBack();

        this.notice = resp.notice;

        this.organizeSelector.setOrganizeIds(this.notice.organize?.ids);
        $('#summernote').summernote('code', this.notice.body);
      });
    }
  }

  private goBack() {
    localStorage.setItem('action', 'back');
    this.location.back();
  }

}
