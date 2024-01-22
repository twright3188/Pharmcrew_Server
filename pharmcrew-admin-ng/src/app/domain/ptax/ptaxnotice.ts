import { AttachFile } from '../common/attach-file';

export class Ptaxnotice {
    noticeId: number;
    title: string;
    body: string;
    openYn = 'Y';
    attachFiles: AttachFile[];
    sendType = 'NONE';
    target = 'ALL';
    targetPharmName: string;
    targetMemberId: number;
    targetMemberName: string;
    os = 'ALL';
    reserveDate: string;
    reserveHour: string;
    reserveMinute: string;
    state: string;
    sendDt: number;
    sendCnt: number;
    recvCnt: number;
    regName: string;
    regDt: number;
}
