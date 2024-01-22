//
//	NetworkCall.m
//	AFNetworking
//
//	 Created by Sungs on 2020. 05. 20.
//	 Copyright © 2020 Sungs. All rights reserved.

#import "RootController.h"
#import "SessionController.h"
#import "MemberController.h"
#import "NewsController.h"
#import "SurveyController.h"
#import "EducationController.h"
#import "AcademyController.h"
#import "PtaxController.h"
#import "TestController.h"
#import "MagicController.h"

@interface NetworkCall : NSObject

@end

@implementation NetworkCall

/**
 * Page=null , 앱 버전을 체크 한다.
 */
-(void)getGetVersion{

	NSString * os = @"";
	NSString * version = @"";

	RootController* api = [RootController new];
	[api getGetVersionWithOs:os
                  version:version
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//	//	//NSString* version = [[result objectForKey:@" version"] objectValue];				// 버전정보 ==> 정보 구조체
				NSString* desc = [result objectForKey:@"desc"];						// 업데이트 내용 설명
				NSString* forced = [result objectForKey:@"forced"];						// 강제 여부 (Y:강제, N:일반, X: 업데이트 없음)
				NSString* link = [result objectForKey:@"link"];						// 다운로드 링크
				NSString* version = [result objectForKey:@"version"];						// 버전네임 (1.0.1)
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 푸시 토큰을 등록한다.
 */
-(void)postTokenUpdate{

	NSString * os = @"";
	NSString * pushToken = @"";

	RootController* api = [RootController new];
	[api postTokenUpdateWithOs:os
                  pushToken:pushToken
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 메인 팝업/배너, 한줄 공지, 파트너 서비스 정보를 요청한다.
 */
-(void)getGetMain{


	RootController* api = [RootController new];
	[api getGetMainSuccess:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
	//	//	//NSArray* banners = [[result objectForKey:@" banners"] allKeys];				// 배너 광고 목록 ==> 리스트 구조체
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 배너 아이디
				NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 노출 순서
				NSString* image = [result objectForKey:@"image"];						// 이미지 URL
				NSInteger moveId = [[result objectForKey:@"moveId"] doubleValue];				// 이동 아이디
				NSString* moveType = [result objectForKey:@"moveType"];						// 이동 타입
				NSString* moveUrl = [result objectForKey:@"moveUrl"];						// 이동 URL
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//	//	//NSArray* notices = [[result objectForKey:@" notices"] allKeys];				// 한줄 공지 목록 ==> 리스트 구조체
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 한줄 공지 아이디 - 소식방 아이디
				NSString* title = [result objectForKey:@"title"];						// 노출할 텍스트 정보 - 소식방 타이틀
	//	//	//NSArray* partners = [[result objectForKey:@" partners"] allKeys];				// 파트너서비스 목록 ==> 리스트 구조체
				NSString* detail = [result objectForKey:@"detail"];						// 파트너 서비스 부가 정보
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 파트너 아이디
				NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 노출 순서
				NSString* image = [result objectForKey:@"image"];						// 아이콘 이미지 URL
				NSString* isUse = [result objectForKey:@"isUse"];						// 파트너서비스 사용 유무 - Y, N (설정화면에서 설정 가능)
				NSString* link = [result objectForKey:@"link"];						// 클릭시 이동할 연동 링크
				NSString* name = [result objectForKey:@"name"];						// 파트너 서비스 이름
	//	//	//NSArray* popups = [[result objectForKey:@" popups"] allKeys];				// 팝업 광고 목록 ==> 리스트 구조체
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 배너 아이디
				NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 노출 순서
				NSString* image = [result objectForKey:@"image"];						// 이미지 URL
				NSInteger moveId = [[result objectForKey:@"moveId"] doubleValue];				// 이동 아이디
				NSString* moveType = [result objectForKey:@"moveType"];						// 이동 타입
				NSString* moveUrl = [result objectForKey:@"moveUrl"];						// 이동 URL
			NSInteger unReadNotiCount = [[result objectForKey:@"unReadNotiCount"] integerValue];				// 읽지 않은 알림 숫자
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 메인 팝업 정보를 요청한다.
 */
-(void)getGetPopups{


	RootController* api = [RootController new];
	[api getGetPopupsSuccess:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//	//	//NSArray* popups = [[result objectForKey:@" popups"] allKeys];				// null ==> 리스트 구조체
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 배너 아이디
				NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 노출 순서
				NSString* image = [result objectForKey:@"image"];						// 이미지 URL
				NSInteger moveId = [[result objectForKey:@"moveId"] doubleValue];				// 이동 아이디
				NSString* moveType = [result objectForKey:@"moveType"];						// 이동 타입
				NSString* moveUrl = [result objectForKey:@"moveUrl"];						// 이동 URL
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 메인 배너 정보를 요청한다.
 */
-(void)getGetBanners{


	RootController* api = [RootController new];
	[api getGetBannersSuccess:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
	//	//	//NSArray* banners = [[result objectForKey:@" banners"] allKeys];				// null ==> 리스트 구조체
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 배너 아이디
				NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 노출 순서
				NSString* image = [result objectForKey:@"image"];						// 이미지 URL
				NSInteger moveId = [[result objectForKey:@"moveId"] doubleValue];				// 이동 아이디
				NSString* moveType = [result objectForKey:@"moveType"];						// 이동 타입
				NSString* moveUrl = [result objectForKey:@"moveUrl"];						// 이동 URL
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 메인 파트너 서비스 정보를 요청한다.
 */
-(void)getGetPartners{


	RootController* api = [RootController new];
	[api getGetPartnersSuccess:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//	//	//NSArray* partners = [[result objectForKey:@" partners"] allKeys];				// null ==> 리스트 구조체
				NSString* detail = [result objectForKey:@"detail"];						// 파트너 서비스 부가 정보
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 파트너 아이디
				NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 노출 순서
				NSString* image = [result objectForKey:@"image"];						// 아이콘 이미지 URL
				NSString* isUse = [result objectForKey:@"isUse"];						// 파트너서비스 사용 유무 - Y, N (설정화면에서 설정 가능)
				NSString* link = [result objectForKey:@"link"];						// 클릭시 이동할 연동 링크
				NSString* name = [result objectForKey:@"name"];						// 파트너 서비스 이름
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 메인 한줄 공지 정보를 요청한다.
 */
-(void)getGetNotices{


	RootController* api = [RootController new];
	[api getGetNoticesSuccess:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//	//	//NSArray* notices = [[result objectForKey:@" notices"] allKeys];				// null ==> 리스트 구조체
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 한줄 공지 아이디 - 소식방 아이디
				NSString* title = [result objectForKey:@"title"];						// 노출할 텍스트 정보 - 소식방 타이틀
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 읽지않은 알림 숫자를 요청한다.
 */
-(void)getGetUnReads{


	RootController* api = [RootController new];
	[api getGetUnReadsSuccess:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			NSInteger unReadNotiCount = [[result objectForKey:@"unReadNotiCount"] integerValue];				// 읽지 않은 알림 숫자
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 로그인을 수행하여 세션을 획득한다.
 */
-(void)postSession{

	NSString * licenseNo = @"";
	NSString * password = @"";

	SessionController* api = [SessionController new];
	[api postSessionWithLicenseNo:licenseNo
                      password:password
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSInteger eduStartTime = [[result objectForKey:@"eduStartTime"] doubleValue];				// 교육 시작 시간 - 교육 진행 중인 경우
	//	//	//NSString* member = [[result objectForKey:@" member"] objectValue];				// 회원 정보 ==> 정보 구조체
				NSString* birth = [result objectForKey:@"birth"];						// 생년월일 (yyyy.MM.dd)
				NSString* email = [result objectForKey:@"email"];						// 이메일
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 사용자 아이디
				NSString* isIngEdu = [result objectForKey:@"isIngEdu"];						// 현재 교육 참여 중인지 여부
				NSString* license = [result objectForKey:@"license"];						// 면허번호
	//	//	//	//NSString* myEdu = [[result objectForKey:@" myEdu"] objectValue];				// 최근 참여중이거나 참여완료된 교육 ==> 정보 구조체
					NSString* certEndTime = [result objectForKey:@"certEndTime"];						// 종료 인증 시간   (yyyy.MM.dd HH:mm)
					NSString* certStartTime = [result objectForKey:@"certStartTime"];						// 시작 인증 시간  (yyyy.MM.dd HH:mm)
					NSInteger certTime = [[result objectForKey:@"certTime"] integerValue];				// 인정 시간(분)
	//	//	//	//	//NSString* course = [[result objectForKey:@" course"] objectValue];				// 강의 정보 - 교육을 강의 별로 들을 경우 ==> 정보 구조체
						NSInteger days = [[result objectForKey:@"days"] integerValue];				// 강의 날짜
						NSString* endTime = [result objectForKey:@"endTime"];						// 종료 시간 (HH:mm)
						NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 강의 아이디
						NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 시간표 순서
						NSString* room = [result objectForKey:@"room"];						// 강의실
						NSString* startTime = [result objectForKey:@"startTime"];						// 시작 시간 (HH:mm)
						NSString* teacher = [result objectForKey:@"teacher"];						// 강사명
						NSString* title = [result objectForKey:@"title"];						// 강의 제목
						NSString* type = [result objectForKey:@"type"];						// 구분
	//	//	//	//	//NSString* education = [[result objectForKey:@" education"] objectValue];				// 교육 정보 ==> 정보 구조체
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
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 로그아웃을 수행하여 세션을 만료시킨다.
 */
-(void)deleteSession{


	SessionController* api = [SessionController new];
	[api deleteSessionSuccess:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 사용자 정보 입력 후 인증을 요청한다.
 */
-(void)postAuthMember{

	NSString * licenseNo = @"";
	NSString * birthDay = @"";
	NSString * name = @"";
	NSString * phone = @"";

	MemberController* api = [MemberController new];
	[api postAuthMemberWithLicenseNo:licenseNo
                         birthDay:birthDay
                             name:name
                            phone:phone
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 인증 문자에 대해 인증을 요청한다.
 */
-(void)postAuthPhone{

	NSString * authCode = @"";

	MemberController* api = [MemberController new];
	[api postAuthPhoneWithAuthCode:authCode
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 회원 정보 등록
 */
-(void)postUpdateInfo{

	NSString * licenseNo = @"";
	NSString * authCode = @"";
	NSString * agreeThird = @"";
	NSString * expireDate = @"";
	NSString * agreeSms = @"";
	NSString * agreePush = @"";
	NSString * password = @"";

	MemberController* api = [MemberController new];
	[api postUpdateInfoWithLicenseNo:licenseNo
                         authCode:authCode
                       agreeThird:agreeThird
                       expireDate:expireDate
                         agreeSms:agreeSms
                        agreePush:agreePush
                         password:password
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 내 정보 요청한다.
 */
-(void)getGetMemberInfo{


	MemberController* api = [MemberController new];
	[api getGetMemberInfoSuccess:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSInteger eduTime = [[result objectForKey:@"eduTime"] integerValue];				// 교육 이수시간 (분단위)
	//	//	//NSString* member = [[result objectForKey:@" member"] objectValue];				// 회원 정보 ==> 정보 구조체
				NSString* birth = [result objectForKey:@"birth"];						// 생년월일 (yyyy.MM.dd)
				NSString* email = [result objectForKey:@"email"];						// 이메일
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 사용자 아이디
				NSString* isIngEdu = [result objectForKey:@"isIngEdu"];						// 현재 교육 참여 중인지 여부
				NSString* license = [result objectForKey:@"license"];						// 면허번호
	//	//	//	//NSString* myEdu = [[result objectForKey:@" myEdu"] objectValue];				// 최근 참여중이거나 참여완료된 교육 ==> 정보 구조체
					NSString* certEndTime = [result objectForKey:@"certEndTime"];						// 종료 인증 시간   (yyyy.MM.dd HH:mm)
					NSString* certStartTime = [result objectForKey:@"certStartTime"];						// 시작 인증 시간  (yyyy.MM.dd HH:mm)
					NSInteger certTime = [[result objectForKey:@"certTime"] integerValue];				// 인정 시간(분)
	//	//	//	//	//NSString* course = [[result objectForKey:@" course"] objectValue];				// 강의 정보 - 교육을 강의 별로 들을 경우 ==> 정보 구조체
						NSInteger days = [[result objectForKey:@"days"] integerValue];				// 강의 날짜
						NSString* endTime = [result objectForKey:@"endTime"];						// 종료 시간 (HH:mm)
						NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 강의 아이디
						NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 시간표 순서
						NSString* room = [result objectForKey:@"room"];						// 강의실
						NSString* startTime = [result objectForKey:@"startTime"];						// 시작 시간 (HH:mm)
						NSString* teacher = [result objectForKey:@"teacher"];						// 강사명
						NSString* title = [result objectForKey:@"title"];						// 강의 제목
						NSString* type = [result objectForKey:@"type"];						// 구분
	//	//	//	//	//NSString* education = [[result objectForKey:@" education"] objectValue];				// 교육 정보 ==> 정보 구조체
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

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=50.0 , 설정 정보를 요청한다.
 */
-(void)getGetSetting{

	NSString * os = @"";

	MemberController* api = [MemberController new];
	[api getGetSettingWithOs:os
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSString* acceptPush = [result objectForKey:@"acceptPush"];						// 푸시 설정 정보 - Y:수신, N:미수신
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* mPush = [result objectForKey:@"mPush"];						// 마케팅 푸시 설정 정보
			NSString* mSms = [result objectForKey:@"mSms"];						// 마케팅 sms 설정 정보
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//	//	//NSArray* partners = [[result objectForKey:@" partners"] allKeys];				// 사용자 파트너 설정 정보 ==> 리스트 구조체
				NSString* detail = [result objectForKey:@"detail"];						// 파트너 서비스 부가 정보
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 파트너 아이디
				NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 노출 순서
				NSString* image = [result objectForKey:@"image"];						// 아이콘 이미지 URL
				NSString* isUse = [result objectForKey:@"isUse"];						// 파트너서비스 사용 유무 - Y, N (설정화면에서 설정 가능)
				NSString* link = [result objectForKey:@"link"];						// 클릭시 이동할 연동 링크
				NSString* name = [result objectForKey:@"name"];						// 파트너 서비스 이름
			NSString* version = [result objectForKey:@"version"];						// 현재 앱 사용 버전
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=50.0 , 파트너 서비스를 업데이트 한다.
 */
-(void)postUpdatePartners{

	NSInteger partnerId = 1;
	NSString * useYn = @"";

	MemberController* api = [MemberController new];
	[api postUpdatePartnersWithPartnerId:partnerId
                                useYn:useYn
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 푸시 설정을 업데이트 한다.
 */
-(void)postAcceptPush{

	NSString * category = @"";
	NSString * agree = @"";

	MemberController* api = [MemberController new];
	[api postAcceptPushWithCategory:category
                           agree:agree
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 프로필 이미지를 등록한다.
 */
-(void)postUploadProfile{

	NSString* image = @"";

	MemberController* api = [MemberController new];
	[api postUploadProfileWithImage:image
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			NSString* profileImgUrl = [result objectForKey:@"profileImgUrl"];						// 변경된 이미지 파일 url
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 프로필 이미지를 삭제한다
 */
-(void)deleteDeleteProfile{


	MemberController* api = [MemberController new];
	[api deleteDeleteProfileSuccess:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 임시비밀번호 발급 요청
 */
-(void)postRenewPassword{

	NSString * name = @"";
	NSString * licenseNo = @"";

	MemberController* api = [MemberController new];
	[api postRenewPasswordWithName:name
                      licenseNo:licenseNo
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			NSString* phone = [result objectForKey:@"phone"];						// 휴대폰번호
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 비밀번호 변경 요청
 */
-(void)postChangePassword{

	NSString * password = @"";

	MemberController* api = [MemberController new];
	[api postChangePasswordWithPassword:password
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 푸시 수신 시 서버로 수신 여부를 등록한다.
 */
-(void)postReceivePush{

	NSString * licenseNo = @"";
	NSInteger pushId = 1;

	MemberController* api = [MemberController new];
	[api postReceivePushWithLicenseNo:licenseNo
                            pushId:pushId
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 광고알림,약사회알림 목록을 요청한다.
 */
-(void)getGetNotiList{

	NSString * type = @"";
	NSInteger page = 1;
	NSInteger count = 1;

	MemberController* api = [MemberController new];
	[api getGetNotiListWithType:type
                        page:page
                       count:count
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//	//	//NSArray* notis = [[result objectForKey:@" notis"] allKeys];				// 사용자 알림 목록 ==> 리스트 구조체
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

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 소식방 목록을 요청한다.
 */
-(void)getGetNewsList{

	NSInteger page = 1;
	NSInteger count = 1;

	NewsController* api = [NewsController new];
	[api getGetNewsListWithPage:page
                       count:count
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//	//	//NSArray* news = [[result objectForKey:@" news"] allKeys];				// 소식방 목록 ==> 리스트 구조체
				NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 소식 아이디
				NSString* isTop = [result objectForKey:@"isTop"];						// 상단고정 여부
				NSString* title = [result objectForKey:@"title"];						// 소식 타이틀
				NSString* type = [result objectForKey:@"type"];						// 공지 타입(일반약사회,지부공지,분회공지)
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 소식방 상세 정보를 요청한다.
 */
-(void)getGetNewsDetail{

	NSInteger newsId = 1;

	NewsController* api = [NewsController new];
	[api getGetNewsDetailWithNewsId:newsId
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	//	//	//NSString* detail = [[result objectForKey:@" detail"] objectValue];				// 소식방 상세 ==> 정보 구조체
	//	//	//	//NSArray* attach = [[result objectForKey:@" attach"] allKeys];				// 첨부파일 목록 ==> 리스트 구조체
					NSString* link = [result objectForKey:@"link"];						// 첨부 파일 다운로드 링크
					NSString* name = [result objectForKey:@"name"];						// 첨부 파일 이름
				NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
				NSString* detail = [result objectForKey:@"detail"];						// 상세 호출 URL
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 소식 아이디
				NSString* isTop = [result objectForKey:@"isTop"];						// 상단고정 여부
				NSString* title = [result objectForKey:@"title"];						// 소식 타이틀
				NSString* type = [result objectForKey:@"type"];						// 공지 타입(일반약사회,지부공지,분회공지)
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 소식방의 문의하기를 등록한다.
 */
-(void)postUpdateNewsQna{

	NSInteger id = 1;
	NSString * category = @"";
	NSString * title = @"";
	NSString * body = @"";
	NSString* file = @"";

	NewsController* api = [NewsController new];
	[api postUpdateNewsQnaWithId:id
                     category:category
                        title:title
                         body:body
                         file:file
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 나의 문의 목록을 요청한다.
 */
-(void)getGetMyQna{

	NSInteger page = 1;
	NSInteger count = 1;

	NewsController* api = [NewsController new];
	[api getGetMyQnaWithPage:page
                    count:count
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//	//	//NSArray* qnas = [[result objectForKey:@" qnas"] allKeys];				// 문의 목록 ==> 리스트 구조체
				NSString* category = [result objectForKey:@"category"];						// 질문 등록 카테고리 - E: 교육, N : 소식
				NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 질문 아이디
				NSString* state = [result objectForKey:@"state"];						// 답변 상태 - '답변대기', '답변완료'
				NSInteger target = [[result objectForKey:@"target"] doubleValue];				// 교육이나 소식 타겟 아이디 -> 향후 교육이나 소식으로 이동 시 필요할까 해서 추가해 봄
				NSString* title = [result objectForKey:@"title"];						// 질문 타이틀
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 나의 문의 상세 정보 요청
 */
-(void)getGetMyQnaDetail{

	NSInteger qnaId = 1;

	NewsController* api = [NewsController new];
	[api getGetMyQnaDetailWithQnaId:qnaId
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	//	//	//NSString* detail = [[result objectForKey:@" detail"] objectValue];				// 문의 답변 상세 ==> 정보 구조체
				NSString* attachName = [result objectForKey:@"attachName"];						// 첨부파일 이름
				NSString* attachPath = [result objectForKey:@"attachPath"];						// 첨부파일 다운로드 경로
				NSString* category = [result objectForKey:@"category"];						// 질문 등록 카테고리 - E: 교육, N : 소식
				NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 질문 아이디
				NSString* qBody = [result objectForKey:@"qBody"];						// 질문 상세
				NSString* rBody = [result objectForKey:@"rBody"];						// 답변 상세 내용
				NSString* rDate = [result objectForKey:@"rDate"];						// 답변 등록일  (yyyy.MM.dd)
				NSString* rTitle = [result objectForKey:@"rTitle"];						// 답변 타이틀
				NSString* state = [result objectForKey:@"state"];						// 답변 상태 - '답변대기', '답변완료'
				NSInteger target = [[result objectForKey:@"target"] doubleValue];				// 교육이나 소식 타겟 아이디 -> 향후 교육이나 소식으로 이동 시 필요할까 해서 추가해 봄
				NSString* title = [result objectForKey:@"title"];						// 질문 타이틀
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 설문 목록을 요청한다.
 */
-(void)getGetSurveyList{

	NSInteger page = 1;
	NSInteger count = 1;

	SurveyController* api = [SurveyController new];
	[api getGetSurveyListWithPage:page
                         count:count
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//	//	//NSArray* surveys = [[result objectForKey:@" surveys"] allKeys];				// 설문 목록 ==> 리스트 구조체
				NSString* detail = [result objectForKey:@"detail"];						// 설문 설명
				NSString* endDate = [result objectForKey:@"endDate"];						// 설문 종료일  (yyyy.MM.dd)
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 설문 아이디
				NSString* isComplete = [result objectForKey:@"isComplete"];						// 설문 참여 여부 - Y:설문 참여, N:설문 미참여
				NSString* showResult = [result objectForKey:@"showResult"];						// 답변 노출 여부 - Y:결과 노출, N:결과 미노출
				NSString* startDate = [result objectForKey:@"startDate"];						// 설문 시작일  (yyyy.MM.dd)
				NSString* title = [result objectForKey:@"title"];						// 제목
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 각 설문의 문항 목록을 요청한다.
 */
-(void)getGetQuestions{

	NSInteger surveyId = 1;

	SurveyController* api = [SurveyController new];
	[api getGetQuestionsWithSurveyId:surveyId
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//	//	//NSArray* questions = [[result objectForKey:@" questions"] allKeys];				// 설문 문항 목록 ==> 리스트 구조체
				NSString* answer = [result objectForKey:@"answer"];						// 내가 참여한 정답 정보
	//	//	//	//NSArray* examples = [[result objectForKey:@" examples"] allKeys];				// 객관식 답변 문항 ==> 리스트 구조체
					NSString* answer = [result objectForKey:@"answer"];						// 답변 예시
					NSInteger no = [[result objectForKey:@"no"] integerValue];				// 답변 문항 번호
					NSInteger result = [[result objectForKey:@"result"] integerValue];				// 질문 답변 비율 - 결과 노출 시 결과를 보여주는 것 ( 완료 한 사람에게 보여줄 정보)=>p39
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 설문 아이디
				NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 문항 순서
				NSString* question = [result objectForKey:@"question"];						// 질문 내용
				NSInteger totalCount = [[result objectForKey:@"totalCount"] integerValue];				// 참여한 전체 사용자 수
				NSString* type = [result objectForKey:@"type"];						// S(ubjective)주관식, O(bjective)객관식
	//	//	//NSString* survey = [[result objectForKey:@" survey"] objectValue];				// 설문정보 ==> 정보 구조체
				NSString* detail = [result objectForKey:@"detail"];						// 설문 설명
				NSString* endDate = [result objectForKey:@"endDate"];						// 설문 종료일  (yyyy.MM.dd)
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 설문 아이디
				NSString* isComplete = [result objectForKey:@"isComplete"];						// 설문 참여 여부 - Y:설문 참여, N:설문 미참여
				NSString* showResult = [result objectForKey:@"showResult"];						// 답변 노출 여부 - Y:결과 노출, N:결과 미노출
				NSString* startDate = [result objectForKey:@"startDate"];						// 설문 시작일  (yyyy.MM.dd)
				NSString* title = [result objectForKey:@"title"];						// 제목
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 설문 응답을 등록한다.
 */
-(void)postPutSurvey{

	NSInteger surveyId = 1;
	NSInteger questNo = 1;
	NSString * answer = @"";

	SurveyController* api = [SurveyController new];
	[api postPutSurveyWithSurveyId:surveyId
                        questNo:questNo
                         answer:answer
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 교육 목록을 요청한다.
 */
-(void)getGetEduList{

	NSInteger page = 1;
	NSInteger count = 1;

	EducationController* api = [EducationController new];
	[api getGetEduListWithPage:page
                      count:count
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	//	//	//NSArray* educations = [[result objectForKey:@" educations"] allKeys];				// 교육 목록 정보 ==> 리스트 구조체
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

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 교육 상세 정보를 요청한다.
 */
-(void)getGetEduDetail{

	NSInteger eduId = 1;

	EducationController* api = [EducationController new];
	[api getGetEduDetailWithEduId:eduId
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	//	//	//NSString* detail = [[result objectForKey:@" detail"] objectValue];				// 교육 상세 정보 ==> 정보 구조체
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
	//	//	//	//NSArray* timeTables = [[result objectForKey:@" timeTables"] allKeys];				// 교육 강의 시간표 ==> 리스트 구조체
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

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 나의 교육 이수 목록을 요청한다.
 */
-(void)getGetEduMine{

	NSInteger year = 1;

	EducationController* api = [EducationController new];
	[api getGetEduMineWithYear:year
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSInteger eduTime = [[result objectForKey:@"eduTime"] integerValue];				// 검색 년도의 교육 이수시간 (분단위)
	//	//	//NSArray* educations = [[result objectForKey:@" educations"] allKeys];				// 나의 교육 정보 목록 ==> 리스트 구조체
				NSString* certEndTime = [result objectForKey:@"certEndTime"];						// 종료 인증 시간   (yyyy.MM.dd HH:mm)
				NSString* certStartTime = [result objectForKey:@"certStartTime"];						// 시작 인증 시간  (yyyy.MM.dd HH:mm)
				NSInteger certTime = [[result objectForKey:@"certTime"] integerValue];				// 인정 시간(분)
	//	//	//	//NSString* course = [[result objectForKey:@" course"] objectValue];				// 강의 정보 - 교육을 강의 별로 들을 경우 ==> 정보 구조체
					NSInteger days = [[result objectForKey:@"days"] integerValue];				// 강의 날짜
					NSString* endTime = [result objectForKey:@"endTime"];						// 종료 시간 (HH:mm)
					NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 강의 아이디
					NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 시간표 순서
					NSString* room = [result objectForKey:@"room"];						// 강의실
					NSString* startTime = [result objectForKey:@"startTime"];						// 시작 시간 (HH:mm)
					NSString* teacher = [result objectForKey:@"teacher"];						// 강사명
					NSString* title = [result objectForKey:@"title"];						// 강의 제목
					NSString* type = [result objectForKey:@"type"];						// 구분
	//	//	//	//NSString* education = [[result objectForKey:@" education"] objectValue];				// 교육 정보 ==> 정보 구조체
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
	//	//	//NSString* nowEdu = [[result objectForKey:@" nowEdu"] objectValue];				// 현재 진행 중인 교육 정보 ==> 정보 구조체
				NSString* certEndTime = [result objectForKey:@"certEndTime"];						// 종료 인증 시간   (yyyy.MM.dd HH:mm)
				NSString* certStartTime = [result objectForKey:@"certStartTime"];						// 시작 인증 시간  (yyyy.MM.dd HH:mm)
				NSInteger certTime = [[result objectForKey:@"certTime"] integerValue];				// 인정 시간(분)
	//	//	//	//NSString* course = [[result objectForKey:@" course"] objectValue];				// 강의 정보 - 교육을 강의 별로 들을 경우 ==> 정보 구조체
					NSInteger days = [[result objectForKey:@"days"] integerValue];				// 강의 날짜
					NSString* endTime = [result objectForKey:@"endTime"];						// 종료 시간 (HH:mm)
					NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 강의 아이디
					NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 시간표 순서
					NSString* room = [result objectForKey:@"room"];						// 강의실
					NSString* startTime = [result objectForKey:@"startTime"];						// 시작 시간 (HH:mm)
					NSString* teacher = [result objectForKey:@"teacher"];						// 강사명
					NSString* title = [result objectForKey:@"title"];						// 강의 제목
					NSString* type = [result objectForKey:@"type"];						// 구분
	//	//	//	//NSString* education = [[result objectForKey:@" education"] objectValue];				// 교육 정보 ==> 정보 구조체
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

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 출석 참여, 퇴실을 등록한다.
 */
-(void)postPutEduAttend{

	NSString * qrcode = @"";

	EducationController* api = [EducationController new];
	[api postPutEduAttendWithQrcode:qrcode
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//	//	//NSString* myEdu = [[result objectForKey:@" myEdu"] objectValue];				// 나의 교육 정보 목록 ==> 정보 구조체
				NSString* certEndTime = [result objectForKey:@"certEndTime"];						// 종료 인증 시간   (yyyy.MM.dd HH:mm)
				NSString* certStartTime = [result objectForKey:@"certStartTime"];						// 시작 인증 시간  (yyyy.MM.dd HH:mm)
				NSInteger certTime = [[result objectForKey:@"certTime"] integerValue];				// 인정 시간(분)
	//	//	//	//NSString* course = [[result objectForKey:@" course"] objectValue];				// 강의 정보 - 교육을 강의 별로 들을 경우 ==> 정보 구조체
					NSInteger days = [[result objectForKey:@"days"] integerValue];				// 강의 날짜
					NSString* endTime = [result objectForKey:@"endTime"];						// 종료 시간 (HH:mm)
					NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 강의 아이디
					NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 시간표 순서
					NSString* room = [result objectForKey:@"room"];						// 강의실
					NSString* startTime = [result objectForKey:@"startTime"];						// 시작 시간 (HH:mm)
					NSString* teacher = [result objectForKey:@"teacher"];						// 강사명
					NSString* title = [result objectForKey:@"title"];						// 강의 제목
					NSString* type = [result objectForKey:@"type"];						// 구분
	//	//	//	//NSString* education = [[result objectForKey:@" education"] objectValue];				// 교육 정보 ==> 정보 구조체
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

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 교육을 평가, 수정한다.
 */
-(void)postEvalEdu{

	NSInteger eduId = 1;
	NSInteger courseId = 1;
	NSInteger star = 1;
	NSString * body = @"";

	EducationController* api = [EducationController new];
	[api postEvalEduWithEduId:eduId
                  courseId:courseId
                      star:star
                      body:body
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=79.0 , 학술정보 PDF 목록 조회한다. : 0.8
 */
-(void)getDocList{

	NSString * searchKey = @"";
	NSInteger page = 1;
	NSInteger count = 1;

	AcademyController* api = [AcademyController new];
	[api getDocListWithSearchKey:searchKey
                         page:page
                        count:count
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	//	//	//NSArray* docs = [[result objectForKey:@" docs"] allKeys];				// 학술정보 PDF 목록 ==> 리스트 구조체
				NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 문서 아이디
				NSString* link = [result objectForKey:@"link"];						// 문서 다운로드 경로
				NSString* title = [result objectForKey:@"title"];						// 문서 타이틀
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=80.0 , 학술정보 PDF 상세 조회 : 0.8
 */
-(void)getDocDetail{

	NSInteger id = 1;

	AcademyController* api = [AcademyController new];
	[api getDocDetailWithId:id
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	//	//	//NSString* doc = [[result objectForKey:@" doc"] objectValue];				// 학술 정보 PDF 정보 ==> 정보 구조체
				NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 문서 아이디
				NSString* link = [result objectForKey:@"link"];						// 문서 다운로드 경로
				NSString* title = [result objectForKey:@"title"];						// 문서 타이틀
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=81.0 , 학술정보 동영상 목록 조회 : 0.8
 */
-(void)getVideoList{

	NSString * searchKey = @"";
	NSInteger page = 1;
	NSInteger count = 1;

	AcademyController* api = [AcademyController new];
	[api getVideoListWithSearchKey:searchKey
                           page:page
                          count:count
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//	//	//NSArray* videos = [[result objectForKey:@" videos"] allKeys];				// 학술정보 동영상 목록 ==> 리스트 구조체
				NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 동영상 아이디
				NSString* title = [result objectForKey:@"title"];						// 문서 타이틀
				NSString* youtube = [result objectForKey:@"youtube"];						// Youtube 동영상 아이디
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=81.0 , 학술정보 동영상 상세 조회 : 0.8
 */
-(void)getVideoDetail{

	NSInteger id = 1;

	AcademyController* api = [AcademyController new];
	[api getVideoDetailWithId:id
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//	//	//NSString* video = [[result objectForKey:@" video"] objectValue];				// 학술 정보 동영상 정보 ==> 정보 구조체
				NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 동영상 아이디
				NSString* title = [result objectForKey:@"title"];						// 문서 타이틀
				NSString* youtube = [result objectForKey:@"youtube"];						// Youtube 동영상 아이디
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=26.0 , 팜택스 고객 1:1 문의 목록을 조회한다. : v0.8
 */
-(void)getQnaList{

	NSInteger page = 1;
	NSInteger count = 1;

	PtaxController* api = [PtaxController new];
	[api getQnaListWithPage:page
                   count:count
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//	//	//NSArray* qnas = [[result objectForKey:@" qnas"] allKeys];				// 팜택스 1:1 문의 목록 ==> 리스트 구조체
				NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
				NSString* existAttach = [result objectForKey:@"existAttach"];						// 첨부파일 유무 Y, N
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 질문 아이디
				NSString* state = [result objectForKey:@"state"];						// 답변 상태 - '답변대기', '답변완료'
				NSString* title = [result objectForKey:@"title"];						// 질문 타이틀
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=27.0 , 팜택스 1:1 문의 상세 조회 : v0.8
 */
-(void)getQnaDetal{

	NSInteger qnaId = 1;

	PtaxController* api = [PtaxController new];
	[api getQnaDetalWithQnaId:qnaId
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	//	//	//NSString* detail = [[result objectForKey:@" detail"] objectValue];				// 팜택스 1:1 문의 답변 상세 ==> 정보 구조체
				NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
				NSString* existAttach = [result objectForKey:@"existAttach"];						// 첨부파일 유무 Y, N
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 질문 아이디
	//	//	//	//NSArray* qAttach = [[result objectForKey:@" qAttach"] allKeys];				// 질문 첨부파일 목록 ==> 리스트 구조체
					NSString* link = [result objectForKey:@"link"];						// 첨부 파일 다운로드 링크
					NSString* name = [result objectForKey:@"name"];						// 첨부 파일 이름
				NSString* qBody = [result objectForKey:@"qBody"];						// 질문 상세
	//	//	//	//NSArray* rAttach = [[result objectForKey:@" rAttach"] allKeys];				// 답변 첨부파일 목록 ==> 리스트 구조체
					NSString* link = [result objectForKey:@"link"];						// 첨부 파일 다운로드 링크
					NSString* name = [result objectForKey:@"name"];						// 첨부 파일 이름
				NSString* rBody = [result objectForKey:@"rBody"];						// 답변 상세 내용
				NSString* rDate = [result objectForKey:@"rDate"];						// 답변 등록일  (yyyy.MM.dd)
				NSString* rTitle = [result objectForKey:@"rTitle"];						// 답변 타이틀
				NSString* state = [result objectForKey:@"state"];						// 답변 상태 - '답변대기', '답변완료'
				NSString* title = [result objectForKey:@"title"];						// 질문 타이틀
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=29.0 , 팜택스 1:1 문의하기를 등록한다. : v0.8
 */
-(void)postUpdateQna{

	NSString * title = @"";
	NSString * body = @"";
	NSString* file1 = @"";
	NSString* file2 = @"";
	NSString* file3 = @"";

	PtaxController* api = [PtaxController new];
	[api postUpdateQnaWithTitle:title
                        body:body
                       file1:file1
                       file2:file2
                       file3:file3
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=30.0 , 팜택스 공지 목록 조회 : v0.8
 */
-(void)getNewsList{

	NSInteger page = 1;
	NSInteger count = 1;

	PtaxController* api = [PtaxController new];
	[api getNewsListWithPage:page
                    count:count
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//	//	//NSArray* news = [[result objectForKey:@" news"] allKeys];				// 팜택스 공지 목록 ==> 리스트 구조체
				NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
				NSString* existAttach = [result objectForKey:@"existAttach"];						// 첨부파일 여부 ( Y, N )
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 소식 아이디
				NSString* title = [result objectForKey:@"title"];						// 소식 타이틀
				NSString* type = [result objectForKey:@"type"];						// 공지 타입(A:전체알림, M:나의 알림)
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=31.0 , 팜택스 공지 상세 : v0.8
 */
-(void)getNewsDetail{

	NSInteger id = 1;

	PtaxController* api = [PtaxController new];
	[api getNewsDetailWithId:id
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	//	//	//NSString* detail = [[result objectForKey:@" detail"] objectValue];				// 팜택스 공지 상세 ==> 정보 구조체
	//	//	//	//NSArray* attach = [[result objectForKey:@" attach"] allKeys];				// 첨부파일 목록 ==> 리스트 구조체
					NSString* link = [result objectForKey:@"link"];						// 첨부 파일 다운로드 링크
					NSString* name = [result objectForKey:@"name"];						// 첨부 파일 이름
				NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
				NSString* detail = [result objectForKey:@"detail"];						// 상세 호출 URL
				NSString* existAttach = [result objectForKey:@"existAttach"];						// 첨부파일 여부 ( Y, N )
				NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 소식 아이디
				NSString* person = [result objectForKey:@"person"];						// 담장자 이름
				NSString* title = [result objectForKey:@"title"];						// 소식 타이틀
				NSString* type = [result objectForKey:@"type"];						// 공지 타입(A:전체알림, M:나의 알림)
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 파일을 등록한다.
 */
-(void)postUploadFile{

	NSString* image = @"";

	TestController* api = [TestController new];
	[api postUploadFileWithImage:image
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSInteger fildId = [[result objectForKey:@"fildId"] doubleValue];				// 파일 등록 후 파일 아이디 확인 
			NSString* imgUrl = [result objectForKey:@"imgUrl"];						// 변경된 이미지 파일 url
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 사용자 자신에게 푸시를 전송한다.
 */
-(void)postSendPushMember{

	NSString * os = @"";
	NSString * title = @"";
	NSString * body = @"";
	NSString * imageUrl = @"";
	NSString* imageFile = @"";
	NSString * category = @"";
	NSString * moveType = @"";
	NSInteger moveId = 1;
	NSString * moveUrl = @"";

	TestController* api = [TestController new];
	[api postSendPushMemberWithOs:os
                         title:title
                          body:body
                      imageUrl:imageUrl
                     imageFile:imageFile
                      category:category
                      moveType:moveType
                        moveId:moveId
                       moveUrl:moveUrl
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 토큰으로 푸시를 전송한다.
 */
-(void)postSendPushToken{

	NSString * token = @"";
	NSString * os = @"";
	NSString * title = @"";
	NSString * body = @"";
	NSString * imageUrl = @"";
	NSString* imageFile = @"";
	NSString * category = @"";
	NSString * moveType = @"";
	NSInteger moveId = 1;
	NSString * moveUrl = @"";

	TestController* api = [TestController new];
	[api postSendPushTokenWithToken:token
                              os:os
                           title:title
                            body:body
                        imageUrl:imageUrl
                       imageFile:imageFile
                        category:category
                        moveType:moveType
                          moveId:moveId
                         moveUrl:moveUrl
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * Page=null , 면허번호로 푸시 전송한다.
 */
-(void)postSendPushLicense{

	NSString * licenseNo = @"";
	NSString * title = @"";
	NSString * body = @"";
	NSString * imageUrl = @"";
	NSString* imageFile = @"";
	NSString * category = @"";
	NSString * moveType = @"";
	NSInteger moveId = 1;
	NSString * moveUrl = @"";

	TestController* api = [TestController new];
	[api postSendPushLicenseWithLicenseNo:licenseNo
                                 title:title
                                  body:body
                              imageUrl:imageUrl
                             imageFile:imageFile
                              category:category
                              moveType:moveType
                                moveId:moveId
                               moveUrl:moveUrl
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * 테스트 푸시를 전송한다.
 */
-(void)sendPush{

	NSString * registrationToken = @"";
	NSString * title = @"";
	NSString * body = @"";
	NSString * imgUrl = @"";
	NSString * categoryType = @"";
	NSString * moveType = @"";
	NSInteger moveId = 1;
	NSString * moveUrl = @"";

	TestController* api = [TestController new];
	[api sendPushWithRegistrationToken:registrationToken
                              title:title
                               body:body
                             imgUrl:imgUrl
                       categoryType:categoryType
                           moveType:moveType
                             moveId:moveId
                            moveUrl:moveUrl
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


/**
 * 캐시를 삭제한다.
 */
-(void)deleteCache{

	NSString * cache = @"";
	NSInteger key = 1;

	MagicController* api = [MagicController new];
	[api deleteCacheWithCache:cache
                       key:key
	success:^(NSURLSessionDataTask * _Nonnull task, id  _Nonnull responseObject) {

		NSDictionary* result = responseObject;
		NSLog(@"Response : %@", result.description);

			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지

		if (code == 200) {
			// 요청 성공 
			//////////////회신 정보 참조용//////////////
			/*
			NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
			NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
			*/

		} else {
			// 서버 내부 오류 
			NSLog(@"Response : %@", msg);
		}
	} failure:^(NSURLSessionDataTask * _Nonnull task, NSError * _Nonnull error) {
		// 서버 외부 오류 
		NSLog(@"Error : %@", error.localizedDescription);
	}];
}


@end
