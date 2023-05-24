---
title: 第13期：cron
date: 2023-05-21 15:55:49
tags:
---
### 常用示例
crontab -e 打开配置文件
-l # 显示当前用户的定时任务内容
-r # 删除用户/var/spool/cron/ 定时任务
```
*/1 * * * * /usr/sbin/chroot --userspec=nobody.nobody / sh -c "echo 'nobody' > /tmp/nobody.log "
```

### cron表达式验证
```
https://www.iamwawa.cn/crontab.html
```