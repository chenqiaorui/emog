---
title: 第30期：Python
date: 2023-05-22 17:03:52
tags:
---
##### 1.6.1 缩进
错误：
```
a = 1
 b = 2
```

正确：
```
a = 1
b = 1
```

##### 1.6.2 print 打印函数
```
print("hello")
```

python3 可以这么写:
```
print "Hello world"
```
##### 1.6.3 数学运算
```
a = 1
b = 2
surface = a * b 

a * b * b  等价于 a * b**2

```
##### 1.6.4 String
3种表示方法
```
message = 'hello'
message = "hello"
message = """hello world"""  or message = '''hello world'''   # 可以表示多行字符
```

##### 1.6.5 print(2)
a = "world"
b = "tony"
print("hello %s" % a)  # 打印hello world
print("hello %s and %s" % (a,b))

##### 1.6.6 module 模块
```
# 导入整个模块
import math     # 等价于导入整个文件(模块)，接着就可以用所有的变量和函数。
math.pi
```

```
# 选择性地导入
from math import pi
surface = pi * R**2
```

```
# 导入所有
from math import * # 这种导入可能会覆盖自定义的变量
```
##### 1.6.6 Function 函数
```
from math import pi
def compute_surface(radius, pi=math.pi):
    return pi* radius * radius
```
##### 1.6.7 list 列表 
长度可变
```
a = [1, 2]
print(a[0])

b = a  # 数组复制
print(b[0])
```

##### 1.6.8 Tuples 数组
长度不变
```
a = (1,2)
print(a[0])
```

##### 1.6.9 Dicts 字典
可变键值对
```
a = {'key1': [1, 'e', '2'], 'key2': 2}
print(a['key2'])
```
##### Slicing 切割
```
a = [1, 2, 3, 4] 等价 a = range(0,4)
a[0]       # 1
a[-1]      # 等价于a[3]

```

##### 1.6.10 for & if  遍历和判断
```
a = [1, 2, 3]
for i in a:
    if i == 1:
        print ("i 是 1")
    elif i == 2:
        print ("i = %s" % i)
    else:
        print ("i is 3")

```
##### 1.6.11 类初始化函数
```
class Test():
    def __init__(self):
        print("you are a bad boy")

Test()
```
例子2：带参
class Test():
    def __init__(self, arg1, arg2=2):
        self.arg1 = arg1
        self.__arg2 = arg2

c1 = Test(1)
c2 = Test(2, arg2=3)

##### 1.6.12 Exception 异常处理
```
try:
    1/0
except Exception as e:
    print("1不能除以0")
    print(e)
    print("pass is ignore your error")
    pass  
finally:
    print("final")
```

##### 1.6.13 lambda 匿名函数
```
from math import pi
  
f = lambda r: pi * r
print(f(2))
```
其中r表参数，f调用lambda函数

例子2：
```
lambda x,y: return x**y 
```
使用两个参数

##### 1.6.14 boolean 
```
a = False
b = True

a == b  # False
```

更多基础知识参考：https://thomas-cokelaer.info/tutorials/python/basics.html
