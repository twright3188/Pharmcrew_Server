import { Component, OnInit, ViewChild } from '@angular/core';
import { PtaxqnaListReq } from '../ptaxqna-list-req';
import { Ptaxqna } from '../ptaxqna';
import { PaginationComponent } from 'src/app/widget/pagination/pagination.component';
import { PcService } from 'src/app/pc.service';
import { PtaxService } from '../ptax.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ptaxqna-list',
  templateUrl: './ptaxqna-list.component.html',
  styleUrls: ['./ptaxqna-list.component.css']
})
export class PtaxqnaListComponent implements OnInit {
  @ViewChild('pagination') pagination: PaginationComponent;
  
  req = new PtaxqnaListReq();
  qnas: Ptaxqna[] = undefined;

  constructor(
    private ptaxService: PtaxService,
    private pcService: PcService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.restoreCondition();
    this.qnaList();
  }
  
  ngAfterViewInit() {
    this.pcService.initDatepicker('datepicker1');
    this.pcService.initDatepicker('datepicker2');
  }

  onChangeTarget() {
    switch (this.req.target) {
      case 'M':
        break;
      default:
        this.req.pharmName = undefined;
        break;
    }
  }

  onSubmitSearch() {
    this.req.page = 1;
    this.qnaList();
  }

  onClickItem(id: number) {
    this.backupCondition();
    this.router.navigate(['/ptaxqnas', id]);
  }

  onPageClick(page: number) {
    this.req.page = page;
    this.qnaList();
  }

  private qnaList() {
    this.ptaxService.qnaList(this.req).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.qnas = resp.qnas;

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
