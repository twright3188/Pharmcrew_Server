import { Component, OnInit, ViewChild } from '@angular/core';
import { OrganizeSelectorComponent } from 'src/app/widget/organize-selector/organize-selector.component';
import { PaginationComponent } from 'src/app/widget/pagination/pagination.component';
import { VideoListReq } from '../video-list-req';
import { AcademyService } from '../academy.service';
import { Video } from '../video';
import { Router } from '@angular/router';

@Component({
  selector: 'app-video-list',
  templateUrl: './video-list.component.html',
  styleUrls: ['./video-list.component.css']
})
export class VideoListComponent implements OnInit {
  @ViewChild('organizeSelector') organizeSelector: OrganizeSelectorComponent;
  @ViewChild('pagination') pagination: PaginationComponent;

  req = new VideoListReq();
  videos: Video[] = undefined;

  constructor(
    private academyService: AcademyService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    // this.videoList();
  }

  ngAfterViewInit() {
    this.restoreCondition();
    this.videoList();
  }

  onSubmitSearch() {
    this.req.page = 1;
    this.req.organizeId = this.organizeSelector.organizeId();
    this.videoList();
  }

  onClickItem(id: number) {
    this.backupCondition();
    this.router.navigate(['/videos', id]);
  }

  onPageClick(page: number) {
    this.req.page = page;
    this.videoList();
  }

  private videoList() {
    this.academyService.videoList(this.req).subscribe(response => {
      let resp = response.body;
      if (resp.code === 200) {
        this.videos = resp.videos;

        this.pagination.setData(this.req.page, resp.searchCnt, this.req.cntPerPage);
      }
    });
  }

  private backupCondition() {
    localStorage.setItem('search', JSON.stringify(this.req));
    this.organizeSelector.backupCondition();
  }

  private restoreCondition() {
    if (localStorage.getItem('action') === 'back') {
      this.req = JSON.parse(localStorage.getItem('search'));
      this.organizeSelector.restoreCondition();
      localStorage.removeItem('action');
    }
  }

}
