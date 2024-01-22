import { Component, OnInit, ViewChild, ViewChildren, QueryList } from '@angular/core';
import { OrganizeSelectorComponent } from 'src/app/widget/organize-selector/organize-selector.component';
import { Education } from '../education';
import { EducationService } from '../education.service';
import { ActivatedRoute } from '@angular/router';
import { PcService } from 'src/app/pc.service';
import { EducationCoursess as EducationCoursess } from '../education-courses';
import { Location } from '@angular/common';
import { EducationCourse } from '../education-course';
import { RegistEducationReq } from '../regist-education-req';
import { FileUploadComponent } from 'src/app/widget/file-upload/file-upload.component';
import { DomSanitizer } from '@angular/platform-browser';
import { UpdateEducationReq } from '../update-education-req';

declare var $ :any;
declare var daum: any;

@Component({
  selector: 'app-education-detail',
  templateUrl: './education-detail.component.html',
  styleUrls: ['./education-detail.component.css']
})
export class EducationDetailComponent implements OnInit {
  @ViewChild('organizeSelector') organizeSelector: OrganizeSelectorComponent;
  @ViewChildren(FileUploadComponent) fileUploadComponents:  QueryList<FileUploadComponent>;

  education = new Education();
  coursess = new EducationCoursess();

  qrData = ' ';

  deleteMapFileId: number = undefined;
  deleteTimetableFileId: number = undefined;

  constructor(
    private educationService: EducationService,
    private pcService: PcService,
    private route: ActivatedRoute,
    private location: Location,
    // private sanitizer: DomSanitizer,
  ) { }

  ngOnInit(): void {
    this.getEducation();
  }
  
  ngAfterViewInit() {
    this.pcService.initDatepicker('datepicker1');
    this.pcService.initDatepicker('datepicker2');
  }

  onChangeAuthType() {
    switch (this.education.authType) {
      case 'A':
        for (let dayIdx = 0; dayIdx < this.coursess.coursess.length; dayIdx++) {
          for (let courseIdx = 0; courseIdx < this.coursess.coursess[dayIdx].length; courseIdx++) {
            const course = this.coursess.coursess[dayIdx][courseIdx];
            course.takeHour = undefined;
            course.takeMinute = undefined;
            course.qrType = 'O';
          }
        }
        break;
      case 'P':
        this.education.takeHour = undefined;
        this.education.takeMinute = undefined;
        this.education.qrType = 'O';
        break;
    }
  }

  onStartDateChange(value: string) {
    console.log(`startDate: ${this.education.startDate}, value: ${value}`);
    if (this.education.endDate !== undefined) {
      const startDate = new Date(value.replace('.', '-'));
      const endDate = new Date(this.education.endDate.replace('.', '-'));
      const diff = endDate.getTime() - startDate.getTime();
      if (diff < 0) {
        alert('시작일이 종료일 이전입니다.');
        this.pcService.set('datepicker1', this.education.startDate);
        return;
      }
    }
    this.education.startDate = value;
  }

  onEndDateChange(value: string) {
    console.log(`endDate: ${this.education.endDate}, value: ${value}`);
    if (this.education.startDate !== undefined) {
      const startDate = new Date(this.education.startDate.replace('.', '-'));
      const endDate = new Date(value.replace('.', '-'));
      const diff = endDate.getTime() - startDate.getTime();
      if (diff < 0) {
        alert('종료일이 시작일 이전입니다.');
        this.pcService.set('datepicker2', this.education.endDate);
        return;
      }
    }
    this.education.endDate = value;
  }

  onDeleteAttach(type: string, idx: number) {
    switch (type) {
      case 'map':
        this.deleteMapFileId = this.education.mapFile.fileId;
        this.education.mapFile = undefined;
        break;
      case 'timetable':
        this.deleteTimetableFileId = this.education.timetableFile.fileId;
        this.education.timetableFile = undefined;
        break;
    }
  }

  onViewAttach(type: string, idx: number) {
    switch (type) {
      case 'map':
        this.educationService.downloadFile(this.education.mapFile.fileId);
        break;
      case 'timetable':
        this.educationService.downloadFile(this.education.timetableFile.fileId);
        break;
    }
  }

  onSearchAddressClick() {
    const geocoder = new daum.maps.services.Geocoder();
    new daum.Postcode({
      oncomplete: function(data) {
        console.log('data:', data);
        // data.zonecode;
        // data.roadAddress;
        // $('#zipcd').val(data.zonecode);
        // $('#zipcd').trigger('click');
        // var addr = data.roadAddress;
        // if (data.buildingName !== undefined && data.buildingName !== '') {
        //   addr += (' (' + data.buildingName + ')');
        // }
        // $('#address').val(addr);
        // $('#address').trigger('click');
        
        geocoder.addressSearch(data.address, function(results, status) {
          if (status === daum.maps.services.Status.OK) {
            var addr = data.roadAddress;
            if (data.buildingName !== undefined && data.buildingName !== '') {
              addr += (' (' + data.buildingName + ')');
            }
            $('#address').val(addr);
            $('#address').trigger('click');

            const result = results[0];
            // alert(`y: ${result.y}, x: ${result.x}`);
            $('#latitude').val(result.y);
            $('#latitude').trigger('click');
            $('#longitude').val(result.x);
            $('#longitude').trigger('click');
          }
        });
      }
    }).open();
  }

