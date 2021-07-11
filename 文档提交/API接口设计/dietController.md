### 1. 获取膳食信息
- url : /diet/diets
- type : get
- response
> fail

```
{
    "status" : 1,
    "msg" : "系统异常"
}
```
> success

```
{
    "status" : 0,
    "data":[
        {
            "id": 1,
            "name": "元气满满",
            "food1": "馒头",
            "food2": "小米粥",
            "food3": "咸菜",
            "food4": "酱肉包",
            "food5": "null",
            "description": "营养搭配的早餐",
            "price": 6,
            "taste": "清淡",
            "picture": "null"
        }
    ]
}

```
### 2. 通过id获取某个膳食具体信息
- url : /diet/editDietForm/{id}
- type : get
- request

```
{
    "id" : 1
}
例如：/diet/editDietForm/{1}
```
- reponse
> fail

```
{
    "status" : 1,
    "msg" : "系统异常"
}

```
> success

```
{
    "status" : 0
    "data" : {
            "id": 1,
            "name": "元气满满",
            "food1": "馒头",
            "food2": "小米粥",
            "food3": "咸菜",
            "food4": "酱肉包",
            "food5": "null",
            "description": "营养搭配的早餐",
            "price": 6,
            "taste": "清淡",
            "picture": "null"
        }
}

```
### 3. 增加膳食信息
- url: /diet/addDiet/{name}{food1}{food2}{food3}{food4}{food5}{description}{taste}{price}{picture}
- type : post
- request

```
{
        "name": "能量套餐",
        "food1": "米饭",
        "food2": "西红柿鸡蛋汤",
        "food3": "红烧排骨",
        "food4": "凉拌黄瓜",
        "food5": "炒时蔬",
        "description": "均衡搭配，菜品丰富",
        "price": 13,
        "taste": "不辣",
        "picture": "null"

}
例如：/diet/addDiet/{能量套餐}{米饭}{西红柿鸡蛋汤}{红烧排骨}{凉拌黄瓜}{炒时蔬}{均衡搭配，菜品丰富}{13}{不辣}{null}
```
- response
> fail

```
[
    {
    "status" : 1,
    "msg" : "系统异常"
    },
    {
        "status" : 2,
        "msg" : "请勿重复添加"
    }
]
```
> success

```
{
    "status" : 0
    "data" :    [
        {
            "id": 1,
            "name": "元气满满",
            "food1": "馒头",
            "food2": "小米粥",
            "food3": "咸菜",
            "food4": "酱肉包",
            "food5": "null",
            "description": "营养搭配的早餐",
            "price": 6,
            "taste": "清淡",
            "picture": "null"
        },
        {
            "id": 2,
            "name": "能量套餐",
            "food1": "米饭",
            "food2": "西红柿鸡蛋汤",
            "food3": "红烧排骨",
            "food4": "凉拌黄瓜",
            "food5": "炒时蔬",
            "description": "均衡搭配",
            "price": 13,
            "taste": "不辣",
            "picture": "null"
        }
    ]
}
```
### 4. 修改膳食信息
- url: /diet/editDiet/{name}{food1}{food2}{food3}{food4}{food5}{description}{taste}{price}{picture}
- type: "put"
- request

```
{
        "name": "能量套餐",
        "food1": "米饭",
        "food2": "西红柿鸡蛋汤",
        "food3": "红烧排骨",
        "food4": "凉拌黄瓜",
        "food5": "炒时蔬",
        "description": "均衡搭配，菜品丰富",
        "price": 14,
        "taste": "不辣",
        "picture": "null"
}
例如：/diet/addDiet/{能量套餐}{米饭}{西红柿鸡蛋汤}{红烧排骨}{凉拌黄瓜}{炒时蔬}{均衡搭配，菜品丰富}{14}{不辣}{null}
```
- response
> fail

```
{
    "status" : 1,
    "msg" : "系统异常"
}
```
> success

```
{
    "status" : 0
    "data" :    [
        {
            "id": 1,
            "name": "元气满满",
            "food1": "馒头",
            "food2": "小米粥",
            "food3": "咸菜",
            "food4": "酱肉包",
            "food5": "null",
            "description": "营养搭配的早餐",
            "price": 6,
            "taste": "清淡",
            "picture": "null"
        },
        {
            "id": 2,
            "name": "能量套餐",
            "food1": "米饭",
            "food2": "西红柿鸡蛋汤",
            "food3": "红烧排骨",
            "food4": "凉拌黄瓜",
            "food5": "炒时蔬",
            "description": "均衡搭配",
            "price": 14,
            "taste": "不辣",
            "picture": "null"
        }
    ]
}
```
### 5. 删除膳食
- url: /diet/deleteDiet/{id}
- type:"delete"
- request

```
{
    "id" : 2
}
例如：/diet/deleteDiet/{2}
```
- response
> fail

```
{
    "status" : 1,
    "msg" : "系统异常"
}
```
> success

```
{
    "status" : 0
    "data" :    [
        {
            "id": 1,
            "name": "元气满满",
            "food1": "馒头",
            "food2": "小米粥",
            "food3": "咸菜",
            "food4": "酱肉包",
            "food5": "null",
            "description": "营养搭配的早餐",
            "price": 6,
            "taste": "清淡",
            "picture": "null"
        }
}
```






















