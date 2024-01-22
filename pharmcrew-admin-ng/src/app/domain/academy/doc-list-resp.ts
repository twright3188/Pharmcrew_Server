import { PcResp } from 'src/app/pc-resp';
import { Doc } from './doc';

export class DocListResp extends PcResp {
    docs: Doc[];
    searchCnt: number;
}
