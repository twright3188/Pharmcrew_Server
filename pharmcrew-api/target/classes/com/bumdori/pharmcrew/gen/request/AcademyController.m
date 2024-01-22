//
//	AcademyController.m
//	AFNetworking
//
//	 Created by Sungs on 2020. 05. 20.
//	 Copyright © 2020 Sungs. All rights reserved.

#import "AcademyController.h"

@implementation AcademyController

/**
 * Page=79.0 , 학술정보 PDF 목록 조회한다. : 0.8
 * @param searchKey : 검색어
 * @param page : 요청할 페이지 : 1 에서 시작
 * @param count : 페이지당 카운트: Default=30개
 */
-(void)getDocListWithSearchKey:(NSString *)searchKey
                          page:(NSInteger)page
                         count:(NSInteger)count
                       success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                       failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/academy/doc";

	//파라메터 구성
	NSString* stringPage = [NSString stringWithFormat:@"%ld", page];
	NSString* stringCount = [NSString stringWithFormat:@"%ld", count];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              searchKey,@"searchKey",
                              stringPage,@"page",
                              stringCount,@"count",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	//NSArray* docs = [[result objectForKey:@" docs"] allKeys];				// 학술정보 PDF 목록 ==> 리스트 구조체
		NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 문서 아이디
		NSString* link = [result objectForKey:@"link"];						// 문서 다운로드 경로
		NSString* title = [result objectForKey:@"title"];						// 문서 타이틀
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=80.0 , 학술정보 PDF 상세 조회 : 0.8
 * @param id : 문서 아이디
 */
-(void)getDocDetailWithId:(NSInteger)id
                  success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                  failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/academy/doc/{id}";

	//파라메터 구성
	NSString* stringId = [NSString stringWithFormat:@"%ld", id];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              stringId,@"id",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	//NSString* doc = [[result objectForKey:@" doc"] objectValue];				// 학술 정보 PDF 정보 ==> 정보 구조체
		NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 문서 아이디
		NSString* link = [result objectForKey:@"link"];						// 문서 다운로드 경로
		NSString* title = [result objectForKey:@"title"];						// 문서 타이틀
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=81.0 , 학술정보 동영상 목록 조회 : 0.8
 * @param searchKey : 검색어
 * @param page : 요청할 페이지 : 1 에서 시작
 * @param count : 페이지당 카운트: Default=30개
 */
-(void)getVideoListWithSearchKey:(NSString *)searchKey
                            page:(NSInteger)page
                           count:(NSInteger)count
                         success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                         failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/academy/video";

	//파라메터 구성
	NSString* stringPage = [NSString stringWithFormat:@"%ld", page];
	NSString* stringCount = [NSString stringWithFormat:@"%ld", count];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              searchKey,@"searchKey",
                              stringPage,@"page",
                              stringCount,@"count",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//NSArray* videos = [[result objectForKey:@" videos"] allKeys];				// 학술정보 동영상 목록 ==> 리스트 구조체
		NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 동영상 아이디
		NSString* title = [result objectForKey:@"title"];						// 문서 타이틀
		NSString* youtube = [result objectForKey:@"youtube"];						// Youtube 동영상 아이디
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=81.0 , 학술정보 동영상 상세 조회 : 0.8
 * @param id : 동영상 아이디
 */
-(void)getVideoDetailWithId:(NSInteger)id
                    success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                    failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/academy/video/{id}";

	//파라메터 구성
	NSString* stringId = [NSString stringWithFormat:@"%ld", id];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              stringId,@"id",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//NSString* video = [[result objectForKey:@" video"] objectValue];				// 학술 정보 동영상 정보 ==> 정보 구조체
		NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 동영상 아이디
		NSString* title = [result objectForKey:@"title"];						// 문서 타이틀
		NSString* youtube = [result objectForKey:@"youtube"];						// Youtube 동영상 아이디
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


@end
