//
//	RootController.m
//	AFNetworking
//
//	 Created by Sungs on 2020. 05. 20.
//	 Copyright © 2020 Sungs. All rights reserved.

#import "RootController.h"

@implementation RootController

/**
 * Page=null , 앱 버전을 체크 한다.
 * @param os : 요청 OS - A:android, I:iOS
 * @param version : 현재 단말의 버전 정보 (1.1.1- 형식)
 */
-(void)getGetVersionWithOs:(NSString *)os
                   version:(NSString *)version
                   success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                   failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/root/versions";

	//파라메터 구성

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              os,@"os",
                              version,@"version",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//NSString* version = [[result objectForKey:@" version"] objectValue];				// 버전정보 ==> 정보 구조체
		NSString* desc = [result objectForKey:@"desc"];						// 업데이트 내용 설명
		NSString* forced = [result objectForKey:@"forced"];						// 강제 여부 (Y:강제, N:일반, X: 업데이트 없음)
		NSString* link = [result objectForKey:@"link"];						// 다운로드 링크
		NSString* version = [result objectForKey:@"version"];						// 버전네임 (1.0.1)
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 푸시 토큰을 등록한다.
 * @param os : 요청 OS - A:android, I:iOS
 * @param pushToken : 푸시 토큰
 */
-(void)postTokenUpdateWithOs:(NSString *)os
                   pushToken:(NSString *)pushToken
                     success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                     failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/root/pushtoken";

	//파라메터 구성

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              os,@"os",
                              pushToken,@"pushToken",
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
 * Page=null , 메인 팝업/배너, 한줄 공지, 파트너 서비스 정보를 요청한다.
 */
-(void)getGetMainSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                 failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/root/maininfo";

	//파라메터 구성
	NSMutableDictionary* param = nil;

	//////////////회신 정보 참조용//////////////
	/*
	//NSArray* banners = [[result objectForKey:@" banners"] allKeys];				// 배너 광고 목록 ==> 리스트 구조체
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 배너 아이디
		NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 노출 순서
		NSString* image = [result objectForKey:@"image"];						// 이미지 URL
		NSInteger moveId = [[result objectForKey:@"moveId"] doubleValue];				// 이동 아이디
		NSString* moveType = [result objectForKey:@"moveType"];						// 이동 타입
		NSString* moveUrl = [result objectForKey:@"moveUrl"];						// 이동 URL
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//NSArray* notices = [[result objectForKey:@" notices"] allKeys];				// 한줄 공지 목록 ==> 리스트 구조체
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 한줄 공지 아이디 - 소식방 아이디
		NSString* title = [result objectForKey:@"title"];						// 노출할 텍스트 정보 - 소식방 타이틀
	//NSArray* partners = [[result objectForKey:@" partners"] allKeys];				// 파트너서비스 목록 ==> 리스트 구조체
		NSString* detail = [result objectForKey:@"detail"];						// 파트너 서비스 부가 정보
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 파트너 아이디
		NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 노출 순서
		NSString* image = [result objectForKey:@"image"];						// 아이콘 이미지 URL
		NSString* isUse = [result objectForKey:@"isUse"];						// 파트너서비스 사용 유무 - Y, N (설정화면에서 설정 가능)
		NSString* link = [result objectForKey:@"link"];						// 클릭시 이동할 연동 링크
		NSString* name = [result objectForKey:@"name"];						// 파트너 서비스 이름
	//NSArray* popups = [[result objectForKey:@" popups"] allKeys];				// 팝업 광고 목록 ==> 리스트 구조체
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 배너 아이디
		NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 노출 순서
		NSString* image = [result objectForKey:@"image"];						// 이미지 URL
		NSInteger moveId = [[result objectForKey:@"moveId"] doubleValue];				// 이동 아이디
		NSString* moveType = [result objectForKey:@"moveType"];						// 이동 타입
		NSString* moveUrl = [result objectForKey:@"moveUrl"];						// 이동 URL
	NSInteger unReadNotiCount = [[result objectForKey:@"unReadNotiCount"] integerValue];				// 읽지 않은 알림 숫자
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 메인 팝업 정보를 요청한다.
 */
-(void)getGetPopupsSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                   failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/root/popups";

	//파라메터 구성
	NSMutableDictionary* param = nil;

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//NSArray* popups = [[result objectForKey:@" popups"] allKeys];				// null ==> 리스트 구조체
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 배너 아이디
		NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 노출 순서
		NSString* image = [result objectForKey:@"image"];						// 이미지 URL
		NSInteger moveId = [[result objectForKey:@"moveId"] doubleValue];				// 이동 아이디
		NSString* moveType = [result objectForKey:@"moveType"];						// 이동 타입
		NSString* moveUrl = [result objectForKey:@"moveUrl"];						// 이동 URL
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 메인 배너 정보를 요청한다.
 */
-(void)getGetBannersSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                    failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/root/banners";

	//파라메터 구성
	NSMutableDictionary* param = nil;

	//////////////회신 정보 참조용//////////////
	/*
	//NSArray* banners = [[result objectForKey:@" banners"] allKeys];				// null ==> 리스트 구조체
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 배너 아이디
		NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 노출 순서
		NSString* image = [result objectForKey:@"image"];						// 이미지 URL
		NSInteger moveId = [[result objectForKey:@"moveId"] doubleValue];				// 이동 아이디
		NSString* moveType = [result objectForKey:@"moveType"];						// 이동 타입
		NSString* moveUrl = [result objectForKey:@"moveUrl"];						// 이동 URL
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 메인 파트너 서비스 정보를 요청한다.
 */
-(void)getGetPartnersSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                     failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/root/partners";

	//파라메터 구성
	NSMutableDictionary* param = nil;

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//NSArray* partners = [[result objectForKey:@" partners"] allKeys];				// null ==> 리스트 구조체
		NSString* detail = [result objectForKey:@"detail"];						// 파트너 서비스 부가 정보
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 파트너 아이디
		NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 노출 순서
		NSString* image = [result objectForKey:@"image"];						// 아이콘 이미지 URL
		NSString* isUse = [result objectForKey:@"isUse"];						// 파트너서비스 사용 유무 - Y, N (설정화면에서 설정 가능)
		NSString* link = [result objectForKey:@"link"];						// 클릭시 이동할 연동 링크
		NSString* name = [result objectForKey:@"name"];						// 파트너 서비스 이름
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 메인 한줄 공지 정보를 요청한다.
 */
-(void)getGetNoticesSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                    failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/root/notices";

	//파라메터 구성
	NSMutableDictionary* param = nil;

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//NSArray* notices = [[result objectForKey:@" notices"] allKeys];				// null ==> 리스트 구조체
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 한줄 공지 아이디 - 소식방 아이디
		NSString* title = [result objectForKey:@"title"];						// 노출할 텍스트 정보 - 소식방 타이틀
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 읽지않은 알림 숫자를 요청한다.
 */
-(void)getGetUnReadsSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                    failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/root/unreads";

	//파라메터 구성
	NSMutableDictionary* param = nil;

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	NSInteger unReadNotiCount = [[result objectForKey:@"unReadNotiCount"] integerValue];				// 읽지 않은 알림 숫자
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


@end
