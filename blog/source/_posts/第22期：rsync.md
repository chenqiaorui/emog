---
title: 第22期：rsync
date: 2023-05-22 15:19:22
tags:
---
##### rsync 
remote sync: 远程同步工具，可以说本地复制或主机间复制。第一次复制是全部，第二次是同步有差别的文件。

##### 使用
-a  # 归档模式，文件递归传输
-v  # 详细模式输出
-z  # 对备份的文件传输时进行压缩
###### 本地复制
```
rsync -a remotesync /opt # 把remotesync(包括remotesync)复制到/opt目录下；如果remotesync写成remotesync/，结果是remotesync下面所有文件都被复制到/opt下
```

###### 本地复制到远程机器
rsync -avz remotesync root@192.168.31.166:/opt/test

###### 远程复制到本机
rsync -avz root@192.168.31.166:/opt/test/test.log /opt

参考：http://www.ruanyifeng.com/blog/2020/08/rsync.html
