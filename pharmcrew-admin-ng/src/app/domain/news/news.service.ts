import { Injectable } from '@angular/core';
import { HttpService } from 'src/app/http.service';
import { NoticeListReq } from './notice-list-req';
import { NoticeListResp } from './notice-list-resp';
import { NoticeResp } from './notice-resp';
import { RegistNoticeReq } from './regist-notice-req';
import { PcResp } from 'src/app/pc-resp';
import { PopupListReq } from './popup-list-req';
import { PopupListResp } from './popup-list-resp';
import { BannerListReq } from './banner-list-req';
import { BannerListResp } from './banner-list-resp';
import { UpdateNoticeReq } from './update-notice-req';
import { PopupResp } from './popup-resp';
import { RegistPopupReq } from './regist-popup-req';
import { UpdatePopupReq } from './update-popup-req';
import { RegistBannerReq } from './regist-banner-req';
import { UpdateBannerReq } from './update-banner-req';
import { BannerResp } from './banner-resp';
import { QnaListReq } from './qna-list-req';
import { QnaListResp } from './qna-list-resp';
import { QnaResp } from './qna-resp';
import { AnswerQnaReq } from './answer-qna-req';

@Injectable({
  providedIn: 'root'
})
export class NewsService extends HttpService {
  // 공지
  registNotice(req: RegistNoticeReq) {
    return this.postMultipart<PcResp>('/notices', req);
  }
  noticeList(req: NoticeListReq) {
    return this.get<NoticeListResp>('/notices', req);
  }
  notice(noticeId: number) {
    return this.get<NoticeResp>('/notices/' + noticeId);
  }
  updateNotice(noticeId: number, req: UpdateNoticeReq) {
    return this.postMultipart<PcResp>('/notices/' + noticeId, req);
  }
  deleteNotice(noticeId: number) {
    return this.delete<PcResp>(`/notices/${noticeId}`);
  }

  // 팝업
  registPopup(req: RegistPopupReq) {
    return this.postMultipart<PcResp>('/popups', req);
  }
  popupList(req: PopupListReq) {
    return this.get<PopupListResp>('/popups', req);
  }
  popup(popupId: number) {
    return this.get<PopupResp>(`/popups/${popupId}`);
  }
  updatePopup(popupId: number, req: UpdatePopupReq) {
    return this.postMultipart<PcResp>(`/popups/${popupId}`, req);
  }
  deletePopup(popupId: number) {
    return this.delete<PcResp>(`/popups/${popupId}`);
  }

  // 배너
  registBanner(req: RegistBannerReq) {
    return this.postMultipart<PcResp>('/banners', req);
  }
  bannerList(req: BannerListReq) {
    return this.get<BannerListResp>('/banners', req);
  }
  banner(bannerId: number) {
    return this.get<BannerResp>(`/banners/${bannerId}`);
  }
  updateBanner(bannerId: number, req: UpdateBannerReq) {
    return this.postMultipart<PcResp>(`/banners/${bannerId}`, req);
  }
  deleteBanner(bannerId: number) {
    return this.delete<PcResp>(`/banners/${bannerId}`);
  }

  // qna
  qnaList(req: QnaListReq) {
    return this.get<QnaListResp>('/qnas', req);
  }
  qna(qnaId: number) {
    return this.get<QnaResp>(`/qnas/${qnaId}`);
  }
  answerQna(qnaId: number, req: AnswerQnaReq) {
    return this.put<PcResp>(`/qnas/${qnaId}`, req);
  }
}
