import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'widget-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {
  pages: Page[] = [];
  curPage: number;

  @Output() onPage = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
  }

  setData(page: number, totalCnt: number, cntPerPage = 20) {
    // console.log(`page: ${page}, totalCnt: ${totalCnt}, cntPerPage: ${cntPerPage}`);
    this.pages.splice(0, this.pages.length);

    this.curPage = page;
    
    let pageCntShow = 10;

    let pageCntTotal = Math.ceil(totalCnt / cntPerPage);
    let startPage = (Math.floor((page - 1) / pageCntShow) * pageCntShow + 1);
    let endPage = startPage + pageCntShow;
    endPage = Math.min(pageCntTotal, endPage);

    if (startPage > pageCntShow) {
        this.pages.push({ page: 1, name: '처음' });
        this.pages.push({ page: startPage - pageCntShow, name: '이전' });
    }
    for (var i = startPage; i <= endPage; i++) {
        this.pages.push({ page: i, name: i });
    }
    if (endPage < pageCntTotal) {
        this.pages.push({ page: startPage + pageCntShow, name: '다음' });
        this.pages.push({ page: pageCntTotal, name: '마지막' });
    }
    // console.log(`pages: ${this.pages}`);
  }

  liClass(name: string) {
    return name === '처음' || name === '이전' || name === '다음' || name === '마지막' ? 'move' : undefined;
  }

  iClass(name: string) {
    switch (name) {
      case '처음':
        return 'fa fa-angle-double-left';
      case '이전':
        return 'fa fa-angle-left';
      case '다음':
        return 'fa fa-angle-right';
      case '마지막':
        return 'fa fa-angle-double-right';
    }
    return undefined;
  }

  onPageClick(page: number) {
    if (page === this.curPage)  return;
    this.onPage.emit(page);
  }
}

export class Page {
  page: number;
  name: string | number;
}