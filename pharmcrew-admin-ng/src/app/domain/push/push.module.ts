import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PushListComponent } from './push-list/push-list.component';
import { PushDetailComponent } from './push-detail/push-detail.component';
import { PushRoutingModule } from './push-routing.module';
import { FormsModule } from '@angular/forms';
import { WidgetModule } from 'src/app/widget/widget.module';
import { PipeModule } from 'src/app/pipe/pipe.module';

@NgModule({
  declarations: [
    PushListComponent,
    PushDetailComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    PushRoutingModule,
    WidgetModule,
    PipeModule,
  ]
})
export class PushModule { }
