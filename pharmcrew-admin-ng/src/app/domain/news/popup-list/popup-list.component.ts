import { Component, OnInit, ViewChild } from '@angular/core';
import { PaginationComponent } from 'src/app/widget/pagination/pagination.component';
import { PopupListReq } from '../popup-list-req';
import { Popup } from '../popup';
import { NewsService } from '../news.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-popup-list',
  templateUrl: './popup-list.component.html',
  styleUrls: ['./popup-list.component.css']
})
export class PopupListComponent implements OnInit {
  @ViewChild('pagination') pagination: PaginationComponent;

  req: PopupListReq = new PopupListReq();
  popups: Popup[] = undefined;

  constructor(
    private newsService: NewsService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.restoreCondition();
    this.popupList();
  }

  onClickItem(id: number) {
    this.backupCondition();
    this.router.navigate(['/popups', id]);
  }

  onSubmitSearch() {
    this.req.page = 1;
    this.popupList();
  }

  onPageClick(page: number) {
    this.req.page = page;
    this.popupList();
  }

  private popupList() {
    this.newsService.popupList(this.req).subscribe(response => {
      let resp = response.body;
      if (resp.code === 200) {
        this.popups = resp.popups;

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
