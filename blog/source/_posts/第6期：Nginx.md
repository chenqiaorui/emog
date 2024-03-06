---
title: 第6期：Nginx
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
### 匹配以/api/开头的uri
```
location ^/api/ {
  proxy_pass http://loclhost:8090;
}
```
#### 一、centos7安装Nginx
##### 源码安装
- [所有nginx版本](http://nginx.org/download/)

wget下载tar.gz包
```
wget http://nginx.org/download/nginx-1.20.1.tar.gz

# 解压
tar xzf nginx-1.20.1.tar.gz
```

编译
```
cd nginx-1.20.1 && ./configure
```
注意：`安装报错误的话比如："C compiler cc is not found"，这个就是缺少编译环境，安装一下就可以了 yum -y install gcc make gcc-c++ openssl-devel`

安装
```
make && make install
```
说明：
- nignx会被安装到`/usr/local/nginx`目录下

nginx测试
```
$ which nginx
/usr/bin/nginx

nginx # 启动nginx
ps aux|grep nginx # 查看nginx进程
nginx -s stop # 停止nginx

nginx -v # nginx 版本
nginx -t # 测试配置文件
nginx -s reload # 重载配置

```

防火墙设置
```
systemctl status firewalld # 查看防火墙状态
```

设置放行端口，编辑`/etc/firewalld/zones/public.xml`文件，在</zone>前面加:

```
<zone>
  ...
  <port port="30000-30100" protocol="tcp"/>
  <port port="80" protocol="tcp"/>
</zone>
```
说明：
- 可以设置端口段或单个端口

配完文件后重载`firewall-cmd --reload`，查看被放行的端口
```
firewall-cmd --list-ports 
```

#### 二、配置文件
查看`/usr/local/nginx/conf/nginx.conf`文件：
```
worker_processes  1;
events {
    worker_connections  1024;
}

http {
    include       mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    gzip  on;
    server {
        listen       80;
        server_name  localhost;
        location / {
            root   html;
            index  index.html index.htm;
        }
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }
    include  vhost/*.conf;
}

```
说明：
- `worker_processes` - work进程数，一般根据cpu核数来设置，可以设为和CPU的数量一样
- `worker_connections` - 单个work进程可以连的并发连接数，连接数依赖于系统级别的进程可打开文件数，`ulimit -n`可查看
  
  `cat /proc/nginx_work_pid/limits` 里面的`Max open files`也可查询。

- `log_format` - 设置nginx日志格式
- `access_log` - 设置文件存储位置和应用的日志格式
- `sendfile` - 开启文件高效传输模式
- `keepalive_timeout` - 客户端和nginx之间建立的tcp长连接维持时间
- `gzip` - 开启gzip模块，文件被压缩传到客户端，优化传输效率，有效节省带宽。
- `listen 80` - 监听80端口
- `root   html;` - 相当于`root /usr/local/nginx/html`
- `index index.html` - 查找`/usr/local/nginx/html`目录下的index.html文件
- `include` - 添加配置文件

示例：在vhost下添加配置文件`test.conf`
```
server {
    listen 80;
    server_name 192.168.1.189;
    location /ricky/ {
	    return 200 "im ricky";
    }
}
```
注意：
- server_name指定为本机的ip而不是localhost，因为上一目录nginx.conf配置的localhost
- 指定访问前缀为/ricky/

检查并重载配置
```
nginx -t
nginx -s reload
```

访问：curl http://192.168.1.189:80/ricky/  
注意：
- 访问的时候`192.168.1.189` 不能写成localhost，不然匹配规则就跑到nginx.conf里面的server去了
- ricky后面的`/`不可以省略；如果想省略，可以把`location /ricky/`写成`location /ricky`
  
  `location /ricky`既会匹配到`http://192.168.1.189:80/ricky`的路由，也会匹配到`http://192.168.1.189:80/rickyadsafaf/a`

  总的来说，nginx先在所有配置文件中匹配端口->再到server_name->具体location(location之间又有优先级的区分)

#### 三、location优先级
优先级高到低
- `location = /uri` - 字符 `=` 表示精准匹配（精准匹配优先级最高）
- `location ^~ /uri` - 字符 `^` 表示以/test开头的路径，`~` 表区分大小写；但是 `~*` 表不区分大小写(带 `~*` 等修饰符优先级次之)
- `location /url` - 不带修饰符的前缀再次之
- `location /` - 最后是交给`/`通用匹配。

示例1：比较精准和带修饰符
```
server {
    listen 80;
    server_name 192.168.1.189;
    location = /ricky {
	    return 200 "精准";
    }

    location ^~ /ricky {
	    return 200 "修饰符";
    }
}

```
重载配置并访问：`curl http://192.168.1.189:80/ricky`，输出`精准`

示例2：比较带修饰符和不带修饰符
```
server {
    listen 80;
    server_name 192.168.1.189;
    location ^~ ricky {
	    return 200 "精准";
    }

    location / {
	    return 200 "通用";
    }
}

```
重载配置并访问：`curl http://192.168.1.189:80/ricky`，输出`精准`；访问不在/ricky规则内的就打出`通用`，如`curl http://192.168.1.189:80/ris`

#### nginx不需要后端就可以返回远程用户ip的配置
配置:
```
location /ip {
    default_type text/plain;
    return 200 $remote_addr;
}
```
访问
```
$ curl https://example.com/ip
192.168.1.187
```

返回json格式：
```
$ curl -s https://example.com/json_ip | jq
{
    "ip": "192.168.1.187"
}
```
### nginx.conf文件详解
```
# 定义nginx运行的用户和用户组
user www www;

# nginx work进程数，一般与cpu核数一致。
worker_processes 8;

# 错误日志类型定义
error_log /var/log/nginx/error.log info;

# 进程文件
pid /var/run/nginx.pid;

# 1个nginx进程最多能打开的文件描述符数，建议与ulimit -n(系统最大能打开的文件描述符数)一致
work_rlimit_nofile 65535;

# 
events {
  # 高性能网络io模型
  use epoll;
  # 单进程最大连接数
  work_connections 65535; 
}

http {
  include mime.type; # 文件扩展与文件类型名映射表
  default_type application/ocetet-stream; # 默认文件类型
  # charset utf-8; # 默认编码
  client_header_buffer_size 32; # 上传文件大小限制
  # 所谓请求报文包含了请求头(如 User-Agent： 或 Accept：)+请求行(GET /index.html HTTP/1.1)+请求body
  large_client_header_buffers 4 64k; # 设定请求行+请求头不能超过4x64k
  client_max_body_size 8m;  # 设置body(或上传文件)最大值8m
  sendfile on; # 开启高效文件传输模式，sendfile指令指定nginx是否调用sendfile函数来输出文件，对于普通应用设为 on，如果用来进行下载等应用磁盘IO重负载应用，可设置为off，以平衡磁盘与网络I/O处理速度，降低系统的负载。注意：如果图片显示不正常把这个改成off。

  autoindex on; #开启目录列表访问，合适下载服务器，默认关闭。
  keepalive_timeout 120; # 长连接超时时间，单位s

  #FastCGI相关参数是为了改善网站的性能：减少资源占用，提高访问速度
  fastcgi_connect_timeout 300;
  fastcgi_send_timeout 300;
  fastcgi_read_timeout 300;
  fastcgi_buffer_size 64k;
  fastcgi_buffers 4 64k;
  fastcgi_busy_buffers_size 128k;
  fastcgi_temp_file_write_size 128k;

  #gzip模块设置
  gzip on;        #开启gzip压缩输出
  gzip_min_length 1k; #最小压缩文件大小
  gzip_buffers 4 16k; #压缩缓冲区
  gzip_http_version 1.0; #压缩版本（默认1.1，前端如果是squid2.5请使用1.0）开始压缩的http协议版本(可以不设置,目前几乎全是1.1协议)
  gzip_comp_level 2;   #推荐6压缩级别(级别越高,压的越小,越浪费CPU计算资源)
  gzip_types text/plain application/x-javascript text/css application/xml;
  #压缩类型，默认就已经包含text/html，所以下面就不用再写了，写上去也不会有问题，但是会有一个warn。
  gzip_vary on;   # 是否传输gzip压缩标志
  #limit_zone crawler $binary_remote_addr 10m; #开启限制IP连接数的时候需要使用

  upstream blog.ha97.com {
    #upstream的负载均衡，weight是权重，可以根据机器配置定义权重。weigth参数表示权值，权值越高被分配到的几率越大。
    server 192.168.80.121:80 weight=3;
    server 192.168.80.122:80 weight=2;
    server 192.168.80.123:80 weight=3;
  }

  #虚拟主机的配置
  server
  {
      #监听端口
      listen 80;
      #域名可以有多个，用空格隔开
      server_name www.ha97.com ha97.com;
      index index.html index.htm index.php;
      root /data/www/ha97;
      location ~ .*\.(php|php5)?$
      {
      fastcgi_pass 127.0.0.1:9000;
      fastcgi_index index.php;
      include fastcgi.conf;
      }
      #图片缓存时间设置
      location ~ .*\.(gif|jpg|jpeg|png|bmp|swf)$
      {
      expires 10d;
      }
      #JS和CSS缓存时间设置
      location ~ .*\.(js|css)?$
      {
      expires 1h;
      }
      #日志格式设定
      log_format access '$remote_addr - $remote_user [$time_local] "$request" '
      '$status $body_bytes_sent "$http_referer" '
      '"$http_user_agent" $http_x_forwarded_for';
      #定义本虚拟主机的访问日志
      access_log /var/log/nginx/ha97access.log access;

      #对 "/" 启用反向代理
      location / {
      proxy_pass http://127.0.0.1:88;
      proxy_redirect off;
      proxy_set_header X-Real-IP $remote_addr;
      #后端的Web服务器可以通过X-Forwarded-For获取用户真实IP
      proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
      #以下是一些反向代理的配置，可选。
      proxy_set_header Host $host;
      client_max_body_size 10m; #允许客户端请求的最大单文件字节数
      client_body_buffer_size 128k; #缓冲区代理缓冲用户端请求的最大字节数，
      proxy_connect_timeout 90; #nginx跟后端服务器连接超时时间(代理连接超时)
      proxy_send_timeout 90; #后端服务器数据回传时间(代理发送超时)
      proxy_read_timeout 90; #连接成功后，后端服务器响应时间(代理接收超时)
      proxy_buffer_size 4k; #设置代理服务器（nginx）保存用户头信息的缓冲区大小
      proxy_buffers 4 32k; #proxy_buffers缓冲区，网页平均在32k以下的设置
      proxy_busy_buffers_size 64k; #高负荷下缓冲大小（proxy_buffers*2）
      proxy_temp_file_write_size 64k;
      #设定缓存文件夹大小，大于这个值，将从upstream服务器传
      }

      #设定查看Nginx状态的地址
      location /NginxStatus {
      stub_status on;
      access_log on;
      auth_basic "NginxStatus";
      auth_basic_user_file conf/htpasswd;
      #htpasswd文件的内容可以用apache提供的htpasswd工具来产生。
      }

      #本地动静分离反向代理配置
      #所有jsp的页面均交由tomcat或resin处理
      location ~ .(jsp|jspx|do)?$ {
      proxy_set_header Host $host;
      proxy_set_header X-Real-IP $remote_addr;
      proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
      proxy_pass http://127.0.0.1:8080;
      }
      #所有静态文件由nginx直接读取不经过tomcat或resin
      location ~ .*.(htm|html|gif|jpg|jpeg|png|bmp|swf|ioc|rar|zip|txt|flv|mid|doc|ppt|pdf|xls|mp3|wma)$
      { expires 15d; }
      location ~ .*.(js|css)?$
      { expires 1h; }
  }

}


```
### 常用nginx配置demo

```
user  nginx nginx;
worker_processes  1;

error_log  /var/log/nginx/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

pid        /var/run/nginx/nginx.pid;

events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #隐藏Nginx版本信息，禁止网站目录浏览
    server_tokens off;
    autoindex off;
    #当FastCGI后端服务器处理请求给出http响应码为4xx和5xx时，就转发给nginx
    fastcgi_intercept_errors on;

    #关于fastcgi的配置
    fastcgi_connect_timeout 300;    
    fastcgi_send_timeout 300;    
    fastcgi_read_timeout 300;    
    fastcgi_buffer_size 64k;    
    fastcgi_buffers 4 64k;    
    fastcgi_busy_buffers_size 128k;    
    fastcgi_temp_file_write_size 128k;

    #支持gzip压缩
    gzip on;
    gzip_min_length 1k;
    gzip_buffers 16 64k;
    gzip_http_version 1.1;
    gzip_comp_level 6;
    gzip_types text/plain application/x-javascript text/css application/javascript text/javascript image/jpeg image/gif image/png application/xml application/json;
    gzip_vary on;
    gzip_disable "MSIE [1-6].(?!.*SV1)";

    #
    # 重定向所有带www请求到非www的请求
    #
    server {
        listen               *:80;
        listen               *:443 ssl spdy;
        server_name www.typecodes.com;
        # ssl证书配置见文章 https://typecodes.com/web/lnmppositivessl.html
        ssl_certificate /etc/nginx/ssl/typecodes.crt;
        # ssl密钥文件见文章 https://typecodes.com/web/lnmppositivessl.html
        ssl_certificate_key /etc/nginx/ssl/typecodes.key;
        # 不产生日志
        access_log off;

        # 访问favicon.ico和robots.txt不跳转（把这两个文件存放在上级目录html中）
        location ~* ^/(favicon.ico|robots.txt)$ {
            root html;
            expires max;
            log_not_found off;
            break;
        }

        location / {
            return 301 https://typecodes.com$request_uri;
        }
    }

    #
    # 将所有http请求重定向到https
    #
    server {
        listen               *:80;
        server_name          typecodes.com;
        # 不产生日志
        access_log off;

        # 访问favicon.ico和robots.txt不跳转（把这两个文件存放在上级目录html中）
        location ~* ^/(favicon.ico|robots.txt)$ {
            root html;
            expires max;
            log_not_found off;
            break;
        }

        location / {
            return 301 https://typecodes.com$request_uri;
        }
    }

    #
    # HTTPS server
    #
    server {
        listen               *:443 ssl spdy;
        server_name typecodes.com;

        # ssl证书配置见文章 https://typecodes.com/web/lnmppositivessl.html
        ssl_certificate /etc/nginx/ssl/typecodes.crt;
        # ssl密钥文件见文章 https://typecodes.com/web/lnmppositivessl.html
        ssl_certificate_key /etc/nginx/ssl/typecodes.key;
        ssl_session_cache shared:SSL:20m;
        ssl_session_timeout 10m;
        ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES256-GCM-SHA384:ECDHE-ECDSA-AES256-GCM-SHA384:DHE-RSA-AES128-GCM-SHA256:DHE-DSS-AES128-GCM-SHA256:kEDH+AESGCM:ECDHE-RSA-AES128-SHA256:ECDHE-ECDSA-AES128-SHA256:ECDHE-RSA-AES128-SHA:ECDHE-ECDSA-AES128-SHA:ECDHE-RSA-AES256-SHA384:ECDHE-ECDSA-AES256-SHA384:ECDHE-RSA-AES256-SHA:ECDHE-ECDSA-AES256-SHA:DHE-RSA-AES128-SHA256:DHE-RSA-AES128-SHA:DHE-DSS-AES128-SHA256:DHE-RSA-AES256-SHA256:DHE-DSS-AES256-SHA:DHE-RSA-AES256-SHA:AES128-GCM-SHA256:AES256-GCM-SHA384:AES128-SHA256:AES256-SHA256:AES128-SHA:AES256-SHA:AES:CAMELLIA:DES-CBC3-SHA:!aNULL:!eNULL:!EXPORT:!DES:!RC4:!MD5:!PSK:!aECDH:!EDH-DSS-DES-CBC3-SHA:!EDH-RSA-DES-CBC3-SHA:!KRB5-DES-CBC3-SHA:!CAMELLIA;
        ssl_protocols TLSv1 TLSv1.1 TLSv1.2; #enables TLSv1, but not SSLv2, SSLv3 which is weak and should no longer be used.
        ssl_prefer_server_ciphers on;
        # 开启spdy功能
        add_header Alternate-Protocol 443:npn-spdy/3.1;
        # 严格的https访问
        add_header Strict-Transport-Security "max-age=31536000; includeSubdomains;";

        #设置网站根目录
        root   /usr/share/nginx/html/typecodes;
        index  index.php index.html;

        charset utf-8;

        #access_log  /var/log/nginx/log/host.access.log  main;

        #设置css/javascript/图片等静态资源的缓存时间
        location ~ .*\.(css|js|ico|png|gif|jpg|json|mp3|mp4|flv|swf)(.*) {
            expires 60d;
        }

        # include /etc/nginx/default.d/*.conf;
        # 设置typecho博客的config文章不被访问，保证安全
        location = /config.inc.php{
            deny  all;
        }

        # keep the uploads directory safe by excluding php, php5, html file accessing. Applying to wordpress and typecho.
        # location ~ .*/uploads/.*\.(php|php5|html)$ {
        #   deny  all;
        # }

        # 设置wordpress和typecho博客中，插件目录无法直接访问php或者html文件
        location ~ .*/plugins/.*\.(php|php5|html)$ {
            deny  all;
        }

        #Rewrite的伪静态(针对wordpress/typecho)，url地址去掉index.php
        location / {
            if (-f $request_filename/index.html){
                rewrite (.*) $1/index.html break;
            }
            if (-f $request_filename/index.php){
                rewrite (.*) $1/index.php;
            }
        if (!-f $request_filename){
                rewrite (.*) /index.php;
            }
        }

        #访问favicon.ico时不产生日志
        location = /favicon.ico {
            access_log off;
        }

        #设置40系列错误的应答文件为40x.html
        error_page  400 401 402 403 404  /40x.html;
        location = /40x.html {
                root   html;
                index  index.html index.htm;
        }

        #设置50系列错误的应答文件为50x.html
        #
        error_page   500 501 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
            index  index.html index.htm;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # 设置Nginx和php通信机制为tcp的socket模式，而不是直接监听9000端口
        location  ~ .*\.php(\/.*)*$ {
             fastcgi_split_path_info ^(.+\.php)(/.+)$;
             #fastcgi_pass   127.0.0.1:9000;
             # the better form of fastcgi_pass than before
             fastcgi_pass   unix:/var/run/php-fpm/php-fpm.sock;
             fastcgi_index  index.php;
             fastcgi_param  SCRIPT_FILENAME  $document_root$fastcgi_script_name;
             include        fastcgi_params;
        }

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }
}
```
