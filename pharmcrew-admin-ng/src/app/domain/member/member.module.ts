import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MemberRoutingModule } from './member-routing.module';
import { MemberListComponent } from './member-list/member-list.component';
import { MemberUploadComponent } from './member-upload/member-upload.component';
import { FormsModule } from '@angular/forms';
import { WidgetModule } from 'src/app/widget/widget.module';
import { RouterModule } from '@angular/router';
import { MemberDetailComponent } from './member-detail/member-detail.component';
import { PipeModule } from 'src/app/pipe/pipe.module';

@NgModule({
  declarations: [
    MemberListComponent,
    MemberUploadComponent,
    MemberDetailComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    MemberRoutingModule,
    WidgetModule,
    PipeModule,
  ],
  exports: [
    RouterModule,
  ]
})
export class MemberModule { }
