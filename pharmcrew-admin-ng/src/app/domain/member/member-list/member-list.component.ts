import { Component, OnInit, ViewChild } from '@angular/core';
import { Member } from '../member';
import { MemberListReq } from '../member-list-req';
import { MemberService } from '../member.service';
import { OrganizeSelectorComponent } from 'src/app/widget/organize-selector/organize-selector.component';
import { PaginationComponent } from 'src/app/widget/pagination/pagination.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-member-list',
  templateUrl: './member-list.component.html',
  styleUrls: ['./member-list.component.css']
})
export class MemberListComponent implements OnInit {
  @ViewChild('organizeSelector') organizeSelector: OrganizeSelectorComponent;
  @ViewChild('pagination') pagination: PaginationComponent;
  
  req = new MemberListReq()
  members: Member[] = undefined;

  constructor(
    private memberService: MemberService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    // this.memberList();
  }

  ngAfterViewInit() {
    this.restoreCondition();
    this.memberList();
  }

  onSubmitSearch() {
    this.req.page = 1;
    this.req.organizeId = this.organizeSelector.organizeId();
    this.memberList();
  }

  onClickItem(id: number) {
    this.backupCondition();
    this.router.navigate(['/members', id]);
  }

  onPageClick(page: number) {
    this.req.page = page;
    this.memberList();
  }

  private memberList() {
    this.memberService.memberList(this.req).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.members = resp.members;

        this.pagination.setData(this.req.page, resp.searchCnt, this.req.cntPerPage);
      }
    })
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
