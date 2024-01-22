import { Partner } from './partner';
import { PcResp } from 'src/app/pc-resp';

export class PartnerListResp extends PcResp {
    partners: Partner[];
    searchCnt: number;
}
