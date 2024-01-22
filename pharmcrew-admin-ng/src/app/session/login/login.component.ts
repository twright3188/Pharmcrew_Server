import { Component, OnInit } from '@angular/core';
import { SessionService } from '../session.service';
import { LoginReq } from '../login-req';
import { Router } from '@angular/router';
import { MeService } from 'src/app/domain/me/me.service';
import { FindLoginIdReq } from '../../domain/me/find-login-id-req';
import { PcService } from 'src/app/pc.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ResetPasswordReq } from 'src/app/domain/me/reset-password-req';

declare var $ :any;

@Component({
  selector: 'account-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  findLoginIdReq = new FindLoginIdReq();
  findLoginId: string = undefined;

  resetPasswordReq = new ResetPasswordReq();

  loginReq: LoginReq = new LoginReq();

  constructor(
    private sessionService: SessionService,
    private meService: MeService,
    private pcService: PcService,
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

  onOpenFindId() {
    this.findLoginIdReq = new FindLoginIdReq();
    $('#find_id').css('display', 'block');
    // $('.popup_wrap').css('width', '500px').css('height', '330px').css('margin-left', '-250px').css('margin-top', '-150px');  // login.component.css로 이동
    $('#fade').css('display', 'block');
  }

  onSubmitFindLoginId() {
    if (this.pcService.isEmpty(this.findLoginIdReq.name)) {
      alert('이름을 입력하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.findLoginIdReq.email)) {
      alert('이메일을 입력하세요.');
      return;
    }
    this.meService.findLoginId(this.findLoginIdReq).subscribe(
      response => {
        const resp = response.body;
        if (resp.code === 200) {
          this.findLoginId = resp.loginId;
        }
      }, error => {
        if (error instanceof HttpErrorResponse) {
          if ((error as HttpErrorResponse).status === 404) {
            this.findLoginId = '등록된 아이디가 없습니다.';
          }
        }
      }
    );
  }

  onCloseFindId() {
    $('.popup_wrap').css('display', 'none');
    $('.black_overlay').css('display', 'none');
  }

  onOpenFindPassword() {
    this.resetPasswordReq = new ResetPasswordReq();
    $('#find_password').css('display', 'block');
    // $('.popup_wrap').css('width', '500px').css('height', '330px').css('margin-left', '-250px').css('margin-top', '-150px');  // login.component.css로 이동
    $('#fade').css('display', 'block');
  }

  onSubmitResetPassword() {
    if (this.pcService.isEmpty(this.resetPasswordReq.name)) {
      alert('이름을 입력하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.resetPasswordReq.loginId)) {
      alert('아이디를 입력하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.resetPasswordReq.email)) {
      alert('이메일을 입력하세요.');
      return;
    }
    this.meService.resetPassword(this.resetPasswordReq).subscribe(response => {
        // const resp = response.body;
        // if (resp.code === 200) {

        // }
        alert('입력하신 정보가 정확한 경우, 이메일이 발송됩니다.');
        this.onCloseFindPassword();
      }
    );
  }

  onCloseFindPassword() {
    $('.popup_wrap').css('display', 'none');
    $('.black_overlay').css('display', 'none');
  }

  onSubmitLogin() {
    if (this.pcService.isEmpty(this.loginReq.loginId)) {
      alert('ID를 입력하세요.');
      return;
    }
    if (this.pcService.isEmpty(this.loginReq.password)) {
      alert('비밀번호를 입력하세요.');
      return;
    }
    this.sessionService.login(this.loginReq).subscribe((response) => {
      let resp = response.body;
      // console.log(`resp: ${JSON.stringify(resp)}`);
      if (resp.code === 200) {
        // this.sessionService.isLoggedIn = true;
        localStorage.setItem('isLoggedIn', 'Y');
        localStorage.setItem('adminId', resp.admin.adminId.toString());

        localStorage.setItem('name', resp.admin.name);
        
        localStorage.setItem('authority', resp.admin.authority);
        if (resp.admin.organize !== undefined) {
          localStorage.setItem('organize', JSON.stringify(resp.admin.organize));
        } else {
          localStorage.removeItem('organize');
        }

        console.log(`redirectUrl: ${this.sessionService.redirectUrl}`);
        let redirect = this.sessionService.redirectUrl ? this.router.parseUrl(this.sessionService.redirectUrl) : '/admins';
        this.router.navigateByUrl(redirect);
      }
    });
  }

}
