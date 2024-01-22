import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminListComponent } from './admin-list/admin-list.component';
import { AdminDetailComponent } from './admin-detail/admin-detail.component';
import { FormsModule } from '@angular/forms';
import { WidgetModule } from 'src/app/widget/widget.module';

@NgModule({
  declarations: [
    AdminListComponent,
    AdminDetailComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    AdminRoutingModule,
    WidgetModule,
  ]
})
export class AdminModule { }
