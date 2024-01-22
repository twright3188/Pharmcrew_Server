import { Component, OnInit, ViewChild } from '@angular/core';
import { MemberService } from '../member.service';
import { ActivatedRoute } from '@angular/router';
import { Member } from '../member';
import { Location } from '@angular/common';
import { MemberEduTimeReq } from '../member-edu-time-req';
import { PcService } from 'src/app/pc.service';

@Component({
  selector: 'app-member-detail',
  templateUrl: './member-detail.component.html',
  styleUrls: ['./member-detail.component.css']
})
export class MemberDetailComponent implements OnInit {
  member: Member = new Member();

  eduTimeReq = new MemberEduTimeReq();
  eduMinute: string = undefined;
  eduRemainMinute: string = undefined;

  constructor(
    private memberService: MemberService,
    private pcService: PcService,
    private route: ActivatedRoute,
    private location: Location,
  ) { }

  ngOnInit(): void {
    this.eduTimeReq.year = new Date().getFullYear();

    this.getMember();
  }

  onYearChange() {

  }

  onBackClick() {
    this.goBack();
  }

  private getMember() {
    const id = +this.route.snapshot.paramMap.get('id');
    if (id !== -1) {
      this.memberService.member(id).subscribe(response => {
        const resp = response.body;
        if (resp.code !== 200)  this.goBack();

        this.member = resp.member;

        // this.organize1Selector.setOrganizeIds(this.member.organize1Ids);
        // if (this.member.organize2Ids !== undefined) {
        //   this.organize2Selector.setOrganizeIds(this.member.organize2Ids);
        // }

        this.memberEduTime();
      })
    }
  }

  private memberEduTime() {
    this.memberService.memberEduTime(this.member.memberId, this.eduTimeReq).subscribe(response => {
      const resp = response.body;
      if (resp.code === 200) {
        this.eduMinute = this.pcService.timeStr(resp.minute);
        this.eduRemainMinute = this.pcService.timeStr(resp.maxMinute - resp.minute);
      }
    })
  }

  private goBack() {
    localStorage.setItem('action', 'back');
    this.location.back();
  }

}
