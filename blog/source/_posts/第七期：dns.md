---
title: 第七期：dns
date: 2023-05-20 01:09:12
tags:
---
##### DNS分层结构
- 根DNS服务器：ROOT nameserver。本地域名服务器查不到结果时，则会向它查询顶级域名服务器ip。
顶级域名服务器：Tld nameserver。负责管理在该域名服务器注册的二级域名，例如.com是顶级域名服务器，向它查询时，可以返回example.com所在的权威域名服务器的ip地址。
- 权威域名服务器：authoritative nameserver。在特定区域内有唯一性，负责维护域名和ip的对应关系。如云解析DNS。
- 本地域名服务器：Local DNS。本地域名服务器是响应来自客户端的递归请求，并最终跟踪直到获取到解析结果的DNS服务器。例如用户本机自动分配的DNS、运营商ISP分配的DNS、谷歌8.8.8.8/114公共DNS等。

##### 域名解析过程
```
DNS查询的结果通常会在本地域名服务器中进行缓存，如果本地域名服务器中有缓存的情况下，则会跳过如下DNS查询步骤，很快返回解析结果。下面的示例则概述了本地域名服务器没有缓存的情况下，DNS查询所需的8个步骤：

1、用户在Web浏览器中输入“example.com”， 则由 本地域名服务器 开始进行递归查询。

2、本地域名服务器采用迭代查询的方法，向根域名服务器进行查询。

3、根域名服务器告诉本地域名服务器，下一步应该查询的顶级域名服务器.com TLD的IP地址。

4、本地域名服务器向顶级域名服务器.com TLD进行查询。

5、.com TLD服务器告诉本地域名服务器，下一步查询example.com权威域名服务器的IP地址。

6、本地域名服务器向example.com权威域名服务器发送查询。

7、example.com权威域名服务器告诉本地域名服务器所查询的主机IP地址。

8、本地域名服务器最后把查询的IP地址响应给web浏览器。

一旦DNS查询的8个步骤返回了example.com的IP地址，浏览器就能够发出对网页的请求：

9、浏览器向IP地址发出HTTP请求

10、该IP处的web服务器返回要在浏览器中呈现的网页
```
### 工具 dig 常用命令
```
dig +short time.geekbang.org # 直接输出域名对应A记录
dig +trace @114.114.114.114 +nodnssec time.geekbang.org # +trace表示开启跟踪查询# +nodnssec表示禁止DNS安全扩展；@114.114.114.114指定使用的dns服务器
```

### 示例
```
$ dig +trace @114.114.114.114 +nodnssec time.geekbang.org

; <<>> DiG 9.10.6 <<>> +trace @114.114.114.114 +nodnssec time.geekbang.org
; (1 server found)
;; global options: +cmd
.                       1791    IN      NS      b.root-servers.net.
.                       1791    IN      NS      h.root-servers.net.
.                       1791    IN      NS      m.root-servers.net.
.                       1791    IN      NS      d.root-servers.net.
.                       1791    IN      NS      a.root-servers.net.
.                       1791    IN      NS      e.root-servers.net.
.                       1791    IN      NS      k.root-servers.net.
.                       1791    IN      NS      f.root-servers.net.
.                       1791    IN      NS      i.root-servers.net.
.                       1791    IN      NS      l.root-servers.net.
.                       1791    IN      NS      g.root-servers.net.
.                       1791    IN      NS      j.root-servers.net.
.                       1791    IN      NS      c.root-servers.net.
;; Received 251 bytes from 114.114.114.114#53(114.114.114.114) in 30 ms

org.                    172800  IN      NS      a0.org.afilias-nst.info.
org.                    172800  IN      NS      a2.org.afilias-nst.info.
org.                    172800  IN      NS      b0.org.afilias-nst.org.
org.                    172800  IN      NS      b2.org.afilias-nst.org.
org.                    172800  IN      NS      c0.org.afilias-nst.info.
org.                    172800  IN      NS      d0.org.afilias-nst.org.
;; Received 448 bytes from 192.5.5.241#53(f.root-servers.net) in 154 ms

geekbang.org.           3600    IN      NS      dns9.hichina.com.
geekbang.org.           3600    IN      NS      dns10.hichina.com.
;; Received 96 bytes from 199.19.53.1#53(c0.org.afilias-nst.info) in 188 ms

time.geekbang.org.      600     IN      A       39.106.233.176
;; Received 62 bytes from 120.76.107.52#53(dns9.hichina.com) in 9 ms
```