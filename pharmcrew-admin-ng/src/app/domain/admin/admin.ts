import { Organize } from '../common/organize';
import { Organize2 } from '../common/organize2';

export class Admin {
    adminId: number;
    loginId: string;
    name: string;
    email: string;
    tel: string;
    phone: string;
    authority = 'A';
    // organizeIds: number[];
    // organizeName: string;
    organize: Organize2;
    regDt: number;
    state = 'N';
}
