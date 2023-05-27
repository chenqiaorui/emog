---
title: 第39期：strace
date: 2023-05-25 12:34:12
tags:
---
#### 介绍
strace: 跟踪用户空间的系统调用。系统调用又称系统呼叫（system call）,它是内核提供的一组抽象接口，内核直接运行于硬件之上。

linux内核目前有300多个系统调用函数，详细列表可用syscalls手册查看。主要分以下几类：
文件和设备访问类：open，write，read，chmod等
进程管理类：fork，clone，execve，exit，getpid
内存管理：brk/mmap/mlock
网络通信：sendmsg，socket


#### strace使用范例
```
# 追踪nginx启动打开的文件
strace -T -tt -f -e trace=file -o /data/log/strace.log -s 1024 nginx

输出内容：第一列为进程pid 第二列毫秒级别时间(-tt) 最后一列为系统调用时间（-T）
```

-T 显示系统调用话费时间
-tt 显示毫秒级别时间
-e trace=file 只显示与文件访问相关；trace=process 与进程管理相关；trace=ipc只显示与进程间通信相关; network 和网络通信调用相关；desc 和文件描述符相关
-p 指定追踪进程

进程间通信设计共享内存：ipcs -m # 查看共享内存

##### 总结
进程异常可通过strace追踪，进程若卡死在用户态strace则不适用。尝试用perf，systemtap，它们可探查内核态。


```
root@ubuntu:/usr# strace cat /dev/ 
execve(, [, ], []) = 
brk()                                  = 
access(, F_OK)      = - ENOENT (No such file or directory)
mmap(NULL, , PROT_READ|PROT_WRITE, MAP_PRIVATE|MAP_ANONYMOUS, -, ) = 
access(, R_OK)      = - ENOENT (No such file or directory)
...
brk() = 
brk() = 
fstat(, {st_mode=S_IFCHR|, st_rdev=makedev(, ), ...}) = 
open(, O_RDONLY) = 
fstat(, {st_mode=S_IFCHR|, st_rdev=makedev(, ), ...}) = 
read(, , ) = 
close() = 
close() = 
close() = 
exit_group() = ?
```
解析上面输出：每一行是一次系统调用，等号左边是系统调用函数名，右边是函数返回值。

参考：https://www.cnblogs.com/machangwei-8/p/10388883.html