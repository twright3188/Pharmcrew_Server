import { Injectable } from '@angular/core';

declare var $ :any;

@Injectable({
  providedIn: 'root'
})
export class PcService {
  private items = {};

  constructor() { }

  isEmpty(str: string) {
    if (str === undefined)  return true;
    str = str.trim();
    if (
      str === undefined ||
      str.length === 0 ||
      str === '<p><br></p>' ||  // summernote
        str === '<p>$nbsp;</p>' ||  // summernote(ie)
      str === '&nbsp' // summernote(ie) 글 썼다가 지우면
      )  return true;
    return false;
  }

  diffStr(msec1: number, msec2: number) {
    const diffMs = msec2 - msec1;
    const diffDays = Math.floor(diffMs / 86400000); // days
    const diffHrs = Math.floor((diffMs % 86400000) / 3600000); // hours
    const diffMins = Math.round(((diffMs % 86400000) % 3600000) / 60000); // minutes

    let result = '';
    if (diffDays > 0) {
      result += `${diffDays}일`;
    }
    if (diffHrs > 0) {
      result += `${diffHrs}시간`;
    }
    if (diffMins > 0) {
      result += `${diffMins}분`;
    }
    return result;
  }

  timeStr(minute: number) {
    const hour = minute / 60;
    minute = minute % 60;
    let result = '';
    if (hour > 0) {
      result += `${hour}시간`;
    }
    if (minute > 0 || result.length === 0) {
      result += `${minute}분`;
    }
    return result;
  }

  // javascript

  initDatepicker(id: string) {
    $('#'+id).datepicker({
      // showOn: 'button' ,
      // buttonImageOnly: true,
      // buttonImage: "/assets/images/ico_calen.gif",

      dateFormat: 'yy.mm.dd',

      changeYear: true,
      changeMonth: true,

      prevText: '이전 달',
      nextText: '다음 달',
      monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
      monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
      dayNames: ['일', '월', '화', '수', '목', '금', '토'],
      dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
      dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],

      onSelect: function(date, inst) {
        $('#'+id).trigger('click');
      }
    });
  }

  set(id: string, value: string) {
    $('#'+id).val(value);
  }
}
