import { Component, OnInit, ViewChild } from '@angular/core';
import { OrganizeSelectorComponent } from 'src/app/widget/organize-selector/organize-selector.component';
import { FileUploadComponent } from 'src/app/widget/file-upload/file-upload.component';
import { Doc } from '../doc';
import { AcademyService } from '../academy.service';
import { PcService } from 'src/app/pc.service';
import { ActivatedRoute } from '@angular/router';
import { RegisterDocReq } from '../register-doc-req';
import { UpdateDocReq } from '../update-doc-req';
import { Location } from '@angular/common';
import { AttachFile } from '../../common/attach-file';

@Component({
  selector: 'academy-doc-detail',
  templateUrl: './doc-detail.component.html',
  styleUrls: ['./doc-detail.component.css']
})
export class DocDetailComponent implements OnInit {
  @ViewChild('organizeSelector') organizeSelector: OrganizeSelectorComponent;
  @ViewChild(FileUploadComponent) fileUploadComponent: FileUploadComponent;
  
  doc = new Doc();

  constructor(
    private academyService: AcademyService,
    private pcService: PcService,
    private route: ActivatedRoute,
    private location: Location,
  ) { }

  ngOnInit(): void {
    this.getDoc();
  }

  onDeleteAttach(idx: number) {
    this.doc.docFile = undefined;
  }

  onViewAttach(idx: number) {
    this.academyService.downloadFile(this.doc.docFile.fileId);
  }

  onBackClick() {
    this.goBack();
  }

  onDeleteClick() {
    if (!confirm('삭제하시겠습니까?'))  return;
    this.academyService.deleteDoc(this.doc.docId).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200)  this.goBack();
    })
  }

  onSaveClick() {
    if (this.doc.openYn === undefined) {
      alert('노출 여부를 선택하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.doc.title)) {
      alert('제목을 입력하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.doc.body)) {
      alert('내용을 입력하세요.');
      return;
    }
    // if (this.doc.docId === undefined && this.fileUploadComponent.files() === undefined) {
    if (this.doc.docFile === undefined && this.fileUploadComponent.files() === undefined) {
        alert('문서 파일을 선택하세요.');
      return;
    }

    if (this.doc.docId === undefined) {
      const req = new RegisterDocReq();
      req.organizeId = this.organizeSelector.organizeId();
      req.title = this.doc.title;
      req.body = this.doc.body;
      req.docFile = this.fileUploadComponent.files()[0];
      req.isOpen = this.doc.openYn;
      this.academyService.registerDoc(req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      });
    } else {
      const req = new UpdateDocReq();
      req.organizeId = this.organizeSelector.organizeId();
      req.title = this.doc.title;
      req.body = this.doc.body;
      if (this.fileUploadComponent.files() !== undefined) {
        req.docFile = this.fileUploadComponent.files()[0];
      }
      req.isOpen = this.doc.openYn;
      this.academyService.updateDoc(this.doc.docId, req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      })
    }
  }

  docFile(): AttachFile[] {
    return [this.doc.docFile];
  }

  private getDoc() {
    const id = +this.route.snapshot.paramMap.get('id');
    if (id !== -1) {
      this.academyService.doc(id).subscribe(response => {
        let resp = response.body;
        if (resp.code !== 200)  this.goBack();

        this.doc = resp.doc;

        if (this.doc.organize !== undefined) {
          this.organizeSelector.setOrganizeIds(this.doc.organize.ids);
        }
      });
    }
  }

  private goBack() {
    localStorage.setItem('action', 'back');
    this.location.back();
  }

}
