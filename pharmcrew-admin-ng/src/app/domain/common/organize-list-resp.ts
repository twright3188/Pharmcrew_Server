import { Organize } from './organize';
import { PcResp } from 'src/app/pc-resp';

export class OrganizeListResp extends PcResp {
    depth: number;
    organizes: Organize[];
}
