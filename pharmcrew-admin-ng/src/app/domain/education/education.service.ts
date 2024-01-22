import { Injectable } from '@angular/core';
import { HttpService } from 'src/app/http.service';
import { EducationListReq } from './education-list-req';
import { EducationListResp } from './education-list-resp';
import { RegistEducationReq } from './regist-education-req';
import { PcResp } from 'src/app/pc-resp';
import { EducationResp } from './education-resp';
import { UpdateEducationReq } from './update-education-req';
import { EduAttendListReq } from './edu-attend-list-req';
import { EduAttendListResp } from './edu-attend-list-resp';
import { UpdateEduAttendApprovalReq } from './update-edu-attend-approval-req';

@Injectable({
  providedIn: 'root'
})
export class EducationService extends HttpService {
  registEducation(req: RegistEducationReq) {
    return this.post<PcResp>('/educations', req);
  }
  educationList(req: EducationListReq) {
    return this.get<EducationListResp>('/educations', req);
  }
  education(educationId: number) {
    return this.get<EducationResp>(`/educations/${educationId}`);
  }
  updateEducation(educationId: number, req: UpdateEducationReq) {
    return this.post<PcResp>(`/educations/${educationId}`, req);
  }
  deleteEducation(educationId: number) {
    return this.delete<PcResp>(`/educations/${educationId}`);
  }

  eduAttendList(educationId: number, req: EduAttendListReq) {
    return this.get<EduAttendListResp>(`/educations/${educationId}/attends`, req);
  }
  updateEduAttendApproval(educationId: number, req: UpdateEduAttendApprovalReq) {
    return this.put<PcResp>(`/educations/${educationId}/attendapproval`, req);
  }
}
