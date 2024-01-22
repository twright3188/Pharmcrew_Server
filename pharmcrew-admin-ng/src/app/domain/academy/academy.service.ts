import { Injectable } from '@angular/core';
import { HttpService } from 'src/app/http.service';
import { PcResp } from 'src/app/pc-resp';
import { RegisterDocReq } from './register-doc-req';
import { DocListReq } from './doc-list-req';
import { DocListResp } from './doc-list-resp';
import { DocResp } from './doc-resp';
import { UpdateDocReq } from './update-doc-req';
import { VideoListReq } from './video-list-req';
import { VideoListResp } from './video-list-resp';
import { RegisterVideoReq } from './register-video-req';
import { VideoResp } from './video-resp';
import { UpdateVideoReq } from './update-video-req';

@Injectable({
  providedIn: 'root'
})
export class AcademyService extends HttpService {
  registerDoc(req: RegisterDocReq) {
    return this.postMultipart<PcResp>('/docs', req);
  }
  docList(req: DocListReq) {
    return this.get<DocListResp>('/docs', req);
  }
  doc(docId: number) {
    return this.get<DocResp>(`/docs/${docId}`);
  }
  updateDoc(docId: number, req: UpdateDocReq) {
    return this.postMultipart<PcResp>(`/docs/${docId}`, req);
  }
  deleteDoc(docId: number) {
    return this.delete<PcResp>(`/docs/${docId}`);
  }

  registerVideo(req: RegisterVideoReq) {
    return this.postMultipart<PcResp>('/videos', req);
  }
  videoList(req: VideoListReq) {
    return this.get<VideoListResp>('/videos', req);
  }
  video(videoId: number) {
    return this.get<VideoResp>(`/videos/${videoId}`);
  }
  updateVideo(videoId: number, req: UpdateVideoReq) {
    return this.postMultipart<PcResp>(`/videos/${videoId}`, req);
  }
  deleteVideo(videoId: number) {
    return this.delete<PcResp>(`/videos/${videoId}`);
  }
}
