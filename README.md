#项目配置与发布
##修改配置文件
application-dev.properties为开发及线下环境配置，默认启用该配置，由application.properties中的spring.profiles.active=dev指定。
application-prod.properties为生产环境配置文件，请在该配置中设置线上MySQL数据库配置及调整Rabbitmq配置。
##Maven打包
1.将Maven配置文件中增加阿里云的Maven镜像
`
<mirror>
    <id>nexus-aliyun</id>
    <mirrorOf>*</mirrorOf>
    <name>Nexus aliyun</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror> 
`
