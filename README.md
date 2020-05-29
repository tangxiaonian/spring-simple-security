# spring-simple-security

JWT整合 spring-security 实现 token 登录。

security-acl 权限控制模块。

- 控制哪些接口需要有哪些角色才能访问。
- 向外暴露登录注册接口

核心类: JwtAuthenticationTokenFilter 验证token。

order-consumer，user-consumer 依赖 security-acl 模块。
