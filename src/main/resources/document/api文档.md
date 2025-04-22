# 接口文档


**简介**:接口文档

**HOST**:localhost:8123

**联系人**:


**Version**:1.0

**接口路径**:/v2/api-docs?group=默认分组


[TOC]



# 错误响应示例

```javascript
{
  "code": 0,
  "msg": "未知错误",
  "data": null
}
```

**说明**:报错有关的信息会在msg字段带回，前端只需要用弹窗提示用户

# 测试接口


## 测试接口

**接口地址**:`/api/test`


**请求方式**:`GET`

**请求数据类型**:`application/x-www-form-urlencoded`

**响应数据类型**:`*/*`

**接口描述**:

**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«string»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||string||
|msg||string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": "",
	"msg": ""
}
```


# 用户接口


## 用户登录


**接口地址**:`/api/user/login`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "password": "",
  "username": ""
}
```

**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userDTO|userDTO|body|true|UserDTO|UserDTO|
|&emsp;&emsp;password|||true|string||
|&emsp;&emsp;username|||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«UserLoginVO»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||UserLoginVO|UserLoginVO|
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;token|token|string||
|&emsp;&emsp;username|用户名|string||
|msg||string||


**响应示例**:
```javascript
{
  "code": 1,
  "msg": null,
  "data": {
    "id": 1,
    "username": "admin",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc0NTM3OTQ2NH0.r3kNHIjMnr30Dof6ECZGXROi3YZf3lEf8ubzc2NonM8_Mo5qBF1o2BGw61BcjzSDt-6BmU8DQfwLt258Nz8XFg"
  }
}
```


## 用户注册


**接口地址**:`/api/user/register`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "password": "",
  "username": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userDTO|userDTO|body|true|UserDTO|UserDTO|
|&emsp;&emsp;password|||true|string||
|&emsp;&emsp;username|||true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|msg||string||


**响应示例**:
```javascript
{
  "code": 1,
  "msg": null,
  "data": null
}
```


# 课程接口


## 添加课程


**接口地址**:`/api/course`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "courseName": "",
  "courseType": 0,
  "credits": 0,
  "id": 0,
  "preRequisiteCourseIds": [],
  "totalHours": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|courseDTO|courseDTO|body|true|CourseDTO|CourseDTO|
|&emsp;&emsp;courseName|||true|string||
|&emsp;&emsp;courseType|||true|integer(int32)||
|&emsp;&emsp;credits|||true|integer(int32)||
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;preRequisiteCourseIds|若不传参默认先修课程为空||false|array|integer(int64)|
|&emsp;&emsp;totalHours|||true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|msg||string||


**响应示例**:
```javascript
{
  "code": 1,
  "msg": null,
  "data": null
}
```


## 修改课程数据

**接口地址**:`/api/course`

**请求方式**:`PUT`

**请求数据类型**:`application/json`

**响应数据类型**:`*/*`


**接口描述**:

**请求示例**:


```javascript
{
  "courseName": "",
  "courseType": 0,
  "credits": 0,
  "id": 0,
  "preRequisiteCourseIds": [],
  "totalHours": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|courseDTO|courseDTO|body|true|CourseDTO|CourseDTO|
|&emsp;&emsp;courseName|为空则不修改此项，下同||false|string||
|&emsp;&emsp;courseType|||false|integer(int32)||
|&emsp;&emsp;credits|||false|integer(int32)||
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;preRequisiteCourseIds|数组为空则不修改||false|array|integer(int64)|
|&emsp;&emsp;totalHours|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|msg||string||


**响应示例**:
```javascript
{
  "code": 1,
  "msg": null,
  "data": null
}
```


## 删除课程

**接口地址**:`/api/course/{id}`


**请求方式**:`DELETE`

**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result|
|204|No Content||
|401|Unauthorized||
|403|Forbidden||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||object||
|msg||string||


**响应示例**:
```javascript
{
  "code": 1,
  "msg": null,
  "data": null
}
```

## 获取用户所有课程


**接口地址**:`/api/course/{userId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId|userId|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«List«Course»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|code||integer(int32)|integer(int32)|
|data||array|Course|
|&emsp;&emsp;courseName||string||
|&emsp;&emsp;courseType||integer(int32)||
|&emsp;&emsp;credits||integer(int32)||
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;totalHours||integer(int32)||
|&emsp;&emsp;userId||integer(int64)||
|msg||string||


**响应示例**:
```javascript
{
  "code": 1,
  "msg": null,
  "data": [
    {
      "id": 1,
      "courseName": "高等数学1",
      "credits": 7,
      "totalHours": 72,
      "courseType": 0,
      "userId": 1
    },
    {
      "id": 2,
      "courseName": "高等数学2",
      "credits": 7,
      "totalHours": 72,
      "courseType": 0,
      "userId": 1
    },
    {
      "id": 3,
      "courseName": "高等数学3",
      "credits": 7,
      "totalHours": 72,
      "courseType": 0,
      "userId": 1
    }
  ]
}
```