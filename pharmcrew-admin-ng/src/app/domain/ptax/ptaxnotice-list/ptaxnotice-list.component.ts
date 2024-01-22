import { Component, OnInit, ViewChild } from '@angular/core';
import { PtaxService } from '../ptax.service';
import { PaginationComponent } from 'src/app/widget/pagination/pagination.component';
import { PtaxnoticeListReq } from '../ptaxnotice-list-req';
import { Ptaxnotice } from '../ptaxnotice';
import { PcService } from 'src/app/pc.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ptaxnotice-list',
  templateUrl: './ptaxnotice-list.component.html',
  styleUrls: ['./ptaxnotice-list.component.css']
})
export class PtaxnoticeListComponent implements OnInit {
  @ViewChild('pagination') pagination: PaginationComponent;

  req = new PtaxnoticeListReq();
  notices: Ptaxnotice[] = undefined;

  constructor(
    private ptaxService: PtaxService,
    private pcService: PcService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.restoreCondition();
    this.noticeList();
  }
  
  ngAfterViewInit() {
    this.pcService.initDatepicker('datepicker1');
    this.pcService.initDatepicker('datepicker2');
  }

  onChangeTarget() {
    switch (this.req.target) {
      case 'ALL':
      case 'G':
        this.req.targetPharmName = undefined;
        break;
    }
  }

  onSubmitSearch() {
    this.req.page = 1;
    this.noticeList();
  }

  onClickItem(id: number) {
    this.backupCondition();
    this.router.navigate(['/ptaxnotices', id]);
  }

  targetName(idx: number) {
    const notice = this.notices[idx];
    switch (notice.target) {
      case 'ALL':
        return '전체';
      case 'G':
        return '비회원';
      case 'M':
        if (notice.targetPharmName !== undefined) {
          return notice.targetPharmName;
        } else {
          return '회원';
        }
    }
    return '?????';
  }

  onResendClick(idx: number) {
    alert('TODO');
    // const notice = this.notices[idx];
    // if (confirm(`미확인 알림 ${notice.sendCnt - notice.recvCnt}건\r재발송 하시겠습니까?`) == false) return;
    // this.ptaxService.resendPush(notice.noticeId).subscribe(response => {
    //   const resp = response.body;
    //   if (resp.code === 200)  alert('재발송 처리 중입니다.');
    // });
  }

  onPageClick(page: number) {
    this.req.page = page;
    this.noticeList();
  }

  private noticeList() {
    this.ptaxService.noticeList(this.req).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.notices = resp.notices;

        this.pagination.setData(this.req.page, resp.searchCnt, this.req.cntPerPage);
      }
    })
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
