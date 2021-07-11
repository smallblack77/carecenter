###  **客户管理** 
### 1. 查询客户列表getCustomerLsit()
### /customer/customers
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
        "id": 1,
        "name": "张三",
        "sex": "男",
        "age": 50,
        "height": 170.0,
        "weight": 70.0,
        "birthday": "1971-4-1",
        "attention": "null"
    },
    {
        "id": 2,
        "name": "李四",
        "sex": "女",
        "age": 56,
        "height": 167.0,
        "weight": 60.0,
        "birthday": "1965-10-10",
        "attention": "null"
    },
    {
        "id": 6,
        "name": "王五",
        "sex": "女",
        "age": 34,
        "height": 167.0,
        "weight": 58.0,
        "birthday": "1965-10-10",
        "attention": "null"
    }
]
```
### 2. 删除客户信息deleteCustomer()
### /customer/{id}
- type:"delete"
- request

```
id,如: /customer/{1}
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
        "id": 2,
        "name": "李四",
        "sex": "女",
        "age": 56,
        "height": 167.0,
        "weight": 60.0,
        "birthday": "1965-10-10",
        "attention": "null"
    },
    {
        "id": 6,
        "name": "王五",
        "sex": "女",
        "age": 34,
        "height": 167.0,
        "weight": 58.0,
        "birthday": "1965-10-10",
        "attention": "null"
    }
]
```
### 3. 修改客户信息editCustomer()
### /customer/{id}
- type:"patch"

- request

```
name,sex,age,height,weight,birthday,attention
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
        "id": 1,
        "name": "颜三",
        "sex": "男",
        "age": 50,
        "height": 180.0,
        "weight": 70.0,
        "birthday": "1971-4-1",
        "attention": "不能吃辣"
    }
]
```
### 4. 新增客户addCustomer()
### /customer/{name}{sex}{age}{height}{weight}{birthday}{attention}
- type:"post"
- request

```
name,sex,age,height,weight,birthday,attention
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
        "id": 1,
        "name": "李六",
        "sex": "男",
        "age": 67,
        "height": 167.0,
        "weight": 72.0,
        "birthday": "1975-8-4",
        "attention": "null"
    }
]
```

###  **入住管理** 
### 1. 查看所有入住信息getCheckinList()
### /customer/checkin/checkins
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
        "id": 1,
        "customerID": 1,
        "bedId": 1,
        "startTime": "2021-07-06"
    },
    {
        "id": 2,
        "customerID": 2,
        "bedId": 2,
        "startTime": "2021-07-08"
    }
]
```
### 2. 删除入住信息deleteCheckin()
### /customer/checkin/{id}
- type:"delete"
- request

```
id,如: /customer/checkin/{1}
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
        "id": 2,
        "customerID": 2,
        "bedId": 2,
        "startTime": "2021-07-08"
    }
]
```
### 3. 修改入住信息editCheckin()
### /customer/checkin/{id}
- type:"patch"

- request

```
custid,bedid,starttime
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
        "id": 1,
        "customerID": 1,
        "bedId": 1,
        "startTime": "2021-07-06"
    },
    {
        "id": 2,
        "customerID": 2,
        "bedId": 2,
        "startTime": "2020-05-08"
    }
]
```
### 4. 新增入住addCheckin()
### /customer/checkin/{custid}{bedid}{starttime}
- type:"post"
- request

```
custid,bedid,starttime
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
        "id": 3,
        "customerID": 3,
        "bedId": 3,
        "startTime": "2021-05-08"
    }
]
```

###  **退住管理** 
### 1. 查看所有退住信息getCheckoutList()
### /customer/checkout/checkouts
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
        "id": 1,
        "customerID": 1,
        "bedId": 1,
        "startTime": "2021-07-06",
        "endTime": "2022-06-01"
    },
    {
        "id": 2,
        "customerID": 2,
        "bedId": 2,
        "startTime": "2021-07-08",
        "endTime": "2022-06-10"
    }
]
```
### 2. 删除退住信息deleteCheckout()
### /customer/checkout/{id}
- type:"delete"
- request

```
id,如: /customer/checkout/{1}
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
        "id": 2,
        "customerID": 2,
        "bedId": 2,
        "startTime": "2021-07-08",
        "endTime": "2022-06-10"
    }
]
```
### 3. 修改退住信息editCheckout()
### /customer/checkout/{id}
- type:"patch"

- request

```
custid,bedid,starttime,endtime
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
        "id": 1,
        "customerID": 1,
        "bedId": 1,
        "startTime": "2021-07-06",
        "endTime": "2022-06-01"
    },
    {
        "id": 2,
        "customerID": 2,
        "bedId": 2,
        "startTime": "2020-05-08",
        "endTime": "2024-09-01"
    }
]
```
### 4. 新增退住addCheckout()
### /customer/checkout/{custid}{bedid}{starttime}{endtime}
- type:"post"
- request

```
custid,bedid,starttime,endtime
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
        "id": 3,
        "customerID": 3,
        "bedId": 3,
        "startTime": "2021-05-08",
        "endTime": "2023-06-01"
    }
]
```

###  **外出管理** 
### 1. 查看所有外出记录getAllOutList()
### /customer/out/outs
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
        "id": 1,
        "customerId": 1,
        "reason": "探亲",
        "startTime": "2021-07-10",
        "expectReturnTime": "2021-07-12",
        "actualReturnTime": "2021-07-14",
        "airPhone": "123456"
    },
    {
        "id": 2,
        "customerId": 2,
        "reason": "疫情",
        "startTime": "2021-07-01",
        "expectReturnTime": "2021-07-24",
        "actualReturnTime": "2021-07-31",
        "airPhone": "111111"
    }
]
```
### 2. 删除外出信息deleteOut()
### /customer/out/{id}
- type:"delete"
- request

```
id,如: /customer/out/{1}
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
        "id": 2,
        "customerId": 2,
        "reason": "疫情",
        "startTime": "2021-07-01",
        "expectReturnTime": "2021-07-24",
        "actualReturnTime": "2021-07-31",
        "airPhone": "111111"
    }
]
```
### 3. 修改外出信息editOut()
### /customer/out/{id}
- type:"patch"

- request

```
custid,reason,starttime,exptime,acttime,aidphone
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
        "id": 1,
        "customerId": 1,
        "reason": "小孩高考",
        "startTime": "2021-07-10",
        "expectReturnTime": "2021-07-12",
        "actualReturnTime": "2021-07-14",
        "airPhone": "123123"
    },
    {
        "id": 2,
        "customerId": 2,
        "reason": "疫情",
        "startTime": "2021-07-01",
        "expectReturnTime": "2021-07-24",
        "actualReturnTime": "2021-07-31",
        "airPhone": "111111"
    }
]
```
### 4. 新增外出addOut()
### /customer/out/{custid}{reason}{starttime}{exptime}{acttime}{aidphone}
- type:"post"
- request

```
custid,reason,starttime,exptime,acttime,aidphone
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
        "id": 3,
        "customerId": 2,
        "reason": "亲戚结婚",
        "startTime": "2020-07-01",
        "expectReturnTime": "2020-07-14",
        "actualReturnTime": "2021-07-10",
        "airPhone": "111222"
    }
]
```