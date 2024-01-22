import { Injectable } from '@angular/core';
import { HttpService } from '../http.service';
import { LoginReq } from './login-req';
import { LoginResp } from './login-resp';
import { PcResp } from '../pc-resp';

@Injectable({
  providedIn: 'root'
})
export class SessionService extends HttpService {
  // isLoggedIn = false;

  /**
   * 사용자가 로그인한 후에 이동할 URL
   */
  // redirectUrl: string;

  login(req: LoginReq) {
    return this.post<LoginResp>('/sessions', req);
  }
  logout() {
    return this.delete<PcResp>('/session');
  }
}
