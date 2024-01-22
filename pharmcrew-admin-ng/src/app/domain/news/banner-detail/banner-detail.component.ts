import { Component, OnInit, ViewChild } from '@angular/core';
import { NewsService } from '../news.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Banner } from '../banner';
import { AttachFile } from '../../common/attach-file';
import { PcService } from 'src/app/pc.service';
import { FileUploadComponent } from 'src/app/widget/file-upload/file-upload.component';
import { RegistBannerReq } from '../regist-banner-req';
import { UpdateBannerReq } from '../update-banner-req';
import { TargetSelectorComponent } from 'src/app/widget/target-selector/target-selector.component';

@Component({
  selector: 'app-banner-detail',
  templateUrl: './banner-detail.component.html',
  styleUrls: ['./banner-detail.component.css']
})
export class BannerDetailComponent implements OnInit {
  @ViewChild(FileUploadComponent) fileUploadComponent: FileUploadComponent;
  @ViewChild('targetSelector') targetSelector: TargetSelectorComponent;
  
  banner: Banner = new Banner();

  deleteImgFileId: number = undefined;

  constructor(
    private newsService: NewsService,
    private pcService: PcService,
    private route: ActivatedRoute,
    private location: Location,
  ) { }

  ngOnInit(): void {
    this.getBanner();
  }
  
  ngAfterViewInit() {
    this.pcService.initDatepicker('datepicker1');
    this.pcService.initDatepicker('datepicker2');
  }

  onChangeOpenStartDate(value: string) {
    console.log(`openStartDate: ${this.banner.openStartDate}, value: ${value}`);
    if (this.banner.openEndDate !== undefined) {
      const startDate = new Date(value.replace('.', '-'));
      const endDate = new Date(this.banner.openEndDate.replace('.', '-'));
      const diff = endDate.getTime() - startDate.getTime();
      if (diff < 0) {
        alert('시작일이 종료일 이전입니다.');
        this.pcService.set('datepicker1', this.banner.openStartDate);
        return;
      }
    }
    this.banner.openStartDate = value;
  }

  onChangeOpenEndDate(value: string) {
    console.log(`openEndDate: ${this.banner.openEndDate}, value: ${value}`);
    if (this.banner.openStartDate !== undefined) {
      const startDate = new Date(this.banner.openStartDate.replace('.', '-'));
      const endDate = new Date(value.replace('.', '-'));
      const diff = endDate.getTime() - startDate.getTime();
      if (diff < 0) {
        alert('종료일이 시작일 이전입니다.');
        this.pcService.set('datepicker2', this.banner.openEndDate);
        return;
      }
    }
    this.banner.openEndDate = value;
  }

  onDeleteAttach(idx: number) {
    this.deleteImgFileId = this.banner.imgFile.fileId;
    this.banner.imgFile = undefined;
  }

  onViewAttach(idx: number) {
    this.newsService.downloadFile(this.banner.imgFile.fileId);
  }

  onChangeMoveType() {
    this.banner.moveId = undefined;
    this.banner.moveUrl = undefined;
  }

  onMoveTargetClick() {
    this.targetSelector.setType(this.banner.moveType);
    this.targetSelector.show();
  }

  onSelectMoveTarget(id: number) {
    this.banner.moveId = id;
  }
  
  onBackClick() {
    this.goBack();
  }

  onDeleteClick() {
    if (!confirm('삭제하시겠습니까?'))  return;
    this.newsService.deleteBanner(this.banner.bannerId).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200)  this.goBack();
    });
  }

  onSaveClick() {
    if (this.banner.openStartDate === undefined) {
      alert('노출 시작일을 선택하세요.');
      return;
    }
    if (this.banner.openStartHour === undefined) {
      alert('노출 시작 시간을 선택하세요.');
      return;
    }
    if (this.banner.openEndDate === undefined) {
      alert('노출 종료일을 선택하세요.');
      return;
    }
    if (this.banner.openEndHour === undefined) {
      alert('노출 종료 시간을 선택하세요.');
      return;
    }
    if (this.banner.isOpen === undefined) {
      alert('노출 여부를 선택하세요.');
      return;
    }
    if (this.banner.title === undefined) {
      alert('제목을 입력하세요.');
      return;
    }
    if ((this.banner.bannerId === undefined && this.fileUploadComponent.files() === undefined) ||
      (this.banner.bannerId !== undefined && this.deleteImgFileId !== undefined && this.fileUploadComponent.files() === undefined)) {
        alert('팝업 이미지를 선택하세요.');
      return;
    }
    if (this.banner.moveType === undefined) {
      alert('연결 타입을 선택하세요.');
      return;
    } else {
      if (this.banner.moveType === 'WU') {
        if (this.banner.moveUrl === undefined) {
          alert('URL을 입력하세요.');
          return;
        }
      } else {
        if (this.banner.moveId === undefined) {
          alert(`연결화면 버튼을 눌러 ${this.banner.moveType === 'NO' ? '공지' : this.banner.moveType === 'ED' ? '교육' : this.banner.moveType === 'SU' ? '설문' : this.banner.moveType}를 선택하세요.`);
          return;
        }
      }
    }

    if (this.banner.bannerId === undefined) {
      const req = new RegistBannerReq();
      req.title = this.banner.title;
      req.bannerFile = this.fileUploadComponent.files()[0];
      req.idx = this.banner.idx;
      req.moveType = this.banner.moveType;
      req.moveId = this.banner.moveId;
      req.moveUrl = this.banner.moveUrl;
      req.isOpen = this.banner.isOpen;
      req.openStartDate = this.banner.openStartDate;
      req.openStartHour = this.banner.openStartHour;
      req.openEndDate = this.banner.openEndDate;
      req.openEndHour = this.banner.openEndHour;
      this.newsService.registBanner(req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      });
    } else {
      const req = new UpdateBannerReq();
      req.title = this.banner.title;
      if (this.fileUploadComponent.files() !== undefined) {
        req.bannerFile = this.fileUploadComponent.files()[0];
      }
      req.idx = this.banner.idx;
      req.moveType = this.banner.moveType;
      req.moveId = this.banner.moveId;
      req.moveUrl = this.banner.moveUrl;
      req.isOpen = this.banner.isOpen;
      req.openStartDate = this.banner.openStartDate;
      req.openStartHour = this.banner.openStartHour;
      req.openEndDate = this.banner.openEndDate;
      req.openEndHour = this.banner.openEndHour;
      this.newsService.updateBanner(this.banner.bannerId, req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      });
    }
  }

  imgFile(): AttachFile[] {
    return [this.banner.imgFile];
  }

  private getBanner() {
    const id = +this.route.snapshot.paramMap.get('id');
    if (id !== -1) {
      this.newsService.banner(id).subscribe(response => {
        let resp = response.body;
        if (resp.code !== 200)  this.goBack();

        this.banner = resp.banner;
      })
    }
  }

  private goBack() {
    localStorage.setItem('action', 'back');
    this.location.back();
  }

}
