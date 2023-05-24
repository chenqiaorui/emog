---
title: 第24期：DOCKER
date: 2023-05-22 15:36:59
tags:
---
#### 安装docker-ce
```
# 安装一些必要的系统工具
sudo yum install -y yum-utils device-mapper-persistent-data lvm2

# 添加源信息
# docker 官方源
sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
# 阿里云源
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

# 安装前可以先更新 yum 缓存：
sudo yum makecache fast
# 安装 Docker-ce
sudo yum install docker-ce

# 如果你想安装特定 docker-ce 版本，先列出 repo 中可用版本，然后选择安装
$ yum list docker-ce --showduplicates | sort -r
# docker-ce.x86_64       18.06.1.ce-3.el7              docker-ce-stable
# docker-ce.x86_64       18.06.1.ce-3.el7              @docker-ce-stable
# docker-ce.x86_64       18.06.0.ce-3.el7              docker-ce-stable
# docker-ce.x86_64       18.03.1.ce-1.el7.centos       docker-ce-stable
# docker-ce.x86_64       18.03.0.ce-1.el7.centos       docker-ce-stable
# docker-ce.x86_64       17.12.1.ce-1.el7.centos       docker-ce-stable
# 选择版本安装
$ sudo yum install docker-ce-<VERSION STRING>

# 选择安装 docker-ce-18.06.1.ce
$ sudo yum install docker-ce-18.06.1.ce

# 启动 Docker 后台服务
sudo systemctl start docker

# 通过运行 hello-world 镜像，验证是否正确安装了 docker
docker run hello-world
```

#### 命令介绍
```
$ docker --help

管理命令:
  container   管理容器
  image       管理镜像
  network     管理网络
命令：
  attach      介入到一个正在运行的容器
  build       根据 Dockerfile 构建一个镜像
  commit      根据容器的更改创建一个新的镜像
  cp          在本地文件系统与容器中复制 文件/文件夹
  create      创建一个新容器
  exec        在容器中执行一条命令
  images      列出镜像
  kill        杀死一个或多个正在运行的容器    
  logs        取得容器的日志
  pause       暂停一个或多个容器的所有进程
  ps          列出所有容器
  pull        拉取一个镜像或仓库到 registry
  push        推送一个镜像或仓库到 registry
  rename      重命名一个容器
  restart     重新启动一个或多个容器
  rm          删除一个或多个容器
  rmi         删除一个或多个镜像
  run         在一个新的容器中执行一条命令
  search      在 Docker Hub 中搜索镜像
  start       启动一个或多个已经停止运行的容器
  stats       显示一个容器的实时资源占用
  stop        停止一个或多个正在运行的容器
  tag         为镜像创建一个新的标签
  top         显示一个容器内的所有进程
  unpause     恢复一个或多个容器内所有被暂停的进程
```

#### 镜像管理
```
docker pull centos:latest  # 从docker.io中下载centos镜像到本地
docker images              # 查看已下载的镜像
docker rmi [image_id]      # 删除镜像，指定镜像id

# 删除所有镜像
# none 默认为 docker.io
docker rmi $(docker images | grep none | awk '{print $3}' | sort -r)

# 连接进行进入命令行模式，exit命令退出。
docker run -t -i nginx:latest /bin/bash
```

##### 通过容器创建镜像
两种方式：
1.从已经创建的容器中更新镜像，并且提交这个镜像
2.使用 Dockerfile 指令来创建一个新的镜像

```
# 第一种
docker commit -m="First Docker" -a="wcjiang" a6b0a6cfdacf wcjiang/nginx:v1.2.1


# 第二种
touch Dockerfile .dockerignore
```
.dockerignoren内容（用于忽略加入镜像的内容）
```
.git
node_modules
npm-debug.log
```
Dockerfile内容
```
FROM node:8.4
COPY . /app
WORKDIR /app
RUN npm install --registry=https://registry.npm.taobao.org
EXPOSE 3000
```
##### 创建镜像
docker build -t koa-demo .

##### 发布镜像
docker tag wcjiang/nginx:v1.2.1 wcjiang/nginx-test:lastest
docker login
docker push wcjiang/nginx-test:lastest

##### 镜像安装软件
```
# 第一次需要运行这个命令，确保源的索引是最新的
# 同步 /etc/apt/sources.list 和 /etc/apt/sources.list.d 中列出的源的索引
apt-get update
# 做过上面更新同步之后，可以运行下面的命令了
apt-get install vim
```

