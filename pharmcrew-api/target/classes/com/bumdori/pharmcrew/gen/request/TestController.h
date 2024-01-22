//
//  TestController.h
//  AFNetworking
//
//  Created by Sungs on 2020. 05. 20.
//  Copyright © 2020 Sungs. All rights reserved.
//
#ifndef TestController_h
#define TestController_h

#import "RequestManager.h"
@interface TestController : NSObject

// Page=null , 파일을 등록한다.
-(void)postUploadFileWithImage:(NSInteger)image
                       success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                       failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 사용자 자신에게 푸시를 전송한다.
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
                        failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 토큰으로 푸시를 전송한다.
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
                          failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 면허번호로 푸시 전송한다.
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
                                failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// 테스트 푸시를 전송한다.
-(void)sendPushWithRegistrationToken:(NSString *)registrationToken
                               title:(NSString *)title
                                body:(NSString *)body
                              imgUrl:(NSString *)imgUrl
                        categoryType:(NSString *)categoryType
                            moveType:(NSString *)moveType
                              moveId:(NSInteger)moveId
                             moveUrl:(NSString *)moveUrl
                             success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                             failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


@end
#endif /* TestController_h */
