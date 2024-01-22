import { Organize2 } from '../common/organize2';
import { AttachFile } from '../common/attach-file';

export class Doc {
    docId: number;
    organize: Organize2;
    title: string;
    body: string;
    docFile: AttachFile;
    openYn: string = 'Y';
    viewCnt: number;
    regName: string;
    regDt: number;
}
