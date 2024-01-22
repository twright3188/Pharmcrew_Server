import { PcResp } from 'src/app/pc-resp';

export class MeResp extends PcResp {
    loginId: string;
    name: string;
    organizeIds: number[];
    email: string;
    tel: string;
    phone: string;
}
