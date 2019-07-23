<p align="center">
  <a href="http://avatar.wlgdo.com">
   <img alt="avatar-github-img" src="https://repository-images.githubusercontent.com/182984652/8992b700-7337-11e9-9c04-df8b5f02c21e">
  </a>
</p>

# Avatars
>Ps: Why is it called Avatar? In fact, it is simple to provide a flexible application framework that can “paint” different business needs.

This project uses the current mainstream micro-service architecture technology, using Springboot, springbootAdmin, Actuator monitoring (supporting custom Endpoint), Dubbo, mybaties, Vue front-end, Quartz cluster
, Gradle (Multiple-Modules-Build), mybatisPlus (multiple, dynamic data source), OAuth2.0 authorization service (SaaS Enterprise Edition), WeChat applet, etc.
The project also integrates middleware and automated build deployment technologies such as Redis, MQ, and Jenkin deployment.
It is a complete enterprise-level SOA architecture, which can be used by enterprise users. A set of frameworks that beginners can master step by step [continuous support]  

[中文版](https://github.com/wligang/avatars/edit/master/README_CN.md)  

### Centrl Service[avatar-service](https://github.com/wligang/avatars/tree/master/avatar-service)
 Provide core services such as large and medium platform support, Rpc provider, DB persistence, etc.

### Front-end API service [avatar-web-x](https://github.com/wligang/avatars/tree/master/avatar-web)
  Provide micro-application API services such as MQ Consumer, RPC Consumer, TCP, HTTP, etc.

### Background Service [avatar-admin](https://github.com/wligang/avatars/tree/master/avatar-admin)
  Provide large background management, permission verification, role configuration, monitoring, etc.
  
### Backend [avatar-admin-web](https://github.com/wligang/avatars/tree/master/avatar-admin-web)
  Using the nodeJs container, the background management platform architecture set up by the vue framework
  
### Task Scheduling Center[avatar-dynamic-quartz](https://github.com/wligang/avatars/tree/master/avatar-dynamic-quartz)
  Use multi-tenancy technology to achieve distributed task scheduling management, dynamic data source switching, task scheduling management and configuration center

### Data Processing Center[avatar-service-dynamic-datasource](https://github.com/wligang/avatars/tree/master/avatar-service-dynamic-datasource) 
   Including multi-data source strategy, dynamic data source, read-write separation, multi-tenancy, custom quartz multi-data source, multi-tenant cluster strategy, can be used as data center, and later consider adding data processing, such as encryption and decryption, report processing and so on.

### Monitoring background[avatar-web-monitor](https://github.com/wligang/avatars/tree/master/avatar-web-monitor)
   Monitor application interface data, system performance parameters, health index, etc., support custom endpoint cut-in

### Instructions
  - Because the project is a multi-module project built with gradle, you need to have a gradle environment in the development environment. If you are not used to it or don't like gradle, you can still use the relevant commands to convert the gradle build into a maven build, which is easy to operate.
  - The entire project is based on multi-module dependencies, but the project architecture itself is loosely coupled and can be selected based on its own business requirements.
  - The project will continuously update new features

### Hido Interconnect

The release completes a simple application Hido interconnection, search for "Hido interconnection" in WeChat applet or scan the small program code below to experience the small program application under Avatar architecture.

### The following is the effect of running

<img src="https://img-blog.csdnimg.cn/20190506121130640.jpg" width = "180" height = "300" div align="left"/>

<img src="https://img-blog.csdnimg.cn/20190506121145624.jpg" width = "180" height = "320" div align="left"/>

<img src="https://img-blog.csdnimg.cn/20190506121156678.jpg" width = "180" height = "320" div align="left"/>

<img src="https://img-blog.csdnimg.cn/20190506121209283.jpg" width = "180" height = "320" div align=""/>



### MINI Program by Wechat
![https://img-blog.csdnimg.cn/20190505115944855.jpg](https://img-blog.csdnimg.cn/20190505115944855.jpg)



If you think the project is good, you can order a Star.
