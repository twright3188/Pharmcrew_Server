import { Organize2 } from '../common/organize2';

export class Survey {
    surveyId: number;
    title: string;
    detail: string;
    isOpen = 'Y';
    openResult = 'Y';
    startDate: string;
    endDate: string;
    // organizeIds: number[];
    // organizeName: string;
    organize: Organize2;
    joinMemberCnt: number;
    regName: string;
    regDt: number;
}
