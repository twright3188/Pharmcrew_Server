package kr.ant.kpa.pharmcrew.service;

import kr.ant.kpa.pharmcrew.resp.PcResp;
import kr.ant.kpa.pharmcrew.resp.academy.DocListResp;
import kr.ant.kpa.pharmcrew.resp.academy.DocResp;
import kr.ant.kpa.pharmcrew.resp.academy.VideoListResp;
import kr.ant.kpa.pharmcrew.resp.academy.VideoResp;
import kr.ant.kpa.pharmcrew.validation.exception.FailSaveFileException;
import org.springframework.web.multipart.MultipartFile;

public interface AcaedmyService {
    PcResp registerDoc(Long organizeId, String isOpen, String title, String body, MultipartFile docFile, long regId) throws FailSaveFileException;
    DocListResp docList(Long organizeId, String keyword, Integer page, Integer cntPerPage);
    DocResp doc(Long docId);
    PcResp updateDoc(Long docId, Long organizeId, String isOpen, String title, String body, MultipartFile docFile, long modId) throws FailSaveFileException;
    PcResp deleteDoc(Long docId);

    PcResp registerVideo(Long organizeId, String isOpen, String title, String url, long regId) throws FailSaveFileException;
    VideoListResp videoList(Long organizeId, String keyword, Integer page, Integer cntPerPage);
    VideoResp video(Long videoId);
    PcResp updateVideo(Long videoId, Long organizeId, String isOpen, String title, String url, long modId) throws FailSaveFileException;
    PcResp deleteVideo(Long videoId);
}
