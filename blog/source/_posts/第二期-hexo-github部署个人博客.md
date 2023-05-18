---
title: '第二期: hexo + github部署个人博客'
date: 2023-05-18 12:29:50
tags:
---
### 修改站点配置文件_config.yaml
```
# Deployment
## Docs: https://hexo.io/docs/one-command-deployment
deploy:
  type: git
  repo: https://github.com/chenqiaorui/chenqiaorui.github.io.git
  branch: master

```
### 安装hexo-Git部署插件
```
npm install hexo-deployer-git --save
```

### 推送
```
hexo clean 
hexo g 
hexo d
```