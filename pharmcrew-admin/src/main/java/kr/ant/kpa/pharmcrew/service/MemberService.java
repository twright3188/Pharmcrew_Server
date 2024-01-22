package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.resp.member.MemberEduTimeResp;
import kr.ant.kpa.pharmcrew.resp.member.MemberListResp;
import kr.ant.kpa.pharmcrew.resp.member.MemberResp;

public interface MemberService {
    MemberListResp memberList(Long organizeId, String keyword, String pharmName, Integer page, Integer cntPerPage);
    MemberResp member(Long memberId);

    MemberEduTimeResp memberEduTime(Long memberId, String year);
}
