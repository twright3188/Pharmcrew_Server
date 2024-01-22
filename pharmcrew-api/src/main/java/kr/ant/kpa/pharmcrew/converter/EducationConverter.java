package kr.ant.kpa.pharmcrew.converter;

import org.springframework.stereotype.Component;

import com.bumdori.util.DateUtils;
import com.bumdori.util.StringUtils;

import kr.ant.kpa.pharmcrew.Config;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationCourseVo;
import kr.ant.kpa.pharmcrew.db.vo.education.EducationVo;
import kr.ant.kpa.pharmcrew.db.vo.member.MemberEducationVo;
import kr.ant.kpa.pharmcrew.resp.education.Edu;
import kr.ant.kpa.pharmcrew.resp.education.EduDetail;
import kr.ant.kpa.pharmcrew.resp.education.MyEdu;
import kr.ant.kpa.pharmcrew.resp.education.TimeTable;

@Component("educationConverter")
public class EducationConverter {

	//@Autowired
	//private 서비스클래스 서비스이름 (ActionService service);

	////////////////////////////////////////////////////////////////////////////////////////
	//	1.교육 목록
	////////////////////////////////////////////////////////////////////////////////////////

	public Edu convertEdu(EducationVo vo) {

		Edu o = new Edu();

		o.setId(vo.getEducation_id()  );
		if (StringUtils.isEmpty(vo.getOrganizeName())) {
			 o.setOrganize("전체");
		} else {
			o.setOrganize(vo.getOrganizeName()  );
		}
		o.setTitle(vo.getTitle()  );
		if (vo.getStart_date() != null) {
			o.setStartDate(DateUtils.getStringFromDate(vo.getStart_date(), "yyyy.MM.dd") );			
		}
		if (vo.getEnd_date() != null) {
			o.setEndDate(DateUtils.getStringFromDate(vo.getEnd_date(), "yyyy.MM.dd"));
		}
		
		o.setStartTime(vo.getStart_time().substring(0,2) + ":" + vo.getStart_time().substring(2, 4)  );
		o.setEndTime(vo.getEnd_time().substring(0,2) + ":" + vo.getEnd_time().substring(2, 4)  );
		
		o.setAddress(vo.getAddress() + " " + vo.getAddress_detail() );
		String contact =vo.getMaster();
		if (!StringUtils.isEmpty(vo.getMaster_phone())) {
			contact = String.format("%s %s", vo.getMaster_phone(), vo.getMaster()  );
		}
		o.setContact(contact);

		return o;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	2.교육 상세
	////////////////////////////////////////////////////////////////////////////////////////

	public EduDetail convertEduDetail(EducationVo vo) {

		EduDetail o = new EduDetail();
				
		o.setId(vo.getEducation_id()  );
		if (StringUtils.isEmpty(vo.getOrganizeName())) {
			 o.setOrganize("전체");
		} else {
			o.setOrganize(vo.getOrganizeName()  );
		}
		o.setTitle(vo.getTitle()  );
		// 교육 일자
		if (vo.getStart_date() != null) {
			o.setStartDate(DateUtils.getStringFromDate(vo.getStart_date(), "yyyy.MM.dd") );			
		}
		if (vo.getEnd_date() != null) {
			o.setEndDate(DateUtils.getStringFromDate(vo.getEnd_date(), "yyyy.MM.dd"));
		}
		// 교육 시간
		o.setStartTime(vo.getStart_time().substring(0,2) + ":" + vo.getStart_time().substring(2, 4)  );
		o.setEndTime(vo.getEnd_time().substring(0,2) + ":" + vo.getEnd_time().substring(2, 4)  );
		// 주소 및 문의처 - 전화번호 null 옵션이기에.
		o.setAddress(vo.getAddress() + " " + vo.getAddress_detail() );
		String contact =vo.getMaster();
		if (!StringUtils.isEmpty(vo.getMaster_phone())) {
			contact = String.format("%s %s", vo.getMaster_phone(), vo.getMaster()  );
		}
		o.setContact(contact);
		// 오시는 길 가이드 
		o.setGuide(vo.getWay_guide()  );
		o.setGuideDetail(vo.getWay_detail());
		// 위도 경도 정보
		o.setLatitude(vo.getLatitude()  );
		o.setLongitude(vo.getLongitude()  );
		// 전화번호 
		o.setTelephone(vo.getMaster_phone()  );
		
		// 약도 파일
		if (vo.getMapFile() != null) {
			o.setMapFile(Config.Inst.localStorageUrl() + vo.getMapFile().getPath() + "/" + vo.getMapFile().getName() );
			o.setMapFileName(vo.getMapFile().getOrg_name());
		}
		
		// 시간표 파일
		if (vo.getTimetableFile() != null) {
			o.setTimeTableFile(Config.Inst.localStorageUrl() + vo.getTimetableFile().getPath() + "/" + vo.getTimetableFile().getName() );
			o.setTimeTableFileName(vo.getTimetableFile().getOrg_name());
		}

		return o;
	}

	public TimeTable convertTimeTable(EducationCourseVo vo) {
		TimeTable o = new TimeTable();
		
		o.setId(vo.getCourse_id());
		o.setDays(vo.getDays());
		o.setIdx(vo.getIdx());
		o.setType(vo.getType());
		o.setTitle(vo.getTitle());
		o.setTeacher(vo.getTeacher());
		o.setRoom(vo.getRoom());
		
		o.setStartTime(vo.getStart_time().substring(0,2) + ":" + vo.getStart_time().substring(2, 4)  );
		o.setEndTime(vo.getEnd_time().substring(0,2) + ":" + vo.getEnd_time().substring(2, 4)  );

		return o;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//	3.나의 교육 목록
	////////////////////////////////////////////////////////////////////////////////////////
	
	public MyEdu convertMyEdu(MemberEducationVo vo) {

		MyEdu o = new MyEdu();

		o.setEducation(convertEduDetail(vo.getEducationVo()));
		
		if (vo.getCourseVo() != null) {
			o.setCourse(convertTimeTable(vo.getCourseVo()));
		}

		if (vo.getStart_dt() != null) {
			o.setCertStartTime(DateUtils.getStringFromDate(vo.getStart_dt(), "yyyy.MM.dd HH:mm"));
		}
		if (vo.getEnd_dt() != null) {
			o.setCertEndTime(DateUtils.getStringFromDate(vo.getEnd_dt(), "yyyy.MM.dd HH:mm"));
		}
		
		o.setCertTime(vo.getTake_min()  );
		//o.setEducation(vo.get  );
		//o.setCourse(vo.get  );
		o.setState(vo.getApproval()  );
		o.setEvalStar(vo.getEval_star()  );
		o.setEvalBody(vo.getEval_body()  );

		return o;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//	4.출석, 퇴실 등록
	////////////////////////////////////////////////////////////////////////////////////////

	//public MyEdu convertMyEdu( vo) {
//
		//MyEdu o = new MyEdu();
//
		//o.setCertStartTime(vo.get  );
		//o.setCertEndTime(vo.get  );
		//o.setCertTime(vo.get  );
		//o.setEducation(vo.get  );
		//o.setCourse(vo.get  );
		//o.setState(vo.get  );
		//o.setEvalStar(vo.get  );
		//o.setEvalBody(vo.get  );
//
		//return o;
	//}

	////////////////////////////////////////////////////////////////////////////////////////
	//	6.교육 평가, 수정하기
	////////////////////////////////////////////////////////////////////////////////////////

}