  onQrClick(idx: number) {
    switch (idx) {
      case 0:
        this.qrData = this.education.qr1;
        break;
      case 1:
        this.qrData = this.education.qr2;
        break;
    }
    $('#qrcode_view').css('display', 'block');
    $('.popup_wrap').css('width', '500px').css('height', '440px').css('margin-left', '-250px').css('margin-top', '-220px');
    $('#fade').css('display', 'block');
  }

  onDownloadQrClick() {
    let data: string = $('.qrcode img').attr('src');
    console.log(`data: ${data}`);
    // https://stackoverflow.com/questions/14011021/how-to-download-a-base64-encoded-image
    const image = new Image();
    image.src = data;
    image.onload = function() {
      const canvas = document.createElement('canvas');
      canvas.width = image.naturalWidth;
      canvas.height= image.naturalHeight;

      const ctx = canvas.getContext('2d');
      ctx.imageSmoothingEnabled = false;
      ctx.drawImage(image, 0, 0);
      // console.log(canvas, image)

      let fileName = "qr"
      const link = document.createElement('a');
      link.download = fileName + '.png';
      // console.log(canvas)
      canvas.toBlob(function(blob) {
          // console.log(blob)
          link.href = URL.createObjectURL(blob);
          link.click();
      });
    }
  }

  onClosePopupClick() {
    $('.popup_wrap').css('display', 'none');
    $('.black_overlay').css('display', 'none');
  }

  onAddDayClick() {
    this.coursess.coursess.push([]);
    this.coursess.coursess[this.coursess.coursess.length - 1].push(new EducationCourse());
  }

  onDeleteDayClick(dayIdx: number) {
    this.coursess.coursess.splice(dayIdx, 1);
  }

  onAddCourseClick(dayIdx: number) {
    this.coursess.coursess[dayIdx].push(new EducationCourse());
  }
  
  onDeleteCourseClick(dayIdx: number, courseIdx: number) {
    this.coursess.coursess[dayIdx].splice(courseIdx, 1);
  }

  onCourseQrClick(dayIdx: number, courseIdx: number, idx: number) {
    switch (idx) {
      case 0:
        this.qrData = this.coursess.coursess[dayIdx][courseIdx].qr1;
        break;
      case 1:
        this.qrData = this.coursess.coursess[dayIdx][courseIdx].qr2;
        break;
    }
    $('#qrcode_view').css('display', 'block');
    $('.popup_wrap').css('width', '500px').css('height', '440px').css('margin-left', '-250px').css('margin-top', '-220px');
    $('#fade').css('display', 'block');
  }

  onBackClick() {
    this.goBack();
  }

