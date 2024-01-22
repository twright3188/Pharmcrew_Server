export class RegisterPtaxnoticeReq {
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
    attachFiles: File[];
}
