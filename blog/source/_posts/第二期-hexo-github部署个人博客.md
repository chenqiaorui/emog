---
title: '第二期: hexo + github部署个人博客'
date: 2023-05-18 12:29:50
tags:
---
### github创建项目
```
仓库名称: ${username}.github.io # username是github账号名称
```

### 参考第一期用hexo创建了项目blog，修改站点配置文件_config.yaml
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
hexo clean # 删除public目录
hexo g # 创建静态资源public目录
hexo d # 部署到远程chenqiaorui.github.io项目
```
说明：
### 绑定域名

第一步：dns服务器设置别名：mydomain.cn  CNAME chenqiaorui.github.io

第二步: 登录GitHub，进入之前创建的仓库，点击settings，设置Custom domain，输入 mydomain.cn 域名

### 更换主题

将Next主题下载到blog目录的themes主题下的next文件夹中
```
git clone https://github.com/iissnan/hexo-theme-next themes/next
```

打开站点的_config.yml配置文件，修改主题为next。
```
theme: next
```

next主题有很多样式，可以用Pisces、Muse、Mist，在themes/next目录下，打开_config.yaml，配置：
```
scheme: Pisces # 选用Pisces样式
```