import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SurveyListComponent } from './survey-list/survey-list.component';
import { SurveyDetailComponent } from './survey-detail/survey-detail.component';
import { FormsModule } from '@angular/forms';
import { SurveyRoutingModule } from './survey-routing.module';
import { WidgetModule } from 'src/app/widget/widget.module';
import { PipeModule } from 'src/app/pipe/pipe.module';



@NgModule({
  declarations: [
    SurveyListComponent,
    SurveyDetailComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    SurveyRoutingModule,
    WidgetModule,
    PipeModule,
  ]
})
export class SurveyModule { }
