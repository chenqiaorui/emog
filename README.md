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