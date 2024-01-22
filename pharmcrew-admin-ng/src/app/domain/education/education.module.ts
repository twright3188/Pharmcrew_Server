import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EducationListComponent } from './education-list/education-list.component';
import { FormsModule } from '@angular/forms';
import { WidgetModule } from 'src/app/widget/widget.module';
import { RouterModule } from '@angular/router';
import { EducationDetailComponent } from './education-detail/education-detail.component';
import { PipeModule } from 'src/app/pipe/pipe.module';
import { QRCodeModule } from 'angularx-qrcode';
import { EduApprovalComponent } from './edu-approval/edu-approval.component';

@NgModule({
  declarations: [EducationListComponent, EducationDetailComponent, EduApprovalComponent],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    WidgetModule,
    PipeModule,
    QRCodeModule,
  ]
})
export class EducationModule { }
