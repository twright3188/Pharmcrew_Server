import { Component, OnInit, ViewChild } from '@angular/core';
import { NewsService } from '../news.service';
import { QnaListReq } from '../qna-list-req';
import { Qna } from '../qna';
import { OrganizeSelectorComponent } from 'src/app/widget/organize-selector/organize-selector.component';
import { PaginationComponent } from 'src/app/widget/pagination/pagination.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-qna-list',
  templateUrl: './qna-list.component.html',
  styleUrls: ['./qna-list.component.css']
})
export class QnaListComponent implements OnInit {
  @ViewChild('organizeSelector') organizeSelector: OrganizeSelectorComponent;
  @ViewChild('pagination') pagination: PaginationComponent;
  
  req = new QnaListReq();
  qnas: Qna[] = undefined;

  constructor(
    private newsService: NewsService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    switch (this.router.url) {
      case '/ntcqnas':
        this.req.type = 'N';
        break;
      case '/eduqnas':
        this.req.type = 'E';
        break;
      default:
        this.req.type = 'OTHER';
        break;
    }
    // this.qnaList();
  }

  ngAfterViewInit() {
    this.restoreCondition();
    this.qnaList();
  }

  onSubmitSearch() {
    this.req.page = 1;
    this.req.organizeId = this.organizeSelector.organizeId();
    this.qnaList();
  }

  onClickItem(id: number) {
    this.backupCondition();
    switch (this.req.type) {
      case 'N':
        this.router.navigate(['/ntcqnas', id]);
        break;
      case 'E':
        this.router.navigate(['/eduqnas', id]);
        break;
      case 'OTHER':
        this.router.navigate(['/qnas', id]);
        break;
    }
  }

  onPageClick(page: number) {
    this.req.page = page;
    this.qnaList();
  }

  private qnaList() {
    this.newsService.qnaList(this.req).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.qnas = resp.qnas;

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
