import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { WidgetModule } from 'src/app/widget/widget.module';
import { PtaxqnaListComponent } from './ptaxqna-list/ptaxqna-list.component';
import { PtaxqnaDetailComponent } from './ptaxqna-detail/ptaxqna-detail.component';
import { PtaxnoticeListComponent } from './ptaxnotice-list/ptaxnotice-list.component';
import { PtaxnoticeDetailComponent } from './ptaxnotice-detail/ptaxnotice-detail.component';
import { PipeModule } from 'src/app/pipe/pipe.module';

@NgModule({
  declarations: [PtaxqnaListComponent, PtaxqnaDetailComponent, PtaxnoticeListComponent, PtaxnoticeDetailComponent],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    WidgetModule,
    PipeModule,
  ]
})
export class PtaxModule { }
