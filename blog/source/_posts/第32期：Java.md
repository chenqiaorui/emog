---
title: 第32期：Java
date: 2023-05-22 17:14:55
tags:
---
#### 安装
环境: centos7

OpenJDK1.8下载地址：https://jdk.java.net/java-se-ri/8-MR3

OpenJDK9以上版本下载地址：http://jdk.java.net/archive/

安装JDK8
```
cd /opt/java

wget https://download.java.net/openjdk/jdk8u42/ri/openjdk-8u42-b03-linux-x64-14_jul_2022.tar.gz

tar -xzvf openjdk-8u42-b03-linux-x64-14_jul_2022.tar.gz

mv java-se-8u42-ri  java8

# vi /etc/profile

export JAVA_HOME="/opt/java/java8"
export JRE_HOME="/opt/java/java8/jre"
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib:$CLASSPATH
export PATH=$JAVA_HOME/bin/:$PATH

source /etc/profile

java -version
```
参考：https://blog.csdn.net/zxbfriend/article/details/117842392

#### 使用
```
cd /opt/java-demo
vi HelloWorld.java

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}

javac HelloWorld.java # 编译生成.class
java HelloWorld # 执行
```
