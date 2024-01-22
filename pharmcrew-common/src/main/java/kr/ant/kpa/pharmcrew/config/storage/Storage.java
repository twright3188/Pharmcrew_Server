package kr.ant.kpa.pharmcrew.config.storage;

import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;

import java.io.File;
import java.util.List;

public interface Storage {
    /**
     * 파일을 저장한다.
     * @param file
     * @param orgName   원본 파일명
     * @param size
//     * @param type  N(otice): 공지, ...
     * @return  파일 ID
     */
    long save(File file, String orgName, long size/*, FILE type*/);

    /**
     * 파일 URL
     * @param fileVo
     * @return
     */
    String fileUrl(FileVo fileVo);
    /**
     * 파일 URL
     * @param fileId
     * @return
     */
    @Deprecated
    // news.xml의 popup-result-map 참조해서 FileVo로 사용할 것
    String fileUrl(Long fileId);

    /**
     * 파일
     * @param fileVo
     * @return
     */
    File file(FileVo fileVo);

    void replace(FileVo fileVo, File file, String orgName, long size);
    /**
     * 파일을 변경한다.
     * @param fileId
     * @param file
     * @param orgName
     * @param size
     */
    void replace(long fileId, File file, String orgName, long size);

    void deletes(List<FileVo> fileVos);
    void delete(FileVo fileVo);
    /**
     * 파일을 삭제한다.
     * @param id    파일 ID
     */
    void delete(long id);

}
