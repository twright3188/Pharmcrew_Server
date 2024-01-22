import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

import { MemberModule } from './domain/member/member.module';
import { LoginComponent } from './session/login/login.component';
import { LayoutModule } from './layout/layout.module';
import { NewsModule } from './domain/news/news.module';
import { AdminModule } from './domain/admin/admin.module';
import { MeModule } from './domain/me/me.module';
import { PushModule } from './domain/push/push.module';
import { WidgetModule } from './widget/widget.module';
import { PartnerModule } from './domain/partner/partner.module';
import { SurveyModule } from './domain/survey/survey.module';
import { EducationModule } from './domain/education/education.module';
import { AcademyModule } from './domain/academy/academy.module';
import { PtaxModule } from './domain/ptax/ptax.module';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,

    LayoutModule,
    WidgetModule,
    MeModule,
    AdminModule,
    PartnerModule,
    MemberModule,
    NewsModule,
    PushModule,
    SurveyModule,
    EducationModule,
    AcademyModule,
    PtaxModule,

    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
