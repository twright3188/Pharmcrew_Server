package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.me.FindLoginIdResp;
import kr.ant.kpa.pharmcrew.resp.me.MeResp;

public interface MeService {
    FindLoginIdResp findLoginId(String name, String email);
    PcResp resetPassword(String name, String loginId, String email);
    MeResp me(long myId);
    PcResp updateMe(long myId, String password, String name, String email, String tel, String phone);
}
