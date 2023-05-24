---
title: 第17期：ssh
date: 2023-05-22 10:14:20
tags:
---
#### SSH-远程连接工具
```
ssh root@192.168.1.2 -p 22
ssh-keygen # 生成`id_rsa.pub`和 `id_rsa.private`
/etc/ssh/sshd_config # 配置文件入口，可以修改端口等
systemctl status sshd # 服务管理