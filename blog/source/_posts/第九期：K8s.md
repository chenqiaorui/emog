---
title: 第九期：K8s
date: 2023-05-20 14:43:05
tags:
---
### 资源对象
- 无状态 Deployment
```
动态伸缩pod，实现高可用；
健康检查；
优雅停止
```

- 有状态 StatefulSet
```
pod名称、ip固定
```

- 守护进程集 DaemonSet
```
```

- 服务 Service
```
```

- 路由 Ingress
```
```

- 配置项 ConfigMap
```
```

- 保密字典 Secert
```
```

- 存储声明 PersistentVolumeClaim
```
```

- 存储卷 PersistentVolume
```
```

- 存储类 StorageClass
```
```
### 常用命令
```
# 复制pod文件到本地，范例：
kubectl cp -c supervisord namespace/pod-name-8bf7c9d8-dj2gg:/var/www/html/storage/logs/analysis.log ./analysis-medias.log

# 复制本地文件到pod，范例：
kubectl cp ./xxbbxg.csv -c php-fpm namespace/pod-name-55967f6dd6-jmrk4:/var/www/html/storage/import/xxbbxg.csv
```