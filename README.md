#项目配置与发布
##修改配置文件
application-dev.properties为开发及线下环境配置，默认启用该配置，由application.properties中的spring.profiles.active=dev指定。
application-prod.properties为生产环境配置文件，请在该配置中设置线上MySQL数据库配置及调整Rabbitmq配置。
##执行SQL脚本
在线上MySQL创建库并执行database/create.sql中的SQL脚本
##Maven打包
1.将Maven配置文件中增加阿里云的Maven镜像
```xml
<mirror>
    <id>nexus-aliyun</id>
    <mirrorOf>*</mirrorOf>
    <name>Nexus aliyun</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror> 
```
2.Maven打包
执行
`
mvn clean package
`
##线上启动
执行
`
java -Dspring.profiles.active=prod -jar wni-1.0.0.jar
`
-Dspring.profiles.active=prod 意为执行配置文件为application-prod.properties
#系统接口调用
##调用方式
系统接口调用方式为Restful风格，支持GET和POST两种请求方式。如果请求报文较大建议使用POST方式进行请求。
##请求地址
http://{部署服务器IP地址}:{部署端口默认8080}/spatial/affected
##请求参数
* geometry（必选） 要查询的几何形状，格式为GeoJSON格式，支持Point和LineString类型。
* queryTime（可选）要查询的时间点，格式为yyyy-MM-dd HH:mm:ss，需要精确到秒，默认为当前时间。
