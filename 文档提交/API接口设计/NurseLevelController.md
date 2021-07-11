###  **护工等级管理** 
### 1. 查看所有护工等级信息getNurseLevelList()
### /nurseLevel/viewNurseLevelList
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
        "levelId": 1,
        "levelName": "超级"
    },
    {
        "levelId": 2,
        "levelName": "高级"
    },
    {
        "levelId": 3,
        "levelName": "中级"
    },
    {
        "levelId": 4,
        "levelName": "普通"
    },
    {
        "levelId": 5,
        "levelName": "一般般"
    }
]
```
### 2. 删除护工等级deleteNurLevel()
### /nurseLevel/deleteNurLevel/{id}
- type:"delete"
- request

```
id,如: /nurseLevel/deleteNurLevel/{1}
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
        "levelId": 1,
        "levelName": "超级"
    }
]
```
### 3. 修改护工等级信息editNurLevel()
### /nurseLevel/editNurLevel/{id}
- type:"patch"

- request

```
levelname
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
        "levelId": 1,
        "levelName": "非常高级"
    }
]
```
### 4. 新增护工等级addNurLevel()
### /nurseLevel/addNurLevel/{id}{levelname}
- type:"post"
- request

```
id,levelname
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
        "levelId": 6,
        "levelName": "十分一般"
    }
]
```