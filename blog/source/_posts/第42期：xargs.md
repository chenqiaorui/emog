---
title: 第42期：xargs
date: 2023-05-26 14:39:40
tags:
---
### xargs作用
接收管道符传递过来的数据，并将数据作为下一个命令的参数

### 用法
用法1
```ps aux|grep code|awk '{ print $2 }'|xargs```
输出：pid1 pid2          # xargs 等价于 xargs，xargs会把空白和换行替换成空格

用法2
```ps aux|grep code|awk '{ print $2 }'|xargs -I {} kill {}```
xargs -I 指定替换字符串为{}，{}代表着管道传递的参数，后面再使用{}会替换成相应参数值。
