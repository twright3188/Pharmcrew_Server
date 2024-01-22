package kr.ant.kpa.pharmcrew.converter;

import org.springframework.stereotype.Component;

import com.bumdori.util.DateUtils;

import kr.ant.kpa.pharmcrew.Config;
import kr.ant.kpa.pharmcrew.db.vo.academy.DocVo;
import kr.ant.kpa.pharmcrew.db.vo.academy.VideoVo;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.resp.academy.Doc;
import kr.ant.kpa.pharmcrew.resp.academy.Video;

@Component("academyConverter")
public class AcademyConverter {

	//@Autowired
	//private 서비스클래스 서비스이름 (ActionService service);

	////////////////////////////////////////////////////////////////////////////////////////
	//	1. 문서 목록 조회
	////////////////////////////////////////////////////////////////////////////////////////

	public Doc convertDoc(DocVo vo) {

		Doc o = new Doc();

		o.setId(vo.getDoc_id()  );
		o.setTitle(vo.getTitle()  );
		
		// 다운로드 링크 구성하기 
		FileVo fileVo = vo.getDocFile();
		if (fileVo != null) {
			o.setLink(Config.Inst.localStorageUrl() + fileVo.getPath() + "/" + fileVo.getName() );
		}
		
		if (vo.getReg_dt() != null) {
			o.setDate(DateUtils.getStringFromDate(vo.getReg_dt(), "yyyy.MM.dd") );			
		}

		return o;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	2. 문서 상세 조회
	////////////////////////////////////////////////////////////////////////////////////////

	//public Doc convertDoc( vo) {
//
		//Doc o = new Doc();
//
		//o.setId(vo.get  );
		//o.setTitle(vo.get  );
		//o.setLink(vo.get  );
		//o.setDate(vo.get  );
//
		//return o;
	//}

	////////////////////////////////////////////////////////////////////////////////////////
	//	3. 동영상 목록 조회
	////////////////////////////////////////////////////////////////////////////////////////

	public Video convertVideo(VideoVo vo) {

		Video o = new Video();

		o.setId(vo.getVideo_id()  );
		o.setTitle(vo.getTitle()  );
		o.setYoutube(vo.getUrl()  );

		if (vo.getReg_dt() != null) {
			o.setDate(DateUtils.getStringFromDate(vo.getReg_dt(), "yyyy.MM.dd") );			
		}


		return o;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	4. 동영상 상세 조회
	////////////////////////////////////////////////////////////////////////////////////////

	//public Video convertVideo( vo) {
//
		//Video o = new Video();
//
		//o.setId(vo.get  );
		//o.setTitle(vo.get  );
		//o.setYoutube(vo.get  );
		//o.setDate(vo.get  );
//
		//return o;
	//}

}
