import { AttachFile } from '../common/attach-file';

export class Partner {
    partnerId: number;
    name: string;
    detail: string;
    iconFile: AttachFile;
    moveUrl: string;
    isOpen = 'Y';
    memberCount: number;
    regName: string;
    regDt: number;
}
