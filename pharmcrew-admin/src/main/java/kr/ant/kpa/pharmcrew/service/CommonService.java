package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.resp.common.OrganizeListResp;
import kr.ant.kpa.pharmcrew.resp.common.PharmListResp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommonService {
    OrganizeListResp organizeList(Long organizeId, Integer depth);
    PharmListResp pharmList();

    void downloadFile(long fileId, HttpServletRequest request, HttpServletResponse response);
}
