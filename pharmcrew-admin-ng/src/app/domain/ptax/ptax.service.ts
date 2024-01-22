import { Injectable } from '@angular/core';
import { HttpService } from 'src/app/http.service';
import { PtaxqnaListReq } from './ptaxqna-list-req';
import { PtaxqnaListResp } from './ptaxqna-list-resp';
import { PtaxqnaResp } from './ptaxqna-resp';
import { AnswerPtaxqnaReq } from './answer-ptaxqna-req';
import { PcResp } from 'src/app/pc-resp';
import { PtaxnoticeListReq } from './ptaxnotice-list-req';
import { PtaxnoticeListResp } from './ptaxnotice-list-resp';
import { RegisterPtaxnoticeReq } from './register-ptaxnotice-req';
import { PtaxnoticeResp } from './ptaxnotice-resp';
import { UpdatePtaxnoticeReq } from './update-ptaxnotice-req';

@Injectable({
  providedIn: 'root'
})
export class PtaxService extends HttpService {
  // QNA
  qnaList(req: PtaxqnaListReq) {
    return this.get<PtaxqnaListResp>('/ptaxqnas', req);
  }
  qna(qnaId: number) {
    return this.get<PtaxqnaResp>(`/ptaxqnas/${qnaId}`);
  }
  answerQna(qnaId: number, req: AnswerPtaxqnaReq) {
    return this.postMultipart<PcResp>(`/ptaxqnas/${qnaId}`, req);
  }
  // 공지
  registerNotice(req: RegisterPtaxnoticeReq) {
    return this.postMultipart<PcResp>('/ptaxnotices', req);
  }
  noticeList(req: PtaxnoticeListReq) {
    return this.get<PtaxnoticeListResp>('/ptaxnotices', req);
  }
  notice(noticeId: number) {
    return this.get<PtaxnoticeResp>(`/ptaxnotices/${noticeId}`);
  }
  updateNotice(noticeId: number, req: UpdatePtaxnoticeReq) {
    return this.postMultipart<PcResp>(`/ptaxnotices/${noticeId}`, req);
  }
  deleteNotice(noticeId: number) {
    return this.delete<PcResp>(`/ptaxnotices/${noticeId}`);
  }
}