  onDeleteClick() {
    if (!confirm('삭제하시겠습니까?'))  return;
    this.educationService.deleteEducation(this.education.educationId).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200)  this.goBack();
    })
  }

  onSaveClick() {
    console.log(`address: ${this.education.address}, latitude: ${this.education.latitude}, longitude: ${this.education.longitude}`);
    if (this.pcService.isEmpty(this.education.startDate)) {
      alert('교육 시작일을 선택하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.education.endDate)) {
      alert('교육 종료일을 선택하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.education.startHour)) {
      alert('교육 시작 시간(시)을 선택하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.education.startMinute)) {
      alert('교육 시작 시간(분)을 선택하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.education.endHour)) {
      alert('교육 종료 시간(시)을 선택하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.education.endMinute)) {
      alert('교육 종료 시간(분)을 선택하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.education.address)) {
      alert('교육 장소를 검색하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.education.addressDetail)) {
      alert('교육 장소 상세 주소를 입력하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.education.wayGuide)) {
      alert('오시는 길 요약을 입력하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.education.master)) {
      alert('담당자를 입력하세요.');
      return;
    }
    switch (this.education.authType) {
      case 'A':
        if (this.pcService.isEmpty(this.education.takeHour)) {
          alert('교육 이수 시간(시)을 선택하세요.');
          return;
        }
        if (this.pcService.isEmpty(this.education.takeMinute)) {
          alert('교육 이수 시간(분)을 선택하세요.');
          return;
        }
        break;
      case 'P':
        break;
    }
    if (this.pcService.isEmpty(this.education.title)) {
      alert(`${this.education.authType === 'A' ? '교육' : this.education.authType === 'P' ? '대표' : this.education.authType} 제목을 입력하세요.`);
      return;
    }
    for (let dayIdx = 0; dayIdx < this.coursess.coursess.length; dayIdx++) {
      for (let courseIdx = 0; courseIdx < this.coursess.coursess[dayIdx].length; courseIdx++) {
        const course = this.coursess.coursess[dayIdx][courseIdx];
        if (this.pcService.isEmpty(course.type)) {
          alert(`${dayIdx+1}일차 ${courseIdx+1}번 째 강의의 구분을 입력하세요`);
          return;
        }
        if (this.pcService.isEmpty(course.startHour)) {
          alert(`${dayIdx+1}일차 ${courseIdx+1}번 째 강의의 시작 시간(시)을 입력하세요.`);
          return;
        }
        if (this.pcService.isEmpty(course.startMinute)) {
          alert(`${dayIdx+1}일차 ${courseIdx+1}번 째 강의의 시작 시간(분)을 입력하세요.`);
          return;
        }
        if (this.pcService.isEmpty(course.endHour)) {
          alert(`${dayIdx+1}일차 ${courseIdx+1}번 째 강의의 종료 시간(시)을 입력하세요.`);
          return;
        }
        if (this.pcService.isEmpty(course.endMinute)) {
          alert(`${dayIdx+1}일차 ${courseIdx+1}번 째 강의의 종료 시간(분)을 입력하세요.`);
          return;
        }
        if (this.pcService.isEmpty(course.title)) {
          alert(`${dayIdx+1}일차 ${courseIdx+1}번 째 강의의 제목을 입력하세요.`);
          return;
        }
        if (this.pcService.isEmpty(course.teacher)) {
          alert(`${dayIdx+1}일차 ${courseIdx+1}번 째 강의의 강사를 입력하세요.`);
          return;
        }
      }
    }

    if (this.education.educationId === undefined) {
      const req = new RegistEducationReq();
      req.authType = this.education.authType;
      req.organizeId = this.organizeSelector.organizeId();
      req.isOpen = this.education.isOpen;
      req.title = this.education.title;
      req.startDate = this.education.startDate;
      req.endDate = this.education.endDate;
      req.startHour = this.education.startHour;
      req.startMinute = this.education.startMinute;
      req.endHour = this.education.endHour;
      req.endMinute = this.education.endMinute;
      req.takeHour = this.education.takeHour;
      req.takeMinute = this.education.takeMinute;
      req.qrType = this.education.qrType;
      req.address = this.education.address;
      req.addressDetail = this.education.addressDetail;
      req.longitude = this.education.longitude;
      req.latitude = this.education.latitude;
      req.wayGuide = this.education.wayGuide;
      req.wayDetail = this.education.wayDetail;
      if (this.fileUploadComponents.toArray()[0].files() !== undefined) {
        req.mapFile = this.fileUploadComponents.toArray()[0].files()[0];
      }
      if (this.fileUploadComponents.toArray()[1].files() !== undefined) {
        req.timetableFile = this.fileUploadComponents.toArray()[1].files()[0];
      }
      req.master = this.education.master;
      req.masterPhone = this.education.masterPhone;
      req.coursessJson = JSON.stringify(this.coursess);
      this.educationService.registEducation(req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      });
    } else {
      const req = new UpdateEducationReq();
      req.organizeId = this.organizeSelector.organizeId();
      req.isOpen = this.education.isOpen;
      req.title = this.education.title;
      req.startDate = this.education.startDate;
      req.endDate = this.education.endDate;
      req.startHour = this.education.startHour;
      req.startMinute = this.education.startMinute;
      req.endHour = this.education.endHour;
      req.endMinute = this.education.endMinute;
      req.takeHour = this.education.takeHour;
      req.takeMinute = this.education.takeMinute;
      req.address = this.education.address;
      req.addressDetail = this.education.addressDetail;
      req.longitude = this.education.longitude;
      req.latitude = this.education.latitude;
      req.wayGuide = this.education.wayGuide;
      req.wayDetail = this.education.wayDetail;
      if (this.fileUploadComponents.toArray()[0].files() !== undefined) {
        req.mapFile = this.fileUploadComponents.toArray()[0].files()[0];
      }
      if (this.fileUploadComponents.toArray()[1].files() !== undefined) {
        req.timetableFile = this.fileUploadComponents.toArray()[1].files()[0];
      }
      req.master = this.education.master;
      req.masterPhone = this.education.masterPhone;
      req.coursessJson = JSON.stringify(this.coursess);
      this.educationService.updateEducation(this.education.educationId, req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      });
    }
  }

  mapFile() {
    return [this.education.mapFile];
  }

  timetableFile() {
    return [this.education.timetableFile];
  }

  private getEducation() {
    const id = +this.route.snapshot.paramMap.get('id');
    if (id !== -1) {
      this.educationService.education(id).subscribe(response => {
        const resp = response.body;
        if (resp.code !== 200)  this.goBack();

        this.education = resp.education;
        this.coursess = resp.coursess;

        this.organizeSelector.setOrganizeIds(resp.education.organize?.ids);
      });
    } else {
      this.coursess.coursess = [];
      this.onAddDayClick();
    }
  }

  private goBack() {
    localStorage.setItem('action', 'back');
    this.location.back();
  }

}
