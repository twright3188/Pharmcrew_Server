//
//	MemberController.m
//	AFNetworking
//
//	 Created by Sungs on 2020. 05. 20.
//	 Copyright © 2020 Sungs. All rights reserved.

#import "MemberController.h"

@implementation MemberController

/**
 * Page=null , 사용자 정보 입력 후 인증을 요청한다.
 * @param licenseNo : 면허번호
 * @param birthDay : 생년월일
 * @param name : 이름
 * @param phone : 휴대전화번호
 */
-(void)postAuthMemberWithLicenseNo:(NSString *)licenseNo
                          birthDay:(NSString *)birthDay
                              name:(NSString *)name
                             phone:(NSString *)phone
                           success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                           failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/member/auth";

	//파라메터 구성

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              licenseNo,@"licenseNo",
                              birthDay,@"birthDay",
                              name,@"name",
                              phone,@"phone",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// POST으로 요청
	[[RequestManager sharedManager] requestPOST:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 인증 문자에 대해 인증을 요청한다.
 * @param authCode : 인증번호
 */
-(void)postAuthPhoneWithAuthCode:(NSString *)authCode
                         success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                         failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/member/auth/phone";

	//파라메터 구성

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              authCode,@"authCode",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// POST으로 요청
	[[RequestManager sharedManager] requestPOST:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 회원 정보 등록
 * @param licenseNo : 면허번호
 * @param authCode : 인증번호
 * @param agreeThird : 개인정보 제 3자 제공동의
 * @param expireDate : 개인정보 유효기간제도 설정
 * @param agreeSms : sms 마케팅 수신 동의
 * @param agreePush : 푸시 마케팅 수신 동의
 * @param password : 패스워드
 */
-(void)postUpdateInfoWithLicenseNo:(NSString *)licenseNo
                          authCode:(NSString *)authCode
                        agreeThird:(NSString *)agreeThird
                        expireDate:(NSString *)expireDate
                          agreeSms:(NSString *)agreeSms
                         agreePush:(NSString *)agreePush
                          password:(NSString *)password
                           success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                           failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/member/info/update";

	//파라메터 구성

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              licenseNo,@"licenseNo",
                              authCode,@"authCode",
                              agreeThird,@"agreeThird",
                              expireDate,@"expireDate",
                              agreeSms,@"agreeSms",
                              agreePush,@"agreePush",
                              password,@"password",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// POST으로 요청
	[[RequestManager sharedManager] requestPOST:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 내 정보 요청한다.
 */
-(void)getGetMemberInfoSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                       failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/member/Info";

	//파라메터 구성
	NSMutableDictionary* param = nil;

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSInteger eduTime = [[result objectForKey:@"eduTime"] integerValue];				// 교육 이수시간 (분단위)
	//NSString* member = [[result objectForKey:@" member"] objectValue];				// 회원 정보 ==> 정보 구조체
		NSString* birth = [result objectForKey:@"birth"];						// 생년월일 (yyyy.MM.dd)
		NSString* email = [result objectForKey:@"email"];						// 이메일
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 사용자 아이디
		NSString* isIngEdu = [result objectForKey:@"isIngEdu"];						// 현재 교육 참여 중인지 여부
		NSString* license = [result objectForKey:@"license"];						// 면허번호
	//	//NSString* myEdu = [[result objectForKey:@" myEdu"] objectValue];				// 최근 참여중이거나 참여완료된 교육 ==> 정보 구조체
			NSString* certEndTime = [result objectForKey:@"certEndTime"];						// 종료 인증 시간   (yyyy.MM.dd HH:mm)
			NSString* certStartTime = [result objectForKey:@"certStartTime"];						// 시작 인증 시간  (yyyy.MM.dd HH:mm)
			NSInteger certTime = [[result objectForKey:@"certTime"] integerValue];				// 인정 시간(분)
	//	//	//NSString* course = [[result objectForKey:@" course"] objectValue];				// 강의 정보 - 교육을 강의 별로 들을 경우 ==> 정보 구조체
				NSInteger days = [[result objectForKey:@"days"] integerValue];				// 강의 날짜
				NSString* endTime = [result objectForKey:@"endTime"];						// 종료 시간 (HH:mm)
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 강의 아이디
				NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 시간표 순서
				NSString* room = [result objectForKey:@"room"];						// 강의실
				NSString* startTime = [result objectForKey:@"startTime"];						// 시작 시간 (HH:mm)
				NSString* teacher = [result objectForKey:@"teacher"];						// 강사명
				NSString* title = [result objectForKey:@"title"];						// 강의 제목
				NSString* type = [result objectForKey:@"type"];						// 구분
	//	//	//NSString* education = [[result objectForKey:@" education"] objectValue];				// 교육 정보 ==> 정보 구조체
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
		NSString* name = [result objectForKey:@"name"];						// 이름
		NSString* org_0 = [result objectForKey:@"org_0"];						// 소속 약사회
		NSString* org_1 = [result objectForKey:@"org_1"];						// 소속 지부
		NSString* org_2 = [result objectForKey:@"org_2"];						// 소속 분회
		NSString* phone = [result objectForKey:@"phone"];						// 전화번호
		NSString* profile = [result objectForKey:@"profile"];						// 프로필 이미지
		NSString* sex = [result objectForKey:@"sex"];						// 성별
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	NSInteger totalTime = [[result objectForKey:@"totalTime"] integerValue];				// 교육 미이수 시간 (분단위)
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=50.0 , 설정 정보를 요청한다.
 * @param os : 요청 OS - A:android, I:iOS
 */
-(void)getGetSettingWithOs:(NSString *)os
                   success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                   failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/member/setting";

	//파라메터 구성

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              os,@"os",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSString* acceptPush = [result objectForKey:@"acceptPush"];						// 푸시 설정 정보 - Y:수신, N:미수신
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* mPush = [result objectForKey:@"mPush"];						// 마케팅 푸시 설정 정보
	NSString* mSms = [result objectForKey:@"mSms"];						// 마케팅 sms 설정 정보
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//NSArray* partners = [[result objectForKey:@" partners"] allKeys];				// 사용자 파트너 설정 정보 ==> 리스트 구조체
		NSString* detail = [result objectForKey:@"detail"];						// 파트너 서비스 부가 정보
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 파트너 아이디
		NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 노출 순서
		NSString* image = [result objectForKey:@"image"];						// 아이콘 이미지 URL
		NSString* isUse = [result objectForKey:@"isUse"];						// 파트너서비스 사용 유무 - Y, N (설정화면에서 설정 가능)
		NSString* link = [result objectForKey:@"link"];						// 클릭시 이동할 연동 링크
		NSString* name = [result objectForKey:@"name"];						// 파트너 서비스 이름
	NSString* version = [result objectForKey:@"version"];						// 현재 앱 사용 버전
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=50.0 , 파트너 서비스를 업데이트 한다.
 * @param partnerId : 설정 On/Off 파트너 서비스 목록
 * @param useYn : on : Y, off : N
 */
-(void)postUpdatePartnersWithPartnerId:(NSInteger)partnerId
                                 useYn:(NSString *)useYn
                               success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                               failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/member/partners";

	//파라메터 구성
	NSString* stringPartnerId = [NSString stringWithFormat:@"%ld", partnerId];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              stringPartnerId,@"partnerId",
                              useYn,@"useYn",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// POST으로 요청
	[[RequestManager sharedManager] requestPOST:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 푸시 설정을 업데이트 한다.
 * @param category : PU : 푸시, MS : 마케팅 SMS, MP : 마케팅 푸시
 * @param agree : 설정 정보 - Y:수신, N:미수신
 */
-(void)postAcceptPushWithCategory:(NSString *)category
                            agree:(NSString *)agree
                          success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                          failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/member/push";

	//파라메터 구성

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              category,@"category",
                              agree,@"agree",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// POST으로 요청
	[[RequestManager sharedManager] requestPOST:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 프로필 이미지를 등록한다.
 * @param image : 이미지 파일
 */
-(void)postUploadProfileWithImage:(NSInteger)image
                          success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                          failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/member/uploadProfile";

	//파라메터 구성
	NSString* stringImage = [NSString stringWithFormat:@"%lf", image];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              stringImage,@"image",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	NSString* profileImgUrl = [result objectForKey:@"profileImgUrl"];						// 변경된 이미지 파일 url
	*/

	// POST으로 요청
	[[RequestManager sharedManager] requestPOST:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 프로필 이미지를 삭제한다
 */
-(void)deleteDeleteProfileSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                          failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/member/deleteProfile";

	//파라메터 구성
	NSMutableDictionary* param = nil;

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// DELETE으로 요청
	[[RequestManager sharedManager] requestDELETE:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 임시비밀번호 발급 요청
 * @param name : 이름
 * @param licenseNo : 면허번호
 */
-(void)postRenewPasswordWithName:(NSString *)name
                       licenseNo:(NSString *)licenseNo
                         success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                         failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/member/password/renew";

	//파라메터 구성

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              name,@"name",
                              licenseNo,@"licenseNo",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	NSString* phone = [result objectForKey:@"phone"];						// 휴대폰번호
	*/

	// POST으로 요청
	[[RequestManager sharedManager] requestPOST:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 비밀번호 변경 요청
 * @param password : 패스워드
 */
-(void)postChangePasswordWithPassword:(NSString *)password
                              success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                              failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/member/password";

	//파라메터 구성

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              password,@"password",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// POST으로 요청
	[[RequestManager sharedManager] requestPOST:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 푸시 수신 시 서버로 수신 여부를 등록한다.
 * @param licenseNo : 사용자의 면허번호 - 로그인 하지 않을 수 있어서
 * @param pushId : 수신한 푸시 아이디
 */
-(void)postReceivePushWithLicenseNo:(NSString *)licenseNo
                             pushId:(NSInteger)pushId
                            success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                            failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/member/push/receive";

	//파라메터 구성
	NSString* stringPushId = [NSString stringWithFormat:@"%ld", pushId];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              licenseNo,@"licenseNo",
                              stringPushId,@"pushId",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// POST으로 요청
	[[RequestManager sharedManager] requestPOST:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 광고알림,약사회알림 목록을 요청한다.
 * @param type : 요청 타입-AD: 광고, NT: 약사회 알림, PT:파트너서비스 알림
 * @param page : 요청할 페이지 : 1 에서 시작
 * @param count : 페이지당 카운트: Default=30개
 */
-(void)getGetNotiListWithType:(NSString *)type
                         page:(NSInteger)page
                        count:(NSInteger)count
                      success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                      failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/member/notifys";

	//파라메터 구성
	NSString* stringPage = [NSString stringWithFormat:@"%ld", page];
	NSString* stringCount = [NSString stringWithFormat:@"%ld", count];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              type,@"type",
                              stringPage,@"page",
                              stringCount,@"count",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//NSArray* notis = [[result objectForKey:@" notis"] allKeys];				// 사용자 알림 목록 ==> 리스트 구조체
		NSString* body = [result objectForKey:@"body"];						// 내용
		NSString* category = [result objectForKey:@"category"];						// 타입
		NSString* date = [result objectForKey:@"date"];						// 등록 일시
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 회원 알림 아이디
		NSInteger moveId = [[result objectForKey:@"moveId"] doubleValue];				// 이동 아이디
		NSString* moveType = [result objectForKey:@"moveType"];						// 이동 타입
		NSString* moveUrl = [result objectForKey:@"moveUrl"];						// 이동 URL
		NSString* title = [result objectForKey:@"title"];						// 제목
	NSInteger totalCount = [[result objectForKey:@"totalCount"] integerValue];				// 전체 알림 숫자
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


@end
