//
//	TestController.m
//	AFNetworking
//
//	 Created by Sungs on 2020. 05. 20.
//	 Copyright © 2020 Sungs. All rights reserved.

#import "TestController.h"

@implementation TestController

/**
 * Page=null , 파일을 등록한다.
 * @param image : 등록 파일
 */
-(void)postUploadFileWithImage:(NSInteger)image
                       success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                       failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/test/uploaFile";

	//파라메터 구성
	NSString* stringImage = [NSString stringWithFormat:@"%lf", image];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              stringImage,@"image",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSInteger fildId = [[result objectForKey:@"fildId"] doubleValue];				// 파일 등록 후 파일 아이디 확인 
	NSString* imgUrl = [result objectForKey:@"imgUrl"];						// 변경된 이미지 파일 url
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// POST으로 요청
	[[RequestManager sharedManager] requestPOST:URL parameters:param success:success failure:failure];
}


/**
 * Page=null , 사용자 자신에게 푸시를 전송한다.
 * @param os : 요청 OS - A:android, I:iOS - 없으면 전체
 * @param title : 전송할 메시지 타이틀
 * @param body : 전송할 메시 내용
 * @param imageUrl : 푸시에 보여질 이미지 링크
 * @param imageFile : 푸시에 보여질 이미지 파일
 * @param category : AD: 광고, NT: 알림
 * @param moveType : WU(웹), NO(공지사항), ED(교육), SU(설문)
 * @param moveId : 이동할 ID(공지사항ID, 교육ID, 설문ID)
 * @param moveUrl : 이동할 URL
 */
-(void)postSendPushMemberWithOs:(NSString *)os
                          title:(NSString *)title
                           body:(NSString *)body
                       imageUrl:(NSString *)imageUrl
                      imageFile:(NSInteger)imageFile
                       category:(NSString *)category
                       moveType:(NSString *)moveType
                         moveId:(NSInteger)moveId
                        moveUrl:(NSString *)moveUrl
                        success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                        failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/test/push/send/member";

	//파라메터 구성
	NSString* stringImageFile = [NSString stringWithFormat:@"%lf", imageFile];
	NSString* stringMoveId = [NSString stringWithFormat:@"%ld", moveId];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              os,@"os",
                              title,@"title",
                              body,@"body",
                              imageUrl,@"imageUrl",
                              stringImageFile,@"imageFile",
                              category,@"category",
                              moveType,@"moveType",
                              stringMoveId,@"moveId",
                              moveUrl,@"moveUrl",
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
 * Page=null , 토큰으로 푸시를 전송한다.
 * @param token : 푸시 수신할 토큰
 * @param os : 요청 OS - A:android, I:iOS
 * @param title : 전송할 메시지 타이틀
 * @param body : 전송할 메시 내용
 * @param imageUrl : 푸시에 보여질 이미지 링크
 * @param imageFile : 푸시에 보여질 이미지 파일
 * @param category : AD: 광고, NT: 알림
 * @param moveType : WU(웹), NO(공지사항), ED(교육), SU(설문)
 * @param moveId : 이동할 ID(공지사항ID, 교육ID, 설문ID)
 * @param moveUrl : 이동할 URL
 */
-(void)postSendPushTokenWithToken:(NSString *)token
                               os:(NSString *)os
                            title:(NSString *)title
                             body:(NSString *)body
                         imageUrl:(NSString *)imageUrl
                        imageFile:(NSInteger)imageFile
                         category:(NSString *)category
                         moveType:(NSString *)moveType
                           moveId:(NSInteger)moveId
                          moveUrl:(NSString *)moveUrl
                          success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                          failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/test/push/send/token";

	//파라메터 구성
	NSString* stringImageFile = [NSString stringWithFormat:@"%lf", imageFile];
	NSString* stringMoveId = [NSString stringWithFormat:@"%ld", moveId];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              token,@"token",
                              os,@"os",
                              title,@"title",
                              body,@"body",
                              imageUrl,@"imageUrl",
                              stringImageFile,@"imageFile",
                              category,@"category",
                              moveType,@"moveType",
                              stringMoveId,@"moveId",
                              moveUrl,@"moveUrl",
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
 * Page=null , 면허번호로 푸시 전송한다.
 * @param licenseNo : 전송할 멤버 라이선스 번호
 * @param title : 전송할 메시지 타이틀
 * @param body : 전송할 메시 내용
 * @param imageUrl : 푸시에 보여질 이미지 링크
 * @param imageFile : 푸시에 보여질 이미지 파일
 * @param category : AD: 광고, NT: 알림
 * @param moveType : WU(웹), NO(공지사항), ED(교육), SU(설문)
 * @param moveId : 이동할 ID(공지사항ID, 교육ID, 설문ID)
 * @param moveUrl : 이동할 URL
 */
-(void)postSendPushLicenseWithLicenseNo:(NSString *)licenseNo
                                  title:(NSString *)title
                                   body:(NSString *)body
                               imageUrl:(NSString *)imageUrl
                              imageFile:(NSInteger)imageFile
                               category:(NSString *)category
                               moveType:(NSString *)moveType
                                 moveId:(NSInteger)moveId
                                moveUrl:(NSString *)moveUrl
                                success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                                failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/test/push/send/license";

	//파라메터 구성
	NSString* stringImageFile = [NSString stringWithFormat:@"%lf", imageFile];
	NSString* stringMoveId = [NSString stringWithFormat:@"%ld", moveId];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              licenseNo,@"licenseNo",
                              title,@"title",
                              body,@"body",
                              imageUrl,@"imageUrl",
                              stringImageFile,@"imageFile",
                              category,@"category",
                              moveType,@"moveType",
                              stringMoveId,@"moveId",
                              moveUrl,@"moveUrl",
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
 * 테스트 푸시를 전송한다.
 * @param registrationToken : 푸시 토큰
 * @param title : 제목
 * @param body : 내용
 * @param imgUrl : 이미지 URL<br>샘플: http://amytech.cafe24.com/PharmCrew/2020/05/21/13/09/1590034143178.jpg
 * @param categoryType : 이동 카테고리 타입<br>AD: 광고, NT: 알림, PT: 팜텍스공지
 * @param moveType : 이동 타입<br>WU: 웹, NO: 공지, ED: 교육, SU: 설문, PT: 팜텍스공지, NQ: 공지 문의, EQ: 교육 문의, PQ: 팜텍스공지 문의
 * @param moveId : 이동 ID<br>moveType이 NO일 때 공지 ID, ED일 때 교육 ID, SU일 때 설문 ID, PT일 때 팜텍스공지 ID, NQ일 때 공지 문의 ID, EQ일 때 교육 문의 ID, PQ일 때 팜텍스공지 문의 ID로 필수
 * @param moveUrl : 이동 URL<br>moveType이 WU일 때 필수
 */
-(void)sendPushWithRegistrationToken:(NSString *)registrationToken
                               title:(NSString *)title
                                body:(NSString *)body
                              imgUrl:(NSString *)imgUrl
                        categoryType:(NSString *)categoryType
                            moveType:(NSString *)moveType
                              moveId:(NSInteger)moveId
                             moveUrl:(NSString *)moveUrl
                             success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                             failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/test/pushs";

	//파라메터 구성
	NSString* stringMoveId = [NSString stringWithFormat:@"%ld", moveId];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              registrationToken,@"registrationToken",
                              title,@"title",
                              body,@"body",
                              imgUrl,@"imgUrl",
                              categoryType,@"categoryType",
                              moveType,@"moveType",
                              stringMoveId,@"moveId",
                              moveUrl,@"moveUrl",
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
