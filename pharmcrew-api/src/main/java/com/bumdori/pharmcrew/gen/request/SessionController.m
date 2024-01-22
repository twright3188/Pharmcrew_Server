//
//	SessionController.m
//	AFNetworking
//
//	 Created by Sungs on 2020. 05. 20.
//	 Copyright © 2020 Sungs. All rights reserved.

#import "SessionController.h"

@implementation SessionController

/**
 * Page=null , 로그인을 수행하여 세션을 획득한다.
 * @param licenseNo : 면허번호
 * @param password : 비밀번호- 회원인증 시 회신되는 정보
 */
-(void)postSessionWithLicenseNo:(NSString *)licenseNo
                       password:(NSString *)password
                        success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                        failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/sessions";

	//파라메터 구성

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              licenseNo,@"licenseNo",
                              password,@"password",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSInteger eduStartTime = [[result objectForKey:@"eduStartTime"] doubleValue];				// 교육 시작 시간 - 교육 진행 중인 경우
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
	*/

	// POST으로 요청
	[[RequestManager sharedManager] requestPOST:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 로그아웃을 수행하여 세션을 만료시킨다.
 */
-(void)deleteSessionSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                    failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/session";

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


@end
