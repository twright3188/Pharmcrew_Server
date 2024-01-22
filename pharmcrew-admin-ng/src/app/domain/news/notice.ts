import { AttachFile } from '../common/attach-file';
import { Organize2 } from '../common/organize2';

export class Notice {
    noticeId: number;
    // organizeIds: number[];
    // organizeName: string;
    organize?: Organize2;
    title: string;
    body: string;
    isOpen: string = 'Y';
    regName: string;
    regDt: number;
    viewCnt: number;
    attachFiles: AttachFile[];
}
