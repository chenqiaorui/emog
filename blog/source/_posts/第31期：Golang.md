---
title: 第31期：Golang
date: 2023-05-22 17:13:36
tags:
---
#### 源码安装
环境：centos7

```
cd /opt/go

wget https://storage.googleapis.com/golang/go1.18.3.linux-amd64.tar.gz

tar -xzvf go1.18.3.linux-amd64.tar.gz

# 编辑 /etc/profile，添加：
export PATH=$PATH:/opt/go/go/bin

保存退出，执行 source /etc/profile

go version 
```

#### 使用
```
cd /opt/go-demo

# vi hello.go

package main

import "fmt"

func main() {
  fmt.Println("hello world");
}

执行: go run hello.go // hello world

# := 的用法：左边有一个新变量，且变量不加var
var i = 5;
t := i;

# 标识符
命名变量名称
- 不能以数字开头

# 包别名
import(
    ff "fmt"
)

ff.Println();

# 布尔值
var b bool
b = true

# 整型
var i,j int
var i init8 // 8位整型

# 数组
var arr [10]int

# 结构类型 struct

type s struct {
    X int
    Y int
}

# 字符串
var s string = "go"

# 函数类型

```
参考：https://github.com/jaywcjlove/golang-tutorial#%E5%AE%89%E8%A3%85go
#### 基础知识
#### 变量创建的5种方式
方式一：
一行声明一个变量。
```
# 这种方式会隐式初始化。如string为空字符串，int为0，float为0.0，bool为false。
var <name> <type>，当然可以声明后初始化。
如：var a string = "helloworld"
```
说明：helloworld一定要双引号，表示字符串，单引号上rune类型。

方式二：
同时声明多个变量。
```
var (
  name string
  age int
  gender string
)
```

方式三：
推导声明
```
# 编译器根据右值类型推导出左边类型。限制：这种方式只能用于函数内部。
name := "helloworld" 
```
方式四：
声明和初始化多个变量。
```
name, age := "ricky", 11
```

方式五：
new(类型)函数，返回指针类型。变量分为普通变量(数据本身)和指针变量(数据内存地址，通过*接指针变量表示从内存地址取值) 
```
age := new(int)
```

#### 整数类型
共有16种，u开头的是无符号，没有负数。

int8 代表8bit，能表示-128～127之间的数字。

#### 数组
https://golang.iswbm.com/c01/c01_05.html

参考：https://golang.iswbm.com/c01/c01_02.html

### Go命令解释
- `go build` 编译源码文件以及它们的依赖包
比如，在`go build`后面不加任何代码文件，它将试图编译当前目录下对应的main.go。Windows系统会生成相应的.exe可执行文件。
- `go build -o main main.go` 指定编译后的可执行包名称为main
- `go env` 打印go语言环境变量

说明：
```
- GOBIN // 存放可执行文件的目录的绝对路径。
- GOARCH // 执行环境计算架构
- GOEXE // 可执行文件后缀
- GOHOSTOS // 执行环境操作系统
- GOPATH // 工作区目录的绝对路径。我们需要显式的设置环境变量GOPATH。如果有多个工作区，那么多个工作区的绝对路径之间需要用分隔符分隔。在windows操作系统下，这个分隔符为“;”。在其它操作系统下，这个分隔符为“:”。注意，GOPATH的值不能与GOROOT的值相同。

- GOROOT // Go语言的安装目录的绝对路径。GOROOT会是我们在安装Go语言时第一个碰到Go语言环境变量。它的值指明了Go语言的安装目录的绝对路径。但是，只有在非默认情况下我们才需要显式的设置环境变量GOROOT。这里所说的默认情况是指：在Windows操作系统下我们把Go语言安装到c:\Go目录下，或者在其它操作系统下我们把Go语言安装到/usr/local/go目录下。另外，当我们不是通过二进制分发包来安装Go语言的时候，也不需要设置环境变量GOROOT的值。比如，在Windows操作系统下，我们可以使用MSI软件包文件来安装Go语言
```
- `go install` 编译和安装文件及其依赖包，一定程度上等价于`go build`，但 `go install`可以指定编译后可执行文件的位置。

- `go get` 安装指定依赖。如 `go get -u github.com/chromedp/chromedp`。`-u`表示只会从网络上下载本地不存在的代码包，而不会更新已有的代码包。

- `go run` 编译和运行源码文件，不需要生成可执行文件即可执行。如`go run main.go`
