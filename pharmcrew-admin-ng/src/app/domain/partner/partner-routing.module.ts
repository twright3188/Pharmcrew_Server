import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PartnerListComponent } from './partner-list/partner-list.component';
import { PartnerDetailComponent } from './partner-detail/partner-detail.component';

const routes: Routes = [
  {
    // path: 'partners',
    // // component: DefaultComponent,
    // canActivate: [SessionGuard],
    // children: [
    //   {
    //     path: '',
    //     component: PartnerListComponent,
    //   },
    //   {
    //     path: ':id',
    //     component: PartnerDetailComponent
    //   }
    // ]
    path: '',
    // canActivate: [SessionGuard],
    component: PartnerListComponent,
  },
  {
    path: ':id',
    // canActivate: [SessionGuard],
    component: PartnerDetailComponent,
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
export class PartnerRoutingModule { }
