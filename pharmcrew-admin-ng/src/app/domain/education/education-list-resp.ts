import { PcResp } from 'src/app/pc-resp';
import { Education } from './education';

export class EducationListResp extends PcResp {
    educations: Education[];
    searchCnt: number;
}
