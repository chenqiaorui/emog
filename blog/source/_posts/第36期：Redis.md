---
title: 第36期：Redis
date: 2023-05-22 17:41:33
tags:
---
### 优缺点
```
# 优：
基于内存的kv数据库，每秒可达到10w次读写操作

# 缺：
不能存储大容量数据
```
#### 大key/热key
```
大Key: Key本身的数据量过大：一个String类型的Key，它的值为5 MB; 
      
      Key中的成员数过多：一个ZSET类型的Key，它的成员数量为10,000个; 
      
      Key中成员的数据量过大：一个Hash类型的Key，它的成员数量虽然只有1,000个但这些成员的Value（值）总大小为100 MB。

热Key: QPS集中在特定的Key：Redis实例的总QPS（每秒查询率）为10,000，而其中一个Key的每秒访问量达到了7,000。
       
      带宽使用率集中在特定的Key：对一个拥有上千个成员且总大小为1 MB的HASH Key每秒发送大量的HGETALL操作请求。

      CPU使用时间占比集中在特定的Key：对一个拥有数万个成员的Key（ZSET类型）每秒发送大量的ZRANGE操作请求。
```

#### redis-cli安装使用
```
Linux
# 下载安装redis-cli（下载Redis源码文件）

wget https://download.redis.io/releases/redis-6.0.9.tar.gz # more version see https://redis.io/download/?spm=a2c4g.11186623.0.0.10ec3783Qw3pBc
tar -xzvf redis-6.0.9.tar.gz

# 执行下述命令进入解压后的目录并编译安装Redis源码文件
cd redis-6.0.9

# 安装
make PREFIX=/usr/local/redis install
cd /usr/local/redis
cp redis.conf 6379.conf

# 启动
./bin/redis-server 6379.conf

# 测试
./bin/redis-cli
127.0.0.1:6379> ping
PONG

# 远程连接
redis-cli -h hostname -p port
# 密码用户认证
AUTH testaccount:Rp829dlwa
```
#### redis开发运维规范
```
1.设计合理的Key中Value的大小，推荐小于10 KB。过大的Value会引发数据倾斜、热点Key、实例流量或CPU性能被占满等问题，应从设计源头上避免此类问题带来的影响。
2.设计合理的Key名称与长度
使用可读字符串作为Key名，如果使用Key名拼接库、表和字段名时，推荐使用英文冒号（:）分隔。例如project:user:001。
长度：推荐Key名的长度不超过128字节（越短越好）。
```
### redis基本操作
```
# 查看所有key
keys * # 不建议在生产环境使用
keys h*

# 键数量
dbsize # 存在大量key的话生产环境禁止使用

exists key # 存在返回1，否则0

del key # 删除键，返回删除个数，若key不存在返回0

ttl key # 键存活时间，若key不存在返回-1

expire key seconds # 设置过期时间，成功返回1，失败返回0

persist key # 去掉过期时间

monitor # 实时监控redis请求信息

info # 查看节点信息
```
### 缓存雪崩
某一个热点数据过期，一时间请求都到
了DB导致雪崩。

解决：
- 1.过期时间避开业务高峰期；
- 2.热点数据不过期

### 缓存穿透
接口的参数是redis不存在的，请求打到了DB，db也找不到，肉鸡疯狂发请求导致db崩溃。

解决：
- ip黑名单和限流
- 接口参数校验

### 缓存雪崩
多数key失效，请求到了db导致崩溃

### 淘汰策略
```
共8种策略。
- allkeys-lru  淘汰最少用的key(应用最广泛)
- no-enviction 禁止驱逐数据，内存满时写数据会报错，默认策略。
```
### 删除策略
```
- 定时策略，设置key的同时设置时间
- 惰性删除，过期的key不清除，等访问的时候发现过期就删除。对cpu友好，但内存会累积。
- 定期删除，每隔100ms检查过期key删除

一般是惰性加定期删除策略。
```

### 持久化机制
```
- RDB 定时持久化
- AOF 实时持久化
```