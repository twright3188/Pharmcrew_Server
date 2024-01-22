//
//  PtaxController.h
//  AFNetworking
//
//  Created by Sungs on 2020. 05. 20.
//  Copyright © 2020 Sungs. All rights reserved.
//
#ifndef PtaxController_h
#define PtaxController_h

#import "RequestManager.h"
@interface PtaxController : NSObject

// Page=26.0 , 팜택스 고객 1:1 문의 목록을 조회한다. : v0.8
-(void)getQnaListWithPage:(NSInteger)page
                    count:(NSInteger)count
                  success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                  failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=27.0 , 팜택스 1:1 문의 상세 조회 : v0.8
-(void)getQnaDetalWithQnaId:(NSInteger)qnaId
                    success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                    failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=29.0 , 팜택스 1:1 문의하기를 등록한다. : v0.8
-(void)postUpdateQnaWithTitle:(NSString *)title
                         body:(NSString *)body
                        file1:(NSInteger)file1
                        file2:(NSInteger)file2
                        file3:(NSInteger)file3
                      success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                      failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=30.0 , 팜택스 공지 목록 조회 : v0.8
-(void)getNewsListWithPage:(NSInteger)page
                     count:(NSInteger)count
                   success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                   failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=31.0 , 팜택스 공지 상세 : v0.8
-(void)getNewsDetailWithId:(NSInteger)id
                   success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                   failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


@end
#endif /* PtaxController_h */
