export class QnaListReq {
    type: string;
    organizeId: number;
    isAnswerd = 'ALL';
    keyword: string;
    page = 1;
    cntPerPage = 20;
}
