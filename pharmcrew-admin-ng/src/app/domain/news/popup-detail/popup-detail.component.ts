import { Component, OnInit, ViewChild } from '@angular/core';
import { NewsService } from '../news.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Popup } from '../popup';
import { AttachFile } from '../../common/attach-file';
import { PcService } from 'src/app/pc.service';
import { FileUploadComponent } from 'src/app/widget/file-upload/file-upload.component';
import { RegistPopupReq } from '../regist-popup-req';
import { UpdatePopupReq } from '../update-popup-req';
import { TargetSelectorComponent } from 'src/app/widget/target-selector/target-selector.component';

@Component({
  selector: 'news-popup-detail',
  templateUrl: './popup-detail.component.html',
  styleUrls: ['./popup-detail.component.css']
})
export class PopupDetailComponent implements OnInit {
  @ViewChild(FileUploadComponent) fileUploadComponent: FileUploadComponent;
  @ViewChild('targetSelector') targetSelector: TargetSelectorComponent;
  
  popup: Popup = new Popup();

  deleteImgFileId: number = undefined;

  constructor(
    private newsService: NewsService,
    private pcService: PcService,
    private route: ActivatedRoute,
    private location: Location,
  ) { }

  ngOnInit(): void {
    this.getPopup();
  }
  
  ngAfterViewInit() {
    this.pcService.initDatepicker('datepicker1');
    this.pcService.initDatepicker('datepicker2');
  }

  onChangeOpenStartDate(value: string) {
    console.log(`openStartDate: ${this.popup.openStartDate}, value: ${value}`);
    if (this.popup.openEndDate !== undefined) {
      const startDate = new Date(value.replace('.', '-'));
      const endDate = new Date(this.popup.openEndDate.replace('.', '-'));
      const diff = endDate.getTime() - startDate.getTime();
      if (diff < 0) {
        alert('시작일이 종료일 이전입니다.');
        this.pcService.set('datepicker1', this.popup.openStartDate);
        return;
      }
    }
    this.popup.openStartDate = value;
  }

  onChangeOpenEndDate(value: string) {
    console.log(`openEndDate: ${this.popup.openEndDate}, value: ${value}`);
    if (this.popup.openStartDate !== undefined) {
      const startDate = new Date(this.popup.openStartDate.replace('.', '-'));
      const endDate = new Date(value.replace('.', '-'));
      const diff = endDate.getTime() - startDate.getTime();
      if (diff < 0) {
        alert('종료일이 시작일 이전입니다.');
        this.pcService.set('datepicker2', this.popup.openEndDate);
        return;
      }
    }
    this.popup.openEndDate = value;
  }

  onDeleteAttach(idx: number) {
    this.deleteImgFileId = this.popup.imgFile.fileId;
    this.popup.imgFile = undefined;
  }

  onViewAttach(idx: number) {
    this.newsService.downloadFile(this.popup.imgFile.fileId);
  }

  onChangeMoveType() {
    this.popup.moveId = undefined;
    this.popup.moveUrl = undefined;
  }

  onMoveTargetClick() {
    this.targetSelector.setType(this.popup.moveType);
    this.targetSelector.show();
  }

  onSelectMoveTarget(id: number) {
    this.popup.moveId = id;
  }
  
  onBackClick() {
    this.goBack();
  }

  onDeleteClick() {
    if (!confirm('삭제하시겠습니까?'))  return;
    this.newsService.deletePopup(this.popup.popupId).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200)  this.goBack();
    });
  }

  onSaveClick() {
    if (this.popup.openStartDate === undefined) {
      alert('노출 시작일을 선택하세요.');
      return;
    }
    if (this.popup.openStartHour === undefined) {
      alert('노출 시작 시간을 선택하세요.');
      return;
    }
    if (this.popup.openEndDate === undefined) {
      alert('노출 종료일을 선택하세요.');
      return;
    }
    if (this.popup.openEndHour === undefined) {
      alert('노출 종료 시간을 선택하세요.');
      return;
    }
    if (this.popup.isOpen === undefined) {
      alert('노출 여부를 선택하세요.');
      return;
    }
    if (this.popup.title === undefined) {
      alert('제목을 입력하세요.');
      return;
    }
    if ((this.popup.popupId === undefined && this.fileUploadComponent.files() === undefined) ||
      (this.popup.popupId !== undefined && this.deleteImgFileId !== undefined && this.fileUploadComponent.files() === undefined)) {
      alert('팝업 이미지를 선택하세요.');
      return;
    }
    if (this.popup.moveType === undefined) {
      alert('연결 타입을 선택하세요.');
      return;
    } else {
      if (this.popup.moveType === 'WU') {
        if (this.popup.moveUrl === undefined) {
          alert('URL을 입력하세요.');
          return;
        }
      } else {
        if (this.popup.moveId === undefined) {
          alert(`연결화면 버튼을 눌러 ${this.popup.moveType === 'NO' ? '공지' : this.popup.moveType === 'ED' ? '교육' : this.popup.moveType === 'SU' ? '설문' : this.popup.moveType}를 선택하세요.`);
          return;
        }
      }
    }

    if (this.popup.popupId === undefined) {
      const req = new RegistPopupReq();
      req.title = this.popup.title;
      req.popupFile = this.fileUploadComponent.files()[0];
      req.idx = this.popup.idx;
      req.moveType = this.popup.moveType;
      req.moveId = this.popup.moveId;
      req.moveUrl = this.popup.moveUrl;
      req.isOpen = this.popup.isOpen;
      req.openStartDate = this.popup.openStartDate;
      req.openStartHour = this.popup.openStartHour;
      req.openEndDate = this.popup.openEndDate;
      req.openEndHour = this.popup.openEndHour;
      this.newsService.registPopup(req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      });
    } else {
      const req = new UpdatePopupReq();
      req.title = this.popup.title;
      if (this.fileUploadComponent.files() !== undefined) {
        req.popupFile = this.fileUploadComponent.files()[0];
      }
      req.idx = this.popup.idx;
      req.moveType = this.popup.moveType;
      req.moveId = this.popup.moveId;
      req.moveUrl = this.popup.moveUrl;
      req.isOpen = this.popup.isOpen;
      req.openStartDate = this.popup.openStartDate;
      req.openStartHour = this.popup.openStartHour;
      req.openEndDate = this.popup.openEndDate;
      req.openEndHour = this.popup.openEndHour;
      this.newsService.updatePopup(this.popup.popupId, req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      });
    }
  }

  imgFile(): AttachFile[] {
    return [this.popup.imgFile];
  }

  private getPopup() {
    const id = +this.route.snapshot.paramMap.get('id');
    if (id !== -1) {
      this.newsService.popup(id).subscribe(response => {
        let resp = response.body;
        if (resp.code !== 200)  this.goBack();

        this.popup = resp.popup;
      })
    }
  }

  private goBack() {
    localStorage.setItem('action', 'back');
    this.location.back();
  }

}
