import { Injectable } from '@angular/core';
import { HttpService } from 'src/app/http.service';
import { MeResp } from './me-resp';
import { PcResp } from 'src/app/pc-resp';
import { UpdateMeReq } from './update-me-req';
import { FindLoginIdReq } from 'src/app/domain/me/find-login-id-req';
import { FindLoginIdResp } from 'src/app/domain/me/find-login-id-resp';
import { ResetPasswordReq } from './reset-password-req';

@Injectable({
  providedIn: 'root'
})
export class MeService extends HttpService {
  findLoginId(req: FindLoginIdReq) {
    return this.get<FindLoginIdResp>('/loginId', req);
  }
  resetPassword(req: ResetPasswordReq) {
    return this.put<PcResp>('/password/reset', req);
  }
  me() {
    return this.get<MeResp>('/me');
  }
  updateMe(req: UpdateMeReq) {
    return this.put<PcResp>('/me', req);
  }
}
