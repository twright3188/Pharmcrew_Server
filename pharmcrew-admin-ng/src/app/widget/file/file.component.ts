import { Component, OnInit, ViewChild, Input, EventEmitter, Output } from '@angular/core';
import { AttachFile } from 'src/app/domain/common/attach-file';

@Component({
  selector: 'widget-file',
  templateUrl: './file.component.html',
  styleUrls: ['./file.component.css']
})
export class FileComponent implements OnInit {
  @ViewChild('file') file: any;

  @Input() idx: number;
  @Input() checkType: string;
  @Input() maxSize: number;
  @Input() attachFile: AttachFile;

  @Output() onDelete = new EventEmitter<number>();
  @Output() onAttach = new EventEmitter<FileComponentAttach>();
  @Output() onView = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
  }

  onChange() {
    if (this.file.nativeElement.files.length === 0) {
      this.onAttach.emit(new FileComponentAttach(this.idx, undefined));
      return;
    }

    let file: File = this.file.nativeElement.files[0];

    let types: string[] = undefined;
    let exts: string[] = undefined;
    switch (this.checkType) {
      case 'popup':
      case 'banner':
      case 'push':
      case 'educaitonMap':
      case 'educationTimetable':
        types = [
          'image/jpeg',
          'image/png',
        ];
        break;
      case 'partner':
        types = [
          'image/png',
        ];
        break;
      case 'doc':
        types = [
          'application/pdf',
        ];
        break;
    }
    
    let pass: boolean = false;
    if (pass === false && types !== undefined) {
      for (let i = 0; i < types.length; i++) {
        console.log(`file.type: ${file.type}`);
        if (file.type === types[i]) {
          pass = true;
          break;
        }
      }
    }
    if (pass === false && exts !== undefined) {
      let ext = undefined;
      let extIndex = file.name.lastIndexOf('.');
      if (extIndex > 0) {
        ext = file.name.substring(file.name.lastIndexOf('.') + 1);
      }
      console.log('ext:', ext);
      if (ext !== undefined) {
        for (let i = 0; i < exts.length; i++) {
          if (ext === exts[i]) {
            pass = true;
            break;
          }
        }
      }
    }

    if (pass === false && (types !== undefined || exts !== undefined)) {
      this.file.nativeElement.value = '';
      this.onAttach.emit(new FileComponentAttach(this.idx, '지원하지 않는 파일 형식입니다.'));
      return;
    }

    // console.log(`size: ${file.size}`);
    if (this.maxSize !== undefined && file.size > this.maxSize) {
      this.file.nativeElement.value = '';
      this.onAttach.emit(new FileComponentAttach(this.idx, '첨부할 수 없는 파일 크기입니다.'));
      return;
    }

    this.onAttach.emit(new FileComponentAttach(this.idx, undefined));
  }

  onDeleteClick() {
    if (this.attachFile !== undefined) {
      this.onDelete.emit(this.idx);
    }
  }

  onViewClick() {
    this.onView.emit(this.idx);
  }

  accept() {
    switch (this.checkType) {
      case 'popup':
      case 'banner':
      case 'push':
      case 'educaitonMap':
      case 'educationTimetable':
        return '.jpg, .jpeg, .png';
      case 'partner':
        return '.png';
      case 'doc':
        return '.pdf';
      default:
        return undefined;
    }
  }
}

export class FileComponentAttach {
  idx: number;
  error: string;

  constructor(idx: number, error: string) {
    this.idx = idx;
    this.error = error;
  }
}
