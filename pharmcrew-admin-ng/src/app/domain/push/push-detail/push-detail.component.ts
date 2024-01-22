import { Component, OnInit, ViewChild } from '@angular/core';
import { Push } from '../push';
import { PushService } from '../push.service';
import { PcService } from 'src/app/pc.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { AttachFile } from '../../common/attach-file';
import { OrganizeSelectorComponent } from 'src/app/widget/organize-selector/organize-selector.component';
import { FileUploadComponent } from 'src/app/widget/file-upload/file-upload.component';
import { RegistPushReq } from '../regist-push-req';
import { UpdatePushReq } from '../update-push-req';
import { TargetSelectorComponent } from 'src/app/widget/target-selector/target-selector.component';

@Component({
  selector: 'app-push-detail',
  templateUrl: './push-detail.component.html',
  styleUrls: ['./push-detail.component.css']
})
export class PushDetailComponent implements OnInit {
  @ViewChild('organizeSelector') organizeSelector: OrganizeSelectorComponent;
  @ViewChild(FileUploadComponent) fileUploadComponent: FileUploadComponent;
  @ViewChild('targetSelector') targetSelector: TargetSelectorComponent;

  categoryType: string = undefined;
  sendType = 'I'; // R(eserve): 예약, I(mmediate): 즉시
  
  push: Push = new Push();

  deleteImgFileId: number = undefined;

  constructor(
    private pushService: PushService,
    private pcService: PcService,
    private router: Router,
    private route: ActivatedRoute,
    private location: Location,
  ) { }

  ngOnInit(): void {
    console.log(`url: ${this.router.url}`);
    if (this.router.url.startsWith('/admsgs')) {
      this.categoryType = 'AD';
    } else if (this.router.url.startsWith('/pushes')) {
      this.categoryType = 'NT';
    }
    this.getPush();
  }
  
  ngAfterViewInit() {
    this.pcService.initDatepicker('datepicker1');
  }

  onChangeSendType() {
    switch (this.sendType) {
      case 'I':
        this.push.reserveDate = undefined;
        this.push.reserveHour = undefined;
        this.push.reserveMinute = undefined;
        break;
    }
  }

  onDeleteAttach(idx: number) {
    this.deleteImgFileId = this.push.imgFile.fileId;
    this.push.imgFile = undefined;
  }

  onViewAttach(idx: number) {
    this.pushService.downloadFile(this.push.imgFile.fileId);
  }

  onChangeMoveType() {
    this.push.moveId = undefined;
    this.push.moveUrl = undefined;
  }

  onMoveTargetClick() {
    this.targetSelector.setType(this.push.moveType);
    this.targetSelector.show();
  }

  onSelectMoveTarget(id: number) {
    this.push.moveId = id;
  }
  
  onBackClick() {
    this.goBack();
  }

  onDeleteClick() {
    if (!confirm('삭제하시겠습니까?'))  return;
    this.pushService.deletePush(this.push.pushId).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200)  this.goBack();
    });
  }

  onSaveClick() {
    if (this.sendType === undefined) {
      alert('발송 옵션을 선택하세요.');
      return;
    }
    if (this.push.osType === undefined) {
      alert('발송 OS를 선택하세요.');
      return;
    }
    if (this.sendType === 'R') {
      if (this.push.reserveDate === undefined) {
        alert('발송 일자를 선택하세요.');
        return;
      }
      if (this.push.reserveHour === undefined) {
        alert('발송 시간을 선택하세요.');
        return;
      }
      if (this.push.reserveMinute === undefined) {
        alert('발송 분을 선택하세요.');
        return;
      }
    }
    if (this.push.title === undefined) {
      alert('푸시 제목을 입력하세요.');
      return;
    }
    if (this.push.body === undefined) {
      alert('푸시 내용을 입력하세요.');
      return;
    }
    if (this.push.moveType === undefined) {
      alert('연결 타입을 선택하세요.');
      return;
    } else {
      switch (this.push.moveType) {
        case 'WU':
          if (this.push.moveUrl === undefined) {
            alert('URL을 입력하세요.');
            return;
          }
          break;
        case 'NO':
          if (this.push.moveId === undefined) {
            alert('연결화면 버튼을 눌러 공지를 선택하세요.');
            return;
          }
          break;
        case 'ED':
          if (this.push.moveId === undefined) {
            alert('연결화면 버튼을 눌러 교육을 선택하세요.');
            return;
          }
          break;
        case 'SU':
          if (this.push.moveId === undefined) {
            alert('연결화면 버튼을 눌러 설문을 선택하세요.');
            return;
          }
          break;
        }
    }

    if (this.push.pushId === undefined) {
      const req = new RegistPushReq();
      req.title = this.push.title;
      req.body = this.push.body;
      if (this.fileUploadComponent.files() !== undefined) {
        req.imgFile = this.fileUploadComponent.files()[0];
      }
      req.reserveDate = this.push.reserveDate;
      req.reserveHour = this.push.reserveHour;
      req.reserveMinute = this.push.reserveMinute;
      req.organizeId = this.organizeSelector.organizeId();
      req.osType = this.push.osType;
      req.categoryType = this.categoryType;
      req.moveType = this.push.moveType;
      req.moveId = this.push.moveId;
      req.moveUrl = this.push.moveUrl;
      this.pushService.registPush(req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      });
    } else {
      const req = new UpdatePushReq();
      req.title = this.push.title;
      req.body = this.push.body;
      req.delImgFileId = this.deleteImgFileId;
      if (this.fileUploadComponent.files() !== undefined) {
        req.imgFile = this.fileUploadComponent.files()[0];
      }
      req.reserveDate = this.push.reserveDate;
      req.reserveHour = this.push.reserveHour;
      req.reserveMinute = this.push.reserveMinute;
      req.organizeId = this.organizeSelector.organizeId();
      req.osType = this.push.osType;
      req.categoryType = this.push.categoryType;
      req.moveType = this.push.moveType;
      req.moveId = this.push.moveId;
      req.moveUrl = this.push.moveUrl;
      this.pushService.updatePush(this.push.pushId, req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      });
    }
  }

  imgFile(): AttachFile[] {
    return [this.push.imgFile];
  }

  private getPush() {
    const id = +this.route.snapshot.paramMap.get('id');
    if (id !== -1) {
      this.pushService.push(id).subscribe(response => {
        let resp = response.body;
        if (resp.code !== 200)  this.goBack();

        this.push = resp.push;
        if (this.push.reserveDate === undefined) {
          this.sendType = 'I';
        } else {
          this.sendType = 'R';
        }

        if (this.push.osType === undefined) {
          this.push.osType = 'ALL';
        }

        if (this.push.moveType === undefined) {
          this.push.moveType = 'NONE';
        }

        if (this.push.organize !== undefined) {
          this.organizeSelector.setOrganizeIds(this.push.organize.ids);
        }
      })
    }
  }

  private goBack() {
    localStorage.setItem('action', 'back');
    this.location.back();
  }

}
