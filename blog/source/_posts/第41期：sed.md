---
title: 第41期：sed
date: 2023-05-26 14:35:36
tags:
---
### demo
```
1. 把文件所有c1的文本变成d1
sed -i 's/c1/d1/g'

# 当替换字符存在特殊的转义字符，如`"`，可加`\`进行防转义
sed -i -e "s/sandbox_image = \"k8s.gcr.io\/pause:3.5\"/sandbox_image = \"registry.aliyuncs.com\/google_containers\/pause:3.4.1\"/g" /etc/containerd/config.toml
sed -i -e "s/runtime_type = \"io.containerd.runc.v2\"/runtime_type = \"io.containerd.runtime.v1.linux\"/g" /etc/containerd/config.toml
```
说明：
- `-e`表示使用可以在sed中执行多条语句，如`sed -e 's/wang/w/g;s/xu/x/g' user.txt`
- `-i`表示对文本文件进行字符替换，不加不会实际影响到文本
- 替换的语句最外部最好加双引号
- 替换的字符原本存在转义字符需要加\防止转义