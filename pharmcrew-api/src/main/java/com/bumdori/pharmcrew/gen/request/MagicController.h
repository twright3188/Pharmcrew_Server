//
//  MagicController.h
//  AFNetworking
//
//  Created by Sungs on 2020. 05. 20.
//  Copyright © 2020 Sungs. All rights reserved.
//
#ifndef MagicController_h
#define MagicController_h

#import "RequestManager.h"
@interface MagicController : NSObject

// 캐시를 삭제한다.
-(void)deleteCacheWithCache:(NSString *)cache
                        key:(NSInteger)key
                    success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                    failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


@end
#endif /* MagicController_h */
