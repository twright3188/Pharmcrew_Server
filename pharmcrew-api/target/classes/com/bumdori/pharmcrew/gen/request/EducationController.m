//
//	EducationController.m
//	AFNetworking
//
//	 Created by Sungs on 2020. 05. 20.
//	 Copyright © 2020 Sungs. All rights reserved.

#import "EducationController.h"

@implementation EducationController

/**
 * Page=null , 교육 목록을 요청한다.
 * @param page : 요청할 페이지 : 1 에서 시작
 * @param count : 페이지당 카운트: Default=30개
 */
-(void)getGetEduListWithPage:(NSInteger)page
                       count:(NSInteger)count
                     success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                     failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/edu/list";

	//파라메터 구성
	NSString* stringPage = [NSString stringWithFormat:@"%ld", page];
	NSString* stringCount = [NSString stringWithFormat:@"%ld", count];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              stringPage,@"page",
                              stringCount,@"count",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	//NSArray* educations = [[result objectForKey:@" educations"] allKeys];				// 교육 목록 정보 ==> 리스트 구조체
		NSString* address = [result objectForKey:@"address"];						// 장소
		NSString* contact = [result objectForKey:@"contact"];						// 문의처
		NSString* endDate = [result objectForKey:@"endDate"];						// 종료일 (yyyy.MM.dd)
		NSString* endTime = [result objectForKey:@"endTime"];						// 종료 시간 (HH:mm)
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 교육 아이디
		NSString* organize = [result objectForKey:@"organize"];						// 주관 부서
		NSString* startDate = [result objectForKey:@"startDate"];						// 시작일 (yyyy.MM.dd)
		NSString* startTime = [result objectForKey:@"startTime"];						// 시작 시간 (HH:mm)
		NSString* title = [result objectForKey:@"title"];						// 교육명
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 교육 상세 정보를 요청한다.
 * @param eduId : 교육 아이디
 */
-(void)getGetEduDetailWithEduId:(NSInteger)eduId
                        success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                        failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/edu/{eduId}";

	//파라메터 구성
	NSString* stringEduId = [NSString stringWithFormat:@"%ld", eduId];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              stringEduId,@"eduId",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	//NSString* detail = [[result objectForKey:@" detail"] objectValue];				// 교육 상세 정보 ==> 정보 구조체
		NSString* address = [result objectForKey:@"address"];						// 장소
		NSString* contact = [result objectForKey:@"contact"];						// 문의처
		NSString* endDate = [result objectForKey:@"endDate"];						// 종료일 (yyyy.MM.dd)
		NSString* endTime = [result objectForKey:@"endTime"];						// 종료 시간 (HH:mm)
		NSString* guide = [result objectForKey:@"guide"];						// 오시는 길
		NSString* guideDetail = [result objectForKey:@"guideDetail"];						// 오시는 길 상세 정보
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 교육 아이디
		NSString* latitude = [result objectForKey:@"latitude"];						// 지도 위도 정보
		NSString* longitude = [result objectForKey:@"longitude"];						// 지도 경도 정보
		NSString* mapFile = [result objectForKey:@"mapFile"];						// 약도 파일
		NSString* mapFileName = [result objectForKey:@"mapFileName"];						// 약도 파일 이름
		NSString* organize = [result objectForKey:@"organize"];						// 주관 부서
		NSString* startDate = [result objectForKey:@"startDate"];						// 시작일 (yyyy.MM.dd)
		NSString* startTime = [result objectForKey:@"startTime"];						// 시작 시간 (HH:mm)
		NSString* telephone = [result objectForKey:@"telephone"];						// 연락처 전화번호
		NSString* timeTableFile = [result objectForKey:@"timeTableFile"];						// 시간표 파일 - pdf, png 등록
		NSString* timeTableFileName = [result objectForKey:@"timeTableFileName"];						// 시간표 파일 이름
	//	//NSArray* timeTables = [[result objectForKey:@" timeTables"] allKeys];				// 교육 강의 시간표 ==> 리스트 구조체
			NSInteger days = [[result objectForKey:@"days"] integerValue];				// 강의 날짜
			NSString* endTime = [result objectForKey:@"endTime"];						// 종료 시간 (HH:mm)
			NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 강의 아이디
			NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 시간표 순서
			NSString* room = [result objectForKey:@"room"];						// 강의실
			NSString* startTime = [result objectForKey:@"startTime"];						// 시작 시간 (HH:mm)
			NSString* teacher = [result objectForKey:@"teacher"];						// 강사명
			NSString* title = [result objectForKey:@"title"];						// 강의 제목
			NSString* type = [result objectForKey:@"type"];						// 구분
		NSString* title = [result objectForKey:@"title"];						// 교육명
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 나의 교육 이수 목록을 요청한다.
 * @param year : 검색할 연도
 */
