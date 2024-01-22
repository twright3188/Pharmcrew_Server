package kr.ant.kpa.pharmcrew.resp.common;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import lombok.Data;

import java.util.List;

@Data
public class PharmListResp extends PcResp {
    private List<String> names;
}
