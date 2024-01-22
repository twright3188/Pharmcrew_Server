import { Component, OnInit, ViewChild } from '@angular/core';
import { FileUploadComponent } from 'src/app/widget/file-upload/file-upload.component';
import { Partner } from '../partner';
import { PartnerService } from '../partner.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { AttachFile } from '../../common/attach-file';
import { RegistPartnerReq } from '../regist-partner-req';
import { UpdatePartnerReq } from '../update-partner-req';
import { PcService } from 'src/app/pc.service';

@Component({
  selector: 'partner-partner-detail',
  templateUrl: './partner-detail.component.html',
  styleUrls: ['./partner-detail.component.css']
})
export class PartnerDetailComponent implements OnInit {
  @ViewChild(FileUploadComponent) fileUploadComponent: FileUploadComponent;

  partner: Partner = new Partner();

  deleteIconFileId: number = undefined;

  constructor(
    private partnerService: PartnerService,
    private pcService: PcService,
    private route: ActivatedRoute,
    private location: Location,
  ) { }

  ngOnInit(): void {
    this.getPartner();
  }

  onDeleteAttach(idx: number) {
    this.deleteIconFileId = this.partner.iconFile.fileId;
    this.partner.iconFile = undefined;
  }

  onViewAttach(idx: number) {
    this.partnerService.downloadFile(this.partner.iconFile.fileId);
  }
  
  onBackClick() {
    this.goBack();
  }

  onDeleteClick() {
    if (!confirm('삭제하시겠습니까?'))  return;
    this.partnerService.deleteParnter(this.partner.partnerId).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200)  this.goBack();
    });
  }

  onSaveClick() {
    if (this.partner.isOpen === undefined) {
      alert('노출 여부를 선택하세요.');
      return;
    }
    if (this.partner.name === undefined) {
      alert('제목을 입력하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.partner.detail)) {
      alert('내용을 입력하세요.');
      return;
    }
    if ((this.partner.partnerId === undefined && this.fileUploadComponent.files() === undefined) ||
      (this.partner.partnerId !== undefined && this.deleteIconFileId !== undefined && this.fileUploadComponent.files() === undefined)) {
      alert('파트너 아이콘을 선택하세요.');
      return;
    }
    if (this.partner.moveUrl === undefined) {
      alert('서비스 연결 경로를 입력하세요.');
      return;
    }

    if (this.partner.partnerId === undefined) {
      const req = new RegistPartnerReq();
      req.name = this.partner.name;
      req.detail = this.partner.detail;
      req.iconFile = this.fileUploadComponent.files()[0];
      req.moveUrl = this.partner.moveUrl;
      req.isOpen = this.partner.isOpen;
      this.partnerService.registPartner(req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      });
    } else {
      const req = new UpdatePartnerReq();
      req.name = this.partner.name;
      req.detail = this.partner.detail;
      if (this.fileUploadComponent.files() !== undefined) {
        req.iconFile = this.fileUploadComponent.files()[0];
      }
      req.moveUrl = this.partner.moveUrl;
      req.isOpen = this.partner.isOpen;
      this.partnerService.updatePartner(this.partner.partnerId, req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      });
    }
  }

  imgFile(): AttachFile[] {
    return [this.partner.iconFile];
  }

  private getPartner() {
    const id = +this.route.snapshot.paramMap.get('id');
    if (id !== -1) {
      this.partnerService.partner(id).subscribe(response => {
        const resp = response.body;
        if (resp.code !== 200)  this.goBack();

        this.partner = resp.partner;
      })
    }
  }

  private goBack() {
    localStorage.setItem('action', 'back');
    this.location.back();
  }

}
