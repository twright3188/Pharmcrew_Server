import { PcResp } from '../pc-resp';
import { Admin } from '../domain/admin/admin';

export class LoginResp extends PcResp {
    admin: Admin;
}
