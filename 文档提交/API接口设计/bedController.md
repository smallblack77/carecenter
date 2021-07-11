### 1.获取某个不可住床位和客户的关系
- url:/bed/viewBedDetail/{bedId}
- type:"get"
- request

```
{
    "bedId" = 1
}
例如：/bed/viewBedDetail/{1}
```
- response
> fail

```
{
    "status" : 1,
    "msg" : "服务器异常"
}
```
> success

```
{
    "status" : 0,
    "data": {
        "id": 2,
        "customerID": 1,
        "bedId": 1,
        "startTime": "2007-09-01",
        "endTime": "2020-01-01"
    }
}
```
### 2. 修改bed信息
- url：/bed/updateBed/{bedId}{roomNum}{bedStatus}{sort}{description}
- type : "put"
- request
```
{
    "bedId" : 1,
    "roomNum" : 202,
    "bedStatus" : 0,
    "sort" : "四人间",
    "desccription" : "新床"
}
例如：/bed/updateBed/{1}{202}{0}{"四人间"}{"新床"}

```
- reponse
> fail

```
{
    "status" : 1,
    "msg" : "服务器异常"
}
```
> success

```
{
    "status" : 0,
    "data" : {
        "id": 1,
        "roomNum": "202",
        "bedStatus": false,
        "sort": "四人间",
        "description": "新床",
    }
}
```
### 3. 新增床位
- url: /bed/addBed/{bedId}{roonNum}{bedStatus}{sort}{description}
- type : "post"
- request
```
{
    "bedId" : 2,
    "roomNum" : 203,
    "bedStatus" : 1,
    "sort" : "四人间",
    "desccription" : "舒适"
}
例如：/bed/updateBed/{2}{203}{1}{"四人间"}{"舒适"}

```
- response
> fail

```
{
    "status" : 1,
    "msg" : "服务器异常"
}
```
> success

```
{
    "status" : 0,
    "data" :[
        {
            "id": 1,
            "roomNum": "202",
            "bedStatus": false,
            "sort": "四人间",
            "description": "新床",
        },
        {
            "id": 2,
            "roomNum": "203",
            "bedStatus": true,
            "sort": "四人间",
            "description": "舒适",
        }
    ]
}
```
### 4. 删除床位信息
- url : /bed/deleteBed/{bedId}
- type : "delete"
- request

```
{
    "bedId" : 2
}
例如：/bed/deleteBed/{2}
```
- reponse
> fail

```
[
    {
        "status" : 1,
        "msg" : "服务器异常"
    },
    {
        "status" : 2,
        "msg" : "床还住人"
    }
]
```
> success

```
{
    "status" : 0,
    "data" :
        [
            {
                "id": 1,
                "roomNum": "202",
                "bedStatus": false,
                "sort": "四人间",
                "description": "新床",
            }
        ]
}
```
### 5. 获取所有床位信息
- url: /bed/bedForm
- type: get
- reponse
> fail

```
{
    "status" : 1,
    "msg" : "服务器异常"
}
```
> success
```
{
    "status" : 0,
    "data" :
        [
            {
                "id": 1,
                "roomNum": "202",
                "bedStatus": false,
                "sort": "四人间",
                "description": "新床",
            }
        ]
}
```
### 6. 通过bedId获取某个床位信息
- url : /bed/updateBedForm/{bedId}
- type : get
- request

```
{
    bedId : 1
}
例如 : /bed/updateBedForm/{1}
```
- response
> fail

```
{
    "status" : 1,
    "msg" : "服务器异常"
}
```
> success

```
{
    "status" : 0,
    "data" : {
        "id": 1,
        "roomNum": "202",
        "bedStatus": false,
        "sort": "四人间",
        "description": "新床",
    }
}
```





 











 





