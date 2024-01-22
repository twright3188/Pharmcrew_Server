import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MeRoutingModule } from './me-routing.module';
import { MeComponent } from './me/me.component';
import { FormsModule } from '@angular/forms';
import { WidgetModule } from 'src/app/widget/widget.module';

@NgModule({
  declarations: [
    MeComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    MeRoutingModule,
    WidgetModule,
  ]
})
export class MeModule { }