##### 容器管理
```
# 列出本机正在运行的容器
docker container ls
# 列出本机所有容器，包括终止运行的容器
docker container ls --all
docker start [containerID/Names] # 启动容器
docker stop [containerID/Names]  # 停止容器
docker rm [containerID/Names]    # 删除容器
docker logs [containerID/Names]  # 查看日志
docker exec -it [containerID/Names] /bin/bash  # 进入容器

# 从正在运行的 Docker 容器里面，将文件拷贝到本机，注意后面有个【点】拷贝到当前目录
docker container cp [containID]:[/path/to/file] .

docker run centos echo "hello world"  # 在docker容器中运行hello world!
docker run centos yum install -y wget # 在docker容器中，安装wget软件
docker ps                             # 列出包括未运行的容器
docker ps -a                          # 查看所有容器(包括正在运行和已停止的)
docker logs my-nginx                  # 查看 my-nginx 容器日志

docker run -i -t centos /bin/bash     # 启动一个容器
docker inspect centos                 # 检查运行中的镜像
docker commit 8bd centos              # 保存对容器的修改
docker commit -m "n changed" my-nginx my-nginx-image # 使用已经存在的容器创建一个镜像
docker inspect -f {{.State.Pid}} 44fc0f0582d9        # 获取id为 44fc0f0582d9 的PID进程编号
# 下载指定版本容器镜像
docker pull gitlab/gitlab-ce:11.2.3-ce.0
```

##### 文件拷贝
```
从主机复制到容器 sudo docker cp host_path containerID:container_path
从容器复制到主机 sudo docker cp containerID:container_path host_path
```

##### Docker私有仓库搭建
```
Harbor 还有 humpback 和 rancher
```

##### 临时运行服务
```
docker run -d \
  -p 5000:5000 \
  --restart=always \
  --name registry \
  registry:2

或者

# ctrl+c退出自动删除容器
docker run --rm \
    --name asynqmon \
    -p 8080:8080 \
    hibiken/asynqmon

# 自定义存储位置
docker run -d \
  -p 5000:5000 \
  --restart=always \
  --name registry \
  -v $HOME/_docker/registry:/var/lib/registry \
  registry:2

docker run -d -p 5000:5000 --restart=always --name registry \
    -v `pwd`/config.yml:/etc/docker/registry/config.yml \
    registry:2
```


##### 使用docker实战
```
-d, --detach=false      # 指定容器运行于前台还是后台，默认为false   
-i, --interactive=false # 打开STDIN，用于控制台交互  
-t, --tty=false         # 分配tty设备，该可以支持终端登录，默认为false  
-u, --user=""           # 指定容器的用户  
-a, --attach=[]         # 登录容器（必须是以docker run -d启动的容器）
-w, --workdir=""        # 指定容器的工作目录 
-c, --cpu-shares=0      # 设置容器CPU权重，在CPU共享场景使用  
-e, --env=[]            # 指定环境变量，容器中可以使用该环境变量  
-m, --memory=""         # 指定容器的内存上限  
-P, --publish-all=false # 指定容器暴露的端口  
-p, --publish=[]        # 指定容器暴露的端口 
-h, --hostname=""       # 指定容器的主机名  
-v, --volume=[]         # 给容器挂载存储卷，挂载到容器的某个目录  
--volumes-from=[]       # 给容器挂载其他容器上的卷，挂载到容器的某个目录
--cap-add=[]            # 添加权限，权限清单详见：http://linux.die.net/man/7/capabilities  
--cap-drop=[]           # 删除权限，权限清单详见：http://linux.die.net/man/7/capabilities  
--cidfile=""            # 运行容器后，在指定文件中写入容器PID值，一种典型的监控系统用法  
--cpuset=""             # 设置容器可以使用哪些CPU，此参数可以用来容器独占CPU  
--device=[]             # 添加主机设备给容器，相当于设备直通  
--dns=[]                # 指定容器的dns服务器  
--dns-search=[]         # 指定容器的dns搜索域名，写入到容器的/etc/resolv.conf文件  
--entrypoint=""         # 覆盖image的入口点  
--env-file=[]           # 指定环境变量文件，文件格式为每行一个环境变量  
--expose=[]             # 指定容器暴露的端口，即修改镜像的暴露端口  
--link=[]               # 指定容器间的关联，使用其他容器的IP、env等信息  
--lxc-conf=[]           # 指定容器的配置文件，只有在指定--exec-driver=lxc时使用  
--name=""               # 指定容器名字，后续可以通过名字进行容器管理，links特性需要使用名字  
--net="bridge"          # 容器网络设置:
                            # bridge 使用docker daemon指定的网桥     
                            # host 	//容器使用主机的网络  
                            # container:NAME_or_ID  >//使用其他容器的网路，共享IP和PORT等网络资源  
                            # none 容器使用自己的网络（类似--net=bridge），但是不进行配置 
--privileged=false      # 指定容器是否为特权容器，特权容器拥有所有的capabilities  
--restart="no"          # 指定容器停止后的重启策略:
                            # no：容器退出时不重启  
                            # on-failure：容器故障退出（返回值非零）时重启 
                            # always：容器退出时总是重启  
--rm=false              # 指定容器停止后自动删除容器(不支持以docker run -d启动的容器)  
--sig-proxy=true        # 设置由代理接受并处理信号，但是SIGCHLD、SIGSTOP和SIGKILL不能被代理
```

参考：https://raw.githack.com/jaywcjlove/docker-tutorial/7a41060/index.html
