package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.partner.PartnerListResp;
import kr.ant.kpa.pharmcrew.resp.partner.PartnerResp;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.gen.NotDeletablePartnersException;
import org.springframework.web.multipart.MultipartFile;

public interface PartnerService {
    PcResp registPartner(String name, String detail, MultipartFile iconFile, String moveUrl, String isOpen, long regId) throws FailSaveFileException;
    PartnerListResp partnerList(Integer page, Integer cntPerPage);
    PartnerResp partner(Long partnerId);
    PcResp updatePartner(Long partnerId, String name, String detail, MultipartFile iconFile, String moveUrl, String isOpen, long modId) throws FailSaveFileException;
    PcResp deletePartner(Long partnerId) throws NotDeletablePartnersException;
}
