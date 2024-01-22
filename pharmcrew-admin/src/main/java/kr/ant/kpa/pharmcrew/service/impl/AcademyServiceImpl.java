package kr.ant.kpa.pharmcrew.service.impl;

import com.bumdori.util.ListUtils;
import com.bumdori.util.MapUtils;
import kr.ant.kpa.pharmcrew.PcUtils;
import kr.ant.kpa.pharmcrew.config.storage.Storage;
import kr.ant.kpa.pharmcrew.converter.AcademyConverter;
import kr.ant.kpa.pharmcrew.db.dao.AcademyDao;
import kr.ant.kpa.pharmcrew.db.vo.academy.DocVo;
import kr.ant.kpa.pharmcrew.db.vo.academy.VideoVo;
import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.academy.DocListResp;
import kr.ant.kpa.pharmcrew.resp.academy.DocResp;
import kr.ant.kpa.pharmcrew.resp.academy.VideoListResp;
import kr.ant.kpa.pharmcrew.resp.academy.VideoResp;
import kr.ant.kpa.pharmcrew.service.AcaedmyService;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import kr.ant.kpa.pharmcrew.validation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AcademyServiceImpl implements AcaedmyService {
    @Autowired
    private AcademyDao academyDao;

    @Autowired
    private AcademyConverter academyConverter;

    @Autowired
    private Storage storage;
    @Autowired
    private PcUtils pcUtils;

    @Transactional
    @Override
    public PcResp registerDoc(Long organizeId, String isOpen, String title, String body, MultipartFile docFile, long regId) throws FailSaveFileException {
        File file = pcUtils.save(docFile);
        long fileId = storage.save(file, docFile.getOriginalFilename(), docFile.getSize());

        DocVo docVo = new DocVo();
        docVo.setOrganize_id(organizeId);
        docVo.setTitle(title);
        docVo.setBody(body);
        docVo.setDoc_file_id(fileId);
        docVo.setOpen_yn(isOpen);
        docVo.setReg_id(regId);
        academyDao.insertDoc(docVo);

        return new PcResp();
    }

    @Override
    public DocListResp docList(Long organizeId, String keyword, Integer page, Integer cntPerPage) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "pathDownOrganizeId", organizeId);  // organizeId의 하위 조직 포함
        MapUtils.put(param, "keyword", keyword);
        MapUtils.pagingMaridDb(param, page, cntPerPage);
        MapUtils.put(param, "orderby", "reg_dt desc");
        List<DocVo> docVos = academyDao.listDoc(param);

        DocListResp resp = new DocListResp();
        if (ListUtils.isEmpty(docVos)) {
            resp.setSearchCnt(0);
        } else {
            for (DocVo docVo: docVos) {
                if (resp.getDocs() == null) {
                    resp.setDocs(new ArrayList<>());
                }
                resp.getDocs().add(academyConverter.convert(docVo));
            }
            resp.setSearchCnt(academyDao.countDoc(param));
        }

        return resp;
    }

    @Override
    public DocResp doc(Long docId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "doc_id", docId);
        DocVo docVo = academyDao.selectDoc(param);
        if (docVo == null)  throw new NotFoundException();

        DocResp resp = new DocResp();
        resp.setDoc(academyConverter.convert(docVo));
        return resp;
    }

    @Transactional
    @Override
    public PcResp updateDoc(Long docId, Long organizeId, String isOpen, String title, String body, MultipartFile docFile, long modId) throws FailSaveFileException {
        File file = null;
        DocVo orgDocVo = null;
        if (docFile != null) {
            file = pcUtils.save(docFile);

            Map<String, Object> param = new HashMap<>();
            MapUtils.put(param, "doc_id", docId);
            orgDocVo = academyDao.selectDoc(param);
        }

        DocVo docVo = new DocVo();
        docVo.setDoc_id(docId);
        docVo.setOrganize_id(organizeId);
        docVo.setTitle(title);
        docVo.setBody(body);
        docVo.setOpen_yn(isOpen);
        docVo.setMod_id(modId);
        academyDao.updateDoc(docVo);

        if (file != null) {
            storage.replace(orgDocVo.getDocFile(), file, docFile.getOriginalFilename(), docFile.getSize());
        }

        return new PcResp();
    }

    @Transactional
    @Override
    public PcResp deleteDoc(Long docId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "doc_id", docId);
        DocVo docVo = academyDao.selectDoc(param);

        academyDao.deleteDoc(docVo);

        storage.delete(docVo.getDocFile());

        return new PcResp();
    }

    @Transactional
    @Override
    public PcResp registerVideo(Long organizeId, String isOpen, String title, String url, long regId) throws FailSaveFileException {
//        Long fileId = null;
//        if (thumbFile != null) {
//            File file = pcUtils.save(thumbFile);
//            fileId = storage.save(file, thumbFile.getOriginalFilename(), thumbFile.getSize());
//        }

        VideoVo videoVo = new VideoVo();
        videoVo.setOrganize_id(organizeId);
        videoVo.setTitle(title);
        videoVo.setUrl(url);
//        videoVo.setThumb_file_id(fileId);
        videoVo.setOpen_yn(isOpen);
        videoVo.setReg_id(regId);
        academyDao.insertVideo(videoVo);

        return new PcResp();
    }

    @Override
    public VideoListResp videoList(Long organizeId, String keyword, Integer page, Integer cntPerPage) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "pathDownOrganizeId", organizeId);  // organizeId의 하위 조직 포함
        MapUtils.put(param, "keyword", keyword);
        MapUtils.pagingMaridDb(param, page, cntPerPage);
        MapUtils.put(param, "orderby", "reg_dt desc");
        List<VideoVo> videoVos = academyDao.listVideo(param);

        VideoListResp resp = new VideoListResp();
        if (ListUtils.isEmpty(videoVos)) {
            resp.setSearchCnt(0);
        } else {
            for (VideoVo videoVo: videoVos) {
                if (resp.getVideos() == null) {
                    resp.setVideos(new ArrayList<>());
                }
                resp.getVideos().add(academyConverter.convert(videoVo));
            }
            resp.setSearchCnt(academyDao.countVideo(param));
        }

        return resp;
    }

    @Override
    public VideoResp video(Long videoId) {
        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "video_id", videoId);
        VideoVo videoVo = academyDao.selectVideo(param);
        if (videoVo == null)  throw new NotFoundException();

        VideoResp resp = new VideoResp();
        resp.setVideo(academyConverter.convert(videoVo));
        return resp;
    }

    @Transactional
    @Override
    public PcResp updateVideo(Long videoId, Long organizeId, String isOpen, String title, String url, long modId) throws FailSaveFileException {
//        File file = null;
//        VideoVo orgVideoVo = null;
//        if (thumbFile != null) {
//            file = pcUtils.save(thumbFile);
//
//            Map<String, Object> param = new HashMap<>();
//            MapUtils.put(param, "video_id", videoId);
//            orgVideoVo = academyDao.selectVideo(param);
//        }

        VideoVo videoVo = new VideoVo();
        videoVo.setVideo_id(videoId);
        videoVo.setOrganize_id(organizeId);
        videoVo.setTitle(title);
        videoVo.setUrl(url);
        videoVo.setOpen_yn(isOpen);
        videoVo.setMod_id(modId);
        academyDao.updateVideo(videoVo);

//        if (file != null) {
//            storage.replace(orgVideoVo.getThumbFile(), file, thumbFile.getOriginalFilename(), thumbFile.getSize());
//        }

        return new PcResp();
    }

    @Transactional
    @Override
    public PcResp deleteVideo(Long videoId) {
//        Map<String, Object> param = new HashMap<>();
//        MapUtils.put(param, "video_id", videoId);
//        VideoVo videoVo = academyDao.selectVideo(param);

        VideoVo videoVo = new VideoVo();
        videoVo.setVideo_id(videoId);
        academyDao.deleteVideo(videoVo);

//        storage.delete(docVo.getThumbFile());

        return new PcResp();
    }
}
