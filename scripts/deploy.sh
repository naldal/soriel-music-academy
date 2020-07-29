#!/bin/bash
JAVA_OPTS='-XX:MaxMetaspaceSize=512m -XX:MetaspaceSize=256m'

REPOSITORY=/home/ec2-user/app/project2
PROJECT_NAME=soriel-music-academy

echo "> build 파일 복사"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -fl soriel-music-academy | grep jar | awk '{print $1}')

if [ -z "$CURRENT_PID" ]; then
        echo ">현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
        echo "> kill -15 $CURRENT_PID"
        kill -9 $CURRENT_PID
        sleep 5
fi

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR NAME : $JAR_NAME"

echo "> JAR_NAME에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar \
-Dspring.config.location=classpath:/application.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
-Dspring.profiles.active=real \
$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &