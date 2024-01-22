export class EduAttendListReq {
    courseId: number;
    type: string;
    keyword: string;
    page = 1;
    cntPerPage = 20;
}