-(void)getGetEduMineWithYear:(NSInteger)year
                     success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                     failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/edu/mine";

	//파라메터 구성
	NSString* stringYear = [NSString stringWithFormat:@"%ld", year];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              stringYear,@"year",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSInteger eduTime = [[result objectForKey:@"eduTime"] integerValue];				// 검색 년도의 교육 이수시간 (분단위)
	//NSArray* educations = [[result objectForKey:@" educations"] allKeys];				// 나의 교육 정보 목록 ==> 리스트 구조체
		NSString* certEndTime = [result objectForKey:@"certEndTime"];						// 종료 인증 시간   (yyyy.MM.dd HH:mm)
		NSString* certStartTime = [result objectForKey:@"certStartTime"];						// 시작 인증 시간  (yyyy.MM.dd HH:mm)
		NSInteger certTime = [[result objectForKey:@"certTime"] integerValue];				// 인정 시간(분)
	//	//NSString* course = [[result objectForKey:@" course"] objectValue];				// 강의 정보 - 교육을 강의 별로 들을 경우 ==> 정보 구조체
			NSInteger days = [[result objectForKey:@"days"] integerValue];				// 강의 날짜
			NSString* endTime = [result objectForKey:@"endTime"];						// 종료 시간 (HH:mm)
			NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 강의 아이디
			NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 시간표 순서
			NSString* room = [result objectForKey:@"room"];						// 강의실
			NSString* startTime = [result objectForKey:@"startTime"];						// 시작 시간 (HH:mm)
			NSString* teacher = [result objectForKey:@"teacher"];						// 강사명
			NSString* title = [result objectForKey:@"title"];						// 강의 제목
			NSString* type = [result objectForKey:@"type"];						// 구분
	//	//NSString* education = [[result objectForKey:@" education"] objectValue];				// 교육 정보 ==> 정보 구조체
			NSString* address = [result objectForKey:@"address"];						// 장소
			NSString* contact = [result objectForKey:@"contact"];						// 문의처
			NSString* endDate = [result objectForKey:@"endDate"];						// 종료일 (yyyy.MM.dd)
			NSString* endTime = [result objectForKey:@"endTime"];						// 종료 시간 (HH:mm)
			NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 교육 아이디
			NSString* organize = [result objectForKey:@"organize"];						// 주관 부서
			NSString* startDate = [result objectForKey:@"startDate"];						// 시작일 (yyyy.MM.dd)
			NSString* startTime = [result objectForKey:@"startTime"];						// 시작 시간 (HH:mm)
			NSString* title = [result objectForKey:@"title"];						// 교육명
		NSString* evalBody = [result objectForKey:@"evalBody"];						// 평가 내용
		NSInteger evalStar = [[result objectForKey:@"evalStar"] integerValue];				// 평가 별점
		NSString* state = [result objectForKey:@"state"];						// 승인대기, 미승인 등 상태값 - Y: 승인, N: 미승인, R: 승인대기
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//NSString* nowEdu = [[result objectForKey:@" nowEdu"] objectValue];				// 현재 진행 중인 교육 정보 ==> 정보 구조체
		NSString* certEndTime = [result objectForKey:@"certEndTime"];						// 종료 인증 시간   (yyyy.MM.dd HH:mm)
		NSString* certStartTime = [result objectForKey:@"certStartTime"];						// 시작 인증 시간  (yyyy.MM.dd HH:mm)
		NSInteger certTime = [[result objectForKey:@"certTime"] integerValue];				// 인정 시간(분)
	//	//NSString* course = [[result objectForKey:@" course"] objectValue];				// 강의 정보 - 교육을 강의 별로 들을 경우 ==> 정보 구조체
			NSInteger days = [[result objectForKey:@"days"] integerValue];				// 강의 날짜
			NSString* endTime = [result objectForKey:@"endTime"];						// 종료 시간 (HH:mm)
			NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 강의 아이디
			NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 시간표 순서
			NSString* room = [result objectForKey:@"room"];						// 강의실
			NSString* startTime = [result objectForKey:@"startTime"];						// 시작 시간 (HH:mm)
			NSString* teacher = [result objectForKey:@"teacher"];						// 강사명
			NSString* title = [result objectForKey:@"title"];						// 강의 제목
			NSString* type = [result objectForKey:@"type"];						// 구분
	//	//NSString* education = [[result objectForKey:@" education"] objectValue];				// 교육 정보 ==> 정보 구조체
			NSString* address = [result objectForKey:@"address"];						// 장소
			NSString* contact = [result objectForKey:@"contact"];						// 문의처
			NSString* endDate = [result objectForKey:@"endDate"];						// 종료일 (yyyy.MM.dd)
			NSString* endTime = [result objectForKey:@"endTime"];						// 종료 시간 (HH:mm)
			NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 교육 아이디
			NSString* organize = [result objectForKey:@"organize"];						// 주관 부서
			NSString* startDate = [result objectForKey:@"startDate"];						// 시작일 (yyyy.MM.dd)
			NSString* startTime = [result objectForKey:@"startTime"];						// 시작 시간 (HH:mm)
			NSString* title = [result objectForKey:@"title"];						// 교육명
		NSString* evalBody = [result objectForKey:@"evalBody"];						// 평가 내용
		NSInteger evalStar = [[result objectForKey:@"evalStar"] integerValue];				// 평가 별점
		NSString* state = [result objectForKey:@"state"];						// 승인대기, 미승인 등 상태값 - Y: 승인, N: 미승인, R: 승인대기
	NSInteger totalTime = [[result objectForKey:@"totalTime"] integerValue];				// 겸색 년도의 교육 미이수 시간 (분단위)
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 출석 참여, 퇴실을 등록한다.
 * @param qrcode : 촬영한 QR 코드 정보
 */
