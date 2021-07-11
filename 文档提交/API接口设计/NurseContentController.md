### 护工管理 
### 1 护工基本信息管理
### 1.1 查询护工基本信息列表viewList()
### /nurseContent/viewList
- type:"get"
- request

```
无
```
- response
fail
```
{
    "status": 1,
    "msg": "服务器异常"
}
```
success

```
[
    "status": 0,
    "data":
     {
        "nurseId": "10001",
        "name": "刘辉",
        "sex": "男",
        "age": 23,
        "price": 2000,
        "description": "认真仔细",
        "levelId": 4,
        "levelName": "普通"
    },
    {
        "nurseId": "10002",
        "name": "夏华",
        "sex": "女",
        "age": 25,
        "price": 4000,
        "description": "做事认真负责，不马虎",
        "levelId": 1,
        "levelName": "超级"
    }
]
```
### 1.2. 删除护工基本信息deleteNurContent()
### /nurseContent/{nurId}
- type:"delete"
- request

```
nurId,如: /nurseContent/{10001}
```
- response
fail
```
{
    "status": 1,
    "msg": "服务器异常"
}
```
success

```
[
    "status": 0,
    "data":
    {
        "nurseId": "10001",
        "name": "刘辉",
        "sex": "男",
        "age": 23,
        "price": 2000,
        "description": "认真仔细",
        "levelId": 4,
        "levelName": "普通"
    },
    {
        "nurseId": "10002",
        "name": "夏华",
        "sex": "女",
        "age": 25,
        "price": 4000,
        "description": "做事认真负责，不马虎",
        "levelId": 1,
        "levelName": "超级"
    }
]
```
### 1.3. 修改护工基本信息updateNurContent()
### /nurseContent/{nurId}
- type:"patch"

- request

```
nurId,name,sex,age,price,description,levelId
```
- response
fail
```
{
    "status": 1,
    "msg": "服务器异常"
}
```
success

```
[
    "status": 0,
    "data":
    {
        "nurseId": "10001",
        "name": "刘辉",
        "sex": "男",
        "age": 23,
        "price": 2000,
        "description": "认真仔细",
        "levelId": 4,
        "levelName": "普通"
    }
]
```
### 1.4. 新增客户addNurContent()
### /nurContent/{nurId}{name}{sex}{age}{description}{price}{levelId}
- type:"post"
- request

```
nurId,name,sex,age,description,price,levelId
```
- response
fail
```
{
    "status": 1,
    "msg": "服务器异常"
}
```
success

```
[
    "status": 0,
    "data":
    {
        "nurseId": "10002",
        "name": "夏华",
        "sex": "女",
        "age": 25,
        "price": 4000,
        "description": "做事认真负责，不马虎",
        "levelId": 1,
        "levelName": "超级"
    }
]
```

###  **2.护理记录管理** 
### 2.1. 查看所有护理记录信息getCheckinList()
### /nurseContent/viewNurseRecord
- type:"get"
- request

```
无
```
- response
fail
```
{
    "status": 1,
    "msg": "服务器异常"
}
```
success

```
[
    "status": 0,
    "data":
    {    
        "id":"10001",
        "customerId":1,
        "nurseId":"10001",
        "content":"无",
        "startTime":"2020-7-11",
        "endTime":"2020-8-9"
    },
    {
        "id":"10002",
        "customerId":2,
        "nurseId":"10002",
        "content":"无",
        "startTime":"2020-7-8",
        "endTime":"2021-7-19"
    }
]
```
### 2.2 删除护理记录deleteNurRecord()
### /nurseContent/deleteNurRecord/{id}
- type:"delete"
- request

```
id,如: /nurseContent/deleteNurRecord/{10001}
```
- response
fail
```
{
    "status": 1,
    "msg": "服务器异常"
}
```
success

```
[
    "status": 0,
    "data":
    {    
        "id":"10001",
        "customerId":1,
        "nurseId":"10001",
        "content":"无",
        "startTime":"2020-7-11",
        "endTime":"2020-8-9"
    }
]
```
### 2.3. 修改护理记录updateNurRecord()
### /nurseContent/updateNurRecord
- type:"patch"

- request

```
customerId,nurseId,content,starttime,endTime
```
- response
fail
```
{
    "status": 1,
    "msg": "服务器异常"
}
```
success

```
[
    "status": 0,
    "data":
    {    
        "id":"10001",
        "customerId":1,
        "nurseId":"10001",
        "content":"无",
        "startTime":"2020-7-11",
        "endTime":"2020-8-9"
    },
    {
        "id":"10002",
        "customerId":2,
        "nurseId":"10002",
        "content":"无",
        "startTime":"2020-7-8",
        "endTime":"2021-7-19"
    }
]
```
### 2.4. 新增护理记录addNurseRecord()
### /customer/checkin/{customerId}{nurseId}{content}{starttime}{endTime}
- type:"post"
- request

```
customerId,nurseId,content,starttime,endTime
```
- response
fail
```
{
    "status": 1,
    "msg": "服务器异常"
}
```
success

```
[
    "status": 0,
    "data":
    {    
        "id":"10003",
        "customerId":3,
        "nurseId":"10001",
        "content":"无",
        "startTime":"2020-7-11",
        "endTime":"2020-8-9"
    }
]
```