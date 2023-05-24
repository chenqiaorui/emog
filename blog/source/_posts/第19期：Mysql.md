---
title: 第19期：Mysql
date: 2023-05-22 12:40:52
tags:
---
#### 连接数据库
mysql -h127.0.0.1 -P3306 -uroot -prootpassword

#### 查看数据库
show databases;

#### 选择数据库
use test;

### 表结构系列操作
#### 添加字段
ALTER TABLE user
ADD age int(3); 

#### 创建索引
CREATE INDEX idx_name
ON user (name);

### 数据系列操作
#### 插入
INSERT INTO user
VALUES (10, 'root', 'root', 'xxxx@163.com');

#### 更新
UPDATE user
SET username='robot', password='robot'
WHERE username = 'root';

#### 删除
DELETE FROM user
WHERE username = 'robot'; // 删除行

TRUNCATE TABLE user; // 清空表

#### 查询
SELECT * FROM mytable LIMIT 0, 5;

### MySQL 主从复制原理
```
mysql主从复制可以实现负载，读写分离，master主要负责写，node负责读。

主从复制类型：
- 主从同步：master和node都写完才通知用户
- 主从异步：master一写完就通知用户
- 主从半同步：master和任一个node写完就通知用户

主从复制原理：
- master需开启了二进制日志跟踪，node服务器通知master：我现在读到了最新的更新位置，然后封锁继续等待master更新通知。

主从复制具体过程：
1/ node启动2个线程，一个IO，另一个sql线程；
2/ IO线程去请求master的binlog日志，且将binlog写到redo log(中继日志)；master特地开了一个log dump进程传输binlog。
3/ node的sql进程用来读redo log，解析成insert等具体操作执行。

```
## Mysql主备搭建
```
mkdir /soft && mkdir /soft/mysql/
cd /soft/mysql/

wget https://dev.mysql.com/get/Downloads/MySQL-8.0/mysql-8.0.20-linux-glibc2.12-x86_64.tar.xz

tar -xvJf mysql-8.0.20-linux-glibc2.12-x86_64.tar.xz
mv mysql-8.0.20-linux-glibc2.12-x86_64 mysql8.0

# 卸载内核自带maridb
rpm -qa | grep mariadb
rpm -e --nodeps mariadb-libs-5.5.56-2.el7.x86_64

cd mysql8.0 && mkdir data

# 配置环境变量
vi /etc/profile

export PATH=$PATH:/soft/mysql/mysql8.0/bin:/soft/mysql/mysql8.0/lib
source /etc/profile

# 创建用户和用户组
groupadd mysql
useradd -g mysql mysql
chown -R mysql.mysql /soft/mysql/mysql8.0

# 初始化mysql并获临时密码
cd /soft/mysql/mysql8.0/bin
./mysqld --user=mysql --basedir=/soft/mysql/mysql8.0 --datadir=/soft/mysql/mysql8.0/data/ --initialize

# mysql启动默认读取/etc/my.cnf

[mysqld]
basedir=/soft/mysql/mysql8.0
datadir=/soft/mysql/mysql8.0/data

port=3306
socket=/tmp/mysql.sock
character-set-server=UTF8MB4

# 编辑启动模板
cd /soft/mysql/mysql8.0
vi support-files/mysql.server

basedir=
datadir=
  
  替换成：

basedir=/soft/mysql/mysql8.0
datadir=/soft/mysql/mysql8.0/data

# 通过启动脚本启动服务
[root@localhost]# ./support-files/mysql.server start
Starting MySQL.Logging to '/soft/mysql/mysql8.0/data/localhost.localdomain.err'.
.. SUCCESS!

# 查看进程
ps aux | grep mysql

# 临时密码登录并修改密码
mysql -uroot -p

mysql> show databases;
ERROR 1820 (HY000): You must reset your password using ALTER USER ...

alter user 'root'@'localhost' identified with mysql_native_password by '123456';

# 自制mysql启动服务
./support-files/mysql.server stop

cp -a ./support-files/mysql.server /etc/init.d/mysql

chmod +x /etc/init.d/mysql

# 通过系统etc目录下的启动文件创建系统服务
[root@localhost]# chkconfig --add mysql

service mysql status

# 设置能远程root连接
mysql> use mysql;

# 直接通过update修改系统用户表
mysql> update user set host="%" where user="root";

# 刷新一下所有用户的权限信息
mysql> flush privileges;


change master to master_host='192.168.31.87',
       master_user='ricky',
       master_password='123456',
       master_port=3306,
       master_log_file='mysql-bin-log.000005',
       master_log_pos=3339;

# 创建用户，@指明任意客户端可登录
create user 'ricky'@'%' identified with mysql_native_password by '123456';
```
### 会话连接
```
# 查看客户端连接状态，客户端如果太长时间没动静，连接器就会自动将它断开。这个时间是由参数 wait_timeout 控制的，默认值是 8 小时。
show processlist;
```
### 授权
```
GRANT SELECT, INSERT ON test.user TO 'pig'@'%';
GRANT ALL ON *.* TO 'pig'@'%';
GRANT ALL ON maindataplus.* TO 'pig'@'%';

# 让用户可以授权
GRANT privileges ON databasename.tablename TO 'username'@'host' WITH GRANT OPTION;

# 撤销授权
REVOKE privilege ON databasename.tablename FROM 'username'@'host';
```

### 更改用户密码
```
SET PASSWORD FOR 'username'@'host' = PASSWORD('newpassword');
```

### 备份与恢复
```
mysqldump 将数据库中的数据备份成一个文本文件，表的结构和表中的数据将存储在生成的文本文件中。

# 备份
mysqldump -u <username> -p <database> [<table1> <table2> ...] > backup.sql

# 恢复
mysql -u <username> -p <database> < backup.sql
```

### 参考
```
https://juejin.cn/post/6844904021497479176
```