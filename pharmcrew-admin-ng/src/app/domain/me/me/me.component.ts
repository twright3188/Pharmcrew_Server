import { Component, OnInit, ViewChild } from '@angular/core';
import { MeService } from '../me.service';
import { MeResp } from '../me-resp';
import { OrganizeSelectorComponent } from 'src/app/widget/organize-selector/organize-selector.component';
import { UpdateMeReq } from '../update-me-req';

@Component({
  selector: 'me-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.css']
})
export class MeComponent implements OnInit {
  @ViewChild('organizeSelector') organizeSelector: OrganizeSelectorComponent;
  
  me: MeResp = new MeResp();

  password: string = undefined;
  repassword: string = undefined;

  constructor(
    private meService: MeService,
  ) { }

  ngOnInit(): void {
    this.getMe();
  }

  onSaveClick() {
    if (this.password !== this.repassword) {
      alert('비밀번호가 동일하지 않습니다.');
      return;
    }
    if (this.me.name === undefined) {
      alert('이름을 입력하세요.');
      return;
    }
    if (this.me.email === undefined) {
      alert('이메일을 입력하세요.');
      return;
    }
    // if (this.me.phone === undefined) {
    //   alert('휴대 전화번호를 입력하세요.');
    //   return;
    // }
    const req = new UpdateMeReq();
    req.password = this.password;
    req.name = this.me.name;
    req.email = this.me.email;
    req.tel = this.me.tel;
    req.phone = this.me.phone;
    this.meService.updateMe(req).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        alert('저장되었습니댜.');
      }
    })
  }

  private getMe() {
    this.meService.me().subscribe(response => {
      const resp: MeResp = response.body;
      if (resp.code === 200) {
        this.me = resp;

        this.organizeSelector.setOrganizeIds(this.me.organizeIds);
      }
    });
  }

}
