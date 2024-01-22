import { PcResp } from 'src/app/pc-resp';
import { Video } from './video';

export class VideoListResp extends PcResp {
    videos: Video[];
    searchCnt: number;
}
