import { Member } from './member';
import { PcResp } from 'src/app/pc-resp';

export class MemberListResp extends PcResp {
    members: Member[];
    searchCnt: number;
}
