import { Injectable } from '@angular/core';
import { HttpService } from 'src/app/http.service';
import { OrganizeListReq } from './organize-list-req';
import { OrganizeListResp } from './organize-list-resp';
import { PharmListResp } from './pharm-list-resp';

@Injectable({
  providedIn: 'root'
})
export class CommonService extends HttpService {
  organizeList(req: OrganizeListReq) {
    return this.get<OrganizeListResp>('/organizes', req);
  }
  pharmList() {
    return this.get<PharmListResp>('/pharms');
  }
}
