import { PcResp } from 'src/app/pc-resp';
import { Education } from './education';
import { EducationCoursess } from './education-courses';

export class EducationResp extends PcResp {
    education: Education;
    coursess: EducationCoursess;
}
