## 后端
### 后端api
https://github.com/PlayEdu/PlayEdu

## 前端
###后台管理前端程序
https://github.com/chenqiaorui/backend

###h5前端
[H5 学习端口](https://github.com/chenqiaorui/h5)

###PC前端项目
[[PC 前端项目](https://github.com/chenqiaorui/frontend)]

## 运维部署
###docker-compose方式
###docker仓库
https://gitee.com/playeduxyz/compose.git

###docker-compose.yml
```
version: "3.5"

x-logging: &default-logging
  driver: "json-file"
  options:
    max-size: "10m"
    max-file: "10"

networks:
  backend:
    driver: bridge
    ipam:
      driver: default
      config:
        - subnet: 172.10.10.0/24

volumes:
  mysql:
    driver: local
  redis:
    driver: local
  minio:
    driver: local

services:
  ### PlayEdu ################################################
  playedu:
    image: registry.cn-hangzhou.aliyuncs.com/playedu/light:1.2.2
    restart: always
    # volumes:
    #   - ./data/logs/nginx:/var/log/nginx
    environment:
      - DB_HOST=mysql
      - DB_PORT=3306
      - DB_NAME=playedu
      - DB_USER=root
      - DB_PASS=playeduxyz
      - REDIS_HOST=redis
      - REDIS_PASS=playeduxyz
      - REDIS_PORT=6379
      - SA_TOKEN_JWT_SECRET_KEY=${PLAYEDU_JWT_KEY:-playeduxyz}
    ports:
      - "${PLAYEDU_API_PORT:-9700}:80"
      - "${PLAYEDU_PC_PORT:-9800}:9800"
      - "${PLAYEDU_H5_PORT:-9801}:9801"
      - "${PLAYEDU_ADMIN_PORT:-9900}:9900"
    networks:
      - backend
    depends_on:
      - mysql
      - redis
      - minio
    logging: *default-logging

  ### Redis ################################################
  redis:
    build: ./redis
    restart: always
    # volumes:
    #   - ./data/redis:/data
    ports:
      - "${REDIS_PORT:-16379}:6379"
    networks:
      - backend
    logging: *default-logging

  ### MySQL ################################################
  mysql:
    build: ./mysql
    restart: always
    environment:
      - MYSQL_DATABASE=playedu
      - MYSQL_ROOT_PASSWORD=playeduxyz
      - TZ=UTC
    # volumes:
    #   - ./data/mysql:/var/lib/mysql
    ports:
      - "${MYSQL_PORT:-33063}:3306"
    networks:
      - backend
    logging: *default-logging

  ### Minio ################################################
  minio:
    image: bitnami/minio:latest
    restart: always
    environment:
      - MINIO_ROOT_USER=${MINIO_ROOT_USER:-username}
      - MINIO_ROOT_PASSWORD=${MINIO_ROOT_PASSWORD:-password}
      - MINIO_DEFAULT_BUCKETS=${MINIO_BUCKETS:-playedu:public}
    # volumes:
    #   - ./data/minio/data:/data
    ports:
      - "${MINIO_PORT:-9002}:9000"
      - "${MINIO_CONSOLE_PORT:-50002}:9001"
    networks:
      - backend
    logging: *default-logging
```
###docker-light
https://gitee.com/rickychen1/docker-light

###Dockerfile
```
FROM eclipse-temurin:17

LABEL maintainer="0xtyz <tengyongzhi@meedu.vip>"

#使用东八区时间环境
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

#安装基本组件
RUN apt update && apt install -y nginx

RUN mkdir /app

#Copy代码
COPY frontend /app/frontend
COPY backend /app/backend
COPY h5 /app/h5
COPY api /app/api
COPY conf/nginx.conf /etc/nginx/sites-enabled/default

RUN chmod +x /app/api/start.sh

ENTRYPOINT ["/app/api/start.sh"]
```
###操作步骤
###创建分类
方便管理员对资源分类，分类管理 -> 新建分类 -> 产品开发
                          - 后端
                          - 前端
                          - 产品设计
###上传资源，资源归属于分类
资源管理 -> 视频 -> 视频分类 -> 批量上传，若未选择分类，分配到未分类

###创建部门
支持多级子部门，如技术部-运维部

###创建学员，学员归属于部门
支持多部门绑定

###指派课程
部门下的学员才可以学习


