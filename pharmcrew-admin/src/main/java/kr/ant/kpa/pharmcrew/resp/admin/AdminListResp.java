package kr.ant.kpa.pharmcrew.resp.admin;

import com.bumdori.spring.annotation.Description;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

import java.util.List;

@Data
public class AdminListResp extends PcResp {
    /**
     * 관리자 리스트
     */
    @Description("관리자 리스트")
    private List<Admin> admins;
    /**
     * 검색된 항목 수
     */
    @Description("검색된 항목 수")
    private Integer searchCnt;
}