-(void)postPutEduAttendWithQrcode:(NSString *)qrcode
                          success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                          failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/edu/attend";

	//파라메터 구성

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              qrcode,@"qrcode",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//NSString* myEdu = [[result objectForKey:@" myEdu"] objectValue];				// 나의 교육 정보 목록 ==> 정보 구조체
		NSString* certEndTime = [result objectForKey:@"certEndTime"];						// 종료 인증 시간   (yyyy.MM.dd HH:mm)
		NSString* certStartTime = [result objectForKey:@"certStartTime"];						// 시작 인증 시간  (yyyy.MM.dd HH:mm)
		NSInteger certTime = [[result objectForKey:@"certTime"] integerValue];				// 인정 시간(분)
	//	//NSString* course = [[result objectForKey:@" course"] objectValue];				// 강의 정보 - 교육을 강의 별로 들을 경우 ==> 정보 구조체
			NSInteger days = [[result objectForKey:@"days"] integerValue];				// 강의 날짜
			NSString* endTime = [result objectForKey:@"endTime"];						// 종료 시간 (HH:mm)
			NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 강의 아이디
			NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 시간표 순서
			NSString* room = [result objectForKey:@"room"];						// 강의실
			NSString* startTime = [result objectForKey:@"startTime"];						// 시작 시간 (HH:mm)
			NSString* teacher = [result objectForKey:@"teacher"];						// 강사명
			NSString* title = [result objectForKey:@"title"];						// 강의 제목
			NSString* type = [result objectForKey:@"type"];						// 구분
	//	//NSString* education = [[result objectForKey:@" education"] objectValue];				// 교육 정보 ==> 정보 구조체
			NSString* address = [result objectForKey:@"address"];						// 장소
			NSString* contact = [result objectForKey:@"contact"];						// 문의처
			NSString* endDate = [result objectForKey:@"endDate"];						// 종료일 (yyyy.MM.dd)
			NSString* endTime = [result objectForKey:@"endTime"];						// 종료 시간 (HH:mm)
			NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 교육 아이디
			NSString* organize = [result objectForKey:@"organize"];						// 주관 부서
			NSString* startDate = [result objectForKey:@"startDate"];						// 시작일 (yyyy.MM.dd)
			NSString* startTime = [result objectForKey:@"startTime"];						// 시작 시간 (HH:mm)
			NSString* title = [result objectForKey:@"title"];						// 교육명
		NSString* evalBody = [result objectForKey:@"evalBody"];						// 평가 내용
		NSInteger evalStar = [[result objectForKey:@"evalStar"] integerValue];				// 평가 별점
		NSString* state = [result objectForKey:@"state"];						// 승인대기, 미승인 등 상태값 - Y: 승인, N: 미승인, R: 승인대기
	*/

	// POST으로 요청
	[[RequestManager sharedManager] requestPOST:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 교육을 평가, 수정한다.
 * @param eduId : 교육 아이디
 * @param courseId : 강의 아이디 - 나의 교육에서 주는 정보
 * @param star : 교육 평가 별점 (1~5)
 * @param body : 교육 평가 내용
 */
-(void)postEvalEduWithEduId:(NSInteger)eduId
                   courseId:(NSInteger)courseId
                       star:(NSInteger)star
                       body:(NSString *)body
                    success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                    failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/edu/{eduId}/eval";

	//파라메터 구성
	NSString* stringEduId = [NSString stringWithFormat:@"%ld", eduId];
	NSString* stringCourseId = [NSString stringWithFormat:@"%ld", courseId];
	NSString* stringStar = [NSString stringWithFormat:@"%ld", star];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              stringEduId,@"eduId",
                              stringCourseId,@"courseId",
                              stringStar,@"star",
                              body,@"body",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// POST으로 요청
	[[RequestManager sharedManager] requestPOST:URL parameters:param success:success failure:failure];
}


@end
