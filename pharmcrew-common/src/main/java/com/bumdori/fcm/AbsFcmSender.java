package com.bumdori.fcm;

import java.util.*;

import com.bumdori.util.StringUtils;
import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bumdori.spring.BLogger;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.common.util.concurrent.MoreExecutors;

public abstract class AbsFcmSender {
    private final Logger logger = LoggerFactory.getLogger(AbsFcmSender.class);

    private static final Gson gson = new Gson();

    public static class FcmData {
        private Long id;

        // notification
        private String title;
        private String body;
        private String imgUrl;

        private Map<String, String> dataMap;

        private String condition;
        private String[] topics;
        private String[] registrationTokens;

        /**
         * message.notification.title
         * @param title
         * @return
         */
        public FcmData setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * message.notification.body
         * @param body
         * @return
         */
        public FcmData setBody(String body) {
            this.body = body;
            return this;
        }

        /**
         * message.notification.image
         * @param imgUrl
         */
        public FcmData setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        /**
         * message.data(key-value)
         * @param key
         * @param value
         * @return
         */
        public FcmData addData(String key, String value) {
            if (StringUtils.isEmpty(value))    return this;
            if (dataMap == null) {
                dataMap = new HashMap<>();
            }
            dataMap.put(key, value);
            return this;
        }

        /**
         * fcm 등록 토큰<br>
         * 우선 순위: condition > topic > registrationToken
         * @param registrationTokens
         * @return
         */
        public FcmData setRegistrationToken(String ...registrationTokens) {
            this.registrationTokens = registrationTokens;
            return this;
        }

        /**
         * fcm 등록 토큰<br>
         * 우선 순위: condition > topic > registrationToken
         * @param registrationTokens
         * @return
         */
        public FcmData setRegistrationToken(List<String> registrationTokens) {
            return setRegistrationToken(registrationTokens.toArray(new String[registrationTokens.size()]));
        }

        /**
         * 토픽은 and 조건으로 추가된다.<br>
         * or 조건이 필요하면 "'TopicA' in topics && ('TopicB' in topics || 'TopicC' in topics)" 형식으로 setCondition을 호출할 것<br>
         * 우선 순위: condition > topic > registrationToken
         * @param topics
         * @return
         * @see AbsFcmSender.FcmData#setCondition(String)
         */
        public FcmData setTopic(String ...topics) {
            this.topics = topics;
            return this;
        }

        /**
         * 조건(토픽 조합 and, or)<br>
         * 우선 순위: condition > topic > registrationToken
         * @param condition
         * @return
         */
        public FcmData setCondition(String condition) {
            this.condition = condition;
            return this;
        }
    }

    /**
     * 데이터가 잘못된 ID 리스트
     * @param ids
     */
    public abstract void onErrorIds(Long ...ids);
    /**
     * 전송 실패된 토큰 리스트<br>
     *     이후 동일한 토큰으로 전송하지 않아야 한다.
     * @param tokens
     */
    public abstract void onFailRegistrationTokens(List<String> tokens);

