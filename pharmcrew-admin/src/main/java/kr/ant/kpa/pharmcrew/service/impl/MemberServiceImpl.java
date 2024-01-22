package kr.ant.kpa.pharmcrew.service.impl;

import com.bumdori.util.ListUtils;
import com.bumdori.util.MapUtils;
import kr.ant.kpa.pharmcrew.Constants;
import kr.ant.kpa.pharmcrew.converter.MemberConverter;
import kr.ant.kpa.pharmcrew.db.dao.MemberDao;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberVo;
import kr.ant.kpa.pharmcrew.resp.member.MemberEduTimeResp;
import kr.ant.kpa.pharmcrew.resp.member.MemberListResp;
import kr.ant.kpa.pharmcrew.resp.member.MemberResp;
import kr.ant.kpa.pharmcrew.service.MemberService;
import kr.ant.kpa.pharmcrew.validation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MemberConverter memberConverter;

    @Override
    public MemberListResp memberList(Long organizeId, String keyword, String pharmName, Integer page, Integer cntPerPage) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "regDtNotNull", true);
        MapUtils.put(param, "pathDownOrganizeId", organizeId);  // organizeId의 하위 조직 포함
        MapUtils.put(param, "keyword", keyword);
        MapUtils.put(param, "pharm_name", pharmName);
        MapUtils.pagingMaridDb(param, page, cntPerPage);
        MapUtils.put(param, "orderby", "reg_dt desc");
        List<MemberVo> memberVos = memberDao.listMember(param);

        MemberListResp resp = new MemberListResp();
        if (ListUtils.isEmpty(memberVos)) {
            resp.setSearchCnt(0);
        } else {
            for (MemberVo memberVo: memberVos) {
                if (resp.getMembers() == null) {
                    resp.setMembers(new ArrayList<>());
                }
                resp.getMembers().add(memberConverter.convert(memberVo));
            }
            resp.setSearchCnt(memberDao.countMember(param));
        }
        return resp;
    }

    @Override
    public MemberResp member(Long memberId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "member_id", memberId);
        MemberVo memberVo = memberDao.selectMember(param);
        if (memberVo == null)   throw new NotFoundException();

        MemberResp resp = new MemberResp();
        resp.setMember(memberConverter.convert(memberVo));
        return resp;
    }

    @Override
    public MemberEduTimeResp memberEduTime(Long memberId, String year) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "member_id", memberId);
        MapUtils.put(param, "year", year);
        int eduTime = memberDao.selectMemberEduTime(param);

        MemberEduTimeResp resp = new MemberEduTimeResp();
        resp.setMaxMinute(Constants.EDU_YEAR_MINUTE);
        resp.setMinute(eduTime);
        return resp;
    }
}
