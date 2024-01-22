import { Injectable } from '@angular/core';
import { HttpService } from 'src/app/http.service';
import { PcResp } from 'src/app/pc-resp';
import { RegistPartnerReq } from './regist-partner-req';
import { PartnerListReq } from './partner-list-req';
import { PartnerListResp } from './partner-list-resp';
import { PartnerResp } from './partner-resp';
import { UpdatePartnerReq } from './update-partner-req';

@Injectable({
  providedIn: 'root'
})
export class PartnerService extends HttpService {
  registPartner(req: RegistPartnerReq) {
    return this.postMultipart<PcResp>('/partners', req);
  }
  partnerList(req: PartnerListReq) {
    return this.get<PartnerListResp>('/partners', req);
  }
  partner(partnerId: number) {
    return this.get<PartnerResp>(`/partners/${partnerId}`);
  }
  updatePartner(partnerId: number, req: UpdatePartnerReq) {
    return this.postMultipart<PcResp>(`/partners/${partnerId}`, req);
  }
  deleteParnter(partnerId: number) {
    return this.delete<PcResp>(`/partners/${partnerId}`);
  }
}
