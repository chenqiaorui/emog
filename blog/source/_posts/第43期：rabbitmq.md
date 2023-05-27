---
title: 第43期：rabbitmq
date: 2023-05-26 20:58:39
tags:
---
### 原理
```
Producer(生产者) -> Exchange（交换机）-> Queue（队列）-> Consumer（消费者）

- 交换机，接收生产者发送的消息，并根据路由键发送给指定队列
- 队列，存储从交换机发来的消息
```
参考：https://www.macrozheng.com/mall/reference/rabbitmq_start.html#windows%E4%B8%8B%E7%9A%84%E5%AE%89%E8%A3%85