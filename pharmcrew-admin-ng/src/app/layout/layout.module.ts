import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HeaderComponent } from './header/header.component';
import { SidenavComponent } from './sidenav/sidenav.component';
import { DefaultComponent } from './default/default.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    DefaultComponent,
    HeaderComponent,
    SidenavComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
  ]
})
export class LayoutModule { }
