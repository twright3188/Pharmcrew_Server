import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminListComponent } from './admin-list/admin-list.component';
import { AdminDetailComponent } from './admin-detail/admin-detail.component';

const routes: Routes = [
  {
    // path: 'admins',
    // // component: DefaultComponent,
    // canActivate: [SessionGuard],
    // children: [
    //   {
    //     path: '',
    //     component: AdminListComponent
    //   },
    //   {
    //     path: ':id',
    //     component: AdminDetailComponent
    //   }
    // ]
    path: '',
    // canActivate: [SessionGuard],
    component: AdminListComponent,
  },
  {
    path: ':id',
    // canActivate: [SessionGuard],
    component: AdminDetailComponent,
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
export class AdminRoutingModule { }
