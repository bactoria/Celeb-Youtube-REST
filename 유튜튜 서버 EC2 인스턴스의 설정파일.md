### 유튜튜 서버 EC2 인스턴스의 설정파일

> ec2 인스턴스를 종료하면서, 인스턴스에 있던 설정 파일들을 남기고자 긁어옴

&nbsp;

**/home/ec2-user/backend/deploy.sh**
```bash
#!/bin/bash

REPOSITORY=/home/ec2-user/backend

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -f celeb-youtube)

echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi

echo "> 새 어플리케이션 배포"

echo "> Build 파일 복사"

cp $REPOSITORY/build/target/*.jar $REPOSITORY/jar/

JAR_NAME=$(ls $REPOSITORY/jar/ |grep 'celeb-youtube' | tail -n 1)

echo "> JAR Name: $JAR_NAME"

nohup java -jar -Dspring.profiles.active=real $REPOSITORY/jar/$JAR_NAME &
~                                                                            
```

(`execute-deploy.sh` 파일에 의해 트리거 됨)

&nbsp;

**crontab**
```
*/5 * * * * java -jar /home/ec2-user/batch/Celeb-Youtube-Batch/target/update-channel-0.0.1-SNAPSHOT.jar --job.name=channelUpdateJob requestDate=$(date +'\%Y\%m\%d') requestTime=$(date +'\%T') &

0 * * * * java -jar /home/ec2-user/batch/Celeb-Youtube-Batch/target/update-channel-0.0.1-SNAPSHOT.jar --job.name=channelLogJob requestDate=$(date +'\%Y\%m\%d') requestHour=$(date +'\%H') &

50 */2 * * * cd /home/ec2-user/macroDC && ./youtube.py
```

&nbsp;

#### 유튜브 홍보 - 셀레니움

**/home/ec2-user/macroDC/youtube.py**
```py
#!/usr/bin/python3
# -*- coding: utf-8 -*- 
#youtube.py

from selenium import webdriver
import time
#import configparser

#Config = configparser.ConfigParser()

#config 파일 로드
#Config.read('./info.conf')
#id = Config.get('dc', 'id')
#pw = Config.get('dc', 'pw')
id = 'DC 아이디'
pw = 'DC 비밀번호'

URL = 'http://www.dcinside.com'
GALL = 'http://gall.dcinside.com/mgallery/board/write/?id=youtube'

TITLE = '유튜브하는 연예인 리스트'
#CONTENT = "<br><p><span style='font-size: 24pt; font-family: Verdana, sans-serif;'><b><br></b></span></p><p><span style='font-size: 24pt; font-family: Verdana, sans-serif;'><b><span style='font-size: 24pt;'>유튜브하는 연예인 알리기 프로젝트</span></b></span></p><p><br></p><p><a href='http://yoututu.kr' target='_blank' class='tx-link'><span style='font-size: 14pt;'><b><span style='font-family: Verdana, sans-serif; font-size: 18pt;'>유튜튜 바로가기</span></b></span></a><br><br><img src='https://github.com/bactoria/Celeb-Youtube-Vue/raw/master/assets/yoututu1.PNG' width='400'></p><p><br><img src='https://raw.githubusercontent.com/bactoria/Celeb-Youtube-Vue/master/assets/yoututu2.PNG' width='400'><br></p>"
CONTENT = "<p>a</p>"

chrome_options = webdriver.ChromeOptions()
chrome_options.add_argument('--headless')
chrome_options.add_argument('--no-sandbox')
chrome_options.add_argument('--disable-dev-shm-usage')
chrome_options.add_argument('--disable-gpu')
chrome_options.add_argument("user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")

#크롬 드라이버 로드
driver = webdriver.Chrome('./chromedriver', chrome_options=chrome_options)

driver.implicitly_wait(3)

```

&nbsp;

**/home/ec2-user/backend/config/real-application.yml**
```
spring:
  profiles: real
  jpa:
    database: POSTGRESQL
    show-sql: false
    hibernate:
      ddl-auto: none
  datasource:
    platform: postgres
    url: jdbc:postgresql://celeb-youtube.caxlg5lgysgh.ap-northeast-2.rds.amazonaws.com:5432/youtube
    username: bactoria
    password: 비밀번호
  security:
    user:
      name: bactoria
      password: 비밀번호

server:
  port: 8087
```



