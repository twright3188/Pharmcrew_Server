//
//  AcademyController.h
//  AFNetworking
//
//  Created by Sungs on 2020. 05. 20.
//  Copyright © 2020 Sungs. All rights reserved.
//
#ifndef AcademyController_h
#define AcademyController_h

#import "RequestManager.h"
@interface AcademyController : NSObject

// Page=79.0 , 학술정보 PDF 목록 조회한다. : 0.8
-(void)getDocListWithSearchKey:(NSString *)searchKey
                          page:(NSInteger)page
                         count:(NSInteger)count
                       success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                       failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=80.0 , 학술정보 PDF 상세 조회 : 0.8
-(void)getDocDetailWithId:(NSInteger)id
                  success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                  failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=81.0 , 학술정보 동영상 목록 조회 : 0.8
-(void)getVideoListWithSearchKey:(NSString *)searchKey
                            page:(NSInteger)page
                           count:(NSInteger)count
                         success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                         failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=81.0 , 학술정보 동영상 상세 조회 : 0.8
-(void)getVideoDetailWithId:(NSInteger)id
                    success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                    failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


@end
#endif /* AcademyController_h */
