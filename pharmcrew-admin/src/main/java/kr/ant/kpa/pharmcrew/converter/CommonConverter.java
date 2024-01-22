package kr.ant.kpa.pharmcrew.converter;

import kr.ant.kpa.pharmcrew.config.storage.Storage;
import kr.ant.kpa.pharmcrew.data.facade.common.OrganizeFacade;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.common.OrganizeVo;
import kr.ant.kpa.pharmcrew.resp.common.AttachFile;
import kr.ant.kpa.pharmcrew.resp.common.Organize;
import kr.ant.kpa.pharmcrew.resp.common.Organize2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommonConverter {
    @Autowired
    private OrganizeFacade organizeFacade;

    @Autowired
    private Storage storage;

    public Organize convert(OrganizeVo s) {
        Organize o = new Organize();
        o.setOrganizeId(s.getOrganize_id());
        o.setOrganizeD1Id(s.getOrganize_d1_id());
        o.setOrganizeD2Id(s.getOrganize_d2_id());
        o.setOrganizeD3Id(s.getOrganize_d3_id());
        o.setName(s.getName());
        return o;
    }

    public Organize2 convertOrganize2(Long organizeId) {
        if (organizeId == null) return null;
        OrganizeVo organize = organizeFacade.get(organizeId);
        if (organize == null)   return null;
        List<Long> ids = new ArrayList<>();
        List<String> names = new ArrayList<>();
        ids.add(organize.getOrganize_d1_id());
        names.add(organize.getD1Name());
        if (organize.getOrganize_d2_id() != null) {
            ids.add(organize.getOrganize_d2_id());
            names.add(organize.getD2Name());
            if (organize.getOrganize_d3_id() != null) {
                ids.add(organize.getOrganize_d3_id());
                names.add(organize.getD3Name());
            }
        }

        Organize2 o = new Organize2();
        o.setIds(ids);
        o.setNames(names);
        return o;
    }

    public AttachFile convert(FileVo s) {
        if (s == null)  return null;
        AttachFile o = new AttachFile();
        o.setFileId(s.getFile_id());
        o.setOrgName(s.getOrg_name());
        o.setUrl(storage.fileUrl(s));
        return o;
    }
}
