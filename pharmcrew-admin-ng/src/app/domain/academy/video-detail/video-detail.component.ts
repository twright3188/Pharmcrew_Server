import { Component, OnInit, ViewChild } from '@angular/core';
import { OrganizeSelectorComponent } from 'src/app/widget/organize-selector/organize-selector.component';
import { Video } from '../video';
import { AcademyService } from '../academy.service';
import { PcService } from 'src/app/pc.service';
import { ActivatedRoute } from '@angular/router';
import { RegisterVideoReq } from '../register-video-req';
import { UpdateVideoReq } from '../update-video-req';
import { Location } from '@angular/common';

@Component({
  selector: 'app-video-detail',
  templateUrl: './video-detail.component.html',
  styleUrls: ['./video-detail.component.css']
})
export class VideoDetailComponent implements OnInit {
  @ViewChild('organizeSelector') organizeSelector: OrganizeSelectorComponent;
  
  video = new Video();

  constructor(
    private academyService: AcademyService,
    private pcService: PcService,
    private route: ActivatedRoute,
    private location: Location,
  ) { }

  ngOnInit(): void {
    this.getVideo();
  }

  onBackClick() {
    this.goBack();
  }

  onDeleteClick() {
    if (!confirm('삭제하시겠습니까?'))  return;
    this.academyService.deleteVideo(this.video.videoId).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200)  this.goBack();
    })
  }

  onSaveClick() {
    if (this.video.openYn === undefined) {
      alert('노출 여부를 선택하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.video.title)) {
      alert('제목을 입력하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.video.url)) {
      alert('Youtube 화면 ID를 입력하세요.');
      return;
    }

    if (this.video.videoId === undefined) {
      const req = new RegisterVideoReq();
      req.organizeId = this.organizeSelector.organizeId();
      req.title = this.video.title;
      req.url = this.video.url;
      req.isOpen = this.video.openYn;
      this.academyService.registerVideo(req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      });
    } else {
      const req = new UpdateVideoReq();
      req.organizeId = this.organizeSelector.organizeId();
      req.title = this.video.title;
      req.url = this.video.url;
      req.isOpen = this.video.openYn;
      this.academyService.updateVideo(this.video.videoId, req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      })
    }
  }

  private getVideo() {
    const id = +this.route.snapshot.paramMap.get('id');
    if (id !== -1) {
      this.academyService.video(id).subscribe(response => {
        let resp = response.body;
        if (resp.code !== 200)  this.goBack();

        this.video = resp.video;

        if (this.video.organize !== undefined) {
          this.organizeSelector.setOrganizeIds(this.video.organize.ids);
        }
      });
    }
  }

  private goBack() {
    localStorage.setItem('action', 'back');
    this.location.back();
  }

}
