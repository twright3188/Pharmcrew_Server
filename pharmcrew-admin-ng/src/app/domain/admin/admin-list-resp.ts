import { Admin } from './admin';
import { PcResp } from 'src/app/pc-resp';

export class AdminListResp extends PcResp {
    admins: Admin[];
    searchCnt: number;
}
