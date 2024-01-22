import { AttachFile } from '../common/attach-file';
import { Organize2 } from '../common/organize2';

export class Qna {
    qnaId: number;
    // organizeIds: number[];
    // organizeName: string;
    organize: Organize2;
    title: string;
    body: string;
    attachFile: AttachFile;
    memberName: string;
    questionDt: number;
    isAnswerd: string;
    answerTitle: string;
    answerBody: string;
    adminName: string;
    answerDt: number;
}
