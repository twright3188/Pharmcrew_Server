package kr.ant.kpa.pharmcrew.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bumdori.util.DateUtils;
import com.bumdori.util.StringUtils;

import kr.ant.kpa.pharmcrew.Config;
import kr.ant.kpa.pharmcrew.data.facade.common.OrganizeFacade;
import kr.ant.kpa.pharmcrew.db.vo.common.FileVo;
import kr.ant.kpa.pharmcrew.db.vo.news.NoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.news.QnaVo;
import kr.ant.kpa.pharmcrew.resp.news.News;
import kr.ant.kpa.pharmcrew.resp.news.NewsAttach;
import kr.ant.kpa.pharmcrew.resp.news.NewsDetail;
import kr.ant.kpa.pharmcrew.resp.news.Qna;
import kr.ant.kpa.pharmcrew.resp.news.QnaDetail;

@Component("newsConverter")
public class NewsConverter {

	@Autowired
	private OrganizeFacade organizeFacade;

	 
	////////////////////////////////////////////////////////////////////////////////////////
	//	1.소식방 목록
	////////////////////////////////////////////////////////////////////////////////////////

	public News convertNews(NoticeVo vo) {

		News o = new News();

		o.setId(vo.getNotice_id()  );
		if (StringUtils.isEmpty(vo.getOrganizeName())) {
			 o.setType("전체공지");
			 o.setIsTop("Y" );
		} else {
			o.setType(vo.getOrganizeName()  );
			o.setIsTop("N" );
		}

		o.setTitle(vo.getTitle()  );

		if (vo.getReg_dt() != null) {
			o.setDate(DateUtils.getStringFromDate(vo.getReg_dt(), "yyyy.MM.dd") );
		}

		return o;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	2.소식방 상세
	////////////////////////////////////////////////////////////////////////////////////////

	public NewsDetail convertNewsDetail(NoticeVo vo) {

		NewsDetail o = new NewsDetail();

		o.setId(vo.getNotice_id()  );
		if (StringUtils.isEmpty(vo.getOrganizeName())) {
			 o.setType("전체공지");
				o.setIsTop("Y" );
		} else {
			o.setType(vo.getOrganizeName()  );
			o.setIsTop("N" );
		}

		o.setTitle(vo.getTitle()  );
	
		if (vo.getReg_dt() != null) {
			o.setDate(DateUtils.getStringFromDate(vo.getReg_dt(), "yyyy.MM.dd") );
		}
		
		if (vo.getAttachFiles() != null) {
			for (FileVo fileVo : vo.getAttachFiles()) {
				o.addAttach(convertAttach(fileVo));
			}
		}
						
		o.setDetail("/web/news/" + vo.getNotice_id()  );

		return o;
	}

	public NewsAttach convertAttach(FileVo vo) {

		NewsAttach o = new NewsAttach();

		o.setName(vo.getOrg_name());
		// 다운로드 링크 구성하기 
		o.setLink(Config.Inst.localStorageUrl() + vo.getPath() + "/" + vo.getName() );

		return o;
	}

	
	////////////////////////////////////////////////////////////////////////////////////////
	//	3.문의하기 등록
	////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////////////
	//	4.나의 문의 목록
	////////////////////////////////////////////////////////////////////////////////////////

	public Qna convertQna(QnaVo vo) {

		Qna o = new Qna();

		o.setId(vo.getQna_id()  );
		o.setCategory(vo.getTarget_div());
		o.setTarget(vo.getTarget_id());
		o.setTitle(vo.getTitle()  );
		if (vo.getQuestion_dt() != null) {
			o.setDate(DateUtils.getStringFromDate(vo.getQuestion_dt(), "yyyy.MM.dd")  );
		}
		o.setState("Y".equals(vo.getIs_answerd())? "답변완료" : "답변대기" );
		

		return o;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	5.나의 문의 상세
	////////////////////////////////////////////////////////////////////////////////////////

	public QnaDetail convertQnaDetail(QnaVo vo) {

		QnaDetail o = new QnaDetail();

		o.setId(vo.getQna_id()  );
		o.setCategory(vo.getTarget_div());
		o.setTarget(vo.getTarget_id());
		// 질의 정보 
		o.setTitle(vo.getTitle()  );
		o.setQBody(vo.getBody()  );
		if (vo.getQuestion_dt() != null) {
			o.setDate(DateUtils.getStringFromDate(vo.getQuestion_dt(), "yyyy.MM.dd")  );
		}
		
		o.setState("Y".equals(vo.getIs_answerd())? "답변완료" : "답변대기" );
		
		// 회신 정보 
		o.setRTitle(vo.getAns_title()  );
		o.setRBody(vo.getAns_body()  );
		if (vo.getAnswer_dt() != null) {
			o.setRDate(DateUtils.getStringFromDate(vo.getAnswer_dt(), "yyyy.MM.dd")  );
		}
		
		// 첨부 파일 정보 
		if (vo.getAttachFile() != null) {
			o.setAttachName(vo.getAttachFile().getName());
			o.setAttachPath(Config.Inst.localStorageUrl() + vo.getAttachFile().getPath() + "/" + vo.getAttachFile().getName() );
		}
		
		return o;
	}

}
