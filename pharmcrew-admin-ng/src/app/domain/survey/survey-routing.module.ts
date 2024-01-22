import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DefaultComponent } from '../../layout/default/default.component';
import { PermissionGuard } from 'src/app/session.guard';
import { SurveyListComponent } from './survey-list/survey-list.component';
import { SurveyDetailComponent } from './survey-detail/survey-detail.component';

const routes: Routes = [
  {
    path: 'surveys',
    // component: DefaultComponent,
    canActivate: [PermissionGuard],
    children: [
      {
        path: '',
        component: SurveyListComponent,
      },
      {
        path: ':id',
        component: SurveyDetailComponent,
      }
    ]
  }
];

@NgModule({
  imports: [
    // RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule,
  ]
})
export class SurveyRoutingModule { }
