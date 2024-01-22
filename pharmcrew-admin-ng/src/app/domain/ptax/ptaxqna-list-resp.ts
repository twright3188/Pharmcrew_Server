import { PcResp } from 'src/app/pc-resp';
import { Ptaxqna } from './ptaxqna';

export class PtaxqnaListResp extends PcResp {
    qnas: Ptaxqna[];
    searchCnt: number;
}
