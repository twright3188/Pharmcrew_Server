package kr.ant.kpa.pharmcrew.converter;

import org.springframework.stereotype.Component;

import com.bumdori.util.DateUtils;

import kr.ant.kpa.pharmcrew.Config;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxNoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.ptax.PtaxQnaVo;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxAttach;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxNews;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxNewsDetail;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxQna;
import kr.ant.kpa.pharmcrew.resp.ptax.PtaxQnaDetail;

@Component("ptaxConverter")
public class PtaxConverter {

	//@Autowired
	//private 서비스클래스 서비스이름 (ActionService service);

	////////////////////////////////////////////////////////////////////////////////////////
	//	1. 1:1 문의 목록
	////////////////////////////////////////////////////////////////////////////////////////

	public PtaxQna convertPtaxQna(PtaxQnaVo vo) {

		PtaxQna o = new PtaxQna();

		o.setId(vo.getQna_id()  );
		o.setTitle(vo.getTitle()  );
		o.setState("Y".equals(vo.getIs_answerd())? "답변완료" : "답변대기" );
		if (vo.getqAttachFiles()!= null && vo.getqAttachFiles().size()>0) {
			o.setExistAttach("Y");	
		} else {
			o.setExistAttach("N");
		}
		
		if (vo.getQuestion_dt() != null) {
			o.setDate(DateUtils.getStringFromDate(vo.getQuestion_dt(), "yyyy.MM.dd")  );
		}

		return o;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	2. 1:1 문의 상세
	////////////////////////////////////////////////////////////////////////////////////////

	public PtaxQnaDetail convertPtaxQnaDetail(PtaxQnaVo vo) {

		PtaxQnaDetail o = new PtaxQnaDetail();

		o.setId(vo.getQna_id()  );
		o.setTitle(vo.getTitle()  );
		o.setState("Y".equals(vo.getIs_answerd())? "답변완료" : "답변대기" );
		if (vo.getqAttachFiles()!= null && vo.getqAttachFiles().size()>0) {
			o.setExistAttach("Y");	
		} else {
			o.setExistAttach("N");
		}
		
		if (vo.getQuestion_dt() != null) {
			o.setDate(DateUtils.getStringFromDate(vo.getQuestion_dt(), "yyyy.MM.dd")  );
		}
		
		o.setQBody(vo.getBody()  );
		o.setRTitle(vo.getAns_title()  );
		o.setRBody(vo.getAns_body()  );
		if (vo.getqAttachFiles()!= null && vo.getqAttachFiles().size()>0) {
			for (FileVo fileVo : vo.getqAttachFiles()) {
				o.addQAttach(convertAttach(fileVo));
			}
			//o.setQAttach(vo.get  );
		}
		if (vo.getaAttachFiles()!= null && vo.getaAttachFiles().size()>0) {
			for (FileVo fileVo : vo.getaAttachFiles()) {
				o.addRAttach(convertAttach(fileVo));
			}
//			o.setRAttach(vo.get  );
		}
	
		
		if (vo.getAnswer_dt() != null) {
			o.setRDate(DateUtils.getStringFromDate(vo.getAnswer_dt(), "yyyy.MM.dd")  );
		}

		return o;
	}
	

	public PtaxAttach convertAttach(FileVo vo) {

		PtaxAttach o = new PtaxAttach();

		o.setName(vo.getOrg_name());
		// 다운로드 링크 구성하기 
		o.setLink(Config.Inst.localStorageUrl() + vo.getPath() + "/" + vo.getName() );

		return o;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	3. 1:1 문의 등록
	////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////////////
	//	4. 공지 목록
	////////////////////////////////////////////////////////////////////////////////////////

	public PtaxNews convertPtaxNews(PtaxNoticeVo vo) {

		PtaxNews o = new PtaxNews();

		o.setId(vo.getNotice_id()  );
		o.setType(vo.getTarget_div()  );
		
		if (vo.getAttachFiles()!= null && vo.getAttachFiles().size()>0) {
			o.setExistAttach("Y");	
		} else {
			o.setExistAttach("N");
		}
		
		o.setTitle(vo.getTitle()  );
		
		if (vo.getReg_dt() != null) {
			o.setDate(DateUtils.getStringFromDate(vo.getReg_dt(), "yyyy.MM.dd")  );
		}

		return o;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	5. 공지 상세
	////////////////////////////////////////////////////////////////////////////////////////

	public PtaxNewsDetail convertPtaxNewsDetail(PtaxNoticeVo vo) {

		PtaxNewsDetail o = new PtaxNewsDetail();
		
		o.setId(vo.getNotice_id()  );
		o.setType(vo.getTarget_div()  );
		
		if (vo.getAttachFiles()!= null && vo.getAttachFiles().size()>0) {
			o.setExistAttach("Y");	
		} else {
			o.setExistAttach("N");
		}
		
		o.setTitle(vo.getTitle()  );
		
		if (vo.getReg_dt() != null) {
			o.setDate(DateUtils.getStringFromDate(vo.getReg_dt(), "yyyy.MM.dd")  );
		}
		
		if (vo.getRegAdmin() != null) {
			o.setPerson(vo.getRegAdmin().getName() );
		} else {
			o.setPerson("관리자"); 
		}
		
		if (vo.getAttachFiles()!= null && vo.getAttachFiles().size()>0) {
			for (FileVo fileVo : vo.getAttachFiles()) {
				o.addAttach(convertAttach(fileVo));
			}
		}
		
		o.setDetail("/web/ptax/news/" + vo.getNotice_id()  );

		return o;
	}

}
