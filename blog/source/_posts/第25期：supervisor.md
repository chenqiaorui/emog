---
title: 第25期：supervisor
date: 2023-05-22 15:38:25
tags:
---
#### supervisor启动查找supervisord.conf配置文件顺序
```
$CWD/supervisord.conf -> $CWD/etc/supervisord.conf -> /etc/supervisord.conf     # 优先级由高到低

supervisord -c /etc/supervisord.conf
supervisorctl -c /etc/supervisord.conf # 直接指定
```

#### supervisor作用？
集中管理子进程

#### supervisor的组件
```
supervisord          # supervisor的服务端
supervisorctl        # supervisor的客户端
```

##### 配置文件详解
```
[unix_http_server]            
file=/tmp/supervisor.sock   ; socket文件作用：supervisorctl与supervisord(服务端)通信
;chmod=0700                 ; socket文件权限
;username=user              ; 使用supervisorctl时候的认证用户，默认不认证
;password=123

[supervisord]               ; 定义supervisord服务端运行时的参数
logfile=/tmp/supervisord.log; 定义supervisord主进程
logfile_maxbytes=50MB       ; 设置文件最大；超过50M则生成新的文件
pidfile=/tmp/supervisord.pid; pid文件
nodaemon=false              ; 以守护进程运行

[supervisorctl]              ;这个主要是针对supervisorctl的一些配置  
serverurl=unix:///tmp/supervisor.sock ; 这个是supervisorctl本地连接supervisord的时候，本地UNIX socket路径，注意这个是和前面的[unix_http_server]对应的；默认值就是unix:///tmp/supervisor.sock

[program:create_elastic_index]             
command                 = /opt/php/bin/php /data/website/clou/artisan queue:work --queue=create_elastic_index --tries=3 --daemon    ;  command不能是后台进程
directory               = /data/website/cloer-v2                                                                                    ; 进程运行前会切换到此目录
process_name            = %(program_name)s_%(process_num)s
priority                = 999            ; 进程启动优先级，优先级低的先启动
numprocs                = 2
autostart               = true
autorestart             = true            ; 如果为unexpected，只有当进程的退出码不在下面的exitcodes里面定义的退出码时才会被重启；true表进程只要挂掉就无条件重启。
;exitcodes              = 0,2
stdout_logfile          = /data/website/cloudedr-v2/storage/logs/supervisor-clip_crdex.log
stdout_logfile_maxbytes = 10MB
stderr_logfile          = /data/website/cloudeder-v2/storage/logs/supervisor-clip_crdex.log
stderr_logfile_maxbytes = 10MB
umask=002
user=nobody
```