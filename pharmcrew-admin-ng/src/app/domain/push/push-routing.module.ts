import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DefaultComponent } from '../../layout/default/default.component';
import { PermissionGuard } from 'src/app/session.guard';
import { PushListComponent } from './push-list/push-list.component';
import { PushDetailComponent } from './push-detail/push-detail.component';

const routes: Routes = [
  {
    path: 'pushes',
    // component: DefaultComponent,
    canActivate: [PermissionGuard],
    children: [
      {
        path: '',
        component: PushListComponent
      },
      {
        path: ':id',
        component: PushDetailComponent
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
export class PushRoutingModule { }
