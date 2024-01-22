import { Injectable } from '@angular/core';
import { HttpService } from 'src/app/http.service';
import { MemberListReq } from './member-list-req';
import { MemberListResp } from './member-list-resp';
import { MemberResp } from './member-resp';
import { MemberEduTimeReq } from './member-edu-time-req';
import { MemberEduTimeResp } from './member-edu-time-resp';

@Injectable({
  providedIn: 'root'
})
export class MemberService extends HttpService {
  memberList(req: MemberListReq) {
    return this.get<MemberListResp>('/members', req);
  }
  member(memberId: number) {
    return this.get<MemberResp>(`/members/${memberId}`);
  }
  memberEduTime(memberId: number, req: MemberEduTimeReq) {
    return this.get<MemberEduTimeResp>(`/members/${memberId}/edutime`, req);
  }
}
