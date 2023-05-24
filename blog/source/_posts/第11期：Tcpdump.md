---
title: 第11期：Tcpdump
date: 2023-05-21 00:40:50
tags:
---
### 常用命令
```
# tcpdump 抓指定post请求并存放到文件
nohup tcpdump -s 0 -A -vv 'tcp[((tcp[12:1] & 0xf0) >> 2):4] = 0x504f5354' | grep -C 100 "/api/screenshot" >> file.log &
```

### TCP
```
三次握手：
1.张三发送一个数据包，设置SYN=1，表明想要建立连接，设置seq=x来注释第一个字节包编号；
2.李四收到了，发送一个数据包，设置ACK=1，表明他收到了数据包，设置ack=x+1，期待收到下一个数据包；同时，他想要建立通信连接，设置seq=y；
3.张三设置ack=y+1给李四，表明自己收到了李四的包。
```
#### wireshark过滤规则
```
ip.src == 192.168.1.189 # 根据源ip匹配
ip.dst == 192.168.1.189 # 根据目的ip匹配
ip.addr == 192.168.1.189 # 根据源或目的ip匹配

http # 根据协议匹配

tcp.port == 80 # 根据端口匹配

http.response.code==200 # 根据状态码匹配

http.request==1 # 匹配所有请求包

http.response==1 # 匹配所有相应包
http.request.method==GET # 匹配GET请求
```
#### tcpdump抓包分析https
案例设计：
```
在centos7机器上，执行`tcpdump -i any -vvv tcp -w /tmp/json.pcap`监听tcp请求;

centos7新起一个窗口，执行`curl https://json.im/`

切换回tcpdump的窗口，`ctrl + c` 停止监听。

执行`tcpdump -r /tmp/json.pcap`查看pcap包 或者使用`wireshark`查看。
```
说明：
- `tcpdump -i any -vvv tcp -w /tmp/json.pcap` 中 `-i any`监听所有网口；`-vvv`获取更多详细信息；`tcp`抓取tcp请求。

- 由于抓取的是https协议，报文数据全是被加密的。
