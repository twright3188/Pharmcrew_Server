import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NewsRoutingModule } from './news-routing.module';
import { NoticeListComponent } from './notice-list/notice-list.component';
import { NoticeDetailComponent } from './notice-detail/notice-detail.component';
import { PopupListComponent } from './popup-list/popup-list.component';
import { PopupDetailComponent } from './popup-detail/popup-detail.component';
import { BannerDetailComponent } from './banner-detail/banner-detail.component';
import { BannerListComponent } from './banner-list/banner-list.component';
import { WidgetModule } from 'src/app/widget/widget.module';
import { FormsModule } from '@angular/forms';
import { PipeModule } from 'src/app/pipe/pipe.module';
import { QnaListComponent } from './qna-list/qna-list.component';
import { QnaDetailComponent } from './qna-detail/qna-detail.component';

@NgModule({
  declarations: [
    NoticeListComponent,
    NoticeDetailComponent,
    PopupListComponent,
    PopupDetailComponent,
    BannerListComponent,
    BannerDetailComponent,
    QnaListComponent,
    QnaDetailComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    NewsRoutingModule,
    WidgetModule,
    PipeModule,
  ]
})
export class NewsModule { }
