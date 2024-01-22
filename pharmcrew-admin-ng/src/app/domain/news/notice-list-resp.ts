import { Notice } from './notice';
import { PcResp } from 'src/app/pc-resp';

export class NoticeListResp extends PcResp {
    notices: Notice[];
    searchCnt: number;
}
