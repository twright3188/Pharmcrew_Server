import { AttachFile } from '../common/attach-file';

export class Ptaxqna {
    qnaId: number;
    memberName: string;
    pharmName: string;
    title: string;
    body: string;
    isAnswered: string;
    ansTitle: string;
    ansBody: string;
    questionDt: number;
    adminName: string;
    answerDt: number;
    qAttachFiles: AttachFile[];
    aAttachFiles: AttachFile[];
}
