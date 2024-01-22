import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './session/login/login.component';
import { DefaultComponent } from './layout/default/default.component';
import { PermissionGuard } from './session.guard';
import { AdminListComponent } from './domain/admin/admin-list/admin-list.component';
import { AdminDetailComponent } from './domain/admin/admin-detail/admin-detail.component';
import { PartnerListComponent } from './domain/partner/partner-list/partner-list.component';
import { PartnerDetailComponent } from './domain/partner/partner-detail/partner-detail.component';
import { MemberListComponent } from './domain/member/member-list/member-list.component';
import { NoticeListComponent } from './domain/news/notice-list/notice-list.component';
import { NoticeDetailComponent } from './domain/news/notice-detail/notice-detail.component';
import { PopupListComponent } from './domain/news/popup-list/popup-list.component';
import { PopupDetailComponent } from './domain/news/popup-detail/popup-detail.component';
import { BannerListComponent } from './domain/news/banner-list/banner-list.component';
import { BannerDetailComponent } from './domain/news/banner-detail/banner-detail.component';
import { QnaListComponent } from './domain/news/qna-list/qna-list.component';
import { QnaDetailComponent } from './domain/news/qna-detail/qna-detail.component';
import { PushListComponent } from './domain/push/push-list/push-list.component';
import { PushDetailComponent } from './domain/push/push-detail/push-detail.component';
import { SurveyListComponent } from './domain/survey/survey-list/survey-list.component';
import { SurveyDetailComponent } from './domain/survey/survey-detail/survey-detail.component';
import { MeComponent } from './domain/me/me/me.component';
import { MemberDetailComponent } from './domain/member/member-detail/member-detail.component';
import { EducationListComponent } from './domain/education/education-list/education-list.component';
import { EducationDetailComponent } from './domain/education/education-detail/education-detail.component';
import { EduApprovalComponent } from './domain/education/edu-approval/edu-approval.component';
import { DocListComponent } from './domain/academy/doc-list/doc-list.component';
import { DocDetailComponent } from './domain/academy/doc-detail/doc-detail.component';
import { VideoListComponent } from './domain/academy/video-list/video-list.component';
import { VideoDetailComponent } from './domain/academy/video-detail/video-detail.component';
import { PtaxqnaListComponent } from './domain/ptax/ptaxqna-list/ptaxqna-list.component';
import { PtaxqnaDetailComponent } from './domain/ptax/ptaxqna-detail/ptaxqna-detail.component';
import { PtaxnoticeListComponent } from './domain/ptax/ptaxnotice-list/ptaxnotice-list.component';
import { PtaxnoticeDetailComponent } from './domain/ptax/ptaxnotice-detail/ptaxnotice-detail.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },

  {
    path: '',
    component: DefaultComponent,
    canActivate: [PermissionGuard],
    children: [
      { path: 'me', canActivate: [PermissionGuard], children: [
        { path: '', component: MeComponent, },
      ]},
      {
        path: 'admins',
        // loadChildren: () => import('./domain/admin/admin.module').then(m => m.AdminModule)
        canActivate: [PermissionGuard],
        children: [
          {
            path: '',
            component: AdminListComponent,
          },
          {
            path: ':id',
            component: AdminDetailComponent,
          },
        ]
      },
      {
        path: 'partners',
        // loadChildren: () => import('./domain/partner/partner.module').then(m => m.PartnerModule)
        canActivate: [PermissionGuard],
        children: [
          {
            path: '',
            component: PartnerListComponent,
          },
          {
            path: ':id',
            component: PartnerDetailComponent,
          },
        ]
      },
      {
        path: 'notices',
        canActivate: [PermissionGuard],
        children: [
          {
            path: '',
            component: NoticeListComponent,
          },
          { path: ':id', component: NoticeDetailComponent, },
        ]
      },
      { path: 'popups', canActivate: [PermissionGuard], children: [
        { path: '', component: PopupListComponent, },
        { path: ':id', component: PopupDetailComponent, },
      ]},
      { path: 'banners', canActivate: [PermissionGuard], children: [
        { path: '', component: BannerListComponent, },
        { path: ':id', component: BannerDetailComponent, },
      ]},
      { path: 'admsgs', canActivate: [PermissionGuard], children: [
        { path: '', component: PushListComponent, },
        { path: ':id', component: PushDetailComponent, },
      ]},
      { path: 'educations', canActivate: [PermissionGuard], children: [
        { path: '', component: EducationListComponent, },
        { path: ':id', component: EducationDetailComponent, },
      ]},
      { path: 'eduapprovals', canActivate: [PermissionGuard], children: [
        { path: '', component: EducationListComponent, },
        { path: ':id', component: EduApprovalComponent, },
      ]},
      { path: 'pushes', canActivate: [PermissionGuard], children: [
        { path: '', component: PushListComponent, },
        { path: ':id', component: PushDetailComponent, },
      ]},
      { path: 'ntcqnas', canActivate: [PermissionGuard], children: [
        { path: '', component: QnaListComponent, },
        { path: ':id', component: QnaDetailComponent, },
      ]},
      { path: 'eduqnas', canActivate: [PermissionGuard], children: [
        { path: '', component: QnaListComponent, },
        { path: ':id', component: QnaDetailComponent, },
      ]},
      { path: 'qnas', canActivate: [PermissionGuard], children: [
        { path: '', component: QnaListComponent, },
        { path: ':id', component: QnaDetailComponent, },
      ]},
      { path: 'surveys', canActivate: [PermissionGuard], children: [
        { path: '', component: SurveyListComponent, },
        { path: ':id', component: SurveyDetailComponent, },
      ]},
      { path: 'members', canActivate: [PermissionGuard], children: [
        { path: '', component: MemberListComponent, },
        { path: ':id', component: MemberDetailComponent, },
      ]},
      { path: 'docs', canActivate: [PermissionGuard], children: [
        { path: '', component: DocListComponent, },
        { path: ':id', component: DocDetailComponent, },
      ]},
      { path: 'videos', canActivate: [PermissionGuard], children: [
        { path: '', component: VideoListComponent, },
        { path: ':id', component: VideoDetailComponent, },
      ]},
      { path: 'ptaxqnas', canActivate: [PermissionGuard], children: [
        { path: '', component: PtaxqnaListComponent, },
        { path: ':id', component: PtaxqnaDetailComponent, },
      ]},
      { path: 'ptaxnotices', canActivate: [PermissionGuard], children: [
        { path: '', component: PtaxnoticeListComponent, },
        { path: ':id', component: PtaxnoticeDetailComponent, },
      ]},

      {
        path: '',
        redirectTo: 'admins',
        pathMatch: 'full'
      }
    ]
  },

  // {
  //   path: '',
  //   redirectTo: 'admins',
  //   pathMatch: 'full'
  // }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      routes,
      // { enableTracing: true }
    )
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
