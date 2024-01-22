import { Survey } from './survey';
import { PcResp } from 'src/app/pc-resp';

export class SurveyListResp extends PcResp {
    surveys: Survey[];
    searchCnt: number;
}
