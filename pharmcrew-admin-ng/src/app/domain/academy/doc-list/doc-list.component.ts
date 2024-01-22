import { Component, OnInit, ViewChild } from '@angular/core';
import { OrganizeSelectorComponent } from 'src/app/widget/organize-selector/organize-selector.component';
import { PaginationComponent } from 'src/app/widget/pagination/pagination.component';
import { DocListReq } from '../doc-list-req';
import { Doc } from '../doc';
import { AcademyService } from '../academy.service';
import { Router } from '@angular/router';

@Component({
  selector: 'academy-doc-list',
  templateUrl: './doc-list.component.html',
  styleUrls: ['./doc-list.component.css']
})
export class DocListComponent implements OnInit {
  @ViewChild('organizeSelector') organizeSelector: OrganizeSelectorComponent;
  @ViewChild('pagination') pagination: PaginationComponent;

  req = new DocListReq();
  docs: Doc[] = undefined;

  constructor(
    private academyService: AcademyService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    // this.docList();
  }

  ngAfterViewInit() {
    this.restoreCondition();
    this.docList();
  }

  onSubmitSearch() {
    this.req.page = 1;
    this.req.organizeId = this.organizeSelector.organizeId();
    this.docList();
  }

  onClickItem(id: number) {
    this.backupCondition(); 
    this.router.navigate(['/docs', id]);
  }

  onPageClick(page: number) {
    this.req.page = page;
    this.docList();
  }

  private docList() {
    this.academyService.docList(this.req).subscribe(response => {
      let resp = response.body;
      if (resp.code === 200) {
        this.docs = resp.docs;

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
