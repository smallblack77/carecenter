### 1. 获取某个admin信息
#### /admin/{adminName}
- type : get
- request
```
    例如：/admin/1
```
- response
> fail
```
{
    "status": 1,
    "msg": "服务器异常"
}

```
> success
```
{
    "status" : 0,
    "data" : 
    {
        "adminName" : "1"
        "password" : "123456"
    }
}
```
### 2. 修改管理员信息
#### /admin/{adminName}{password}
- type : 'patch'
- request
```
{
    adminName:123
    password:123456
}
例如：/admin/{123}{123456}
```
- response
> fail
```
{
    "status": 1,
    "msg": "服务器异常"
}
```
> success
```
{
    "status" : 0,
    "data" : {
        "adminName":"123"
        "password":"123456"
    }
}
```

### 3. 修改用户信息
#### /admin/user/{userId}{username}{sex}{age}{phoneNumber}{role}{password}{email}
- type : 'patch'
- request
```
{
    userId:10001
    username:Lily
    sex:1
    age:20
    phoneNumber:12345
    role:护士
    password:1
    email:123@qq.com
}
例如：/admin/user/{10001}{Lily}{1}{20}{12345}{护士}{1}{123@qq.com}
```
- response
> fail
```
{
    "status": 1,
    "msg": "服务器异常"
}
```
> success
```
{
    "status" : 0,
    "data" : {
        "userId": 10001,
        "username": "Lily",
        "password": "1",
        "sex": "女",
        "email": "123@qq.com",
        "age": 20,
        "phoneNumber": "12345",
        "role": "护士"
    }
}
```
### 4. 新增用户信息
#### url : /admin/user/{userId}{username}{sex}{age}{phoneNumber}{role}{password}{email}
- type : "put"
- request
```
{
    "userId": 10002,
    "username": "Bob",
    "password": "123",
    "sex": "男",
    "email": "123456@qq.com",
    "age": 40,
    "phoneNumber": "667788",
    "role": "医生"
}
```
- response
> fail
```
{
    "status": 1,
    "msg": "服务器异常"
}
```
> success
```
{
    "status" : 0,
    "data" : {
        {
            "userId": 10001,
            "username": "Lily",
            "password": "1",
            "sex": "女",
            "email": "123@qq.com",
            "age": 20,
            "phoneNumber": "12345",
            "role": "护士"
        },{
            "userId": 10002,
            "username": "Bob",
            "password": "123",
            "sex": "男",
            "email": "123456@qq.com",
            "age": 40,
            "phoneNumber": "667788",
            "role": "医生"
        }
    }
}
```
### 5. 删除用户信息
#### /admin/user/{userId}
- type : 'delete'
- request
```
{
    userId:10003
}
例如：/admin/user/{10003}
```
- response
> fail
```
{
    "status": 1,
    "msg": "服务器异常"
}
```
> success
```
{
    "status" : 0,
    "data" : {
         {
        "userId": 10001,
        "username": "Lily",
        "password": null,
        "sex": "女",
        "email": "123@qq.com",
        "age": 20,
        "phoneNumber": "12345",
        "role": "护士"
    },
    {
        "userId": 10002,
        "username": "Bob",
        "password": null,
        "sex": "男",
        "email": "667788@qq.com",
        "age": 24,
        "phoneNumber": "667788",
        "role": "医生"
    }
    }
}
```
