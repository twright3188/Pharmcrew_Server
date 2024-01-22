import { Component, OnInit, ViewChild } from '@angular/core';
import { PushListReq } from '../push-list-req';
import { PushService } from '../push.service';
import { Push } from '../push';
import { PaginationComponent } from 'src/app/widget/pagination/pagination.component';
import { OrganizeSelectorComponent } from 'src/app/widget/organize-selector/organize-selector.component';
import { PcService } from 'src/app/pc.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-push-list',
  templateUrl: './push-list.component.html',
  styleUrls: ['./push-list.component.css']
})
export class PushListComponent implements OnInit {
  @ViewChild('organizeSelector') organizeSelector: OrganizeSelectorComponent;
  @ViewChild('pagination') pagination: PaginationComponent;

  req = new PushListReq();
  pushes: Push[] = undefined;

  constructor(
    private pushService: PushService,
    private pcService: PcService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    console.log(`url: ${this.router.url}`);
    switch (this.router.url) {
      case '/admsgs':
        this.req.categoryType = 'AD';
        break;
      case '/pushes':
        this.req.categoryType = 'NT';
        break;
    }
    // this.restoreCondition();
    // this.pushList();
  }
  
  ngAfterViewInit() {
    this.pcService.initDatepicker('datepicker1');
    this.pcService.initDatepicker('datepicker2');
    this.restoreCondition();
    this.pushList();
  }

  onSubmitSearch() {
    this.req.page = 1;
    this.req.organizeId = this.organizeSelector.organizeId();
    this.pushList();
  }

  onClickDetail(id: number) {
    this.backupCondition();
    switch (this.req.categoryType) {
      case 'AD':
        this.router.navigate(['/admsgs', id]);
        break;
      case 'NT':
        this.router.navigate(['/pushes', id]);
        break;
    }
  }

  onResendClick(idx: number) {
    const push = this.pushes[idx];
    if (confirm(`미확인 알림 ${push.sendCnt - push.recvCnt}건\r재발송 하시겠습니까?`) == false) return;
    this.pushService.resendPush(push.pushId).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200)  alert('재발송 처리 중입니다.');
    });
  }

  onPageClick(page: number) {
    this.req.page = page;
    this.pushList();
  }

  private pushList() {
    this.pushService.pushList(this.req).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.pushes = resp.pushes;

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
