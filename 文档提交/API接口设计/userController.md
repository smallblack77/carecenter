### 1.注册
- url:/account/registration/{userId}{username}{password}{sex}{email}{age}{phoneNumber}{role}
- type:"put"
- request

```
{
    "userId": 10002,
    "username": "Bob",
    "password": 123,
    "sex": "男",
    "email": "123456@qq.com",
    "age": 40,
    "phoneNumber": "667788",
    "role": "医生"
}
例如：/account/registration/{10002}{"Bob"}{"123"}{"男"}{"12345@qq.com”}{40}{"667788"}{"医生"}

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
    "status": 0,
    "data" : {
        "userId": 10002,
        "username": "Bob",
        "password": 123,
        "sex": "男",
        "email": "123456@qq.com",
        "age": 40,
        "phoneNumber": "667788",
        "role": "医生"
    }
}
```
### 2.获取所有User信息
- url:/account/getAllUser
- type:"get"
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
[
    "status":0
    data:
    {
        "userId": 10001,
        "username": "Lily",
        "password": "2333",
        "sex": "男",
        "email": "66@qq.com",
        "age": 23,
        "phoneNumber": "88888888",
        "role": "医生"
    },
    {
        "userId": 10005,
        "username": "陈明",
        "password": "123",
        "sex": "男",
        "email": "77@qq.com",
        "age": 30,
        "phoneNumber": "11111111",
        "role": "医生"
    },
    {
        "userId": 10006,
        "username": "大卫",
        "password": "996",
        "sex": "男",
        "email": "88@qq.com",
        "age": 32,
        "phoneNumber": "22222222",
        "role": "医生"
    },
    {
        "userId": 10007,
        "username": "刘鑫",
        "password": "778",
        "sex": "男",
        "email": "99@qq.com",
        "age": 27,
        "phoneNumber": "33333333",
        "role": "护士"
    },
    {
        "userId": 10008,
        "username": "小红",
        "password": "007",
        "sex": "女",
        "email": "11@qq.com",
        "age": 20,
        "phoneNumber": "13908465",
        "role": "护士"
    }
]
```
### 3.登录
- url:/account/login/{userId}{password}
- type:"get"
- request

```
{
    userId : 10001,
    password : "2333"
}
例如：/account/login/{10001}{"2333"}
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
    "status": 0,
    "data" : {
        "userId": 10001,
        "username": "Lily",
        "password": "2333",
        "sex": "男",
        "email": "66@qq.com",
        "age": 23,
        "phoneNumber": "88888888",
        "role": "医生"
    }
}
```





