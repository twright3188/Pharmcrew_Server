import { Component, OnInit, ViewChild } from '@angular/core';
import { AdminService } from '../admin.service';
import { AdminListReq } from '../admin-list-req';
import { Admin } from '../admin';
import { PaginationComponent } from 'src/app/widget/pagination/pagination.component';
import { Router } from '@angular/router';

@Component({
  selector: 'admin-admin-list',
  templateUrl: './admin-list.component.html',
  styleUrls: ['./admin-list.component.css']
})
export class AdminListComponent implements OnInit {
  @ViewChild('pagination') pagination: PaginationComponent;

  req = new AdminListReq();
  admins: Admin[] = undefined;

  constructor(
    private adminService: AdminService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.restoreCondition();
    this.adminList();
  }

  onSubmitSearch() {
    this.req.page = 1;
    this.adminList();
  }

  onClickDetail(id: number) {
    this.backupCondition();
    this.router.navigate(['/admins', id]);
  }

  onPageClick(page: number) {
    this.req.page = page;
    this.adminList();
  }

  private adminList() {
    this.adminService.adminList(this.req).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.admins = resp.admins;

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
