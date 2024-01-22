package kr.ant.kpa.pharmcrew.db.dao;

import java.util.List;
import java.util.Map;

import kr.ant.kpa.pharmcrew.db.vo.academy.DocVo;
import kr.ant.kpa.pharmcrew.db.vo.academy.VideoVo;

public interface AcademyDao {

	////////////////////////////////////////////////////////////////////////////////////////
	//	문서
	////////////////////////////////////////////////////////////////////////////////////////

	void insertDoc(DocVo vo); 
	List<DocVo> listDoc(Map<String, Object> param); 
	int countDoc(Map<String, Object> param); 
	DocVo selectDoc(Map<String, Object> param); 
	void updateDoc(DocVo vo); 
	void deleteDoc(DocVo vo); 


	////////////////////////////////////////////////////////////////////////////////////////
	//	동영상
	////////////////////////////////////////////////////////////////////////////////////////

	void insertVideo(VideoVo vo); 
	List<VideoVo> listVideo(Map<String, Object> param); 
	int countVideo(Map<String, Object> param); 
	VideoVo selectVideo(Map<String, Object> param); 
	void updateVideo(VideoVo vo); 
	void deleteVideo(VideoVo vo); 


}

