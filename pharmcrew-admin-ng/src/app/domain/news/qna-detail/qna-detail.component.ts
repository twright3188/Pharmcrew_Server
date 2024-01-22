import { Component, OnInit } from '@angular/core';
import { NewsService } from '../news.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { Qna } from '../qna';
import { AnswerQnaReq } from '../answer-qna-req';

@Component({
  selector: 'app-qna-detail',
  templateUrl: './qna-detail.component.html',
  styleUrls: ['./qna-detail.component.css']
})
export class QnaDetailComponent implements OnInit {
  type: string = undefined;
  qna = new Qna();

  modify = false;

  constructor(
    private newsService: NewsService,
    private router: Router,
    private route: ActivatedRoute,
    private location: Location,
  ) { }

  ngOnInit(): void {
    if (this.router.url.startsWith('/ntcqnas')) {
      this.type = 'N';
    } else if (this.router.url.startsWith('/eduqnas')) {
      this.type = 'E';
    } else if (this.router.url.startsWith('/qnas')) {
      this.type = 'OTHER';
    }
    this.getQna();
  }

  onBackClick() {
    this.goBack();
  }

  onSaveClick() {
    if (this.qna.answerTitle === undefined) {
      alert('답변 제목을 입력하세요.');
      return;
    }
    if (this.qna.answerBody === undefined) {
      alert('답변 내용을 입력하세요.');
      return;
    }
    const req = new AnswerQnaReq();
    req.answerTitle = this.qna.answerTitle;
    req.answerBody = this.qna.answerBody;
    this.newsService.answerQna(this.qna.qnaId, req).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.goBack();
      }
    });
  }

  onModifyClick() {
    this.modify = true;
  }

  private getQna() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.newsService.qna(id).subscribe(response => {
      const resp = response.body;
      if (resp.code !== 200)  this.goBack();

      this.qna = resp.qna;
    })
  }

  private goBack() {
    localStorage.setItem('action', 'back');
    this.location.back();
  }

}
