---
title: 第3期：Python学习
date: 2023-05-18 14:00:32
tags:
---

## Python编译器

- linux自带python2.7
- Windows 可以在Python官网 https://www.python.org/ 下载.exe

## 运行python
```
python --version 或 python3 --version

python3 # 进入交互环境，打印python版本
>>> import sys
>>> print(sys.version) 
```

## 编辑工具

[Visual Studio Code](https://code.visualstudio.com/)

[PyCharm](https://www.jetbrains.com/pycharm/download/)

## 编写文件及运行
编辑hello.py
```
print('hello python')
```
python hello.py # 执行

## 注释
```
"""
hello boy

Version: 0.1
Author: Ricky Chen
"""

# hello world

print('hello')
```

## 变量
```
a = 10
b = 10.23

print(a + b) # 20.23

c = 3
print(a / c) # 除法，3.3333333333333335
print(a % c) # 取余，1

d = 'hello, world'
e = True

print(type(a)) # 打印变量类型
```

## 变量类型转换和input使用
```
"""
输入a，b，转换成正数
%d表示整数占位符，进行a+b

Author: Ricky
"""
a = int(input('a = '))
b = int(input('b = '))

print('%d + %d = %d' % (a, b, a + b)) # 10 + 3 = 13
print('%d / %d = %d' % (a, b, a / b)) # 10 / 3 = 3，本来输出浮点数的，但是被%d转化了
print('%d // %d = %d' % (a, b, a // b)) # 10 // 3 = 3
```

## 赋值运算符
```
"""
复合赋值运算符
"""
a = 5
b = 2
a += b # 等价 a = a + b
print(a) # 7
a *= b # 等价 a = a * b
print(a) # 14
```
up https://github.com/jackfrued/Python-100-Days/blob/master/Day01-15/02.%E8%AF%AD%E8%A8%80%E5%85%83%E7%B4%A0.md