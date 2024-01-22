import { Injectable } from '@angular/core';
import { HttpService } from 'src/app/http.service';
import { AdminListReq } from './admin-list-req';
import { AdminListResp } from './admin-list-resp';
import { PcResp } from 'src/app/pc-resp';
import { CheckLoginIdReq } from './check-login-id-req';
import { AdminResp } from './admin-resp';
import { RegistAdminReq } from './regist-admin-req';
import { UpdateAdminReq } from './update-admin-req';

@Injectable({
  providedIn: 'root'
})
export class AdminService extends HttpService {
  registAdmin(req: RegistAdminReq) {
    return this.post<PcResp>('/admins', req);
  }
  checkLoginId(req: CheckLoginIdReq) {
    return this.get<PcResp>('/admin/loginId', req);
  }
  adminList(req: AdminListReq) {
    return this.get<AdminListResp>('/admins', req);
  }
  admin(adminId: number) {
    return this.get<AdminResp>(`/admins/${adminId}`);
  }
  updateAdmin(adminId: number, req: UpdateAdminReq) {
    return this.put<PcResp>(`/admins/${adminId}`, req);
  }
}
