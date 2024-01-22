//
//  MemberController.h
//  AFNetworking
//
//  Created by Sungs on 2020. 05. 20.
//  Copyright © 2020 Sungs. All rights reserved.
//
#ifndef MemberController_h
#define MemberController_h

#import "RequestManager.h"
@interface MemberController : NSObject

// Page=null , 사용자 정보 입력 후 인증을 요청한다.
-(void)postAuthMemberWithLicenseNo:(NSString *)licenseNo
                          birthDay:(NSString *)birthDay
                              name:(NSString *)name
                             phone:(NSString *)phone
                           success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                           failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 인증 문자에 대해 인증을 요청한다.
-(void)postAuthPhoneWithAuthCode:(NSString *)authCode
                         success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                         failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 회원 정보 등록
-(void)postUpdateInfoWithLicenseNo:(NSString *)licenseNo
                          authCode:(NSString *)authCode
                        agreeThird:(NSString *)agreeThird
                        expireDate:(NSString *)expireDate
                          agreeSms:(NSString *)agreeSms
                         agreePush:(NSString *)agreePush
                          password:(NSString *)password
                           success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                           failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 내 정보 요청한다.
-(void)getGetMemberInfoSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                       failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=50.0 , 설정 정보를 요청한다.
-(void)getGetSettingWithOs:(NSString *)os
                   success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                   failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=50.0 , 파트너 서비스를 업데이트 한다.
-(void)postUpdatePartnersWithPartnerId:(NSInteger)partnerId
                                 useYn:(NSString *)useYn
                               success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                               failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 푸시 설정을 업데이트 한다.
-(void)postAcceptPushWithCategory:(NSString *)category
                            agree:(NSString *)agree
                          success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                          failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 프로필 이미지를 등록한다.
-(void)postUploadProfileWithImage:(NSInteger)image
                          success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                          failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 프로필 이미지를 삭제한다
-(void)deleteDeleteProfileSuccess:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                          failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 임시비밀번호 발급 요청
-(void)postRenewPasswordWithName:(NSString *)name
                       licenseNo:(NSString *)licenseNo
                         success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                         failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 비밀번호 변경 요청
-(void)postChangePasswordWithPassword:(NSString *)password
                              success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                              failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 푸시 수신 시 서버로 수신 여부를 등록한다.
-(void)postReceivePushWithLicenseNo:(NSString *)licenseNo
                             pushId:(NSInteger)pushId
                            success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                            failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


// Page=null , 광고알림,약사회알림 목록을 요청한다.
-(void)getGetNotiListWithType:(NSString *)type
                         page:(NSInteger)page
                        count:(NSInteger)count
                      success:(void (^)(NSURLSessionDataTask *task, id responseObject))success
                      failure:(void (^)(NSURLSessionDataTask *task, NSError *error))failure;


@end
#endif /* MemberController_h */
