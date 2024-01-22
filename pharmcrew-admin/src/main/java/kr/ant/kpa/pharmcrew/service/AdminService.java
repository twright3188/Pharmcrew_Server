package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.admin.AdminListResp;
import kr.ant.kpa.pharmcrew.resp.admin.AdminResp;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotUseableLoginidException;

public interface AdminService {
    PcResp registAdmin(String loginId, String password, String name, String email, String authority, String tel, String phone, Long organizeId, String state, long regId);
    PcResp checkLoginId(String loginId) throws NotUseableLoginidException;
    AdminListResp adminList(String keyword, Integer page, Integer cntPerPage, Long organizeId);
    AdminResp admin(Long adminId);
    PcResp updateAdmin(Long adminId, String password, String name, String email, String authority, String tel, String phone, Long organizeId, String state, long modId);
    AdminResp deleteAdmin(Long adminId);
}
