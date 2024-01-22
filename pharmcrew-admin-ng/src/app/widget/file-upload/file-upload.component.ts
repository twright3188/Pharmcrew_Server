import { Component, OnInit, ViewChildren, QueryList, Input, Output, EventEmitter } from '@angular/core';
import { FileComponent, FileComponentAttach } from '../file/file.component';
import { AttachFile } from 'src/app/domain/common/attach-file';

@Component({
  selector: 'widget-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {
  @ViewChildren(FileComponent) fileComponents: QueryList<FileComponent>;

  @Input() checkType: string;
  @Input() maxCount: number;
  @Input() maxSize: number;
  @Input() attachFiles: AttachFile[];

  @Output() onDelete = new EventEmitter<number>();
  @Output() onView = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
    console.log(`attachFiles: ${this.attachFiles}`);
  }

  onAttachFile(attach: FileComponentAttach) {
    console.log(`FileUploadComponent#onAttachFile - attach: ${attach}`);
    if (attach.error !== undefined) alert(attach.error);
  }

  onDeleteFile(idx: number) {
    console.log(`FileUploadComponent#onDeleteFile - idx: ${idx}`);
    // this.attachFiles.splice(idx, 1);
    this.onDelete.emit(idx);
  }

  onViewFile(idx: number) {
    console.log(`FileUploadComponent#onViewFile - idx: ${idx}`);
    this.onView.emit(idx);
  }

  files(): File[] {
    let files: File[] = undefined;
    this.fileComponents
      .map(fileComponent => fileComponent.file)
      .filter(file => file !== undefined && file.nativeElement.files.length > 0)
      .map(file => file.nativeElement.files[0])
      .forEach((file: File) => {
        if (files === undefined) {
          files = [];
        }
        console.log(`file: ${file}`);
        files.push(file);
      });
      console.log(`files: ${files}`);
    return files;
  }

}
