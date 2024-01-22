import { Component, OnInit, ViewChild } from '@angular/core';
import { PaginationComponent } from 'src/app/widget/pagination/pagination.component';
import { BannerListReq } from '../banner-list-req';
import { Banner } from '../banner';
import { NewsService } from '../news.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-banner-list',
  templateUrl: './banner-list.component.html',
  styleUrls: ['./banner-list.component.css']
})
export class BannerListComponent implements OnInit {
  @ViewChild('pagination') pagination: PaginationComponent;

  req: BannerListReq = new BannerListReq();
  banners: Banner[] = undefined;

  constructor(
    private newsService: NewsService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.restoreCondition();
    this.bannerList();
  }

  onSubmitSearch() {
    this.req.page = 1;
    this.bannerList();
  }

  onClickItem(id: number) {
    this.backupCondition();
    this.router.navigate(['/banners', id]);
  }

  onPageClick(page: number) {
    this.req.page = page;
    this.bannerList();
  }

  private bannerList() {
    this.newsService.bannerList(this.req).subscribe(response => {
      let resp = response.body;
      if (resp.code === 200) {
        this.banners = resp.banners;

        this.pagination.setData(this.req.page, resp.searchCnt, this.req.cntPerPage);
      }
    });
  }

  private backupCondition() {
    localStorage.setItem('search', JSON.stringify(this.req));
  }

  private restoreCondition() {
    if (localStorage.getItem('action') === 'back') {
      this.req = JSON.parse(localStorage.getItem('search'));
      localStorage.removeItem('action');
    }
  }

}
