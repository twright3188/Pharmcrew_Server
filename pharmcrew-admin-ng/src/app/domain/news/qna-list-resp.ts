import { PcResp } from 'src/app/pc-resp';
import { Qna } from './qna';

export class QnaListResp extends PcResp {
    qnas: Qna[];
    searchCnt: number;
}
