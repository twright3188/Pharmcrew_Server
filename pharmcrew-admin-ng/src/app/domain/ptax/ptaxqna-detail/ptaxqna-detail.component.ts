import { Component, OnInit, ViewChild } from '@angular/core';
import { Ptaxqna } from '../ptaxqna';
import { PtaxService } from '../ptax.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { AnswerPtaxqnaReq } from '../answer-ptaxqna-req';
import { FileUploadComponent } from 'src/app/widget/file-upload/file-upload.component';

@Component({
  selector: 'app-ptaxqna-detail',
  templateUrl: './ptaxqna-detail.component.html',
  styleUrls: ['./ptaxqna-detail.component.css']
})
export class PtaxqnaDetailComponent implements OnInit {
  @ViewChild(FileUploadComponent) fileUploadComponent: FileUploadComponent;
  
  qna = new Ptaxqna();

  deleteAttachFileIds: number[] = undefined;

  modify = false;

  constructor(
    private ptaxService: PtaxService,
    private route: ActivatedRoute,
    private location: Location,
  ) { }

  ngOnInit(): void {
    this.getQna();
  }

  onDeleteAttach(idx: number) {
    console.log(`onDeleteAttach - idx: ${idx}`);
    console.log(`aAttachFiles: ${JSON.stringify(this.qna.aAttachFiles)}`);
    const attachFile = this.qna.aAttachFiles[idx];
    console.log(`attachFile: ${attachFile}`);
    if (this.deleteAttachFileIds === undefined) {
      this.deleteAttachFileIds = [];
    }
    this.deleteAttachFileIds.push(this.qna.aAttachFiles[idx].fileId);
    this.qna.aAttachFiles.splice(idx, 1);
  }

  onViewAttach(idx: number) {
    this.ptaxService.downloadFile(this.qna.aAttachFiles[idx].fileId);
  }

  onBackClick() {
    this.goBack();
  }

  onSaveClick() {
    if (this.qna.ansTitle === undefined) {
      alert('답변 제목을 입력하세요.');
      return;
    }
    if (this.qna.ansBody === undefined) {
      alert('답변 내용을 입력하세요.');
      return;
    }
    const req = new AnswerPtaxqnaReq();
    req.answerTitle = this.qna.ansTitle;
    req.answerBody = this.qna.ansBody;
    req.delAttachFileIds = this.deleteAttachFileIds;
    req.attachFiles = this.fileUploadComponent.files();
    this.ptaxService.answerQna(this.qna.qnaId, req).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.goBack();
      }
    });
  }

  onModifyClick() {
    this.modify = true;
  }

  private getQna() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.ptaxService.qna(id).subscribe(response => {
      const resp = response.body;
      if (resp.code !== 200)  this.goBack();

      this.qna = resp.qna;
    })
  }

  private goBack() {
    localStorage.setItem('action', 'back');
    this.location.back();
  }

}
