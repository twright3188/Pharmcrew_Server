export class UpdatePtaxnoticeReq {
    sendType: string;
    target: string;
    targetPharmName: string;
    targetMemberId: number;
    openYn: string;
    reserveDate: string;
    reserveHour: string;
    reserveMinute: string;
    osType: string;
    title: string;
    body: string;
    delAttachFileIds: number[];
    attachFiles: File[];
}
