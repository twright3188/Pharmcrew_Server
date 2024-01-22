//
//  SessionController.h
//  AFNetworking
//
//  Created by Sungs on 2020. 05. 20.
//  Copyright © 2020 Sungs. All rights reserved.
//
#ifndef SessionController_h
#define SessionController_h

#import "RequestManager.h"
@interface SessionController : NSObject

// Page=null , 로그인을 수행하여 세션을 획득한다.
-(void)postSessionWithLicenseNo:(NSString *)licenseNo
                       password:(NSString *)password
                        success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                        failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 로그아웃을 수행하여 세션을 만료시킨다.
-(void)deleteSessionSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                    failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


@end
#endif /* SessionController_h */
