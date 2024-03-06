---
title: 第8期：shell
date: 2023-05-20 09:27:34
tags:
---
##### 数字比较符eq
```
只能比较数字，若字符则会被转成数字，如字符0不会等于数字0

-ne // 不等于
-gt // 大于
-eq // 等于
-lt // 小于

if [ 1 -eq 0 ];then
	echo "eq"
fi
```

##### 特殊变量
```
$0 当前脚本名称
$n 传递给脚本的第几个参数，如$1获取第一个参数
$# 传递给脚本的参数个数
$* 传递给脚本的所有参数
$? 上个命令的退出状态
$$ 当前shell的pid

# 示例
cd /optsafjlajdk
echo $?  // 切换到一个不存在的目录，返回1不正常状态
```

##### if/else
```
branch_name="test"

if [ ${branch_name} == "test" ];then
	echo "yes"
else
	echo "no"
fi
```

##### case
```
env=$1
case $env in
  release)
	echo "release"
	wait
	;;
  test)
	echo "test"
	wait
	;;
  *)
	echo "any"
	;;
esac;
```

##### 文件表达式
```
// -e 文件存在则为真
// -d 目录存在则为真
// -s 判断文件是否为空

if [ -s empty.log ];then
	echo "not emp"
else
	echo "emp"
fi

if [ -d test1 ];then
	echo "存在";
fi
```

##### 字符串表达式比较
```
a="a"
b="a"
c="c"
if [ $a = $b -o $a = $c ];then
        echo "eq"
fi

// Note: $a = $b 不能写成$a=$b
// -o 表逻辑或
// -a 表逻辑与
// ! 表逻辑非，如if [ $a != $c ]
```

##### for
```
for i in `seq 10`;do
	echo $i
done
```

##### sh -x 显示执行过程
```
例子1：
如有脚本test.sh，内容:

echo "hello"

执行sh -x test.sh，结果：

+ echo hell
hell
```
```
例子2：
echo "hell"

echo `dirname $PWD`  # 注意这条语句包含多个执行过程，打印结果的时候会有两个+号

执行后，
+ echo hell
hell
++ dirname /opt/test/process
+ echo /opt/test
/opt/test
```

```sh -n``` 
检查脚本语法

```time sh test.sh``` 
脚本执行时间

##### set
set -e      # 若指令传回值不为0，立即退出脚本

### linux常用命令
```
zip -qr yasuo.zip yasuo # -q变不显示压缩信息，-r 表递归压缩下面层级的目录或文件

cat access.log| awk '{print $4}'|sort | uniq -c| sort -nr # 排序

# 查找1天前以.gz结尾的日志，并批量删除
find /var/log/nginx/ -name "*.gz" -type f -mtime +1|xargs rm -f

## vim技巧
:set ff # 查看文本格式
:set binary # 改为unix格式
```