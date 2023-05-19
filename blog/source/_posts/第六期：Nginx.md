---
title: 第六期：Nginx
date: 2023-05-20 00:42:05
tags:
---
### 服务管理
```
nginx # 启动
nginx -t # 测试配置
nginx -s reload # 重载配置
```

### 示例
```
server {
    listen      88;
    server_name localhost;
    charset     utf-8;
    index       index.html index.htm;
    access_log  logs/access.log;
    location / {
        return 200 "test conf";
    }
}
```
### 重定向（地址会变）
```
location / {
    return 302 http://localhost:89/$request_uri;
}

# redirect.conf
server {
    listen      89;
    server_name localhost;
    charset     utf-8;
    index       index.html index.htm;
    access_log  logs/access.log;
    location / {
        return 200 "after redirect uri";
    }
}

进行测试：curl -L localhost:88
返回：after redirect uri
```