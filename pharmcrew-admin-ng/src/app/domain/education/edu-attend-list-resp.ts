import { EduAttend } from './edu-attend';
import { PcResp } from 'src/app/pc-resp';

export class EduAttendListResp extends PcResp {
    evalStarAvg: number;
    evalCnt: number;
    attends: EduAttend[];
    searchCnt: number;
}
