import { AttachFile } from '../common/attach-file';
import { Organize2 } from '../common/organize2';

export class Push {
    pushId: number;
    osType = 'ALL';
    title: string;
    body: string;
    imgFile: AttachFile;
    reserveDate: string;
    reserveHour: string;
    reserveMinute: string;
    // organizeIds: number[];
    // organizeName: string;
    organize: Organize2;
    categoryType: string;
    moveType = 'NONE';
    moveId: number;
    moveUrl: string;
    state: string;
    sendDt: number;
    sendCnt: number;
    recvCnt: number;
    regDt: number;
}
