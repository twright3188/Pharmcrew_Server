import { Component, OnInit, ViewChild } from '@angular/core';
import { NewsService } from '../news.service';
import { NoticeListReq } from '../notice-list-req';
import { Notice } from '../notice';
import { OrganizeSelectorComponent } from 'src/app/widget/organize-selector/organize-selector.component';
import { PaginationComponent } from 'src/app/widget/pagination/pagination.component';
import { Router } from '@angular/router';

@Component({
  selector: 'news-notice-list',
  templateUrl: './notice-list.component.html',
  styleUrls: ['./notice-list.component.css']
})
export class NoticeListComponent implements OnInit {
  @ViewChild('organizeSelector') organizeSelector: OrganizeSelectorComponent;
  @ViewChild('pagination') pagination: PaginationComponent;

  req: NoticeListReq = new NoticeListReq();
  notices: Notice[] = undefined;

  constructor(
    private newsService: NewsService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    // this.restoreCondition();
    // this.noticeList();
  }

  ngAfterViewInit() {
    this.restoreCondition();
    this.noticeList();
  }

  onSubmitSearch() {
    this.req.page = 1;
    this.req.organizeId = this.organizeSelector.organizeId();
    this.noticeList();
  }

  onClickItem(id: number) {
    this.backupCondition();
    this.router.navigate(['/notices', id]);
  }

  onPageClick(page: number) {
    this.req.page = page;
    this.noticeList();
  }

  private noticeList() {
    this.newsService.noticeList(this.req).subscribe(response => {
      let resp = response.body;
      if (resp.code === 200) {
        this.notices = resp.notices;

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
