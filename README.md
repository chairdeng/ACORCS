# 项目配置与发布

## 修改配置文件

application-dev.properties为开发及线下环境配置，默认启用该配置，由application.properties中的spring.profiles.active=dev指定。
application-prod.properties为生产环境配置文件，请在该配置中设置线上MySQL数据库配置及调整Rabbitmq配置。
## 执行SQL脚本
在线上MySQL创建库并执行database/create.sql中的SQL脚本

## Maven打包
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
## 线上启动
执行
`
java -Dspring.profiles.active=prod -jar wni-1.0.0.jar
`
-Dspring.profiles.active=prod 意为执行配置文件为application-prod.properties

# 系统接口调用

## WNI空间查询

### 调用方式

系统接口调用方式为Restful风格，支持GET和POST两种请求方式。如果请求报文较大建议使用POST方式进行请求。

### 请求地址

http://{部署服务器IP地址}:{部署端口默认8080}/spatial/affected

### 请求参数

* geometry（必选） 要查询的几何形状，格式为GeoJSON格式，支持Point和LineString类型。
* queryTime（可选）要查询的时间点，格式为yyyy-MM-dd HH:mm:ss，需要精确到秒，默认为当前时间。

### 返回数据

返回数据格式为JSON   
返回数据为对该点或线有影响的WNI数据清单。如果没有任何影响则返回“[]”   
如果返回数据中不为空，则列表中的每一项都为一个WNI数据实体。

#### WNI实体基础字段

* header WNI头 枚举值
* contentsKind 气象类型 枚举值，选项为CLOUD,JET STREAM,STORM CENTRE,TROPOPAUSE,TURBULENCE,VOLCANO.  
* notice 报文实体包括字段：type（String），elem（String），dataname（String），updated（Date），basetime（Date），validtime（Date）.   
* geographic WNI报文所描述的天气现象的地理数据，格式为GeoJSON，contentsKind不同所表示的地理类型也不同.

#### 其他字段（根据contentsKind类型不同所包含的额外字段有所差别）

* CLOUD 包含字段cloudDistribution（String），cloudType（String），altitudes（int），airframeIcing（String），extendedDegree（int）
* JET STREAM 包含字段 无
* STORM CENTRE 包含字段 stormName（String），type（String）
* TROPOPAUSE 包含字段significance（String），altitudes（int数组）
* TURBULENCE 包含字段extendedDegree（String），altitudes（int数组）
* VOLCANO 包含字段featureName（String），timeSignificance（String），specialClouds（String）

### 调用事例

1. GET方式调用
>http://localhost:8080/spatial/affected?geometry={ "type": "Point", "coordinates": [42.44, 18.6] }&queryTime=2016-01-15 13:00:00

## 自定义限制
* 返回数据增加contentsKind为CUSTOM的自定义类型
* CUSTOM具有属性code（String）；level（int）；basetime（yyyy-MM-dd HH:mm:ss），validtime（yyyy-MM-dd HH:mm:ss）
### 调用方式

仅支持POST请求

### 请求地址

http://{部署服务器IP地址}:{部署端口默认8080}/spatial/geometry/polygen 多边形限制区创建
http://{部署服务器IP地址}:{部署端口默认8080}/spatial/geometry/circle 圆形限制区创建

### 编码方式

Content-Type application/json;charset=UTF-8

### 请求内容（RequestBody）
* 多边形区域创建
```json
{
    "code": "123123123",
    "level": 1,
    "validtime":"2019-02-19 12:00:00",
    "basetime":"2017-02-19 12:00:00",
    "geographic": {
        "coordinates": [
            [
                [
                    6.53,
                    85.61
                ],
                [
                    6.82,
                    84.06
                ],
                [
                    9.38,
                    81.6
                ],
                [
                    9.14,
                    79.86
                ],
                [
                    5.69,
                    80.58
                ],
                [
                    4.56,
                    82.44
                ],
                [
                    5.51,
                    85.79
                ],
                [
                    6.53,
                    85.61
                ]
            ]
        ],
        "type": "Polygon"
    }
}
```

* 圆形区域创建

```json
{
    "code": "123123124",
    "level": 1,
    "validtime":"2019-02-19 12:00:00",
    "basetime":"2017-02-19 12:00:00",
    "center": {
        "coordinates": [
            [
                [
                    6.53,
                    85.61
                ]
            ]
        ],
        "type": "Point"
    },
    "radius":12.00
}
```

## 错误码

HttpStatus:200请求正确响应。
HttpStatus:400错误的请求，具体错误见返回报文中的code及message，返回报文code：0001数据校验错误，错误字段及原因详细见message，0002：数据格式错误，错误字段及原因见message，0003，唯一键约束错误，如创建请求中的code需要唯一。
HttpStatus:500服务器内部未知错误，返回报文code为9999
