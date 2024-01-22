import { Organize2 } from '../common/organize2';
import { AttachFile } from '../common/attach-file';

export class Education {
    educationId: number;
    organize?: Organize2;
    isOpen: string = 'Y';
    title: string;
    startDate: string;
    endDate: string;
    startHour: string;
    startMinute: string;
    endHour: string;
    endMinute: string;
    takeHour: string;
    takeMinute: string;
    authType: string = 'A';
    address: string;
    addressDetail: string;
    longitude: string;
    latitude: string;
    wayGuide: string;
    wayDetail: string;
    mapFile: AttachFile;
    timetableFile: AttachFile;
    master: string;
    masterPhone: string;
    qr1: string;
    qr2: string;
    viewCnt: number;
    approvalCnt: number;
    regName: string;
    regDt: number;

    // ui
    qrType = 'O';
}
