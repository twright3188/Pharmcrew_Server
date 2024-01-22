//
//  SurveyController.h
//  AFNetworking
//
//  Created by Sungs on 2020. 05. 20.
//  Copyright © 2020 Sungs. All rights reserved.
//
#ifndef SurveyController_h
#define SurveyController_h

#import "RequestManager.h"
@interface SurveyController : NSObject

// Page=null , 설문 목록을 요청한다.
-(void)getGetSurveyListWithPage:(NSInteger)page
                          count:(NSInteger)count
                        success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                        failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 각 설문의 문항 목록을 요청한다.
-(void)getGetQuestionsWithSurveyId:(NSInteger)surveyId
                           success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                           failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 설문 응답을 등록한다.
-(void)postPutSurveyWithSurveyId:(NSInteger)surveyId
                         questNo:(NSInteger)questNo
                          answer:(NSString *)answer
                         success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                         failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


@end
#endif /* SurveyController_h */
