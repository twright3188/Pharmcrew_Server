import { Component, OnInit, ViewChild } from '@angular/core';
import { PaginationComponent } from 'src/app/widget/pagination/pagination.component';
import { PartnerListReq } from '../partner-list-req';
import { Partner } from '../partner';
import { PartnerService } from '../partner.service';
import { Router } from '@angular/router';

@Component({
  selector: 'partner-partner-list',
  templateUrl: './partner-list.component.html',
  styleUrls: ['./partner-list.component.css']
})
export class PartnerListComponent implements OnInit {
  @ViewChild('pagination') pagination: PaginationComponent;

  req: PartnerListReq = new PartnerListReq();
  partners: Partner[] = undefined;

  constructor(
    private partnerService: PartnerService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.restoreCondition();
    this.partnerList();
  }

  onClickItem(id: number) {
    this.backupCondition();
    this.router.navigate(['/partners', id]);
  }

  onPageClick(page: number) {
    this.req.page = page;
    this.partnerList();
  }

  private partnerList() {
    this.partnerService.partnerList(this.req).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.partners = resp.partners;

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
