---
title: 第18期：linux性能
date: 2023-05-22 10:15:30
tags:
---
##### 一、什么是性能问题？
```
突然有一天，公司开发在夜里打电话给你，“现在有很多用户说系统弹出一个错误，系统繁忙”之类的。
开发同事一看接口返回500，看了后端日志好像没有特别明显的报错信息。

这时，你赶忙上服务器看下nginx日志，好家伙，一看nginx日志发现后发现接口请求过了60s还没有响应。
`top`看一下服务器资源，用户空间的cpu使用率100%，是哪些进程呢？php-fpm。里面发生了什么？我不知道啊？
唉，重启服务能解决90%的问题，那就重启看看吧。果然，重启后用户反馈正常了。害，暂时是没有问题了，那下次再来一次怎么办？...无后续
```

看，cpu100%这类问题就归属性能问题。

##### 研究性能问题需要学习操作系统主要组件原理
CPU性能、内存、磁盘IO性能、网络

衡量性能的指标：
并发（吞吐）、响应快（时延）

##### 二、CPU性能篇

##### 什么是平均负载
看一个例子：`uptime`
```
$ uptime
02:34:03 up 2 days, 20:14,  1 user,  load average: 0.63, 0.83, 0.88
```
说明：
- `02:34:03` 当前时间
- `up 2 days, 20:14` 系统从开机后运行的时长
- `1 user` 正在登录的用户数
- `load average: 0.63, 0.83, 0.88` 最近1分钟、5分钟、15分钟的平均负载(Load average)

`平均负载`是指单位时间内，系统处于可运行(Running或ready)和不可中断(blocked)的进程数。结合最近1分钟、5分钟、15分钟的平均负载，我们可以全面了解cpu的使用情况。像了解一天早中晚的气候变化。

Running是指正在使用cpu；Ready是指代码(指令)已经加载到了内存，等cpu来执行指令；

不可中断是指进程在使用cpu，突然需要进行磁盘IO(读写)的长时间操作，先不用cpu，等IO操作完毕再回来使用cpu。

一般而言，平均负载超过cpu核数70%就要检查cpu的使用情况，考虑是否优化。

查看cpu核数
```
grep 'model name' /proc/cpuinfo|wc -l
```

##### 什么是CPU使用率
cpu使用率衡量了单位时间cpu的繁忙程度。

对于I/O密集型进程，平均负载就高，但cpu却不繁忙，也就是cpu使用率不高。

看一个例子：`top`
```
$ top
top - 11:15:27 up 40 days, 51 min,  1 user,  load average: 0.32, 0.27, 0.26
Tasks: 356 total,   1 running, 355 sleeping,   0 stopped,   0 zombie
%Cpu(s):  4.2 us,  5.6 sy,  0.0 ni, 90.3 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
MiB Mem :   7777.8 total,    281.6 free,   4394.8 used,   3101.3 buff/cache
MiB Swap:   2048.0 total,    580.2 free,   1467.8 used.   3015.1 avail Mem 

    PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND                                                                                                                 
3811294 root      20   0   15440   4540   3768 R  20.0   0.1   0:00.03 top                                                                                                                     
    682 avahi     20   0   10524   5696   3120 S   6.7   0.1 110:29.16 avahi-daemon                                                                                                            
   7726 root      20   0  750872  21192   5772 S   6.7   0.3 259:56.50 travel-api  
```
说明：
- `%Cpu(s)` 比如说有4个cpu，%Cpu(s)代表这4个的平均使用率。

  cpu = 用户空间使用率(us) + 内核空间使用率(sy) + 空闲(id)

  `ni` 用户空间通过改变进程优先级占用的cpu百分比

  `wa` 等待io操作占用的cpu百分比

  `hi/si` 硬/软中断进行cpu上下文切换占用的百分比

- `RES` 使用的真实物理内存（KB）
- `%CPU` 一个cpu的使用率，毕竟一个进程只占用一个cpu
- `TIME+` 累计使用cpu时间

附top使用快捷键说明：
- `shift + m` # 按照内存使用率排序，shift m 等价于大写M
- `shift + p` # 按照cpu使用率排序
- `c` # 显示命令全路径
- `F` # 挑选你要选择展示的列，按下空格选中，* 代表会展示的列(列会出现在最后)，按q退出。
    可以展示进程使用哪一个cpu
- 按`1` # 展示每个cpu的使用情况
