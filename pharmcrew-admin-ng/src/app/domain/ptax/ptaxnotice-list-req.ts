export class PtaxnoticeListReq {
    regDtStartDate: string;
    regDtEndDate: string;
    target = 'ALL';
    targetPharmName: string;
    sendType = 'ALL';
    keyword: string;
    page = 1;
    cntPerPage = 20;
}
