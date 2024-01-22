export class PtaxqnaListReq {
    regDtStartDate: string;
    regDtEndDate: string;
    target = 'ALL';
    pharmName: string;
    answerYn = 'ALL';
    keyword: string;
    page = 1;
    cntPerPage = 20;
}
