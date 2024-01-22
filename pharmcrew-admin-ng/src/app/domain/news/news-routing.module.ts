import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DefaultComponent } from '../../layout/default/default.component';
import { NoticeListComponent } from './notice-list/notice-list.component';
import { NoticeDetailComponent } from './notice-detail/notice-detail.component';
import { PermissionGuard } from 'src/app/session.guard';
import { BannerListComponent } from './banner-list/banner-list.component';
import { BannerDetailComponent } from './banner-detail/banner-detail.component';
import { PopupListComponent } from './popup-list/popup-list.component';
import { PopupDetailComponent } from './popup-detail/popup-detail.component';
import { QnaListComponent } from './qna-list/qna-list.component';
import { QnaDetailComponent } from './qna-detail/qna-detail.component';

const routes: Routes = [
  {
    path: 'notices',
    // component: DefaultComponent,
    canActivate: [PermissionGuard],
    children: [
      {
        path: '',
        component: NoticeListComponent
      },
      {
        path: ':id',
        component: NoticeDetailComponent
      }
    ]
  },
  {
    path: 'popups',
    // component: DefaultComponent,
    canActivate: [PermissionGuard],
    children: [
      {
        path: '',
        component: PopupListComponent
      },
      {
        path: ':id',
        component: PopupDetailComponent
      }
    ]
  },
  {
    path: 'banners',
    // component: DefaultComponent,
    canActivate: [PermissionGuard],
    children: [
      {
        path: '',
        component: BannerListComponent,
      },
      {
        path: ':id',
        component: BannerDetailComponent,
      }
    ]
  },
  {
    path: 'qnas',
    // component: DefaultComponent,
    canActivate: [PermissionGuard],
    children: [
      {
        path: '',
        component: QnaListComponent,
      },
      {
        path: ':id',
        component: QnaDetailComponent,
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
export class NewsRoutingModule { }
