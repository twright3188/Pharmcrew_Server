import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DefaultComponent } from '../../layout/default/default.component';
import { MemberListComponent } from './member-list/member-list.component';
import { MemberUploadComponent } from './member-upload/member-upload.component';
import { PermissionGuard } from 'src/app/session.guard';

const routes: Routes = [
  {
    path: 'members',
    // component: DefaultComponent,
    canActivate: [PermissionGuard],
    children: [
      {
        path: '',
        component: MemberListComponent,
      },
      {
        path: 'upload',
        component: MemberUploadComponent,
      }
    ]
  }
];

@NgModule({
  imports: [
    // RouterModule.forChild(routes)
  ]
})
export class MemberRoutingModule { }
