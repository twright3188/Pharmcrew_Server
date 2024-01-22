package kr.ant.kpa.pharmcrew.config.storage.impl;

import com.bumdori.util.FileUtils;
import com.bumdori.util.ListUtils;
import com.bumdori.util.MapUtils;
import kr.ant.kpa.pharmcrew.Config;
import kr.ant.kpa.pharmcrew.config.storage.Storage;
import kr.ant.kpa.pharmcrew.db.dao.CommonDao;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalStorage implements Storage {
    @Autowired
    private CommonDao commonDao;

    @Override
    public long save(File file, String orgName, long size) {
    	
    	String filePath = path();
    	
        FileVo fileVo = FileVo.builder()
                .name(file.getName())
                .org_name(orgName)
                .size(size)
                .path(filePath)
                .build();
        commonDao.insertFile(fileVo);

        FileUtils.move(file, Config.Inst.localStoragePath() + filePath, file.getName());

        return fileVo.getFile_id();
    }

    @Override
    public String fileUrl(FileVo fileVo) {
        return String.format("%s%s/%s", Config.Inst.localStorageUrl(),
                fileVo.getPath(),
                fileVo.getName());
    }
    
    @Override
    public String fileUrl(Long fileId) {
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("file_id", fileId);
    	FileVo fileVo = commonDao.selectFile(param);
    	if (fileVo != null) {
    		return fileUrl(fileVo);
    	}
    	return null;
    }

    @Override
    public File file(FileVo fileVo) {
        return new File(String.format("%s/%s/%s", Config.Inst.localStoragePath(), fileVo.getPath(), fileVo.getName()));
    }

    @Override
    public void replace(FileVo fileVo, File file, String orgName, long size) {
        String oldName = fileVo.getName();

        fileVo.setOrg_name(orgName);
        fileVo.setName(file.getName());
        fileVo.setSize(size);
        commonDao.updateFile(fileVo);

        FileUtils.deletes(new File( Config.Inst.localStoragePath() + fileVo.getPath() + "/" + oldName));
        FileUtils.move(file, String.format("%s%s", Config.Inst.localStoragePath(), fileVo.getPath()), file.getName());
    }

    @Override
    public void replace(long fileId, File file, String orgName, long size) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("file_id", fileId);
		FileVo fileVo = commonDao.selectFile(param);
		if (fileVo != null) {
			
			String oldName = fileVo.getName();
			
			fileVo.setOrg_name(orgName);
			fileVo.setName(file.getName());
			fileVo.setSize(size);
			commonDao.updateFile(fileVo);
			
			// 기존 파일 삭제 
			FileUtils.deletes(new File( Config.Inst.localStoragePath() + fileVo.getPath() + "/" + oldName));
			// 새로운 파일 이동 
			FileUtils.move(file, Config.Inst.localStoragePath() + fileVo.getPath(), file.getName());
		}
    }

    @Override
    public void deletes(List<FileVo> fileVos) {
        if (ListUtils.isEmpty(fileVos)) return;
        List<Long> fileIds = new ArrayList<>();
        for (FileVo fileVo: fileVos) {
            fileIds.add(fileVo.getFile_id());
        }

        Map<String, Object> param = new HashMap<>();
        MapUtils.put(param, "fileIds", fileIds);
        commonDao.deleteFile(param);

        for (FileVo fileVo: fileVos) {
            FileUtils.deletes(file(fileVo));
        }
    }

    @Override
    public void delete(FileVo fileVo) {
        if (fileVo == null) return;
        commonDao.deleteFile(fileVo);

        FileUtils.deletes(file(fileVo));
    }

    @Override
    public void delete(long id) {
    	throw new RuntimeException("not implement");
    }

    /**
     * 파일 경로 지정 하기
     * @return
     */
    private String path() {
    	return FileUtils.makeDatePathString();
    }
}
