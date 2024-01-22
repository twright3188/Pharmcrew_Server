import { Component, OnInit, ViewChild } from '@angular/core';
import { CheckLoginIdReq } from '../check-login-id-req';
import { AdminService } from '../admin.service';
import { ActivatedRoute } from '@angular/router';
import { Admin } from '../admin';
import { Location } from '@angular/common';
import { OrganizeSelectorComponent } from 'src/app/widget/organize-selector/organize-selector.component';
import { RegistAdminReq } from '../regist-admin-req';
import { UpdateAdminReq } from '../update-admin-req';
import { Organize2 } from '../../common/organize2';

@Component({
  selector: 'user-admin-detail',
  templateUrl: './admin-detail.component.html',
  styleUrls: ['./admin-detail.component.css']
})
export class AdminDetailComponent implements OnInit {
  @ViewChild('organizeSelector') organizeSelector: OrganizeSelectorComponent;

  emailDomains = [
    'naver.com',
    'gmail.com'
  ];

  id: number;
  admin = new Admin();
  
  validLoginId = false;
  lastValidLoginId = undefined;

  password: string = undefined;
  repassword: string = undefined;

  emailId: string = undefined;
  emailDomain: string = undefined;
  emailDomainSelector: string = undefined;

  constructor(
    private adminService: AdminService,
    private route: ActivatedRoute,
    private location: Location,
  ) { }

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id');
    if (this.id !== -1) {
      this.getAdmin();
    } else {
      // this.emailDomainSelector = this.emailDomains[0];
      // this.onEmailDomainChange();
      this.emailDomainSelector = 'manual';

      this.admin.authority = this.myAuthority();
    //   this.onChangeAuthority();
    }
  }

  ngAfterViewInit() {
    // this.getAdmin();
    if (this.id === -1) {
      // this.emailDomainSelector = this.emailDomains[0];
      // this.onEmailDomainChange();

      // this.admin.authority = this.myAuthority();
      setTimeout(() => {
        this.onChangeAuthority();
      }, 100);
    }
  }

  onChangeLoginId() {
    if (this.lastValidLoginId !== this.admin.loginId) {
      this.validLoginId = false;
    } else {
      this.validLoginId = true;
    }
  }

  onCheckLoginIdClick() {
    if (this.admin.loginId === undefined) {
      alert('아이디를 입력하세요.');
      return;
    }

    const req = new CheckLoginIdReq();
    req.loginId = this.admin.loginId;
    this.adminService.checkLoginId(req).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        alert('사용할 수 있는 아이디입니다.');
        this.validLoginId = true;
        this.lastValidLoginId = this.admin.loginId;
      } else {
        this.validLoginId = false;
      }
    });
  }

  onEmailDomainChange() {
    if (this.emailDomainSelector === 'manual') {
      this.emailDomain = undefined;
    } else {
      this.emailDomain = this.emailDomainSelector;
    }
  }

  onChangeAuthority() {
    switch (this.admin.authority) {
      case 'S':
        this.organizeSelector.setOrganizeIds(undefined);
        break;
      case 'A': {
        const myOrganize = this.myOrgainze();
        this.organizeSelector.setOrganizeIds(this.admin.organize !== undefined ? this.admin.organize.ids: myOrganize !== undefined ? myOrganize.ids : undefined);
        this.organizeSelector.setOrganizeId(2, undefined);
        this.organizeSelector.setOrganizeEnable(2, false);
        this.organizeSelector.setOrganizeId(3, undefined);
        this.organizeSelector.setOrganizeEnable(3, false);
      }
        break;
      case 'O': {
        const myOrganize = this.myOrgainze();
        console.log(`myOrganize: ${myOrganize}`);
        this.organizeSelector.setOrganizeIds(this.admin.organize !== undefined ? this.admin.organize.ids: myOrganize !== undefined ? myOrganize.ids : undefined);
        if (myOrganize === undefined || (myOrganize !== undefined && myOrganize.ids.length <= 1)) {
          this.organizeSelector.setOrganizeEnable(2, true);
        }
        if (myOrganize === undefined || (myOrganize !== undefined && myOrganize.ids.length <= 2)) {
          this.organizeSelector.setOrganizeEnable(3, true);
        }
      }
        break;
    }
  }
  
  onBackClick() {
    this.goBack();
  }

  onDeleteClick() {
    // this.adminService.deleteAdmin(this.admin.adminId).subscribe(response => {
    //   const resp = response.body;
    //   if (resp.code === 200)  this.goBack();
    // });
  }

  onSaveClick() {
    if (this.admin.loginId === undefined) {
      alert('아이디를 입력하세요.');
      return;
    }
    if (this.validLoginId === false) {
      alert('아이디 중복 확인하세요.');
      return;
    }
    if (this.admin.name === undefined) {
      alert('이름을 입력하세요.');
      return;
    }
    if (this.admin.adminId === undefined &&
      this.password === undefined) {
      alert('비밀번호를 입력하세요.');
      return;
    }
    if (this.password !== this.repassword) {
      alert('비밀번호가 동일하지 않습니다.');
      return;
    }
    if (this.emailId === undefined) {
      alert('이메일 아이디를 입력하세요.');
      return;
    }
    if (this.emailDomain === undefined) {
      alert('이메일 도메인을 입력 혹은 선택하세요.');
      return;
    }
    if (this.admin.authority === undefined) {
      alert('관리자 타입을 선택하세요.');
      return;
    }
    switch (this.admin.authority) {
      case 'A':
        if (this.organizeSelector.organizeIdByDepth(1) === undefined) {
          alert('관리 조직(약사회)을 선택하세요.');
          return;
        }
        break;
      case 'O': {
        const myDepth = this.myOrgainze()?.ids?.length;
        if (this.organizeSelector.organizeIdByDepth(2) === undefined) {
          alert('관리 조직(지부)을 선택하세요.');
          return;
        }
        if (this.organizeSelector.organizeIdByDepth(3) === undefined &&
          myDepth >= 3) {
          alert('관리 조직(분회)을 선택하세요.');
          return;
        }
      }
        break;
    }
    if (this.admin.state === undefined) {
      alert('사용 여부를 선택하세요.');
      return;
    }

    if (this.admin.adminId === undefined) {
      if (!confirm('등록하시겠습니까?'))  return;
      const req = new RegistAdminReq();
      req.loginId = this.admin.loginId;
      req.password = this.password;
      req.name = this.admin.name;
      req.email = `${this.emailId}@${this.emailDomain}`;
      req.authority = this.admin.authority;
      req.tel = this.admin.tel;
      req.phone = this.admin.phone;
      req.organizeId = this.organizeSelector.organizeId();
      req.state = this.admin.state;
      this.adminService.registAdmin(req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200)  this.goBack();
      });
    } else {
      if (!confirm('수정하시겠습니까?'))  return;
      const req = new UpdateAdminReq();
      req.password = this.password;
      req.name = this.admin.name;
      req.email = `${this.emailId}@${this.emailDomain}`;
      req.authority = this.admin.authority;
      req.tel = this.admin.tel;
      req.phone = this.admin.phone;
      req.organizeId = this.organizeSelector.organizeId();
      req.state = this.admin.state;
      this.adminService.updateAdmin(this.admin.adminId, req).subscribe(response => {
        const resp = response.body;
        if (resp.code === 200) {
          if (this.admin.adminId === +localStorage.getItem('adminId')) {
            // 내 정보를 변경한 경우 내 정보 갱신
            // login.component.ts와 동일하게 맞춰야 한다.
            localStorage.setItem('name', this.admin.name);
            
            localStorage.setItem('authority', this.admin.authority);
            if (this.admin.organize !== undefined) {
              localStorage.setItem('organize', JSON.stringify(this.admin.organize));
            } else {
              localStorage.removeItem('organize');
            }
          }
          this.goBack();
        }
      });
    }
  }

  myAuthority() {
    return localStorage.getItem('authority');
  }
  
  private getAdmin() {
    // const id = +this.route.snapshot.paramMap.get('id');
    // if (id !== -1) {
      this.validLoginId = true;

      this.adminService.admin(this.id).subscribe(response => {
        const resp = response.body;
        if (resp.code !== 200)  this.goBack();

        this.admin = resp.admin;

        const emailTokens = this.admin.email.split('@');
        this.emailId = emailTokens[0];
        this.emailDomain = emailTokens[1];
        if (this.emailDomains.includes(this.emailDomain)) {
          this.emailDomainSelector = this.emailDomain;
        } else {
          this.emailDomainSelector = 'manual';
        }

        this.organizeSelector.setOrganizeIds(this.admin.organize !== undefined ? this.admin.organize.ids: undefined);
        this.onChangeAuthority();
      });
    // } else {
    //   this.emailDomainSelector = this.emailDomains[0];
    //   this.onEmailDomainChange();

    //   this.admin.authority = this.myAuthority();
    //   this.onChangeAuthority();
    // }
  }

  private myOrgainze(): Organize2 {
    const organize = localStorage.getItem('organize');
    if (organize !== null) {
      return JSON.parse(organize);
    }
    return undefined;
  }

  private goBack() {
    localStorage.setItem('action', 'back')
    this.location.back();
  }

}
