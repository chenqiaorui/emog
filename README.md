## emog
EMOG is emog zzzzzzzzzzzzz.

```
本项目用于分享互联网知识。
```
## 创建博文和发布
```
# 切换目录
cd blog

# 依赖
npm install

# cli安装
参考[博客框架](https://hexo.io/zh-cn/)

npm install hexo-cli -g
# 运行
hexo server

# 创建博文
hexo new "第一期：页面"

# 发布
npm install hexo-deployer-git --save

hexo clean 

hexo deploy

```

### 一键提交并发布

cd .. ; git add . ; git commit -m"update" ; git push ; cd blog ; hexo clean ; hexo deploy

### issue

Q: 1.首页文章内容长度
hexo-excerpt

A: npm install hexo-excerpt --save

_config.yml添加内容：

```
#自动在首页对文章进行摘要描述作为前言文本
excerpt:
  depth: 3 #摘要的内容多少
  excerpt_excludes: []
  more_excludes: []
  hideWholePostExcerpts: true
```

