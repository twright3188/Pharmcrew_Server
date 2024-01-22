export class PushListReq {
    reservDtStartDate: string;
    reservDtEndDate: string;
    keyword: string;
    organizeId: number;
    categoryType: string;
    sendType: string = 'ALL';
    page = 1;
    cntPerPage = 20;
}
