package kr.ant.kpa.pharmcrew.converter;

import org.springframework.stereotype.Component;

import kr.ant.kpa.pharmcrew.Config;
import kr.ant.kpa.pharmcrew.db.vo.common.AppVersionVo;
import kr.ant.kpa.pharmcrew.db.vo.news.BannerVo;
import kr.ant.kpa.pharmcrew.db.vo.news.NoticeVo;
import kr.ant.kpa.pharmcrew.db.vo.news.PopupVo;
import kr.ant.kpa.pharmcrew.resp.root.Advertise;
import kr.ant.kpa.pharmcrew.resp.root.Notice;
import kr.ant.kpa.pharmcrew.resp.root.Version;

@Component("rootConverter")
public class RootConverter {

	//@Autowired
	//private 서비스클래스 서비스이름 (ActionService service);

	////////////////////////////////////////////////////////////////////////////////////////
	//	1.버전 체크
	////////////////////////////////////////////////////////////////////////////////////////

	public Version convertVersion(AppVersionVo vo) {

		Version o = new Version();

		o.setVersion(vo.getVersion()  );
		o.setForced(vo.getForce_count()>0? "Y":"N");
		o.setDesc(vo.getDetail() );

		return o;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	2.푸시 토큰 등록
	////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////////////
	//	3.메인 정보 요청
	////////////////////////////////////////////////////////////////////////////////////////

	public Advertise convertAdvertise(BannerVo vo) {

		Advertise o = new Advertise();

		o.setId(vo.getBanner_id()  );
		o.setIdx(vo.getOpen_idx()  );
		o.setMoveType(vo.getMove_div()  );
		o.setMoveUrl(vo.getMove_url()  );
		o.setMoveId(vo.getMove_id()  );
		// 파일 이미지 
		if(vo.getImgFile() != null) {
			o.setImage(Config.Inst.localStorageUrl() + vo.getImgFile().getPath() + "/" + vo.getImgFile().getName() );
		}
		
		return o;
	}

	public Advertise convertAdvertise(PopupVo vo) {

		Advertise o = new Advertise();

		o.setId(vo.getPopup_id()  );
		o.setIdx(vo.getOpen_idx()  );
		o.setMoveType(vo.getMove_div()  );
		o.setMoveUrl(vo.getMove_url()  );
		o.setMoveId(vo.getMove_id()  );
		// 파일 이미지 
		if(vo.getImgFile() != null) {
			o.setImage(Config.Inst.localStorageUrl() + vo.getImgFile().getPath() + "/" + vo.getImgFile().getName() );
		}

		return o;
	}


	//public Notice convertNotice( vo) {
//
		//Notice o = new Notice();
//
		//o.setId(vo.get  );
		//o.setTitle(vo.get  );
//
		//return o;
	//}

	////////////////////////////////////////////////////////////////////////////////////////
	//	4.메인 팝업 정보 요청
	////////////////////////////////////////////////////////////////////////////////////////

	//public Advertise convertAdvertise( vo) {
//
		//Advertise o = new Advertise();
//
		//o.setId(vo.get  );
		//o.setIdx(vo.get  );
		//o.setImage(vo.get  );
		//o.setMoveType(vo.get  );
		//o.setMoveUrl(vo.get  );
		//o.setMoveId(vo.get  );
//
		//return o;
	//}

	////////////////////////////////////////////////////////////////////////////////////////
	//	5.메인 배너 정보 요청
	////////////////////////////////////////////////////////////////////////////////////////

	//public Advertise convertAdvertise( vo) {
//
		//Advertise o = new Advertise();
//
		//o.setId(vo.get  );
		//o.setIdx(vo.get  );
		//o.setImage(vo.get  );
		//o.setMoveType(vo.get  );
		//o.setMoveUrl(vo.get  );
		//o.setMoveId(vo.get  );
//
		//return o;
	//}

	////////////////////////////////////////////////////////////////////////////////////////
	//	6.메인 파트너 서비스 요청
	////////////////////////////////////////////////////////////////////////////////////////

	

	////////////////////////////////////////////////////////////////////////////////////////
	//	7.메인 한줄 공지 요청
	////////////////////////////////////////////////////////////////////////////////////////

	public Notice convertNotice(NoticeVo vo) {

		Notice o = new Notice();

		o.setId(vo.getNotice_id()  );
		o.setTitle(vo.getTitle()  );

		return o;
	}

}
