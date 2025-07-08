### 配置思路

##### 后端项目初始配置：

​	1.配置拦截器

​	2.配置全局异常捕获器

​	3.配合 Result类、ResultPage类，引入 http 请求的 json 序列化和反序列化插件，配置消息转化器

​	4.在 WebMvcConfiguration 注册拦截器、消息转化器

​	5.配置 Mybatis-plus 的乐观锁拦截器



##### 前端项目初始配置：



​	





### 报错解决

###### 配置 websocket 后无法进行单元测试

添加 @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) 的注解在 Test 类





### 问题解答

