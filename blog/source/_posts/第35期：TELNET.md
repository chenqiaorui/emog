---
title: 第35期：TELNET
date: 2023-05-22 17:35:10
tags:
---
#### telnet
本质是上一个远程登录工具，只不过是明文传输数据。

常用功能：
- 测试远程端口是否可用

centos7安装telnet
yum install -y telnet

如果返回`Connection refused`说明服务端口没有被占用，反之。

测试mysql是否可用
```
$ telnet localhost 3306
Trying ::1...
telnet: connect to address ::1: Connection refused
Trying 127.0.0.1...
Connected to localhost.
Escape character is '^]'.
R
5.5.65-MariaDB/+R<uX;Z?;fp"Ug%}k"bKmysql_native_passwordConnection closed by foreign host.
```
以上表示mysql服务可连。在`foreign host.`后面输入quit可退出 或在后面输入`info`可查返回细节。
