import { AttachFile } from '../common/attach-file';

export class Popup {
    popupId: number;
    title: string;
    imgFile: AttachFile;
    idx: number;
    moveType: string;
    moveId: number;
    moveUrl: string;
    isOpen = 'Y';
    openStartDate: string;
    // openStartHour: string;
    openStartHour = '00';
    openEndDate: string;
    // openEndHour: string;
    openEndHour = '23';
    regName: string;
    regDt: number;
}
