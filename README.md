# Avatars
>Ps:为什么叫Avatar呢？其实简单，就是想提供一套灵活的，可以根据不用业务来‘画像’的轻应用框架。

本项目使用的是目前较为流行的Springboot+Dubbo+mybaties+Vue+Gradle(Multiple modules)来构造的基础架构，是一套比较完备的的企业级SOA架构，项目还整合Redis,MQ,Jenkin部署等中间件及自动化构建部署脚本，企业用户可以拿来即用，初学者可以逐级掌握的一套框架。

### 中台服务[avatar-service](https://github.com/wligang/avatars/tree/master/avatar-service)
 提供大中台支持、Rpc提供者、DB持久化等等核心服务

### 前台PAI服务[avatar-web-x](https://github.com/wligang/avatars/tree/master/avatar-web)
  提供各微服务API服务,如MQ消费者、RPC消费者、TCP、HTTP等等

### 后台服务[avatar-admin](https://github.com/wligang/avatars/tree/master/avatar-admin)
  提供大后台管理，权限校验，角色配置，监控等等
  
### 后台前端[avatar-admin-web](https://github.com/wligang/avatars/tree/master/avatar-admin-web)
  使用nodeJs,vue框架搭设的后台框架
  
基础框架搭起来了，只需要添砖加瓦了

### Hido互联
这个工程已经发布完成了一个简单版本，在微信小程序搜索“Hido互联”或者扫描下面的小程序码,即可体验

###下面是运行的效果

<img src="https://img-blog.csdnimg.cn/20190506121130640.jpg" width = "180" height = "300" div align="left"/>

<img src="https://img-blog.csdnimg.cn/20190506121145624.jpg" width = "180" height = "320" div align="left"/>

<img src="https://img-blog.csdnimg.cn/20190506121156678.jpg" width = "180" height = "320" div align="left"/>

<img src="https://img-blog.csdnimg.cn/20190506121209283.jpg" width = "180" height = "320" div align=""/>



### 小程序码
![https://img-blog.csdnimg.cn/20190505115944855.jpg](https://img-blog.csdnimg.cn/20190505115944855.jpg)



如果觉得项目不错，可以点个Star哦😉😉😉



