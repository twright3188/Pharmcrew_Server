//
//  EducationController.h
//  AFNetworking
//
//  Created by Sungs on 2020. 05. 20.
//  Copyright © 2020 Sungs. All rights reserved.
//
#ifndef EducationController_h
#define EducationController_h

#import "RequestManager.h"
@interface EducationController : NSObject

// Page=null , 교육 목록을 요청한다.
-(void)getGetEduListWithPage:(NSInteger)page
                       count:(NSInteger)count
                     success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                     failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 교육 상세 정보를 요청한다.
-(void)getGetEduDetailWithEduId:(NSInteger)eduId
                        success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                        failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 나의 교육 이수 목록을 요청한다.
-(void)getGetEduMineWithYear:(NSInteger)year
                     success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                     failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 출석 참여, 퇴실을 등록한다.
-(void)postPutEduAttendWithQrcode:(NSString *)qrcode
                          success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                          failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 교육을 평가, 수정한다.
-(void)postEvalEduWithEduId:(NSInteger)eduId
                   courseId:(NSInteger)courseId
                       star:(NSInteger)star
                       body:(NSString *)body
                    success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                    failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


@end
#endif /* EducationController_h */
