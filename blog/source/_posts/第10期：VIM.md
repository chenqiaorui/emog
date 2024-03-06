---
title: 第十期：VIM
date: 2023-05-20 18:50:15
tags:
---
### 常见命令
```
:set ff # 查看文件文本格式
:set binary # 设置成unix格式
注释：ctrl+v 根据光标选中要注释的行，按下I，输入#，esc退出编辑。
取消：ctrl+v 根据光标选中要取消的行，按下s，esc退出编辑。
ctrl+r # 取消撤销
:set list # 可以显示一些符号，$出现在行结尾，代表换行；^I 代表tab键；空格依旧是空格
:%s/str/newstr/g       # 全部替换
```