    public void send(FcmData ...datas) {
        List<Long> errorIds = null;
        List<Long> failIds = null;
        List<Message> messages = null;
        List<String> registrationTokens = null;

        for (FcmData data: datas) {
            // 대상이 없는 경우 에외 처리
            if ((StringUtils.isEmpty(data.condition) && ArrayUtils.isEmpty(data.topics) && ArrayUtils.isEmpty(data.registrationTokens)) ||
                    // 데이터가 없는 경우 예외 처리
                    (StringUtils.isEmpty(data.title) && StringUtils.isEmpty(data.body) && data.dataMap == null)) {
                if (data.id != null) {
                    if (errorIds == null)   errorIds = new ArrayList<>();
                    errorIds.add(data.id);
                }
                continue;
            }

            Notification notification = null;
            if (!StringUtils.isEmpty(data.title) || !StringUtils.isEmpty(data.body) || !StringUtils.isEmpty(data.imgUrl)) {
                notification = Notification.builder()
                        .setTitle(data.title)
                        .setBody(data.body)
                        .setImage(data.imgUrl)
                        .build();
            }

            String condition = null;
            String topic = null;
            if (!StringUtils.isEmpty(data.condition)) {
                condition = data.condition;
            } else {
                if (!ArrayUtils.isEmpty(data.topics)) {
                    if (data.topics.length == 1) {
                        topic = data.topics[0];
                    } else {
                        StringBuffer sb = new StringBuffer();
                        for (int i = 0; i < data.topics.length; i++) {
                            if (sb.length() > 0)    sb.append(" && ");
                            sb.append(String.format("'%s' in topics", data.topics[i]));
                        }
                        condition = sb.toString();
                    }
                }
            }

            if (!StringUtils.isEmpty(condition) || !StringUtils.isEmpty(topic) ||
                    (!ArrayUtils.isEmpty(data.registrationTokens) && data.registrationTokens.length == 1)) {
                // send or sendAll
                String registrationToken = null;

                Message.Builder builder = Message.builder();

                // <===== iOS
//                ApnsFcmOptions apnsFcmOptions = ApnsFcmOptions.builder()
//                        .build();
                Aps aps = Aps.builder()
//                        .setContentAvailable(true)  // silent notifiation
                        .setAlert(ApsAlert.builder()
                                // TODO 이건 프로젝트에 종속적인데...
                                .setTitle(data.dataMap != null ? data.dataMap.get("title") : null)
                                // TODO 이건 프로젝트에 종속적인데...
                                .setBody(data.dataMap != null ? data.dataMap.get("body") : null)
                                .build())
                        // TODO 이건 프로젝트에 종속적인데...
                        .setMutableContent(data.dataMap != null ? data.dataMap.containsKey("imgUrl") : false)  // required time for process content
                        .build();
//                FcmOptions fcmOptions = FcmOptions.builder()
//                        .build();

                ApnsConfig apnsConfig = ApnsConfig.builder()
                        .setAps(aps)
//                        .setFcmOptions(apnsFcmOptions)
                        .build();
                builder.setApnsConfig(apnsConfig);
//                builder.setFcmOptions(fcmOptions);
                // iOS =====>

                if (notification != null)   builder.setNotification(notification);
                if (data.dataMap != null)   builder.putAllData(data.dataMap);
                if (!StringUtils.isEmpty(condition))    builder.setCondition(condition);
                else if (!StringUtils.isEmpty(topic))   builder.setTopic(topic);
                else {
                    registrationToken = data.registrationTokens[0];
                    builder.setToken(registrationToken);
                }
                Message message = builder.build();
                BLogger.debug(logger, "message: {}", gson.toJson(message));

                if (messages == null) {
                    messages = new ArrayList<>();
                    registrationTokens = new ArrayList<>();
                }
                messages.add(message);
                registrationTokens.add(registrationToken);

                if (messages.size() == 500) {
                    send(new ArrayList<>(messages), new ArrayList<>(registrationTokens));
                    messages.clear();
                    registrationTokens.clear();
                }
            } else {
                // sendMulticast
                MulticastMessage.Builder builder = MulticastMessage.builder();

                // <===== iOS
//                ApnsFcmOptions apnsFcmOptions = ApnsFcmOptions.builder()
//                        .build();
                Aps aps = Aps.builder()
//                        .setContentAvailable(true)  // silent notifiation
                        .setAlert(ApsAlert.builder()
                                // TODO 이건 프로젝트에 종속적인데...
                                .setTitle(data.dataMap != null ? data.dataMap.get("title") : null)
                                // TODO 이건 프로젝트에 종속적인데...
                                .setBody(data.dataMap != null ? data.dataMap.get("body") : null)
                                .build())
                        // TODO 이건 프로젝트에 종속적인데...
                        .setMutableContent(data.dataMap != null ? data.dataMap.containsKey("imgUrl") : false)  // required time for process content
                        .build();

                ApnsConfig apnsConfig = ApnsConfig.builder()
                        .setAps(aps)
//                        .setFcmOptions(apnsFcmOptions)
                        .build();
                builder.setApnsConfig(apnsConfig);
                // iOS =====>

                if (notification != null)   builder.setNotification(notification);
                if (data.dataMap != null)   builder.putAllData(data.dataMap);
                builder.addAllTokens(Arrays.asList(data.registrationTokens));
                MulticastMessage message = builder.build();
                BLogger.debug(logger, "message: {}", gson.toJson(message));

                for (int i = 0; i < data.registrationTokens.length; i += 500) {
                    String[] subRegistrationTokens = ArrayUtils.subarray(data.registrationTokens, i * 500, i * 500 + 500);
                    send(message, subRegistrationTokens);
                }
            }
        }

        if (messages != null && messages.size() > 0) {
            send(messages, registrationTokens);
        }
    }

