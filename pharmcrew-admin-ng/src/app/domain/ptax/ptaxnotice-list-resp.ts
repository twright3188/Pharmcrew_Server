import { PcResp } from 'src/app/pc-resp';
import { Ptaxnotice } from './ptaxnotice';

export class PtaxnoticeListResp extends PcResp {
    notices: Ptaxnotice[];
    searchCnt: number;
}
