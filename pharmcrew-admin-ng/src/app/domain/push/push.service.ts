import { Injectable } from '@angular/core';
import { HttpService } from 'src/app/http.service';
import { RegistPushReq } from './regist-push-req';
import { PcResp } from 'src/app/pc-resp';
import { PushListReq } from './push-list-req';
import { PushListResp } from './push-list-resp';
import { PushResp } from './push-resp';
import { UpdatePushReq } from './update-push-req';
import { ResendPushReq } from './resend-push-req';

@Injectable({
  providedIn: 'root'
})
export class PushService extends HttpService {
  registPush(req: RegistPushReq) {
    return this.postMultipart<PcResp>('/pushes', req);
  }
  pushList(req: PushListReq) {
    return this.get<PushListResp>('/pushes', req);
  }
  push(pushId: number) {
    return this.get<PushResp>(`/pushes/${pushId}`);
  }
  updatePush(pushId: number, req: UpdatePushReq) {
    return this.postMultipart<PcResp>(`/pushes/${pushId}`, req);
  }
  deletePush(pushId: number) {
    return this.delete<PcResp>(`/pushes/${pushId}`, undefined);
  }
  resendPush(pushId: number) {
    const req = new ResendPushReq();  // 타입이 무조건 P이므로
    console.log(`req: ${JSON.stringify(req)}`);
    return this.post<PcResp>(`/pushes/${pushId}/resend`, req);
  }
}