    private void send(List<Message> messages, List<String> registrationTokens) {
        if (messages.size() == 1) {
            ApiFuture<String> future = FirebaseMessaging.getInstance().sendAsync(messages.get(0));
            BLogger.debug(logger, "future: {}", future);
            ApiFutures.addCallback(future, new ApiFutureCallback<String>() {

                @Override
                public void onFailure(Throwable t) {
                    // TODO fcm send onFailure
                    t.printStackTrace();
                }

                @Override
                public void onSuccess(String result) {
                    // TODO fcm send onSuccess
                    BLogger.debug(logger, "result: {}", result);
                }
            }, MoreExecutors.directExecutor());
        } else {
            ApiFuture<BatchResponse> future = FirebaseMessaging.getInstance().sendAllAsync(messages);
            BLogger.debug(logger, "future: {}", future);
            ApiFutures.addCallback(future, new ApiFutureCallback<BatchResponse>() {

                @Override
                public void onFailure(Throwable t) {
                    // TODO fcm sendAll onFailure
                    t.printStackTrace();
                }

                @Override
                public void onSuccess(BatchResponse result) {
                    // TODO fcm sendAll onSuccess
                    BLogger.debug(logger, "result: s({})/f({})", result.getSuccessCount(), result.getFailureCount());

                    if (result.getFailureCount() > 0) {
                        List<String> failedTokens = new ArrayList<>();

                        List<SendResponse> responses = result.getResponses();
                        for (int i = 0; i < responses.size(); i++) {
                            SendResponse response = responses.get(i);
                            if (!response.isSuccessful()) {
                                failedTokens.add(registrationTokens.get(i));
                            }
                        }

                        onFailRegistrationTokens(failedTokens);
                    }
                }
            }, MoreExecutors.directExecutor());
        }
    }

    private void send(MulticastMessage message, String ...registrationTokens) {
        ApiFuture<BatchResponse> future = FirebaseMessaging.getInstance().sendMulticastAsync(message);
        BLogger.debug(logger, "future: {}", future);
        ApiFutures.addCallback(future, new ApiFutureCallback<BatchResponse>() {

            @Override
            public void onFailure(Throwable t) {
                // TODO fcm sendMulticast onFailure
                t.printStackTrace();
            }

            @Override
            public void onSuccess(BatchResponse result) {
                // TODO fcm sendMulticast onSuccess
                BLogger.debug(logger, "result: s({})/f({})", result.getSuccessCount(), result.getFailureCount());

                if (result.getFailureCount() > 0) {
                    List<String> failedTokens = new ArrayList<>();

                    List<SendResponse> responses = result.getResponses();
                    for (int i = 0; i < responses.size(); i++) {
                        if (!(responses.get(i).isSuccessful())) {
                            failedTokens.add(registrationTokens[i]);
                        }
                    }

                    onFailRegistrationTokens(failedTokens);
                }
            }
        }, MoreExecutors.directExecutor());
    }
}
