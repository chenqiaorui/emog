---
title: 第14期：linux-jq
date: 2023-05-22 10:07:10
tags:
---
#### jq简介
jq，用来处理json数据的工具。

#### jq安装使用
centos安装
```
yum install -y jq
```

使用
```
# 获取一个键的值
echo '{"name":"ricky", "age":18}' |jq '.name'

# 获取数组数据
echo '[{"name": "flolunsa", "age": 12}, {"name": "ricky", "age": 27}]' | jq .[0]
echo '[{"name":"JSON", "good":true}, {"name":"XML", "good":false1}]' | jq '.[1]' # false不能写成false1

# 同时获取多个key的值
echo '{"name":"ricky", "age":18}' |jq '.name, .age'
```

#### 定义一个json
```
# 错误例子: 使用单引号
{
 "url": 'https://www.examples.com'
}

# 错误使用非十进制数据，json只能使用十进制 
{
  "foo": 0x123
}

# 正确例子
# 定义对象
{  
  "bar": "nisha",
  composition: {
    "a": 1,
    "name": "ricky"
  }  
}

```
参考：https://stedolan.github.io/jq/tutorial/
