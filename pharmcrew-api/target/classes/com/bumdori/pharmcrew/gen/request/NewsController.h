//
//  NewsController.h
//  AFNetworking
//
//  Created by Sungs on 2020. 05. 20.
//  Copyright © 2020 Sungs. All rights reserved.
//
#ifndef NewsController_h
#define NewsController_h

#import "RequestManager.h"
@interface NewsController : NSObject

// Page=null , 소식방 목록을 요청한다.
-(void)getGetNewsListWithPage:(NSInteger)page
                        count:(NSInteger)count
                      success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                      failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 소식방 상세 정보를 요청한다.
-(void)getGetNewsDetailWithNewsId:(NSInteger)newsId
                          success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                          failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 소식방의 문의하기를 등록한다.
-(void)postUpdateNewsQnaWithId:(NSInteger)id
                      category:(NSString *)category
                         title:(NSString *)title
                          body:(NSString *)body
                          file:(NSInteger)file
                       success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                       failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 나의 문의 목록을 요청한다.
-(void)getGetMyQnaWithPage:(NSInteger)page
                     count:(NSInteger)count
                   success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                   failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 나의 문의 상세 정보 요청
-(void)getGetMyQnaDetailWithQnaId:(NSInteger)qnaId
                          success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                          failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


@end
#endif /* NewsController_h */
