import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PartnerListComponent } from './partner-list/partner-list.component';
import { PartnerRoutingModule } from './partner-routing.module';
import { PartnerDetailComponent } from './partner-detail/partner-detail.component';
import { WidgetModule } from 'src/app/widget/widget.module';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    PartnerListComponent,
    PartnerDetailComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    PartnerRoutingModule,
    WidgetModule,
  ]
})
export class PartnerModule { }
