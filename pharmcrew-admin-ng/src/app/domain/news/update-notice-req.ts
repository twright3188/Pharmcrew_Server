export class UpdateNoticeReq {
    organizeId: number;
    title: string;
    body: string;
    delAttachFileIds: number[];
    attachFiles: File[];
    isOpen: string;
}
