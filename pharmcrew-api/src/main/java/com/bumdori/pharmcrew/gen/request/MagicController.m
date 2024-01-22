//
//	MagicController.m
//	AFNetworking
//
//	 Created by Sungs on 2020. 05. 20.
//	 Copyright © 2020 Sungs. All rights reserved.

#import "MagicController.h"

@implementation MagicController

/**
 * 캐시를 삭제한다.
 * @param cache : 캐시 종류
 * @param key : 키
 */
-(void)deleteCacheWithCache:(NSString *)cache
                        key:(NSInteger)key
                    success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                    failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure {

	NSString* URL = @"/caches/{cache}";

	//파라메터 구성
	NSString* stringKey = [NSString stringWithFormat:@"%ld", key];

	NSMutableDictionary* param = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                              cache,@"cache",
                              stringKey,@"key",
                              nil];

	//////////////회신 정보 참조용//////////////
	/*
	NSInteger code = [[result objectForKey:@"code"] integerValue];				// 회신 코드
	NSString* msg = [result objectForKey:@"message"];						// 회신 메시지
	*/

	// DELETE으로 요청
	[[RequestManager sharedManager] requestDELETE:URL parameters:param success:success failure:failure];
}


@end
