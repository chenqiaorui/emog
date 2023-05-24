---
title: 第16期：grep
date: 2023-05-22 10:12:57
tags:
---
例子：grep
```
grep "ricky" test.log   # 只取选中的字符所在行
grep -v "ricky" test.log  # 取不选中的行
grep -r "ricky" ./*   # 递归查找当前目录及子目录下的ricky
grep -e "rick*" test.log  # 开启正则匹配模式
grep -w 'ricky' txt                          # 精确匹配字符串，-w只会匹配单独存在的ricky，如文本里存在arickya不会被匹配到，但a ricky a 会
```