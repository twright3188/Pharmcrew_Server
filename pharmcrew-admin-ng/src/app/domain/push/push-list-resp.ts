import { Push } from './push';
import { PcResp } from 'src/app/pc-resp';

export class PushListResp extends PcResp {
    pushes: Push[];
    searchCnt: number;
}
