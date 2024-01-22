//
//	NewsController.m
//	AFNetworking
//
//	 Created by Sungs on 2020. 05. 20.
//	 Copyright © 2020 Sungs. All rights reserved.

#import "NewsController.h"

@implementation NewsController

/**
 * Page=null , 소식방 목록을 요청한다.
 * @param page : 요청할 페이지 : 1 에서 시작
 * @param count : 페이지당 카운트: Default=30개
 */
-(void)getGetNewsListWithPage:(NSInteger)page
                        count:(NSInteger)count
                      success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                      failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/news/list";

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
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//NSArray* news = [[result objectForKey:@" news"] allKeys];				// 소식방 목록 ==> 리스트 구조체
		NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 소식 아이디
		NSString* isTop = [result objectForKey:@"isTop"];						// 상단고정 여부
		NSString* title = [result objectForKey:@"title"];						// 소식 타이틀
		NSString* type = [result objectForKey:@"type"];						// 공지 타입(일반약사회,지부공지,분회공지)
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 소식방 상세 정보를 요청한다.
 * @param newsId : 소식 아이디
 */
-(void)getGetNewsDetailWithNewsId:(NSInteger)newsId
                          success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                          failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/news/{newsId}";

	//파라메터 구성
	NSString* stringNewsId = [NSString stringWithFormat:@"%ld", newsId];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              stringNewsId,@"newsId",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	//NSString* detail = [[result objectForKey:@" detail"] objectValue];				// 소식방 상세 ==> 정보 구조체
	//	//NSArray* attach = [[result objectForKey:@" attach"] allKeys];				// 첨부파일 목록 ==> 리스트 구조체
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

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 소식방의 문의하기를 등록한다.
 * @param id : 소식 or 교육 아이디
 * @param category : E: 교육, N : 소식
 * @param title : 문의 타이틀
 * @param body : 문의 내용
 * @param file : 첨부파일
 */
-(void)postUpdateNewsQnaWithId:(NSInteger)id
                      category:(NSString *)category
                         title:(NSString *)title
                          body:(NSString *)body
                          file:(NSInteger)file
                       success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                       failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/news/qna";

	//파라메터 구성
	NSString* stringId = [NSString stringWithFormat:@"%ld", id];
	NSString* stringFile = [NSString stringWithFormat:@"%lf", file];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              stringId,@"id",
                              category,@"category",
                              title,@"title",
                              body,@"body",
                              stringFile,@"file",
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
 * Page=null , 나의 문의 목록을 요청한다.
 * @param page : 요청할 페이지 : 1 에서 시작
 * @param count : 페이지당 카운트: Default=30개
 */
-(void)getGetMyQnaWithPage:(NSInteger)page
                     count:(NSInteger)count
                   success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                   failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/news/mine";

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
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//NSArray* qnas = [[result objectForKey:@" qnas"] allKeys];				// 문의 목록 ==> 리스트 구조체
		NSString* category = [result objectForKey:@"category"];						// 질문 등록 카테고리 - E: 교육, N : 소식
		NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 질문 아이디
		NSString* state = [result objectForKey:@"state"];						// 답변 상태 - '답변대기', '답변완료'
		NSInteger target = [[result objectForKey:@"target"] doubleValue];				// 교육이나 소식 타겟 아이디 -> 향후 교육이나 소식으로 이동 시 필요할까 해서 추가해 봄
		NSString* title = [result objectForKey:@"title"];						// 질문 타이틀
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 나의 문의 상세 정보 요청
 * @param qnaId : 질문 아이디
 */
-(void)getGetMyQnaDetailWithQnaId:(NSInteger)qnaId
                          success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                          failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/news/mine/{qnaId}";

	//파라메터 구성
	NSString* stringQnaId = [NSString stringWithFormat:@"%ld", qnaId];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              stringQnaId,@"qnaId",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	//NSString* detail = [[result objectForKey:@" detail"] objectValue];				// 문의 답변 상세 ==> 정보 구조체
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

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


@end
