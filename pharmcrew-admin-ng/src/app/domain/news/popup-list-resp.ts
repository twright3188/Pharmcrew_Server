import { PcResp } from 'src/app/pc-resp';
import { Popup } from './popup';

export class PopupListResp extends PcResp {
    popups: Popup[];
    searchCnt: number;
}
