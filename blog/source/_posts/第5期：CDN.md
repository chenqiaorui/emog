---
title: 第五期：CDN
date: 2023-05-20 00:21:56
tags:
---
### CDN介绍
Content Delivery Network 内容分发网络

原理：自有域名以CNAME方式绑定运营商提供对加速域名，CDN配置目标源站，获取静态资源返回给边缘节点。

以阿里云CDN为例:

- 添加域名
```
加速域名  a.example.com（自有域名）
业务类型  图片小文件
加速区域  仅中国内地

源站信息（以OSS为例）
  - 源站类型：oss
  - 域名：bucket.oss-cn-beijing.aliyun.com
  - 优先级：主
  - 端口：80
```
- 缓存配置
可以设置静态文件如png的过期时间，提高命中率
```
地址：jpg
类型：文件后缀名
过期时间：10分钟
```
- 访问控制
设置防盗链白名单，以实现身份验证（允许空refer）
```
localhost
a.b.com
*.example.com
```
- 刷新预热
```
刷新：删除cdn节点上的缓存数据，重新回源取数据
预热：从源站取数据存到cdn节点，提升用户体验
```

