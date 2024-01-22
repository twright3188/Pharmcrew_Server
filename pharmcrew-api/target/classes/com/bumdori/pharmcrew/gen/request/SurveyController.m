//
//	SurveyController.m
//	AFNetworking
//
//	 Created by Sungs on 2020. 05. 20.
//	 Copyright © 2020 Sungs. All rights reserved.

#import "SurveyController.h"

@implementation SurveyController

/**
 * Page=null , 설문 목록을 요청한다.
 * @param page : 요청할 페이지 : 1 에서 시작
 * @param count : 페이지당 카운트: Default=30개
 */
-(void)getGetSurveyListWithPage:(NSInteger)page
                          count:(NSInteger)count
                        success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                        failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/survey/list";

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
	//NSArray* surveys = [[result objectForKey:@" surveys"] allKeys];				// 설문 목록 ==> 리스트 구조체
		NSString* detail = [result objectForKey:@"detail"];						// 설문 설명
		NSString* endDate = [result objectForKey:@"endDate"];						// 설문 종료일  (yyyy.MM.dd)
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 설문 아이디
		NSString* isComplete = [result objectForKey:@"isComplete"];						// 설문 참여 여부 - Y:설문 참여, N:설문 미참여
		NSString* showResult = [result objectForKey:@"showResult"];						// 답변 노출 여부 - Y:결과 노출, N:결과 미노출
		NSString* startDate = [result objectForKey:@"startDate"];						// 설문 시작일  (yyyy.MM.dd)
		NSString* title = [result objectForKey:@"title"];						// 제목
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 각 설문의 문항 목록을 요청한다.
 * @param surveyId : 설문 아이디
 */
-(void)getGetQuestionsWithSurveyId:(NSInteger)surveyId
                           success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                           failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/survey/{surveyId}";

	//파라메터 구성
	NSString* stringSurveyId = [NSString stringWithFormat:@"%ld", surveyId];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              stringSurveyId,@"surveyId",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	//NSArray* questions = [[result objectForKey:@" questions"] allKeys];				// 설문 문항 목록 ==> 리스트 구조체
		NSString* answer = [result objectForKey:@"answer"];						// 내가 참여한 정답 정보
	//	//NSArray* examples = [[result objectForKey:@" examples"] allKeys];				// 객관식 답변 문항 ==> 리스트 구조체
			NSString* answer = [result objectForKey:@"answer"];						// 답변 예시
			NSInteger no = [[result objectForKey:@"no"] integerValue];				// 답변 문항 번호
			NSInteger result = [[result objectForKey:@"result"] integerValue];				// 질문 답변 비율 - 결과 노출 시 결과를 보여주는 것 ( 완료 한 사람에게 보여줄 정보)=>p39
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 설문 아이디
		NSInteger idx = [[result objectForKey:@"idx"] integerValue];				// 문항 순서
		NSString* question = [result objectForKey:@"question"];						// 질문 내용
		NSInteger totalCount = [[result objectForKey:@"totalCount"] integerValue];				// 참여한 전체 사용자 수
		NSString* type = [result objectForKey:@"type"];						// S(ubjective)주관식, O(bjective)객관식
	//NSString* survey = [[result objectForKey:@" survey"] objectValue];				// 설문정보 ==> 정보 구조체
		NSString* detail = [result objectForKey:@"detail"];						// 설문 설명
		NSString* endDate = [result objectForKey:@"endDate"];						// 설문 종료일  (yyyy.MM.dd)
		NSInteger id = [[result objectForKey:@"id"] doubleValue];				// 설문 아이디
		NSString* isComplete = [result objectForKey:@"isComplete"];						// 설문 참여 여부 - Y:설문 참여, N:설문 미참여
		NSString* showResult = [result objectForKey:@"showResult"];						// 답변 노출 여부 - Y:결과 노출, N:결과 미노출
		NSString* startDate = [result objectForKey:@"startDate"];						// 설문 시작일  (yyyy.MM.dd)
		NSString* title = [result objectForKey:@"title"];						// 제목
	*/

	// GET으로 요청
	[[RequestManager sharedManager] requestGET:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 설문 응답을 등록한다.
 * @param surveyId : 설문 아이디
 * @param questNo : 질문 번호 목록
 * @param answer : 답변 목록
 */
-(void)postPutSurveyWithSurveyId:(NSInteger)surveyId
                         questNo:(NSInteger)questNo
                          answer:(NSString *)answer
                         success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                         failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/survey/{surveyId}";

	//파라메터 구성
	NSString* stringSurveyId = [NSString stringWithFormat:@"%ld", surveyId];
	NSString* stringQuestNo = [NSString stringWithFormat:@"%ld", questNo];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              stringSurveyId,@"surveyId",
                              stringQuestNo,@"questNo",
                              answer,@"answer",
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
