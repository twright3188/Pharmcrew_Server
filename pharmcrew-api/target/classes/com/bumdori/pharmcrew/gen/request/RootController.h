//
//  RootController.h
//  AFNetworking
//
//  Created by Sungs on 2020. 05. 20.
//  Copyright © 2020 Sungs. All rights reserved.
//
#ifndef RootController_h
#define RootController_h

#import "RequestManager.h"
@interface RootController : NSObject

// Page=null , 앱 버전을 체크 한다.
-(void)getGetVersionWithOs:(NSString *)os
                   version:(NSString *)version
                   success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                   failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 푸시 토큰을 등록한다.
-(void)postTokenUpdateWithOs:(NSString *)os
                   pushToken:(NSString *)pushToken
                     success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                     failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 메인 팝업/배너, 한줄 공지, 파트너 서비스 정보를 요청한다.
-(void)getGetMainSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                 failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 메인 팝업 정보를 요청한다.
-(void)getGetPopupsSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                   failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 메인 배너 정보를 요청한다.
-(void)getGetBannersSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                    failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 메인 파트너 서비스 정보를 요청한다.
-(void)getGetPartnersSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                     failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 메인 한줄 공지 정보를 요청한다.
-(void)getGetNoticesSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                    failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 읽지않은 알림 숫자를 요청한다.
-(void)getGetUnReadsSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                    failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


@end
#endif /* RootController_h */
