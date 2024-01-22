package kr.ant.kpa.pharmcrew.service.impl;

import com.bumdori.util.ListUtils;
import com.bumdori.util.MapUtils;
import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.config.storage.Storage;
import kr.ant.kpa.pharmcrew.converter.PartnerConverter;
import kr.ant.kpa.pharmcrew.db.dao.PartnersDao;
import kr.ant.kpa.pharmcrew.db.vo.partners.PartnersVo;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.partner.PartnerListResp;
import kr.ant.kpa.pharmcrew.resp.partner.PartnerResp;
import kr.ant.kpa.pharmcrew.service.PartnerService;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.NotFoundException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotDeletablePartnersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PartnerServiceImpl implements PartnerService {
    @Autowired
    private PartnersDao partnersDao;

    @Autowired
    private PartnerConverter partnerConverter;

    @Autowired
    private Storage storage;
    @Autowired
    private PcUtils pcUtils;

    @Transactional
    @Override
    public PcResp registPartner(String name, String detail, MultipartFile iconFile, String moveUrl, String isOpen, long regId) throws FailSaveFileException {
        File file = pcUtils.save(iconFile);
        long fileId = storage.save(file, iconFile.getOriginalFilename(), iconFile.getSize());

        PartnersVo partnersVo = new PartnersVo();
        partnersVo.setName(name);
        partnersVo.setDetail(detail);
        partnersVo.setIcon_file_id(fileId);
        partnersVo.setMove_url(moveUrl);
        partnersVo.setReg_id(regId);
        partnersDao.insertPartners(partnersVo);

        return new PcResp();
    }

    @Override
    public PartnerListResp partnerList(Integer page, Integer cntPerPage) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "withMemberCount", true);
        MapUtils.put(param, "orderby", "reg_dt desc");
        MapUtils.pagingMaridDb(param, page, cntPerPage);
        MapUtils.put(param, "orderby", "reg_dt desc");
        List<PartnersVo> partnerVos = partnersDao.listPartners(param);

        PartnerListResp resp = new PartnerListResp();
        if (ListUtils.isEmpty(partnerVos)) {
            resp.setSearchCnt(0);
        } else {
            for (PartnersVo partnersVo: partnerVos) {
                if (resp.getPartners() == null) {
                    resp.setPartners(new ArrayList<>());
                }
                resp.getPartners().add(partnerConverter.convert(partnersVo));
            }
            resp.setSearchCnt(partnersDao.countPartners(param));
        }
        return resp;
    }

    @Override
    public PartnerResp partner(Long partnerId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "partner_id", partnerId);
        MapUtils.put(param, "withMemberCount", true);
        PartnersVo partnerVo = partnersDao.selectPartners(param);
        if (partnerVo == null)  throw new NotFoundException();

        PartnerResp resp = new PartnerResp();
        resp.setPartner(partnerConverter.convert(partnerVo));
        return resp;
    }

    @Transactional
    @Override
    public PcResp updatePartner(Long partnerId, String name, String detail, MultipartFile iconFile, String moveUrl, String isOpen, long modId) throws FailSaveFileException {
        File file = null;
        PartnersVo orgPartnersVo = null;
        if (iconFile != null) {
            file = pcUtils.save(iconFile);

            Map<String, Object> param = new HashMap<>();
            MapUtils.put(param, "partner_id", partnerId);
            orgPartnersVo = partnersDao.selectPartners(param);
        }

        PartnersVo partnersVo = new PartnersVo();
        partnersVo.setPartner_id(partnerId);
        partnersVo.setName(name);
        partnersVo.setDetail(detail);
        partnersVo.setIs_open(isOpen);
//        partnersVo.setIcon(fileId);
        partnersVo.setMove_url(moveUrl);
        partnersVo.setMod_id(modId);
        partnersDao.updatePartners(partnersVo);

        if (file != null) {
            storage.replace(orgPartnersVo.getIconFile(), file, iconFile.getOriginalFilename(), iconFile.getSize());
        }

        return new PcResp();
    }

    @Override
    public PcResp deletePartner(Long partnerId) throws NotDeletablePartnersException {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "partner_id", partnerId);
        PartnersVo partnerVo = partnersDao.selectPartners(param);

        try {
            partnersDao.deletePartners(partnerVo);
        } catch (DataIntegrityViolationException e) {
            throw new NotDeletablePartnersException();
        }

        storage.delete(partnerVo.getIconFile());

        return new PcResp();
    }
}
