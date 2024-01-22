//
//	PtaxController.m
//	AFNetworking
//
//	 Created by Sungs on 2020. 05. 20.
//	 Copyright © 2020 Sungs. All rights reserved.

#import "PtaxController.h"

@implementation PtaxController

/**
 * Page=26.0 , 팜택스 고객 1:1 문의 목록을 조회한다. : v0.8
 * @param page : 요청할 페이지 : 1 에서 시작
 * @param count : 페이지당 카운트: Default=30개
 */
-(void)getQnaListWithPage:(NSInteger)page
                    count:(NSInteger)count
                  success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                  failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/ptax/qna/list";

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
	//NSArray* qnas = [[result objectForKey:@" qnas"] allKeys];				// 팜택스 1:1 문의 목록 ==> 리스트 구조체
		NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
		NSString* existAttach = [result objectForKey:@"existAttach"];						// 첨부파일 유무 Y, N
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 질문 아이디
		NSString* state = [result objectForKey:@"state"];						// 답변 상태 - '답변대기', '답변완료'
		NSString* title = [result objectForKey:@"title"];						// 질문 타이틀
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=27.0 , 팜택스 1:1 문의 상세 조회 : v0.8
 * @param qnaId : 질문 아이디
 */
-(void)getQnaDetalWithQnaId:(NSInteger)qnaId
                    success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                    failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/ptax/qna/{qnaId}";

	//파라메터 구성
	NSString* stringQnaId = [NSString stringWithFormat:@"%ld", qnaId];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              stringQnaId,@"qnaId",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	//NSString* detail = [[result objectForKey:@" detail"] objectValue];				// 팜택스 1:1 문의 답변 상세 ==> 정보 구조체
		NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
		NSString* existAttach = [result objectForKey:@"existAttach"];						// 첨부파일 유무 Y, N
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 질문 아이디
	//	//NSArray* qAttach = [[result objectForKey:@" qAttach"] allKeys];				// 질문 첨부파일 목록 ==> 리스트 구조체
			NSString* link = [result objectForKey:@"link"];						// 첨부 파일 다운로드 링크
			NSString* name = [result objectForKey:@"name"];						// 첨부 파일 이름
		NSString* qBody = [result objectForKey:@"qBody"];						// 질문 상세
	//	//NSArray* rAttach = [[result objectForKey:@" rAttach"] allKeys];				// 답변 첨부파일 목록 ==> 리스트 구조체
			NSString* link = [result objectForKey:@"link"];						// 첨부 파일 다운로드 링크
			NSString* name = [result objectForKey:@"name"];						// 첨부 파일 이름
		NSString* rBody = [result objectForKey:@"rBody"];						// 답변 상세 내용
		NSString* rDate = [result objectForKey:@"rDate"];						// 답변 등록일  (yyyy.MM.dd)
		NSString* rTitle = [result objectForKey:@"rTitle"];						// 답변 타이틀
		NSString* state = [result objectForKey:@"state"];						// 답변 상태 - '답변대기', '답변완료'
		NSString* title = [result objectForKey:@"title"];						// 질문 타이틀
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=29.0 , 팜택스 1:1 문의하기를 등록한다. : v0.8
 * @param title : 문의 타이틀
 * @param body : 문의 내용
 * @param file1 : 첨부파일 1
 * @param file2 : 첨부파일 2
 * @param file3 : 첨부파일 3
 */
-(void)postUpdateQnaWithTitle:(NSString *)title
                         body:(NSString *)body
                        file1:(NSInteger)file1
                        file2:(NSInteger)file2
                        file3:(NSInteger)file3
                      success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                      failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/ptax/qna";

	//파라메터 구성
	NSString* stringFile1 = [NSString stringWithFormat:@"%lf", file1];
	NSString* stringFile2 = [NSString stringWithFormat:@"%lf", file2];
	NSString* stringFile3 = [NSString stringWithFormat:@"%lf", file3];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              title,@"title",
                              body,@"body",
                              stringFile1,@"file1",
                              stringFile2,@"file2",
                              stringFile3,@"file3",
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
 * Page=30.0 , 팜택스 공지 목록 조회 : v0.8
 * @param page : 요청할 페이지 : 1 에서 시작
 * @param count : 페이지당 카운트: Default=30개
 */
-(void)getNewsListWithPage:(NSInteger)page
                     count:(NSInteger)count
                   success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                   failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/ptax/news";

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
	//NSArray* news = [[result objectForKey:@" news"] allKeys];				// 팜택스 공지 목록 ==> 리스트 구조체
		NSString* date = [result objectForKey:@"date"];						// 등록일  (yyyy.MM.dd)
		NSString* existAttach = [result objectForKey:@"existAttach"];						// 첨부파일 여부 ( Y, N )
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 소식 아이디
		NSString* title = [result objectForKey:@"title"];						// 소식 타이틀
		NSString* type = [result objectForKey:@"type"];						// 공지 타입(A:전체알림, M:나의 알림)
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=31.0 , 팜택스 공지 상세 : v0.8
 * @param id : 팜텍스 공지 아이디
 */
-(void)getNewsDetailWithId:(NSInteger)id
                   success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                   failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/ptax/news/{id}";

	//파라메터 구성
	NSString* stringId = [NSString stringWithFormat:@"%ld", id];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              stringId,@"id",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	//NSString* detail = [[result objectForKey:@" detail"] objectValue];				// 팜택스 공지 상세 ==> 정보 구조체
	//	//NSArray* attach = [[result objectForKey:@" attach"] allKeys];				// 첨부파일 목록 ==> 리스트 구조체
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

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


@end
