import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrganizeSelectorComponent } from './organize-selector/organize-selector.component';
import { FormsModule } from '@angular/forms';
import { PaginationComponent } from './pagination/pagination.component';
import { FileComponent } from './file/file.component';
import { FileUploadComponent } from './file-upload/file-upload.component';
import { PipeModule } from '../pipe/pipe.module';
import { TargetSelectorComponent } from './target-selector/target-selector.component';

@NgModule({
  declarations: [
    OrganizeSelectorComponent,
    PaginationComponent,
    FileComponent,
    FileUploadComponent,
    TargetSelectorComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,

    PipeModule,
  ],
  exports: [
    OrganizeSelectorComponent,
    PaginationComponent,
    FileComponent,
    FileUploadComponent,
    TargetSelectorComponent,
  ]
})
export class WidgetModule { }
