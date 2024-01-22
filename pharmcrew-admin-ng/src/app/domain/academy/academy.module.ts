import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { WidgetModule } from 'src/app/widget/widget.module';
import { DocListComponent } from './doc-list/doc-list.component';
import { DocDetailComponent } from './doc-detail/doc-detail.component';
import { VideoListComponent } from './video-list/video-list.component';
import { VideoDetailComponent } from './video-detail/video-detail.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [DocListComponent, DocDetailComponent, VideoListComponent, VideoDetailComponent],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    WidgetModule,
  ]
})
export class AcademyModule { }
