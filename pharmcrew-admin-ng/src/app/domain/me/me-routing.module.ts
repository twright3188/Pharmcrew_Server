import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MeComponent } from './me/me.component';
import { DefaultComponent } from '../../layout/default/default.component';
import { PermissionGuard } from 'src/app/session.guard';

const routes: Routes = [
  {
    path: 'me',
    // component: DefaultComponent,
    canActivate: [PermissionGuard],
    children: [
      {
        path: '',
        component: MeComponent
      },
    ]
  },
];

@NgModule({
  imports: [
    // RouterModule.forChild(routes)
  ]
})
export class MeRoutingModule { }
