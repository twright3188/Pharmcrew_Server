import { Banner } from './banner';
import { PcResp } from 'src/app/pc-resp';

export class BannerListResp extends PcResp {
    banners: Banner[];
    searchCnt: number;
}
