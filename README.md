# setting environment
## docker
- docker-compose.yml
<pre>
version: '3.1'

services: 
  mariadb: 
    image: mariadb:latest
    container_name: pharmcrew-mariadb
    ports: 
      - "10000:3306"
    volumes: 
      - ~/docker-data/kr.ant.kpa.pharmcrew/mariadb:/var/lib/mysql
    environment: 
      - MYSQL_ROOT_PASSWORD=dkagh123
      - TZ="Asia/Seoul"

  redis-cache:
    image: redis:latest
    container_name: pharmcrew-redis-cache
    ports:
      - "10100:6379"

  redis-api-session:
    image: redis:latest
    container_name: pharmcrew-redis-api-session
    ports: 
      - "10101:6379"
</pre>
- 기동
<pre>
$ docker-compose up
</pre>
- mariadb connection
<pre>
$ docker exec -it pharmcrew-mariadb /bin/bash
</pre>

## db
<pre>
create schema `pharmcrew` default character set utf8 collate utf8_unicode_ci;
grant all privileges on pharmcrew.* to 'pcadmin'@'%' identified by 'dkagh123' with grant option;
grant all privileges on pharmcrew.* to 'pcdev'@'%' identified by 'dkagh123' with grant option;
</pre>

## reference
- firebase cloud messaging<br>
https://firebase.google.com/docs/cloud-messaging/send-message
- fcm iOS image<br>
https://firebase.google.com/docs/cloud-messaging/ios/send-image?hl=ko
- fcm iOS silent notification : content_available<br>
https://developer.apple.com/documentation/usernotifications/setting_up_a_remote_notification_server/pushing_background_updates_to_your_app
https://firebase.google.com/docs/cloud-messaging/http-server-ref?hl=ko
https://charlie-choi.tistory.com/234
- log4j2 java configuration<br>
https://www.baeldung.com/log4j2-programmatic-config
- log4j2<br>
https://medium.com/@pakss328/log4j%EC%97%90%EC%84%9C-log4j2-migration-3fe5c77ca32
- swagger2<br>
https://daddyprogrammer.org/post/313/swagger-api-doc/
- 이거한번보자<br>
http://wonwoo.ml/index.php/post/889