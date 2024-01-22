package kr.ant.kpa.pharmcrew.service.impl;

import com.bumdori.util.BrowserUtils;
import com.bumdori.util.MapUtils;
import kr.ant.kpa.pharmcrew.config.storage.Storage;
import kr.ant.kpa.pharmcrew.converter.CommonConverter;
import kr.ant.kpa.pharmcrew.db.dao.CommonDao;
import kr.ant.kpa.pharmcrew.db.dao.MemberDao;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.common.OrganizeVo;
import kr.ant.kpa.pharmcrew.resp.common.OrganizeListResp;
import kr.ant.kpa.pharmcrew.resp.common.PharmListResp;
import kr.ant.kpa.pharmcrew.service.CommonService;
import kr.ant.kpa.pharmcrew.validation.exception.NotFoundException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CommonDao commonDao;
    @Autowired
    private MemberDao memberDao;

    @Autowired
    private CommonConverter commonConverter;

    @Autowired
    private Storage storage;

    @Override
    public OrganizeListResp organizeList(Long organizeId, Integer depth) {
        Map<String, Object> param = new HashMap<>();
        if (depth != null) {
            switch (depth) {
                case 1:
                    MapUtils.put(param, "organize_d1_id", organizeId);
                    break;
                case 2:
                    MapUtils.put(param, "organize_d2_id", organizeId);
                    break;
            }
            MapUtils.put(param, "depth", depth + 1);
        } else {
            MapUtils.put(param, "depth", 1);
        }
        List<OrganizeVo> organizeVoList = commonDao.listOrganize(param);

        OrganizeListResp resp = new OrganizeListResp();
        resp.setDepth(depth);
        for (OrganizeVo organizeVo: organizeVoList) {
            if (resp.getOrganizes() == null) {
                resp.setOrganizes(new ArrayList<>());
            }
            resp.getOrganizes().add(commonConverter.convert(organizeVo));
        }
        return resp;
    }

    @Override
    public PharmListResp pharmList() {
        List<String> pharmNames = memberDao.listMemberPharmName();

        PharmListResp resp = new PharmListResp();
        for (String pharmName: pharmNames) {
            if (pharmName == null)  continue;
            if (resp.getNames() == null) {
                resp.setNames(new ArrayList<>());
            }
            resp.getNames().add(pharmName);
        }
        return resp;
    }

    @Override
    public void downloadFile(long fileId, HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream os = null;
        FileInputStream fis = null;
        try {
            Map<String, Object> param = new HashMap<>();
            MapUtils.put(param, "file_id", fileId);
            FileVo fileVo = commonDao.selectFile(param);

            File file = storage.file(fileVo);

            response.setContentLength((int) file.length());
            response.setStatus(HttpServletResponse.SC_OK);
            response.setHeader("Content-Disposition", BrowserUtils.getDisposition(fileVo.getOrg_name(), BrowserUtils.getBrowser(request)));

            os = response.getOutputStream();

            fis = new FileInputStream(file);
            IOUtils.copy(fis, os);

            fis.close();
            os.close();
        } catch (IOException e) {
            throw new NotFoundException();
        } finally {
            try {
                if (fis != null)    fis.close();
                if (os != null) os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
