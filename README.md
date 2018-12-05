# learning-springcloud

## 配置注释

```
# 应用名称，Eureka 默认会取 spring.application.name 的配置
spring.application.name=EUREKA-SERVER

##########
# Eureka #
########## 
# 服务实例 ID，采用随机字符串，可在单机启动多个实例
eureka.instance.instance-id=${spring.application.name}:${random.value}
# 优先使用 IP 地址作为主机名
eureka.instance.prefer-ip-address=true
# 服务续约客户端发送心跳间隔
eureka.instance.lease-renewal-interval-in-seconds=10
# 服务续约服务端收到心跳后等待时间上限
eureka.instance.lease-expiration-duration-in-seconds=20
# 开发环境开启
eureka.server.enable-self-preservation=false
# 主机名
eureka.instance.hostname=localhost
# 是否向注册中心注册
eureka.client.register-with-eureka=false
# 是否需要检索服务
eureka.client.fetch-registry=false
# 注册中心地址
eureka.client.service-url.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/

################
# Spring Admin #
################
# Spring Admin 地址
spring.boot.admin.client.url=http://localhost:9100
# 优先使用 IP 地址作为主机名
spring.boot.admin.client.instance.prefer-ip=true
# 登录用户名
spring.boot.admin.client.username=admin
# 登录密码
spring.boot.admin.client.password=admin

##########
# Config #
##########
# Git 地址
spring.cloud.config.server.git.uri=https://github.com/smallmenu/learning-springcloud-config.git
# Git 用户名
spring.cloud.config.server.git.username=
# Git 密码
spring.cloud.config.server.git.password=

# 客户端开启配置主动发现
spring.cloud.config.discovery.enabled=true
# 指定配置中心服务ID
spring.cloud.config.server-id=CONFIG-SERVER
# 指定获取的配置 profile
spring.cloud.config.profile=product
# 指定分支
spring.cloud.config.label=master
```
