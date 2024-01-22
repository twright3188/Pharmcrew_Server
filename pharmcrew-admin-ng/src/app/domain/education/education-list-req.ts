export class EducationListReq {
    startDate: string;
    endDate: string;
    organizeId: number;
    keyword: string;
    isOpen = 'ALL';
    page = 1;
    cntPerPage = 20;
}
